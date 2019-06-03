package ECameraOrganizadaTest;

import ECamaraOrganizada.PessoaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PessoaControllerTest {

    private PessoaController pessoaController;

    @BeforeEach
    void criaPessoaController() {
        this.pessoaController = new PessoaController();
        // sem interesse e sem partido
        this.pessoaController.cadastrarPessoa("Jonas", "12345678-9", "PB", "");
        // com interesse e sem partido
        this.pessoaController.cadastrarPessoa("Jonas", "12345678-0", "PB", "educação");
        // sem interesse e com partido
        this.pessoaController.cadastrarPessoa("Jonas", "12345678-1", "PB", "", "PT");
        // com interesse e com partido
        this.pessoaController.cadastrarPessoa("Jonas", "12345678-6", "PB", "educação", "PT");
    }

    @Test
    void cadastrarPessoaNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastrarPessoaComPartidoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-9", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniVazio() {
        assertThrows(IllegalArgumentException.class,  () -> this.pessoaController.cadastrarPessoa("Jonas",
                "", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniVazio() {
        assertThrows(IllegalArgumentException.class,  () -> this.pessoaController.cadastrarPessoa("Jonas",
                "", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                null, "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                null, "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                " 12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                " 12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                ".12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                ".12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "A12346578", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "A12346578", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "A12345678", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "A12345678", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A123456789", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A123456789", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A412345678", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "A412345678", null, "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosNomeNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniVazioEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                null, "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniNuloEstadoVazio() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                null, "", "", "PT"));
    }

    @Test
    void cadastraPessoaDniJaCadastrado() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoDniJaCadastrado() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("",
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-9", "PB", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoNomeNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa(null,
                "12345678-1", "PB", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", "", ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", "", "", "PT"));
    }

    @Test
    void cadastraPessoaPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", null, ""));
    }

    @Test
    void cadastraPessoaComPartidoPrecedenciaDosErrosDniJaCadastradoEstadoNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaController.cadastrarPessoa("Jonas",
                "12345678-9", null, "", "PT"));
    }

    @Test
    void cadastrarPessoa() {
        this.pessoaController.cadastrarPessoa("Juan", "98765432-1", "RJ", "");
    }

    @Test
    void cadastrarPessoaComInteresse() {
        this.pessoaController.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação");
    }

    @Test
    void cadastrarPessoaComPartido() {
        this.pessoaController.cadastrarPessoa("Juan", "98765432-1", "RJ", "", "PT");
    }

    @Test
    void cadastrarPessoaComPartidoComInteresse() {
        this.pessoaController.cadastrarPessoa("Juan", "98765432-1", "RJ", "Educação",
                "PT");
    }

    @Test
    void testExibirPessoaDniNulo(){
        assertThrows(NullPointerException.class, () -> this.pessoaController.exibirPessoa(null));
    }

    @Test
    void testExibirPessoaDniVazio(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa(" "));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComEspaco(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa(" 12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComLetra(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa("A12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoComCaractereEspecial(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa(".%12345678"));
    }

    @Test
    void testExibirPessoaDniInvalidoInvalidoSemTraco(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa("123456781"));
    }

    @Test
    void testExibirPessoaInexistente(){
        assertThrows(IllegalArgumentException.class, () -> this.pessoaController.exibirPessoa("12345228-3"));
    }

    @Test
    void testExibirPessoaSemInteresseESemPartido(){
        String esperado = "Jonas - 12345678-9 (PB)";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-9"));
    }

    @Test
    void testExibirPessoaComInteresseESemPartido(){
        String esperado = "Jonas - 12345678-0 (PB) - Interesses: educação";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-0"));
    }

    @Test
    void testExibirPessoaSemInteresseEComPartido(){
        String esperado = "Jonas - 12345678-1 (PB) - PT";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirPessoaComInteresseEComPartido(){
        String esperado = "Jonas - 12345678-6 (PB) - PT - Interesses: educação";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-6"));
    }

    @Test
    void testExibirDeputadoSemInteresse(){
        this.pessoaController.cadastrarDeputado("12345678-1", "20102018");
        String esperado = "POL: Jonas - 12345678-1 (PB) - PT - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-1"));
    }

    @Test
    void testExibirDeputadoComInteresse(){
        this.pessoaController.cadastrarDeputado("12345678-6", "20102018");
        String esperado = "POL: Jonas - 12345678-6 (PB) - PT - Interesses: educação - 20/10/2018 - 0 Leis";
        assertEquals(esperado, this.pessoaController.exibirPessoa("12345678-6"));
    }
}