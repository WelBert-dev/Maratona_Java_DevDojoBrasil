package nio;

// Equivalente da classe DirectoryStream porém para interar de maneira recursiva
// ou seja toda a arvore hierarquica.

// Primeiro devemos criar uma classe que extends de SimpleFileVisitor<T> (T é Path ou File).
// Pois precisamos de uma instância compatível com esta classe ("É um") para passarmos
// Ao método estático Files.walkFileTree(objPathDePartidaInicial, new ClassExtendsSimpleFileVisitor());

// ClassExtendsSimpleFileVisitor() é a classe na qual foi sobreScrevito os métodos a seguir:
// preVisitDirectory: Executado antes de abrir e apontar o cursor para o novo diretório.
// visitFile: Executado após abrir e apontar o cursor para o diretório corrente.
// visitFileFailed: Executado em casos de errors em algum dos passos anteriores.
// postVisitDirectory: Executado após abrir e apontar o cursor para o diretório corrente retornando.
// Mais DETALHES desses métodos Override em src/nio/Aula155SimpleFileVisitor_OverrideMethods.java

// Para os retornos dos métodos a cima é necessário utilizar uma das enumerações de FileVisitResult:
// FileVisitResult é uma enumeração utilizada para definir como vai ser
// o próximo passo da iteração. A seguir as opções e suas ações:
// FileVisitResult.CONTINUE: Define que ira continuar a interação, ou seja próximo Path.
// FileVisitResult.TERMINATE: Define que encontrou o Path desejado e Finaliza.
// FileVisitResult.SKIP_SUBTREE: Define que é para sair da subpasta corrente.
// FileVisitResult.SKIP_SIBLINGS Continua interação pulando quem está no mesmo nível da arvore.

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Aula154SimpleFileVisitor_interacaoAllRecursivaEmDirs {
    public static void main(String[] args) throws IOException {
        Path rootPath = Paths.get("."); // root
        if (Files.exists(rootPath)) {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){

                // Windows

            }else{

                Path archiveTxtEmJavaPath = Paths.get("src/nio/aula154SimpleFileVisitorSaida.txt");
                Path archiveTxtEmBashPath = Paths.get("/home/welbert/Documentos/comandoBashDirRecursivo.txt");

                if (Files.notExists(archiveTxtEmJavaPath)) { // se não existe ele cria utilizando a recursividade
                    Files.createFile(archiveTxtEmJavaPath);

                    // Escreve a saída em um arq .txt percorrendo os diretorios e salvando o nome de arqvs .java
                    Files.walkFileTree(rootPath, new ListAllFiles());

                    // Verificando se as saídas batem (Bash X Java):
                    comparaSeArquivosTxtEqualsNoPerformance(archiveTxtEmJavaPath, archiveTxtEmBashPath);

                    // Mesma verificação porém utilizando hashCode:
                    MessageDigest md = null;
                    try {
                        md = MessageDigest.getInstance("SHA-1");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    System.out.println(compareFilesWithHashCode(archiveTxtEmJavaPath, archiveTxtEmBashPath, md));

                    // Mesma verificação porém utilziando Inode (Maior performance):
                    // PORÉM ele verifica se é TOTALMENTE o mesmo arquivo,
                    // não servindo para nós, pois estamos querendo verificar o CONTEÙDO do arquivo em sí
                    // não o OBJ em memória!
                    System.out.println(compareFilesWithInode(archiveTxtEmJavaPath, archiveTxtEmBashPath));

                    // Mesma verificação porém visando o CONTEÚDO em sí e com melhor performance:
                    System.out.println(compareIfContentFilesIsEquals(archiveTxtEmJavaPath, archiveTxtEmBashPath));

                } else { // se ja existe
                    MessageDigest md = null;
                    try {
                        md = MessageDigest.getInstance("SHA-1");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    // Verificação utilizando HashCode
                    System.out.println(compareFilesWithHashCode(archiveTxtEmJavaPath, archiveTxtEmBashPath, md));

                    // Mesma verificação porém utilziando Inode (Maior performance):
                    // PORÉM ele verifica se é TOTALMENTE o mesmo arquivo,
                    // não servindo para nós, pois estamos querendo verificar o CONTEÙDO do arquivo em sí
                    // não o OBJ em memória!
                    System.out.println(compareFilesWithInode(archiveTxtEmJavaPath, archiveTxtEmBashPath));

                    // Mesma verificação porém visando o CONTEÚDO em sí e com melhor performance:
                    System.out.println(compareIfContentFilesIsEquals(archiveTxtEmJavaPath, archiveTxtEmBashPath));
                }
            }
        }
    }

    // Método que compara se dois arquivos são iguais utilizando Strings (Menor performance)
    public static void comparaSeArquivosTxtEqualsNoPerformance(Path filePath1, Path filePath2) throws IOException {
        String fileTxtEmJavaString = Files.readString(filePath1);
        String fileTxtEmBashString = Files.readString(filePath2);

        System.out.println(fileTxtEmBashString);
        System.out.println("--------------------------------------------------------------");
        System.out.println(fileTxtEmJavaString);
        if (fileTxtEmJavaString.equals(fileTxtEmBashString)) {
            System.out.println("Conteúdos Identicos. :D");
        } else {
            System.out.println("Conteúdos diferentes! ;-;.");
        }
    }

    // Método que compara se dois arquivos são iguais utilizando HashCode:
    public static boolean compareFilesWithHashCode(Path file1, Path file2, MessageDigest md) throws IOException {
        // update the digest using the contents of file1
        FileInputStream fis1 = new FileInputStream(file1.toFile());
        byte[] dataBytes1 = new byte[1024];
        int nread1 = 0;
        while ((nread1 = fis1.read(dataBytes1)) != -1) {
            md.update(dataBytes1, 0, nread1);
        };
        byte[] hashBytes1 = md.digest();

        // update the digest using the contents of file2
        FileInputStream fis2 = new FileInputStream(file2.toFile());
        byte[] dataBytes2 = new byte[1024];
        int nread2 = 0;
        while ((nread2 = fis2.read(dataBytes2)) != -1) {
            md.update(dataBytes2, 0, nread2);
        };
        byte[] hashBytes2 = md.digest();

        // compare the hash codes of the two files
        if (hashBytes1.length != hashBytes2.length) return false;
        for (int i = 0; i < hashBytes1.length; i++) {
            if (hashBytes1[i] != hashBytes2[i]) return false;
        }
        return true;
    }

    // Método ja pronto do Java para comparar se dois arquivos são iguais (Melhor performance) utilizando inode:
    // PORÉM ele verifica se é TOTALMENTE o mesmo arquivo,
    // não servindo para nós, pois estamos querendo verificar o CONTEÙDO do arquivo em sí
    // não o OBJ em memória!
    public static boolean compareFilesWithInode(Path file1, Path file2) throws IOException {
        return Files.isSameFile(file1, file2);
    }

    // Método que compara se o CONTEÙDO de dois arquivos é igual (Melhor performance)
    public static boolean compareIfContentFilesIsEquals(Path file1, Path file2) throws IOException {
        try (FileChannel channel1 = FileChannel.open(file1, StandardOpenOption.READ, StandardOpenOption.WRITE);
             FileChannel channel2 = FileChannel.open(file2, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            if (channel1.size() != channel2.size()) {
                return false;
            }

            long size = channel1.size();
            long position = 0;
            while (position < size) {
                position += channel1.transferTo(position, size - position, channel2);
            }

            return true;
        }
    }
}

class ListAllFiles extends SimpleFileVisitor<Path> {
    // Atalho para sobre-escrever métodos: CTRL + O

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".java")) {
            // printa todos os arquivos .java encontrados na iteração.

            // Função equivalente ao comando em bash:
            // find /home/welbert/Documentos/github/MaratonaJava-DevDojo -name "*.java" -type f;

            // Ou filtra a saída para deixar apenas o nome do arquivo, eliminando o caminho completo:
            // find /home/welbert/Documentos/github/MaratonaJava-DevDojo -name "*.java" -type f -exec basename {} \;

            System.out.println(file.getFileName());

            // salvando em arquivo .txt a saída ("*.java") e comparando com o equivalente em bash
            // e depois realiza a comparação dos dois arquivos para validar se ambas correspondem
            // Saída gerada pelo Java == Saída gerada pelo Bash
            Path outputPath = Paths.get("src/nio/aula154SimpleFileVisitorSaida.txt");
            Files.write(outputPath, (file.getFileName().toString()+"\n").getBytes(), StandardOpenOption.APPEND);

        }
        return FileVisitResult.CONTINUE; // continua iteração até o final da arvore. ALL Itens

        // FileVisitResult é uma enumeração utilizada para definir como vai ser
        // o próximo passo da iteração. A seguir as opções e suas ações:

        // CONTINUE: Define que ira continuar a interação, ou seja próximo Path.

        // TERMINATE: Define que encontrou o Path desejado e Finaliza.

        // SKIP_SUBTREE: Define que é para sair da subpasta corrente.

        // SKIP_SIBLINGS Continua interação pulando quem está no mesmo nível da arvore.

    }
}
