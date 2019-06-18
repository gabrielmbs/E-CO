package controllers;

import entidades.Funcao;
import entidades.Pessoa;
import util.Validador;

import java.util.HashMap;
import java.util.Map;

public class DeputadoController {

    private Validador validador;

    /**
     * Representa mapa de pessoa, com a chave representando o dni e valor a pessoa.
     */
    private Map<String, Pessoa> pessoas;

    public DeputadoController() {
        this.validador = new Validador();
        this.pessoas = new HashMap<>();
    }

    /**
     * Os métodos cadastrarPessoa a seguir são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * @param nome       nome da pessoa a ser cadastrada.
     * @param dni        dni da pessa a ser cadastrada.
     * @param estado     estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        validaCadastrarPessoa(nome, dni, estado);
        if (existePessoa(dni)) {
            throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses));
        return true;
    }

    /**
     * Método responsável por validar um cadastro de uma pessoa.
     *
     * @param nome   nome da pessoa.
     * @param dni    dni da pessoa.
     * @param estado estado onde reside a pessoa.
     */
    private void validaCadastrarPessoa(String nome, String dni, String estado) {
        this.validador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        this.validador.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        this.validador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar pessoa: ");
    }

    /**
     * Método que verifica se uma pessoa existe no sistema.
     *
     * @param dni dni a ser procurado.
     * @return boolean informando se a pessoa existe (ou não).
     */
    public boolean existePessoa(String dni) {
        return this.pessoas.containsKey(dni);
    }


    /**
     * O método cadastrarPessoa a seguir e o método cadastrarPessoa anterior são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * @param nome       nome da pessoa a ser cadastrada.
     * @param dni        dni da pessa a ser cadastrada.
     * @param estado     estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @param partido    partido que a pessoa a ser cadastrada é filiada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        validaCadastrarPessoa(nome, dni, estado);
        if (existePessoa(dni)) {
            throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
        return true;
    }

    /**
     * Método responsável por cadastrar um deputado no sistema, cujos dados (dni e dataInicio), todos do tipo String,
     * são passados como parâmetro.
     *
     * @param dni          dni da pessoa a se tornar deputado.
     * @param dataDeInicio data de ínicio do mandato do deputado.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        validaCadastrarDeputado(dni, dataDeInicio);
        Pessoa pessoa = this.pessoas.get(dni);
        pessoa.viraDeputado(dataDeInicio);
    }
    /**
     * Método responsável por acesssar o Mapa que armazena as pessoas cadastradas e retornar uma
     * representação em String da pessoa cujo DNI foi passado como parâmetro.
     *
     * @param dni dni da pessoa a ser consultado no sistema.
     * @return a representação em String da pessoa, caso ela já tenha sido cadastrado.
     */
    public String exibirPessoa(String dni) {
        this.validador.validaDNI(dni, "Erro ao exibir pessoa: ");
        if (!this.pessoas.containsKey(dni)) {
            throw new NullPointerException("Erro ao exibir pessoa: pessoa nao encontrada");
        }
        return this.pessoas.get(dni).toString();

    }

    /**
     * Método responsável por validar um cadastro de um deputado.
     *
     * @param dni          dni do deputado.
     * @param dataDeInicio data de ínicio do mandato do deputado.
     */
    private void validaCadastrarDeputado(String dni, String dataDeInicio) {
        this.validador.validaDNI(dni, "Erro ao cadastrar deputado: ");
        if (!existePessoa(dni)) {
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa nao encontrada");
        }
        if (this.pessoas.get(dni).getPartido() == null) {
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
        }
        this.validador.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");
    }

    /**
     * Método que verifica se uma pessoa existente no sistema
     * eh o atributo diferente de null, o que significa que eh deputado.
     *
     * @param dni dni a ser procurado.
     * @return boolean informando se a pessoa é deputado ou não.
     */
    public boolean pessoaEhDeputado(String dni) {
        return this.pessoas.get(dni).getFuncao() != null;
    }

    /**
     * Método que incrementa 1 ao atributo leis de Deputado a
     * cada que vez que uma pessoa deputada propõe um projeto de lei.
     *
     * @param dni dni a ser procurado.
     *
     */
    public void incrementaLeisDeputado(String dni) {
        Funcao funcao = this.pessoas.get(dni).getFuncao();
        funcao.incrementaNumeroDeLeis();
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa a quantiade de deputados
     * cadastrados no sistema.
     *
     * @return inteiro que informa a quantiade de deputados.
     */
    public int totalDeputados() {
        int totalDeputados = 0;
        for (String dni : this.pessoas.keySet()) {
            if (this.pessoas.get(dni).getFuncao() != null) {
                totalDeputados++;
            }
        }
        return totalDeputados;
    }

    public Pessoa buscaPessoa(String dni){
        return this.pessoas.get(dni);
    }
}
