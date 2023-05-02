package F_io;

import java.io.File;
import java.io.IOException;

public class Aula143FileParaDiretorios {
    public static void main(String[] args) {

        try {
            File fileDirectory = new File("pasta");
            boolean isFolderCreated = fileDirectory.mkdir();
            System.out.println(isFolderCreated);

            File fileArchiveDirectory = new File(fileDirectory, "arquivo.txt");
            boolean isArchiveCreated = fileArchiveDirectory.createNewFile();
            System.out.println(isArchiveCreated);

            // renomeando um arquivo:
            File fileRenamed = new File(fileDirectory, "arquivo_renamedpa.txt"); // sempre passando o caminho correto.
            boolean isRenamedArchive = fileArchiveDirectory.renameTo(fileRenamed);
            System.out.println(isRenamedArchive);

            // renomeando um diretório:
            File fileFolderRenamed = new File("pasta_renamed"); // sempre passando o caminho correto.
            boolean isRenamedFolder = fileDirectory.renameTo(fileFolderRenamed);
            System.out.println(isRenamedFolder);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
