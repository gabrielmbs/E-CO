package entidades;

import comparators.ComparadorPropostaCodigo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a estratégia de busca por constitucional, ou seja, dará preferencia a primeiro PECs, depois PLPs
 * depois PLs.
 */
public class EstrategiaConstitucional implements EstrategiaDesempate, Serializable {

    /**
     * Método responsável por pegar uma determinada proposição (ainda em tramitação) utilizando como critério de busca o
     * tipo da proposição, seguindo a seguinte precedência (PEC > PLP > PL). Em caso de haver mais de uma proposição do
     * mesmo tipo, é retornada a proposição mais antiga primeiro considerando o ano e em caso de empate nos anos de
     * cadastro, considera-se o número de cadastro. O método recebe como parâmetro uma lista com todas as proposições
     * empatadas em número de interesses em comum com uma determinada pessoa e retorna a proposição mais relevante para
     * pessoa.
     *
     * @param maioresPropostas lista com as proposições empatadas em número de interesses.
     * @return a proposição mais relevante para uam determinada pessoa.
     */
    @Override
    public String pegarPropostaRelacionada(List<PropostaAbstract> maioresPropostas) {

        List<PropostaAbstract> listaPECs = new ArrayList<>();
        List<PropostaAbstract> listaPLPs = new ArrayList<>();
        List<PropostaAbstract> listaPLs = new ArrayList<>();
        for (PropostaAbstract proposta : maioresPropostas) {
            if ("PEC".equals(proposta.getTipoDeProposicao())) {
                listaPECs.add(proposta);
            } else if ("PLP".equals(proposta.getTipoDeProposicao())) {
                listaPLPs.add(proposta);
            } else {
                listaPLs.add(proposta);
            }
        }

        String result = "";
        if (listaPECs.size() >= 1) {
            result = verificaProposicaoRelevante(listaPECs);
        } else if (listaPLPs.size() >= 1) {
            result = verificaProposicaoRelevante(listaPLPs);
        } else if (listaPLs.size() >= 1) {
            result = verificaProposicaoRelevante(listaPLs);
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
