package Q_threads.sincronismo;

/* TOKEN reservada `synchronized`

Ela bloqueia algum recurso passado no parâmetro, assim quando uma thread
acessa tal recurso ele fica blocked para outras threads, assim podemos
garantir que outra thread só irá acessar tal recurso após a thread anterior
finalizar as execuções deste bloco.

A palavra reservada synchronized é usada para fornecer controle de concorrência
em threads. Ela é utilizada para garantir que apenas uma thread execute um bloco
de código ou um método sincronizado por vez, evitando assim que ocorram problemas
de concorrência como condições de corrida, inconsistências de dados ou deadlocks.

Quando um método ou um bloco de código é declarado como synchronized, ele se torna
um bloco de código crítico, o que significa que apenas uma thread pode executá-lo
por vez. Quando uma thread tenta acessar um bloco de código ou um método sincronizado,
ela deve primeiro adquirir o monitor associado a esse bloco de código ou método.
Se outro thread já estiver executando o bloco de código ou método sincronizado, a
thread que está tentando acessá-lo será bloqueada até que o monitor seja liberado.

O uso de synchronized é importante para evitar problemas de concorrência em
aplicações multithreaded, como condições de corrida em que duas ou mais threads
tentam acessar e modificar uma mesma variável ou objeto ao mesmo tempo, causando
um resultado indeterminado. Também ajuda a garantir a consistência dos dados,
evitando que as threads leiam ou escrevam em dados desatualizados ou inconsistentes.
Além disso, synchronized pode ser usado em conjunto com outros mecanismos de
sincronização, como objetos Lock ou Semaphore, para fornecer uma maior
flexibilidade e controle na implementação de threads seguras e eficientes.


*/

import Q_threads.domain.AccountModel;

public class Aula224e225Threads_sincronismoDeThreads_introduction implements Runnable {
    private AccountModel account = new AccountModel();
    public static void main(String[] args) {
        Aula224e225Threads_sincronismoDeThreads_introduction runnable1 = new Aula224e225Threads_sincronismoDeThreads_introduction();
        Thread wellison = new Thread(runnable1, "Wellison");
        Thread danielle = new Thread(runnable1, "Danielle");

        wellison.start();
        danielle.start();

    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            withdraw(10);
            if (account.getBalance() < 0) {
                System.out.println("FODEOOO ACABOU o SALDO");
            }
        }
    }
    private synchronized void withdraw(int amount) {
//        synchronized (account){ // Não precisa mais pois o próprio método é.
            if (account.getBalance() >= amount) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Trabalhador/Thread: " + Thread.currentThread().getName() +
                        "\nRealizando saque de "+amount);

                account.withdraw(amount);

                System.out.println("Trabalhador/Thread: " + Thread.currentThread().getName() +
                        " completou o saque! :D\nSaldo disponível: "+ account.getBalance());
                System.out.println("---------------------------------------------------------");
            } else {
                System.out.println("---------------------------------------------------------");
                System.out.println("Não tem dinheiro disponível para a thread/trabalhador: " + Thread.currentThread().getName() +
                        " completar o saque! ;-;\nSaldo disponível: "+ account.getBalance());
                System.out.println("---------------------------------------------------------");
            }
        }
//    }
}
