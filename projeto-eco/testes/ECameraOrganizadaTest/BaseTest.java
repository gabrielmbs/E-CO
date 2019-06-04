package ECameraOrganizadaTest;

import ECamaraOrganizada.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {
    private Base base;
    private Base base2;


    @BeforeEach
    void criaBaseParaTeste(){
        this.base = new Base();
        this.base2 = new Base();

        this.base.cadastrarPartido("PMDB");
        this.base.cadastrarPartido("PSDB");
        this.base.cadastrarPartido("PT");
        this.base.cadastrarPartido("DEM");
        this.base.cadastrarPartido("PCO");
        this.base.cadastrarPartido("PCB");
        this.base.cadastrarPartido("NOVO");
    }

    @Test
    void cadastrarPartidoNullPointerTest() {
        assertThrows(NullPointerException.class, () -> this.base.cadastrarPartido(null));
    }

    @Test
    void cadastrarPartidoIllegalArgumentTest(){
        assertThrows(IllegalArgumentException.class, () -> this.base.cadastrarPartido("  "));
    }

    @Test
    void exibirBaseTest() {
        String stringEsperada = "DEM,NOVO,PCB,PCO,PMDB,PSDB,PT";
        assertEquals(stringEsperada, this.base.exibirBase());
    }

    @Test
    void exibirBaseVaziaTest(){
        assertEquals("", this.base2.exibirBase());
    }
}