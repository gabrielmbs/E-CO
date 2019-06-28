package util;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe responsável por toda a validação no sistema.
 */
public class Validador implements Serializable {

    /**
     * Método responsável por fazer todas as validações de data de uma só vez.
     *
     * @param data data a ser analisada.
     * @param msg mensagem de erro que será lançada.
     */
    public void validaData(String data, String msg){
        String erroString = "data nao pode ser vazio ou nulo";
        validaString(data, msg + erroString);
        validaDataInvalida(data, msg);
        validaDataFutura(data, msg);
    }
    /**
     * Método responsável por validar um ano de criação
     * de um projeto de lei.
     *
     * @param ano ano a ser analisado.
     * @param msg mensagem de erro que será lançada.
     */
    public void validaAnoLei(int ano, String msg){
        int anoAtual = Year.now().getValue();
        String msgAnoPosteriorAnoAtual = "ano posterior ao ano atual";
        String msgAnoAnterior1988 = "ano anterior a 1988";
        if (ano > anoAtual ){
            throw new IllegalArgumentException(msg + msgAnoPosteriorAnoAtual);
        }
        else if (ano < 1988){
            throw new IllegalArgumentException(msg + msgAnoAnterior1988);

        }
    }

    /**
     * Método que verifica se a String passada como parâmetro é nula ou vazia de uma vez só.
     *
     * @param string String a ser checada se é nula ou vazia.
     * @param msg mensagem de erro que será lançada.
     */
    public void validaString(String string, String msg){
        validaStringNula(string, msg);
        validaStringVazia(string, msg);
    }

    /**
     * Método responsável por validar um dni inválido.
     *
     * @param dni dni a ser verificado.
     * @param mensagem mensagem de erro que será lançada caso o dni seja inválido.
     */
    public void validaDNI(String dni, String mensagem) {
        String erroString = "dni nao pode ser vazio ou nulo";
        String erroInvalido = "dni invalido";
        validaString(dni, mensagem + erroString);
        if (!dni.matches("[0-9-]+")){
            throw new IllegalArgumentException(mensagem + erroInvalido);
        }
    }

    /**
     * Método que verifica se um ano é bissexto.
     *
     * @param ano ano a ser verificado.
     * @return boolean informando se o ano é (ou não) bissexto.
     */
    private static boolean ehBissexto(int ano){
        return ano % 4 == 0 && (ano % 400 == 0 || ano % 100 != 0);
    }

    /**
     * Método responsável por validar uma data inválida.
     *
     * @param data data a ser analisada.
     * @param msg mensagem de erro que será lançada.
     */
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

    /**
     * Método responsável por validar uma data futura.
     *
     * @param data data a ser analisada.
     * @param msg mensagem de erro que será lançada.
     */
    private void validaDataFutura(String data, String msg){
        String erro = "data futura";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate dataDoSistema = LocalDate.now();
        LocalDate dataAtual = LocalDate.parse(data, formatter);
        if(dataDoSistema.isBefore(dataAtual)){
            throw new IllegalArgumentException(msg + erro);
        }
    }

    /**
     * Método que verifica se uma String é nula e lança uma exceção caso seja.
     *
     * @param parametro parametro a ser checado se é nulo.
     * @param mensagem mensagem de erro que será lançada.
     */
    private void validaStringNula(String parametro, String mensagem) {
        if (parametro == null) {
            throw new NullPointerException(mensagem);
        }
    }

    /**
     * Método que verifica se uma String é vazia e lança uma exceção caso seja.
     *
     * @param parametro parametro a ser checado se é vazia.
     * @param mensagem mensagem de erro que será lançada.
     */
    private  void validaStringVazia(String parametro, String mensagem) {
        if ("".equals(parametro.trim())) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public void validaProjeto(String dni, int ano, String ementa, String interesses, String url) {
        this.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validaAnoLei(ano, "Erro ao cadastrar projeto: ");
    }
}

