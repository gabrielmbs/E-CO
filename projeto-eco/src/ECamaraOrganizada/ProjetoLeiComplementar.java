package ECamaraOrganizada;

import util.Validador;

/**
 * Representação de um Projeto de Lei Complementar, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses, url e artigos, todos do tipo String e  ano do tipo int.
 */
public class ProjetoLeiComplementar extends ProposicaoAbstract {
    /**
     * Atributo que denota sobre quais artigos da Constituição o projeto vai atuar.
     */
    private  String artigos;
    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;
    /**
     * Método responsável por criar um Projeto de Emenda Constitucional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String, ano do tipo int
     * são passados como parâmetro.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * Verifica-se ainda, se o ano passado é anterior à 1988 ou posterior a 2019. Se for,
     * IllegalArgumentException será lançado.
     *
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * Usa-se o construtor da classe ProposicaoAbstract para inicializar os atributos dni, ano, codigoLei, ementa,
     * interesses e url.
     *
     * @param codigoLei código da lei.
     * @param dni dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url endereço url do projeto.
     * @param ano ano de criacção do projeto
     * @param artigos artigos da Constituição sobre os quais o projetor irá atuar.
     *
     *
     */
    public ProjetoLeiComplementar(String codigoLei, String dni, int ano, String ementa, String interesses, String url, String artigos) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.validador = new Validador();
        this.validador.validaString(codigoLei,"Erro ao cadastrar projeto: codigo de lei nao pode ser vazio ou nulo");
        this.validador.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar projeto: ");
        this.validador.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
        this.artigos = artigos;
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato "Projeto de Lei Complementar - Código - DNI - Ementa - Artigos - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    public String toString(){
        return "Projeto de Lei Complementar" + super.toString() + this.artigos + " - " + this.situacao;
    }
}
