package G_nio;

// Interface Especializada para ambiente Windows.
// Extende de BasicFileAtributtes (Igual outros do mesmo contexto).

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.Set;

public class Aula151DosFileAttributes {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src/nio/home/welbert/dev/arquivo2_path.txt");
        if (Files.exists(filePath)) {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){

                // Obtem o principal de usuário para o usuário corrente
                UserPrincipal user = filePath.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName(System.getProperty("user.name"));

                // Define permissões de leitura e escrita para o usuário corrente
                AclFileAttributeView aclView = Files.getFileAttributeView(filePath, AclFileAttributeView.class);
                List<AclEntry> aclEntries = aclView.getAcl();
                AclEntry entry = AclEntry.newBuilder()
                        .setType(AclEntryType.ALLOW)
                        .setPrincipal(user)
                        .setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.WRITE_DATA)
                        .build();
                aclEntries.add(entry);
                aclView.setAcl(aclEntries);

            }else{
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
