package ECamaraOrganizada;

import util.Autenticador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Base {
    private Set<String> base;

    public Base(){
        this.base = new HashSet<>();
    }

    public void cadastrarPartido(String partido) {
        Autenticador.validaString(partido, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
        this.base.add(partido);
    }

    public String exibirBase() {
        List<String> listaPartidos = new ArrayList<>(this.base);
        listaPartidos.sort(String::compareTo);

        if (listaPartidos.size() == 0) {
            return "";
        }

        String resultado = "";
        for (String partido : listaPartidos) {
            resultado += partido + ",";
        }

        return resultado.substring(0, (resultado.length() - 1));
    }

    public Set<String> getBase() {
        return base;
    }

}
