package controllers;

import entidades.*;
import util.Validador;
import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ControllerGeral {
    private PessoaController pessoaController;

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

    /**
     * Controller de proposições.
     */
    private ProposicaoController proposicaoController;

    private Persistencia persistencia;

    /**
     * Constrói o controller geral.
     */
    public ControllerGeral() {
        this.base = new HashSet<>();
        this.validador = new Validador();
        this.comissoes = new HashMap<>();
        this.pessoaController = new PessoaController();
        this.proposicaoController = new ProposicaoController();
        this.persistencia = new Persistencia();
    }

    /**
     * Os métodos cadastrarPessoa a seguir são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * @param nome       nome da pessoa a ser cadastrada.
     * @param dni        dni da pessa a ser cadastrada.
     * @param estado     estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        return this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
    }

    /**
     * O método cadastrarPessoa a seguir e o método cadastrarPessoa anterior são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * @param nome       nome da pessoa a ser cadastrada.
     * @param dni        dni da pessa a ser cadastrada.
     * @param estado     estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @param partido    partido que a pessoa a ser cadastrada é filiada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        return this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);
    }

    /**
     * Método responsável por cadastrar um deputado no sistema, cujos dados (dni e dataInicio), todos do tipo String,
     * são passados como parâmetro.
     *
     * @param dni          dni da pessoa a se tornar deputado.
     * @param dataDeInicio data de ínicio do mandato do deputado.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        this.pessoaController.cadastrarDeputado(dni, dataDeInicio);
    }

    /**
     * Método responsável por acesssar o Mapa que armazena as pessoas cadastradas e retornar uma
     * representação em String da pessoa cujo DNI foi passado como parâmetro.
     *
     * @param dni dni da pessoa a ser consultado no sistema.
     * @return a representação em String da pessoa, caso ela já tenha sido cadastrado.
     */
    public String exibirPessoa(String dni) {
        return this.pessoaController.exibirPessoa(dni);
    }

    /**
     * Método responsável por cadastrar um partido no sistema, recebendo como parâmetro o nome do partido.
     * <p>
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
     * Método responsável por cadastrar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * @param dni        dni do autor do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        endereço url do projeto.
     * @param ano        ano de criação do projeto.
     * @param conclusivo situção conclusiva do projeto
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);
        if (this.pessoaController.existePessoa(dni)) {
            if (this.pessoaController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Método responsável por cadastrar um Projeto de Lei Complementar no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni        dni da pessoa deputada autor da lei.
     * @param ementa     ementa da lei.
     * @param interesses interesses da lei.
     * @param url        endereço url da lei.
     * @param artigos    artigos da constituição sobre os quais a lei vai atuar.
     * @param ano        ano de criação do projeto
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        if (this.pessoaController.existePessoa(dni)) {
            if (this.pessoaController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Método responsável por cadastrar um Projeto de Emenda Constituicional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni        dni da pessoa deputada autor da lei.
     * @param ementa     ementa da lei.
     * @param interesses interesses da lei.
     * @param url        endereço url da lei.
     * @param artigos    artigos da constituição sobre os quais a lei vai atuar.
     * @param ano        ano de criação do projeto
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        if (this.pessoaController.existePessoa(dni)) {
            if (this.pessoaController.pessoaEhDeputado(dni)) {
                return this.proposicaoController.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Método responsável por acesssar o Mapa que armazena os projetos de lei cadastrados e retornar uma
     * representação em String da lei cujo código foi passado como parâmetro.
     *
     * @param codigo dni da pessoa a ser consultado no sistema.
     * @return a representação em String da pessoa, caso ela já tenha sido cadastrado.
     */
    public String exibirProjeto(String codigo) {
        this.validador.validaString(codigo, "Erro ao exibir projeto: codigo de lei nao pode ser vazio ou nulo");
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
     * @param tema      representa o tema da comissão
     * @param politicos lista de politicos separados por ",".
     */
    public void cadastrarComissao(String tema, String politicos) {
        this.validador.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
        this.validador.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
        if (this.comissoes.containsKey(tema)) {
            throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
        }
        String[] arrayDeDNIs = geraArrayDeDNIsValidos(politicos);
        this.comissoes.put(tema, new Comissao(tema, arrayDeDNIs));
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta em uma determinada comissão, ele recebe como
     * parâmetro o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e o próximo
     * local no qual a porposta será votada. O método retorna um boolean que indica o resultado da votação.
     *
     * @param codigo           código da proposta.
     * @param statusGovernista status da proposta.
     * @param proximoLocal     próximo locla no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validaVotarComissao(codigo, statusGovernista, proximoLocal);

        String localDeVotacao = this.proposicaoController.buscaProposicao(codigo).getLocalDeVotacao();
        int votosFavoraveis = calculaVotosComissao(codigo, localDeVotacao, statusGovernista);
        int participantes = this.comissoes.get(localDeVotacao).getDNIs().length;
        int chao = (participantes / 2 + 1);

        PropostaAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        Pessoa deputado = this.pessoaController.buscaPessoa(proposicao.getDniAutor());

        return this.proposicaoController.votarComissao(codigo, proximoLocal, chao, votosFavoraveis, deputado);
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta no plenário, ele recebe como parâmetro o código
     * da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE) e uma String com o DNI de todos os
     * deputados presentes separados por vírgula. O método retorna um boolean que indica o resultado da votação.
     *
     * @param codigo código da proposição a ser votada.
     * @param statusGovernista status da proposição a ser votada.
     * @param presentes String com todos os presentes.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        PropostaAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        Pessoa deputado = this.pessoaController.buscaPessoa(proposicao.getDniAutor());
        int totalDeDeputados = this.pessoaController.totalDeputados();
        String[] deputados = presentes.split(",");
        proposicao.verificaQuorum(deputados, totalDeDeputados);
        int votosFavoraveis = calculaVotosPlenario(codigo, statusGovernista, deputados);
        return this.proposicaoController.votarPlenario(codigo, deputados, deputado, votosFavoraveis, totalDeDeputados);
    }

    /**
     * Método responsável pela configuração de uma estratégia de busca de proposições para uma determinada pessoa, este
     * método recebe como parâmetro o DNI da pessoa que vai utilizar a estratégia para buscar uma proposição e uma
     * estratégia de busca, que pode ser (CONSTITUCIONAL, APROVACAO ou CONCLUSAO).
     *
     * @param dni dni da pessoa que procura uma determinada proposição.
     * @param estrategia estrátegia de busca que será utilizada.
     */
    public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
        this.validador.validaString(dni, "Erro ao configurar estrategia: pessoa nao pode ser vazia ou nula");
        this.validador.validaDNI(dni, "Erro ao configurar estrategia: ");
        this.validador.validaString(estrategia, "Erro ao configurar estrategia: estrategia vazia");
        this.pessoaController.configurarEstrategiaPropostaRelacionada(dni, estrategia);
    }

    /**
     * Método responsável pela busca de uma proposição (ainda em tramitação) para uma detemrinada pessoa com base nos
     * interesses em comum entre a pessoa e a proposição, em caso de empate nos interesses, a busca é realizada usando
     * como critério a estrátegia de busca definida pela pessoa, que por padrão é a estratégia CONSTITUCIONAL.
     *
     * @param dni dni da pessoa que deseja buscar uma determinada proposição.
     * @return uma String que representa a proposição encontrada.
     */
    public String pegarPropostaRelacionada(String dni) {
        this.validador.validaString(dni, "Erro ao pegar proposta relacionada: pessoa nao pode ser vazia ou nula");
        this.validador.validaDNI(dni, "Erro ao pegar proposta relacionada: ");

        Pessoa pessoa = this.pessoaController.buscaPessoa(dni);
        Set<String> interessesPessoa = new HashSet<>(Arrays.asList(pessoa.getInteresses().split(",")));
        Set<PropostaAbstract> interessesPropostas = new HashSet<>(this.proposicaoController.getProposicoesDeLeis().values());
        List<PropostaAbstract> propostasRelevantes = new ArrayList<>();
        int soma = 0;
        for (PropostaAbstract proposta : interessesPropostas) {
            int aux = 0;
            for (String interesse : interessesPessoa) {
                if (proposta.getInteresses().contains(interesse)) {
                    aux++;
                }
            }
            if (aux > soma && proposta.getProposicaoAtiva()) {
                soma = aux;
                propostasRelevantes.clear();
                propostasRelevantes.add(proposta);
            } else if (aux == soma && aux != 0 && proposta.getProposicaoAtiva()) {
                propostasRelevantes.add(proposta);
            }
        }

        String result = pessoa.getEstrategiaBuscaProposta().pegarPropostaRelacionada(propostasRelevantes);
        return result;
    }

    /**
     * Esse método é responsável por limpar as informações das coleções presentes no ControllerGeral
     * e nos demais controllers do sistema. Criando arquivos da extensão .dat vazios no diretório files/ .
     */
    public void limparSistema() {
        this.persistencia.limpar("mapaComissoes");
        this.persistencia.limpar("base");
        this.pessoaController.limparSistema();
        this.proposicaoController.limparSistema();
    }

    /**
     * Esse método é responsável por fazer a persistência dos dados, ou seja, ele grava todas as informações
     * das coleções presentes no ControllerGeral e nos demais controller do sistema. Criando um arquivo da
     * extensão .dat com as informações no diretório files/ .
     */
    public void salvarSistema() {
        this.persistencia.salvar(this.comissoes, "mapaComissoes");
        this.persistencia.salvar(this.base, "base");
        this.proposicaoController.salvarSistema();
        this.pessoaController.salvarSistema();
    }

    /**
     * Esse método é responsável por carregar os dados do ControllerGeral e dos demais controllers do sistema
     * armazenados diretório files/ .
     */
    public void carregarSistema() {
        Object aux = this.persistencia.carregar("mapaComissoes");
        this.comissoes = new HashMap<>();
        if (aux != null) {
            this.comissoes = (Map<String, Comissao>) aux;
        }

        Object aux2 = this.persistencia.carregar("base");
        this.base = new HashSet<>();
        if (aux2 != null) {
            this.base = (Set<String>) aux2;
        }
        this.proposicaoController.carregarSistema();
        this.pessoaController.carregarSistema();
    }

    public String exibirTramitacao(String codigo) {
        return this.proposicaoController.exibirTramitacao(codigo);
    }

    /**
     * Gera um array de Strings, que representa os dnis dos politicos.
     *
     * @param politicos uma String, que representa dnis separados por ","
     * @return Um array de Strings, que contem as dnis.
     */
    private String[] geraArrayDeDNIsValidos(String politicos) {
        String[] arrayDeDNIs = politicos.split(",");
        for (String dni : arrayDeDNIs) {
            this.validador.validaDNI(dni, "Erro ao cadastrar comissao: ");
            if (!this.pessoaController.existePessoa(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa inexistente");
            }
            if (!this.pessoaController.pessoaEhDeputado(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
            }
        }
        return arrayDeDNIs;
    }

    /**
     * Método auxiliar que indica se um deputado e uma proposição têm interesses em comum.
     *
     * @param deputado deputado a ser analisado.
     * @param codigo   codigo da proposição a ser analisada.
     * @return boolean indicando se tem ou não interesse em comum.
     */
    private boolean intEmComum(Pessoa deputado, String codigo) {
        String[] interessesDep = deputado.getInteresses().split(",");
        String[] interessesPl = this.proposicaoController.buscaProposicao(codigo).getInteresses().split(",");
        for (int i = 0; i < interessesDep.length; i++) {
            for (int j = 0; j < interessesPl.length; j++) {
                if (interessesDep[i].equals(interessesPl[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa a quantiade de votos favoráveis à
     * aprovação de de determinada proposta em uma comissão.
     *
     * @param codigo           código da proposta a ser votada.
     * @param comissao         comissão na quala  proposta será votada.
     * @param statusGovernista status da proposta.
     * @return um inteiro que indica a quantiade de votos favoráveis.
     */
    private int calculaVotosComissao(String codigo, String comissao, String statusGovernista) {
        int votosFavoraveis = 0;
        for (String dni : this.comissoes.get(comissao).getDNIs()) {
            Pessoa deputado = this.pessoaController.buscaPessoa(dni);
            boolean ehDaBase = this.base.contains(deputado.getPartido());
            boolean temInteressesEmComum = intEmComum(deputado, codigo);
            if (deputado.getFuncao().votoPolitico(statusGovernista, ehDaBase, temInteressesEmComum)) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa a quantidade de votos favoráveis
     * à aprovação de uma determinada proposta no plenário.
     *
     * @param codigo           código da proposta a ser votada.
     * @param statusGovernista status da proposta a ser votada.
     * @param presentes        deputados presentes no plenário.
     * @return um inteiro que indica a quantidade de votos favoráveis.
     */
    private int calculaVotosPlenario(String codigo, String statusGovernista, String[] presentes) {
        int votosFavoraveis = 0;
        for (String dni : presentes) {
            Pessoa deputado = this.pessoaController.buscaPessoa(dni);
            boolean ehDaBase = this.base.contains(deputado.getPartido());
            boolean temInteressesEmComum = intEmComum(deputado, codigo);
            if (deputado.getFuncao().votoPolitico(statusGovernista, ehDaBase, temInteressesEmComum)) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    /**
     * Método responsável por validar o cadastro de um projeto de lei.
     *
     * @param dni        dni da lei a ser cadastrada.
     * @param ementa     ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url        url da lei a ser cadastrada.
     * @param ano        ano da lei a ser cadastrada.
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
     * @param dni        dni da lei a ser cadastrada.
     * @param ementa     ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url        url da lei a ser cadastrada.
     * @param ano        ano da lei a ser cadastrada.
     * @param artigos    artigos com os quais a lei irá trabalhar
     */
    private void validaCadastrarLeiComArtigo(String dni, String ementa, String interesses, String url, int ano,
                                             String artigos) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);
        this.validador.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
    }

    /**
     * Esse método auxiliar é responsável por realizar as validações necessárias para o
     * método votarComissão. Ele lança exceções quando dados inválidos são passados como
     * parâmetro.
     *
     * @param codigo           código da proposta a ser votada.
     * @param statusGovernista status da proposta a ser votada.
     * @param proximoLocal     próximo local no qual a proposta será votada.
     */
    private void validaVotarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validador.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
        PropostaAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        if (!"GOVERNISTA".equals(statusGovernista) && !"OPOSICAO".equals(statusGovernista) &&
                !"LIVRE".equals(statusGovernista)) {
            throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
        }
        if (!this.comissoes.containsKey("CCJC")) {
            throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
        }
        if (!this.proposicaoController.existeLei(codigo)) {
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }
        if ("plenario".equals(proposicao.getLocalDeVotacao())) {
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
        }
        if (!proposicao.getProposicaoAtiva()) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
    }
}