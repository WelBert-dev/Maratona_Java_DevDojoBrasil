package R_concurrent;

// Obs: Sempre que trabalhar com execuções paralelas definir um timeout para
// evitar travar a current thread que está aguardando finalizar execução
// de algum Callable por muito tempo, lançando a exception e tratando ela
// adequadamente.

/* Definiçõs sobre a classe Future<V>

É uma interface que representa um valor futuro que pode ou não estar disponível.
Ela permite que você execute operações assíncronas em segundo plano enquanto sua
aplicação continua executando outras tarefas.

Em termos simples, um Future é uma promessa de que um valor será retornado em
algum momento futuro. Ele pode ser usado para executar operações em segundo
plano e retornar um resultado posteriormente.

---> Algumas aplicações comuns do Future incluem:

    - Execução de operações assíncronas: Pode ser usado para executar operações em
    segundo plano sem bloquear a thread principal do aplicativo.
    Isso é útil para operações que podem levar algum tempo para serem concluídas,
    como chamadas de rede ou processamento de arquivos grandes.

    - Criação de pipelines de processamento: Os Future podem ser encadeados para
    criar pipelines de processamento em que o resultado de uma operação é passado
    para a próxima operação.
    Isso é útil para processamento de dados em lote, como processamento de imagens
    ou análise de dados.

    - Sincronização de threads: Pode ser usado para sincronizar várias threads em
    um ponto específico em que os resultados das operações em segundo plano são
    necessários.

Para usar a classe Future, você precisa criar uma instância de um objeto Future e
fornecer uma implementação para a tarefa que será executada em segundo plano.
A tarefa é executada em uma thread separada e o resultado é armazenado na instância
do Future.

Então, quando o resultado estiver disponível, ele pode ser recuperado chamando o
método get() na instância do Future. Se o resultado ainda não estiver disponível,
a thread current que está chamando o método get() aguardará até que a tarefa seja
concluída e o resultado esteja disponível.

*/

import java.util.concurrent.*;

public class Aula239Concurrent_Executors_Future_currentThreadAguardaRetornoDoCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> dollarRequest = executorService.submit(() -> {
            System.out.printf("### %s Dentro do dollarRequest, Callable submitado pelo executor!!!%n", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
            System.out.printf("### %s Dentro do dollarRequest após 5 segundos de sleep, Callable finalizado pelo executor!!!%n", Thread.currentThread().getName());
            return 5.4D;
        });
        TimeUnit.SECONDS.sleep(3);
        System.out.println("*** Passou pelo submit");
        // ------------- [Main e Thread paralela executam ao mesmo tempo esse bloco ] -------
        // main executa em partalelo (soma += i até 1.000.000)
        System.out.println(doSomething());
        // main aguarda a execução em paralelo esperando até no máximo x time:
        Double dollarResponse = dollarRequest.get(6, TimeUnit.SECONDS);
        System.out.printf("*** %s finalmente esta com o dollarResponse: %f depois de esperar 3 segundos no máximo: ", Thread.currentThread().getName(), dollarResponse);
        // ----------------------------------------------------------------------------------

        executorService.shutdown();
    }
    // doSomething tradução: faz algo
    public static long doSomething() {
        System.out.printf("*** %s está fazendo algo...%n", Thread.currentThread().getName());
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        System.out.printf("*** %s finalizou o que estava fazendo...%n", Thread.currentThread().getName());
        return sum;
    }
}
