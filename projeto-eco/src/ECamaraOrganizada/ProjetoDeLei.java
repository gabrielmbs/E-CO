package ECamaraOrganizada;

public class ProjetoDeLei extends ProposicaoAbstract {
    private String conclusivo;

    public ProjetoDeLei(String codigoLei, String dni, int ano, String ementa, String interesses, String url, String conclusivo) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.conclusivo = conclusivo;
    }

    public String toString(){
        return "Projeto de Lei" + " - " + this.codigoLei + " - " + this.dniAutor +
                " - " + this.ementa + " - " + this.conclusivo + " - " + this.situacao;
    }
}
