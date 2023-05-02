package F_io;

// Equivalente da classe FileReader porém otimizada com maior performance,
// pois ela trabalha primeiro em Memória RAM e ao final do processamento
// realiza a Leitura do arquivo no HD.
// Obs: Mesmo assim ainda é necessário uma instância de FileReader
// e passa-la para o BufferedReader.

// Obs2: Ao invés de lermos caractere a caractere, ja lemos uma linha por completo
// assim ganhamos mais performance.

import java.io.*;

public class Aula142BufferedReader_Optimized_Utils {
    public static void main(String[] args) {
        File file = new File("/home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt"); // caminho absoluto ou parcial

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)){

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
