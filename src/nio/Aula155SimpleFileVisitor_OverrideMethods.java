package nio;

// Interação de maneira recursiva em toda a arvere de diretórios
// Sobre-Escrita de métodos do extends SimpleFileVisitor<T>
// Classe responsável por definir as regras na qual o método:
// File.walkFileTree(pathInicial, new ClassExtendsSimpleFileVisitor<>());
// Irá percorrer recursivamente toda a arvore hierarquica de diretórios apartir de um.

// preVisitDirectory: Executado antes de abrir e apontar o cursor para o novo diretório.
// visitFile: Executado após abrir e apontar o cursor para o diretório corrente.
// visitFileFailed: Executado em casos de errors em algum dos passos anteriores.
// postVisitDirectory: Executado após abrir e apontar o cursor para o diretório corrente retornando.

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Aula155SimpleFileVisitor_OverrideMethods {
    public static void main(String[] args) throws IOException {
        Path rootPath = Paths.get(".");

        if (Files.exists(rootPath)) {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {

            } else {
                Files.walkFileTree(rootPath, new ListAllFilesEndsWithJavaOrClass());
            }
        }
    }
}

class ListAllFilesEndsWithJavaOrClass extends SimpleFileVisitor<Path> {
    // Atalho para sobre-escrever métodos: CTRL + O

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("preVisit: "+dir.getFileName());
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("visitFile: "+file.getFileName());
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("visitFileFailed: "+file.getFileName());
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("postVisitDirectory: "+dir.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
