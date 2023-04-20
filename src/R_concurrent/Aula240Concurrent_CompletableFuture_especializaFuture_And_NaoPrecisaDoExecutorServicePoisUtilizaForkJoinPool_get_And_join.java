package R_concurrent;

// Diferenças do método `get()` e `join()` do CompletableFuture<V>:
// Ambos fazem a mesma coisa porém o join() não lança exceptions!
// (pelomenos Checked, porém lança Unchecked em Runtime)
// Ou seja, Utilizados para obter o valor do CompletableFuture<V>

// Em teoria o `join()` é mais performatico que o `get()`, porém ambos apresentaram
// a mesma performance nos testes.

/* Definição mais a fundo sobre get() e join() do CompletableFuture<V>:

A principal diferença entre esses dois métodos é a forma como eles tratam exceções:

* O método get() lança uma InterruptedException e uma ExecutionException (que
encapsula a exceção original lançada pelo processo assíncrono) quando o resultado
estiver disponível. Ele também espera pelo resultado, bloqueando a thread atual
até que o processo assíncrono seja concluído.

* O método join() é semelhante ao get(), mas lança apenas a ExecutionException.
Ele também espera pelo resultado, mas não lança a InterruptedException e é
geralmente usado em contextos em que essa exceção não é relevante.

Em resumo, o get() é usado quando se precisa lidar com a exceção InterruptedException
e o join() é usado quando não é necessário tratá-la. Em ambos os casos, é importante
lembrar que esses métodos são blocantes e podem causar problemas de desempenho se
forem usados incorretamente em aplicações que precisam de alta escalabilidade e
eficiência.


---> Em quais cenários escolher um ou outro?

A escolha entre ambos depende do contexto em que você está usando o CompletableFuture
e das exceções que você espera lidar.

Se você está em um contexto em que pode lidar com a exceção InterruptedException
(por exemplo, em um aplicativo de desktop em que o usuário está interagindo com a
interface gráfica do usuário), é recomendável usar o get(), já que ele lança essa
exceção e permite que você lide com ela.

Por outro lado, se você está em um contexto em que não é necessário lidar com a
exceção InterruptedException (por exemplo, em um servidor de aplicativos que
processa solicitações em um pool de threads), é recomendável usar o join(), pois
é mais simples e mais eficiente em termos de desempenho.

Em geral, a recomendação é evitar o uso de get() e join() sempre que possível,
pois eles bloqueiam a thread atual até que o resultado seja retornado, o que
pode causar problemas de desempenho em aplicações com alta carga de trabalho.
Em vez disso, é recomendável usar métodos como thenApply(), thenAccept() e
thenRun(), que permitem que você especifique ações a serem executadas quando
o resultado estiver disponível, sem bloquear a thread atual.

*/


import java.util.concurrent.*;

public class Aula240Concurrent_CompletableFuture_especializaFuture_And_NaoPrecisaDoExecutorServicePoisUtilizaForkJoinPool_get_And_join {
    public static void main(String[] args) {

        // Testando a execução sincronizada já conhecida:
//        searchPricesSync(); // Time passed to searchPricesSync 8044ms (2s por Store)

        // Testando a execução assincrona com Future<V>:
        // Utiliza o ExecutorService (threadPool)
//        searchPricesAsyncWithFuture(); // Time passed to searchPricesSync 2029ms (TODOS Store por 2s)

        // Testando a execução assincrona com CompletableFuture<V>:
        // Não Utiliza o ExecutorService (ForkJoinPool por debaixo dos panos).
        // Testando os métodos `get()` e `join()`.
        // Obs: Como não utiliza ExecutorService, não é nceessário finalizar com shutDown().
        // Ao utilizar `get()` é necessário tratar exceções checked, diferente do `join()` unchecked.
        // Em Teoria o `join()` é mais performatico, porém nos testes ambos foram iguais.
        searchPricesAsyncWithCompletableFuture(); // Time passed to searchPricesSync 4036ms
    }

