package comparators;

import entidades.ProposicaoAbstract;

import java.util.Comparator;

public class ComparadorPropostaCodigo implements Comparator<ProposicaoAbstract> {

    @Override
    public int compare(ProposicaoAbstract proposicaoAbstract, ProposicaoAbstract t1) {
        String[] nCadastroAno1 = proposicaoAbstract.getCodigoLei().split("/");
        String[] nCadastro1 = nCadastroAno1[0].split(" ");
        String ano1 = nCadastroAno1[1];
        String[] nCadastroAno2 = t1.getCodigoLei().split("/");
        String[] nCadastro2 = nCadastroAno2[0].split(" ");
        String ano2 = nCadastroAno2[1];

        int result;
        if(ano1.compareTo(ano2) == 0){
            result = nCadastro1[1].compareTo(nCadastro2[1]);
        }else{
            result = ano1.compareTo(ano2);
        }

        return result;
    }
}
