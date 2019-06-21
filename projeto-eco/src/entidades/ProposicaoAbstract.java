package entidades;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representação abstrata de um Projeto , caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String e ano do tipo int.
 */
public abstract class ProposicaoAbstract {

    /**
     * DNI do autor do projeto.
     */
    private String dniAutor;

    /**
     * Ano da criação do projeto.
     */
    private int ano;

    /**
     * Código do projeto.
     */
    private String codigoLei;

    /**
     * Ementa do projeto.
     */
    private String ementa;

    /**
     * Interesses do projeto.
     */
    private String interesses;

    /**
     * Endereço URL do projeto.
     */
    private String urlDocumento;

    /**
     * Indica se um projeto está tramitando pela câmara ou se já foi encerrado.
     */
    private boolean proposicaoAtiva;

    /**
     * Indica se uma proposta já foi votada no plenário.
     */
    private boolean passouNoPlenario;

    /**
     * Atributo que indica se uma proposta já passou pela CCJC.
     */
    private boolean passouNaCCJC;

    /**
     * Atributo que denota o estado conclusivo do Projeto de Lei.
     */
    protected boolean conclusivo;

    /**
     * Situação em que se encontra o projeto.
     */
    protected String situacao;

    /**
     * Local onde o projeto se encontra para votação.
     */
    protected String localDeVotacao;

    /**
     * Indica o tipo de projeto (PL, PLP ou PEC).
     */
    protected String tipoDeProposicao;

    protected int quantidadeDeComissoes;


    /**
     * Método responsável por criar um Projeto de Emenda Constitucional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String, ano do tipo int
     * são passados como parâmetro.
     * <p>
     * Inicializa o atributo situação como "EM VOTACAO (CCJC)".
     *
     * @param codigoLei    código da lei.
     * @param dniAutor     dni do autor do projeto.
     * @param ementa       ementa do projeto.
     * @param interesses   interesses do projeto.
     * @param urlDocumento endereço url do projeto.
     * @param ano          ano de criacção do projeto
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
        this.proposicaoAtiva = true;
        this.passouNoPlenario = false;
        this.passouNaCCJC = false;
        this.quantidadeDeComissoes = 0;
    }

    /**
     * Método que sobreescreve o método equals de Objects para se enquadrar nos moldes
     * da classe ProposicaoAbstract. Um projeto é igual a outro projeto se ambos possuírem código de lei iguais.
     *
     * @param o parâmetro a ser comparado.
     * @return true, se os objetos forem iguais, false, se os objetos forem diferentes
     * ou se o objeto passado como parâmetro for null.
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
     * <p>
     * É usado para compor o toString individual de cada tipo específico
     * de projeto de lei.
     *
     * @return retorna a representação em String do projeto
     */
    @Override
    public String toString() {
        return " - " + this.codigoLei + " - " + this.dniAutor +
                " - " + this.ementa + " - ";
    }

    /**
     * Altera a situação de um projeto com base em uma nova situação passada como parâmetro.
     *
     * @param situacao nova situação.
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * Retorna o dni do autor da proposta.
     *
     * @return dni do autor da proposta.
     */
    public String getDniAutor() {
        return dniAutor;
    }

    /**
     * Retorna os interesses da proposta.
     *
     * @return os interesses da proposta.
     */
    public String getInteresses() {
        return interesses;
    }

    /**
     * Retorna o local no qual a proposta está para ser votada.
     *
     * @return local no qual a proposta está para ser votada.
     */
    public String getLocalDeVotacao() {
        return localDeVotacao;
    }

    /**
     * Altera o local no qual o projeto se encontra para ser votado a partir de um novo
     * local passado como parâmetro.
     *
     * @param localDeVotacao novo local de votação.
     */
    public void setLocalDeVotacao(String localDeVotacao) {
        this.localDeVotacao = localDeVotacao;
    }

    /**
     * Retorna um boolean que indica se a proposta é conclusiva ou não.
     *
     * @return boolean que indica se a proposta é conclusiva.
     */
    public boolean isConclusivo() {
        return conclusivo;
    }

    /**
     * Retorna um boolean que indica se a proposta está tramitanto na câmara ou se já foi encerrada.
     *
     * @return um boolean que indica se a porposta está (ou não) tramitando.
     */
    public boolean getProposicaoAtiva() {
        return proposicaoAtiva;
    }

    /**
     * Altera o status que indica se uma proposta está tamitando na câmara (ou não) a partir
     * de um novo status passado como parâmetro.
     *
     * @param proposicaoAtiva novo status.
     */
    public void setProposicaoAtiva(boolean proposicaoAtiva) {
        this.proposicaoAtiva = proposicaoAtiva;
    }

    /**
     * Retorna uam String que indica o tipo do projeto (PL, PLP ou PEC).
     *
     * @return uma String que indica o tipo do projeto.
     */
    public String getTipoDeProposicao() {
        return tipoDeProposicao;
    }

    /**
     * Altera o atributo passouNoPlenario a partir de um novo status passado como
     * parâmetro.
     *
     * @param passouNoPlenario novo status.
     */
    public void setPassouNoPlenario(boolean passouNoPlenario) {
        this.passouNoPlenario = passouNoPlenario;
    }

    /**
     * Retorna um boolean que indica se um determinado projeto já passou pelo plenário.
     *
     * @return um boolean que indica se o projeto já passou no plenário.
     */
    public boolean getPassouNoPlenario() {
        return passouNoPlenario;
    }

    /**
     * Retorna um boolean que indica se um determinado projeto já passou pela CCJC.
     *
     * @return um boolean que indica se o projeto já passou no plenário.
     */
    public boolean getPassouNaCCJC() {
        return passouNaCCJC;
    }

    /**
     * Altera o atributo passouNaCCJC a partir de um novo status passado como parâmetro.
     *
     * @param passouNaCCJC novo status.
     */
    public void setPassouNaCCJC(boolean passouNaCCJC) {
        this.passouNaCCJC = passouNaCCJC;
    }

    public abstract int caulculaChao(int participantes);

    public String getCodigoLei() {
        return codigoLei;
    }

    public void setQuantidadeDeComissoes() {
        this.quantidadeDeComissoes++;
    }

    public String getSituacao() {
        return situacao;
    }

    public Integer getQuantidadeDeComissoes() {
        return quantidadeDeComissoes;
    }
}
