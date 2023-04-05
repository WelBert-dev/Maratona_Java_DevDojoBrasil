package F_io;

// Após carregarmos o arquivo em sí no Objeto File,
// podemos utilizar esta classe para escrever nele.
// porém é um classe bem LowLevel ou seja, não otimizada
// pois manipula diretamente no HD, diferente de outras (BufferedWriter)
// na qual manipula primeiro em memória, e depois realiza a escrita,
// logo conseguimos mais desempenho (+ otimizada) porém é necessário também
// além de uma instância de File, uma de FileWriter simples LowLevel para sua criação.

// Classes aboradads neste package:
// File (Base para outras classes)
// FileWriter (Low level, não otimizada)
// FileReader (Low level, não otimizada)
// BufferedWriter (Em memória, logo == + rápido)
// BufferedReader (Em memória, logo == + rápido)


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Aula139FileWriter_LowLevel_Utils {
    public static void main(String[] args) {
        File file = new File("/home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt"); // caminho absoluto ou parcial

        try (FileWriter fw = new FileWriter(file, true)){ // Implementa clouseble então pode utilizar try with resources
        // relembrando try with resources: O java mesmo se encarrega de executar o .close();
            fw.write("Wellsion é lindão, a certificação oracle veeem!!\n");
            fw.write("Wellsion é lindão, a certificação oracle veeem!!\n");
            fw.write("Wellsion é lindão, a certificação oracle veeem!!\n");
            fw.write("Wellsion é lindão, a certificação oracle veeem!!\n");
            fw.write("Wellsion é lindão, a certificação oracle veeem!!");
            fw.flush(); // libera o buffer pois existe a chance de antes de fechar o arquivo, o java não mandou todos
            // os arquivos do buffer, assim é necessário libera-lo e após liberar fechar

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
