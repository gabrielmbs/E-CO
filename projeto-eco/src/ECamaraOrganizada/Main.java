package ECamaraOrganizada;

public class Main {
    public static void main(String[] args) {
        CamaraController camaraController = new CamaraController();

        camaraController.cadastrarPessoa("Eric", "000000000-0", "RN", "esporte", "Anarquista");
        camaraController.cadastrarDeputado("000000000-0", "05062019");

        System.out.println(camaraController.exibirPessoa("000000000-0"));

        camaraController.cadastraPEC("000000000-0", 2019, "Muda CTB", "Trânsito", "www.mudactb.com", "CTB - 01");
        camaraController.cadastraPEC("000000000-0", 2019, "Muda sla", "Trânsito", "www.mudactb.com", "CTB - 01");
        System.out.println(camaraController.exibeProjeto("PEC 1/2019"));
        System.out.println(camaraController.exibeProjeto("PEC 2/2019"));
        System.out.println(camaraController.exibirPessoa("000000000-0"));

        camaraController.cadastraPL("000000000-0", 2019, "Muda CTB", "Trânsito", "www.mudactb.com", "Conclusivo");
        camaraController.cadastraPL("000000000-0", 2019, "Muda sla", "Trânsito", "www.mudactb.com", "Conclusivo");

        System.out.println(camaraController.exibeProjeto("PL 1/2019"));
        System.out.println(camaraController.exibeProjeto("PL 2/2019"));
        System.out.println(camaraController.exibirPessoa("000000000-0"));

        camaraController.cadastraPLP("000000000-0", 2019, "Muda CTB", "Trânsito", "www.mudactb.com", "CTB - 01");
        camaraController.cadastraPLP("000000000-0", 2019, "Muda sla", "Trânsito", "www.mudactb.com", "CTB - 01");

        camaraController.cadastraPLP("000000000-0", 2020, "Muda CTB", "Trânsito", "www.mudactb.com", "CTB - 01");
        System.out.println(camaraController.exibeProjeto("PLP 1/2019"));
        System.out.println(camaraController.exibeProjeto("PLP 2/2019"));
        System.out.println(camaraController.exibeProjeto("PLP 1/2020"));
        System.out.println(camaraController.exibirPessoa("000000000-0"));



    }
}
