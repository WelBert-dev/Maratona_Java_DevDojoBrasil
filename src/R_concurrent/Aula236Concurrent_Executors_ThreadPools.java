package R_concurrent;

// Executors abstrai a complexidade e facilita a criação e manipulação de threads

/* Definições sobre Executors e ExecutorService:

O Executors é uma classe da API de concorrência que fornece métodos para criar e
gerenciar pools de threads. Ele é usado para simplificar a criação e gerenciamento
de threads.

O Executors fornece vários métodos estáticos que retornam objetos do tipo
ExecutorService. Esses objetos permitem que você execute tarefas em paralelo
usando várias threads.


---> Alguns dos métodos mais comuns da classe Executors são:

    - newSingleThreadExecutor(): Cria um executor que executa tarefas em uma
    única thread.

    - newFixedThreadPool(n): Cria um executor que executa tarefas em um pool
    de n threads.

    - newCachedThreadPool(): Cria um executor que cria threads conforme necessário
    e reutiliza threads ociosas existentes.

    - newScheduledThreadPool(n): Cria um executor que executa tarefas com um atraso
    ou em um horário específico.

Além disso, o Executors fornece métodos para interromper a execução de um conjunto
de tarefas em um executor e métodos para agendar tarefas para execução em um
horário específico ou com um atraso específico.


*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Aula236Concurrent_Executors_ThreadPools {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors()); // 4
        ExecutorService executorService4Threads = Executors.newFixedThreadPool(4);// limita a quantidade de instanciação de threads possivel

        // Podemos controlar a execução das threads sem precisar agora executar o .start()
        executorService4Threads.execute(new Printer(1));
        executorService4Threads.execute(new Printer(2));
        executorService4Threads.execute(new Printer(3));
        executorService4Threads.execute(new Printer(4));
        executorService4Threads.execute(new Printer(5));
        // Podemos criar mais execuções passando do número fixo estabelecido anteriormente
        // porém ele ira executar 4 e só irá executar os próximos após finalizar os 4.
        // Assim controlamos o número de criação de threads mesmo se executado mais que o definido.
        // E sempre irá executar em paralelo apenas a quantidade de threads definidas.

        // pool-1-thread-1 inicio: 1
        // pool-1-thread-4 inicio: 4
        // pool-1-thread-3 inicio: 3
        // pool-1-thread-2 inicio: 2
        // pool-1-thread-1 finalizou! -> aqui após finalizar da start na 5ª thread.
        // pool-1-thread-3 finalizou!
        // pool-1-thread-1 inicio: 5
        // pool-1-thread-4 finalizou!
        // pool-1-thread-2 finalizou!
        // pool-1-thread-1 finalizou!

        // Dando "Sinal" que desejamos encerrar as execuções do ExecutorService:
        // Obs: Só irá finalizar após a finalização das threads em execução!!
        // executorService4Threads.shutdown();


        // Travando a thread current (main) até terminar a execução do ExecutorService:
        // OBS: SOLUÇÂO HORRIVEL
         while(!executorService4Threads.isTerminated()){}

        // --------------------------------------------------------------------

        // Cria a quantidade de threads necessária para a execução, e re-utiliza elas
        // para outras execuções caso finalizado, ao invés de criar mais sem necessidade:
        ExecutorService executorServiceRealocateUsingCached = Executors.newCachedThreadPool();
        executorServiceRealocateUsingCached.execute(new Printer(1));
        executorServiceRealocateUsingCached.execute(new Printer(2));
        executorServiceRealocateUsingCached.execute(new Printer(3));
        executorServiceRealocateUsingCached.execute(new Printer(4));
        executorServiceRealocateUsingCached.execute(new Printer(5));
        Thread.sleep(5000); // Sleep para simular essa de re-utilizar threads
        executorServiceRealocateUsingCached.execute(new Printer(6));
        executorServiceRealocateUsingCached.execute(new Printer(7));
        executorServiceRealocateUsingCached.execute(new Printer(8));
        executorServiceRealocateUsingCached.execute(new Printer(9));
        executorServiceRealocateUsingCached.execute(new Printer(10));

        // pool-2-thread-1 inicio: 1
        // pool-2-thread-5 inicio: 5
        // pool-2-thread-4 inicio: 4
        // pool-2-thread-3 inicio: 3
        // pool-2-thread-2 inicio: 2
        // pool-2-thread-4 finalizou!
        // pool-2-thread-1 finalizou!
        // pool-2-thread-5 finalizou!
        // pool-2-thread-3 finalizou!
        // pool-2-thread-2 finalizou!
        // pool-2-thread-3 inicio: 7
        // pool-2-thread-5 inicio: 9
        // pool-2-thread-1 inicio: 10
        // pool-2-thread-4 inicio: 8
        // pool-2-thread-2 inicio: 6
        // pool-2-thread-2 finalizou!
        // pool-2-thread-4 finalizou!
        // pool-2-thread-3 finalizou!
        // pool-2-thread-5 finalizou!
        // pool-2-thread-1 finalizou!

        // Apenas foram criadas 5 threads ao invéz das 10 que eu executei, e mesmo assim
        // executou 10 processamentos (que era o objetivo), pois ao finalizar as
        // 5 primeiras ele re-utiliza essas mesmas 5 threads criadas primeiro, para
        // executar as 5 restantes (por conta do sleep(5000) utilizado a cima ).
        // com isso temos o resultado esperado, porém otimizando a criação de threads
        // pois se torna desnecessário quando as primeiras threads finalizam suas
        // tarefas, podendo assim executar outros servicos na mesma thread finalizada
        // anteriormente.

    }
}

class Printer implements Runnable {
    private final int num;
    public Printer(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        System.out.printf("%s inicio: %d%n", Thread.currentThread().getName(), num);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s finalizou!%n", Thread.currentThread().getName());
    }
}
