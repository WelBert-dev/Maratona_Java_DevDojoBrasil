package nio;

// Aqui um exemplo de como fazer matcher em Paths
// como por exemplo: Retornar todos os arquivos cuja extensão termine em .java ou .class

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class Aula156PathMather_WithGlobExpressions {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".");
        Path arquivoTxtEmJavaPath = Paths.get("src/nio/aula156PathMather_WithGlobExpressions.txt");
        Path arquivoTxtEmJavaPathOrganizado = Paths.get("src/nio/aula156PathMather_WithGlobExpressions_Organizado.txt");
        if (Files.notExists(arquivoTxtEmJavaPath)){
            Files.createFile(arquivoTxtEmJavaPath);

            String globExpression = "glob:**/Aula*.{java}";
            Files.walkFileTree(path, new ListAllFilesEndsWithJavaOrClass(arquivoTxtEmJavaPath, globExpression)); // ListAllFiles vem da aula Aula154SimpleFileVisitor_interacaoAllRecursivaEmDirs
        }

        try {
            List<String> lines = Files.readAllLines(arquivoTxtEmJavaPath);
            String[] array = lines.toArray(new String[lines.size()]);
            Arrays.sort(array);
            Files.write(arquivoTxtEmJavaPathOrganizado, Arrays.asList(array));
        } catch (IOException e) {
            System.out.println("Erro ao ler/gravar arquivo: " + e.getMessage());
        }

    }
}

class ListAllFilesEndsWithJavaOrClass extends SimpleFileVisitor<Path> {
    // Atalho para sobre-escrever métodos: CTRL + O

    private final Path outputPath;
    private final String globExpression;
    public ListAllFilesEndsWithJavaOrClass(Path saveOutputPath, String globExpression) {
        super();
        this.outputPath = saveOutputPath;
        this.globExpression = globExpression;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        // Verifica se possuem a extensão .java ou .class
//        if (isMatcherInPathsWithGlob(file, "glob:**/*.{java,class}")) {
//            System.out.println("Path que deu match: "+file.getFileName());
//        }

        // Verifica se possue "Aula" no nome do arquivo:

        if (isMatcherInPathsWithGlob(file, globExpression)) {
            System.out.println("Path que deu match: "+file.getFileName());
            Files.write(outputPath, (file.getFileName().toString()+"\n").getBytes(), StandardOpenOption.APPEND);
        }

        return FileVisitResult.CONTINUE;
    }

    public static boolean isMatcherInPathsWithGlob(Path path, String globExpression) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(globExpression);
        return pathMatcher.matches(path);
    }
}
