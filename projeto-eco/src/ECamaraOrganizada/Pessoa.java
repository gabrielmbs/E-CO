package ECamaraOrganizada;

import java.util.Objects;

public class Pessoa {
    protected String nome;
    protected String dni;
    protected String estado;
    protected String interesses;
    protected String partido;

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

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getInteresses() {
        return interesses;
    }

    public String getPartido() {
        return partido;
    }

    //Formato: Nome - DNI (Estado) [ - PARTIDO ] [ - Interesses ]
    //Exemplo: Matheus G. - 123456789-0 (PB) - Interesses: ensino superior, cooperativismo
    @Override
    public String toString() {
        if( ( !"".equals(this.interesses) ) && (this.partido != null)) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses;
        } else if( (this.partido != null) && ("".equals(this.interesses)) ) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido;
        } else if( (this.partido == null) && (!"".equals(this.interesses)) ){
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + "Interesses: " + this.interesses;
        }
        return this.nome + " - " + this.dni + " (" + this.estado + ")";

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
}
