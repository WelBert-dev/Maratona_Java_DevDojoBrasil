package serializacao;

// Todos os objetos serializados possuem um atributo psf long serialVersionUID
// Que garante o estádo de um objeto, em casos de não explicitar erros
// com mudanças de estádos podem ocorrer, uma vez que o Java vai se encarregar
// de alterar esse id toda vez que recompilar, porisso é necessário definir
// esse atributo de maneira estática e default (porisso uma constante final)

// Obs: Além de se atentar com versionamento com possíveis errors ao alterar
// Estrutura de objetos e tentar reculperar objetos antigos não mais compatíveis.

// Atenção: Atributos Estáticos pertencentes a classe não tem o seu valor serializado
// apenas os atributos relacionados ao Objeto em sí!
// em casos de serializar objetos que em suas classes possuem estáticos,
// ao deserializar o comportamento/valor desses se mantém ao definido na classe.

// Atenção2: Associação ou seja quando possuímos como atributos Objetos e desejamos
// serializar, é necessário ou a classe deste Objeto também implementar a Interface Serializable
// ou se não for possível devemos indicar ao Java essas configurações.
// Para isto iremos escrever o método @Serial pv writeObject() e/ou  pv readObject() na classe
// que faz referência, além disto devemos utilizar o token transient no atributo do Objeto referenciado,
// e nele setar as informações descritas abaixo. (./dominio/ProductModelSerialWithAssociation.java)

import estruturaDados.models.PatientModel;
import serializacao.dominio.ProductModelSerial;
import serializacao.dominio.ProductModelSerialWithAssociation;
import serializacao.dominio.ProductModelWithAssociationSerializer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula160Serialization_pt02_serialVersionUID {
    public static void main(String[] args) {
        ProductModelSerial serialProduct = new ProductModelSerial("SerializadoComVersion", 10, 100, "vazio");
        Path productSerealizedOutputpath = Paths.get("src/serializacao/product_fileSpecializedGenerics02.ser");

//        Aula159Serialization_pt01.serializedFileSpecialized(serialProduct, productSerealizedOutputpath);
//        ProductModelSerial productModelSerial = Aula159Serialization_pt01.deserializedFileSpecialized(productSerealizedOutputpath, ProductModelSerial.class);
//        System.out.println(productModelSerial.getTransientPropriety()); // null (transient propriety)


        PatientModel patientModel = new PatientModel("Irinéia", 1);

        // Serialização com associação (Atributos que referênciam objetos):
        ProductModelSerialWithAssociation productModelSerialWithAssociation =
                new ProductModelSerialWithAssociation("Irineu", 10, 10,
                        patientModel); // <- objeto referenciado que ira serializar junto.

        // serializa:
        Path productWithAssociationSerialPath =
                Paths.get("src/serializacao/product_fileSpecializedGenericsWithAssociation.ser");
        Aula159Serialization_pt01.serializedFileSpecialized(productModelSerialWithAssociation,
                                                            productWithAssociationSerialPath);

        // deserializa:
        ProductModelSerialWithAssociation productModelSerialWithAssociationDeserializado =
                Aula159Serialization_pt01.deserializedFileSpecialized(productWithAssociationSerialPath,
                                                                      ProductModelSerialWithAssociation.class);

        System.out.println("Serializado: " + productModelSerialWithAssociation);
        System.out.println("Deserializado: " + productModelSerialWithAssociationDeserializado);

        // verifica se o objeto deserializado corresponde com o objeto serializado:

        // objeto original antes de serializar:
        boolean isEquals = productModelSerialWithAssociationDeserializado.equals(productModelSerialWithAssociation);
        if (isEquals) {
            System.out.println("Objetos iguais!!");
        }else {
            System.out.println("Objetos diferentes! ;-;");
        }
    }
}
