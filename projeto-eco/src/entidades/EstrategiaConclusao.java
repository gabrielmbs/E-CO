package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaConclusao implements EstrategiaDesempate {
    
    @Override
    public String pegarPropostaRelacionada(List<ProposicaoAbstract> maioresPropostas) {
        List<ProposicaoAbstract> listaTurno2 = new ArrayList<>();
        List<ProposicaoAbstract> listaTurno1 = new ArrayList<>();
        List<ProposicaoAbstract> listaPassouEmMaisComissoes = new ArrayList<>();
        int aux = 0;
        for (ProposicaoAbstract proposta : maioresPropostas) {
            if (proposta.getSituacao().contains("2o turno")) {
                listaTurno2.add(proposta);
            } else if (proposta.getSituacao().contains("1o turno")) {
                listaTurno1.add(proposta);
            } else {
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
            result = verificaProposicaoRelevante(listaTurno2);
        } else if (listaTurno1.size() >= 1) {
            result = verificaProposicaoRelevante(listaTurno1);
        } else {
            result = verificaProposicaoRelevante(listaPassouEmMaisComissoes);
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
