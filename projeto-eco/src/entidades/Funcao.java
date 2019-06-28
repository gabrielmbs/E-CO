package entidades;

import java.io.Serializable;

public interface Funcao extends Serializable {
    public String exibir(String representacao);

    public void incrementaNumeroDeLeis();

    public boolean votoPolitico(String statusGovernista, boolean ehDaBase, boolean temInteressesEmComum);
}
