package entidades;

import java.util.List;

public interface EstrategiaDesempate {
    public String pegarPropostaRelacionada(List<ProposicaoAbstract> maioresPropostas);
}
