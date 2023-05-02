package Q_threads.sincronismo;

/* Definições sobre os métodos `wait()`, `notify()` e `notifyAll()` da class Object:

Os métodos wait(), notify() e notifyAll() são ferramentas para sincronização de
threads e permitem que as threads cooperem entre si para executar tarefas de
forma sincronizada e sem interferência.

    - wait(): É usado para colocar uma thread em espera até que outra thread
    notifique que alguma condição importante ocorreu.
    Isso é feito chamando o método `wait()` em um objeto, e a thread entrará em
    espera até que outra thread chame o método `notify()` ou `notifyAll()` nesse
    mesmo objeto.
    O método `wait()` também libera o lock (trava) associado ao objeto, permitindo
    que outras threads possam acessá-lo.

    - notify(): É usado para notificar uma thread em espera de que uma determinada
    condição ocorreu.
    Isso é feito chamando o método `notify()` em um objeto, o que faz com que a
    primeira thread em espera seja notificada e acorde.
    A thread notificada ainda precisará competir com outras threads para obter
    acesso ao objeto, mas pelo menos ela pode continuar a execução.

    - notifyAll(): É semelhante ao método `notify()`, mas notifica todas as threads
    em espera em um objeto, em vez de apenas uma.

Esses métodos são usados em conjunto com o conceito de monitor em Java, que é uma
construção que permite que threads cooperem entre si e sincronizem o acesso a um
recurso compartilhado.

---> Algumas aplicações e utilidades desses métodos incluem:

- Implementação de um sistema produtor-consumidor, onde uma thread produtora cria
objetos e as threads consumidoras processam esses objetos.

- Implementação de um sistema de mensagens ou eventos assíncronos, onde as threads
aguardam a chegada de uma mensagem ou evento e são notificadas quando eles chegam.

- Implementação de uma barreira de sincronização, onde as threads esperam todas
chegarem em um ponto específico antes de prosseguir.

- Implementação de um sistema de monitoramento de recursos compartilhados, onde as
threads esperam para acessar um recurso e são notificadas quando ele está disponível.

Em resumo, os métodos wait(), notify() e notifyAll() são ferramentas fundamentais
para a sincronização de threads, permitindo que elas cooperem e sincronizem o
acesso a recursos compartilhados.

*/

/* Mais sobre Conceito de monitor em Java:

O conceito de monitor é uma construção que permite que as threads cooperem entre
si e sincronizem o acesso a um recurso compartilhado.
O monitor é representado por um objeto que é acessado pelas threads, e que possui
um lock (trava) associado a ele. Quando uma thread entra no monitor, ela adquire
o lock e pode acessar o recurso compartilhado. Quando ela sai do monitor, ela
libera o lock, permitindo que outras threads possam acessar o recurso.

Os métodos wait(), notify() e notifyAll() são usados em conjunto com o monitor
para implementar a sincronização de threads.
Quando uma thread chama o método wait() em um objeto, ela libera o lock associado
a esse objeto e entra em estado de espera.
Isso permite que outras threads possam acessar o objeto e realizar operações nele.
Quando outra thread chama o método notify() ou notifyAll() no mesmo objeto, a
thread em espera é notificada e acorda, retomando a execução a partir do ponto
onde havia chamado o método wait().

Essa construção é importante para evitar condições de corrida (race conditions)
e garantir a consistência dos dados compartilhados entre threads.
Sem a sincronização adequada, as threads podem tentar acessar e modificar os
mesmos dados simultaneamente, levando a resultados imprevisíveis ou até mesmo
erros.

O uso correto do monitor e dos métodos wait(), notify() e notifyAll() é fundamental
para garantir a corretude de programas concorrentes. No entanto, é importante notar
que a sincronização excessiva pode levar a problemas de desempenho e bloqueio de
threads, e que outras ferramentas de concorrência, como as classes atômicas e os
locks, podem  adser maisequadas em alguns casos.

*/

import Q_threads.domain.Members;
import Q_threads.service.EmailDeliveryService;

import javax.swing.*;

public class Aula228Threads_wait_notify_and_notifyAll_comunicacaoEntreThreadsPorMeioDoObjLock {
    public static void main(String[] args) {
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
