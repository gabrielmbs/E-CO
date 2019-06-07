package ECamaraOrganizada;

public class ProjetoLeiComplementar extends ProposicaoAbstract {
    private String artigos;
    public ProjetoLeiComplementar(String codigoLei, String dni, int ano, String ementa, String interesses, String url, String artigos) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.artigos = artigos;
    }

    public String toString(){
        return "Projeto de Lei Complementar" + " - " + this.codigoLei + " - " + this.dniAutor +
                " - " + this.ementa + " - " + this.artigos + " - " + this.situacao;
    }
}
