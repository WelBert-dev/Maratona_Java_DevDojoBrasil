package H_serializacao.dominio;

import ZA_estruturaDados.models.PatientModel;

import java.io.*;
import java.util.Objects;

public class ProductModelSerialWithAssociation implements Serializable {

    @Serial
    private static final long serialVersionUID = -3917627648361167277L;
    private String nome;
    private double preco;
    private int quantidade;
    private transient PatientModel patientModelTransient;

    public ProductModelSerialWithAssociation() {
    }
    public ProductModelSerialWithAssociation(String nome, double preco, int quantidade, PatientModel patientModelTransient) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.patientModelTransient = patientModelTransient; // hidden propriety in serialized context
    }

    @Serial
    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Apartir daqui devemos escrever atributo por atributo deste objeto referenciado
        // que desejamos armazenar (a depender dos tipos utilizar o método correspondente
        // neste caso é apenas uma String então utilizamos o writeUTF()
        out.writeUTF(this.patientModelTransient.getNome());
        out.writeInt(this.patientModelTransient.getPriority());
        System.out.println("Write object correct");
    }

    @Serial
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("read default antes da execução");
        in.defaultReadObject();

        this.setPatientModelTransient(new PatientModel(in.readUTF(), in.readInt()));
        System.out.println("Read object correct");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModelSerialWithAssociation that = (ProductModelSerialWithAssociation) o;
        
        boolean isEqualsPreco = Double.compare(that.preco, preco) == 0;
        boolean isEqualsQtde = quantidade == that.quantidade;
        boolean isEqualsNome = Objects.equals(nome, that.nome);
        boolean isEqualsTransientProperty = Objects.equals(patientModelTransient, that.patientModelTransient);

        System.out.println("Compara preço: " + isEqualsPreco);
        System.out.println("Compara quantidade: " + isEqualsQtde);
        System.out.println("Compara nome: " + isEqualsNome);
        System.out.println("Compara objeto transient referenciado: " + isEqualsTransientProperty);

        return isEqualsPreco && isEqualsQtde && isEqualsNome && isEqualsTransientProperty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, quantidade, patientModelTransient);
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
