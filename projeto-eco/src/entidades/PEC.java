package entidades;

import util.Validador;

import java.io.Serializable;

/**
 * Representação de um Projeto de Emenda Constitucional, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses, url e artigos, todos do tipo String,  ano do tipo int.
 */
public class PEC extends PropostaAbstract implements Serializable {

    /**
     * Atributo que denota sobre quais artigos da Constituição o projeto vai atuar.
     */
    private String artigos;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    /**
     * Método responsável por criar um Projeto de Emenda Constitucional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String, ano do tipo int
     * são passados como parâmetro.
     *
     * @param codigoLei  código da lei.
     * @param dni        dni do autor do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        endereço url do projeto.
     * @param ano        ano de criacção do projeto
     * @param artigos    artigos da Constituição sobre os quais o projetor irá atuar.
     */
    public PEC(String codigoLei, String dni, int ano, String ementa, String interesses,
               String url, String artigos) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.validador = new Validador();
        this.validador.validaString(codigoLei, "Erro ao cadastrar projeto: codigo de lei nao pode ser vazio ou nulo");
        this.validador.validaProjeto(dni, ano, ementa, interesses, url);
        this.validador.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
        this.artigos = artigos;
        this.tipoDeProposicao = "PEC";
        this.conclusivo = false;
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato "Projeto de Emenda Constitucional - Código - DNI - Ementa - Artigos - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    @Override
    public String toString() {
        return "Projeto de Emenda Constitucional" + super.toString() + this.artigos + " - " + this.situacao;
    }

    /**
     * Método que verifica se o quorum é válido.
     *
     * @param deputados
     * @param totalDeDeputados
     */
    @Override
    public void verificaQuorum(String[] deputados, int totalDeDeputados) {
        int quorum = ((3 / 5) * totalDeDeputados) + 1;
        if (deputados.length < quorum) {
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
        }
    }

    @Override
    public int calculaChao(int participantes) {
        return ((3 * participantes) / 5 + 1);
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
        boolean result = false;
        if ("plenario".equals(proximoLocal)) {
            this.situacao = "EM VOTACAO (Plenario - 1o turno)";
            atualizaTramitacaoLei("EM VOTACAO (Plenario - 1o turno)");
        } else atualizaTramitacaoLei("EM VOTACAO (" + proximoLocal + ")");

        if (votosFavoraveis >= chao) {
            this.quantidadeDeAprovacoes++;
            result = true;
            atualizaTramitacaoLei("APROVADO (" + this.localDeVotacao + ")");
        } else {
            atualizaTramitacaoLei("REJEITADO (" + this.localDeVotacao + ")");
        }
        this.localDeVotacao = proximoLocal;
        this.passouNaCCJC = true;
        this.quantidadeDeComissoes++;
        return result;
    }
}
