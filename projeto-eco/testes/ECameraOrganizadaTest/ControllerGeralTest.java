package ECameraOrganizadaTest;

import controllers.ControllerGeral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerGeralTest {

    private ControllerGeral controllerGeral;

    private ControllerGeral controllerGeral2;

    private ControllerGeral controllerGeral3;

    @BeforeEach
    void criaPessoaController() {
        this.controllerGeral = new ControllerGeral();
        this.controllerGeral2 = new ControllerGeral();
        this.controllerGeral3 = new ControllerGeral();

        // sem interesse e sem partido
        this.controllerGeral.cadastrarPessoa("Jonas", "12345678-9", "PB", "");
        // com interesse e sem partido
        this.controllerGeral.cadastrarPessoa("Jonas", "12345678-0", "PB", "educação");
        // sem interesse e com partido
        this.controllerGeral.cadastrarPessoa("Jonas", "12345678-1", "PB", "", "PT");
        // com interesse e com partido
        this.controllerGeral.cadastrarPessoa("Jonas", "12345678-6", "PB", "educação", "PT");

        this.controllerGeral.cadastrarPartido("PMDB");
        this.controllerGeral.cadastrarPartido("PSDB");
        this.controllerGeral.cadastrarPartido("PT");
        this.controllerGeral.cadastrarPartido("DEM");
        this.controllerGeral.cadastrarPartido("PCO");
        this.controllerGeral.cadastrarPartido("PCB");
        this.controllerGeral.cadastrarPartido("NOVO");

        this.controllerGeral3.cadastrarPessoa("P1", "071111111-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P2", "071222222-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P3", "071333333-0", "PB", "saude,seguranca publica,trabalho", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P4", "071444444-0", "PI", "saude,seguranca publica,trabalho", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P5", "071555555-0", "PI", "nutricao", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P6", "071666666-0", "RO", "educacao,seguranca publica,saude", "PartidoOpo");
        this.controllerGeral3.cadastrarPessoa("P7", "071777777-0", "RO", "educacao,seguranca publica,saude", "PartidoOpo");
        this.controllerGeral3.cadastrarPessoa("P8", "071888888-0", "RO", "saude,seguranca publica,trabalho", "PartidoOpo");
        this.controllerGeral3.cadastrarPessoa("P9", "071999999-0", "RO", "saude,seguranca publica,trabalho", "PartidoOpo");
        this.controllerGeral3.cadastrarPessoa("P10", "071000000-0", "RO", "nutricao", "PartidoOpo");

        this.controllerGeral3.cadastrarPessoa("P11", "091111111-0", "PB", "games", "PartidoGov");
        this.controllerGeral3.cadastrarPessoa("P12", "091999999-0", "MT", "saude,seguranca publica,trabalho", "PartidoOpo");
        this.controllerGeral3.cadastrarPessoa("P12", "091000000-0", "MT", "nutricao", "PartidoOpo");


        this.controllerGeral3.cadastrarDeputado("071111111-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071222222-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071333333-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071444444-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071555555-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071666666-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071777777-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071888888-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071999999-0", "29022016");
        this.controllerGeral3.cadastrarDeputado("071000000-0", "29022016");

        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "nutricao", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071333333-0", 2016, "Ementa PLnconc", "cidadania,educacao basica", "http://example.com/semana_cidadania", false);
        this.controllerGeral3.cadastrarPL("071333333-0", 2016, "Ementa PLnconc", "cidadania,educacao basica", "http://example.com/semana_cidadania", false);
        this.controllerGeral3.cadastrarPL("071222222-0", 2016, "Ementa PL conc", "nutricao", "http://example.com/semana_saude", true);
        this.controllerGeral3.cadastrarPL("071222222-0", 2013, "Ementa PL conc", "saude", "http://example.com/semana_saude", true);


        this.controllerGeral3.cadastrarPLP("071222222-0", 2016, "Ementa PLP", "fiscal,jogos", "https://example.net/jogos%40aposta", "153");
        this.controllerGeral3.cadastrarPLP("071222222-0", 2013, "Ementa PLP", "saude", "https://example.net/jogos%40aposta", "153");
        this.controllerGeral3.cadastrarPLP("071222222-0", 2016, "Ementa PLP", "nutricao", "https://example.net/jogos%40aposta", "153");


        this.controllerGeral3.cadastrarPEC("071222222-0", 2016, "Ementa PEC", "saude", "https://example.com/sindicato/algo.html", "7,8");
        this.controllerGeral3.cadastrarPEC("071222222-0", 2016, "Ementa PEC", "saude", "https://example.com/sindicato/algo.html", "7,8");
        this.controllerGeral3.cadastrarPEC("071222222-0", 2016, "Ementa PEC", "saude", "https://example.com/sindicato/algo.html", "7,8");
        this.controllerGeral3.cadastrarPEC("071222222-0", 2016, "Ementa PEC", "nutri", "https://example.com/sindicato/algo.html", "7,8");


        this.controllerGeral3.cadastrarPartido("PartidoGov");

        //5 da base, 4 oposicao
        this.controllerGeral3.cadastrarComissao("CCJC", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0");
        //5 da base, 5 oposicao
        this.controllerGeral3.cadastrarComissao("CTF", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0");
        //5 da base
        this.controllerGeral3.cadastrarComissao("CGOV", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0");
        //5 da oposicao
        this.controllerGeral3.cadastrarComissao("COPO", "071666666-0,071777777-0,071888888-0,071999999-0,071000000-0");

    }

    @Test
    void cadastrarPessoaNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastrarPessoaComPartidoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                null, "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                null, "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                " 12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                " 12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                ".12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                ".12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "A12346578", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "A12346578", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A123456789", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A123456789", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A412345678", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "A412345678", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                null, "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                null, "", "", "PT"));
    }

    @Test
    void cadastraPessoaDniJaCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniJaCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa(null,
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastrarPessoa() {
        this.controllerGeral.cadastrarPessoa("Juan", "98765432-1", "RJ", "");
    }

    @Test
    void cadastrarPessoaComInteresse() {
        this.controllerGeral.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação");
    }

    @Test
    void cadastrarPessoaComPartido() {
        this.controllerGeral.cadastrarPessoa("Juan", "98765432-1", "RJ", "", "PT");
    }

    @Test
    void cadastrarPessoaComPartidoComInteresse() {
        this.controllerGeral.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação",
                "PT");
    }

    @Test
    void testExibirPessoaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.exibirPessoa(null));
    }

    @Test
    void testExibirPessoaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirPessoa(" "));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirPessoa(" 12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirPessoa("A12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirPessoa(".%12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoSemTraco() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.exibirPessoa("123456781"));
    }

    @Test
    void testExibirPessoaInexistente() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.exibirPessoa("12345228-3"));
    }

    @Test
    void testExibirPessoaSemInteresseESemPartido() {
        String esperado = "Jonas - 12345678-9 (PB)";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-9"));
    }

    @Test
    void testExibirPessoaComInteresseESemPartido() {
        String esperado = "Jonas - 12345678-0 (PB) - Interesses: educação";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-0"));
    }

    @Test
    void testExibirPessoaSemInteresseEComPartido() {
        String esperado = "Jonas - 12345678-1 (PB) - PT";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirPessoaComInteresseEComPartido() {
        String esperado = "Jonas - 12345678-6 (PB) - PT - Interesses: educação";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-6"));
    }

    @Test
    void testExibirDeputadoSemInteresse() {
        this.controllerGeral.cadastrarDeputado("12345678-1", "20102018");
        String esperado = "POL: Jonas - 12345678-1 (PB) - PT - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirDeputadoComInteresse() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "20102018");
        String esperado = "POL: Jonas - 12345678-6 (PB) - PT - Interesses: educação - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.controllerGeral.exibirPessoa("12345678-6"));
    }

    @Test
    void cadastrarPartidoNullPointerTest() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPartido(null));
    }

    @Test
    void cadastrarPartidoIllegalArgumentTest() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPartido("  "));
    }

    @Test
    void exibirBaseTest() {
        String stringEsperada = "DEM,NOVO,PCB,PCO,PMDB,PSDB,PT";
        assertEquals(stringEsperada, this.controllerGeral.exibirBase());
    }

    @Test
    void exibirBaseVaziaTest() {
        assertEquals("", this.controllerGeral2.exibirBase());
    }

    @Test
    void cadastrarPLPDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP(null, 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPEmentaVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPEmentaNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPUrlVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "", "153"));
    }

    @Test
    void cadastrarPLPUrlNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", null, "153"));
    }

    @Test
    void cadastrarPLPInteressesVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPInteressesNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", null, "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComEspaco() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP(" 112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComLetra() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("A112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPDniInvalidoComCaractereEspecial() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP(".112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPAnoPosteriorAoAtual() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2021,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPAnoAnteriorA1988() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 1980,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPPessoaInexistente() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-5", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLPArtigosVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", ""));
    }

    @Test
    void cadastrarPLPArtigosNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPLP("112345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", null));
    }

    @Test
    void cadastrarPLPDadosValidos() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PLP 1/2016", this.controllerGeral.cadastrarPLP("12345678-6", 2016,
                "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPLConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL(null, 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaEmentaVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaEmentaNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaUrlVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", "", true));
    }

    @Test
    void cadastrarPLConclusivaUrlNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", null, true));
    }

    @Test
    void cadastrarPLConclusivaInteressesVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaInteressesNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", null, "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComEspaco() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL(" 112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComLetra() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("A112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDniInvalidoComCaractereEspecial() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL(".112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaAnoPosteriorAoAtual() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2021,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaAnoAnteriorA1988() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 1980,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaPessoaInexistente() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-5", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void cadastrarPLConclusivaDadosValidos() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PL 1/2016", this.controllerGeral.cadastrarPL("12345678-6", 2016,
                "Ementa PL conc", "educacao", "http://example.com/semana_educacao", true));
    }

    @Test
    void cadastrarPECDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC(null, 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECEmentaVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECEmentaNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECUrlVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "", "153"));
    }

    @Test
    void cadastrarPECUrlNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", null, "153"));
    }

    @Test
    void cadastrarPECInteressesVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECInteressesNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", null, "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComEspaco() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC(" 112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComLetra() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("A112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECDniInvalidoComCaractereEspecial() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC(".112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECAnoPosteriorAoAtual() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2021,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECAnoAnteriorA1988() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 1980,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECPessoaInexistente() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-5", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void cadastrarPECArtigosVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", ""));
    }

    @Test
    void cadastrarPECArtigosNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPEC("112345678-6", 2016,
                "Ementa PEC", "saude", "http://example.com/semana_saude", null));
    }

    @Test
    void cadastrarPECDadosValidos() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PEC 1/2016", this.controllerGeral.cadastrarPEC("12345678-6", 2016,
                "Ementa PEC", "seguranca", "http://example.com/semana_seguranca", "153"));
    }

    @Test
    void cadastrarPLNaoConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL(null, 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaEmentaVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaEmentaNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                null, "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaUrlVazia() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", "", false));
    }

    @Test
    void cadastrarPLNaoConclusivaUrlNula() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", null, false));
    }

    @Test
    void cadastrarPLNaoConclusivaInteressesVazio() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaInteressesNulo() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", null, "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComEspaco() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL(" 112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComLetra() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("A112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDniInvalidoComCaractereEspecial() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL(".112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaAnoPosteriorAoAtual() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2021,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaAnoAnteriorA1988() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 1980,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaPessoaInexistente() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-5", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaPessoaNaoEhDeputado() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.cadastrarPL("112345678-6", 2016,
                "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void cadastrarPLNaoConclusivaDadosValidos() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        assertEquals("PL 1/2016", this.controllerGeral.cadastrarPL("12345678-6", 2016,
                "Ementa PLnconc", "nutricao", "http://example.com/semana_nutricao", false));
    }

    @Test
    void exibirProjetoCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirProjeto(""));
    }

    @Test
    void exibirProjetoCodigoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.exibirProjeto(null));
    }

    @Test
    void exibirProjetoInexistente() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral.exibirProjeto("PL 2/2015"));
    }

    @Test
    void exibirProjetoPLConclusiva() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        this.controllerGeral.cadastrarPL("12345678-6", 2016, "Ementa PL conc", "educacao",
                "http://example.com/semana_educacao", true);
        assertEquals("Projeto de Lei - PL 1/2016 - 12345678-6 - Ementa PL conc - Conclusiva - EM VOTACAO (CCJC)",
                this.controllerGeral.exibirProjeto("PL 1/2016"));
    }

    @Test
    void exibirProjetoPLNaoConclusiva() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        this.controllerGeral.cadastrarPL("12345678-6", 2016, "Ementa PLnconc", "saude",
                "http://example.com/semana_saude", false);
        assertEquals("Projeto de Lei - PL 1/2016 - 12345678-6 - Ementa PLnconc - EM VOTACAO (CCJC)",
                this.controllerGeral.exibirProjeto("PL 1/2016"));
    }

    @Test
    void exibirProjetoPLP() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        this.controllerGeral.cadastrarPLP("12345678-6", 2016, "Ementa PLP", "seguranca",
                "http://example.com/semana_seguranca", "153");
        assertEquals("Projeto de Lei Complementar - PLP 1/2016 - 12345678-6 - Ementa PLP - 153 - EM VOTACAO (CCJC)",
                this.controllerGeral.exibirProjeto("PLP 1/2016"));
    }

    @Test
    void exibirProjetoPEC() {
        this.controllerGeral.cadastrarDeputado("12345678-6", "29022016");
        this.controllerGeral.cadastrarPEC("12345678-6", 2016, "Ementa PEC", "trabalho",
                "http://example.com/semana_trabalho", "13");
        assertEquals("Projeto de Emenda Constitucional - PEC 1/2016 - 12345678-6 - Ementa PEC - 13 - EM VOTACAO (CCJC)",
                this.controllerGeral.exibirProjeto("PEC 1/2016"));
    }

    @Test
    void testCadastrarComissaoTemaVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("", "051222222-0,051444444-0"));
    }

    @Test
    void testCadastrarComissaoTemaNulo() {
        assertThrows(NullPointerException.class, () ->
                this.controllerGeral.cadastrarComissao(null, "051222222-0,051444444-0"));
    }

    @Test
    void testCadastrarComissaoPoliticosVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", ""));
    }

    @Test
    void testCadastrarComissaoPoliticosNulo() {
        assertThrows(NullPointerException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", null));
    }

    @Test
    void testCadastrarComissaoTemaJaCadastrado() {
        this.controllerGeral.cadastrarPessoa("jose", "051444444-0", "sp", "saude",
                "pt");
        this.controllerGeral.cadastrarDeputado("051444444-0", "11102018");
        this.controllerGeral.cadastrarComissao("CLP", "051444444-0");
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testCadastrarComissaoDNIInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", "*051444444-1"));
    }

    @Test
    void testCadastrarComissaoPessoaInexistente() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testCadastrarComissaoPessoaNaoEhDeputado() {
        this.controllerGeral.cadastrarPessoa("jose", "051444444-0", "sp", "saude",
                "pt");
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.cadastrarComissao("CLP", "051444444-1"));
    }

    @Test
    void testVotarComissaoConclusiva() {
        assertTrue(this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("  ", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoStatusGovernistaVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("PL 1/2016", "  ", "CTF"));
    }

    @Test
    void testVotarComissaoProximoLocalVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "  "));
    }

    @Test
    void testVotarComissaoCCJCNaoCadastrada() {
        ControllerGeral camaraController1 = new ControllerGeral();
        camaraController1.cadastrarPessoa("P1", "071111111-0", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        camaraController1.cadastrarDeputado("071111111-0", "29022016");
        camaraController1.cadastrarPL("071111111-0", 2016, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true);
        assertThrows(IllegalArgumentException.class, () -> camaraController1.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoPropostaNoPlenarioConclusiva() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoTramitacaoEncerrada1Conclusiva() {
        this.controllerGeral3.votarComissao("PL 1/2016", "OPOSICAO", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoAceitaConclusiva() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        assertTrue(this.controllerGeral3.votarComissao("PL 1/2016", "LIVRE", "-"));
    }

    @Test
    void testVotarComissaoArquivada() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        assertFalse(this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "-"));
    }

    @Test
    void testVotarComissaoNaoConclusiva() {
        this.controllerGeral3.cadastrarPL("071333333-0", 2017, "Ementa PLnconc", "cidadania,educacao basica", "http://example.com/semana_cidadania", false);
        assertTrue(this.controllerGeral3.votarComissao("PL 1/2017", "GOVERNISTA", "CTF"));
    }

    @Test
    void testVotarComissaoNoPlenarioNaoConclusiva() {
        this.controllerGeral3.cadastrarPL("071333333-0", 2017, "Ementa PLnconc", "cidadania,educacao basica", "http://example.com/semana_cidadania", false);
        assertTrue(this.controllerGeral3.votarComissao("PL 1/2017", "GOVERNISTA", "plenario"));
    }

    void testExceptionVotarPlenarioPL() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 1/2016", "GOVERNISTA", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 1/2016", "GOVERNISTA", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 4/2016", "GOVERNISTA", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 6/2016", "GOVERNISTA", "071111111-0"));

    }

    @Test
    void testExceptionVotarPlenarioPL2() {
        this.controllerGeral3.votarComissao("PL 7/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 7/2016", "GOVERNISTA", "plenario");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 7/2016", "GOVERNISTA", "071111111-0"));
        this.controllerGeral3.votarPlenario("PL 7/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071777777-0,071888888-0,071999999-0");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PL 7/2016", "GOVERNISTA", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0,071000000-0"));

    }


    @Test
    void testBooleanVotarPlenarioPL() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 7/2016", "GOVERNISTA", "CGOV");
        assertTrue(this.controllerGeral3.votarPlenario("PL 6/2016", "GOVERNISTA", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0"));
        assertTrue(this.controllerGeral3.votarComissao("PL 7/2016", "GOVERNISTA", "plenario"));
        assertFalse(this.controllerGeral3.votarPlenario("PL 7/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071777777-0,071888888-0,071999999-0"));

    }

    @Test
    void testBooleanPlenarioPLP() {
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "plenario");
        assertFalse(this.controllerGeral3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0"));

    }

    @Test
    void testExceptionPlenarioPLP() {
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "plenario");
        this.controllerGeral3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0");
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PLP 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0"));

    }

    @Test
    void testExceptionPlenarioPEC() {
        this.controllerGeral3.votarComissao("PEC 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario");
        this.controllerGeral3.votarPlenario("PEC 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0");

        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral3.votarPlenario("PEC 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0"));
    }

    @Test
    void testBooleanPlenarioPEC() {
        this.controllerGeral3.votarComissao("PEC 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PEC 1/2016", "GOVERNISTA", "plenario");
        this.controllerGeral3.votarComissao("PEC 2/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PEC 2/2016", "GOVERNISTA", "plenario");
        assertFalse(this.controllerGeral3.votarPlenario("PEC 1/2016", "OPOSICAO", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071999999-0,071000000-0"));
        assertFalse(this.controllerGeral3.votarPlenario("PEC 2/2016", "LIVRE", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0"));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaDniVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("", "CONCLUSAO"));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaDniNulo() {
        assertThrows(NullPointerException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada(null, "CONCLUSAO"));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaDniInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("22223", "CONCLUSAO"));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaEstrategiaVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("12345678-6", ""));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaEstrategiaNull() {
        assertThrows(NullPointerException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("12345678-6", null));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaPessoaInexistente() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("12345622-2", "CONCLUSAO"));
    }

    @Test
    void configurarEstrategiaPropostaRelacionadaEstrategiaInvalida() {
        assertThrows(IllegalArgumentException.class, () ->
                this.controllerGeral.configurarEstrategiaPropostaRelacionada("12345678-6", "CONCLUSAO2"));
    }

    @Test
    void pegaPropostaRelacionadaSemInteressesEmComum() {
        assertEquals("", this.controllerGeral3.pegarPropostaRelacionada("091111111-0"));
    }

    @Test
    void pegaPropostaRelacionadaPECPrimeiro() {
        assertEquals("PEC 1/2016", this.controllerGeral3.pegarPropostaRelacionada("091999999-0"));
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));

    }

    @Test
    void pegaPropostaRelacionadaConclusao() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091999999-0", "CONCLUSAO");
        assertEquals("PL 1/2013", this.controllerGeral3.pegarPropostaRelacionada("091999999-0"));
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));


    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPEC() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "CGOV");
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));


    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPEC2() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "COPO");
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));

    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPLP() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PLP 2/2016", "GOVERNISTA", "CGOV");
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));

    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPLP2() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PLP 2/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 2/2016", "GOVERNISTA", "plenario");
        assertEquals("PLP 2/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));

    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPL() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PL 5/2016", "GOVERNISTA", "plenario");
        assertEquals("PL 5/2016", this.controllerGeral3.pegarPropostaRelacionada("091000000-0"));

    }

    @Test
    void pegaPropostaRelacionadaConclusaoAvancoPEC3() {
        this.controllerGeral3.configurarEstrategiaPropostaRelacionada("091000000-0", "CONCLUSAO");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA","CGOV");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA","COPO");
        this.controllerGeral3.votarPlenario("PEC 4/2016","LIVRE", "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0");
        assertEquals("PEC 1/2016",this.controllerGeral3.pegarPropostaRelacionada("091999999-0"));
    }

    @Test
    void exibirTramitacaoPLConclusivaNaCCJC() {
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral3.exibirTramitacao("PL 1/2016"));
    }

    @Test
    void exibirTramitacaoPLPNaCCJC() {
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral3.exibirTramitacao("PLP 1/2016"));
    }

    @Test
    void exibirTramitacaoPLConclusivaAprovadaNaCCJC() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        assertEquals("APROVADO (CCJC), EM VOTACAO (CTF)", this.controllerGeral3.exibirTramitacao("PL 1/2016"));
    }

    @Test
    void exibirTramitacaoPLConclusivaReprovadaNaCCJC() {
        this.controllerGeral3.votarComissao("PL 1/2016", "OPOSICAO", "CTF");
        assertEquals("REJEITADO (CCJC)", this.controllerGeral3.exibirTramitacao("PL 1/2016"));
    }

    @Test
    void exibirTramitacaoPLConclusivaVariasComissoesRejeitado() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 1/2016", "OPOSICAO", "-");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF)", this.controllerGeral3.exibirTramitacao("PL 1/2016"));
    }

    @Test
    void exibirTramitacaoPLConclusivaVariasComissoesAprovado() {
        this.controllerGeral3.votarComissao("PL 1/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 1/2016", "LIVRE", "-");
        assertEquals("APROVADO (CCJC), APROVADO (CTF)", this.controllerGeral3.exibirTramitacao("PL 1/2016"));
    }

    @Test
    void exibirTramitacaoPLNConclusiva() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CTF");
        assertEquals("APROVADO (CCJC), EM VOTACAO (CTF)", this.controllerGeral3.exibirTramitacao("PL 6/2016"));
    }

    @Test
    void exibirTramitacaoPLNConclusivaVariasComissoes() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "COPO");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), EM VOTACAO (COPO)",
                this.controllerGeral3.exibirTramitacao("PL 6/2016"));
    }

    @Test
    void exibirTramitacaoPLNConclusivaPlenario() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PL 6/2016", "OPOSICAO", "plenario");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), APROVADO (COPO), EM VOTACAO (Plenario)",
                this.controllerGeral3.exibirTramitacao("PL 6/2016"));
    }

    @Test
    void exibirTramitacaoPLNConclusivaPlenarioAprovado() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PL 6/2016", "OPOSICAO", "plenario");
        this.controllerGeral3.votarPlenario("PL 6/2016", "GOVERNISTA",
                "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0," +
                        "071999999-0");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), APROVADO (COPO), APROVADO (Plenario)",
                this.controllerGeral3.exibirTramitacao("PL 6/2016"));
    }

    @Test
    void exibirTramitacaoPLNConclusivaPlenarioRejeitado() {
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PL 6/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PL 6/2016", "OPOSICAO", "plenario");
        this.controllerGeral3.votarPlenario("PL 6/2016", "OPOSICAO",
                "071111111-0,071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0," +
                        "071999999-0");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), APROVADO (COPO), REJEITADO (Plenario)",
                this.controllerGeral3.exibirTramitacao("PL 6/2016"));
    }

    @Test
    void exibirTramitacaoPEC() {
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral3.exibirTramitacao("PEC 1/2016"));
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PEC 4/2016", "GOVERNISTA", "plenario");
        assertEquals("APROVADO (CCJC), APROVADO (CGOV), REJEITADO (COPO)", this.controllerGeral3.exibirTramitacao("PEC 4/2016"));
    }

    @Test
    void exibirTramitacaoPLPVariasComissoes() {
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "COPO");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), EM VOTACAO (COPO)",
                this.controllerGeral3.exibirTramitacao("PLP 1/2016"));
    }

    @Test
    void exibirTramitacaoPLPPlenario() {
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PLP 1/2016", "OPOSICAO", "plenario");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), APROVADO (COPO), EM VOTACAO (Plenario " +
                        "- 1o turno)",
                this.controllerGeral3.exibirTramitacao("PLP 1/2016"));
    }

    @Test
    void exibirTramitacaoPLPPlenario1TurnoRejeitado() {
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CTF");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "CGOV");
        this.controllerGeral3.votarComissao("PLP 1/2016", "GOVERNISTA", "COPO");
        this.controllerGeral3.votarComissao("PLP 1/2016", "OPOSICAO", "plenario");
        this.controllerGeral3.votarPlenario("PLP 1/2016", "GOVERNISTA", "071111111-0," +
                "071222222-0,071333333-0,071444444-0,071555555-0,071666666-0,071777777-0,071888888-0,071999999-0");
        assertEquals("APROVADO (CCJC), REJEITADO (CTF), APROVADO (CGOV), APROVADO (COPO), REJEITADO (Plenario " +
                        "- 1o turno)",
                this.controllerGeral3.exibirTramitacao("PLP 1/2016"));
    }

    @Test
    void exibirTramitacaoCodigoInvalido() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral3.exibirTramitacao("Pepe 1/2016"));
    }

    @Test
    void exibirTramitacaoCodigoVazio() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral3.exibirTramitacao("  "));
    }

    @Test
    void exibirTramitacaoCodigoNulo() {
        assertThrows(NullPointerException.class, () -> this.controllerGeral3.exibirTramitacao(null));
    }
}

