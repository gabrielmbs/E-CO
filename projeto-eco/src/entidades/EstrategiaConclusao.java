package entidades;

import comparators.ComparadorPropostaCodigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaConclusao implements EstrategiaDesempate {

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
