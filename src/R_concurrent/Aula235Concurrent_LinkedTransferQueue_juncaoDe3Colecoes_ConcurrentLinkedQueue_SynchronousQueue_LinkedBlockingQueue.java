package R_concurrent;

/* Definições sobre LinkedTransferQueue:

É uma implementação da interface BlockingQueue, que fornece uma estrutura de
dados baseada em fila que pode ser usada para gerenciar threads concorrentes.
Ela é projetada para ser usada em cenários em que as threads precisam se
comunicar e cooperar entre si.

A principal utilidade da LinkedTransferQueue é a transferência de elementos
entre threads. A classe é projetada para permitir que uma thread insira um
elemento na fila e aguarde até que outra thread retire esse elemento.
Isso torna a classe ideal para implementar padrões de comunicação entre
threads, como o modelo produtor/consumidor.

---> Algumas das principais aplicações e utilidades da classe incluem:

    - Processamento de eventos: Pode ser usada para enviar eventos entre threads
    em um sistema de eventos assíncronos.

    - Processamento em lote: Pode ser usada para processar lotes de dados em
    paralelo, onde uma thread adiciona elementos na fila e outras threads retiram
    esses elementos para processamento.

    - Escalonamento de tarefas: Pode ser usada para implementar um escalonador de
    tarefas, onde as tarefas são adicionadas à fila e retiradas por threads de
    execução.

A LinkedTransferQueue é especialmente útil em cenários em que o número de threads
é desconhecido ou pode variar durante a execução do programa, já que ela é projetada
para ser escalável e eficiente em termos de uso de recursos. Além disso, ela oferece
suporte a operações atômicas, o que torna sua utilização segura em ambientes
concorrentes.

Em resumo, a LinkedTransferQueue é uma classe útil para gerenciamento de threads
concorrentes no Java, permitindo que as threads se comuniquem e cooperem entre si
de forma eficiente e segura.

*/

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Aula235Concurrent_LinkedTransferQueue_juncaoDe3Colecoes_ConcurrentLinkedQueue_SynchronousQueue_LinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        // Junção das funcionalidades de: ConcurrentLinkedQueue; SynchronousQueue; LinkedBlockingQueue;
        // ATENÇÃO: Não é necessário passar uma initialCapacity porém ele cria uma:
        TransferQueue<String> linkedTransferQueue = new LinkedTransferQueue(); // 2147483647
        System.out.println(linkedTransferQueue.remainingCapacity()); // pega a capacidade restante
        linkedTransferQueue.add("Wellison"); // se não der lança exception

        // --------------------------------------------------------------------

        linkedTransferQueue.offer("Danielle"); // add e diferente do anterior ele retorna boolean ao invés de throws

        // Também é possível adicionar e esperar um tempo (thread fica wait) após add com offer:
        linkedTransferQueue.offer("Pirokinha", 10, TimeUnit.SECONDS);

        // --------------------------------------------------------------------

        // Tenta adicionar e espera até liberar um espaço (Igual ao ArrayBlockingQueue anterior):
        // Ou seja, a thread fica em wait até liberar espaço na coleção:
        // neste caso como a coleção não foi definido uma initialCapacity
        // então podemos adicionar (até 2147483647 elementos) então a thread main não fica await aqui
        linkedTransferQueue.put("Pamonha");

        // --------------------------------------------------------------------

        // Definições sobre o método transfer() que é meio complicado:
        // Se houver uma thread esperando para receber um elemento, o elemento é entregue
        // imediatamente a essa thread e o método retorna.

        // Se não houver nenhuma thread esperando para receber um elemento, o elemento é
        // adicionado à fila e a thread atual é bloqueada até que outra thread chame o
        // método take() ou poll() para receber um elemento.

        // Se a thread atual for interrompida enquanto está bloqueada, o elemento será
        // removido da fila e a exceção InterruptedException será lançada.

        // Se a fila estiver cheia, o método transfer() bloqueará a thread atual até que
        // um elemento seja removido da fila.

        // Em resumo, o método transfer() é uma forma eficiente e segura de enviar elementos
        // de uma thread para outra, garantindo que as threads não fiquem bloqueadas em espera
        // ocupando recursos desnecessariamente, e que os elementos sejam entregues de forma
        // ordenada e segura.

        // Colocamos em um if com hasWaitingConsumer() que verifica se existe alguma thread
        // aguardando (await) que seja adicionado um recurso para ai transferir o elemento
        // desta thread para a thread consumidora que está aguardando.
        if(linkedTransferQueue.hasWaitingConsumer()) {
            linkedTransferQueue.transfer("Complicadinhu");
        }
        // Caso não queira utilizar o if anterior, temos a opção de tryTransfer():
        // Aqui a thread corrente (main) não fica await até um consumidor receber,
        // diferente da anterior que aguarda (await) até um consumidor receber.

        // True se: Algum consumidor receber a transferência.
        // False se: Nenhum consumidor receber a transferência.
        // Ou seja, Se algum consumidor estiver await esperando uma transferência.
        boolean isPossibleTransfer = linkedTransferQueue.tryTransfer("Complicadinhu");
        // Também é possível utilizar a sobrecarga com time aonde a thread corrente (main)
        // tenta por até o tempo desejado (ficando await aqui até conseguir ou o tempo expires):
        linkedTransferQueue.tryTransfer("Comnplicadinhu tenta por x tempo", 5, TimeUnit.SECONDS);

        // --------------------------------------------------------------------

        // Retorna o primeiro elemento da fila e não remove, e diferente do peek() que
        // retorna null se não existir elementos, aqui ele lança uma Exception: NoSuchElementException
        linkedTransferQueue.element();

        // --------------------------------------------------------------------

        // Retorna o primeiro elemento e remove, e diferente do poll() que
        // retorna null se não existir elementos, aqui ele lança uma Exception: NoSuchElementException
        linkedTransferQueue.remove();

        // --------------------------------------------------------------------

        // Retorna o primeiro elemento e remove, e diferente do anterior remove() que
        // lança exception se não existir elementos, aqui ele retorna null:
        linkedTransferQueue.poll();

        // --------------------------------------------------------------------

        // Retorna o primeiro elemento e remove, e em ambientes de concorrência a
        // current thread (main) fica wait esperando alguma outra adicionar elementos
        // caso não exista isEmpty() nenhum elemento na filia.
        linkedTransferQueue.take();
    }
}
