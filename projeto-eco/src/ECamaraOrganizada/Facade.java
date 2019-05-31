package ECamaraOrganizada;

import easyaccept.EasyAccept;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"ECamaraOrganizada.Facade", "easyaccept_tests/use_case_1.txt",
                            "easyaccept_tests/use_case_2.txt", "easyaccept_tests/use_case_3.txt",
                            "easyaccept_tests/use_case_4.txt"};
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
        this.pessoaController.cadastrarDeputado(DNI, dataDeInicio);
    }

    public String exibirPessoa(String DNI){
        return this.pessoaController.exibirPessoa(DNI);
    }

    public void cadastrarPartido(String partido){
        this.base.cadastrarPartido(partido);
    }

    public String exibirBase(){
        return this.base.exibirBase();
    }

    public void limparSistema(){

    }

    public void salvarSistema(){

    }

    public void carregarSistema(){

    }
}

