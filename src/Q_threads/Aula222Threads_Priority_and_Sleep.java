package Q_threads;

public class Aula222Threads_Priority_and_Sleep {
    public static void main(String[] args) {

        Thread t1 = new Thread(new ThreadExample('A'), "Trabalhador A_01");
        Thread t2 = new Thread(new ThreadExample('B'), "Trabalhador B_02");
        Thread t3 = new Thread(new ThreadExample('C'), "Trabalhador C_03");
        Thread t4 = new Thread(new ThreadExample('D'), "Trabalhador D_04");

        // A depender da JVM que está executando os valores podem ser diferentes, então
        // é recomendável utilizar os Enums para garantir valores válidos.

        // LEMBRANDO: É apenas uma indicação de desejo, Scheduler é quem escolhe quem executa.
        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.NORM_PRIORITY); // 5
        t3.setPriority(Thread.MAX_PRIORITY); // 10

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // --------------------------------------------------------------------
        // Sleep é uma das pequenas ações dentro das threads na qual podemos garantir
        // que irá executar, lembrando que o método é estático pois nenhuma thread
        // pode interferir em outra, apenas a própria pode se colocar para dormir.

        try {
            Thread.sleep(2000); // main dorme por 2 segundos
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
