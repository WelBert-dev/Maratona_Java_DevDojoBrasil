package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.Set;

public class Aula152PosixFileAttributes {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src/nio/home/welbert/dev/arquivo2_path.txt");
        if (Files.exists(filePath)) {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){

                // Faz nada pois para este se utiliza a especializada: DosFileAttributes

            }else{

                // Para Unix like: PosixFileAttributes

                PosixFileAttributes posixFileAttributes = Files.readAttributes(filePath, PosixFileAttributes.class);
                System.out.println(posixFileAttributes.permissions());

                // Define permissões como rwxrw-rw-
                Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-r--r--");
                Files.setAttribute(filePath, "posix:permissions", permissions);

                // Verifica se as permissões foram aplicadas corretamente
                Set<PosixFilePermission> newPermissions =
                        (Set<PosixFilePermission>) Files.getAttribute(filePath, "posix:permissions");
                System.out.println(PosixFilePermissions.toString(newPermissions));
            }
        }
    }
}
