package ECamaraOrganizada;

import util.Autenticador;

import java.util.HashMap;
import java.util.Map;

public class PessoaController {
    private Map<String, Pessoa> pessoas;
    private Map<String, Deputado> deputados;

    public PessoaController(){
        this.pessoas = new HashMap<>();
        this.deputados = new HashMap<>();
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        Autenticador.validaStringNula(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");

        Autenticador.validaStringNula(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");

        Autenticador.validaStringNula(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");

        Autenticador.validaDNI(dni);
        if(this.pessoas.containsKey(dni)){
            throw new NullPointerException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses));
        return true;
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        Autenticador.validaStringNula(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");

        Autenticador.validaStringNula(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");

        Autenticador.validaStringNula(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        Autenticador.validaStringVazia(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");

        Autenticador.validaDNI(dni);
        if(this.pessoas.containsKey(dni)){
            throw new NullPointerException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
        return true;
    }

    public void cadastrarDeputado(String DNI, String dataDeInicio){

    }

    public String exibirPessoa(String DNI){
        return "";
    }
}
