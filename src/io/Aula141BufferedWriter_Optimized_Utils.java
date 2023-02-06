package io;

// Equivalente da classe FileWriter porém otimizada com maior performance,
// pois ela trabalha primeiro em Memória RAM e ao final do processamento
// realiza a Escrita do arquivo no HD.
// Obs: Mesmo assim ainda é necessário uma instância de FileWriter
// e passa-la para o BufferedWriter.

// Obs2: Alem disto essa classe é mais nova então ela entende as diferenças
// entre os S.O's, então ao invés de utilizar "\n" para quebra de linha
// utilzamos o método bufferedWriterObj.newLine(); pois ele ja se encarrega
// de utilizar o caractere especial corretamente para cada S.O
// uma vez que nem todos entendem "\n" como quebra de linha.

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Aula141BufferedWriter_Optimized_Utils {
    public static void main(String[] args) {
        File file = new File("/home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt"); // caminho absoluto ou parcial

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw, 2048)){
            // Implementa clouseble então pode utilizar try with resources
            // relembrando try with resources: O java mesmo se encarrega de executar o .close();
            bw.write("Wellsion é lindão, a certificação oracle veeem!!");
            bw.newLine();
            bw.write("Wellsion é lindão, a certificação oracle veeem!!");
            bw.newLine();
            bw.write("Wellsion é lindão, a certificação oracle veeem!!");
            bw.newLine();
            bw.write("Wellsion é lindão, a certificação oracle veeem!!");
            bw.newLine();
            bw.write("Wellsion é lindão, a certificação oracle veeem!!");
            bw.flush();
            // libera o buffer pois existe a chance de antes de fechar o arquivo, o java não mandou todos
            // os arquivos do buffer, assim é necessário libera-lo e após liberar fechar

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
