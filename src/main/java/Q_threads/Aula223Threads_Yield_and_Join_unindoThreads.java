package Q_threads;

/*

- Thread.yield(): Da uma dica ao scheduler que a thread corrente pode ficar
"Runnable", ou seja pode disponibilizar a vez para outra thread (APENAS UMA DICA),
ou seja a Thread pode deixar de executar.

- objThread.join(): Indica ao scheduler para parar a execução da thread corrente
e se unir a thread objThread, ou seja para o fluxo corrente até o final do método
run() da thread indicada.

Os métodos yield e join são utilizados em programação concorrente para controlar o
fluxo de execução das threads.

O método yield é utilizado para permitir que outras threads tenham a oportunidade
de executar. Quando uma thread chama o método yield, ela indica ao sistema que está
disposta a liberar a CPU, permitindo que outras threads executem.
No entanto, é importante notar que chamar o método yield não garante que a thread
irá ceder a CPU imediatamente, pois isso depende do escalonador de threads do sistema
operacional.

Já o método join é utilizado para aguardar o término da execução de uma thread.
Quando uma thread chama o método join em outra thread, ela aguarda até que a outra
thread termine sua execução antes de continuar a sua própria execução.
Isso é útil em situações em que uma thread precisa esperar pelo resultado de outra
thread antes de prosseguir.

Por exemplo, suponha que temos duas threads em nosso programa: a thread A e a thread B.
Se a thread A precisa utilizar um resultado que será produzido pela thread B, podemos
chamar o método join na thread B a partir da thread A.
Isso fará com que a thread A aguarde até que a thread B termine sua execução e produza
o resultado necessário antes de prosseguir.

Em resumo, o método yield é utilizado para permitir que outras threads tenham a
oportunidade de executar, enquanto o método join é utilizado para aguardar o término
da execução de uma thread antes de continuar a execução de outra thread.

*/


public class Aula223Threads_Yield_and_Join_unindoThreads {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadExample2('K'));
        Thread t2 = new Thread(new ThreadExample2('M'));

        t1.start();
        try {
            t1.join(); // main se uni a thread 1 e la fica até finalizar o run()
            t2.start(); // Depois que acabar o run() do t1 a thread main volta para este ponto e executa.

            System.out.printf(Thread.currentThread().getName() + " diz Terminou!");
        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadExample2 extends Thread {
    private final char c;
    public ThreadExample2(char c) {
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

//            if(i == 200) {
//                Thread.yield();
//            }

        }
    }
}
