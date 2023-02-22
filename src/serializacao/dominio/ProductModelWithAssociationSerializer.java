package serializacao.dominio;

import estruturaDados.models.PatientModel;

import java.io.*;

public class ProductModelWithAssociationSerializer implements Serializable {
    @Serial
    private static final long serialVersionUID = 4452595238297229661L;

    public static void writeObject(ObjectOutputStream out, ProductModelSerialWithAssociation produto) throws IOException {
        out.writeUTF(produto.getNome());
        out.writeDouble(produto.getPreco());
        out.writeInt(produto.getQuantidade());

        // Apartir daqui devemos escrever atributo por atributo deste objeto referenciado
        // que desejamos armazenar (a depender dos tipos utilizar o método correspondente
        // neste caso é apenas uma String então utilizamos o writeUTF()
        out.writeObject(produto.getPatientModelTransient());
        System.out.println("Write object correct");
    }

    public static void readObject(ObjectInputStream in, ProductModelSerialWithAssociation produto) throws IOException, ClassNotFoundException {
        produto.setNome(in.readUTF());
        produto.setPreco(in.readDouble());
        produto.setQuantidade(in.readInt());

        produto.setPatientModelTransient(new PatientModel(((PatientModel)in.readObject()).getNome(),
                                                            ((PatientModel)in.readObject()).getPriority()));
        System.out.println("Read object correct");
    }
}
