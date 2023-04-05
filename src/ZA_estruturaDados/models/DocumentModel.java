package ZA_estruturaDados.models;

public class DocumentModel {

    public DocumentModel() {}

    public DocumentModel(String nome, int paperNum) {
        this.nome = nome;
        this.paperNum = paperNum;
    }

    private String nome;
    private int paperNum;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(int paperNum) {
        this.paperNum = paperNum;
    }
}
