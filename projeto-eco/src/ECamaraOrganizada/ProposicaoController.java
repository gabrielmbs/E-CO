package ECamaraOrganizada;

import util.Contador;
import util.Validador;

import java.util.HashMap;
import java.util.Map;

public class ProposicaoController {
    /**
     * Representa mapa onde se armazena as contagens específicas de cada tipo de lei em consonância
     * com seu ano de cadastro, com a chave no formato: "tipoDeLei AnoCadastro".
     *
     * Permite o acompanhamento mais preciso da quantidade de um tipo específico de leis
     * cadastradas em um ano.
     */
    private Map<String, Contador> contadores;

    /**
     * Representa mapa onde se armazena as proposições de lei armazenadas no sistema,
     * com a chave no formato: "tipoDeLei ordemContagem/AnoDeCadastro".
     */
    private Map<String, ProposicaoAbstract> proposicoesDeLeis;
    private Validador validador;

    public ProposicaoController() {
        this.proposicoesDeLeis = new HashMap<>();
        this.validador = new Validador();
        this.contadores = new HashMap<>();
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
        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
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

        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
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
        if (this.deputadoController.existePessoa(dni)) {
            if (this.deputadoController.pessoaEhDeputado(dni)) {
                Contador contador = this.contadores.get(chaveContador);
                String codigoLei = "PEC " + contador.getContagem() + "/" + ano;
                this.proposicoesDeLeis.put(codigoLei, new ProjetoEmendaConstitucional(codigoLei, dni, ano, ementa, interesses, url, artigosConcatenados));
                contador.incrementaContagem();
                return codigoLei;
            } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
        } else throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta em uma determinada comissão, ele recebe como
     * parâmetro o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e o próximo
     * local no qual a porposta será votada.
     *
     * @param codigo código da proposta.
     * @param statusGovernista status da proposta.
     * @param proximoLocal próximo locla no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        validaVotarComissao(codigo, statusGovernista, proximoLocal);
        String localDeVotacao = this.proposicoesDeLeis.get(codigo).getLocalDeVotacao();

        int chao = (this.comissoes.get(localDeVotacao).getDNIs().length / 2) + 1;
        int votosFavoraveis = calculaVotosComissao(codigo, localDeVotacao, statusGovernista);

        if(this.proposicoesDeLeis.get(codigo).isConclusivo()){
            return votarComissaoPLConc(codigo, votosFavoraveis, chao, proximoLocal);
        }
        return votarComissaoNConc(codigo, votosFavoraveis, chao, proximoLocal);

    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta no plenário, ele recebe como parâmetro
     * o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e os presentes
     * no plenário (DNIs separados por vírgula), todos do tipo String. O método retorna um boolena que indica
     * o resultado da votação.
     *
     * @param codigo código da proposta.
     * @param statusGovernista status da proposta.
     * @param presentes deputados presentes para votação.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        String[] deputados = presentes.split(",");
        verificaQuorum(codigo, deputados);
        if(!this.proposicoesDeLeis.get(codigo).getPassouNaCCJC()){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }
        if(!this.proposicoesDeLeis.get(codigo).getProposicaoAtiva()){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
        int totalDeputados = this.deputadoController.totalDeputados();
        int chao = 0;
        int votosFavoraveis = calculaVotosPlenario(codigo, statusGovernista, deputados);
        boolean retorno = false;
        if ("PL".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao()) && !this.proposicoesDeLeis.get(codigo).isConclusivo()) {
            chao = (deputados.length / 2) + 1;
            this.proposicoesDeLeis.get(codigo).setProposicaoAtiva(false);
            if (votosFavoraveis >= chao) {
                retorno = true;
                this.deputadoController.incrementaLeisDeputado(this.proposicoesDeLeis.get(codigo).getDniAutor());
            }
        } else if ("PLP".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (totalDeputados / 2) + 1;
            retorno = aprovadaOuArquivada(codigo, votosFavoraveis, chao);
        } else if ("PEC".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (((3/5) * totalDeputados) / 2) + 1;
            retorno = aprovadaOuArquivada(codigo, votosFavoraveis, chao);
        }
        return retorno;
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
     * Método que verifica se um projeto de lei existe no sistema.
     *
     * @param codigo codigo da lei a ser procurado.
     * @return boolean informando se o projeto existe ou não.
     */
    public boolean existeLei(String codigo) {
        return this.proposicoesDeLeis.containsKey(codigo);
    }

    public ProposicaoAbstract buscaProposicao(String codigo){
        return this.proposicoesDeLeis.get(codigo);
    }

}
