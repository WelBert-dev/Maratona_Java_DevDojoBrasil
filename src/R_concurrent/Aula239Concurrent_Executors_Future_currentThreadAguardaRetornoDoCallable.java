package R_concurrent;

// Obs: Sempre que trabalhar com execuções paralelas definir um timeout para
// evitar travar a current thread que está aguardando finalizar execução
// de algum Callable por muito tempo, lançando a exception e tratando ela
// adequadamente.

// Obs: Atenção ao se utilizar Future em fluxos Streams! apresenta mesmo problema
// estudado em Aula241!!

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


---> Métodos presentes na interface:

- boolean cancel(boolean mayInterruptIfRunning): É usado para cancelar a tarefa
associada à instância de Future. O parâmetro boolean "mayInterruptIfRunning"
indica se a thread em execução deve ser interrompida. Se a tarefa já foi concluída,
o método retorna false.

- boolean isCancelled(): É usado para verificar se a tarefa associada à instância
de Future foi cancelada. Se a tarefa foi cancelada, o método retorna true.

- boolean isDone(): É usado para verificar se a tarefa associada à instância de
Future foi concluída. Se a tarefa foi concluída, o método retorna true.

- V get(): É usado para obter o resultado da tarefa associada à instância de
Future. Se a tarefa ainda não foi concluída, o método bloqueia a current thread
que chamou o get() até que o resultado esteja disponível. O tipo de retorno do
método é definido pelo tipo genérico da interface Future.

- V get(long timeout, TimeUnit unit): É usado para obter o resultado da tarefa
associada à instância de Future com um tempo limite especificado. Se a tarefa
ainda não foi concluída, o método bloqueia a current thread que esta chamando
esse método, até que o resultado esteja disponível ou o tempo limite seja atingido.
O parâmetro "timeout" especifica o tempo limite e o parâmetro "unit" especifica a
unidade de tempo.

Esses métodos são comuns a todas as classes que implementam a interface Future.
Os métodos específicos de cada classe, como métodos para encadear operações
assíncronas em CompletableFuture, são adicionados para fornecer recursos
adicionais para lidar com resultados assíncronos.


---> Mais classes e Interfaces com mesmo propósito, porém com funções adicionais:

* CompletableFuture: É a classe mais avançada e versátil que implementa a interface
Future. Ela fornece muitos métodos úteis para trabalhar com resultados assíncronos,
como encadear várias operações assíncronas e manipular erros de forma eficiente.
O CompletableFuture também permite que você especifique uma execução em segundo
plano com um Executor personalizado epara operações de longa duração, incluindo
chamadas de rede, processamento de arquivos grandes e outras tarefas assíncronas.

* FutureTask: É uma classe concreta que implementa a interface Future e é adequada
para tarefas que precisam ser executadas em segundo plano. Você pode passar uma
instância de FutureTask para uma instância do ExecutorService e a tarefa será
executada em uma thread separada. FutureTask é uma opção mais simples que o
CompletableFuture e fornece apenas o básico para executar uma tarefa em segundo
plano.

* ScheduledFuture: É uma subinterface da interface Future e é usada para agendar
tarefas para serem executadas em algum momento futuro. Você pode usar uma instância
de ScheduledFuture para agendar tarefas periódicas ou para agendar uma tarefa para
ser executada após um determinado período de tempo.


---> Diferenças entre o ScheduledFuture e o ScheduledExecutorService:

O ScheduledExecutorService é uma interface que estende a interface ExecutorService
e define um conjunto de métodos para agendar tarefas para serem executadas em um
momento futuro, incluindo tarefas periódicas ou para serem executadas após um
determinado período de tempo.

O ScheduledFuture, por sua vez, é uma subinterface de Future que estende a interface
Delayed. Ele representa uma tarefa agendada para ser executada em um momento futuro
pelo ScheduledExecutorService.

Em resumo, o ScheduledExecutorService é usado para criar e gerenciar agendamentos
de tarefas e o ScheduledFuture é usado para representar uma tarefa agendada e
fornecer métodos adicionais para trabalhar com o tempo restante até a execução
da tarefa.

Os métodos adicionais fornecidos pela interface ScheduledFuture incluem:

- long getDelay(TimeUnit unit): É usado para obter o tempo restante até a execução
da tarefa agendada em uma unidade de tempo específica.

- int compareTo(Delayed other): É usado para comparar o tempo restante até a execução
da tarefa agendada com outro objeto Delayed. Se o tempo restante for menor que o do
outro objeto, este método retorna um valor negativo. Se o tempo restante for maior
que o do outro objeto, este método retorna um valor positivo. Se o tempo restante for
igual ao do outro objeto, este método retorna zero.

Ambas as interfaces, ScheduledExecutorService e ScheduledFuture, são usadas em
conjunto para criar e gerenciar tarefas agendadas.

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
