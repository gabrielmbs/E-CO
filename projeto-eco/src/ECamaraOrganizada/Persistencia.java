package ECamaraOrganizada;

import java.io.*;

/**
 * Classe responsavel pela armazenação e leitura de objetos em arquivos.
 */
public class Persistencia {

    /**
     * Nome padrão do diretorio.
     */
    private static final String NOME_DIRETORIO = "files";
    /**
     * Extensão padrão do arquivo a ser salvo.
     */
    public static final String EXTENSAO_ARQUIVO = ".txt";

    /**
     * Caminho do diretorio onde o arquivo será armazenado.
     */
    private final String caminhoDiretorio;

    /**
     * Cria uma instancia da classe Persistencia com nome do diretorio padrão;
     */
    public Persistencia() {
        this(NOME_DIRETORIO);
    }

    /**
     * Cria uma instacia da classe Pesistencia com o nome do diretorio passado por paramentro
     *
     * @param nomeDiretorio
     */
    public Persistencia(String nomeDiretorio) {
        this.caminhoDiretorio = nomeDiretorio + File.separator;
    }

    /**
     * Salva um objeto em um arquivo.
     *
     * @param obj         representa o objeto que vamos salvar no arquivo.
     * @param nomeArquivo representa o nome do arquivo que vai ser salvo objeto.
     */
    public void salvar(Object obj, String nomeArquivo) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(this.caminhoDiretorio + nomeArquivo + EXTENSAO_ARQUIVO);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Recupera o objeto, caso esteja, no arquivo recebindo como paramentro.
     *
     * @param nomeArquivo representa o nome do aquivo que vai ser recuperado o objeto.
     * @return o objeto que estava armazenado.
     */
    public Object carregar(String nomeArquivo) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object obj = null;

        try {
            fis = new FileInputStream(this.caminhoDiretorio + nomeArquivo + EXTENSAO_ARQUIVO);
            ois = new ObjectInputStream(fis);
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }

    /**
     * Limpa o arquivo passado por parametro, caso o arquivo não exista ele é criado vazio.
     *
     * @param nomeArquivo representa o nome do arquivo.
     */
    public void limpar(String nomeArquivo) {

        try {
            PrintWriter pw = new PrintWriter(new File(this.caminhoDiretorio + nomeArquivo + EXTENSAO_ARQUIVO));
            pw.close();
        } catch (IOException e) {
            System.out.println("erro : " + e);
        }

    }
}
