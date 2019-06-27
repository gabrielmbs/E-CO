package entidades;

import util.Validador;
import java.io.Serializable;

/**
 * Representação de um Projeto de Lei, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String,  ano do tipo int e conclusivo do tipo boolean.
 *
 */
public class ProjetoDeLei extends ProposicaoAbstract implements Serializable {

    /**
     * Atributo que será utilizado para validações.
     */
    private Validador validador;

    /**
     * Método responsável por criar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * @param codigoLei código da lei.
     * @param dni dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url endereço url do projeto.
     * @param ano ano de criacção do projeto
     * @param conclusivo situção conclusiva do projeto
     *
     */
    public ProjetoDeLei(String codigoLei, String dni, int ano, String ementa, String interesses, String url,
                        boolean conclusivo) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.validador = new Validador();
        this.validador.validaString(codigoLei,"Erro ao cadastrar projeto: codigo de lei nao pode ser vazio ou nulo");
        this.validador.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar projeto: ");
        this.conclusivo = conclusivo;
        this.tipoDeProposicao = "PL";
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato "Projeto de Lei - Código - DNI - Ementa - Conclusivo - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    public String toString() {
        if(this.conclusivo){
            return "Projeto de Lei" + super.toString() + "Conclusiva"  + " - " + this.situacao;
        }
        else return "Projeto de Lei" + super.toString() + this.situacao;
    }

    @Override
    public void verificaQuorum(String[] deputados, int totalDeDeputados) {
        int quorum = (totalDeDeputados / 2) + 1;
        if (deputados.length < quorum) {
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
        }
    }

    @Override
    public int calculaChao(int participantes) {
        return ((participantes / 2) + 1);
    }

    @Override
    public boolean votarNaoConclusivoNaComissao(String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        boolean result = false;
        if(votosFavoraveis >= chao) {
            this.quantidadeDeAprovacoes++;
            result = true;
            atualizaTramitacaoLei("APROVADO (" + this.localDeVotacao + ")");
            if ("plenario".equals(proximoLocal)) {
                this.situacao = "EM VOTACAO (Plenario - 1o turno)";
                atualizaTramitacaoLei("EM VOTACAO (Plenario)");
            } else atualizaTramitacaoLei("EM VOTACAO (" + proximoLocal + ")");
        }
        else{
            atualizaTramitacaoLei("REJEITADO (" + this.localDeVotacao + ")");
        }
        this.localDeVotacao = proximoLocal;
        this.passouNaCCJC = true;
        this.quantidadeDeComissoes++;
        return result;
    }

    @Override
    public boolean votarPlenario(String[] deputados, Pessoa deputado, int votosFavoraveis, int totalDeputados) {
        if(!this.passouNaCCJC){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }
        if(!this.proposicaoAtiva){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
        int chao = calculaChao(deputados.length);
        boolean result = false;
        if (!this.conclusivo) {
            this.proposicaoAtiva = false;
            if (votosFavoraveis >= chao) {
                this.quantidadeDeAprovacoes++;
                atualizaTramitacaoLei("APROVADO (Plenario)");
                deputado.getFuncao().incrementaNumeroDeLeis();
                result = true;
            } else{
                atualizaTramitacaoLei("REJEITADO (Plenario)");
            }
        }
        return result;
    }
}
