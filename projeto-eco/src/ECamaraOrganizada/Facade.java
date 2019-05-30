package ECamaraOrganizada;

import easyaccept.EasyAccept;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"ECamaraOrganizada.Facade", "easyaccept_tests/use_case_1.txt"};
        EasyAccept.main(args);
    }

    private PessoaController pessoaController;
    private Base base;

    public Facade(){
        this.pessoaController = new PessoaController();
        this.base = new Base();
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        return this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        return this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);

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
