package comparators;

import entidades.PropostaAbstract;
import java.util.Comparator;

/**
 * Ordena uma lista de proposições com base no código da proposição.
 */
public class ComparadorPropostaCodigo implements Comparator<PropostaAbstract> {

    /**
     * Recebe como parâmetros duas proposições a serem comparadas e retorna um inteiro que indica
     * o resultado dessa comparação.
     *
     * @param proposicao proposição a ser comparada.
     * @param proposicao2 proposição a ser comparada.
     * @return int que indica o resultado da comparação.
     */
    @Override
    public int compare(PropostaAbstract proposicao, PropostaAbstract proposicao2) {
        String[] nCadastroAno1 = proposicao.getCodigoLei().split("/");
        String[] nCadastro1 = nCadastroAno1[0].split(" ");
        String ano1 = nCadastroAno1[1];
        String[] nCadastroAno2 = proposicao2.getCodigoLei().split("/");
        String[] nCadastro2 = nCadastroAno2[0].split(" ");
        String ano2 = nCadastroAno2[1];

        int result;
        if (ano1.compareTo(ano2) == 0) {
            result = nCadastro1[1].compareTo(nCadastro2[1]);
        } else {
            result = ano1.compareTo(ano2);
        }

        return result;
    }
}
