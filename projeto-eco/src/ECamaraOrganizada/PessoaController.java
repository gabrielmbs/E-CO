package ECamaraOrganizada;

import util.Autenticador;

import java.awt.dnd.DnDConstants;
import java.io.DataInput;
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
        Autenticador.validaData(dataDeInicio);

        Pessoa pessoa = this.pessoas.get(DNI);
        this.deputados.put(DNI, new Deputado(pessoa.getNome(), DNI, pessoa.getEstado(), pessoa.getInteresses(),
                            pessoa.getPartido(), dataDeInicio));
    }


    public String exibirPessoa(String dni) {
        Autenticador.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
        Autenticador.validaDNI(dni, "Erro ao exibir pessoa: dni invalido");
        if (!this.pessoas.containsKey(dni)) {
            throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
        }
        if (this.deputados.containsKey(dni)) {
            return this.deputados.get(dni).toString();
        }
        return this.pessoas.get(dni).toString();

    }
}
