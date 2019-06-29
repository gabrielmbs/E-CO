package entidades;

import util.Validador;

import java.io.Serializable;

/**
 * Representação de um deputado que tem um número de leis (que, por padrão, se inicia em 0) e uma data de ínicio
 * de mandato.
 */
public class Deputado implements Funcao, Serializable {

    /**
     * Número de leis do deputado.
     */
    private int numeroDeLeis;

    /**
     * Data de ínicio do mandato.
     */
    private String dataInicio;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    /**
     * Constrói um deputado a partir da data de ínicio do mandato. Além disso, verifica se os parâmetros são
     * nulos ou estão na forma de String vazia e se estiverem, exceções do tipo NullPointerException e
     * IllegalArgumentExeception serã lançadas, respectivamente.
     *
     * @param dataInicio data de ínicio do mandato do deputado.
     */
    public Deputado(String dataInicio) {
        this.validador = new Validador();
        this.validador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        this.dataInicio = dataInicio;
        this.numeroDeLeis = 0;
    }

    /**
     * Adiciona as informacoes especificas da funcao do deputado na represetacao de pessoa, no caso,
     * é adicionado a data de inicio e a quantidade de leis aceitas do deputado.
     *
     * @param representacao dados da pessoa, no caso, nome, dni, partido e/ou interresses.
     * @return a representação em String de um deputado.
     */
    @Override
    public String exibir(String representacao) {
        String dia = this.dataInicio.substring(0, 2);
        String mes = this.dataInicio.substring(2, 4);
        String ano = this.dataInicio.substring(4);
        return "POL: " + representacao + " - " + dia + "/" + mes + "/" + ano + " - " + this.numeroDeLeis + " Leis";
    }

    /**
     * Incrementa em um o atributo numeroDeLeis sempre que é chamado.
     */
    public void incrementaNumeroDeLeis() {
        this.numeroDeLeis++;
    }

    /**
     * Esse método retorna um boolean que informa se foi aprovado ou não o voto.
     *
     * @param statusGovernista status.
     * @return boolean informando se o voto foi ou não aprovado.
     */
    public boolean votoPolitico(String statusGovernista, boolean ehDaBase, boolean temInteressesEmComum) {
        boolean result = false;
        if ("GOVERNISTA".equals(statusGovernista) && ehDaBase) {
            result = true;
        } else if ("LIVRE".equals(statusGovernista) && temInteressesEmComum) {
            result = true;
        } else if ("OPOSICAO".equals(statusGovernista) && !ehDaBase) {
            result = true;
        }
        return result;
    }
}
