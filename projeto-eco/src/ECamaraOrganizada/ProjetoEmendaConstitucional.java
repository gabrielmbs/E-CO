package ECamaraOrganizada;

import util.Validador;

/**
 * Representação de um Projeto de Emenda Constitucional, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses, url e artigos, todos do tipo String,  ano do tipo int.
 *
 */
public class ProjetoEmendaConstitucional extends ProposicaoAbstract {
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
    public ProjetoEmendaConstitucional(String codigoLei, String dni, int ano, String ementa, String interesses, String url, String artigos) {
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
     * segue o formato "Projeto de Emenda Constitucional - Código - DNI - Ementa - Artigos - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    @Override
    public String toString(){
        return "Projeto de Emenda Constitucional" + super.toString() + this.artigos + " - " + this.situacao;
    }
}
