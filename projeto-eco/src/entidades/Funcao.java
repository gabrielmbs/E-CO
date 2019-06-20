package entidades;

public interface Funcao {
    public String exibir(String representacao);
    public void incrementaNumeroDeLeis();
    public int votoPolitico(String statusGovernista, boolean ehDaBase, boolean temInteressesEmComum);
}
