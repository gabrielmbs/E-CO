package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaAprovacao implements EstrategiaDesempate {
    @Override
    public String pegarPropostaRelacionada(List<ProposicaoAbstract> maioresPropostas) {

        List<ProposicaoAbstract> listaMaisAprovadas = new ArrayList<>();
        int aux = 0;
        for (ProposicaoAbstract proposicao : maioresPropostas) {
            if (proposicao.getQuantiadeDeAprovacoes() > aux) {
                aux = proposicao.getQuantiadeDeAprovacoes();
                listaMaisAprovadas.clear();
                listaMaisAprovadas.add(proposicao);
            } else if (proposicao.getQuantiadeDeAprovacoes() == aux) {
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
