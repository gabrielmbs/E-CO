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
    private Map<String, ProposicaoAbstract> proposicoesDeLeis;
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
    private boolean passouNaCCJC;

    public CamaraController() {
        this.pessoas = new HashMap<>();
        this.base = new HashSet<>();
        this.validador = new Validador();
        this.proposicoesDeLeis = new HashMap<>();
        this.contadores = new HashMap<>();
        this.comissoes = new HashMap<>();
        this.passouNaCCJC = false;
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
     * Método responsável por cadastrar um partido no sistema, recebendo como parâmetro o nome do partido.
     * 
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
    
    /**
     * Método que verifica se uma pessoa existente no sistema
     * eh o atributo diferente de null, o que significa que eh deputado.
     *
     * @param dni dni a ser procurado.
     * @return boolean informando se a pessoa é deputado ou não.
     */
    private boolean pessoaEhDeputado(String dni) {
        return this.pessoas.get(dni).getFuncao() != null;
    }

    /**
     * Método que verifica se um projeto de lei existe no sistema.
     *
     * @param codigo codigo da lei a ser procurado.
     * @return boolean informando se o projeto existe ou não.
     */
    private boolean existeLei(String codigo) {
        return this.proposicoesDeLeis.containsKey(codigo);
    }

    /**
     * Método que incrementa 1 ao atributo leis de Deputado a
     * cada que vez que uma pessoa deputada propõe um projeto de lei.
     *
     * @param dni dni a ser procurado.
     *
     */
    private void incrementaLeisDeputado(String dni) {
        Funcao funcao = this.pessoas.get(dni).getFuncao();
        funcao.setNumeroDeLeis();
    }

    /**
     * Método responsável por validar o cadastro de um projeto de lei
     * que opere sobre artigos da Constituição.
     *
     * @param dni dni da lei a ser cadastrada.
     * @param ementa ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url url da lei a ser cadastrada.
     * @param ano ano da lei a ser cadastrada.
     * @param artigos artigos com os quais a lei irá trabalhar
     */
    private void validaCadastrarLeiComArtigo(String dni, String ementa, String interesses, String url, int ano, String artigos) {
        validaCadastrarLei(dni,ementa, interesses, url, ano);
        this.validador.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
    }

    /**
     * Método responsável por validar o cadastro de um projeto de lei.
     *
     * @param dni dni da lei a ser cadastrada.
     * @param ementa ementea da lei a ser cadastrada.
     * @param interesses interesses da lei a ser cadastrada.
     * @param url url da lei a ser cadastrada.
     * @param ano ano da lei a ser cadastrada.
     */
    private void validaCadastrarLei(String dni, String ementa, String interesses, String url, int ano) {
        this.validador.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar projeto: ");

    }

    /**
     * Método responsável por separar artigos com vírgula e espaço, como no
     * formato a seguir: "artigo1, artigo2, ..., artigoN".
     * @param artigos String base para geração da nova string dos artigos concatenados.
     * @return
     */
    private String contatenaArtigos(String artigos){
        if(artigos.contains(",")){
            String artigosConcatenados = "";
            for(String caractere : artigos.split(",")){
                artigosConcatenados += caractere + ", ";

            }
            return artigosConcatenados.substring(0, artigosConcatenados.length() - 2);
        }
        return artigos;
    }
    /**
     * Método responsável por cadastrar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * @param dni dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url endereço url do projeto.
     * @param ano ano de criação do projeto.
     * @param conclusivo situção conclusiva do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validaCadastrarLei(dni, ementa, interesses, url, ano);

        String chaveContador = ano + "PL";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PL " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoDeLei(codigoLei, dni, ano, ementa, interesses, url,
                                                                        conclusivo));
                contador.incrementaContagem();
                return codigoLei;
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Método responsável por cadastrar um Projeto de Lei Complementar no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni dni da pessoa deputada autor da lei.
     * @param ementa ementa da lei.
     * @param interesses interesses da lei.
     * @param url endereço url da lei.
     * @param artigos artigos da constituição sobre os quais a lei vai atuar.
     * @param ano ano de criação do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        String artigosConcatenados = contatenaArtigos(artigos);

        String chaveContador = ano + "PLP";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }

        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PLP " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoLeiComplementar(codigoLei, dni, ano, ementa,
                                                                                interesses, url,
                                                                                artigosConcatenados));
                contador.incrementaContagem();
                return codigoLei;
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");

        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");

    }

    /**
     * Método responsável por cadastrar um Projeto de Emenda Constituicional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni dni da pessoa deputada autor da lei.
     * @param ementa ementa da lei.
     * @param interesses interesses da lei.
     * @param url endereço url da lei.
     * @param artigos artigos da constituição sobre os quais a lei vai atuar.
     * @param ano ano de criação do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaCadastrarLeiComArtigo(dni, ementa, interesses, url, ano, artigos);
        String artigosConcatenados = contatenaArtigos(artigos);

        String chaveContador = ano + "PEC";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        if (existePessoa(dni)) {
            if (pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PEC " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoEmendaConstitucional(codigoLei, dni, ano, ementa, interesses, url, artigosConcatenados));
                contador.incrementaContagem();
                return codigoLei;
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Método responsável por acesssar o Mapa que armazena os projetos de lei cadastrados e retornar uma
     * representação em String da lei cujo código foi passado como parâmetro.
     *
     * @param codigo dni da pessoa a ser consultado no sistema.
     * @return a representação em String da pessoa, caso ela já tenha sido cadastrado.
     */
    public String exibirProjeto(String codigo) {
        this.validador.validaString(codigo,"Erro ao exibir projeto: codigo de lei nao pode ser vazio ou nulo");
        if (!existeLei(codigo)) {
            throw new NullPointerException("Erro ao exibir projeto: projeto inexistente");
        } else {
            return this.proposicoesDeLeis.get(codigo).toString();
        }
    }

    /**
     * O método cadastraComissao serve para cadastrar no sistema uma comissão que possui um tema e uma lista de politicos
     * que o compõe.
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

    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validador.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
        if(!"GOVERNISTA".equals(statusGovernista) && !"OPOSICAO".equals(statusGovernista) && !"LIVRE".equals(statusGovernista)){
            throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
        }
        if(!existeLei(codigo)){
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }

        if (!this.comissoes.containsKey("CCJC")) {
            throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
        }
        if(!this.proposicoesDeLeis.get(codigo).getProposicaoAtiva()){
            if (this.proposicoesDeLeis.get(codigo).isConclusivo()) {
                throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
            } else throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
        }
        return votarComissaoPLConc(codigo, statusGovernista, proximoLocal);
    }

    private boolean votarComissaoPLConc(String codigo, String statusGovernista, String proximoLocal) {
        String localDeVotacao = this.proposicoesDeLeis.get(codigo).getLocalDeVotacao();

        int chao = (this.comissoes.get(localDeVotacao).getDNIs().length / 2) + 1;
        int votosFavoraveis = calculaVotos(codigo, localDeVotacao, statusGovernista);

        this.proposicoesDeLeis.get(codigo).setLocalDeVotacao(proximoLocal);
        boolean retorno = false;
        if (votosFavoraveis < chao && !this.passouNaCCJC) {
            this.proposicoesDeLeis.get(codigo).setProposicaoAtiva(false);
            this.passouNaCCJC = true;
        }else if(votosFavoraveis >= chao && !this.passouNaCCJC){
            this.passouNaCCJC = true;
            this.proposicoesDeLeis.get(codigo).setSituacao("EM VOTACAO (" + proximoLocal + ")");
            retorno = true;
        }else if(votosFavoraveis >= chao){
            if(proximoLocal.equals("-")){
                this.proposicoesDeLeis.get(codigo).setSituacao("APROVADO");
                incrementaLeisDeputado(this.proposicoesDeLeis.get(codigo).getDniAutor());
            }
            retorno = true;
        }else if(votosFavoraveis < chao){
            this.proposicoesDeLeis.get(codigo).setProposicaoAtiva(false);
            if(proximoLocal.equals("-")){
                this.proposicoesDeLeis.get(codigo).setSituacao("ARQUIVADO");
            }
        }
        return retorno;
    }

    private int calculaVotos(String codigo, String comissao, String statusGovernista) {
        int votosFavoraveis = 0;

        for (String dni : this.comissoes.get(comissao).getDNIs()) {
            if (votoPolitico(codigo, statusGovernista, dni) == 1) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    private int calculaVotosPlenario(String codigo, String statusGovernista, String[] presentes) {
        int votosFavoraveis = 0;

        for (String dni : presentes) {
            if (votoPolitico(codigo, statusGovernista, dni) == 1) {
                votosFavoraveis++;
            }
        }
        return votosFavoraveis;
    }

    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        String[] deputados = presentes.split(",");
        int chao = 0;
        int votosFavoraveis = calculaVotosPlenario(codigo, statusGovernista, deputados);
        boolean retorno = false;
        if ("PL".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao()) && !this.proposicoesDeLeis.get(codigo).isConclusivo()) {
            chao = (deputados.length / 2) + 1;

            if (votosFavoraveis >= chao) {
                retorno = true;
            } else retorno = false;
        } else if ("PLP".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (totalDeputados() / 2) + 1;

            if (votosFavoraveis >= chao) {
                retorno = true;
            } else retorno = false;
        } else if ("PEC".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (((3/5) * totalDeputados()) / 2) + 1;

            if (votosFavoraveis >= chao) {
                retorno = true;
            } else retorno = false;
        }
        return retorno;
    }

    private int totalDeputados() {
        int totalDeputados = 0;
        for (String dni : this.pessoas.keySet()) {
            if (this.pessoas.get(dni).getFuncao() != null) {
                totalDeputados++;
            }
        }
        return totalDeputados;
    }

    /**
     * Esse método auxiliar retorna um inteiro que informa se foi aprovado ou não o voto.
     * 1 para aprovado - 1 para reprovado.
     *
     * @param codigo código da proposição.
     * @param statusGovernista status.
     * @return int.
     */
    private int votoPolitico(String codigo, String statusGovernista, String dni){
        Pessoa deputado = this.pessoas.get(dni);
        boolean ehDaBase = this.base.contains(deputado.getPartido());
        boolean temInteressesEmComum = intEmComum(deputado, codigo);
        int saida = 0;
        if("GOVERNISTA".equals(statusGovernista) && ehDaBase) {
            saida = 1;
        }else if("OPOSICAO".equals(statusGovernista) && ehDaBase){
            saida = -1;
        }else if("LIVRE".equals(statusGovernista) && temInteressesEmComum){
            saida = 1;
        }else if("LIVRE".equals(statusGovernista) && !temInteressesEmComum){
            saida = -1;
        }
        return saida;
    }

    /**
     * Método auxiliar que indica se um deputado e uma proposição têm interesses em comum.
     *
     * @param deputado deputado a ser analisado.
     * @param codigo codigo da proposição a ser analisada.
     * @return boolean indicando se tem ou não interesse em comum.
     */
    private boolean intEmComum(Pessoa deputado, String codigo) {
        String[] interessesDep = deputado.getInteresses().split(",");
        String[] interessesPl = this.proposicoesDeLeis.get(codigo).getInteresses().split(",");
        for(int i=0; i < interessesDep.length; i++){
            for(int j = 0; j < interessesPl.length; j++){
                if(interessesDep[i].equals(interessesPl[j])){
                    return true;
                }
            }
        }
        return false;
    }
}