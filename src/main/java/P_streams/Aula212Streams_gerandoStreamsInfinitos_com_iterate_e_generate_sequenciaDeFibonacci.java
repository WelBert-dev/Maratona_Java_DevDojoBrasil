package P_streams;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Aula212Streams_gerandoStreamsInfinitos_com_iterate_e_generate_sequenciaDeFibonacci {
    public static void main(String[] args) {

        // Os 10 primeiros impares
        Stream.iterate(1, n -> n + 2)
                .limit(10)
                .forEach(n -> System.out.print(n + ", "));
        // 1, 3, 5, 7, 9, 11, 13, 15, 17, 19,

        // Sequencia de Fibonacci: (0,1) (1,1) (1,2) (2,3) (3,5) (5,8) (8,13)
        // Aonde a posição 0 do atual sempre é a posição 1 do anterior
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(10)
                .forEach(a -> System.out.print(Arrays.toString(a) + " "));
        // [0, 1] [1, 1] [1, 2] [2, 3] [3, 5] [5, 8] [8, 13] [13, 21] [21, 34] [34, 55]

        // Mesma coisa porém retornando apenas os valores da sequencia:
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(10)
                .map(a -> a[0])
                .forEach(val -> System.out.print(val + ", "));
        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34,

        // Gerando com números aleatórios:
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Stream.generate(() -> random.nextInt(1, 1000))
                .limit(10)
                .forEach(n -> System.out.print(n + ", "));
        // 782, 837, 295, 485, 233, 281, 251, 728, 310, 112,

    }
}
