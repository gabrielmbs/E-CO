package ECameraOrganizadaTest;

import ECamaraOrganizada.CamaraController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CamaraControllerTest {

    private CamaraController camaraController;

    private CamaraController camaraController2;

    @BeforeEach
    void criaPessoaController() {
        this.camaraController = new CamaraController();
        this.camaraController2 = new CamaraController();

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
        assertThrows(IllegalArgumentException.class,  () -> this.camaraController.cadastrarPessoa("Jonas",
                "", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniVazio() {
        assertThrows(IllegalArgumentException.class,  () -> this.camaraController.cadastrarPessoa("Jonas",
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
    void testExibirPessoaDniNulo(){
        assertThrows(NullPointerException.class, () -> this.camaraController.exibirPessoa(null));
    }

    @Test
    void testExibirPessoaDniVazio(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(" "));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComEspaco(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(" 12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComLetra(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa("A12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComCaractereEspecial(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa(".%12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoSemTraco(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa("123456781"));
    }

    @Test
    void testExibirPessoaInexistente(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.exibirPessoa("12345228-3"));
    }

    @Test
    void testExibirPessoaSemInteresseESemPartido(){
        String esperado = "Jonas - 12345678-9 (PB)";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-9"));
    }

    @Test
    void testExibirPessoaComInteresseESemPartido(){
        String esperado = "Jonas - 12345678-0 (PB) - Interesses: educação";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-0"));
    }

    @Test
    void testExibirPessoaSemInteresseEComPartido(){
        String esperado = "Jonas - 12345678-1 (PB) - PT";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirPessoaComInteresseEComPartido(){
        String esperado = "Jonas - 12345678-6 (PB) - PT - Interesses: educação";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-6"));
    }

    @Test
    void testExibirDeputadoSemInteresse(){
        this.camaraController.cadastrarDeputado("12345678-1", "20102018");
        String esperado = "POL: Jonas - 12345678-1 (PB) - PT - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirDeputadoComInteresse(){
        this.camaraController.cadastrarDeputado("12345678-6", "20102018");
        String esperado = "POL: Jonas - 12345678-6 (PB) - PT - Interesses: educação - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.camaraController.exibirPessoa("12345678-6"));
    }

    @Test
    void cadastrarPartidoNullPointerTest() {
        assertThrows(NullPointerException.class, () -> this.camaraController.cadastrarPartido(null));
    }

    @Test
    void cadastrarPartidoIllegalArgumentTest(){
        assertThrows(IllegalArgumentException.class, () -> this.camaraController.cadastrarPartido("  "));
    }

    @Test
    void exibirBaseTest() {
        String stringEsperada = "DEM,NOVO,PCB,PCO,PMDB,PSDB,PT";
        assertEquals(stringEsperada, this.camaraController.exibirBase());
    }

    @Test
    void exibirBaseVaziaTest(){
        assertEquals("", this.camaraController2.exibirBase());
    }
}
