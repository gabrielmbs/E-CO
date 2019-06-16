package ECameraOrganizadaTest;

import ECamaraOrganizada.ProjetoEmendaConstitucional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoEmendaConstitucionalTest {

    private ProjetoEmendaConstitucional projetoEmendaConstitucional;

    @Test
    void criaPECCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("", "112345678-6",
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPECCodigoNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional(null, "112345678-6",
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPECDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016", "",
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPECDniNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016", null,
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPECEmentaVazia() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECEmentaNula() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, null, "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECInteressesVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", "", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECInteressesNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", null, "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECUrlVazia() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", "saude", "", "153"));
    }

    @Test
    void criaPECUrlNula() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", "saude", null, "153"));
    }

    @Test
    void criaPECDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                " 112345678-6", 2016, "Ementa PEC", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "A112345678-6", 2016, "Ementa PEC", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                ".112345678-6", 2016, "Ementa PEC", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECAnoPosteriorAoAtual() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2021, "Ementa PEC", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECAnoAnteriorA1988() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 1980, "Ementa PEC", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPECArtigosVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", "saude", "http://example.com/semana_saude",
                ""));
    }

    @Test
    void criaPECArtigosNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoEmendaConstitucional("PEC 1/2016",
                "112345678-6", 2016, "Ementa PEC", "saude", "http://example.com/semana_saude",
                null));
    }

    @Test
    void criaPECDadosValidos() {
        this.projetoEmendaConstitucional = new ProjetoEmendaConstitucional("PEC 1/2016", "112345678-6",
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153");
    }

    @Test
    void toStringPEC() {
        this.projetoEmendaConstitucional = new ProjetoEmendaConstitucional("PEC 1/2016", "112345678-6",
                2016, "Ementa PEC", "saude", "http://example.com/semana_saude", "153");
        assertEquals("Projeto de Emenda Constitucional - PEC 1/2016 - 112345678-6 - Ementa PEC - 153 - EM VOTACAO (CCJC)",
                this.projetoEmendaConstitucional.toString());
    }
}