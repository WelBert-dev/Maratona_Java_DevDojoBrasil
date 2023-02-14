package nio;

// Classe utilizada para navegar em level 1 nos diretórios
// ou seja, apenas no primeiro nível da arvore
// com um looping de interação.
// Obs: Para interação all recursiva se utiliza a SimpleFileVisitor

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class Aula153DirectoryStream_interacaoLvl1emDirs {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/nio"); // root
        if (Files.exists(filePath)) {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){

                // Faz nada pois para este se utiliza a especializada: DosFileAttributes

            }else{

                // Para Unix like: PosixFileAttributes
                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(filePath, "*.java")) {
                    for (Path path : directoryStream) {
                        System.out.println(path);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
