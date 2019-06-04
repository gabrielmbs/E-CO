package ECamaraOrganizada;

import util.Autenticador;

import java.util.Objects;

public class Pessoa {
    private String nome;
    private String dni;
    private String estado;
    private String interesses;
    private String partido;
    private Funcao funcao;

    public Pessoa(String nome, String dni, String estado, String interesses) {
        Autenticador.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaDNI(dni, "Erro ao cadastrar pessoa: dni invalido");
        Autenticador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Autenticador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");

        this.nome = nome;
        this.dni = dni;
        this.estado = estado;
        this.interesses = interesses;
    }

    public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
        this(nome, dni, estado, interesses);

        Autenticador.validaString(partido, "Erro ao cadastrar pessoa: partido nao pode ser vazio ou nulo");
        this.partido = partido;
    }

    public void viraDeputado(String dataInicio) {
        Autenticador.validaString(dataInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        Autenticador.validaDataInvalida(dataInicio, "Erro ao cadastrar deputado: data invalida");
        Autenticador.validaDataFutura(dataInicio, "Erro ao cadastrar deputado: data futura");

        this.funcao = new Deputado(dataInicio);
    }

    @Override
    public String toString() {
        if (this.funcao == null) {
            return exibePessoa();
        } else {
            return exibeDeputado();
        }
    }

    private String exibeDeputado() {
        if ((!"".equals(this.interesses.trim()))) {
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses + " - " + this.funcao.exibirDeputado();
        }else{
            return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido
                    + " - " + this.funcao.exibirDeputado();
        }
    }

    private String exibePessoa() {
        if ((!"".equals(this.interesses.trim())) && (this.partido != null)) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido +
                    " - " + "Interesses: " + this.interesses;
        } else if ((this.partido != null) && ("".equals(this.interesses.trim()))) {
            return this.nome + " - " + this.dni + " (" + this.estado + ") " + "- " + this.partido;
        } else if ((this.partido == null) && (!"".equals(this.interesses.trim()))) {
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

    public String getPartido() {
        return partido;
    }
}
