package nio;

// Introdução ao New IO (Melhorias nas manipulações de arquivos)
// Maior coesão entre classes e tudo mais.
// É Possível transformar um File em Path e vice e versa. (.toPath() ou .toFile() )

// [Class File] <=====================================> [Interface Path] -> substitui a classe File por Path
//     /\                                                     /\
//     ||                                                     ||
//     ||                                                [Class Paths] -> Utilizada para dar origem a uma interface Path
//     ||
// [Class Util Files] -> Todos os métodos de File foram passados para Files (Estáticos)

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Aula144e145Introducao_Path_Paths_Files {
    public static void main(String[] args) {

        try {
            // introd
            Path p1 = Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo/", "file.txt"); // old: new File("...");
            System.out.println(p1.getFileName());

            // criando diretorio unico
            Path directoryPath = Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo/pasta_renamed2");
            if(Files.notExists(directoryPath)) {
                Path directoryObj = Files.createDirectory(directoryPath);
                System.out.println(directoryObj);
            }

            // criando multiplos sub-diretorios de uma vez
            Path directoriesPath = Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo/pasta_renamed/subpasta/subsubpasta");
            if(Files.notExists(directoriesPath)) {
                // Path directoriesObj = Files.createDirectory(directoriesPath); error (não é possivel criar varios mkdir de uma vez)
                Path directoriesObj = Files.createDirectories(directoriesPath);
                System.out.println(directoriesObj);
            }

            // criando um arquivo dentro de um diretorio criado anteriormente:
            Path filePath = Paths.get(directoriesPath.toString(),"fileTest.txt");
            if (Files.notExists(filePath)) {
                Path filePathCreated = Files.createFile(filePath);
                System.out.println(filePathCreated);
            }

            // renomeando um arquivo criado anteriormente:
            Path source = filePath; // Origem
            Path target = Paths.get(filePath.getParent().toString(), "fileTest_renamed"); // destino
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); // ultimo parametro faz Override se ja existir


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
