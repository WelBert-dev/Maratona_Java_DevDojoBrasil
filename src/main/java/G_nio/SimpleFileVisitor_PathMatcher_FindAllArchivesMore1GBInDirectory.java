package G_nio;

// Buscando arquivos maiores que 1GB em um diretório de maneira recursiva
// ou seja level ALL abaixo da arvore de diretórios.

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SimpleFileVisitor_PathMatcher_FindAllArchivesMore1GBInDirectory {
    public static void main(String[] args) throws IOException {

        Path startDir = Paths.get( "/home/welbert/Downloads");
        Path outputSaveReturnPath = Paths.get("src/nio/SimpleFileVisitor_listaArquivosMaioresQue1GB.txt");

        if (Files.notExists(outputSaveReturnPath)) {
            Files.createFile(outputSaveReturnPath);

            LargeFileFinder finder = new LargeFileFinder();
            Files.walkFileTree(startDir, finder);
        }

        LargeFileFinder finder = new LargeFileFinder();
        Files.walkFileTree(startDir.normalize(), finder);

    }
}

class LargeFileFinder extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private String globExpression;
    private long maxSize = 1_000_000_000L;

    private Path outputFile = Paths.get("src/nio/SimpleFileVisitor_listaArquivosMaioresQue1GB.txt");

    public LargeFileFinder() throws IOException {
        this.globExpression = "glob:**/*.{mp4,avi,mkv,flv,mov,mpg,wmv}";

        if (Files.notExists(outputFile)) {

            Files.createFile(outputFile);
        }

        this.matcher = FileSystems.getDefault().getPathMatcher(globExpression);

    }

    public LargeFileFinder(String globExpression) throws IOException {
        this();
        this.matcher = FileSystems.getDefault().getPathMatcher(globExpression);
    }

    public LargeFileFinder(long maxSize) throws IOException {
        this();
        this.maxSize = maxSize;
    }

    public LargeFileFinder(String globExpression, long maxSize) throws IOException {
        this();
        this.globExpression = globExpression;
        this.maxSize = maxSize;
        this.matcher = FileSystems.getDefault().getPathMatcher(globExpression);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try {
            // verifica se o arquivo é maior que maxSize e se corresponde à expressão glob
            if (attrs.isRegularFile() && attrs.size() > maxSize &&
                    file.getFileName().toString().matches(globExpression)) {
                String output = file.getFileName() + "\n";
                Files.writeString(outputFile, output, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return FileVisitResult.CONTINUE;
    }
}
