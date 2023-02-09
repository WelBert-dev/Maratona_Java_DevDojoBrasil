package nio;

// Resolvendo paths quando temos dois, um representando o diretório
// e outro representando o arquivo em si.
// Temos o método .resolve(path) que faz essa concatenação e resolve o caminho.

import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula147ResolvendoPaths_concatenando {
    public static void main(String[] args) {
        Path dir = Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo");
    }
}
