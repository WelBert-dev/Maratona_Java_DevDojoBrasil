package nio;

// Aqui um exemplo de como fazer matcher em Paths
// como por exemplo: Retornar todos os arquivos cuja extensão termine em .java ou .class

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Aula156PathMather_WithGlobExpressions {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".");
        Files.walkFileTree(path, new ListAllFilesEndsWithJavaOrClass()); // ListAllFiles vem da aula Aula154SimpleFileVisitor_interacaoAllRecursivaEmDirs

    }
}

class ListAllFilesEndsWithJavaOrClass extends SimpleFileVisitor<Path> {
    // Atalho para sobre-escrever métodos: CTRL + O

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (isMatcherInPathsWithGlob(file, "glob:**/*.{java,class}")) {
            System.out.println("Path que deu match: "+file.getFileName());
        }

        return FileVisitResult.CONTINUE;
    }

    public static boolean isMatcherInPathsWithGlob(Path path, String globExpression) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(globExpression);
        return pathMatcher.matches(path);
    }
}
