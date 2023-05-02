package Q_threads.service;

import Q_threads.domain.Members;

public class EmailDeliveryService implements Runnable{
    private final Members members;

    public EmailDeliveryService(Members members) {
        this.members = members;
    }

    @Override
    public void run() {
        String nameOfCurrentThread = Thread.currentThread().getName();
        System.out.println(nameOfCurrentThread+" starting to deliver emails");
        while (members.isOpen() || members.pendingEmails() > 0)  {
            try {
                String email = members.retrieveEmail();
                if (email == null) continue;

                System.out.println(nameOfCurrentThread+" enviando email para "+email);
                Thread.sleep(2000);
                System.out.println(nameOfCurrentThread+" enviou email com sucesso para "+email);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Todos os emails foram enviados com sucesso!");
    }
}
