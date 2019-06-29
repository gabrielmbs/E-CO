package entidades;

import comparators.ComparadorPropostaCodigo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa uma estratégia de busca que pode ser utilizada na pesquisa de uma determinada proposição.
 */
public class EstrategiaAprovacao implements EstrategiaDesempate {

    /**
     * Método responsável por pegar uma determinada proposição (ainda em tramitação) utilizando como critério de busca o
     * número de aprovações da proposição, a proposição com maior número de aprovações é retornada, em caso de empate é
     * retornada a proposição mais antiga primeiro considerando o ano e em caso de empate nos anos de cadastro,
     * considera-se o número de cadastro. O método recebe como parâmetro uma lista com todas as proposições empatadas em
     * número de interesses em comum com uma determinada pessoa e retorna a proposição mais relevante para pessoa.
     *
     * @param maioresPropostas lista com as proposições empatadas em número de interesses.
     * @return a proposição mais relevante para uam determinada pessoa.
     */
    @Override
    public String pegarPropostaRelacionada(List<PropostaAbstract> maioresPropostas) {

        List<PropostaAbstract> listaMaisAprovadas = new ArrayList<>();
        int aux = 0;
        for (PropostaAbstract proposicao : maioresPropostas) {
            if (proposicao.getQuantidadeDeAprovacoes() > aux) {
                aux = proposicao.getQuantidadeDeAprovacoes();
                listaMaisAprovadas.clear();
                listaMaisAprovadas.add(proposicao);
            } else if (proposicao.getQuantidadeDeAprovacoes() == aux) {
                listaMaisAprovadas.add(proposicao);
            }
        }

        String result = "";
        if (listaMaisAprovadas.size() >= 1) {
            if (listaMaisAprovadas.size() == 1) {
                result = listaMaisAprovadas.get(0).getCodigoLei();
            } else {
                Collections.sort(listaMaisAprovadas, new ComparadorPropostaCodigo());
                result = listaMaisAprovadas.get(0).getCodigoLei();
            }
        }
        return result;
    }
}
