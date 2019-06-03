package ECamaraOrganizada;

import util.Autenticador;

public class Deputado implements Funcao{
    private int numeroDeLeis;
    private String dataInicio;

    public Deputado(String dataInicio) {
        Autenticador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
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
