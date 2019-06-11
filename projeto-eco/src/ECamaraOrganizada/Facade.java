package ECamaraOrganizada;

import easyaccept.EasyAccept;

import java.util.List;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"ECamaraOrganizada.Facade", "easyaccept_tests/use_case_1.txt",
                            "easyaccept_tests/use_case_2.txt", "easyaccept_tests/use_case_3.txt",
                            "easyaccept_tests/use_case_4.txt", "easyaccept_tests/use_case_5.txt"};
        EasyAccept.main(args);
    }

    private CamaraController camaraController;

    public Facade(){
        this.camaraController = new CamaraController();
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        return this.camaraController.cadastrarPessoa(nome, dni, estado, interesses);
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        return this.camaraController.cadastrarPessoa(nome, dni, estado, interesses, partido);

    }

    public void cadastrarDeputado(String DNI, String dataDeInicio){
        this.camaraController.cadastrarDeputado(DNI, dataDeInicio);
    }

    public String exibirPessoa(String DNI){
        return this.camaraController.exibirPessoa(DNI);
    }

    public void cadastrarPartido(String partido){
        this.camaraController.cadastrarPartido(partido);
    }

    public String exibirBase(){
        return this.camaraController.exibirBase();
    }

    public void cadastrarComissao(String tema, String politicos){
        this.camaraController.cadastrarComissao(tema, politicos);
    }

    public void limparSistema(){

    }

    public void salvarSistema(){

    }

    public void carregarSistema(){

    }
}

