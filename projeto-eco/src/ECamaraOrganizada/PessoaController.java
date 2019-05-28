package ECamaraOrganizada;

import java.util.HashMap;
import java.util.Map;

public class PessoaController {
    private Map<String, Pessoa> pessoas;
    private Map<String, Deputado> deputados;

    public PessoaController(){
        this.pessoas = new HashMap<>();
        this.deputados = new HashMap<>();
    }

    public void cadastrarPessoa(String nome, String dni, String estado, String interesses){

    }

    public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido){

    }

    public void cadastrarDeputado(String DNI, String dataDeInicio){

    }

    public String exibirPessoa(String DNI){
        return "";
    }
}
