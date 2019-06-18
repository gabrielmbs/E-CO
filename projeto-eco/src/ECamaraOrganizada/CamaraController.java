package ECamaraOrganizada;

import util.Contador;
import util.Validador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class CamaraController {



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

    public CamaraController() {
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
        if (!existeLei(codigo)) {
            throw new NullPointerException("Erro ao exibir projeto: projeto inexistente");
        } else {
            return this.proposicoesDeLeis.get(codigo).toString();
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
     * Método responsável por separar artigos com vírgula e espaço, como no
     * formato a seguir: "artigo1, artigo2, ..., artigoN".
     * @param artigos String base para geração da nova string dos artigos concatenados.
     * @return
     */
    private String contatenaArtigos(String artigos){
        if(artigos.contains(",")){
            String artigosConcatenados = "";
            for(String caractere : artigos.split(",")){
                artigosConcatenados += caractere + ", ";

            }
            return artigosConcatenados.substring(0, artigosConcatenados.length() - 2);
        }
        return artigos;
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
        if(!this.proposicaoController.existeLei(codigo)){
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }

        if (!this.comissoes.containsKey("CCJC")) {
            throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
        }
        if("plenario".equals(proposicao.getLocalDeVotacao())){
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
        }
        if(!proposicao.getProposicaoAtiva()) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
    }

    /**
     * Esse método auxiliar é responsável por realizar a votação de uma PL não conclusiva
     * em uma determinada comissão, ele retorna um boolean que indica o resultado da
     * votação.
     *
     * @param codigo código da proposta a ser votada.
     * @param votosFavoraveis votos favoráveis à aprovação da proposta.
     * @param chao votos mínimos necessários para aprovação da proposta.
     * @param proximoLocal próximo local no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean votarComissaoNConc(String codigo, int votosFavoraveis, int chao, String proximoLocal){
        ProposicaoAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        if ("plenario".equals(proximoLocal)) {
            proposicao.setSituacao("EM VOTACAO (Plenario - 1o turno)");
        }
        boolean retorno = false;
        if(votosFavoraveis >= chao){
            retorno = true;
        }
        proposicao.setLocalDeVotacao(proximoLocal);
        proposicao.setPassouNaCCJC(true);
        return retorno;
    }

    /**
     * Esse método auxiliar é responsável por realizar a votação de uma PL conclusiva
     * em uma determinada comissão, ele retorna um boolean que indica o resultado da
     * votação.
     *
     * @param codigo código da proposta a ser votada.
     * @param votosFavoraveis votos favoráveis à aprovação da proposta.
     * @param chao votos mínimos necessários para aprovação da proposta.
     * @param proximoLocal próximo local no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean votarComissaoPLConc(String codigo, int votosFavoraveis, int chao, String proximoLocal) {
        boolean retorno = false;
        ProposicaoAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        if (votosFavoraveis < chao && !proposicao.getPassouNaCCJC()) {
            proposicao.setProposicaoAtiva(false);
            proposicao.setPassouNaCCJC(true);
        }else if(votosFavoraveis >= chao && !proposicao.getPassouNaCCJC()){
            proposicao.setPassouNaCCJC(true);
            proposicao.setSituacao("EM VOTACAO (" + proximoLocal + ")");
            proposicao.setLocalDeVotacao(proximoLocal);
            retorno = true;
        }else if(votosFavoraveis >= chao){
            if(proximoLocal.equals("-")){
                proposicao.setSituacao("APROVADO");
                this.deputadoController.incrementaLeisDeputado(proposicao.getDniAutor());
                proposicao.setProposicaoAtiva(false);
            }
            retorno = true;
        }else if(votosFavoraveis < chao){
            proposicao.setProposicaoAtiva(false);
            if(proximoLocal.equals("-")){
                proposicao.setSituacao("ARQUIVADO");

            }
        }
        return retorno;
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
     * Esse método auxiliar retorna um boolean que informa se uma PLP ou uma PEC foi Aprovada
     * ou Arquivada no Plenário. Além disso ele atualiza o atributo númeroDeLeis do deputado e
     * atualiza o atributo situação da proposição.
     *
     * @param codigo código da proposição a ser votada.
     * @param votosFavoraveis votos à favor da aprovação.
     * @param chao mínimo de votos necessário para aprovar a proposição.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean aprovadaOuArquivada(String codigo, int votosFavoraveis, int chao) {
        boolean retorno = false;
        ProposicaoAbstract proposicao = this.proposicaoController.buscaProposicao(codigo);
        if (votosFavoraveis >= chao) {
            retorno = true;
            if (proposicao.getPassouNoPlenario()) {
                this.proposicaoController.buscaProposicao(codigo).setSituacao("APROVADO");
                this.deputadoController.incrementaLeisDeputado(proposicao.getDniAutor());
                proposicao.setProposicaoAtiva(false);
            } else {
                proposicao.setSituacao("EM VOTACAO (Plenario - 2o turno)");
                proposicao.setPassouNoPlenario(true);
            }
        } else {
            proposicao.setSituacao("ARQUIVADO");
            proposicao.setProposicaoAtiva(false);

        }
        return retorno;
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
}