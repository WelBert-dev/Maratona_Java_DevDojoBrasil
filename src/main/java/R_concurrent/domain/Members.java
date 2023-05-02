package R_concurrent.domain;

// conditions.signalAll: Equivalente do obj.notifyAll em blocos `synchronized`.
// conditions.signal: Equivalente do obj.notify em blocos `synchronized`.
// conditions.await: Equivalente do obj.wait em blocos `synchronized`.


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Members {
    private Queue<String> emailsQueue = new ArrayBlockingQueue<>(10);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean open = true;
    public boolean isOpen() {
        return this.open;
    }
    public int pendingEmails() {
        try {
            lock.lock();

            return this.emailsQueue.size();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
    public void addMemberEmail (String email) {
        try {
            lock.lock();

            String nameOfThread = Thread.currentThread().getName();
            System.out.println(nameOfThread+" adicionou um novo email na lista!");
            this.emailsQueue.add(email);

            //this.emailsQueue.notifyAll(); --> substituido:
            condition.signalAll(); // avisa todas threads que estão wait no obj emailsQueue

        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    public String retrieveEmail() {
        String nameOfThread = Thread.currentThread().getName();
        System.out.println(nameOfThread+" checking if there are emails...");

        try{
            lock.lock();

            while (this.emailsQueue.size() == 0) {
                if (!this.open) return null;
                System.out.println(Thread.currentThread().getName()+" Não tem email disponível na lista, entrando em modo de espera (Wait)");
                try {
                    //this.emailsQueue.wait(); -> substituido
                    condition.await();
                    // Agora ela esta esperando até ser notificada, para assim proceguir.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            return this.emailsQueue.poll();

        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    public void close() {
        this.open = false;
        try {
            lock.lock();

            System.out.printf(Thread.currentThread().getName()+" Não estamos mais pegando emails!");
            condition.signalAll();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
