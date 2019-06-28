package entidades;

import java.io.Serializable;
import java.util.List;

public interface EstrategiaDesempate extends Serializable {
    public String pegarPropostaRelacionada(List<PropostaAbstract> maioresPropostas);
}
