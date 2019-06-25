package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaConclusao implements EstrategiaDesempate {

    List<ProposicaoAbstract> listaTurno2 = new ArrayList<>();
    List<ProposicaoAbstract> listaTurno1 = new ArrayList<>();
    List<ProposicaoAbstract> listaPassouEmMaisComissoes = new ArrayList<>();
    @Override
    public String pegarPropostaRelacionada(List<ProposicaoAbstract> maioresPropostas) {
        int aux = 0;
        for (ProposicaoAbstract proposta : maioresPropostas) {
            if (proposta.getSituacao().contains("2o turno")) {
                listaTurno2.add(proposta);
            } else if (proposta.getSituacao().contains("1o turno")) {
                listaTurno1.add(proposta);
            } else if (!proposta.getSituacao().equals("EM VOTACAO")) {
                if (proposta.getQuantidadeDeComissoes() > aux) {
                    aux =proposta.getQuantidadeDeComissoes();
                    listaPassouEmMaisComissoes.clear();
                    listaPassouEmMaisComissoes.add(proposta);
                } else if (proposta.getQuantidadeDeComissoes() == aux) {
                    listaPassouEmMaisComissoes.add(proposta);
                }
            }
        }

        String result = "";
        if (listaTurno2.size() >= 1) {
            if (listaTurno2.size() == 1) {
                result = listaTurno2.get(0).getCodigoLei();
            } else {
                Collections.sort(listaTurno2, new ComparadorPropostaCodigo());
                result = listaTurno2.get(0).getCodigoLei();
            }
        } else if (listaTurno1.size() >= 1) {
            if (listaTurno1.size() == 1) {
                result = listaTurno1.get(0).getCodigoLei();
            } else {
                Collections.sort(listaTurno1, new ComparadorPropostaCodigo());
                result = listaTurno1.get(0).getCodigoLei();
            }
        } else {
            if (listaPassouEmMaisComissoes.size() == 1) {
                result = listaPassouEmMaisComissoes.get(0).getCodigoLei();
            } else {
                Collections.sort(listaPassouEmMaisComissoes, new ComparadorPropostaCodigo());
                result = listaPassouEmMaisComissoes.get(0).getCodigoLei();
            }
        }
        return result;
    }
}
