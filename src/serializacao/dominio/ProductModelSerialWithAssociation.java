package serializacao.dominio;

import estruturaDados.models.PatientModel;

import java.io.*;

public class ProductModelSerialWithAssociation implements Serializable {

    @Serial
    private static final long serialVersionUID = 5952629333466625575L;
    private String nome;
    private double preco;
    private int quantidade;
    private transient PatientModel patientModelTransient;

    public ProductModelSerialWithAssociation(String nome, double preco, int quantidade, PatientModel patientModelTransient) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.patientModelTransient = patientModelTransient; // hidden propriety in serialized context
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PatientModel getPatientModelTransient() {
        return patientModelTransient;
    }

    public void setPatientModelTransient(PatientModel patientModelTransient) {
        this.patientModelTransient = patientModelTransient;
    }

    @Override
    public String toString() {
        return "ProductModelSerialWithAssociation{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", patientModelTransient=" + patientModelTransient +
                '}';
    }
}
