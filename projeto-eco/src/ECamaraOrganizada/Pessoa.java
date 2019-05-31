package ECamaraOrganizada;

import java.util.Objects;

public class Pessoa {
    private String nome;
    private String dni;
    private String estado;
    private String interesses;
    private String partido;
    private Funcao funcao;

    public Pessoa(String nome, String dni, String estado, String interesses) {
        this.nome = nome;
        this.dni = dni;
        this.estado = estado;
        this.interesses = interesses;
    }

    public Pessoa(String nome, String dni, String estado, String interesses, String partido){
        this(nome, dni, estado, interesses);
        this.partido = partido;
    }

    public void viraDeputado(String dataInicio){
        this.funcao = new Deputado(dataInicio);
    }
    
    @Override
    public String toString() {
        if(this.funcao == null){
            if( ( !"".equals(this.interesses) ) && (this.partido != null)) {
                return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                        " - " + "Interesses: " + this.interesses;
            } else if( (this.partido != null) && ("".equals(this.interesses)) ) {
                return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido;
            } else if( (this.partido == null) && (!"".equals(this.interesses)) ){
                return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + "Interesses: " + this.interesses;
            }
            return this.nome + " - " + this.dni + " (" + this.estado + ")";
        }else{
            if((!"".equals(this.interesses) ) && (this.partido != null)) {
                return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                        " - " + "Interesses: " + this.interesses + " - "  + this.funcao.exibirDeputado();
            } else if((this.partido != null) && ("".equals(this.interesses))) {
                return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido
                        + " - "  + this.funcao.exibirDeputado();
            } else if((this.partido == null) && (!"".equals(this.interesses))){
                return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + "Interesses: " + this.interesses
                        + " - " + this.funcao.exibirDeputado();
            }
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ")" + this.funcao.exibirDeputado();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(dni, pessoa.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getPartido() {
        return partido;
    }
}
