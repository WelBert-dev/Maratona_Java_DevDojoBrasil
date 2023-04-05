package nio;

// Utilizado para apartir de um path "ir" para outro
// Ou seja, como faz para ir de uma path1 para o path2
// exemplo:
// (path1) /home/welbert/dev
// (path2) /home/irineu/inemEu

// path2.relativise(path1): ../../welbert/dev

// Obs: NÃO é possível ir apartir de um absoluto para um relativo
// pois como é relativo vai depender de onde esta sendo executado
// assim o Java não tem ideia do caminho até ali.
//


import java.nio.file.Path;
import java.nio.file.Paths;

public class Aula148RelativisandoPaths {
    public static void main(String[] args) {
        Path source = Paths.get("/home/irineu/inemEu"); // origem
        Path target = Paths.get("/home/welbert/dev "); // destino
        System.out.println(source.relativize(target)); // ../../welbert/dev
        System.out.println(target.relativize(source)); // ../../irineu/inemEu
    }
}
