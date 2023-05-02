package H_serializacao.dominio;

// Devemos ler atributo por atributo e atualizar a classe original,
// não podemos utilizar o deafultReadObject nem o defaultWriteObject
// pois estamos fora da classe original!

// então devemos ler e escrever utilizando os métodos readInt writeInt
// correspondentes, e na oredem nos quais foram lidos e escritos
// pois os valores são empilhados na stack

import ZA_estruturaDados.models.PatientModel;

import java.io.*;

public class ProductModelWithAssociationSerializer implements Serializable {

    @Serial
    private static final long serialVersionUID = 4452595238297229661L;

    @Serial
    public static void writeObject(ObjectOutputStream out, ProductModelSerialWithAssociation produto) throws IOException {
        // correspondente ao defaultWriteObject porém como esta sendo utilizado está classe auxiliar
        // devemos escrever atributo por atributo
        // aqui escreve os atributos básicos sem o objeto de referência transient
        out.writeUTF(produto.getNome());
        out.writeDouble(produto.getPreco());
        out.writeInt(produto.getQuantidade());

        // Escrever os atributos do objeto referenciado transient
        out.writeUTF(produto.getPatientModelTransient().getNome());
        out.writeInt(produto.getPatientModelTransient().getPriority());

        System.out.println("Write object correct serializer");
    }

    @Serial
    public static void readObject(ObjectInputStream in, ProductModelSerialWithAssociation produto) throws IOException, ClassNotFoundException {
        System.out.println("raed auxiliar antes da execução");
        produto = new ProductModelSerialWithAssociation(in.readUTF(),
                                                        in.readDouble(),
                                                        in.readInt(),
                                                        new PatientModel(in.readUTF(),
                                                                         in.readInt()));

        System.out.println("Read object correct serializer");
    }
}
