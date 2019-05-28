package ECamaraOrganizada;

public class Facade {
    private PessoaController pessoaController;
    private Base base;

    public Facade(){
        this.pessoaController = new PessoaController();
        this.base = new Base();
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

    public void cadastrarPartido(String partido){

    }

    public String exibirBase(){
        return "";
    }
}
