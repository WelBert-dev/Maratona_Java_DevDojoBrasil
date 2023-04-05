package F_io;

// Todas classes especializadas em input e output utilizam como base a
// classe File, pois para utilizar as outras deste pacote é necessário uma instância dela.

// Classes aboradads neste package:
// File (Base para outras classes)
// FileWriter (Low level, não otimizada)
// FileReader (Low level, não otimizada)
// BufferedWriter (Em memória, logo == + rápido)
// BufferedReader (Em memória, logo == + rápido)

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;

public class Aula138File_introduction_Utils {
    public static void main(String[] args) {
        File file = new File("/home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt"); // caminho absoluto ou parcial
        boolean isDeleted;

        if (file.exists()) {
           // isDeleted = file.delete();
        }

        System.out.println(file.isFile()); // true
        System.out.println(file.isDirectory()); // false
        System.out.println(file.isHidden()); // false

        System.out.println(file.getAbsolutePath()); // /home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt
        System.out.println(file.getPath()); // /home/welbert/Documentos/github/MaratonaJava-DevDojo/file.txt
        System.out.println(file.getParent()); // /home/welbert/Documentos/github/MaratonaJava-DevDojo
        System.out.println(file.getTotalSpace()); // 318450962432
        System.out.println(file.getFreeSpace()); // 254839607296

        Map<String, String> shortIds = ZoneId.SHORT_IDS;
        for (String keyOfZone  : shortIds.keySet()) {
            System.out.println("Chave: " + keyOfZone +  " | Valor: "+shortIds.get(keyOfZone));
        }

        // Ultima modificação em LocalDateTime
        System.out.println(Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());
        // 2023-01-04T21:08:13.624
        try {
            boolean isCreated = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
