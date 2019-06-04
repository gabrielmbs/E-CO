package ECamaraOrganizada;

import util.Validador;

import java.util.Objects;
/**
 * Representação de uma pessoa, caracterizada pelo seu nome, dni, estado,
 * intereses, partido (todos do tipo String) e funcao que sinaliza para o sistema
 * que a pessoa é "pessoa normal" ou deputado.
 *
 * É o dni que identifica uma pessoa.
 *
 */
public class Pessoa {
    /**
     * Nome da pessoa.
     */
    private String nome;
    /**
     * DNI da pessoa.
     */
    private String dni;
    /**
     * Estado de origem da pessoa.
     */
    private String estado;
    /**
     * Temas que a pessoa demonstra interesse.
     */
    private String interesses;
    /**
     * Partido (caso possua) ao qual a pessoa é filiada.
     */
    private String partido;
    /**
     * Atributo que sinaliza para o sistema
     * que a pessoa é "pessoa normal" ou deputado,
     * que consiste em uma interface implementada
     * pela classe Deputado.
     */
    private Funcao funcao;

    /**
     * Os métodos construtores a seguir são sobrecarregados de modo
     * a podermos instanciar uma pessoa que pessoa filiação a algum partido
     * ou não.
     */

    /**
     * Constrói uma pessoa pelo nome, dni, estado e interesses desta,
     * valores esses do tipo String, passados como parâmetro para o método
     * construtor em questão. Além disso, verifica se os parâmetros são
     * nulos ou estão na forma de String vazia e se estiverem, exceções
     * do tipo NullPointerException e IllegalArgumentExeception serão
     * lançadas, respectivamente.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * @param nome nome da pessoa a ser criada.
     * @param dni dni da pessoa a ser criada.
     * @param estado estado de origem da pessoa a ser criada.
     * @param interesses lista de interesses da pessoa.
     */
    public Pessoa(String nome, String dni, String estado, String interesses) {
        Validador.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Validador.validaDNI(dni, "Erro ao cadastrar pessoa: dni invalido");
        Validador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Validador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");

        this.nome = nome;
        this.dni = dni;
        this.estado = estado;
        this.interesses = interesses;
    }

    /**
     * Constrói uma pessoa pelo nome, dni, estado, interesses e partido desta,
     * valores esses do tipo String, passados como parâmetro para o método
     * construtor em questão. Além disso, verifica se os parâmetros são
     * nulos ou estão na forma de String vazia e se estiverem, exceções
     * do tipo NullPointerException e IllegalArgumentExeception serão
     * lançadas, respectivamente.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * @param nome nome da pessoa a ser criada.
     * @param dni dni da pessoa a ser criada.
     * @param estado estado de origem da pessoa a ser criada.
     * @param interesses lista de interesses da pessoa.
     * @param partido partido ao qual a pessoa filiada.
     */
    public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
        this(nome, dni, estado, interesses);

        Validador.validaString(partido, "Erro ao cadastrar pessoa: partido nao pode ser vazio ou nulo");
        this.partido = partido;
    }

    /**
     * Método responsável por tornar uma Pessoa também Deputado, por meio
     * da atribuição de uma instanciação de um Deputado ao atributo funcao,
     * que é uma interface implementada por Deputado.
     *
     * Recebe a data de início (do tipo String) do mandato do Deputado. Checa-se
     * se a data passada como parâmetro é nula ou vazia, se é uma data válida
     * ou se é uma data futura à data de hoje.
     * @param dataInicio
     */
    public void viraDeputado(String dataInicio) {
        Validador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        Validador.validaDataInvalida(dataInicio, "Erro ao cadastrar deputado: data invalida");
        Validador.validaDataFutura(dataInicio, "Erro ao cadastrar deputado: data futura");

        this.funcao = new Deputado(dataInicio);
    }

    @Override
    public String toString() {
        if (this.funcao == null) {
            return exibePessoa();
        } else {
            return exibeDeputado();
        }
    }

    private String exibeDeputado() {
        if ((!"".equals(this.interesses.trim()))) {
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses + " - " + this.funcao.exibirDeputado();
        }else{
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido
                    + " - " + this.funcao.exibirDeputado();
        }
    }

    private String exibePessoa() {
        if ((!"".equals(this.interesses.trim())) && (this.partido != null)) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses;
        } else if ((this.partido != null) && ("".equals(this.interesses.trim()))) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido;
        } else if ((this.partido == null) && (!"".equals(this.interesses.trim()))) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + "Interesses: " + this.interesses;
        }
        return this.nome + " - " + this.dni + " (" + this.estado + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(dni, pessoa.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getPartido() {
        return partido;
    }
}
