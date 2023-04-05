package time;

// Utilizado para trabalhar com Nano segundos,
// representando um ponto instantâneo dentro de uma linha de tempo.
// Imutável, ou seja toda vez que queremos alterar o valor real em memória
// será necessário re-atribuições.

// Na pratica ele representa os nano segundos de 1970 até hoje.
// Bem parecido com a classe Date, porém para se trabalhar apenas com os nano seconds

// A saída é a mesma que a LocalDateTime, porém com a diferença de um Z nela no final,
// esse Z representa o tempo zulu (Padrão aonde a representação sempre é a mesma independente da localização)
// muito utilizado para salvar dados, e na reculperação do mesmo é só realizar um calculo,
// de acordo com a origem (JVM) que esta executando, assim
// basta converter zulu <=> LocalDateTime.default()
// utc time também representa a mesma hora que o zulu time!

// Dica: Para saber as diferenças de horas entre o zulu time e outras regiões:
// Pesquisar no google: zulu time to brazil brasilia
// Greenwich Mean Time está 3 horas à frente de Brasília, DF

// Como a representação do Instant é os nano segundos apartir de 1970,
// então o limite ultrapassa um Long, assim o Instant utiliza 2 variaveis para representar:
// Nano: objInstant.get
// EpochSeconds:

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Locale;

public class Aula122Instant_NanoSeconds {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now); // 2022-12-09T00:20:37.073909679Z

        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println(nowDateTime); // 2022-12-09T00:20:37.073909679

        Locale local = Locale.getDefault();
        System.out.println(local); // en_US

        // Duas representações do Instant:
        System.out.println(now.getEpochSecond()); // 1670546135 (long) // basicamente é os segundos
        System.out.println(now.getNano()); // 938357305 (int) 999.999.999 <=> 1 Epoch // basicamente é os nanos


        // Aqui explica na prática oq é o nano(milesimos e etc) e o epoch(segundo)
        System.out.println(Instant.ofEpochSecond(2, 999_999_999).plusNanos(1)); //1970-01-01T00:00:03Z
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000)); // 1970-01-01T00:00:03Z

        // Mesma coisa porém diminuindo com valores negativos:
        System.out.println(Instant.ofEpochSecond(2, -1_000_000_000)); // 1970-01-01T00:00:01Z
    }
}
