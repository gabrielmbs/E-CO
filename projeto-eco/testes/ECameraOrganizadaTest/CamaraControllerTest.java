package ECameraOrganizadaTest;

import controllers.ControllerGeral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CamaraControllerTest {

    private ControllerGeral camaraController;

    private ControllerGeral camaraController2;

    private ControllerGeral camaraController3;

    @BeforeEach
    void criaPessoaController() {
        this.camaraController = new ControllerGeral();
        this.camaraController2 = new ControllerGeral();
        this.camaraController3 = new ControllerGeral();

        // sem interesse e sem partido
        this.camaraController.cadastrarPessoa("Jonas", "12345678-9", "PB", "");
        // com interesse e sem partido
        this.camaraController.cadastrarPessoa("Jonas", "12345678-0", "PB", "educação");
        // sem interesse e com partido
        this.camaraController.cadastrarPessoa("Jonas", "12345678-1", "PB", "", "PT");
        // com interesse e com partido
        this.camaraController.cadastrarPessoa("Jonas", "12345678-6", "PB", "educação", "PT");

        this.camaraController.cadastrarPartido("PMDB");
        this.camaraController.cadastrarPartido("PSDB");
        this.camaraController.cadastrarPartido("PT");
        this.camaraController.cadastrarPartido("DEM");
        this.camaraController.cadastrarPartido("PCO");
        this.camaraController.cadastrarPartido("PCB");
        this.camaraController.cadastrarPartido("NOVO");

        this.camaraController3.cadastrarPessoa("P1", "071111111-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        this.camaraController3.cadastrarPessoa("P2", "071222222-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        this.camaraController3.cadastrarPessoa("P3", "071333333-0","PB","saude,seguranca publica,trabalho", "PartidoGov");
        this.camaraController3.cadastrarPessoa("P4","071444444-0", "PI","saude,seguranca publica,trabalho", "PartidoGov" );
        this.camaraController3.cadastrarPessoa("P5","071555555-0", "PI","nutricao","PartidoGov" );
        this.camaraController3.cadastrarPessoa("P6","071666666-0", "RO","educacao,seguranca publica,saude","PartidoOpo" );
        this.camaraController3.cadastrarPessoa("P7","071777777-0", "RO","educacao,seguranca publica,saude","PartidoOpo" );
        this.camaraController3.cadastrarPessoa("P8","071888888-0", "RO","saude,seguranca publica,trabalho","PartidoOpo" );
        this.camaraController3.cadastrarPessoa("P9","071999999-0", "RO","saude,seguranca publica,trabalho","PartidoOpo" );
        this.camaraController3.cadastrarPessoa("P10","071000000-0", "RO","nutricao","PartidoOpo" );




        this.camaraController3.cadastrarDeputado("071111111-0", "29022016");
        this.camaraController3.cadastrarDeputado("071222222-0", "29022016");
        this.camaraController3.cadastrarDeputado("071333333-0", "29022016");
        this.camaraController3.cadastrarDeputado("071444444-0", "29022016");
        this.camaraController3.cadastrarDeputado("071555555-0", "29022016");
        this.camaraController3.cadastrarDeputado("071666666-0", "29022016");
        this.camaraController3.cadastrarDeputado("071777777-0", "29022016");
        this.camaraController3.cadastrarDeputado("071888888-0", "29022016");
        this.camaraController3.cadastrarDeputado("071999999-0", "29022016");
        this.camaraController3.cadastrarDeputado("071000000-0", "29022016");

        this.camaraController3.cadastrarPL("071222222-0", 2016, "Ementa PL conc","saude,educacao basica", "http://example.com/semana_saude",true);
        this.camaraController3.cadastrarPL("071222222-0", 2016, "Ementa PL conc","saude,educacao basica", "http://example.com/semana_saude",true);
        this.camaraController3.cadastrarPL("071222222-0", 2016, "Ementa PL conc","saude,educacao basica", "http://example.com/semana_saude",true);
        this.camaraController3.cadastrarPL("071222222-0", 2016, "Ementa PL conc","saude", "http://example.com/semana_saude",true);
        this.camaraController3.cadastrarPL("071222222-0", 2016, "Ementa PL conc","nutricao", "http://example.com/semana_saude",true);
        this.camaraController3.cadastrarPL("071333333-0", 2016, "Ementa PLnconc","cidadania,educacao basica", "http://example.com/semana_cidadania",false);
        this.camaraController3.cadastrarPL("071333333-0", 2016, "Ementa PLnconc","cidadania,educacao basica", "http://example.com/semana_cidadania",false);


        this.camaraController3.cadastrarPLP("071222222-0", 2016, "Ementa PLP","fiscal,jogos", "https://example.net/jogos%40aposta", "153");
        this.camaraController3.cadastrarPLP("071222222-0", 2013, "Ementa PLP", "saude", "https://example.net/jogos%40aposta", "153");


        this.camaraController3.cadastrarPEC("071222222-0", 2016, "Ementa PEC","saude", "https://example.com/sindicato/algo.html", "7,8");
        this.camaraController3.cadastrarPEC("071222222-0", 2016, "Ementa PEC", "saude","https://example.com/sindicato/algo.html", "7,8" );


        this.camaraController3.cadastrarPartido("PartidoGov");

        //5 da base, 4 oposicao
        this.camaraController3.cadastrarComissao("CCJC", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0");
        //5 da base, 5 oposicao
        this.camaraController3.cadastrarComissao("CTF", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0");
        //5 da base
        this.camaraController3.cadastrarComissao("CGOV","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0");
        //5 da oposicao
        this.camaraController3.cadastrarComissao("COPO","071666666-0,071777777-0,071888888-0,071999999-0,071000000-0");

    }

    @Test
    void cadastrarPessoaNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastrarPessoaComPartidoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                null, "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                null, "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                " 12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                " 12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                ".12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                ".12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "A12346578", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "A12346578", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A123456789", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A123456789", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A412345678", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "A412345678", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                null, "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                null, "", "", "PT"));
    }

    @Test
    void cadastraPessoaDniJaCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniJaCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa(null,
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastrarPessoa() {
        this.camaraController.cadastrarPessoa("Juan", "98765432-1", "RJ", "");
    }

    @Test
    void cadastrarPessoaComInteresse() {
        this.camaraController.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação");
    }

    @Test
    void cadastrarPessoaComPartido() {
        this.camaraController.cadastrarPessoa("Juan", "98765432-1", "RJ", "", "PT");
    }

    @Test
    void cadastrarPessoaComPartidoComInteresse() {
        this.camaraController.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação",
                "PT");
    }

    @Test
    void testExibirPessoaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirPessoa(null));
    }

    @Test
    void testExibirPessoaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(" "));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(" 12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa("A12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(".%12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoSemTraco() {
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirPessoa("123456781"));
    }

    @Test
    void testExibirPessoaInexistente() {
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirPessoa("12345228-3"));
    }

    @Test
    void testExibirPessoaSemInteresseESemPartido() {
        String esperado = "Jonas - 12345678-9 (PB)";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-9"));
    }

    @Test
    void testExibirPessoaComInteresseESemPartido() {
        String esperado = "Jonas - 12345678-0 (PB) - Interesses: educação";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-0"));
    }

    @Test
    void testExibirPessoaSemInteresseEComPartido() {
        String esperado = "Jonas - 12345678-1 (PB) - PT";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirPessoaComInteresseEComPartido() {
        String esperado = "Jonas - 12345678-6 (PB) - PT - Interesses: educação";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-6"));
    }

    @Test
    void testExibirDeputadoSemInteresse() {
        this.camaraController.cadastrarDeputado("12345678-1", "20102018");
        String esperado = "POL: Jonas - 12345678-1 (PB) - PT - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirDeputadoComInteresse() {
        this.camaraController.cadastrarDeputado("12345678-6", "20102018");
        String esperado = "POL: Jonas - 12345678-6 (PB) - PT - Interesses: educação - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-6"));
    }

    @Test
    void cadastrarPartidoNullPointerTest() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPartido(null));
    }

    @Test
    void cadastrarPartidoIllegalArgumentTest() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPartido("  "));
    }

    @Test
    void exibirBaseTest() {
        String stringEsperada = "DEM,NOVO,PCB,PCO,PMDB,PSDB,PT";
        assertEquals(stringEsperada, this.camaraController.exibirBase());
    }

    @Test
    void exibirBaseVaziaTest() {
        assertEquals("", this.camaraController2.exibirBase());
    }

    @Test
    void cadastrarPLPDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP(null, 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPEmentaVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPEmentaNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPUrlVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "", "153"));
    }

    @Test
    void cadastrarPLPUrlNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", null, "153"));
    }

    @Test
    void cadastrarPLPInteressesVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPInteressesNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", null, "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComEspaco() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP(" 112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComLetra() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("A112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComCaractereEspecial() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP(".112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPAnoPosteriorAoAtual() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2021,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPAnoAnteriorA1988() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 1980,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPPessoaInexistente() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-5", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPArtigosVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", ""));
    }

    @Test
    void cadastrarPLPArtigosNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", null));
    }

    @Test
    void cadastrarPLPDadosValidos() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PLP 1/2016", this.camaraController.cadastrarPLP("12345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL(null, 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaEmentaVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaEmentaNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaUrlVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", "", true));
    }

    @Test
    void cadastrarPLConclusivaUrlNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", null, true));
    }

    @Test
    void cadastrarPLConclusivaInteressesVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaInteressesNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", null, "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComEspaco() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL(" 112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComLetra() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("A112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComCaractereEspecial() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL(".112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaAnoPosteriorAoAtual() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2021,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaAnoAnteriorA1988() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 1980,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaPessoaInexistente() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-5", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDadosValidos() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PL 1/2016", this.camaraController.cadastrarPL("12345678-6", 2016,
                "Ementa PL conc", "educacao", "http://example.com/semana_educacao", true));
    }

    @Test
    void cadastrarPECDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC(null, 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECEmentaVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECEmentaNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECUrlVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "", "153"));
    }

    @Test
    void cadastrarPECUrlNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", null, "153"));
    }

    @Test
    void cadastrarPECInteressesVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECInteressesNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", null, "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComEspaco() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC(" 112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComLetra() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("A112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComCaractereEspecial() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC(".112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECAnoPosteriorAoAtual() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2021,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECAnoAnteriorA1988() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 1980,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECPessoaInexistente() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-5", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECArtigosVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", ""));
    }

    @Test
    void cadastrarPECArtigosNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", null));
    }

    @Test
    void cadastrarPECDadosValidos() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PEC 1/2016", this.camaraController.cadastrarPEC("12345678-6", 2016,
                "Ementa PEC", "seguranca", "http://example.com/semana_seguranca", "153"));
    }

    @Test
    void cadastrarPLNaoConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL(null, 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaEmentaVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaEmentaNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaUrlVazia() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", "", false));
    }

    @Test
    void cadastrarPLNaoConclusivaUrlNula() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", null, false));
    }

    @Test
    void cadastrarPLNaoConclusivaInteressesVazio() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaInteressesNulo() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", null, "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComEspaco() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL(" 112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComLetra() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("A112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComCaractereEspecial() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL(".112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaAnoPosteriorAoAtual() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2021,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaAnoAnteriorA1988() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPL("112345678-6", 1980,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaPessoaInexistente() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-5", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDadosValidos() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PL 1/2016", this.camaraController.cadastrarPL("12345678-6", 2016,
                "Ementa PLnconc", "nutricao", "http://example.com/semana_nutricao", false));
    }

    @Test
    void exibirProjetoCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirProjeto(""));
    }

    @Test
    void exibirProjetoCodigoNulo() {
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirProjeto(null));
    }

    @Test
    void exibirProjetoInexistente() {
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirProjeto("PL 2/2015"));
    }

    @Test
    void exibirProjetoPLConclusiva() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        this.camaraController.cadastrarPL("12345678-6", 2016, "Ementa PL conc", "educacao",
                "http://example.com/semana_educacao", true);
        assertEquals("Projeto de Lei - PL 1/2016 - 12345678-6 - Ementa PL conc - Conclusiva - EM VOTACAO (CCJC)",
                this.camaraController.exibirProjeto("PL 1/2016"));
    }

    @Test
    void exibirProjetoPLNaoConclusiva() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        this.camaraController.cadastrarPL("12345678-6", 2016, "Ementa PLnconc", "saude",
                "http://example.com/semana_saude", false);
        assertEquals("Projeto de Lei - PL 1/2016 - 12345678-6 - Ementa PLnconc - EM VOTACAO (CCJC)",
                this.camaraController.exibirProjeto("PL 1/2016"));
    }

    @Test
    void exibirProjetoPLP() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        this.camaraController.cadastrarPLP("12345678-6", 2016, "Ementa PLP", "seguranca",
                "http://example.com/semana_seguranca", "153");
        assertEquals("Projeto de Lei Complementar - PLP 1/2016 - 12345678-6 - Ementa PLP - 153 - EM VOTACAO (CCJC)",
                this.camaraController.exibirProjeto("PLP 1/2016"));
    }

    @Test
    void exibirProjetoPEC() {
        this.camaraController.cadastrarDeputado("12345678-6", "29022016");
        this.camaraController.cadastrarPEC("12345678-6", 2016, "Ementa PEC", "trabalho",
                "http://example.com/semana_trabalho", "13");
        assertEquals("Projeto de Emenda Constitucional - PEC 1/2016 - 12345678-6 - Ementa PEC - 13 - EM VOTACAO (CCJC)",
                this.camaraController.exibirProjeto("PEC 1/2016"));
    }

    @Test
    void testCadastrarComissaoTemaVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("", "051222222-0,051444444-0"));
    }

    @Test
    void testCadastrarComissaoTemaNulo() {
        assertThrows(NullPointerException.class, () ->
                this.camaraController.cadastrarComissao(null, "051222222-0,051444444-0"));
    }

    @Test
    void testCadastrarComissaoPoliticosVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("CLP", ""));
    }

    @Test
    void testCadastrarComissaoPoliticosNulo() {
        assertThrows(NullPointerException.class, () ->
                this.camaraController.cadastrarComissao("CLP", null));
    }

    @Test
    void testCadastrarComissaoTemaJaCadastrado() {
        this.camaraController.cadastrarPessoa("jose", "051444444-0", "sp", "saude",
                "pt");
        this.camaraController.cadastrarDeputado("051444444-0", "11102018");
        this.camaraController.cadastrarComissao("CLP", "051444444-0");
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testCadastrarComissaoDNIInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("CLP", "*051444444-1"));
    }

    @Test
    void testCadastrarComissaoPessoaInexistente() {
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testCadastrarComissaoPessoaNaoEhDeputado() {
        this.camaraController.cadastrarPessoa("jose", "051444444-0", "sp", "saude",
                "pt");
        assertThrows(IllegalArgumentException.class, () ->
                this.camaraController.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testVotarComissaoConclusiva() {
        assertTrue(this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("  ", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoStatusGovernistaVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("PL 1/2016", "  ", "CTF"));
    }

    @Test
    void testVotarComissaoProximoLocalVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "  "));
    }

    @Test
    void testVotarComissaoCCJCNaoCadastrada() {
        ControllerGeral camaraController1 = new ControllerGeral();
        camaraController1.cadastrarPessoa("P1", "071111111-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        camaraController1.cadastrarDeputado("071111111-0", "29022016");
        camaraController1.cadastrarPL("071111111-0", 2016, "Ementa PL conc","saude,educacao basica", "http://example.com/semana_saude",true);
        assertThrows(IllegalArgumentException.class, () -> camaraController1.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoPropostaNoPlenarioConclusiva() {
        this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoTramitacaoEncerrada1Conclusiva() {
        this.camaraController3.votarComissao("PL 1/2016", "OPOSICAO", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoAceitaConclusiva() {
        this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        assertTrue(this.camaraController3.votarComissao("PL 1/2016", "LIVRE", "-"));
    }

    @Test
    void testVotarComissaoArquivada() {
        this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        assertFalse(this.camaraController3.votarComissao("PL 1/2016", "GOVERNISTA", "-"));
    }

    @Test
    void testVotarComissaoNaoConclusiva() {
        this.camaraController3.cadastrarPL("071333333-0", 2017, "Ementa PLnconc","cidadania,educacao basica", "http://example.com/semana_cidadania",false);
        assertTrue(this.camaraController3.votarComissao("PL 1/2017", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoNoPlenarioNaoConclusiva() {
        this.camaraController3.cadastrarPL("071333333-0", 2017, "Ementa PLnconc","cidadania,educacao basica", "http://example.com/semana_cidadania",false);
        assertTrue(this.camaraController3.votarComissao("PL 1/2017", "GOVERNISTA", "plenario"));
    }

    void testExceptionVotarPlenarioPL(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 1/2016", "GOVERNISTA","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 1/2016", "GOVERNISTA","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 4/2016", "GOVERNISTA","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 6/2016", "GOVERNISTA","071111111-0"));

    }

    @Test
    void testExceptionVotarPlenarioPL2(){
        this.camaraController3.votarComissao("PL 7/2016", "GOVERNISTA", "CGOV");
        this.camaraController3.votarComissao("PL 7/2016", "GOVERNISTA", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 7/2016", "GOVERNISTA","071111111-0"));
        this.camaraController3.votarPlenario("PL 7/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071777777-0,071888888-0,071999999-0");
        assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarPlenario("PL 7/2016", "GOVERNISTA","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));

    }


   @Test
    void testBooleanVotarPlenarioPL(){
        this.camaraController3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.camaraController3.votarComissao("PL 7/2016", "GOVERNISTA", "CGOV");
        assertTrue(this.camaraController3.votarPlenario("PL 6/2016", "GOVERNISTA","071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0"));
        assertTrue(this.camaraController3.votarComissao("PL 7/2016", "GOVERNISTA", "plenario"));
        assertFalse(this.camaraController3.votarPlenario("PL 7/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071777777-0,071888888-0,071999999-0"));

   }

   @Test
    void testBooleanPlenarioPLP(){
       this.camaraController3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
       this.camaraController3.votarComissao("PLP 1/2016", "GOVERNISTA", "plenario");
       assertFalse(this.camaraController3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0"));

   }

   @Test
    void testExceptionPlenarioPLP(){
       this.camaraController3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
       this.camaraController3.votarComissao("PLP 1/2016", "GOVERNISTA", "plenario");
       this.camaraController3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0");
       assertThrows(IllegalArgumentException.class, () ->  this.camaraController3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0"));

   }

   @Test
    void testExceptionPlenarioPEC(){
       this.camaraController3.votarComissao("PEC 1/2016", "GOVERNISTA", "CGOV");
       this.camaraController3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario");
       this.camaraController3.votarPlenario("PEC 1/2016","OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0");

       assertThrows(IllegalArgumentException.class, () -> this.camaraController3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario"));
       assertThrows(IllegalArgumentException.class, () ->        this.camaraController3.votarPlenario("PEC 1/2016","OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0"));
   }

    @Test
    void testBooleanPlenarioPEC(){
        this.camaraController3.votarComissao("PEC 1/2016", "GOVERNISTA", "CGOV");
        this.camaraController3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario");
        this.camaraController3.votarComissao("PEC 2/2016", "GOVERNISTA", "CGOV");
        this.camaraController3.votarComissao("PEC 2/2016", "GOVERNISTA", "plenario");
        assertFalse(this.camaraController3.votarPlenario("PEC 1/2016","OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0"));
        assertTrue(this.camaraController3.votarPlenario("PEC 2/2016","LIVRE", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0"));
        assertTrue(this.camaraController3.votarPlenario("PEC 2/2016","LIVRE", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0"));
    }
}

