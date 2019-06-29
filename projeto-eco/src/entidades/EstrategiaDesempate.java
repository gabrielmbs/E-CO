package entidades;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que define os métodos que toda estratégia de busca de proposições do sistema deve ter.
 */
public interface EstrategiaDesempate extends Serializable {
    /**
     * Método responsável por pegar uma determinada proposição (ainda em tramitação) com base no critério
     * utilizado pela classe que implementar a interface. O método recebe uma lista com todas as proposições
     * empatadas em número de interesses em comum com uma determinada pessoa e retorna a proposição mais relevante
     * para pessoa.
     *
     * @param maioresPropostas lista de prposições empatadas em número de interesses.
     * @return proposição mais relevante.
     */
    public String pegarPropostaRelacionada(List<PropostaAbstract> maioresPropostas);
}
