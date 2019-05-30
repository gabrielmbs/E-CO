package ECamaraOrganizada;

public class Deputado extends Pessoa {
    private int numeroDeLeis;
    private String dataInicio;

    public Deputado(String nome, String dni, String estado, String interesses, String partido, String dataInicio) {
        super(nome, dni, estado, interesses, partido);
        this.dataInicio = dataInicio;
        this.numeroDeLeis = 0;
    }

    @Override
    public String toString() {
        return "Deputado{" +
                "numeroDeLeis=" + numeroDeLeis +
                ", dataInicio='" + dataInicio + '\'' +
                '}';
    }
}