    // Testando a execução sincronizada ja conhecida:
    public static void searchPricesSync() {
        long start = System.currentTimeMillis();
        System.out.println(StoreService.getPricesSync("Store 1"));
        System.out.println(StoreService.getPricesSync("Store 2"));
        System.out.println(StoreService.getPricesSync("Store 3"));
        System.out.println(StoreService.getPricesSync("Store 4"));
        long end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesSync %dms%n", end - start);
    }
    // Testando a execução assincrona, com o Future<V>: (Utiliza o ExecutorService)
    public static void searchPricesAsyncWithFuture() {
        long start = System.currentTimeMillis();

        try {
            // Assim ele continua executando de maneira sincronizada, devemos utilizar
            // variável de referência para funcionar corretamente de maneira Async
            // Demorando: Time passed to searchPricesSync 8062ms (2s de sleep por store)
//            System.out.println(StoreService.getPricesAsyncWithFuture("Store 1").get());
//            System.out.println(StoreService.getPricesAsyncWithFuture("Store 2").get());
//            System.out.println(StoreService.getPricesAsyncWithFuture("Store 3").get());
//            System.out.println(StoreService.getPricesAsyncWithFuture("Store 4").get());

            // Forma correta: Time passed to searchPricesSync 2029ms (Aqui demora os 2 segundos de sleep para TODOS)
            Future<Double> pricesAsyncWithFuture1 = StoreService.getPricesAsyncWithFuture("Store 1");
            Future<Double> pricesAsyncWithFuture2 = StoreService.getPricesAsyncWithFuture("Store 2");
            Future<Double> pricesAsyncWithFuture3 = StoreService.getPricesAsyncWithFuture("Store 3");
            Future<Double> pricesAsyncWithFuture4 = StoreService.getPricesAsyncWithFuture("Store 4");

            System.out.println(pricesAsyncWithFuture1.get());
            System.out.println(pricesAsyncWithFuture2.get());
            System.out.println(pricesAsyncWithFuture3.get());
            System.out.println(pricesAsyncWithFuture4.get());

            StoreService.executorShutDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesSync %dms%n", end - start);
    }
    // Testando a execução assincrona, com o CompletableFuture<V>:
    // Obs: Especilização do Future<V> que não utiliza o ExecutorService
    // Utiliza por debaixo dos panos o ForkJoinPool
    public static void searchPricesAsyncWithCompletableFuture() {
        long start = System.currentTimeMillis();

//        try {
            // Mesma lógica do Future<V> aonde deve utilizar variável de referência
            // antes de fazer get() para funcionar corretamente de forma Async
            // Demorando: Time passed to searchPricesSync 8057ms (2s de sleep por store)
//            StoreService.getPricesAsyncWithCompletableFuture("Store 1").get();
//            StoreService.getPricesAsyncWithCompletableFuture("Store 2").get();
//            StoreService.getPricesAsyncWithCompletableFuture("Store 3").get();
//            StoreService.getPricesAsyncWithCompletableFuture("Store 4").get();

            // Forma correta: Time passed to searchPricesSync 4045ms (Aqui demora os 4 segundos para TODOS, menos performatico que o Future<V>)
//            CompletableFuture<Double> pricesAsyncWithCompletableFuture1 =
//                    StoreService.getPricesAsyncWithCompletableFuture("Store 1");
//            CompletableFuture<Double> pricesAsyncWithCompletableFuture2 =
//                    StoreService.getPricesAsyncWithCompletableFuture("Store 2");
//            CompletableFuture<Double> pricesAsyncWithCompletableFuture3 =
//                    StoreService.getPricesAsyncWithCompletableFuture("Store 3");
//            CompletableFuture<Double> pricesAsyncWithCompletableFuture4 =
//                    StoreService.getPricesAsyncWithCompletableFuture("Store 4");
//
//            System.out.println(pricesAsyncWithCompletableFuture1.get());
//            System.out.println(pricesAsyncWithCompletableFuture2.get());
//            System.out.println(pricesAsyncWithCompletableFuture3.get());
//            System.out.println(pricesAsyncWithCompletableFuture4.get());

            // Saída:
            // Getting prices async with CompletableFuture<V> for store: Store 1
            // Getting prices async with CompletableFuture<V> for store: Store 2
            // Getting prices async with CompletableFuture<V> for store: Store 3
            // Getting prices async with CompletableFuture<V> for store: Store 4
            // ForkJoinPool.commonPool-worker-1 generating price...
            // ForkJoinPool.commonPool-worker-2 generating price...
            // ForkJoinPool.commonPool-worker-3 generating price...
            // ForkJoinPool.commonPool-worker-1 generating price...
            // 3070.0
            // 4430.0
            // 1320.0
            // 520.0
            // Time passed to searchPricesSync 4045ms
            //
            // Process finished with exit code 0

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        // Com join() que não lança exceptions checked (Lança Unchecked em Runtime)
        // Time passed to searchPricesSync 4047ms
        // Em teoria era pra ser mais performatico que o get(), porém o tempo foi igual.
        CompletableFuture<Double> pricesAsyncWithCompletableFuture1 =
                StoreService.getPricesAsyncWithCompletableFuture("Store 1");
        CompletableFuture<Double> pricesAsyncWithCompletableFuture2 =
                StoreService.getPricesAsyncWithCompletableFuture("Store 2");
        CompletableFuture<Double> pricesAsyncWithCompletableFuture3 =
                StoreService.getPricesAsyncWithCompletableFuture("Store 3");
        CompletableFuture<Double> pricesAsyncWithCompletableFuture4 =
                StoreService.getPricesAsyncWithCompletableFuture("Store 4");

        System.out.println(pricesAsyncWithCompletableFuture1.join());
        System.out.println(pricesAsyncWithCompletableFuture2.join());
        System.out.println(pricesAsyncWithCompletableFuture3.join());
        System.out.println(pricesAsyncWithCompletableFuture4.join());

        long end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesSync %dms%n", end - start);
    }
}

class StoreService {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static double priceGenerator() {
        System.out.printf("%s generating price...%n", Thread.currentThread().getName());
        delay();
        return ThreadLocalRandom.current().nextInt(1, 500) * 10;
    }
    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executorShutDown() {
        executorService.shutdown();
    }

    // Metodos geradores de threads que utilizam o priceGenerator() nas execuções:

    // Testando a execução sincronizada ja conhecida:
    public static double getPricesSync(String storeName) {
        System.out.printf("Getting prices sync for store: %s%n", storeName);
        return priceGenerator();
    }
    // Testando a execução assincrona, com o Future<V>:
    public static Future<Double> getPricesAsyncWithFuture(String storeName) {
        System.out.printf("Getting prices async with Future<V> for store: %s%n", storeName);

        return executorService.submit(StoreService::priceGenerator);
    }
    // Testando a execução assincrona, com o CompletableFuture<V>:
    // Obs: Não precisa de um ExecutorService!
    // Por debaixo dos panos ele utiliza o ForkJoinPool
    public static CompletableFuture<Double> getPricesAsyncWithCompletableFuture(String storeName) {
        System.out.printf("Getting prices async with CompletableFuture<V> for store: %s%n", storeName);

        return CompletableFuture.supplyAsync(StoreService::priceGenerator);
    }
}
