package util;

public class Contador {
    private Integer contagem;


    public Contador() {
        this.contagem = 1;

    }

    public Integer incrementaContagem(){
        return this.contagem++;
    }

    public Integer getContagem() {
        return contagem;
    }


}
