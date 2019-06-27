package comparators;

import entidades.ProposicaoAbstract;

import java.util.Comparator;

public class ComparaPropostaPorVotacoes implements Comparator<ProposicaoAbstract> {
    @Override
    public int compare(ProposicaoAbstract o1, ProposicaoAbstract o2) {
        return o1.getQuantidadeDeComissoes().compareTo(o2.getQuantidadeDeComissoes());
    }
}
