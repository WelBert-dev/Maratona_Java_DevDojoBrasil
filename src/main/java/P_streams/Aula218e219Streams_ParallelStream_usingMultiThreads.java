package P_streams;

// Obs: Deve ser uma tarefa pré definida, para o java saber lidar e dividir as
// tarefas.

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Aula218e219Streams_ParallelStream_usingMultiThreads {
    public static void main(String[] args) {

        // Verifica quantos cores estão disponíveis em runtime:
        System.out.println(Runtime.getRuntime().availableProcessors()); // 4 Núcleos
        long num = 10_000_000;

        // for nativo tem a melhor performance para iterações em LowLevel
        sumForLowLevel(num);
        // Sum for nativo: 50000005000000 17ms

        // --------------------------------------------------------------------

        // StreamIterate (genérico, ou seja faz autoBoxing e unBoxing) menas performance:
        sumStreamGenericIterate(num);
        // Sum StreamIterate genérico (c/ Wrapper): 50000005000000 327ms

        // --------------------------------------------------------------------

        // StreamIterate (genérico, mesmo cenário anterior, porém com flag parallel():
        // Obs: Não indicando para o java a tarefa pré definida assim ele não sabera lidar:
        sumParallelStreamGenericIterateNotPerformance(num);
        // Sum StreamIterate genérico (c/ Wrapper) com parallel SEM PERFORMANCE: 50000005000000 1469ms

        // --------------------------------------------------------------------

        // LongStream (especializada, porém sem multiThreads com parallel():
        sumLongStreamIterateNotPerformance(num);
        // Sum LongStream (sem Wrapper, não rola autoBoxing e unBoxing) SEM parallel MENAS PERFORMANCE: 50000005000000 48ms

        // --------------------------------------------------------------------

        // Solução: Informar ao java antecipadamente para ele saber lidar e dividir as
        // tarefas corretamente:
        // Pré definimos a forma como o java irá lidar com a iteração pois o
        // método range() ou rangeClosed() do LongStream contém valor
        // inicial e final, e por conta disso o java consegue saber como
        // irá distribuir a tarefa entre os nucleos.
        // ALÉM de não rolar autoBoxing e unBoxing devido a especializada.
        sumParallelLongStreamIteratePerformance(num);
         // Sum LongStream (sem Wrapper, não rola autoBoxing e unBoxing) com parallel MAIS PERFORMANCE: 50000005000000 31ms

    }
    private static void sumForLowLevel(long num) {
        System.out.print("Sum for nativo: ");
        long result = 0;
        long timeInit = System.currentTimeMillis();
        for (long i = 1; i <= num; i++) {
            result += i;
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println(result + " " +( timeEnd - timeInit) +"ms");
    }
    private static void sumStreamGenericIterate(long num) {
        System.out.print("Sum StreamIterate genérico (c/ Wrapper): ");
        long timeInit = System.currentTimeMillis();
        Long result = Stream.iterate(1L, n -> n + 1).limit(num).reduce(Long::sum).get();
        long timeEnd = System.currentTimeMillis();
        System.out.println(result + " " +( timeEnd - timeInit) +"ms");
    }
    private static void sumParallelStreamGenericIterateNotPerformance(long num) {
        // Não indicamos até quanto o for vai ir, apenas limitamso com limit()
        // logo o java não sabera separar as tarefas em threads!
        // Solução: Ao invés de utilizar Stream genérico, utilizar as especializadas
        // exemplo: LongStream.range() ou longStream.rangeClosed();
        // pois com ele indicamos qual será o valor final de iteração, logo o java
        // conseguirá distribuir as tarefas de uma forma correta.
        System.out.print("Sum StreamIterate genérico (c/ Wrapper) com parallel SEM PERFORMANCE: ");
        long timeInit = System.currentTimeMillis();
        Long result = Stream.iterate(1L, n -> n + 1).parallel().limit(num).reduce(Long::sum).get();
        long timeEnd = System.currentTimeMillis();
        System.out.println(result + " " +( timeEnd - timeInit) +"ms");
    }
    private static void sumLongStreamIterateNotPerformance(long num) {
        System.out.print("Sum LongStream (sem Wrapper, não rola autoBoxing e unBoxing) SEM parallel MENAS PERFORMANCE: ");
        long timeInit = System.currentTimeMillis();
        // rangeClosed especifica o valor final de iteração, logo o java consegue
        // definir e distribuir corretamente as tarefas para as Threads
        long result = LongStream.rangeClosed(1L, num).reduce(0, Long::sum);
        long timeEnd = System.currentTimeMillis();
        System.out.println(result + " " +( timeEnd - timeInit) +"ms");
    }
    private static void sumParallelLongStreamIteratePerformance(long num) {
        System.out.print("Sum LongStream (sem Wrapper, não rola autoBoxing e unBoxing) com parallel MAIS PERFORMANCE: ");
        long timeInit = System.currentTimeMillis();
        // rangeClosed especifica o valor final de iteração, logo o java consegue
        // definir e distribuir corretamente as tarefas para as Threads
        long result = LongStream.rangeClosed(1L, num).parallel().reduce(0, Long::sum);
        long timeEnd = System.currentTimeMillis();
        System.out.println(result + " " +( timeEnd - timeInit) +"ms");
    }
}
