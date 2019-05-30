package util;

public class Autenticador {
    public static void validaStringNula(String parametro, String mensagem) {
        if (parametro == null) {
            throw new NullPointerException(mensagem);
        }
    }

    public static void validaStringVazia(String parametro, String mensagem) {
        if (parametro.trim().equals("")) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static void validaDNI(String parametro) {
        if (!parametro.matches("[0-9-]+")){
            throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni invalido");
        }
    }
}

