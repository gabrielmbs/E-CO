package entidades;

import util.Validador;

import java.io.Serializable;

/**
 * Representação de um Projeto de Lei, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String,  ano do tipo int e conclusivo do tipo boolean.
 */
public class PL extends PropostaAbstract implements Serializable {

    /**
     * Atributo que será utilizado para validações.
     */
    private Validador validador;

    /**
     * Método responsável por criar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * @param codigoLei  código da lei.
     * @param dni        dni do autor do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        endereço url do projeto.
     * @param ano        ano de criacção do projeto
     * @param conclusivo situção conclusiva do projeto
     */
    public PL(String codigoLei, String dni, int ano, String ementa, String interesses, String url,
              boolean conclusivo) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.validador = new Validador();
        this.validador.validaString(codigoLei, "Erro ao cadastrar projeto: codigo de lei nao pode ser vazio ou nulo");
        this.validador.validaProjeto(dni, ano, ementa, interesses, url);
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
        if (this.conclusivo) {
            return "Projeto de Lei" + super.toString() + "Conclusiva" + " - " + this.situacao;
        } else return "Projeto de Lei" + super.toString() + this.situacao;
    }

    /**
     * Método que verifica se o quorum é válido.
     *
     * @param deputados
     * @param totalDeDeputados
     */
    @Override
    public void verificaQuorum(String[] deputados, int totalDeDeputados) {
        int quorum = (totalDeDeputados / 2) + 1;
        if (deputados.length < quorum) {
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
        }
    }

    /**
     * Método que calcula o chão da proposta.
     *
     * @param participantes participantes que irá depender da proposta.
     * @return int representando o chão.
     */
    @Override
    public int calculaChao(int participantes) {
        return ((participantes / 2) + 1);
    }

    /**
     * Método para votar a proposta na comissao.
     *
     * @param proximoLocal    próximo local de votação.
     * @param chao            quantidade mínima de deputados.
     * @param votosFavoraveis total de votos favoraveis.
     * @param autor           autor da proposta.
     * @return boolean informando se foi aprovado ou não a proposta.
     */
    @Override
    public boolean votarComissao(String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        if (this.conclusivo) {
            return votarComissaoConc(proximoLocal, chao, votosFavoraveis, autor);
        }
        return votarComissaoNConc(proximoLocal, chao, votosFavoraveis, autor);
    }

    @Override
    public boolean votarPlenario(String[] deputados, Pessoa deputado, int votosFavoraveis, int totalDeputados) {
        if (!this.passouNaCCJC) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }
        if (!this.proposicaoAtiva) {
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
            } else {
                atualizaTramitacaoLei("REJEITADO (Plenario)");
            }
        }
        return result;
    }

    private boolean votarComissaoNConc(String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        boolean result = false;
        if (votosFavoraveis >= chao) {
            this.quantidadeDeAprovacoes++;
            result = true;
            atualizaTramitacaoLei("APROVADO (" + this.localDeVotacao + ")");
            if ("plenario".equals(proximoLocal)) {
                this.situacao = "EM VOTACAO (Plenario - 1o turno)";
                atualizaTramitacaoLei("EM VOTACAO (Plenario)");
            } else atualizaTramitacaoLei("EM VOTACAO (" + proximoLocal + ")");
        } else {
            atualizaTramitacaoLei("REJEITADO (" + this.localDeVotacao + ")");
        }
        this.localDeVotacao = proximoLocal;
        this.passouNaCCJC = true;
        this.quantidadeDeComissoes++;
        return result;
    }

    private boolean votarComissaoConc(String proximoLocal, int chao, int votosFavoraveis, Pessoa autor) {
        boolean result = false;
        if (votosFavoraveis < chao && !this.passouNaCCJC) {
            this.proposicaoAtiva = false;
            this.passouNaCCJC = true;
            this.quantidadeDeComissoes++;
            atualizaTramitacaoLei("REJEITADO (" + this.localDeVotacao + ")");
        } else if (votosFavoraveis >= chao && !this.passouNaCCJC) {
            this.passouNaCCJC = true;
            this.situacao = "EM VOTACAO (" + proximoLocal + ")";
            atualizaTramitacaoLei("APROVADO (" + this.localDeVotacao + ")");
            this.localDeVotacao = proximoLocal;
            this.quantidadeDeComissoes++;
            this.quantidadeDeAprovacoes++;
            atualizaTramitacaoLei("EM VOTACAO (" + this.localDeVotacao + ")");
            result = true;
        } else if (votosFavoraveis >= chao) {
            if (proximoLocal.equals("-")) {
                this.situacao = "APROVADO";
                atualizaTramitacaoLei("APROVADO (" + this.localDeVotacao + ")");
                autor.getFuncao().incrementaNumeroDeLeis();
                this.proposicaoAtiva = false;
                this.quantidadeDeComissoes++;
            }
            this.quantidadeDeAprovacoes++;
            result = true;

        } else if (votosFavoraveis < chao) {
            this.proposicaoAtiva = false;
            this.quantidadeDeComissoes++;
            if (proximoLocal.equals("-")) {
                this.situacao = "ARQUIVADO";
                atualizaTramitacaoLei("REJEITADO (" + this.localDeVotacao + ")");
            }
        }
        return result;
    }
}
