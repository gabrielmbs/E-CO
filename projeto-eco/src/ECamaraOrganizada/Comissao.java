package ECamaraOrganizada;

import java.util.Objects;

public class Comissao {
    private String[] DNIs;
    private String tema;

    public Comissao(String tema, String[] DNIs) {
        this.tema = tema;
        this.DNIs = DNIs;
    }

    public String[] getDNIs() {
        return DNIs;
    }

    public String getTema() {
        return tema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comissao comissao = (Comissao) o;
        return Objects.equals(tema, comissao.tema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tema);
    }
}
