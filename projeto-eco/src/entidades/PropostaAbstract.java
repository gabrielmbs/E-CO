package entidades;

import java.io.Serializable;
import java.util.*;

/**
 * Representação abstrata de um Projeto , caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String e ano do tipo int.
 */
public abstract class PropostaAbstract implements Serializable {

    /**
     * DNI do autor do projeto.
     */
    private String dniAutor;

    /**
     * Ano da criação do projeto.
     */
    private int ano;

    /**
     * Código do projeto.
     */
    private String codigoLei;

    /**
     * Ementa do projeto.
     */
    private String ementa;

    /**
     * Interesses do projeto.
     */
    private String interesses;

    /**
     * Endereço URL do projeto.
     */
    private String urlDocumento;

    /**
     * Indica se um projeto está tramitando pela câmara ou se já foi encerrado.
     */
    protected boolean proposicaoAtiva;

    /**
     * Indica se uma proposta já foi votada no plenário.
     */
    protected boolean passouNoPlenario;

    /**
     * Atributo que indica se uma proposta já passou pela CCJC.
     */
    protected boolean passouNaCCJC;

    /**
     * Atributo que denota o estado conclusivo do Projeto de Lei.
     */
    protected boolean conclusivo;

    /**
     * Situação em que se encontra o projeto.
     */
    protected String situacao;

    /**
     * Local onde o projeto se encontra para votação.
     */
    protected String localDeVotacao;

    /**
     * Indica o tipo de projeto (PL, PLP ou PEC).
     */
    protected String tipoDeProposicao;

    protected int quantidadeDeComissoes;

    protected int quantidadeDeAprovacoes;


    /**
     * Lista que armazena todas as situações e pareceres das votaçoẽs pela qual o projeto de lei passou.
     */
    protected List<String> tramitacao;


    /**
     * Método responsável por criar um Projeto de Emenda Constitucional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String, ano do tipo int
     * são passados como parâmetro.
     * <p>
     * Inicializa o atributo situação como "EM VOTACAO (CCJC)".
     *
     * @param codigoLei    código da lei.
     * @param dniAutor     dni do autor do projeto.
     * @param ementa       ementa do projeto.
     * @param interesses   interesses do projeto.
     * @param urlDocumento endereço url do projeto.
     * @param ano          ano de criacção do projeto
     */
    public PropostaAbstract(String dniAutor, Integer ano, String codigoLei, String ementa, String interesses,
                            String urlDocumento) {
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.codigoLei = codigoLei;
        this.ementa = ementa;
        this.interesses = interesses;
        this.urlDocumento = urlDocumento;
        this.situacao = "EM VOTACAO (CCJC)";
        this.localDeVotacao = "CCJC";
        this.proposicaoAtiva = true;
        this.passouNoPlenario = false;
        this.passouNaCCJC = false;
        this.quantidadeDeComissoes = 0;
        this.quantidadeDeAprovacoes = 0;
        this.inicializarTramitacao();
    }

    /**
     * Método que verifica se o quorum é válido.
     *
     * @param deputados
     * @param totalDeDeputados
     */
    public abstract void verificaQuorum(String[] deputados, int totalDeDeputados);

    /**
     * Método que calcula o chão da proposta.
     *
     * @param participantes participantes que irá depender da proposta.
     * @return int representando o chão.
     */
    public abstract int calculaChao(int participantes);

    /**
     * Método para votar a proposta na comissao.
     *
     * @param proximoLocal próximo local de votação.
     * @param chao quantidade mínima de deputados.
     * @param votosFavoraveis total de votos favoraveis.
     * @param autor autor da proposta.
     * @return boolean informando se foi aprovado ou não a proposta.
     */
    public abstract boolean votarComissao(String proximoLocal, int chao, int votosFavoraveis, Pessoa autor);

