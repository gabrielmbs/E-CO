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

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dni='" + dni + '\'' +
                ", estado='" + estado + '\'' +
                ", interesses='" + interesses + '\'' +
                ", partido='" + partido + '\'' +
                '}';
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
