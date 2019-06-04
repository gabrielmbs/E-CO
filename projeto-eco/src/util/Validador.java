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

    public void validaString(String string, String msg){
        validaStringNula(string, msg);
        validaStringVazia(string, msg);
    }

    public void validaDNI(String parametro, String mensagem) {
        String erroString = "dni nao pode ser vazio ou nulo";
        String erroInvalido = "dni invalido";
        validaString(parametro, mensagem + erroString);
        if (!parametro.matches("[0-9-]+")){
            throw new IllegalArgumentException(mensagem + erroInvalido);
        }
    }

    private static boolean ehBissexto(int ano){
        return ano % 4 == 0 && (ano % 400 == 0 || ano % 100 != 0);
    }

    private void validaDataInvalida(String data, String msg){
        String erro = "data invalida";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(msg + erro);
        }

        int dia = Integer.parseInt(data.substring(0,2));
        int mes = Integer.parseInt(data.substring(2,4));
        int ano = Integer.parseInt(data.substring(4));
        if(dia == 29 && mes == 2 && !ehBissexto(ano)){
            throw new IllegalArgumentException(msg + erro);
        }
    }

    private void validaDataFutura(String data, String msg){
        String erro = "data futura";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate dataDoSistema = LocalDate.now();
        LocalDate dataAtual = LocalDate.parse(data, formatter);
        if(dataDoSistema.isBefore(dataAtual)){
            throw new IllegalArgumentException(msg + erro);
        }
    }

    public void validaData(String data, String msg){
        String erroString = "data nao pode ser vazio ou nulo";
        validaString(data, msg + erroString);
        validaDataInvalida(data, msg);
        validaDataFutura(data, msg);
    }
}

