package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Autenticador {
    private static void validaStringNula(String parametro, String mensagem) {
        if (parametro == null) {
            throw new NullPointerException(mensagem);
        }
    }

    private static void validaStringVazia(String parametro, String mensagem) {
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

    public static void validaData(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate d = LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Erro ao cadastrar deputado: data invalida");
        }

        int dia = Integer.parseInt(data.substring(0,2));
        int mes = Integer.parseInt(data.substring(2,4));
        int ano = Integer.parseInt(data.substring(4));
        if(dia == 29 && mes == 2 && !ehBissexto(ano)){
            throw new IllegalArgumentException("Erro ao cadastrar deputado: data invalida");
        }

        LocalDate dataDoSistema = LocalDate.now();
        LocalDate dataAtual = LocalDate.parse(data, formatter);
        if(dataDoSistema.isBefore(dataAtual)){
            throw new IllegalArgumentException("Erro ao cadastrar deputado: data futura");
        }
    }
}

