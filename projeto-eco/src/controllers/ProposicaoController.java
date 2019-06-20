package controllers;

import entidades.*;
import util.Contador;
import util.Validador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposicaoController {

    /**
     * Representa mapa onde se armazena as contagens específicas de cada tipo de lei em consonância
     * com seu ano de cadastro, com a chave no formato: "tipoDeLei AnoCadastro".
     *
     * Permite o acompanhamento mais preciso da quantidade de um tipo específico de leis
     * cadastradas em um ano.
     */
    private Map<String, Contador> contadores;

    /**
     * Representa mapa onde se armazena as proposições de lei armazenadas no sistema,
     * com a chave no formato: "tipoDeLei ordemContagem/AnoDeCadastro".
     */
    private Map<String, ProposicaoAbstract> proposicoesDeLeis;

    /**
     * Atributo que será utilizado para validacoes.
     */
    private Validador validador;

    public ProposicaoController() {
        this.proposicoesDeLeis = new HashMap<>();
        this.validador = new Validador();
        this.contadores = new HashMap<>();
    }

    /**
     * Método responsável por cadastrar um Projeto de Lei no sistema, cujos dados: dni,
     * ementa, interesses e url, todos do tipo String, ano do tipo int e
     * conclusivo do tipo boolean, são passados como parâmetro.
     *
     * @param dni dni do autor do projeto.
     * @param ementa ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url endereço url do projeto.
     * @param ano ano de criação do projeto.
     * @param conclusivo situção conclusiva do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {

        String chaveContador = ano + "PL";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PL " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new ProjetoDeLei(codigoLei, dni, ano, ementa, interesses, url,
                conclusivo));
        contador.incrementaContagem();
        return codigoLei;
    }

    /**
     * Método responsável por cadastrar um Projeto de Lei Complementar no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni dni da pessoa deputada autor da lei.
     * @param ementa ementa da lei.
     * @param interesses interesses da lei.
     * @param url endereço url da lei.
     * @param artigos artigos da constituição sobre os quais a lei vai atuar.
     * @param ano ano de criação do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        String artigosConcatenados = contatenaArtigos(artigos);

        String chaveContador = ano + "PLP";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PLP " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new ProjetoLeiComplementar(codigoLei, dni, ano, ementa,
                interesses, url,
                artigosConcatenados));
        contador.incrementaContagem();
        return codigoLei;
    }

    /**
     * Método responsável por cadastrar um Projeto de Emenda Constituicional no sistema, cujos dados: dni,
     * ementa, interesses, url e artigos, todos do tipo String e ano do tipo int
     * são passados como parâmetro.
     *
     * @param dni dni da pessoa deputada autor da lei.
     * @param ementa ementa da lei.
     * @param interesses interesses da lei.
     * @param url endereço url da lei.
     * @param artigos artigos da constituição sobre os quais a lei vai atuar.
     * @param ano ano de criação do projeto
     *
     * @return retorna o código da lei cadastrada.
     */
    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        String artigosConcatenados = contatenaArtigos(artigos);
        String chaveContador = ano + "PEC";
        if (!this.contadores.containsKey(chaveContador)) {
            this.contadores.put(chaveContador, new Contador());
        }
        Contador contador = this.contadores.get(chaveContador);
        String codigoLei = "PEC " + contador.getContagem() + "/" + ano;
        this.proposicoesDeLeis.put(codigoLei, new ProjetoEmendaConstitucional(codigoLei, dni, ano, ementa, interesses,
                                    url, artigosConcatenados));
        contador.incrementaContagem();
        return codigoLei;
    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta em uma determinada comissão, ele recebe como
     * parâmetro o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e o próximo
     * local no qual a porposta será votada.
     *
     * @param codigo código da proposta.
     * @param statusGovernista status da proposta.
     * @param proximoLocal próximo locla no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal, int chao,
                                 int votosFavoraveis, Pessoa autor) {
        if(this.proposicoesDeLeis.get(codigo).isConclusivo()){
            return votarComissaoPLConc(codigo, votosFavoraveis, chao, proximoLocal, autor);
        }
        return votarComissaoNConc(codigo, votosFavoraveis, chao, proximoLocal);

    }

    /**
     * Esse método é responsável por realizar a votação de uma proposta no plenário, ele recebe como parâmetro
     * o código da proposta a ser votada, o status da proposta (GOVERNISTA, OPOSICAO ou LIVRE), e os presentes
     * no plenário (DNIs separados por vírgula), todos do tipo String. O método retorna um boolena que indica
     * o resultado da votação.
     *
     * @param codigo código da proposta.
     * @return um boolean que indica o resultado da votação.
     */
    public boolean votarPlenario(String codigo, String[] deputados, Pessoa deputado,
                                 int votosFavoraveis, int totalDeputados) {
        if(!this.proposicoesDeLeis.get(codigo).getPassouNaCCJC()){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }
        if(!this.proposicoesDeLeis.get(codigo).getProposicaoAtiva()){
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
        int chao = 0;
        boolean retorno = false;
        if ("PL".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao()) &&
                !this.proposicoesDeLeis.get(codigo).isConclusivo()) {
            chao = (deputados.length / 2) + 1;
            this.proposicoesDeLeis.get(codigo).setProposicaoAtiva(false);
            if (votosFavoraveis >= chao) {
                retorno = true;
                this.proposicoesDeLeis.get(codigo).atualizaTramitacaoLei("APROVADO (Plenario)");
                deputado.getFuncao().incrementaNumeroDeLeis();
            }
            this.proposicoesDeLeis.get(codigo).atualizaTramitacaoLei("REJEITADO (Plenario)");

        } else if ("PLP".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (totalDeputados / 2) + 1;
            retorno = aprovadaOuArquivada(codigo, votosFavoraveis, chao, deputado);
        } else if ("PEC".equals(this.proposicoesDeLeis.get(codigo).getTipoDeProposicao())) {
            chao = (((3/5) * totalDeputados) / 2) + 1;
            retorno = aprovadaOuArquivada(codigo, votosFavoraveis, chao,deputado);
        }
        return retorno;
    }

    /**
     * Método que verifica se um projeto de lei existe no sistema.
     *
     * @param codigo codigo da lei a ser procurado.
     * @return boolean informando se o projeto existe ou não.
     */
    public boolean existeLei(String codigo) {
        return this.proposicoesDeLeis.containsKey(codigo);
    }

    public ProposicaoAbstract buscaProposicao(String codigo){
        return this.proposicoesDeLeis.get(codigo);
    }

    /**
     * Esse método auxiliar é responsável por realizar a votação de uma PL não conclusiva
     * em uma determinada comissão, ele retorna um boolean que indica o resultado da
     * votação.
     *
     * @param codigo código da proposta a ser votada.
     * @param votosFavoraveis votos favoráveis à aprovação da proposta.
     * @param chao votos mínimos necessários para aprovação da proposta.
     * @param proximoLocal próximo local no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean votarComissaoNConc(String codigo, int votosFavoraveis, int chao, String proximoLocal){
        ProposicaoAbstract proposicao = buscaProposicao(codigo);
        if ("plenario".equals(proximoLocal)) {
            proposicao.setSituacao("EM VOTACAO (Plenario - 1o turno)");
        }
        boolean retorno = false;
        if(votosFavoraveis >= chao){
            retorno = true;
            proposicao.atualizaTramitacaoLei("APROVADO (" + proposicao.getLocalDeVotacao() + ")");
        }
        proposicao.setLocalDeVotacao(proximoLocal);
        if(proposicao.getLocalDeVotacao().equals("plenario")){
            proposicao.atualizaTramitacaoLei("EM VOTACAO (Plenario)");
        }
        else proposicao.atualizaTramitacaoLei("EM VOTACAO (" + proposicao.getLocalDeVotacao() + ")");
        proposicao.setPassouNaCCJC(true);
        return retorno;
    }

    /**
     * Esse método auxiliar é responsável por realizar a votação de uma PL conclusiva
     * em uma determinada comissão, ele retorna um boolean que indica o resultado da
     * votação.
     *
     * @param codigo código da proposta a ser votada.
     * @param votosFavoraveis votos favoráveis à aprovação da proposta.
     * @param chao votos mínimos necessários para aprovação da proposta.
     * @param proximoLocal próximo local no qual a proposta será votada.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean votarComissaoPLConc(String codigo, int votosFavoraveis, int chao, String proximoLocal, Pessoa autor) {
        boolean retorno = false;
        ProposicaoAbstract proposicao = buscaProposicao(codigo);
        if (votosFavoraveis < chao && !proposicao.getPassouNaCCJC()) {
            proposicao.setProposicaoAtiva(false);
            proposicao.setPassouNaCCJC(true);
            proposicao.atualizaTramitacaoLei("REJEITADO (" + proposicao.getLocalDeVotacao() +")");
        }else if(votosFavoraveis >= chao && !proposicao.getPassouNaCCJC()){
            proposicao.setPassouNaCCJC(true);
            proposicao.setSituacao("EM VOTACAO (" + proximoLocal + ")");
            proposicao.atualizaTramitacaoLei("APROVADO (" + proposicao.getLocalDeVotacao() + ")");
            proposicao.setLocalDeVotacao(proximoLocal);
            proposicao.atualizaTramitacaoLei("EM VOTACAO (" + proposicao.getLocalDeVotacao() + ")");
            retorno = true;
        }else if(votosFavoraveis >= chao){
            if(proximoLocal.equals("-")){
                proposicao.setSituacao("APROVADO");
                proposicao.atualizaTramitacaoLei("APROVADO (" + proposicao.getLocalDeVotacao() +")");
                autor.getFuncao().incrementaNumeroDeLeis();
                proposicao.setProposicaoAtiva(false);
            }
            retorno = true;
        }else if(votosFavoraveis < chao){
            proposicao.setProposicaoAtiva(false);
            if(proximoLocal.equals("-")){
                proposicao.setSituacao("ARQUIVADO");
                proposicao.atualizaTramitacaoLei("REJEITADO (" + proposicao.getLocalDeVotacao()+")");

            }
        }
        return retorno;
    }

    /**
     * Esse método auxiliar retorna um boolean que informa se uma PLP ou uma PEC foi Aprovada
     * ou Arquivada no Plenário. Além disso ele atualiza o atributo númeroDeLeis do deputado e
     * atualiza o atributo situação da proposição.
     *
     * @param codigo código da proposição a ser votada.
     * @param votosFavoraveis votos à favor da aprovação.
     * @param chao mínimo de votos necessário para aprovar a proposição.
     * @return um boolean que indica o resultado da votação.
     */
    private boolean aprovadaOuArquivada(String codigo, int votosFavoraveis, int chao, Pessoa deputado) {
        boolean retorno = false;
        ProposicaoAbstract proposicao = buscaProposicao(codigo);
        if (votosFavoraveis >= chao) {
            retorno = true;
            if (proposicao.getPassouNoPlenario()) {
                buscaProposicao(codigo).setSituacao("APROVADO");
                deputado.getFuncao().incrementaNumeroDeLeis();
                proposicao.setProposicaoAtiva(false);
            } else {
                proposicao.setSituacao("EM VOTACAO (Plenario - 2o turno)");
                proposicao.setPassouNoPlenario(true);
            }
        } else {
            proposicao.setSituacao("ARQUIVADO");
            proposicao.setProposicaoAtiva(false);

        }
        return retorno;
    }

    /**
     * Método responsável por separar artigos com vírgula e espaço, como no
     * formato a seguir: "artigo1, artigo2, ..., artigoN".
     * @param artigos String base para geração da nova string dos artigos concatenados.
     * @return
     */
    private String contatenaArtigos(String artigos){
        if(artigos.contains(",")){
            String artigosConcatenados = "";
            for(String caractere : artigos.split(",")){
                artigosConcatenados += caractere + ", ";

            }
            return artigosConcatenados.substring(0, artigosConcatenados.length() - 2);
        }
        return artigos;
    }

    public String exibirTramitacao(String codigo) {
        List<String> tramitacao = this.proposicoesDeLeis.get(codigo).getTramitacao();

        String separador = ", ";
        String tramitacaoFormatada = String.join(separador, tramitacao) + ".";
        return tramitacaoFormatada;
    }
}