package ECamaraOrganizada;

import easyaccept.EasyAccept;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"ECamaraOrganizada.Facade", "easyaccept_tests/use_case_1.txt",
                            "easyaccept_tests/use_case_2.txt", "easyaccept_tests/use_case_3.txt",
                            "easyaccept_tests/use_case_4.txt", "easyaccept_tests/use_case_5.txt",
                            "easyaccept_tests/use_case_6.txt"};
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

    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo){
        return this.camaraController.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
    }

    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos){
        return this.camaraController.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
    }

    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos){
        return this.camaraController.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
    }

    public String exibirProjeto(String codigo){
        return this.camaraController.exibirProjeto(codigo);
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

