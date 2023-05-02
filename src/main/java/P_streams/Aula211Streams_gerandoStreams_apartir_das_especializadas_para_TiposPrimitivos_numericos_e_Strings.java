package P_streams;

// Com as interfaces personalizadas para tipos primitivos numéricos Stream
// é possível gerar ranges de valores, com dois métodos sendo eles:
// InterfacePersonalizada.range(): considera o inicio, e o fim é (valorFinal - 1)
// InterfacePersonalizada.rangeClosed(): considera o inicio, e o fim!

// Também é possível gerar ranges do alfabéto com macete de mapear IntStream
// em inteiros que representem os chars na tabela ASCII, exemplo:
//
//         IntStream.range('a', 'z'+1)
//                .mapToObj(Character::toString)

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Aula211Streams_gerandoStreams_apartir_das_especializadas_para_TiposPrimitivos_numericos_e_Strings {
    public static void main(String[] args) {
        // ------------------------ [Gerando Inteiros] ------------------------
        IntStream.range(0, 21) // de 0 a 20
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + ", "));
        // 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20,

        // Obs: Exemplo diferente pois como estamos trabalhando com PARES
        // o 21 seria desconsiderado no print da mesma forma que no primeiro exemplo
        // pois ele não passaria no filtro `n % 2 == 0`!
        // Então para demonstração acrescentei +1 para considerar tôdô o range:
        IntStream.rangeClosed(0, 22)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + ", "));
        // 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, <- considerou o range até o valor passado!
        // NÃO fez (valorFinal - 1)

        // ------------------------- [Gerando Strings] -------------------------
        // Macete para gerar ranges do alfabéto com IntStream que represente ASCII

        IntStream.range('a', 'z'+1)
                .mapToObj(Character::toString)
                .forEach(letra -> System.out.print(letra + ", "));
        // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
        // O método range() gera uma sequência de inteiros começando em 'a' e
        // terminando em 'z' + 1, que corresponde ao código ASCII da letra 'z mais um.
        // Em seguida, o método mapToObj() é usado para mapear cada valor inteiro
        // para uma String usando o método Character.toString().

        // Gerando Stream de Strings ou qualquer outro Objeto <T>:
        Stream.of("Wellison","wesley", "Irineu", "InemEu")
                .filter(string -> string.toUpperCase().startsWith("W") && string.length() > 7)
                .forEach(string -> System.out.print(string + ", "));
        // Wellison,

        // ---------------------- [Iterando por index ] -----------------------
        List<String> lista = Arrays.asList("elemento1", "elemento2", "elemento3", "elemento4", "elemento5");
        List<String> elementosEmPosicoesPares =
                IntStream.iterate(0, i -> i + 1) // gera um fluxo de índices
                        .limit(lista.size()) // limita o fluxo para o tamanho da lista
                        .filter(i -> i % 2 == 0) // filtra apenas os índices pares
                        .mapToObj(lista::get) // converte o índice em elemento da lista
                        .collect(Collectors.toList()); // coleta em uma lista

        System.out.println(elementosEmPosicoesPares); // [elemento1, elemento3, elemento5]

        // ---------- [Gerando Streams apartir de Arrays nativos ] ------------
        int[] numbersArrayNativo = {1, 2, 3, 4, 5, 6};
        Arrays.stream(numbersArrayNativo)
                .average()
                .ifPresent(System.out::println); // 3.5

        // -------------- [Gerando Streams apartir de Arquivos ] --------------

        try(Stream<String> lines = Files.lines(Paths.get("/home/welbert/Documentos/github/MaratonaJava-DevDojo/src/nio/SimpleFileVisitor_listaArquivosMaioresQue1GB.txt"))) {
            lines.filter(line -> line.startsWith("R"))
                    .forEach(System.out::println);
            // Rick and Morty S06E10 WEB-DL 1080p DUAL 5.1.mkv
            // Rick.and.Morty.S06E09.1080p.WEB-DL.DUAL.5.1.mkv
            // Rick.and.Morty.S06E08.1080p.WEB-DL.DUAL.5.1.mkv
            // Rick.and.Morty.S06E07.1080p.WEB-DL.DUAL.mkv
            // Rick.and.Morty.S06E06.1080p.WEB-DL.DUAL.mkv


            // Se contem alguma string em qualquer lugar:
            lines.filter(line -> line.contains("1080p"))
                    .forEach(System.out::println);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
