package entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe representa uma commisão, ela guarda atributos especificos de cada comissão e gerecia as mesmas.
 */
public class Comissao implements Serializable {
    /**
     * Representa os deputados que fazem parte da comissão
     */
    private String[] DNIs;
    /**
     * Representa o tema da comissão
     */
    private String tema;

    /**
     * Cria uma comissão a partir de um tema e um array de DNIs.
     *
     * @param tema uma String, representa o tema da comissão.
     * @param DNIs um array de Strings, representa os dnis dos deputados.
     */
    public Comissao(String tema, String[] DNIs) {
        this.tema = tema;
        this.DNIs = DNIs;
    }

    /**
     * Pega a lista de dnis da comissão, no caso, os deputados que fazem parte da comissão.
     * @return um array de Strings, representa os dnis dos deputados.
     */
    public String[] getDNIs() {
        return DNIs;
    }

    /**
     * Método que sobreescreve o método equals de Objects para se enquadrar nos moldes
     * da classe Comissão. Uma comissão é igual a outra comissão se ambas possuírem temas iguais.
     *
     * @param o parâmetro a ser comparado, para verificar se algum outro Object Comissao é igual ou não a ele.
     * @return true, se os objetos Comissao forem iguais, false, se os objetos Comissao forem diferentes ou se o objeto
     * passado como parâmetro for null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comissao comissao = (Comissao) o;
        return Objects.equals(tema, comissao.tema);
    }

    /**
     * Método que sobreescreve o método hashcode de Objects para se enquadrar nos moldes
     * da classe Comissao. Uma comissao é igual a outra se ambas possuírem o mesmo tema,
     * portanto devem possuir o mesmo hashcode.
     *
     * @return um inteiro gerado automaticamente pelo método hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tema);
    }
}
