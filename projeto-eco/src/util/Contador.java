package util;

import java.io.Serializable;

/**
 * Representação de um contador, utlizado como
 * mecanismo auxiliar para gerar o código de uma
 * lei a ser cadastrada.
 */
public class Contador implements Serializable {
    /**
     * Atributo que indica em que número a contagem está.
     */
    private Integer contagem;

    /**
     * Constrói um contador, iniciando sua contagem em 1.
     */
    public Contador() {
        this.contagem = 1;

    }

    /**
     * Método responsável por incrementar em 1 a contagem do
     * contador
     */
    public void incrementaContagem() {
        this.contagem++;
    }

    /**
     * Acessador desenvolvido com intuito de garantir acesso à
     * contagem do aluno.
     *
     * @return retorna o valor da contagem do contador.
     */
    public Integer getContagem() {
        return contagem;
    }
}
