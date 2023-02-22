package serializacao.dominio;

// File -> Settings -> Editor -> Inspections -> Java -> Serialization issues -> Serializable class without 'serialVersionUID' - set flag and click 'OK'.
// Alt + Enter in class name and select context action "Create constant field..."

// O token transient é utilizado para ocultar/ignorar campos na hora de serializar.

import java.io.Serial;
import java.io.Serializable;

public class ProductModelSerial implements Serializable {
    @Serial
    private static final long serialVersionUID = 2470479391015028350L;
    private String nome;
    private double preco;
    private int quantidade;

    private transient String transientPropriety = "Hidden"; // garante que estado/valor não irá ser salvo ao serializar

    public ProductModelSerial(String nome, double preco, int quantidade, String transientPropriety) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.transientPropriety = transientPropriety; // hidden propriety in serialized context
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

    public String getTransientPropriety() {
        return transientPropriety;
    }

    public void setTransientPropriety(String transientPropriety) {
        this.transientPropriety = transientPropriety;
    }

    @Override
    public String toString() {
        return "ProductModelSerial{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", transientPropriety='" + transientPropriety + '\'' +
                '}';
    }
}
