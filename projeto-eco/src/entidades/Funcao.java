package entidades;

public interface Funcao {
    public String exibir(String representacao);
    public void incrementaNumeroDeLeis();
    public boolean votoPolitico(String statusGovernista, boolean ehDaBase, boolean temInteressesEmComum);
}
