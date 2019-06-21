package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaConstitucional implements EstrategiaDesempate {

    @Override
    public String pegarPropostaRelacionada(List<ProposicaoAbstract> maioresPropostas) {

        List<ProposicaoAbstract> listaPECs = new ArrayList<>();
        List<ProposicaoAbstract> listaPLPs = new ArrayList<>();
        List<ProposicaoAbstract> listaPLs = new ArrayList<>();
        for (ProposicaoAbstract proposta : maioresPropostas) {
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
            if (listaPECs.size() == 1) {
                result = null;
            } else {
                Collections.sort(listaPECs, new ComparadorPropostaCodigo());
                result = null;
            }

        } else if (listaPLPs.size() >= 1) {
            if (listaPLPs.size() == 1) {
                result = null;
            } else {
                Collections.sort(listaPLPs, new ComparadorPropostaCodigo());
                result = null;
            }
        } else if (listaPLs.size() >= 1) {
            if (listaPLs.size() == 1) {
                result = null;
            } else {
                Collections.sort(listaPLs, new ComparadorPropostaCodigo());
                result = null;
            }
        }

        return result;
    }
}
