package ECameraOrganizadaTest;

import entidades.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {
    Pessoa pessoaSemPartido;
    Pessoa pessoaComPartido;

    @BeforeEach
    void setUp() {
        this.pessoaSemPartido = new Pessoa("Gabriel", "011111111-0", "PB", "educacao, saude");
        this.pessoaComPartido = new Pessoa("Gabriel", "012222222-0", "PB", "festas, drogas e musicas", "ABC");
    }

    @Test
    void TestConstrutorPessoaSemPartido() {
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "educacao, saude");
    }

    @Test
    void TestConstrutorPessoaSemPartidoParametrosNulos() {
        assertThrows(NullPointerException.class, () -> new Pessoa(null, "013333333-0", "PB", "educacao, saude"));
        assertThrows(NullPointerException.class, () -> new Pessoa("Gabriel", null, "PB", "educacao, saude"));
        assertThrows(NullPointerException.class, () -> new Pessoa("Gabriel", "013333333-0", null, "educacao, saude"));
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", null);
    }

    @Test
    void TestConstrutorPessoaSemPartidoParametrosVazios() {
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("  ", "013333333-0", "PB", "educacao, saude"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Gabriel", "  ", "PB", "educacao, saude"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Gabriel", "013333333-0", "   ", "educacao, saude"));
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "  ");
    }

    @Test
    void TestConstrutorPessoaComPartido() {
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "educacao, saude", "ABC");
    }

    @Test
    void TestConstrutorPessoaComPartidoParametrosNulos() {
        assertThrows(NullPointerException.class, () -> new Pessoa(null, "013333333-0", "PB", "educacao, saude", "ABC"));
        assertThrows(NullPointerException.class, () -> new Pessoa("Gabriel", null, "PB", "educacao, saude", "ABC"));
        assertThrows(NullPointerException.class, () -> new Pessoa("Gabriel", "013333333-0", null, "educacao, saude", "ABC"));
        assertThrows(NullPointerException.class, () -> new Pessoa("Gabriel", "013333333-0", "PB", " ", null));
    }

    @Test
    void TestConstrutorPessoaComPartidoParametrosVazios() {
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("  ", "013333333-0", "PB", "educacao, saude", "ABC"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Gabriel", "  ", "PB", "educacao, saude", "ABC"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Gabriel", "013333333-0", "   ", "educacao, saude", "ABC"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Gabriel", "013333333-0", "PB", "  ", "  "));
    }

    @Test
    void viraDeputado() {
        this.pessoaComPartido.viraDeputado("02032018");
    }

    @Test
    void viraDeputadoParametroNulo() {
        assertThrows(NullPointerException.class, () -> this.pessoaComPartido.viraDeputado(null));
    }

    @Test
    void viraDeputadoParametroVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaComPartido.viraDeputado("  "));
    }

    @Test
    void viraDeputadoParametroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> this.pessoaComPartido.viraDeputado("23062020"));
    }

    @Test
    void toString1() {
        //Pessoa com partido e com interesses
        assertEquals("Gabriel - 012222222-0 (PB) - ABC - Interesses: festas, drogas e musicas", this.pessoaComPartido.toString());
        //Pessoa sem partido e com interesses
        assertEquals("Gabriel - 011111111-0 (PB) - Interesses: educacao, saude", this.pessoaSemPartido.toString());
        //Pessoa sem partido e sem interesses
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "");
        assertEquals("Gabriel - 013333333-0 (PB)", pessoa.toString());
        //Pessoa com partido e sem interesses
        pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "", "ABC");
        assertEquals("Gabriel - 013333333-0 (PB) - ABC", pessoa.toString());
    }

    @Test
    void equals1() {
        Pessoa pessoa = new Pessoa("Gabriel", "013333333-0", "PB", "educacao, saude");
        Pessoa pessoa2 = new Pessoa("Gabriel", "013333333-0", "PB", "educacao, saude");
        assertTrue(pessoa.equals(pessoa2));
        assertTrue(pessoa.equals(pessoa));
        assertFalse(pessoa.equals(this.pessoaComPartido));
        assertFalse(pessoa.equals(null));
        assertFalse(pessoa.equals("Outra classe."));
    }

    @Test
    void hashCode1() {
        assertEquals(this.pessoaComPartido.hashCode(), this.pessoaComPartido.hashCode());
    }

    @Test
    void getPartido() {
        assertEquals("ABC", this.pessoaComPartido.getPartido());
    }
}