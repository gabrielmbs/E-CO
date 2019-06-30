package ECameraOrganizadaTest;

import entidades.PL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoDeLeiTest {

    private PL projetoDeLei;

    @Test
    void criaPLConclusivaCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("", "112345678-6",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaCodigoNulo() {
        assertThrows(NullPointerException.class, () -> new PL(null, "112345678-6",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", null,
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaEmentaVazia() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaEmentaNula() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, null, "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaInteressesVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PL conc", "", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaInteressesNulo() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PL conc", null, "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaUrlVazia() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PL conc", "saude", "", true));
    }

    @Test
    void criaPLConclusivaUrlNula() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PL conc", "saude", null, true));
    }

    @Test
    void criaPLConclusivaDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", " 112345678-6",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "A112345678-6",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", ".112345678-6",
                2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaAnoPosteriorAoAtual() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2021, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLConclusivaAnoAnteriorA1988() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                1980, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void criaPLNaoConclusivaCodigoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("", "112345678-6",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaCodigoNulo() {
        assertThrows(NullPointerException.class, () -> new PL(null, "112345678-6",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaDniVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaDniNulo() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", null,
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaEmentaVazia() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaEmentaNula() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, null, "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaInteressesVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PLnconc", "", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaInteressesNulo() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PLnconc", null, "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaUrlVazia() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PLnconc", "saude", "", false));
    }

    @Test
    void criaPLNaoConclusivaUrlNula() {
        assertThrows(NullPointerException.class, () -> new PL("PL 1/2016", "112345678-6",
                2016, "Ementa PLnconc", "saude", null, false));
    }

    @Test
    void criaPLNaoConclusivaDniInvalidoComEspaco() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", " 112345678-6",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaDniInvalidoComLetra() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "A112345678-6",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaDniInvalidoComCaractereEspecial() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", ".112345678-6",
                2016, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaAnoPosteriorAoAtual() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                2021, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLNaoConclusivaAnoAnteriorA1988() {
        assertThrows(IllegalArgumentException.class, () -> new PL("PL 1/2016", "112345678-6",
                1980, "Ementa PLnconc", "saude", "http://example.com/semana_saude", false));
    }

    @Test
    void criaPLConclusivaDadosValidos() {
        this.projetoDeLei = new PL("PL 1/2016", "112345678-6", 2016, "Ementa PL conc",
                "saude", "http://example.com/semana_saude", true);
    }

    @Test
    void criaPLNaoConclusivaDadosValidos() {
        this.projetoDeLei = new PL("PL 1/2016", "112345678-6", 2016, "Ementa PLnconc",
                "saude", "http://example.com/semana_saude", false);
    }

    @Test
    void toStringPLConclusiva() {
        this.projetoDeLei = new PL("PL 1/2016", "112345678-6", 2016, "Ementa PL conc",
                "saude", "http://example.com/semana_saude", true);
        assertEquals("Projeto de Lei - PL 1/2016 - 112345678-6 - Ementa PL conc - Conclusiva - EM VOTACAO (CCJC)",
                this.projetoDeLei.toString());
    }

    @Test
    void toStringPLNaoConclusiva() {
        this.projetoDeLei = new PL("PL 1/2016", "112345678-6", 2016, "Ementa PLnconc",
                "saude", "http://example.com/semana_saude", false);
        assertEquals("Projeto de Lei - PL 1/2016 - 112345678-6 - Ementa PLnconc - EM VOTACAO (CCJC)",
                this.projetoDeLei.toString());
    }
}