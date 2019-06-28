package controllers;

import entidades.*;
import util.Contador;
import util.Validador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Persistencia persistencia;

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
     * parâmetro o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e o próximo
     * local no qual a porposta será votada.
     *
     * @param codigo       código da proposta.
     * @param proximoLocal próximo locla no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarComissao(String codigo, String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        PropostaAbstract proposicao = this.proposicoesDeLeis.get(codigo);
        return proposicao.votarComissao(proximoLocal, chao, votosFavoraveis, autor);
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta no plenário, ele recebe como parâmetro
     * o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e os presentes
     * no plenário (DNIs separados por vírgula), todos do tipo String. O método retorna um boolena que indica
     * o resultado da votação.
     * <p>
     * Ao longo das condições referentes à situação atual de um determinado projeto de lei,
     * tal situação é registrada, de modo a compor a tramitação do projeto, que pode ser
     * posteriormente resgatada.
     *
     * @param codigo código da proposta.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarPlenario(String codigo, String[] deputados, Pessoa deputado, int votosFavoraveis,
                                 int totalDeputados) {
        PropostaAbstract proposicao = this.proposicoesDeLeis.get(codigo);
        return proposicao.votarPlenario(deputados, deputado, votosFavoraveis, totalDeputados);
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

    /**
     * Esse método é responsável por limpar as informações das coleções presentes no ProposicaoController.
     * Criando um arquivo da extensão .dat vazio no diretório files/ .
     */
    public void limparSistema() {
        this.persistencia.limpar("mapaProposicoesDeLeis");
        this.persistencia.limpar("mapaContadores");
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
}