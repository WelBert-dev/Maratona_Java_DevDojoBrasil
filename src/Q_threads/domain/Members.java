package Q_threads.domain;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Members {
    private Queue<String> emailsQueue = new ArrayBlockingQueue<>(10);
    private boolean open = true;
    public boolean isOpen() {
        return this.open;
    }
    public int pendingEmails() {
        synchronized (this.emailsQueue){
            return this.emailsQueue.size();
        }
    }
    public void addMemberEmail (String email) {
        synchronized (this.emailsQueue) {
            String nameOfThread = Thread.currentThread().getName();
            System.out.println(nameOfThread+" adicionou um novo email na lista!");
            this.emailsQueue.add(email);

            this.emailsQueue.notifyAll(); // avisa todas threads que estão wait no obj emailsQueue
        }
    }
    public String retrieveEmail() {
        String nameOfThread = Thread.currentThread().getName();
        System.out.println(nameOfThread+" checking if there are emails...");
        synchronized (this.emailsQueue){
            while (this.emailsQueue.size() == 0) {
                if (!this.open) return null;
                System.out.println(Thread.currentThread().getName()+" Não tem email disponível na lista, entrando em modo de espera (Wait)");
                try {
                    this.emailsQueue.wait();
                    // Obs: Só pode chamar o objLock.wait() em contextos lock ou seja em blocos `synchronized`.
                    // Agora ela esta esperando até ser notificada, para assim proceguir.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            return this.emailsQueue.poll();
        }
    }
    public void close() {
        this.open = false;
        synchronized (this.emailsQueue) {
            System.out.printf(Thread.currentThread().getName()+" Não estamos mais pegando emails!");
        }
    }
}
