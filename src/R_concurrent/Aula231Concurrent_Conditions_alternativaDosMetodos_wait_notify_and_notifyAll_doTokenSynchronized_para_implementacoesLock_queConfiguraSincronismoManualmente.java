package R_concurrent;

// Alternativas para comunicação entre threads quando se utiliza Lock manual:

// substituindo os métodos `wait`, `notify` e `notifyAll` do `Synchronized` pelos
// equivalentes quando se trabalha com Lock manual utilizando ReetrantLock por exe:
// Métodos vem da implementação da interface `Condition` fornecida pelo lock.newCondition():
// `condition.await`, `condition.signall` e `condition.signalAll`.

// Obs: Mesmo exemplo utilizado em: ..\Q_threads\sincronismo\Aula228Threads_wait_notify_and_notifyAll_comunicacaoEntreThreadsPorMeioDoObjLock.java
// Porém substituindo `Synchronized` por objetos ReentrantLock que configura lock manualmente.

import R_concurrent.domain.Members;
import R_concurrent.service.EmailDeliveryService;

import javax.swing.*;

public class Aula231Concurrent_Conditions_alternativaDosMetodos_wait_notify_and_notifyAll_doTokenSynchronized_para_implementacoesLock_queConfiguraSincronismoManualmente {
    public static void main(String[] args) {
        // Mesmo exemplo de problema anterior, porém utilizando Lock e Conditions ao invés do `Synchronized`:
        // Problema: EmailDeliveryService
        // Obs: ./domain/* e ./service/* -> com as mesmas classes de ../Q_threads/domain/* e ../Q_threads/service/*
        // porém substituindo `Synchronized` por `Lock` e `ReentrantLock`:
        // e substituindo os métodos: `wait`, `notify` e `notifyAll` pelos equivalentes:
        // `await`, `signal` e `signalAll` do Conditions:

        Members members = new Members();
        // EmailDeliveryService emailDeliveryServiceRunnable = new EmailDeliveryService(members);

        // entregam os emails:
        Thread wellison = new Thread(new EmailDeliveryService(members), "Wellison");
        Thread danielle = new Thread(new EmailDeliveryService(members), "Danielle");
        wellison.start();
        danielle.start();

        // Add emails para wellison e danielle entregarem:
        while(true) {
            String emailInput = JOptionPane.showInputDialog("Entre com o email para enviar: ");

            if (emailInput == null || emailInput.isEmpty()) {
                members.close();
                break;
            }

            members.addMemberEmail(emailInput);
        }


    }
}
