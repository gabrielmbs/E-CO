package controllers;

import entidades.*;
import util.Contador;
import util.Validador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe controladora de Proposições, ela é responsavel por guardar e manipular os dados da classe PropostaAbstrat
 * e suas filhas.
 */
public class ProposicaoController {

    /**
     * Representa mapa onde se armazena as contagens específicas de cada tipo de lei em consonância
     * com seu ano de cadastro, com a chave no formato: "tipoDeLei AnoCadastro".
     * <p>
     * Permite o acompanhamento mais preciso da quantidade de um tipo específico de leis
     * cadastradas em um ano.
     */
    private Map<String, Contador> contadores;

    /**
     * Representa mapa onde se armazena as proposições de lei armazenadas no sistema,
     * com a chave no formato: "tipoDeLei ordemContagem/AnoDeCadastro".
     */
    private Map<String, PropostaAbstract> proposicoesDeLeis;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    /**
     * Atributo que será utilizado para o gerenciamento de arquivos.
     */
    private Persistencia persistencia;

    /**
     * Constrói o controller de proposições.
     */
    public ProposicaoController() {
        this.proposicoesDeLeis = new HashMap<>();
        this.validador = new Validador();
        this.contadores = new HashMap<>();
        this.persistencia = new Persistencia();
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

        String chaveContador = ano + "PL";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PL " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new PL(codigoLei, dni, ano, ementa, interesses, url,
                conclusivo));
        contador.incrementaContagem();
        return codigoLei;
    }

    /**
     * Retorna o mapa com todas as Proposições de Leis.
     *
     * @return o mapa de proposições.
     */
    public Map<String, PropostaAbstract> getProposicoesDeLeis() {
        return proposicoesDeLeis;
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
        String artigosConcatenados = contatenaArtigos(artigos);

        String chaveContador = ano + "PLP";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PLP " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new PLP(codigoLei, dni, ano, ementa,
                interesses, url,
                artigosConcatenados));
        contador.incrementaContagem();
        return codigoLei;
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
        String artigosConcatenados = contatenaArtigos(artigos);
        String chaveContador = ano + "PEC";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PEC " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new PEC(codigoLei, dni, ano, ementa, interesses,
                url, artigosConcatenados));
        contador.incrementaContagem();
        return codigoLei;
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta em uma determinada comissão, ele recebe como
     * parâmetro o código da proposta a ser votada, o próximo local no qual a porposta será votada, o chão que define
     * quantos votos são necessários para aprovar a proposição, os votos favoráveis à aprovação da proposição e o autor
     * da lei. O método retorna um boolean que indica se a proposição foi ou não aprovada.
     *
     * @param codigo          código da proposta.
     * @param proximoLocal    próximo locla no qual a proposta será votada.
     * @param chao            mínimo de votos necessários para aprovação da proposição.
     * @param votosFavoraveis votos favoráveis à aprovação da proposição.
     * @param autor           o autor da proposição a ser votada.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarComissao(String codigo, String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        PropostaAbstract proposicao = this.proposicoesDeLeis.get(codigo);
        return proposicao.votarComissao(proximoLocal, chao, votosFavoraveis, autor);
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta no plenário, ele recebe como parâmetro
     * o código da proposta a ser votada, um Array com os deputados presentes para votação, o deputado autor da proposição
     * a ser votada, o número de votos favoráveis à aprovação da proposição e o total de deputados cadastrados no sistema.
     * O método retorna um boolean que indica se a proposição foi o não aprovada.
     *
     * @param codigo          código da proposta.
     * @param deputados       Array com os deputados presentes.
     * @param deputado        o autor da lei.
     * @param votosFavoraveis quantidade de votos favoráveis à aprovação.
     * @param totalDeputados  total de deputados cadastrados no sistema.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarPlenario(String codigo, String[] deputados, Pessoa deputado, int votosFavoraveis, int totalDeputados) {
        PropostaAbstract proposicao = this.proposicoesDeLeis.get(codigo);
        return proposicao.votarPlenario(deputados, deputado, votosFavoraveis, totalDeputados);
    }

    /**
     * Método responsável por exibir a tramitação de um determinado projeto de lei,
     * recuperada pela pesquisa no mapa que as armazena por meio de seu código.
     *
     * @param codigo String que identifica o projeto de lei.
     * @return String com todas as situações e pareceres da lei ao longo de suas votações.
     */
    public String exibirTramitacao(String codigo) {
        if (!existeLei(codigo)) {
            throw new NullPointerException("Erro ao exibir tramitacao: projeto inexistente");
        }
        List<String> tramitacao = this.proposicoesDeLeis.get(codigo).getTramitacao();

        String separador = ", ";
        String tramitacaoFormatada = String.join(separador, tramitacao);
        return tramitacaoFormatada;
    }

    /**
     * Método que verifica se um projeto de lei existe no sistema.
     *
     * @param codigo codigo da lei a ser procurado.
     * @return boolean informando se o projeto existe ou não.
     */
    public boolean existeLei(String codigo) {
        return this.proposicoesDeLeis.containsKey(codigo);
    }

    /**
     * Método que recupera um projeto de lei no sistema, buscando no mapa que os organiza.
     *
     * @param codigo codigo da lei a ser procurado.
     * @return Object a lei pesquisada.
     */
    public PropostaAbstract buscaProposicao(String codigo) {
        return this.proposicoesDeLeis.get(codigo);
    }

    /**
     * Esse método é responsável por armazenar as informações das coleções presentes no ProposicaoController.
     * Criando um arquivo da extensão .dat, com as informações das coleções, no diretório files/ .
     */
    public void salvarSistema() {
        this.persistencia.salvar(this.proposicoesDeLeis, "mapaProposicoesDeLeis");
        this.persistencia.salvar(this.contadores, "mapaContadores");
    }

    /**
     * Esse método é responsável por ler as informações das coleções do ProposicaoController, armazenadas
     * no diretório files/ .
     */
    public void carregarSistema() {
        Object aux = this.persistencia.carregar("mapaContadores");
        this.contadores = new HashMap<>();
        if (aux != null) {
            this.contadores = (Map<String, Contador>) aux;
        }

        Object aux2 = this.persistencia.carregar("mapaProposicoesDeLeis");
        this.proposicoesDeLeis = new HashMap<>();
        if (aux2 != null) {
            this.proposicoesDeLeis = (Map<String, PropostaAbstract>) aux2;
        }
    }

    /**
     * Esse método é responsável por limpar as informações das coleções presentes no ProposicaoController.
     * Criando um arquivo da extensão .dat vazio no diretório files/ .
     */
    public void limparSistema() {
        this.persistencia.limpar("mapaProposicoesDeLeis");
        this.persistencia.limpar("mapaContadores");
    }

    /**
     * Método responsável por separar artigos com vírgula e espaço, como no
     * formato a seguir: "artigo1, artigo2, ..., artigoN".
     *
     * @param artigos String base para geração da nova string dos artigos concatenados.
     * @return String dos artigos separados por ", "
     */
    private String contatenaArtigos(String artigos) {
        if (artigos.contains(",")) {
            String artigosConcatenados = "";
            for (String caractere : artigos.split(",")) {
                artigosConcatenados += caractere + ", ";

            }
            return artigosConcatenados.substring(0, artigosConcatenados.length() - 2);
        }
        return artigos;
    }
}