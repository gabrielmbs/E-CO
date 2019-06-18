package controllers;

import entidades.*;
import util.Validador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ControllerGeral {
    private DeputadoController deputadoController;

    /**
     * Conjunto de partido.
     */
    private Set<String> base;

    /**
     * Representa mapa de comissoes, com a chave representando o tema e o valor a comissao.
     */
    private Map<String, Comissao> comissoes;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    private ProposicaoController proposicaoController;

    public ControllerGeral() {
        this.base = new HashSet<>();
        this.validador = new Validador();
        this.comissoes = new HashMap<>();
        this.deputadoController = new DeputadoController();
        this.proposicaoController = new ProposicaoController();
    }

    /**
     * Método responsável por cadastrar um partido no sistema, recebendo como parâmetro o nome do partido.
     * 
     * Checa-se se esse parâmetro é nulo ou vazio, e se for, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * @param partido partido a ser cadastrado.
     */
    public void cadastrarPartido(String partido) {
        this.validador.validaString(partido, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
        this.base.add(partido);
    }

    /**
     * Método responsável por exibir a base, que é formada por diversos partidos, todos eles em String.
     *
     * @return uma String com todos os partidos da base.
     */
    public String exibirBase() {
        List<String> listaPartidos = new ArrayList<>(this.base);
        listaPartidos.sort(String::compareTo);
        
        if (listaPartidos.size() == 0) {
            return "";
        }
        String resultado = "";
        for (String partido : listaPartidos) {
            resultado += partido + ",";
        }
        return resultado.substring(0, (resultado.length() - 1));
    }

    /**
     * Método responsável por acesssar o Mapa que armazena os projetos de lei cadastrados e retornar uma
     * representação em String da lei cujo código foi passado como parâmetro.
     *
     * @param codigo dni da pessoa a ser consultado no sistema.
     * @return a representação em String da pessoa, caso ela já tenha sido cadastrado.
     */
    public String exibirProjeto(String codigo) {
        this.validador.validaString(codigo,"Erro ao exibir projeto: codigo de lei nao pode ser vazio ou nulo");
        if (!this.proposicaoController.existeLei(codigo)) {
            throw new NullPointerException("Erro ao exibir projeto: projeto inexistente");
        } else {
            return this.proposicaoController.buscaProposicao(codigo).toString();
        }
    }

    /**
     * O método cadastraComissao serve para cadastrar no sistema uma comissão que possui um tema e uma lista de politicos
     * que o compõe.
     *
     * @param tema representa o tema da comissão
     * @param politicos lista de politicos separados por ",".
     */
    public void cadastrarComissao (String tema, String politicos){
        this.validador.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
        this.validador.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
        if (this.comissoes.containsKey(tema)) {
            throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
        }
        String[] arrayDeDNIs = geraArrayDeDNIsValidos(politicos);
        this.comissoes.put(tema, new Comissao(tema, arrayDeDNIs));
    }

    /**
     * Gera um array de Strings, que representa os dnis dos politicos.
     *
     * @param politicos uma String, que representa dnis separados por ","
     * @return Um array de Strings, que contem as dnis.
     */
    private String[] geraArrayDeDNIsValidos (String politicos){
        String[] arrayDeDNIs = politicos.split(",");
        for (String dni : arrayDeDNIs) {
            this.validador.validaDNI(dni, "Erro ao cadastrar comissao: ");
            if (!this.deputadoController.existePessoa(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa inexistente");
            }
            if (!this.deputadoController.pessoaEhDeputado(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
            }
        }
        return arrayDeDNIs;
    }


    /**
     * Esse método auxiliar retorna um inteiro que informa se foi aprovado ou não o voto.
     * 1 para aprovado - 1 para reprovado.
     *
     * @param codigo código da proposição.
     * @param statusGovernista status.
     * @return int.
     */
    private int votoPolitico(String codigo, String statusGovernista, String dni){
        Pessoa deputado = this.deputadoController.buscaPessoa(dni);
        boolean ehDaBase = this.base.contains(deputado.getPartido());
        boolean temInteressesEmComum = intEmComum(deputado, codigo);
        int saida = 0;
        if("GOVERNISTA".equals(statusGovernista) && ehDaBase) {
            saida = 1;
        }else if("OPOSICAO".equals(statusGovernista) && ehDaBase){
            saida = -1;
        }else if("LIVRE".equals(statusGovernista) && temInteressesEmComum){
            saida = 1;
        }else if("LIVRE".equals(statusGovernista) && !temInteressesEmComum){
            saida = -1;
        }
        return saida;
    }

    /**
     * Método auxiliar que indica se um deputado e uma proposição têm interesses em comum.
     *
     * @param deputado deputado a ser analisado.
     * @param codigo codigo da proposição a ser analisada.
     * @return boolean indicando se tem ou não interesse em comum.
     */
    private boolean intEmComum(Pessoa deputado, String codigo) {
        String[] interessesDep = deputado.getInteresses().split(",");
        String[] interessesPl = this.proposicaoController.buscaProposicao(codigo).getInteresses().split(",");
        for(int i=0; i < interessesDep.length; i++){
            for(int j = 0; j < interessesPl.length; j++){
                if(interessesDep[i].equals(interessesPl[j])){
                    return true;
                }
            }
        }
        return false;
    }

    public String exibirPessoa(String dni) {
        return this.deputadoController.exibirPessoa(dni);
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        return this.deputadoController.cadastrarPessoa(nome, dni, estado, interesses);
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        return this.deputadoController.cadastrarPessoa(nome, dni, estado, interesses, partido);
    }

    public void cadastrarDeputado(String dni, String dataDeInicio) {
        this.deputadoController.cadastrarDeputado(dni, dataDeInicio);
    }

    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);
        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validaVotarComissao(codigo, statusGovernista, proximoLocal);
        String localDeVotacao = this.proposicaoController.buscaProposicao(codigo).getLocalDeVotacao();
        int votosFavoraveis = calculaVotosComissao(codigo, localDeVotacao, statusGovernista);
        int chao = (this.comissoes.get(localDeVotacao).getDNIs().length / 2) + 1;
        Pessoa deputado = this.deputadoController.buscaPessoa(this.proposicaoController.buscaProposicao(codigo).getDniAutor());
        return this.proposicaoController.votarComissao(codigo, statusGovernista, proximoLocal, chao, votosFavoraveis, deputado);
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa a quantiade de votos favoráveis à
     * aprovação de de determinada proposta em uma comissão.
     *
     * @param codigo código da proposta a ser votada.
     * @param comissao comissão na quala  proposta será votada.
     * @param statusGovernista status da proposta.
     * @return um inteiro que indica a quantiade de votos favoráveis.
     */
    private int calculaVotosComissao(String codigo, String comissao, String statusGovernista) {
        int votosFavoraveis = 0;
        for (String dni : this.comissoes.get(comissao).getDNIs()) {
            if (votoPolitico(codigo, statusGovernista, dni) == 1) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        Pessoa deputado = this.deputadoController.buscaPessoa(this.proposicaoController.buscaProposicao(codigo).getDniAutor());
        int totalDeputados = this.deputadoController.totalDeputados();
        String[] deputados = presentes.split(",");
        verificaQuorum(codigo, deputados);
        int votosFavoraveis = calculaVotosPlenario(codigo, statusGovernista, deputados);
        return this.proposicaoController.votarPlenario(codigo, deputados, deputado, votosFavoraveis,
                                                        totalDeputados);
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa a quantidade de votos favoráveis
     * à aprovação de uma determinada proposta no plenário.
     *
     * @param codigo código da proposta a ser votada.
     * @param statusGovernista status da proposta a ser votada.
     * @param presentes deputados presentes no plenário.
     * @return um inteiro que indica a quantidade de votos favoráveis.
     */
    private int calculaVotosPlenario(String codigo, String statusGovernista, String[] presentes) {
        int votosFavoraveis = 0;
        for (String dni : presentes) {
            if (votoPolitico(codigo, statusGovernista, dni) == 1) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    /**
     * Esse método auxiliar verifica se há no plenário o quórum mínimo necessário
     * (quantiade mínima de deputados presentes) para que a votação da proposição
     * seja realizada. Caso o quórum mínimo não seja atendido, uma exceção do tipo
     * IllegalArgumentException é lançada.
     *
     * @param codigo
     * @param deputados
     */
    private void verificaQuorum(String codigo, String[] deputados) {
        ProposicaoAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        boolean ePL = "PL".equals(proposicao.getTipoDeProposicao());
        boolean ePLP = "PLP".equals(proposicao.getTipoDeProposicao());
        boolean ePEC = "PEC".equals(proposicao.getTipoDeProposicao());
        int totalDeputados = this.deputadoController.totalDeputados();
        if (ePL || ePLP) {
            int quorum = (totalDeputados / 2) + 1;
            if (deputados.length < quorum) {
                throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
            }
        } else if (ePEC) {
            int quorum = (((3/5) * totalDeputados) / 2) + 1;
            if (deputados.length < quorum) {
                throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
            }
        }
    }

    /**
     * Método responsável por validar o cadastro de um projeto de lei.
     *
     * @param dni dni da lei a ser cadastrada.
     * @param ementa ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url url da lei a ser cadastrada.
     * @param ano ano da lei a ser cadastrada.
     */
    private void validaCadastrarLei(String dni, String ementa, String interesses, String url, int ano) {
        this.validador.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar projeto: ");
    }

    /**
     * Método responsável por validar o cadastro de um projeto de lei
     * que opere sobre artigos da Constituição.
     *
     * @param dni dni da lei a ser cadastrada.
     * @param ementa ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url url da lei a ser cadastrada.
     * @param ano ano da lei a ser cadastrada.
     * @param artigos artigos com os quais a lei irá trabalhar
     */
    private void validaCadastrarLeiComArtigo(String dni, String ementa, String interesses, String url, int ano, String artigos) {
        validaCadastrarLei(dni,ementa, interesses, url, ano);
        this.validador.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
    }

    /**
     * Esse método auxiliar é responsável por realizar as validações necessárias para o
     * método votarComissão. Ele lança exceções quando dados inválidos são passados como
     * parâmetro.
     *
     * @param codigo código da proposta a ser votada.
     * @param statusGovernista status da proposta a ser votada.
     * @param proximoLocal próximo local no qual a proposta será votada.
     */
    private void validaVotarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validador.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
        ProposicaoAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        if(!"GOVERNISTA".equals(statusGovernista) && !"OPOSICAO".equals(statusGovernista) && !"LIVRE".equals(statusGovernista)){
            throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
        }
        if (!this.comissoes.containsKey("CCJC")) {
            throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
        }
        if(!this.proposicaoController.existeLei(codigo)){
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }
        if("plenario".equals(proposicao.getLocalDeVotacao())){
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
        }
        if(!proposicao.getProposicaoAtiva()) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
    }
}