    /**
     * Método responsável por realizar a votação de um determinada proposição no plenário, ele recebe como parâmetros
     * o deputado que criou a lei, a quantidade de votos favoráveis à aprovação da lei e o total de deputados cadastrados
     * no sistema. O método retorna um boolean que indica se a lei foi aprovada ou não.
     *
     * @param deputado o deputado que criou a lei.
     * @param votosFavoraveis votos favoráveis à aprovação da lei.
     * @param totalDeputados total de deputados cadastrados no sistema.
     * @return um boolean que indica se a lei foi ou não aprovada.
     */
    public boolean votarPlenario(Pessoa deputado, int votosFavoraveis, int totalDeputados) {
        if(!this.passouNaCCJC){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }
        if(!this.proposicaoAtiva){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
        int chao = calculaChao(totalDeputados);
        boolean result = false;
        if (votosFavoraveis >= chao) {
            this.quantidadeDeAprovacoes++;
            result = true;
            if (this.passouNoPlenario) {
                this.situacao = "APROVADO";
                atualizaTramitacaoLei("APROVADO (Plenario - 2o turno)");
                deputado.getFuncao().incrementaNumeroDeLeis();
                this.proposicaoAtiva = false;
            } else {
                atualizaTramitacaoLei("APROVADO (Plenario - 1o turno)");
                this.situacao = "EM VOTACAO (Plenario - 2o turno)";
                atualizaTramitacaoLei("EM VOTACAO (Plenario - 2o turno)");
                this.passouNoPlenario = true;
            }
        } else {
            if (!this.passouNoPlenario) {
                atualizaTramitacaoLei("REJEITADO (Plenario - 1o turno)");
            }
            else atualizaTramitacaoLei("REJEITADO (Plenario - 2o turno)");
            this.situacao = "ARQUIVADO";
            this.proposicaoAtiva = false;
        }
        return result;
    }

    /**
     * Método responsável por adicionar uma nova situação à lista de pareceres das votações
     * de um determinado projeto de lei
     *
     * @param situacao parecer atual do projeto de lei em questão.
     */
    public void atualizaTramitacaoLei(String situacao){
        if((situacao.contains("REJEITADO") || situacao.contains("APROVADO"))){
            List<String> novaTramitacao = new ArrayList<String>();
            for(String statusLei : this.tramitacao){
                if(!statusLei.contains("EM VOTACAO")){
                    novaTramitacao.add(statusLei);
                }
            }
            novaTramitacao.add(situacao);
            this.tramitacao = novaTramitacao;
        }
        else this.tramitacao.add(situacao);
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato: - this.código da lei - dni do autor - ementa -
     * <p>
     * É usado para compor o toString individual de cada tipo específico
     * de projeto de lei.
     *
     * @return retorna a representação em String do projeto
     */
    @Override
    public String toString() {
        return " - " + this.codigoLei + " - " + this.dniAutor +
                " - " + this.ementa + " - ";
    }

    /**
     * Método que sobreescreve o método equals de Objects para se enquadrar nos moldes
     * da classe PropostaAbstract. Um projeto é igual a outro projeto se ambos possuírem código de lei iguais.
     *
     * @param o parâmetro a ser comparado.
     * @return true, se os objetos forem iguais, false, se os objetos forem diferentes
     * ou se o objeto passado como parâmetro for null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropostaAbstract that = (PropostaAbstract) o;
        return codigoLei.equals(that.codigoLei);
    }

    /**
     * Método que sobreescreve o método hashcode de Objects para se enquadrar nos moldes
     * da classe PropostaAbstract. Um projeto é igual a outro se ambos possuírem o mesmo código de lei,
     * portanto devem possuir o mesmo hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigoLei);
    }

    /**
     * Retorna o dni do autor da proposta.
     *
     * @return dni do autor da proposta.
     */
    public String getDniAutor() {
        return dniAutor;
    }

    /**
     * Retorna os interesses da proposta.
     *
     * @return os interesses da proposta.
     */
    public String getInteresses() {
        return interesses;
    }

    /**
     * Retorna o local no qual a proposta está para ser votada.
     *
     * @return local no qual a proposta está para ser votada.
     */
    public String getLocalDeVotacao() {
        return localDeVotacao;
    }

    /**
     * Retorna um boolean que indica se a proposta é conclusiva ou não.
     *
     * @return boolean que indica se a proposta é conclusiva.
     */
    public boolean isConclusivo() {
        return conclusivo;
    }

    /**
     * Retorna um boolean que indica se a proposta está tramitanto na câmara ou se já foi encerrada.
     *
     * @return um boolean que indica se a porposta está (ou não) tramitando.
     */
    public boolean getProposicaoAtiva() {
        return proposicaoAtiva;
    }

    /**
     * Retorna uam String que indica o tipo do projeto (PL, PLP ou PEC).
     *
     * @return uma String que indica o tipo do projeto.
     */
    public String getTipoDeProposicao() {
        return tipoDeProposicao;
    }

    /**
     * Retorna a lista de situações e pareceres de votações para um determinado projeto de lei.
     *
     * @return lista de situações e pareceres de votações para um determinado projeto de lei.
     */
    public List<String> getTramitacao() {
        return tramitacao;
    }

    /**
     * Pega o código de lei da proposta.
     *
     * @return String que representa o código de lei.
     */
    public String getCodigoLei() {
        return codigoLei;
    }

    /**
     * Pega a situação atual da proposta.
     *
     * @return String que representa a situação da proposta.
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Retorna a quantidade de comissões por onde uma determinada proposição passou.
     *
     * @return Integer que indica por quantas comissões a proposição passou.
     */
    public Integer getQuantidadeDeComissoes() {
        return quantidadeDeComissoes;
    }

    /**
     * Retorna a quantidade de aprovações que uma determinada proposição teve ao longo de sua tramitação.
     *
     * @return Integer que indica quantas aprovações a proposição teve.
     */
    public Integer getQuantidadeDeAprovacoes() {
        return quantidadeDeAprovacoes;
    }

    /**
     * Método auxiliar para inicializar o estado da tramitação.
     */
    private void inicializarTramitacao(){
        this.tramitacao = new ArrayList<>();
        this.tramitacao.add("EM VOTACAO (CCJC)");
    }
}
