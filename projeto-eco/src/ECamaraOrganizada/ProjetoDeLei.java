package ECamaraOrganizada;

import util.Validador;

/**
 * Representação de um Projeto de Lei, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String,  ano do tipo int e conclusivo do tipo boolean.
 *
 */
public class ProjetoDeLei extends ProposicaoAbstract {
    /**
     * Atributo que denota o estado conclusivo do Projeto de Lei.
     */
    private boolean conclusivo;
    /**
     * Atributo que será utilizado para validações.
     */
    private Validador validador;

    /**
     * Método responsável por criar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * Ademais, checa-se se o dni passado é válido (composto apenas de números
     * no formato XXXXXXXXX-X, sendo cada X um valor de 0 a 9). Se não for, lança-se
     * uma exceção com uma mensagem indicando que o dni é inválido.
     *
     * Verifica-se ainda, se o ano passado é anterior à 1988 ou posterior a 2019. Se for,
     * IllegalArgumentException será lançado.
     *
     * Checa-se se cada um desses parâmetros são nulos ou vazios, e se forem, exceções do tipo NullPointerException
     * e IllegalArgumentExeception serão lançadas, respectivamente.
     *
     * Usa-se o construtor da classe ProposicaoAbstract para inicializar os atributos dni, ano, codigoLei, ementa,
     * interesses e url.
     *
     * @param codigoLei código da lei.
     * @param dni dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url endereço url do projeto.
     * @param ano ano de criacção do projeto
     * @param conclusivo situção conclusiva do projeto
     *
     */
    public ProjetoDeLei(String codigoLei, String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        super(dni, ano, codigoLei, ementa, interesses, url);
        this.validador = new Validador();
        this.validador.validaString(codigoLei,"Erro ao cadastrar projeto: codigo de lei nao pode ser vazio ou nulo");
        this.validador.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        this.validador.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        this.validador.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        this.validador.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        this.validador.validaDNI(dni, "Erro ao cadastrar projeto: ");
        this.validador.validaAnoLei(ano, "Erro ao cadastrar projeto: ");
        this.conclusivo = conclusivo;
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato "Projeto de Lei - Código - DNI - Ementa - Conclusivo - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    public String toString() {
        if (this.conclusivo) {
            return "Projeto de Lei" + " - " + this.codigoLei + " - " + this.dniAutor +
                    " - " + this.ementa + " - Conclusiva"  + " - " + this.situacao;
        }
        else return "Projeto de Lei" + super.toString() + this.situacao;
    }

}
