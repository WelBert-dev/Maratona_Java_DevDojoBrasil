package Q_threads.sincronismo;

// Ocorre quando duas ou mais threads bloqueiam algum recurso sincronizado
// em X ou seja, quando ocorre um bloqueio cruzado, assim ambas ficam
// aguardando o lock da outra e assim segue eternamente.

/* DeadLock:

    (Thread_01-)-------{->Recurso_01}
               \      ↗ (Thread_02 aguardando lock de Thread_01)
                \   /
                 \ /
                 / \
                /   \
               /      ↘ (Thread_01 aguardando lock de Thread_02)
    (Thread_02-)-------{->Recurso_02}
    
*/
public class Aula227Threads_DeadLock_problemaAoSincronizar {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Runnable r1 = () -> {
            synchronized (lock1) {
                System.out.println("Thread 01: Segurando lock1");
                System.out.println("Thread 01: Esperando lock2");
                synchronized (lock2) {
                    System.out.println("Thread 01: Segurando lock2");
                }
            }
        };
        Runnable r2 = () -> {
            synchronized (lock2) {
                System.out.println("Thread 02: Segurando lock2");
                System.out.println("Thread 02: Esperando lock1");
                synchronized (lock1) {
                    System.out.println("Thread 02: Segurando lock1");
                }
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
