package entidades;

import comparators.ComparadorPropostaCodigo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa uma estratégia de busca que pode ser utilizada na pesquisa de uma determinada proposição.
 */
public class EstrategiaConclusao implements EstrategiaDesempate {

    /**
     * Método responsável por pegar uma determinada proposição (ainda em tramitação) utilizando como critério de busca o
     * quão próxima do final da tramitação está a proposição, seguindo a seguinte precedência (Plenário (2º turno) >
     * Plenário (1º turno)), caso a proposição não esteja no plenário é considerada a que tiver passado por mais comisseõs,
     * em caso de empate é retornada a proposição mais antiga primeiro considerando o ano e em caso de empate nos anos de
     * cadastro, considera-se o número de cadastro. O método recebe como parâmetro uma lista com todas as proposições
     * empatadas em número de interesses em comum com uma determinada pessoa e retorna a proposição mais relevante para
     * pessoa.
     *
     * @param maioresPropostas lista com as proposições empatadas em número de interesses.
     * @return a proposição mais relevante para uam determinada pessoa.
     */
    @Override
    public String pegarPropostaRelacionada(List<PropostaAbstract> maioresPropostas) {
        List<PropostaAbstract> listaTurno2 = new ArrayList<>();
        List<PropostaAbstract> listaTurno1 = new ArrayList<>();
        List<PropostaAbstract> listaPassouEmMaisComissoes = new ArrayList<>();
        int aux = 0;
        for (PropostaAbstract proposta : maioresPropostas) {
            if (proposta.getSituacao().contains("2o turno")) {
                listaTurno2.add(proposta);
            } else if (proposta.getSituacao().contains("1o turno")) {
                listaTurno1.add(proposta);
            } else {
                if (proposta.getQuantidadeDeComissoes() > aux) {
                    aux = proposta.getQuantidadeDeComissoes();
                    listaPassouEmMaisComissoes.clear();
                    listaPassouEmMaisComissoes.add(proposta);
                } else if (proposta.getQuantidadeDeComissoes() == aux) {
                    listaPassouEmMaisComissoes.add(proposta);
                }
            }
        }

        String result = "";
        if (listaTurno2.size() >= 1) {
            result = verificaProposicaoRelevante(listaTurno2);
        } else if (listaTurno1.size() >= 1) {
            result = verificaProposicaoRelevante(listaTurno1);
        } else {
            result = verificaProposicaoRelevante(listaPassouEmMaisComissoes);
        }
        return result;
    }

    /**
     * Método auxiliar utilizado para retornar a proposição mais relevante após a filtragem pelo critério de busca
     * definido pela pessoa. Retorna a proposição mais relevante para pessoa. Este método recebe como parâmetro uma lista
     * com as porposições já filtradas pelo critério de busca definido pela pessoa.
     *
     * @param proposicoes lista de proposições filtradas pelo critério.
     * @return proposição mais relevante para pessoa.
     */
    private String verificaProposicaoRelevante(List<PropostaAbstract> proposicoes) {
        String result = "";
        if (proposicoes.size() == 1) {
            result = proposicoes.get(0).getCodigoLei();
        } else {
            Collections.sort(proposicoes, new ComparadorPropostaCodigo());
            result = proposicoes.get(0).getCodigoLei();
        }
        return result;
    }
}
