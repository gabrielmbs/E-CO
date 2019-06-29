package entidades;

import util.Validador;
import java.io.Serializable;
import java.util.Objects;

/**
 * Representação de uma pessoa, caracterizada pelo seu nome, dni, estado,
 * intereses, partido (todos do tipo String) e funcao que sinaliza para o sistema
 * que a pessoa é "pessoa normal" ou deputado.
 *
 * É o dni que identifica uma pessoa.
 */
public class Pessoa implements Serializable {

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
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    /**
     * Representa a estrategia de busca da Pessoa, por padrão é Constitucional,
     * o atributo consiste em uma interface implementada pelas estratégias.
     */
    private EstrategiaDesempate estrategiaBuscaProposta;

    /**
     * Os métodos construtores a seguir são sobrecarregados de modo
     * a podermos instanciar uma pessoa que possua filiação a algum partido
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
     * @param nome       nome da pessoa a ser criada.
     * @param dni        dni da pessoa a ser criada.
     * @param estado     estado de origem da pessoa a ser criada.
     * @param interesses lista de interesses da pessoa.
     */
    public Pessoa(String nome, String dni, String estado, String interesses) {
        this.validador = new Validador();

        this.validador.validaDNI(dni, "Erro ao cadastrar pessoa: ");
        this.validador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        this.validador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        this.nome = nome;
        this.dni = dni;
        this.estado = estado;
        this.interesses = interesses;
        this.estrategiaBuscaProposta = new EstrategiaConstitucional();
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
     * @param nome       nome da pessoa a ser criada.
     * @param dni        dni da pessoa a ser criada.
     * @param estado     estado de origem da pessoa a ser criada.
     * @param interesses lista de interesses da pessoa.
     * @param partido    partido ao qual a pessoa filiada.
     */
    public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
        this(nome, dni, estado, interesses);

        this.validador.validaString(partido, "Erro ao cadastrar pessoa: partido nao pode ser vazio ou nulo");
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
     *
     * @param dataInicio data de ínicio do mandato do deputado.
     */
    public void viraDeputado(String dataInicio) {
        this.validador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        this.validador.validaData(dataInicio, "Erro ao cadastrar deputado: ");

        this.funcao = new Deputado(dataInicio);
    }

    /**
     * Retorna a String que representa uma pessoa e a respectiva funcao
     * do mesmo. As informações sobre partido ou interesses são optativos
     * para pessoas.
     *
     * A represetacao segue o formato "Nome - DNI (Estado) [ - PARTIDO ]
     * [ - Interesses ]"
     *
     * @return a representação em String de uma pessoa.
     */
    @Override
    public String toString() {
        String representacao = this.nome + " - " + this.dni + " (" + this.estado + ")";

        if ((!"".equals(this.interesses.trim())) && (this.partido != null)) {
            representacao += " - " + this.partido + " - " + "Interesses: " + this.interesses;
        } else if ((this.partido != null) && ("".equals(this.interesses.trim()))) {
            representacao += " - " + this.partido;
        } else if ((this.partido == null) && (!"".equals(this.interesses.trim()))) {
            representacao += " - " + "Interesses: " + this.interesses;
        }

        if (this.funcao != null) {
            representacao = this.funcao.exibir(representacao);
        }

        return representacao;
    }

    /**
     * Método que sobreescreve o método equals de Objects para se enquadrar nos moldes
     * da classe Pessoa. Uma pessoa é igual a outra pessoa se ambas possuírem dni iguais.
     *
     * @param o parâmetro a ser comparado, para verificar se algum outro Object Pessoa é igual ou não a ele.
     * @return true, se os objetos Pessoa forem iguais, false, se os objetos Pessoa forem diferentes ou se o objeto
     * passado como parâmetro for null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(dni, pessoa.dni);
    }

    /**
     * Método que sobreescreve o método hashcode de Objects para se enquadrar nos moldes
     * da classe Pessoa. Uma pessoa é igual a outra se ambas possuírem o mesmo dni,
     * portanto devem possuir o mesmo hashcode.
     *
     * @return um inteiro gerado automaticamente pelo método hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    /**
     * Acessador desenvolvido com intuito de garantir acesso ao
     * partido da pessoa.
     *
     * @return retorna o valor do partido da pessoa.
     */
    public String getPartido() {
        return partido;
    }


    /**
     * Pega a funcao da pessoa, ou seja, se ela eh ou nao deputado.
     *
     * @return retorna a funcao da pessoa.
     */
    public Funcao getFuncao() {
        return funcao;
    }

    /**
     * Pega os interesses da pessoa.
     *
     * @return retorna uma String, representando os interreses da pessoa separada por ','.
     */
    public String getInteresses() {
        return interesses;
    }

    /**
     * Método responsável por configurar a estrategia de busca, para o proposta relacionada com o seus interesses.
     * A estratégia pode ser: CONSTITUCIONAL, CONCLUSAO, APROVACAO. Se por acaso não forem passadas por paramentro
     * é lançado um IllegalArgumentException.
     * A estrategia de busca padrão é: CONSTITUCIONAL.
     *
     * @param estrategia String, que representa a estratégia de busca desejada.
     */
    public void configurarEstrategiaPropostaRelacionada(String estrategia) {
        if ("CONSTITUCIONAL".equals(estrategia)) {
            this.estrategiaBuscaProposta = new EstrategiaConstitucional();
        } else if ("CONCLUSAO".equals(estrategia)) {
            this.estrategiaBuscaProposta = new EstrategiaConclusao();
        } else if ("APROVACAO".equals(estrategia)) {
            this.estrategiaBuscaProposta = new EstrategiaAprovacao();
        } else {
            throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia invalida");
        }
    }

    /**
     * Retorna a estratégia de busca utilizada pela pessoa.
     *
     * @return a estratégia utilizada pela pessoa.
     */
    public EstrategiaDesempate getEstrategiaBuscaProposta() {
        return estrategiaBuscaProposta;
    }
}
