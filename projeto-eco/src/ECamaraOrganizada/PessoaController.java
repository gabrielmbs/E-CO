package ECamaraOrganizada;

import util.Autenticador;

import java.util.HashMap;
import java.util.Map;

public class PessoaController {
    private Map<String, Pessoa> pessoas;;

    public PessoaController(){
        this.pessoas = new HashMap<>();
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        validaCadastrarPessoa(nome, dni, estado);
        validaDni(dni);

        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses));
        return true;
    }

    private void validaDni(String dni) {
        if (this.pessoas.containsKey(dni)) {
            throw new NullPointerException("Erro ao cadastrar pessoa: dni ja cadastrado");
        }
    }

    private void validaCadastrarPessoa(String nome, String dni, String estado) {
        Autenticador.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        Autenticador.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        Autenticador.validaDNI(dni, "Erro ao cadastrar pessoa: dni invalido");
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        validaCadastrarPessoa(nome, dni, estado);
        validaDni(dni);

        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
        return true;
    }

    public void cadastrarDeputado(String DNI, String dataDeInicio) {
        Autenticador.validaString(DNI, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaDNI(DNI, "Erro ao cadastrar deputado: dni invalido");
        if (!this.pessoas.containsKey(DNI)) {
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa nao encontrada");
        }
        if(this.pessoas.get(DNI).getPartido() == null){
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
        }
        Autenticador.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
        Autenticador.validaDataInvalida(dataDeInicio, "Erro ao cadastrar deputado: data invalida");
        Autenticador.validaDataFutura(dataDeInicio, "Erro ao cadastrar deputado: data futura");

        Pessoa pessoa = this.pessoas.get(DNI);
        pessoa.viraDeputado(dataDeInicio);
    }


    public String exibirPessoa(String dni) {
        Autenticador.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaDNI(dni, "Erro ao exibir pessoa: dni invalido");
        if (!this.pessoas.containsKey(dni)) {
            throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
        }
        return this.pessoas.get(dni).toString();

    }
}
