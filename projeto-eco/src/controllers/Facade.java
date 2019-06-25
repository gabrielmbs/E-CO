package controllers;

import easyaccept.EasyAccept;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"controllers.Facade",
                "easyaccept_tests/use_case_1.txt","easyaccept_tests/use_case_2.txt",
                "easyaccept_tests/use_case_3.txt","easyaccept_tests/use_case_4.txt",
                "easyaccept_tests/use_case_5.txt","easyaccept_tests/use_case_6.txt", "easyaccept_tests/use_case_7.txt",
                "easyaccept_tests/use_case_8.txt", "easyaccept_tests/use_case_9.txt"};
        EasyAccept.main(args);
    }

    private ControllerGeral controllerGeral;

    public Facade(){
        this.controllerGeral = new ControllerGeral();
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        return this.controllerGeral.cadastrarPessoa(nome, dni, estado, interesses);
    }

    public boolean cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        return this.controllerGeral.cadastrarPessoa(nome, dni, estado, interesses, partido);

    }

    public void cadastrarDeputado(String DNI, String dataDeInicio){
        this.controllerGeral.cadastrarDeputado(DNI, dataDeInicio);
    }

    public String exibirPessoa(String DNI){
        return this.controllerGeral.exibirPessoa(DNI);
    }

    public void cadastrarPartido(String partido){
        this.controllerGeral.cadastrarPartido(partido);
    }

    public String exibirBase(){
        return this.controllerGeral.exibirBase();
    }

    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo){
        return this.controllerGeral.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
    }

    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos){
        return this.controllerGeral.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
    }

    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos){
        return this.controllerGeral.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
    }

    public String exibirProjeto(String codigo){
        return this.controllerGeral.exibirProjeto(codigo);
    }

    public void cadastrarComissao(String tema, String politicos){
        this.controllerGeral.cadastrarComissao(tema, politicos);
    }

    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        return this.controllerGeral.votarComissao(codigo, statusGovernista, proximoLocal);
    }

    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        return this.controllerGeral.votarPlenario(codigo, statusGovernista, presentes);
    }

    public String exibirTramitacao(String codigo) {
        return this.controllerGeral.exibirTramitacao(codigo);
    }

    public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia){
        this.controllerGeral.configurarEstrategiaPropostaRelacionada(dni,estrategia);
    }

    public String pegarPropostaRelacionada(String dni){
        return this.controllerGeral.pegarPropostaRelacionada(dni);
    }

    public void limparSistema(){
        this.controllerGeral.limparSistema();
    }

    public void salvarSistema(){
        this.controllerGeral.salvarSistema();
    }

    public void carregarSistema(){
        this.controllerGeral.carregarSistema();
    }
}

