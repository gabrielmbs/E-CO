package ECamaraOrganizada;

import java.util.Objects;

public abstract class ProposicaoAbstract implements ProposicaoInterface {
    protected String dniAutor;
    protected Integer ano;
    protected String codigoLei;
    protected String ementa;
    protected String interesses;
    protected String urlDocumento;
    protected String situacao;

    public ProposicaoAbstract(String dniAutor, Integer ano, String codigoLei, String ementa, String interesses, String urlDocumento) {
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.codigoLei = codigoLei;
        this.ementa = ementa;
        this.interesses = interesses;
        this.urlDocumento = urlDocumento;
        this.situacao = "EM VOTACAO (CCJC)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposicaoAbstract that = (ProposicaoAbstract) o;
        return codigoLei.equals(that.codigoLei);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoLei);
    }

    public abstract String toString();
}
