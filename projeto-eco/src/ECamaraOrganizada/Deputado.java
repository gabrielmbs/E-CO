package ECamaraOrganizada;

public class Deputado extends Pessoa {
    private int numeroDeLeis;
    private String dataInicio;

    public Deputado(String nome, String dni, String estado, String interesses, String partido, String dataInicio) {
        super(nome, dni, estado, interesses, partido);
        this.dataInicio = dataInicio;
        this.numeroDeLeis = 0;
    }

    //Formato: POL: Nome - DNI (Estado) [ - PARTIDO ] [ - Interesses ] - DATA - N Leis
    //Exemplo: POL: Thanos - 234567890-1 (SP) - MCU - Interesses: economia, minerais - 01/01/1019 - 2 Leis
    @Override
    public String toString() {
        String dia = this.dataInicio.substring(0,2);
        String mes = this.dataInicio.substring(2,4);
        String ano = this.dataInicio.substring(4);
        String data = dia + "/" + mes + "/" + ano;
        if(!"".equals(this.interesses)) {
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses + " - " + data +
                    " - " + this.numeroDeLeis + " Leis";
        }
        return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                " - " + data + " - " + this.numeroDeLeis + " Leis";
    }

}
