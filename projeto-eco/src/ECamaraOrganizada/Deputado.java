package ECamaraOrganizada;

public class Deputado implements Funcao{
    private int numeroDeLeis;
    private String dataInicio;

    public Deputado(String dataInicio) {
        this.dataInicio = dataInicio;
        this.numeroDeLeis = 0;
    }

    @Override
    public String exibirDeputado() {
        String dia = this.dataInicio.substring(0,2);
        String mes = this.dataInicio.substring(2,4);
        String ano = this.dataInicio.substring(4);
        return dia + "/" + mes + "/" + ano + " - " + this.numeroDeLeis + " Leis";
    }
}
