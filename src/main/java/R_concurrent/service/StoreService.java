package R_concurrent.service;

// Fonte interessante sobre: https://www.devmedia.com.br/trabalhando-com-completablefuture-no-java/32854

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
