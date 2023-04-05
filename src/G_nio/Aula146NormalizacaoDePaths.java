package nio;

// A normalização é a conversão de paths relativos com uso de variáveis de ambiente
// para a transformação do caminho completo (Mantendo assim um código legível e funcional)
// retirando esses caracteres coringa da expressão e substituindo pelo correspondente correto.

// Exemplo sem normalização: ./pasta/subpasta/arquivo.txt || ~/pasta/subpasta/arquivo.txt
// Exemplo anterior normalizado: /home/welbert/pasta/subpasta/arquivo.txt


import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula146NormalizacaoDePaths {
    public static void main(String[] args) {
        String projectDirectory = "/home/welbert/Documentos/github/MaratonaJava-DevDojo";
        String txtArchive = "../../arquivo.txt";

        Path p1 = Paths.get(projectDirectory, txtArchive); //                            \/ n esta resolvendo
        System.out.println(p1); // /home/welbert/Documentos/github/MaratonaJava-DevDojo/../../arquivo.txt

        System.out.println(p1.normalize()); // /home/welbert/Documentos/arquivo.txt <- resolveu
    }
}
