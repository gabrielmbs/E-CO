package ECameraOrganizadaTest;

import entidades.Comissao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComissaoTest {

    private Comissao comissao;

    @BeforeEach
    void setUp() {
        String[] dnis = {"051222222-0,051444444-0"};
        this.comissao = new Comissao("CLP", dnis);
    }

    @Test
    void equalsTrue() {
        String[] dni = {"051222222-0"};
        Comissao comissao = new Comissao("CLP", dni);
        assertTrue(this.comissao.equals(comissao));
    }

    @Test
    void equalsFalse() {
        String[] dni = {"051222222-0"};
        Comissao comissao = new Comissao("CCJC", dni);
        assertFalse(this.comissao.equals(comissao));
    }
}