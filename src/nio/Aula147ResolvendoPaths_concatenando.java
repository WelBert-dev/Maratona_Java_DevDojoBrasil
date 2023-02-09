package nio;

// Resolvendo paths quando temos dois, um representando o diretório
// e outro representando o arquivo em si.
// Temos o método .resolve(path) que faz essa concatenação e resolve o caminho.

// Se o caminho ja estiver correto ele desconsidera e retorna sem mudanças e sem erros.
// Ele só resolve apartir de um absoluto para o relativo nunca ao contrário.

import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula147ResolvendoPaths_concatenando {
    public static void main(String[] args) {
        Path dir = Paths.get("src/nio/home/welbert");
        Path archive = Paths.get("dev/arquivo.txt");
        Path resolve = dir.resolve(archive);
        System.out.println(resolve);

        Path absoluto = Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/home/welbert");
        Path relativo = Paths.get("dev");
        Path file = Paths.get("file.txt");

        System.out.println("- Absoluto resolve relativo: "+absoluto.resolve(relativo));
        // /home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/home/welbert/dev
        System.out.println("- Absoluto resolve file: "+absoluto.resolve(file));
        // /home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/home/welbert/file.txt
        System.out.println("- Relativo resolve absoluto: "+relativo.resolve(absoluto));
        // /home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/home/welbert
        System.out.println("- Relativo resolve file: "+relativo.resolve(file));
        // dev/file.txt
        System.out.println("- File resolve absoluto: "+file.resolve(absoluto));
        // /home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/home/welbert
        System.out.println("- File resolve relativo: "+file.resolve(relativo));
        // file.txt/dev
    }
}
