package entidades;

import java.io.Serializable;

/**
 * É uma interface que define os métodos que um deputado precisa ter no sistema.
 */
public interface Funcao extends Serializable {

    /**
     * Adiciona as informações especificas da função do deputado na represetação de pessoa, no caso,
     * é adicionado a data de início e a quantidade de leis aceitas do deputado.
     *
     * @param representacao dados da pessoa, no caso, nome, dni, partido e/ou interresses.
     * @return a representação em String de um deputado.
     */
    public String exibir(String representacao);

    /**
     * Incrementa em um o atributo numeroDeLeis sempre que é chamado.
     */
    public void incrementaNumeroDeLeis();

    /**
     * Esse método retorna um boolean que informa se o deputado votou a favor ou não da aprovação de
     * determinada proposição.
     *
     * @param statusGovernista status.
     * @param ehDaBase boolean que indica se o deputado é ou não da base.
     * @param temInteressesEmComum boolean que indica se o deputado tem interesse em comum com a proposta.
     * @return boolean que indica o voto do deputado.
     */
    public boolean votoPolitico(String statusGovernista, boolean ehDaBase, boolean temInteressesEmComum);
}
