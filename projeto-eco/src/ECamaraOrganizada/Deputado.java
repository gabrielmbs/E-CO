package ECamaraOrganizada;

import util.Validador;

/**
 * Representação de um deputado que tem um número de leis (que, por padrão, se inicia em 0) e uma data de ínicio
 * de mandato.
 */
public class Deputado implements Funcao{

    /**
     * Número de leis do deputado.
     */
    private int numeroDeLeis;

    /**
     * Data de ínicio do mandato.
     */
    private String dataInicio;

    /**
     * Constrói um deputado a partir da data de ínicio do mandato. Além disso, verifica se os parâmetros são
     * nulos ou estão na forma de String vazia e se estiverem, exceções do tipo NullPointerException e
     * IllegalArgumentExeception serã lançadas, respectivamente.
     *
     * @param dataInicio data de ínicio do mandato do deputado.
     */
    public Deputado(String dataInicio) {
        Validador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        this.dataInicio = dataInicio;
        this.numeroDeLeis = 0;
    }

    @Override
    public String exibir() {
        String dia = this.dataInicio.substring(0,2);
        String mes = this.dataInicio.substring(2,4);
        String ano = this.dataInicio.substring(4);
        return dia + "/" + mes + "/" + ano + " - " + this.numeroDeLeis + " Leis";
    }
}
