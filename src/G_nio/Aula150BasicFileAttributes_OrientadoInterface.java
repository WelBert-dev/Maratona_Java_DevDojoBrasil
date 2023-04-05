package G_nio;

// Utilizamos como variavel de referência o tipo mais geral/generico
// BasicFileAttributes e deixamos que o objeto criado por de baixo dos panos
// seja responsabilidade do Java (No caso do ambiente windows ele vai
// invocar um DosFileAttributes, e no linux um PosixFileAttributes ().

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Aula150BasicFileAttributes_OrientadoInterface {
    public static void main(String[] args) throws IOException {
        // Lendo informações de arquivo utilizando a interface mais genérica/geral de acesso simples
        Path filePath = Paths.get("src/nio/home/welbert/dev/arquivo2_path.txt");
        if (Files.exists(filePath)) {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            FileTime creationTime = basicFileAttributes.creationTime();
            FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
            FileTime lastAccessTime = basicFileAttributes.lastAccessTime();
            System.out.println("Data de criação: "+creationTime);// 2023-01-30T16:06:57.120342248Z
            System.out.println("Ultima modificação: "+lastModifiedTime);// 2023-01-30T16:06:57.120342248Z
            System.out.println("Ultimo acesso: "+lastAccessTime); // 2023-02-09T21:56:24.411474014Z
        }

        // Setando informações de arquivo utilizando a interface mais genérica/geral de acesso simples
        if (Files.exists(filePath)) {
            BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(filePath, BasicFileAttributeView.class);

            FileTime lastModifiedTime = basicFileAttributeView.readAttributes().lastModifiedTime();
            FileTime lastAccessTime = FileTime.from(LocalDateTime.now().minusDays(10).toInstant(ZoneOffset.UTC));
            FileTime creationTime = basicFileAttributeView.readAttributes().creationTime();

            basicFileAttributeView.setTimes(lastModifiedTime, lastAccessTime, creationTime);

            System.out.println("Valor do lastAcessTime: "+lastAccessTime);
            System.out.println("Lendo depois de setar: "+basicFileAttributeView.readAttributes().lastAccessTime());
        }


    }
}
