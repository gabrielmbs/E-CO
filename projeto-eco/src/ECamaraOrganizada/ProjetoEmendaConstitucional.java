package ECamaraOrganizada;

public class ProjetoEmendaConstitucional extends ProposicaoAbstract {
    private final String artigos;

    public ProjetoEmendaConstitucional(String codigoLei, String dni, int ano, String ementa, String interesses, String url, String artigos) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.artigos = artigos;
    }

    public String toString(){
        return "Projeto de Emenda Constitucional" + super.toString() + this.artigos + " - " + this.situacao;
    }
}
