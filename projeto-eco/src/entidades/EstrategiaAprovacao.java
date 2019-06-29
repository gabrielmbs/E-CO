package entidades;

import comparators.ComparadorPropostaCodigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a estratégia de busca por conclusão, ou seja, dará preferencia as propostas com maior aprovação
 * na sua tramitação.
 */
public class EstrategiaAprovacao implements EstrategiaDesempate {
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
