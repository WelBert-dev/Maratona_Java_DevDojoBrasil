package nio;

// Interface genérica para se trabalhar com as operações básicas
// sem modificações em sí, apenas leitura de atributos
// como ultima data de modificação do arquivo e etc...
// Graças ao POLIMORFISMO as implementações vão se basear no S.O.

// BasicFileAttributes interface mais genérica
// DosFileAttributes interface mais especializada para windows
// PosixFileAttributes interface mais especializada para Unix like
// Obs: Todos possuem apenas as operações básicas de leitura.
// Para manipulações e setters utilizamos as mesmas interfaces com final View.

// Dica: Trabalhar orientado a interface BasicFileAttribute como referência
// para manter compatibilidade com diversos Sistemas Operacionais,
// deixando o trabalho/criação do objeto correto em sí para o Java administrar.

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Aula149BasicFileAttributes {
    public static void main(String[] args) throws IOException {
        BasicFileAttributes basicFileAttributes; // Geral readOnly
        DosFileAttributes dosFileAttributes; // Windows readOnly
        PosixFileAttributes posixFileAttributes; // Unix like readOnly

        BasicFileAttributeView basicFileAttributeView; // Geral setter/write
        DosFileAttributeView dosFileAttributeView; // Windows setter/write
        PosixFileAttributeView posixFileAttributeView; // Unix like setter/write

        // modificando data de ultima modificação do jeito antigo (ni)
        LocalDateTime date = LocalDateTime.now().minusDays(10);
        File file = new File("src/nio/home/welbert/dev/arquivo2.txt");

        if (!file.exists()) {
            boolean isCreatedFile = file.createNewFile();
            boolean isModifiedFile = file.setLastModified(date.toInstant(ZoneOffset.UTC).toEpochMilli());
            System.out.println(isCreatedFile);
            System.out.println(isModifiedFile);

            // Ele considerou, porém não fez verificação sobre quando foi criado e quando foi modificado
            // uma vez que a modificação setada por nós é inconcistente com a data de criação.
        }

        // modificando data de ultima modificação do jeito novo (nio)
        Path filePath = Paths.get("src/nio/home/welbert/dev/arquivo2_path.txt");
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
        FileTime fileTime = FileTime.from(date.toInstant(ZoneOffset.UTC));
        Files.setLastModifiedTime(filePath, fileTime);

        System.out.println("É Writeble? "+Files.isWritable(filePath));
        System.out.println("É Executable? "+Files.isExecutable(filePath));
        System.out.println("É Readable? "+Files.isReadable(filePath));

    }
}
