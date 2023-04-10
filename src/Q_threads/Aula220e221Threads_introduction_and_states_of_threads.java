package Q_threads;

/* States Of Threads:

- NEW: O estado inicial de uma thread. Quando uma thread é criada, mas ainda não
foi iniciada, ela está em estado NEW.

- RUNNABLE: Quando a thread é iniciada com o método start(), ela passa para o
estado RUNNABLE. Isso indica que a thread está pronta para ser executada, mas
ainda não necessariamente está executando no momento.

- BLOCKED: Uma thread pode entrar no estado BLOCKED quando está esperando para
adquirir um monitor de objeto (ou seja, um bloqueio). Isso pode acontecer, por
exemplo, quando uma thread está tentando acessar um recurso que já foi bloqueado
por outra thread.

- WAITING: Uma thread pode entrar no estado WAITING quando está esperando
indefinidamente por outra thread. Isso pode acontecer, por exemplo, quando uma
thread está esperando por outra thread para liberar um recurso que está sendo
usado.

- TIMED_WAITING: Uma thread pode entrar no estado TIMED_WAITING quando está
esperando por uma determinada quantidade de tempo por outra thread. Isso pode
acontecer, por exemplo, quando uma thread está esperando por uma resposta de
uma conexão de rede por um tempo determinado.
    - Ou também Waiting Blocked.

- TERMINATED: Quando uma thread conclui sua execução ou é interrompida, ela passa
para o estado TERMINATED.


                            [Waiting Blocked]<\
                           /                   \
                          /                     \
   [New]----> [Runnable]</ <-------------------> [Running]----> [Dead]

Obs: scheduler que escolhe quando uma Thread pode ser executada, nós apenas deixamos ela "Runnable".

*/

/* Existem dois tipos de Threads: Daemon e User

- Daemon: Threads criadas pelo Java e pode ser encerrada a qualquer momento, ou
seja, quando acaba toda a execução das threads do tipo User esses podem ser
interrompidas e finalizar de vez a execução
    Exemplo: Coletor de lixo, aonde ao acabar a execução do programa, em casos
    de o coletor estar em running o programa simplismente vai encerrar.

- User: Threads criadas por nós desenvolvedores, essas tem prioridade e só é
encerrada ao final do programa, ou quando nós matar elas.

* Objetivo: As threads do tipo daemon são usadas para fornecer serviços para
outras threads, enquanto as threads do tipo usuário são usadas para executar
tarefas principais do programa.

* Prioridade de execução: As threads do tipo usuário têm prioridade de execução
mais alta do que as threads do tipo daemon. Isso significa que as threads do
tipo usuário serão executadas primeiro e terão mais tempo de CPU do que as
threads do tipo daemon.

* Finalização: Quando todas as threads do tipo usuário terminam de executar, o
programa é finalizado e todas as threads do tipo daemon são interrompidas
automaticamente e encerradas. Isso significa que as threads do tipo daemon não
impedem que o programa termine, mas as threads do tipo usuário sim.

* Comportamento após a criação: As threads do tipo daemon são iniciadas como
daemon usando o método setDaemon(true), enquanto as threads do tipo usuário são
iniciadas normalmente.

As threads do tipo daemon são úteis para fornecer serviços em segundo plano, como
limpeza de memória, monitoramento de arquivos, etc. Essas threads geralmente não
precisam ser executadas até o final e podem ser interrompidas quando todas as
threads do tipo usuário terminarem de executar.
Por outro lado, as threads do tipo usuário são responsáveis por executar as
principais tarefas do programa e não devem ser interrompidas até que o programa
seja finalizado.

*/

public class Aula220e221Threads_introduction_and_states_of_threads {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadStatesExample());

        // Pegando o Nome da Thread corrente em runtime:
        // System.out.println(Thread.currentThread().getName()); // main

        ThreadExample t1 = new ThreadExample('A');
        ThreadExample t2 = new ThreadExample('B');
        ThreadExample t3 = new ThreadExample('C');
        ThreadExample t4 = new ThreadExample('D');
        t1.run(); // Ainda será a Thread main que ira executar,
        t2.run(); // Não samos nós que chamamos o método run, e sim o proprio Java
        t3.run(); // O método correto é start()
        t4.run(); //

        t1.start(); // Correto, porém deve-se saber que não existe ordem na execução
        t2.start(); // apenas podemos explicitar que desejamos priorizar alguma thread
        t3.start(); // ainda sim, é o scheduller que irá decidir quando uma thread
        t4.start(); // irá executar ou mudar seus states durante a execução.

        // A depender da JVM que está executando os valores podem ser diferentes, então
        // é recomendável utilizar os Enums para garantir valores válidos.
        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.NORM_PRIORITY); // 5
        t3.setPriority(Thread.MAX_PRIORITY); // 10


        System.out.println();
        // --------------------------------------------------------------------

        // States Of Threads:

        // NEW state
        System.out.println("Thread state: " + thread.getState());

        // RUNNABLE state
        thread.start();
        System.out.println("Thread state: " + thread.getState());

        // TIMED_WAITING state
        Thread.sleep(1000);
        System.out.println("Thread state: " + thread.getState());

        // BLOCKED state
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("Thread state: " + thread.getState());

        // TERMINATED state
        thread.join();
        System.out.println("Thread state: " + thread.getState());
    }
}
class ThreadStatesExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread running...");
    }
}

class ThreadExample extends Thread {
    private final char c;
    public ThreadExample(char c) {
        this.c = c;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            System.out.print(c);
            if (i % 100 == 0){
                System.out.println();
            }
        }
    }
}
