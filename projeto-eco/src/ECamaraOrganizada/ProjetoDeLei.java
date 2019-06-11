package ECamaraOrganizada;

public class ProjetoDeLei extends ProposicaoAbstract {
    private boolean conclusivo;

    public ProjetoDeLei(String codigoLei, String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.conclusivo = conclusivo;
    }

    public String toString() {
        if (this.conclusivo) {
            return "Projeto de Lei" + " - " + this.codigoLei + " - " + this.dniAutor +
                    " - " + this.ementa + " - Conclusiva"  + " - " + this.situacao;
        }
        else return "Projeto de Lei" + super.toString() + this.situacao;
    }

}
