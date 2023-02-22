package serializacao;

// Serialização em Java é o processo de transformar um objeto em uma sequência de bytes
// que podem ser armazenados ou transmitidos pela rede, e posteriormente reconstruídos
// em um objeto equivalente. Isso permite que o objeto seja persistido em um arquivo,
// banco de dados ou enviado pela rede para outro sistema, e depois reconstruído em sua forma original.
//
//A serialização é frequentemente usada em Java para permitir que objetos sejam transferidos
// entre diferentes aplicativos ou sistemas, sem perder o estado e as propriedades. Por exemplo,
// um objeto que representa um usuário em um sistema pode ser serializado e transmitido para
// outro sistema onde pode ser reconstruído para fins de autenticação.
//
//Para serializar um objeto em Java, é necessário que a classe do objeto implemente a Interface Serializable.
// A interface Serializable não contém nenhum método, é apenas uma marcação que indica ao Java que a classe
// pode ser serializada. Depois disso, pode-se usar a classe ObjectOutputStream para escrever o objeto em
// um fluxo de saída, e a classe ObjectInputStream para ler o objeto de volta em sua forma original.
//
//A serialização em Java é uma técnica poderosa que permite a transferência de objetos complexos entre
// diferentes sistemas. No entanto, é importante lembrar que a serialização pode ser afetada por problemas
// de compatibilidade, pois a estrutura do objeto serializado pode mudar com o tempo. Portanto, é importante
// considerar as implicações da serialização em projetos de longa duração e com várias versões.

// IMPORTANTE: Ao DESERIALIZAR um Objeto o método construtor não será executado!! para tal é necessário um macete.

//                                                     ┌───────────┐
//                                                     │  Object   │
//                                                     └──────┬────┘
//                                                            │
//            ┌───────────────────────────────────────────────┼───────────────────────────────────────────────────┐
//            │                                               │                                                   │
//      OutputStream                                    InputStream                                         PrintStream
//            │                                               │                                                   │
//   ┌───────────┴───────┐                           ┌───────────┴───────┐                           ┌────────────┴─────────────┐
//   │                   │                           │                   │                           │                  │
//  FilterOutputStream BufferedOutputStream    PipedInputStream   FileInputStream   FileOutputStream     PrintStream   FilterInputStream
//   │                   │                           │                   │                          │                  │
//  BufferedInputStream ObjectInputStream   SequenceInputStream PushbackInputStream  FilterInputStream    ByteArrayInputStream  BufferedOutputStream
//   │                   │                           │                   │                          │                  │
//  DataInputStream StringBufferInputStream   FilterInputStream   LineNumberInputStream                   DataOutputStream  PrintStream
//   │                                                                                                    │
//  ObjectOutputStream                                                                            CheckedOutputStream
//   │                                                                                                    │
//  ByteArrayOutputStream                                                                       DeflaterOutputStream
//   │                                                                                                    │
//                                                                                             GZIPOutputStream
import jdk.jshell.execution.Util;
import serializacao.dominio.ProductModelSerial;
import serializacao.dominio.ProductModelSerialWithAssociation;
import serializacao.dominio.ProductModelWithAssociationSerializer;
import serializacao.dominio.ProdutoModel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula159Serialization_pt01<T> {
    public static void main(String[] args) {

        // Para serializar um objeto, o mesmo deve implementar a Interface Serializable.
        ProdutoModel productEntity = new ProdutoModel("Paçoka", 10.50, 10);
        Path pathObjSerialized = Paths.get("src/serializacao/product.ser");
        Path productSerealizedOutputpath = Paths.get("src/serializacao/product_fileSpecialized.ser");
        Path productSerealizedOutputpathGenerics = Paths.get("src/serializacao/product_fileSpecializedGenerics.ser");

        // Abordagem básica vista na aula
        serialized(productEntity);
        deserialized();

        // Abordagem com melhor desempenho utilizando ByteArray antes de salvar o objeto
        // Garantindo assim a estrutura de dados que sera salvo, visando POLIMORFISMO em diferentes S.O's
        // com auxílio do ChatGPT
        serializedFileSpecialized(productEntity, productSerealizedOutputpath);
        ProdutoModel productModel = deserializedFileSpecialized(productSerealizedOutputpath, ProdutoModel.class);
        System.out.println("Com genérics: " + productModel);

        ProductModelSerial productModelSerial = new ProductModelSerial("Pipokinha", 10, 10, "vazio");
        serializedFileSpecialized(productModelSerial, productSerealizedOutputpathGenerics);
        ProductModelSerial productModelSerialGenerics = deserializedFileSpecialized(productSerealizedOutputpathGenerics, ProductModelSerial.class);
        System.out.println(productModelSerialGenerics);
    }

    public static void serialized(ProdutoModel product){
        Path productSerealizedOutputpath = Paths.get("src/serializacao/product.ser");
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(productSerealizedOutputpath))) {
            oos.writeObject(product); // se fosse um String seria writeString();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deserialized(){
        Path productSerealizedOutputpath = Paths.get("src/serializacao/product.ser");
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(productSerealizedOutputpath))) {
            ProdutoModel obj = (ProdutoModel) ois.readObject();// se fosse um String seria writeString();
            System.out.println(obj);
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> void serializedFileSpecialized(T product, Path outputPath){

        // Abordagem utilizando Buffer ou seja, maior performance
        // Além de transformar o objeto em um ByteArray antes de salva-lo em um arquivo
        // Garantindo a estrutura de dados antes de salvar, para assim
        // melhor se comportar diante de diferentes S.O's



        try (FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             FileChannel fileChannel = fos.getChannel()) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            // Obs: Ao passar um objByteArrayOutputStream para o construtor do ObjectOutputStream
            // faz a saída padrão do fluxo apontar para um array em memória, antes de salvar
            // em um arquivo, porisso o ganho de performance.
            // E porisso utilizamos dois write, um para escrever em um array na RAM
            // E por fim o fileChannel para salvar em memória HD

            // Escreve o objeto serializado no ByteArrayOutputStream
            ProductModelWithAssociationSerializer.writeObject(oos, (ProductModelSerialWithAssociation) product);

            // Fecha o ObjectOutputStream para liberar recursos
            oos.close();

            oos.writeObject(product);

            ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            fileChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T deserializedFileSpecialized(Path pathObjSerialized, Class<T> clazz){

        try (FileInputStream fis = new FileInputStream(pathObjSerialized.toFile());
             FileChannel fileChannel = fis.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(buffer);
            buffer.flip();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);

            Object savedProduct = ois.readObject();
            if (clazz.isInstance(savedProduct)) {
                return clazz.cast(savedProduct);
            } else {
                throw new IllegalArgumentException("The object in the file is not of the expected class.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
