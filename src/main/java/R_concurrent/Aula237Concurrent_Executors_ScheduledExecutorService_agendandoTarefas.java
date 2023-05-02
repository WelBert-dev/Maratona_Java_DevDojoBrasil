package R_concurrent;

/* Definições sobre a ScheduledExecutorService que extends ExecutorService:

ScheduledExecutorService é uma subclasse da classe ExecutorService (interface
na verdade) que é usada para agendar tarefas que devem ser executadas em um
momento específico ou em intervalos regulares de tempo.
Ele é usado para executar tarefas agendadas em threads separadas, permitindo
que o aplicativo execute tarefas em segundo plano enquanto continua a realizar
outras atividades.


---> Algumas aplicações e utilidades do ScheduledExecutorService incluem:

    - Tarefas de manutenção agendadas: Pode usar o ScheduledExecutorService para
    agendar tarefas de manutenção, como limpeza de cache, atualização de banco de
    dados, backup de arquivos, entre outros.

    - Tarefas de notificação agendadas: Pode usar o ScheduledExecutorService para
    enviar notificações em momentos específicos ou em intervalos regulares, como
    envio de emails de lembrete, notificações push, entre outros.

    - Tarefas de agendamento de processamento em lotes: Pode ser usado para agendar
    o processamento em lotes em um horário específico, como processamento de arquivos
    de log ou processamento de arquivos de entrada em horários programados.

    - Tarefas de monitoramento: Pode ser usado para agendar tarefas de monitoramento
    para verificar se um serviço está funcionando corretamente, se os recursos do
    sistema estão sendo usados adequadamente, entre outras verificações.

    - Tarefas de sincronização de dados: Pode ser usado para agendar tarefas de
    sincronização de dados, como sincronização de dados entre bancos de dados ou
    sincronização de dados com serviços de nuvem.

Em resumo, o ScheduledExecutorService é usado para agendar tarefas que devem ser
executadas em um momento específico ou em intervalos regulares de tempo.
Ele pode ser usado em várias aplicações, como tarefas de manutenção agendadas,
tarefas de notificação agendadas, tarefas de agendamento de processamento em lotes,
tarefas de monitoramento e tarefas de sincronização de dados.


---> Diferenças para cada implementação dela:

* ScheduledThreadPoolExecutor: Esta é a classe padrão que implementa a interface
ScheduledExecutorService. Ele cria um pool de threads que pode ser usado para
executar tarefas agendadas. Ele também fornece vários métodos para agendar
tarefas, como schedule(), scheduleAtFixedRate() e scheduleWithFixedDelay().

* SingleThreadScheduledExecutor: Esta classe cria um executor que usa uma única
thread para executar tarefas agendadas. É útil para tarefas que precisam ser
executadas em ordem ou quando a sincronização é necessária.

* ScheduledExecutorCompletionService: Esta classe extends a classe ExecutorCompletionService
e adiciona recursos de agendamento de tarefas. Ele pode ser usado para executar
várias tarefas assíncronas e recuperar seus resultados em ordem de conclusão.

* ForkJoinPool: Esta é uma classe de pool de threads que implementa a interface
ExecutorService e pode ser usada para executar tarefas agendadas em um ambiente
de programação paralela. Ele usa uma abordagem de divisão e conquista para dividir
tarefas grandes em tarefas menores e executá-las em paralelo.

* WorkStealingPool: Esta classe também implementa a interface ExecutorService e
pode ser usada para executar tarefas agendadas em um ambiente de programação
paralela. Ele usa um algoritmo de roubo de trabalho para equilibrar a carga de
trabalho em vários threads.


---> Diferenças de uso para cada classe que implementa:

As diferentes classes que implementam a interface ScheduledExecutorService foram
criadas para atender a diferentes requisitos de programação. As principais diferenças
entre as classes são a forma como elas gerenciam o número de threads e o comportamento
de execução das tarefas agendadas.

* ScheduledThreadPoolExecutor: Esta é a implementação padrão da interface. Ele usa
um pool de threads fixo para executar as tarefas agendadas. Você pode especificar
o tamanho do pool ao criar uma instância da classe.
Ele fornece métodos para agendar tarefas de maneira ad-hoc ou para executá-las em
intervalos regulares. Ele também suporta a execução de tarefas com um atraso
específico em relação ao horário atual.

* SingleThreadScheduledExecutor: Esta classe cria um executor que usa uma única
thread para executar tarefas agendadas. Ele é útil quando você precisa garantir
que as tarefas sejam executadas em uma ordem específica ou quando a sincronização
é necessária.
Ele não é adequado para aplicativos que precisam executar muitas tarefas simultaneamente.

* ScheduledExecutorCompletionService: Esta classe extends a classe ExecutorCompletionService
e adiciona recursos de agendamento de tarefas.
Ele pode ser usado para executar várias tarefas assíncronas e recuperar seus resultados
em ordem de conclusão.
Ele é útil para aplicativos que precisam executar várias tarefas em paralelo e
recuperar os resultados em uma ordem específica.

* ForkJoinPool: Esta é uma classe de pool de threads que implementa a interface
ExecutorService e pode ser usada para executar tarefas agendadas em um ambiente
de programação paralela.
Ele usa uma abordagem de divisão e conquista para dividir tarefas grandes em
tarefas menores e executá-las em paralelo.
É especialmente útil para aplicativos que precisam executar tarefas que podem
ser divididas em tarefas menores e executadas em paralelo.

* WorkStealingPool: Esta classe também implementa a interface ExecutorService e
pode ser usada para executar tarefas agendadas em um ambiente de programação
paralela.
Ele usa um algoritmo de roubo de trabalho para equilibrar a carga de trabalho
em vários threads.
É especialmente útil para aplicativos que precisam executar tarefas que podem
ser executadas em paralelo, mas onde a carga de trabalho não é uniformemente
distribuída entre os threads.


*/

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Aula237Concurrent_Executors_ScheduledExecutorService_agendandoTarefas {

    public static void main(String[] args) {
        System.out.println("Iniciando programa em: "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        ScheduledExecutorServiceBeeper.beeperLoopingExecutionWithStop();
    }
}

class ScheduledExecutorServiceBeeper {
    private static final ScheduledExecutorService executorBeeper = Executors.newScheduledThreadPool(1);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
    private static final Runnable tarefa = () -> {
        System.out.println(LocalTime.now().format(formatter)+" beep");
    };
    
    private static ScheduledFuture<?> scheduleWithFixedDelay;

    // Vai executar após 5 segundos MAS APENAS UMA VEZ:
    public static void beeperOneExecution() {
        executorBeeper.schedule(tarefa, 5, TimeUnit.SECONDS); // aqui agenda a tarefa no escalonador
    }

    // Vai executar em looping, a cada x tempo INFINITAMENTE:
    public static void beeperLoopingExecutionNotStop() {
        scheduleWithFixedDelay = executorBeeper.scheduleWithFixedDelay(tarefa, 1, 5, TimeUnit.SECONDS);
    }

    // Vai executar em looping, a cada x tempo e com limitação:
    // OBS: Para esse método precisamos da referência do scheduler que desejamos parar!!

    // A diferença entre `scheduleWithFixedDelay` e `scheduleAtFixedRate` é que
    // o scheduleWithFixedDelay começa a contagem a partir do momento em que a thread
    // esta running, ou seja se anteriormente ela ficar sleep, vai somar o tempo
    // de sleep + delay em que ela ficará wait
    // Já a `scheduleAtFixedRate` considera o tempo total, ou seja, considera também o
    // tempo de sleep passado, subtraindo esse tempo de sleep - delay

    // Exemplo: Thread dorme por 2 segundos, e definimos o delay de 5 segundos,
    // então a scheduleWithFixedDelay vai somar 2 + 5 segundos e executará a tarefa em 7 segundos
    // Já a `scheduleAtFixedRate` vai considerar também o tempo de sleep, então irá subtrair
    // 2 - 5 e executará a tarefa após 3 segundos do sleep (pois o sleep foi de 2s) somando e assim
    // chega no tempo de 5 segundos definidos no delay.

    // Ou seja, um começa a contar o tempo a partir do momento em que saiu de wait para running
    // que é o `scheduleWithFixedDelay` e o outro `scheduleAtFixedRate` considera o tempo em que
    // ficou wait também, assim ele considera o tempo de sleep() também, diferente do anterior
    // que não considera.
    public static void beeperLoopingExecutionWithStop() {
        scheduleWithFixedDelay = executorBeeper.scheduleWithFixedDelay(tarefa, 1, 5, TimeUnit.SECONDS);

        // Utilizando um schedule para parar outro:
        executorBeeper.schedule((Runnable) () -> {
            System.out.println("Parando a execução do scheduleWithFixedDelay que executa infinitamente!");

            scheduleWithFixedDelay.cancel(false);
            // O parâmetro indica se deseja interromper a execução em andamento
            // deste ExecutorService

            // Após cancelar, se desejamos finalizar programa deve-se shutDown:
            executorBeeper.shutdown();

        }, 20, TimeUnit.SECONDS);

        // Iniciando programa em: 06:49:35
        // 06:49:36 beep
        // 06:49:41 beep
        // 06:49:46 beep
        // 06:49:51 beep
        // Parando a execução do scheduleWithFixedDelay que executa infinitamente!
        //
        // Process finished with exit code 0 -> aqui é por conta do shutDown()
    }
}
