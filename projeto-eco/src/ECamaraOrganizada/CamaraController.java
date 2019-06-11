package ECamaraOrganizada;

import util.Contador;
import util.Validador;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class CamaraController {
    /**
     * Representa mapa de pessoa, com a chave representando o dni e valor a pessoa.
     */
    private Map<String, Pessoa> pessoas;
    private Map<String, ProposicaoInterface> proposicoesDeLeis;
    private Map<String, Contador> contadores;

    /**
     * Conjunto de partido.
     */
    private Set<String> base;

    /**
     * Representa mapa de comissoes, com a chave representando o tema e o valor a comissao.
     */
    private Map<String, Comissao> comissoes;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    public CamaraController() {
        this.pessoas = new HashMap<>();
        this.base = new HashSet<>();
        this.validador = new Validador();
        this.proposicoesDeLeis = new HashMap<>();
        this.contadores = new HashMap<>();
        this.comissoes = new HashMap<>();
    }

    /**
     * Os métodos cadastrarPessoa a seguir são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     * <p>
     * Método responsável por cadastrar uma pessoa no sistema, cujos dados (nome, dni, estado e interesses),
     * todos do tipo String, são passados como parâmetro.
     * <p>
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     * <p>
     * Caso a pessoa já tenha sido cadastrada, o que é checado por meio da existência ou não de uma chave no Mapa
     * que armazena pessoas (se existe, ela já foi cadastrada, senão, ainda não foi cadastrada) a operação é abortada e
     * lança-se uma exceção. Caso contrário, ela será adicionada ao Mapa que armazena pessoas.
     * <p>
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
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
     * O método cadastrarPessoa a seguir e o método cadastrarPessoa anterior são sobrecarregados de modo
     * a podermos cadastrar no sistema uma pessoa que possua filiação a algum partido
     * ou não.
     * <p>
     * Método responsável por cadastrar uma pessoa no sistema, cujos dados (nome, dni, estado, interesses e partido),
     * todos do tipo String, são passados como parâmetro.
     * <p>
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     * <p>
     * Caso a pessoa já tenha sido cadastrada, o que é checado por meio da existência ou não de uma chave no Mapa
     * que armazena pessoas (se existe, ela já foi cadastrada, senão, ainda não foi cadastrada) a operação é abortada e
     * lança-se uma exceção. Caso contrário, ela será adicionada ao Mapa que armazena pessoas.
     * <p>
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
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
     * <p>
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     * <p>
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     * <p>
     * Caso a pessoa que vier a se tornar deputado não exista, é lançada uma exceção. Além disso, caso a pessoa não
     * tiver partido, também será lançada uma exceção.
     *
     * @param dni          dni da pessoa a se tornar deputado.
     * @param dataDeInicio data de ínicio do mandato do deputado.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        validaCadastrarDeputado(dni, dataDeInicio);
        Pessoa pessoa = this.pessoas.get(dni);
        pessoa.viraDeputado(dataDeInicio);
    }

    public String exibirPessoa(String dni) {
        this.validador.validaDNI(dni, "Erro ao exibir pessoa: ");
        if (!this.pessoas.containsKey(dni)) {
            throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
        }
        return this.pessoas.get(dni).toString();

    }

    /**
     * Método responsável por cadastrar um partido no sistema, recebendo como parâmetro o nome do partido.
     * <p>
     * Checa-se se esse parâmetro é nulo ou vazio, e se for, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * @param partido partido a ser cadastrado.
     */
    public void cadastrarPartido(String partido) {
        this.validador.validaString(partido, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
        this.base.add(partido);
    }

    /**
     * Método responsável por exibir a base, que é formada por diversos partidos, todos eles em String.
     *
     * @return uma String com todos os partidos da base.
     */
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
     * Método que verifica se uma pessoa existe no sistema.
     *
     * @param dni dni a ser procurado.
     * @return boolean informando se a pessoa existe (ou não).
     */
    private boolean existePessoa(String dni) {
        return this.pessoas.containsKey(dni);
    }

    private boolean pessoaEhDeputado(String dni) {
        return this.pessoas.get(dni).getFuncao() != null;
    }

    private boolean existeLei(String codigo) {
        return this.proposicoesDeLeis.containsKey(codigo);
    }

    private void incrementaLeisDeputado(String dni) {
        Funcao funcao = this.pessoas.get(dni).getFuncao();
        funcao.setNumeroDeLeis();
    }

    private void validaCadastrarLei(String dni, String ementa, String interesses, String url, int ano) {
        this.validador.validaString(ementa, "Erro ao cadastrar proposicao: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar proposicao: dni nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar proposicao: interesses nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar proposicao: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar pessoa: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar proposicao: ");

    }

    public String cadastraPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);

        String chaveContador = ano + "PL";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PL " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoDeLei(codigoLei, dni, ano, ementa, interesses, url, conclusivo));
                contador.incrementaContagem();
                incrementaLeisDeputado(dni);
                return codigoLei;
            } else throw new NullPointerException("Pessão não é deputado");
        } else throw new NullPointerException("Pessoa não cadastrada");
    }

    public String cadastraPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);


        String chaveContador = ano + "PLP";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }

        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PLP " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoLeiComplementar(codigoLei, dni, ano, ementa, interesses, url, artigos));
                contador.incrementaContagem();
                incrementaLeisDeputado(dni);
                return codigoLei;
            } else throw new NullPointerException("Pessão não é deputado");

        } else throw new NullPointerException("Pessoa não cadastrada");

    }


    public String cadastraPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);

        String chaveContador = ano + "PEC";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PEC " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoEmendaConstitucional(codigoLei, dni, ano, ementa, interesses, url, artigos));
                contador.incrementaContagem();
                incrementaLeisDeputado(dni);
                return codigoLei;
            } else throw new NullPointerException("Pessão não é deputado");
        } else throw new NullPointerException("Pessoa não cadastrada");
    }

    public String exibeProjeto(String codigo) {
        if (!existeLei(codigo)) {
            throw new NullPointerException("Lei não existe");
        } else return this.proposicoesDeLeis.get(codigo).toString();
    }

        /**
         * O método cadastraComissao serve para cadastrar no sistema uma comissão que possui um tema e uma lista de politicos
         * que o compõe.
         *
         * Verifica se cada um dos parâmetros passados são nulos ou vazios, e se forem, exceções do tipo NullPointerException
         * e IllegalArgumentExeception serão lançadas, respectivamente.
         *
         * Caso a comissão já tenha sido cadastrada, o que é checado por meio da existência ou não de uma chave no Mapa
         * que armazena comissões (se existe, ela já foi cadastrada, senão, ainda não foi cadastrada) a operação é abortada e
         * lança-se uma exceção. Caso contrário, ela será adicionada ao Mapa que armazena comissões.
         *
         *
         * @param tema representa o tema da comissão
         * @param politicos lista de politicos separados por ",".
         */
        public void cadastrarComissao (String tema, String politicos){
            this.validador.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
            this.validador.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
            if (this.comissoes.containsKey(tema)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
            }
            String[] arrayDeDNIs = geraArrayDeDNIsValidos(politicos);
            this.comissoes.put(tema, new Comissao(tema, arrayDeDNIs));
        }

        /**
         * Gera um array de Strings, que representa os dnis dos politicos.
         *
         * Ademais, checa-se se os dnis passado é válido (composto apenas de números
         * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
         * uma exceção com uma mensagem indicando que o dni é inválido.
         *
         * @param politicos uma String, que representa dnis separados por ","
         * @return Um array de Strings, que contem as dnis.
         */
        private String[] geraArrayDeDNIsValidos (String politicos){
            String[] arrayDeDNIs = politicos.split(",");
            for (String dni : arrayDeDNIs) {
                this.validador.validaDNI(dni, "Erro ao cadastrar comissao: ");
                if (!this.existePessoa(dni)) {
                    throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa inexistente");
                }
                if (!pessoaEhDeputado(dni)) {
                    throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
                }
            }
            return arrayDeDNIs;
        }
    }




