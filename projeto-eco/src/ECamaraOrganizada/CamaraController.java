package ECamaraOrganizada;

import util.Validador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class CamaraController {
    private Map<String, Pessoa> pessoas;

    private Set<String> base;

    public CamaraController(){
        this.pessoas = new HashMap<>();
        this.base = new HashSet<>();
    }

    /**
     * Os métodos cadastrarPessoa a seguir são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * Método responsável por cadastrar uma pessoa no sistema, cujos dados (nome, dni, estado e interesses),
     * todos do tipo String, são passados como parâmetro.
     *
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * Caso a pessoa já tenha sido cadastrada, o que é checado por meio da existência ou não de uma chave no Mapa
     * que armazena pessoas (se existe, ela já foi cadastrada, senão, ainda não foi cadastrada) a operação é abortada e
     * lança-se uma exceção. Caso contrário, ela será adicionada ao Mapa que armazena pessoas.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * @param nome nome da pessoa a ser cadastrada.
     * @param dni dni da pessa a ser cadastrada.
     * @param estado estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        validaCadastrarPessoa(nome, dni, estado);
        validaDni(dni);

        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses));
        return true;
    }

    /**
     * O método cadastrarPessoa a seguir e o método cadastrarPessoa anterior são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     *
     * Método responsável por cadastrar uma pessoa no sistema, cujos dados (nome, dni, estado, interesses e partido),
     * todos do tipo String, são passados como parâmetro.
     *
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * Caso a pessoa já tenha sido cadastrada, o que é checado por meio da existência ou não de uma chave no Mapa
     * que armazena pessoas (se existe, ela já foi cadastrada, senão, ainda não foi cadastrada) a operação é abortada e
     * lança-se uma exceção. Caso contrário, ela será adicionada ao Mapa que armazena pessoas.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * @param nome nome da pessoa a ser cadastrada.
     * @param dni dni da pessa a ser cadastrada.
     * @param estado estado da pessoa a ser cadastrada.
     * @param interesses interesses da pessoa a ser cadastrada.
     * @param partido partido que a pessoa a ser cadastrada é filiada.
     * @return true, se a pessoa foi cadastrada com êxito.
     */
    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        validaCadastrarPessoa(nome, dni, estado);
        validaDni(dni);

        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
        return true;
    }

    private void validaDni(String dni) {
        if (this.pessoas.containsKey(dni)) {
            throw new NullPointerException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
    }

    private void validaCadastrarPessoa(String nome, String dni, String estado) {
        Validador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Validador.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Validador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        Validador.validaDNI(dni, "Erro ao cadastrar pessoa: dni invalido");
    }

    /**
     * Método responsável por cadastrar um deputado no sistema, cujos dados (dni e dataInicio), todos do tipo String,
     * são passados como parâmetro.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * Caso a pessoa que vier a se tornar deputado não exista, é lançada uma exceção. Além disso, caso a pessoa não
     * tiver partido, também será lançada uma exceção.
     *
     * @param dni dni da pessoa a se tornar deputado.
     * @param dataDeInicio data de ínicio do mandato do deputado.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        Validador.validaString(dni, "Erro ao cadastrar deputado: dni nao pode ser vazio ou nulo");
        Validador.validaDNI(dni, "Erro ao cadastrar deputado: dni invalido");
        if (!this.pessoas.containsKey(dni)) {
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa nao encontrada");
        }
        if(this.pessoas.get(dni).getPartido() == null){
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
        }
        Validador.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        Validador.validaDataInvalida(dataDeInicio, "Erro ao cadastrar deputado: data invalida");
        Validador.validaDataFutura(dataDeInicio, "Erro ao cadastrar deputado: data futura");

        Pessoa pessoa = this.pessoas.get(dni);
        pessoa.viraDeputado(dataDeInicio);
    }


    public String exibirPessoa(String dni) {
        Validador.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
        Validador.validaDNI(dni, "Erro ao exibir pessoa: dni invalido");
        if (!this.pessoas.containsKey(dni)) {
            throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
        }
        return this.pessoas.get(dni).toString();

    }

    public void cadastrarPartido(String partido) {
        Validador.validaString(partido, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
        this.base.add(partido);
    }

    public String exibirBase() {
        List<String> listaPartidos = new ArrayList<>(this.base);
        listaPartidos.sort(String::compareTo);

        if (listaPartidos.size() == 0) {
            return "";
        }

        String resultado = "";
        for (String partido : listaPartidos) {
            resultado += partido + ",";
        }

        return resultado.substring(0, (resultado.length() - 1));
    }
}
