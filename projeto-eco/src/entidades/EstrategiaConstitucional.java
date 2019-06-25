package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaConstitucional implements EstrategiaDesempate, Serializable {

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
            result = verificaProposicaoRelevante(listaPECs);
        } else if (listaPLPs.size() >= 1) {
            result = verificaProposicaoRelevante(listaPLPs);
        } else if (listaPLs.size() >= 1) {
            result = verificaProposicaoRelevante(listaPLs);
        }
        return result;
    }

    private String verificaProposicaoRelevante(List<ProposicaoAbstract> proposicoes) {
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
