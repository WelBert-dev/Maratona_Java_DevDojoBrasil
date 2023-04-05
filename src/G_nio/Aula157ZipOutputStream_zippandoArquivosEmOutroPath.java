package nio;

// Quando uma classe possue "Stream" geralmente é classe LowLevel
// que trabalha com binários.

// Aqui vamos pegar varios arquivos distintos (Path's) e vamos comprimilos em outro.

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Aula157ZipOutputStream_zippandoArquivosEmOutroPath {
    public static void main(String[] args) throws IOException {
        Path targetPath = Paths.get("src/nio/aula157ZipOutputStream.zip");
        Path sourceDirPath = Paths.get("src/nio");
        if (Files.notExists(targetPath)) {
            Files.createFile(targetPath);

            List<Path> archivesPathList = new ArrayList<>();
//            archivesPathList.add(Paths.get("src/nio/Aula144e145Introducao_Path_Paths_Files.java"));
//            archivesPathList.add(Paths.get("src/nio/Aula146NormalizacaoDePaths.java"));
//            archivesPathList.add(Paths.get("src/nio/Aula147ResolvendoPaths_concatenando.java"));

            // zippando todos arquivos com extensão .java (lvl 1 da arvore) nio:
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDirPath, path ->
                    path.toString().endsWith(".java"))) {
                for (Path path : directoryStream) {
                    archivesPathList.add(path);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            zip(targetPath, archivesPathList);
        }
    }
    public static void zip(Path targetPath, List<Path> archivesPathList) {
        try (ZipOutputStream zipStream = new ZipOutputStream(Files.newOutputStream(targetPath))) {

            for (Path path: archivesPathList) {
                System.out.println(path.getFileName());

                ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
                zipStream.putNextEntry(zipEntry); // prepara o arquivo, copiando apenas o nome dele
                Files.copy(path, zipStream); // copia o conteúdo
                zipStream.closeEntry(); // fecha a entrada
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
