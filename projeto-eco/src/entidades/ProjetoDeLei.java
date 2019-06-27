package entidades;

import util.Validador;

import java.io.Serializable;

/**
 * Representação de um Projeto de Lei, caracterizado pelo seu código de lei, dni de seu autor,
 * ementa, interesses e url, todos do tipo String,  ano do tipo int e conclusivo do tipo boolean.
 *
 */
public class ProjetoDeLei extends ProposicaoAbstract implements Serializable {

    /**
     * Atributo que será utilizado para validações.
     */
    private Validador validador;

    /**
     * Método responsável por criar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
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
    public ProjetoDeLei(String codigoLei, String dni, int ano, String ementa, String interesses, String url,
                        boolean conclusivo) {
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
        this.tipoDeProposicao = "PL";
    }

    /**
     * Retorna a representação em String do projeto. A representação
     * segue o formato "Projeto de Lei - Código - DNI - Ementa - Conclusivo - Situação".
     *
     * @return retorna a representação em String do projeto
     */
    public String toString() {
        if(this.conclusivo){
            return "Projeto de Lei" + super.toString() + "Conclusiva"  + " - " + this.situacao;
        }
        else return "Projeto de Lei" + super.toString() + this.situacao;
    }

    @Override
    public int calculaChao(int participantes) {
        return ((participantes / 2) + 1);
    }
}
