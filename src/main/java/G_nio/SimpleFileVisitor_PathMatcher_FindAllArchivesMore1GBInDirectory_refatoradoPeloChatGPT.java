package G_nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SimpleFileVisitor_PathMatcher_FindAllArchivesMore1GBInDirectory_refatoradoPeloChatGPT {
    public static void main(String[] args) throws IOException {
        Path startDir = Paths.get("/home/welbert/Downloads");
        Path outputSaveReturnPath = Paths.get("src/nio/SimpleFileVisitor_listaArquivosMaioresQue1GB_refactor.txt");

        if (Files.notExists(outputSaveReturnPath)) {
            Files.createFile(outputSaveReturnPath);
        }

        LargeFileFinderRefactor finder = new LargeFileFinderRefactor(2_000_000_000L, "glob:**/*.{mp4,avi,mkv,flv,mov,mpg,wmv}", outputSaveReturnPath);
        Files.walkFileTree(startDir.normalize(), finder);
    }
}

class LargeFileFinderRefactor extends SimpleFileVisitor<Path> {
    private final PathMatcher matcher;
    private final Path outputFile;
    private final long maxSize;

    private final String globExpression;

    public LargeFileFinderRefactor(long maxSize, String globExpression, Path outputFile) throws IOException {
        super();
        this.maxSize = maxSize;
        this.globExpression = globExpression;
        this.outputFile = outputFile;

        if (Files.notExists(outputFile)) {
            Files.createFile(outputFile);
        }

        this.matcher = FileSystems.getDefault().getPathMatcher(globExpression);
    }

    public LargeFileFinderRefactor(String globExpression, long maxSize, Path outputFile) throws IOException {
        super();
        this.maxSize = maxSize;
        this.globExpression = globExpression;
        this.outputFile = outputFile;

        if (Files.notExists(outputFile)) {
            Files.createFile(outputFile);
        }

        this.matcher = FileSystems.getDefault().getPathMatcher(globExpression);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile() && attrs.size() > maxSize && matcher.matches(file)) {
            String output = file.getFileName() + "\n";
            Files.writeString(outputFile, output, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }

        return FileVisitResult.CONTINUE;
    }
}
