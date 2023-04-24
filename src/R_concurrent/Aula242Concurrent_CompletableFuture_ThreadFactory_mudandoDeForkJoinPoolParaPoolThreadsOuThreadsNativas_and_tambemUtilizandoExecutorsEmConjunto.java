package R_concurrent;

// Ao utilizar apenas o CompletableFuture o padrão do ThreadFactory é
// o Fork Join Pool, podemos alterar isso utilizando ele em conjunto com
// Executors.
// Para isto basta passar uma instância de Executors na sobrecarga do método
// `CompletableFuture.supplyAsync()`.
// Caso utilizado a instância com o ThreadFactory padrão do Executors, será
// utilizado o Pool de Threads, e caso queira adotar outros Frameworks de
// gerenciamento de threads, basta utilizar a sobrecarga dos métodos builders
// do Executors (newFixedThreadPool, newCachedThreadPool...) passando a regra
// de criação das threads, no caso aqui utilizamos threads padrão nativas.

// Resumo: O que muda é a instância do ExecutorService.

// Mais sobre Fork Join: http://www.inf.ufsc.br/~bosco.sobral/ensino/Fork-Join-Framework-Java.pdf

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Aula242Concurrent_CompletableFuture_ThreadFactory_mudandoDeForkJoinPoolParaPoolThreadsOuThreadsNativas_and_tambemUtilizandoExecutorsEmConjunto {
    public static void main(String[] args) {
        List<String> storesList = List.of("Store 1", "Store 2", "Store 3", "Store 4");
        long start; // time para calcular ms da execução
        long end;

        // --------------------------------------------------------------------

        // O que muda é a instância de ExecutorService:

        // --------------------------------------------------------------------

        // Para o padrão do Executors:  `pool-1-thread-1`
        ExecutorService executorServiceDefault = Executors.newFixedThreadPool(10);
        // Saída do método abaixo: Time passed using ExecutorService com default (pool of threads): 2051ms
        // --------------------------------------------------------------------

        // Para criar Executors utilizando threads nativas: `Thread-0`
        ExecutorService executorServiceThreadNativa = Executors.newFixedThreadPool(10, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        // Saída do método abaixo: Time passed using ExecutorService com threads nativas: 2030ms
        // --------------------------------------------------------------------

        // O Default do `CompletableFuture` é utilizar o `ForkJoinPool.commonPool-worker-1`
        // Saída do método abaixo: Time passed using Fork Join Pool (Sem utilizar Executors): 4049ms
        // --------------------------------------------------------------------
        // Utilizando alguma das instâncias personalizadas do ExecutorService:

        start = System.currentTimeMillis();
        List<CompletableFuture<Double>> prices = storesList.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> StoreService.getPricesSync(s)))
                .collect(Collectors.toList());

        prices.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());

        end = System.currentTimeMillis();
        System.out.printf("Time passed using Fork Join Pool (Sem utilizar Executors): %dms%n", end - start);

        // Getting prices sync for store: Store 1
        // pool-1-thread-1 generating price... <- antes era `ForkJoinPool.commonPool-worker-1`

        // --------------------------------------------------------------------


    }
}
