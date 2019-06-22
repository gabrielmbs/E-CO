package entidades;

import java.util.Comparator;

public class ComparaPropostaPorAprovacoes implements Comparator<ProposicaoAbstract> {
    @Override
    public int compare(ProposicaoAbstract o1, ProposicaoAbstract o2) {
        return o1.getQuantiadeDeAprovacoes().compareTo(o2.getQuantiadeDeAprovacoes());
    }
}
