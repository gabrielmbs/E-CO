package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador {
    private void validaStringNula(String parametro, String mensagem) {
        if (parametro == null) {
            throw new NullPointerException(mensagem);
        }
    }

    private  void validaStringVazia(String parametro, String mensagem) {
        if (parametro.trim().equals("")) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static void validaString(String string, String msg){
        validaStringNula(string, msg);
        validaStringVazia(string, msg);
    }

    public static void validaDNI(String parametro, String mensagem) {
        if (!parametro.matches("[0-9-]+")){
            throw new IllegalArgumentException(mensagem);
        }
    }

    private static boolean ehBissexto(int ano){
        return ano % 4 == 0 && (ano % 400 == 0 || ano % 100 != 0);
    }

    public static void validaDataInvalida(String data, String msg){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(msg);
        }

        int dia = Integer.parseInt(data.substring(0,2));
        int mes = Integer.parseInt(data.substring(2,4));
        int ano = Integer.parseInt(data.substring(4));
        if(dia == 29 && mes == 2 && !ehBissexto(ano)){
            throw new IllegalArgumentException(msg);
        }
    }

    public static void validaDataFutura(String data, String msg){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate dataDoSistema = LocalDate.now();
        LocalDate dataAtual = LocalDate.parse(data, formatter);
        if(dataDoSistema.isBefore(dataAtual)){
            throw new IllegalArgumentException(msg);
        }
    }
}

