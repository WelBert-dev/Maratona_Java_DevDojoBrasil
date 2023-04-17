package R_concurrent;

// Interface BlockingQueue:

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Aula234Concurrent_ArrayBlockingQueue_threadFicaBloqueadaAguardandoLiberarEspacoParaAddDeAcordoComInitalCapacityDefinidaAoCriar {
    public static void main(String[] args) throws InterruptedException {
        // Definimos uma capacidade inicial obrigatória pois é por ela
        // que é identificado quando a thread deve esperar até liberar
        // espaço para add valores, OBS: A THREAD executando fica wait
        // até a liberação da lista!!!

        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);

        bq.put("Wellison");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
        System.out.println("Trying to add another value");
        //bq.put("Danielle"); // Aqui a thread main fica wait infinitamente até que bq.remove e etc..

        // Nunca será executado essa linha:
        //System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());

        // Solução: Executar outra thread que faça a remoção do valor para a main não ficar mais wait:

        Runnable runnableRemover = () -> {
            System.out.printf("%s removed the value, unAwait the main thread%n", Thread.currentThread().getName());
            bq.remove();
            try {
                Thread.sleep(2000);
                System.out.printf("%s removed the value, unAwait the main thread again%n", Thread.currentThread().getName());
                bq.remove();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(runnableRemover).start();
        bq.put("Danielle");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
        System.out.println("Trying to add another value");
        bq.put("Animal");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
    }

}
