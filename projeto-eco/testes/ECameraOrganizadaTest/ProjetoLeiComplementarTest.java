package ECameraOrganizadaTest;

import entidades.ProjetoLeiComplementar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoLeiComplementarTest {
    private ProjetoLeiComplementar projetoLeiComplementar;

    @Test
    void criaPLPCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("", "112345678-6",
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPLPCodigoNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar(null, "112345678-6",
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPLPDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016", "",
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPLPDniNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar("PLP 1/2016", null,
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153"));
    }

    @Test
    void criaPLPEmentaVazia() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPEmentaNula() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, null, "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPInteressesVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", "", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPInteressesNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", null, "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPUrlVazia() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", "saude", "", "153"));
    }

    @Test
    void criaPLPUrlNula() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", "saude", null, "153"));
    }

    @Test
    void criaPLPDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                " 112345678-6", 2016, "Ementa PLP", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "A112345678-6", 2016, "Ementa PLP", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                ".112345678-6", 2016, "Ementa PLP", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPAnoPosteriorAoAtual() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2021, "Ementa PLP", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPAnoAnteriorA1988() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 1980, "Ementa PLP", "saude", "http://example.com/semana_saude",
                "153"));
    }

    @Test
    void criaPLPArtigosVazio() {
        assertThrows(IllegalArgumentException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", "saude", "http://example.com/semana_saude",
                ""));
    }

    @Test
    void criaPLPArtigosNulo() {
        assertThrows(NullPointerException.class, () -> new ProjetoLeiComplementar("PLP 1/2016",
                "112345678-6", 2016, "Ementa PLP", "saude", "http://example.com/semana_saude",
                null));
    }

    @Test
    void criaPLPDadosValidos() {
        this.projetoLeiComplementar = new ProjetoLeiComplementar("PLP 1/2016", "112345678-6",
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153");
    }

    @Test
    void toStringPEC() {
        this.projetoLeiComplementar = new ProjetoLeiComplementar("PLP 1/2016", "112345678-6",
                2016, "Ementa PLP", "saude", "http://example.com/semana_saude", "153");
        assertEquals("Projeto de Lei Complementar - PLP 1/2016 - 112345678-6 - Ementa PLP - 153 - EM VOTACAO (CCJC)",
                this.projetoLeiComplementar.toString());
    }

}