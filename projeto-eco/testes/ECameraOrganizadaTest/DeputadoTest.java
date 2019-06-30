package ECameraOrganizadaTest;

import entidades.Deputado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeputadoTest {

    private Deputado deputado;

    @BeforeEach
    void setUp() {
        this.deputado = new Deputado("03102011");
    }

    @Test
    void TestConstrutorDeputado() {
        Deputado deputado2 = new Deputado("02102011");
    }

    @Test
    void TestConstrutorDeputadoParametroNulo() {
        assertThrows(NullPointerException.class, () -> new Deputado(null));
    }

    @Test
    void TestConstrutorDeputadoParametroVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Deputado("  "));
    }

    @Test
    void exibir() {
        assertEquals("POL: Gabriel - 011111111-0 - ABC - 03/10/2011 - 0 Leis", this.deputado.exibir("Gabriel - 011111111-0 - ABC"));
    }
}