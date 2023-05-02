package F_io;

// LowLevel ou seja não é otimizado, recomendavel utilizar BufferedReader!
// A leitura básica se da por um looping verificando se o retorno é diferente
// de -1 (método fileReaderObj.read()) que retorna um Int que representa o char
// para converter basta fazer um cast (char) no retorno.
// Para alimentar um array de chars basta passá-lo no método (fileReaderObj.read(charArray))
// e assim ja realiza a conversão e armazena no array automaticamente.

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Aula140FileReader_LowLevel_Utils {
    public static void main(String[] args) {
        File file = new File("/home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt"); // caminho absoluto ou parcial

        try (FileReader fr = new FileReader(file)){
            System.out.println(fr.read());
            // retorna um Int que representa um caractere na tabela ASC (-1 reprersenta o final)

            char[] in = new char[(int)file.length()-1];
            System.out.println(fr.read(in));
            // retorna o Int e ja converte em char alimentando o array de char
            for (char c : in) {
                System.out.print(c);
            }

//            int i;
//            while ((i = fr.read()) != -1) {
//                //System.out.print(Character.toChars(i));
//                System.out.print((char)i);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
