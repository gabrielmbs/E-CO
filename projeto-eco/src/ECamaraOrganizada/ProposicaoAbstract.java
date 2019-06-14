package ECamaraOrganizada;

import java.util.Objects;

/**
 * Representação abstrata de um Projeto , caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String e ano do tipo int.
 *
 */
public abstract class ProposicaoAbstract {
    /**
     * DNI do autor do projeto.
     */
    protected String dniAutor;
    /**
     * Ano da criação do projeto.
     */
    protected int ano;
    /**
     * Código do projeto.
     */
    protected String codigoLei;
    /**
     * Ementa do projeto.
     */
    protected String ementa;
    /**
     * Interesses do projeto.
     */
    protected String interesses;
    /**
     * Endereço URL do projeto.
     */
    protected String urlDocumento;
    /**
     * Situação em que se encontra o projeto.
     */
    protected String situacao;

    protected String localDeVotacao;

    /**
     * Método responsável por criar um Projeto de Emenda Constitucional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String, ano do tipo int
     * são passados como parâmetro.
     *
     * Inicializa o atributo situação como "EM VOTACAO (CCJC)".
     *
     * @param codigoLei código da lei.
     * @param dniAutor dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param urlDocumento endereço url do projeto.
     * @param ano ano de criacção do projeto
     *
     *
     */
    public ProposicaoAbstract(String dniAutor, Integer ano, String codigoLei, String ementa, String interesses, String urlDocumento) {
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.codigoLei = codigoLei;
        this.ementa = ementa;
        this.interesses = interesses;
        this.urlDocumento = urlDocumento;
        this.situacao = "EM VOTACAO (CCJC)";
        this.localDeVotacao = "CCJC";
    }

    /**
     * Método que sobreescreve o método equals de Objects para se enquadrar nos moldes
     * da classe ProposicaoAbstract. Um projeto é igual a outro projeto se ambos possuírem código de lei iguais.
     *
     * @param o parâmetro a ser comparado.
     *
     * @return true, se os objetos forem iguais, false, se os objetos forem diferentes
     *                                                    ou se o objeto passado como parâmetro for null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposicaoAbstract that = (ProposicaoAbstract) o;
        return codigoLei.equals(that.codigoLei);
    }

    /**
     * Método que sobreescreve o método hashcode de Objects para se enquadrar nos moldes
     * da classe ProposicaoAbstract. Um projeto é igual a outro se ambos possuírem o mesmo código de lei,
     * portanto devem possuir o mesmo hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigoLei);
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato: - this.código da lei - dni do autor - ementa -
     *
     * É usado para compor o toString individual de cada tipo específico
     * de projeto de lei.
     *
     * @return retorna a representação em String do projeto
     */
    @Override
    public String toString(){
        return " - " + this.codigoLei + " - " + this.dniAutor +
                " - " + this.ementa + " - ";
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDniAutor() {
        return dniAutor;
    }

    public String getInteresses() {
        return interesses;
    }

    public String getLocalDeVotacao() {
        return localDeVotacao;
    }

    public void setLocalDeVotacao(String localDeVotacao) {
        this.localDeVotacao = localDeVotacao;
    }
}
