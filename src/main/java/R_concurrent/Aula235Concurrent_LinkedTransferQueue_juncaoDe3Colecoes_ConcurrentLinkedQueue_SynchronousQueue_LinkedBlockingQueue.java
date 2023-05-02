package R_concurrent;

/* Definições sobre a Interface TransferQueue:

É uma extensão da interface BlockingQueue que adiciona um método adicional chamado
"transfer()", que permite que uma thread coloque um elemento na fila e bloqueie
até que outro thread retire esse elemento da fila.

O método "transfer" é diferente do método "put" da interface BlockingQueue, pois
ele bloqueia a thread produtora até que o elemento seja retirado da fila por outra
thread consumidora, enquanto o método "put" bloqueia a thread produtora apenas
quando a fila está cheia.

A TransferQueue é uma opção mais avançada e flexível para trabalhar com filas em
ambientes multithreaded, já que permite que as threads produtoras e consumidoras
trabalhem de forma mais cooperativa e eficiente.


---> Algumas das aplicações mais comuns do TransferQueue incluem:

    - Comunicação inter-thread: É útil para sincronizar a comunicação entre threads
    produtoras e consumidoras em um ambiente multithreaded. Ela pode ser usada para
    passar mensagens e objetos entre as threads de forma cooperativa, sem a
    necessidade de sincronização adicional.

    - Processamento assíncrono: Pode ser usada para implementar processamento
    assíncrono, permitindo que as threads produtoras adicionem tarefas à fila
    e as threads consumidoras as executem em segundo plano.

    - Implementação de pool de threads: Pode ser usada para implementar um pool
    de threads eficiente, onde cada thread trabalha em uma tarefa específica e
    é bloqueada quando não há mais tarefas disponíveis na fila.

    - Implementação de fluxo de controle: Pode ser usada para implementar fluxo
    de controle em uma aplicação, permitindo que as threads produtoras controlem
    a execução das threads consumidoras por meio da inserção ou remoção de
    elementos na fila.

Em resumo, a interface TransferQueue é uma extensão poderosa da BlockingQueue
que permite que as threads produtoras e consumidoras trabalhem de forma mais
cooperativa e eficiente em ambientes multithreaded. Ela pode ser usada para
sincronizar a comunicação inter-thread, implementar processamento assíncrono,
implementar pool de threads e controlar o fluxo de execução em uma aplicação.


---> Diferenças para cada implementação dela:

* LinkedTransferQueue:
    - É uma implementação não limitada da TransferQueue, o que significa que não
    há limite para o número de elementos que podem ser adicionados na fila.

    - É adequada para cenários onde não é necessário limitar o tamanho da fila e
    onde a inserção e remoção de elementos pode ocorrer em qualquer extremidade
    da fila.

    - É menos eficiente do que outras implementações da TransferQueue em cenários
    com alta concorrência e alto volume de elementos na fila.

* SynchronousQueue:
    - É uma implementação limitada da TransferQueue que permite a comunicação
    estrita e sincronizada entre duas threads, uma produtora e uma consumidora,
    onde uma thread espera pela outra para transferir o elemento na fila.

    - É adequada para cenários onde a comunicação precisa ser estritamente
    sincronizada e controlada entre duas threads.

    - É muito eficiente em termos de desempenho, pois não mantém uma fila de
    elementos, transferindo imediatamente os elementos entre as threads.

* LinkedBlockingDeque:
    - É uma implementação limitada da TransferQueue que permite a inserção e
    remoção de elementos em ambas as extremidades da fila, além de permitir que
    as threads esperem por outras threads para transferir elementos na fila.

    - É adequada para cenários onde é necessário permitir que as threads insiram
    e removam elementos em ambas as extremidades da fila e também sincronizem a
    comunicação entre as threads.

    - É menos eficiente do que outras implementações da TransferQueue em cenários
    com alta concorrência e alto volume de elementos na fila.


---> Pontos importantes e atenções em comum para as classes que implementam:

1o - Operações síncronas e assíncronas: essas classes suportam operações síncronas
e assíncronas para transferência de elementos entre threads. A transferência
síncrona bloqueia a thread produtora até que um consumidor esteja pronto para
receber o elemento, enquanto a transferência assíncrona tenta transferir o
elemento imediatamente, sem bloquear a thread produtora.

2o - Performance: a escolha da classe TransferQueue correta para uma determinada
aplicação pode afetar significativamente o desempenho geral. Por exemplo, a classe
SynchronousQueue é adequada para transferências de elementos em alta frequência e
baixa latência, enquanto a classe LinkedBlockingDeque pode ser mais adequada para
transferências menos frequentes e com menos pressão de latência.

3o - Tamanho da fila: algumas classes, como a ArrayTransferQueue e a LinkedBlockingQueue,
exigem um tamanho de fila fixo na criação, enquanto outras, como a LinkedTransferQueue,
não têm um limite máximo de tamanho.

4o - Compatibilidade: as classes que implementam a interface TransferQueue são
compatíveis com as APIs padrão do Java, como a interface Executor e a classe
ThreadPoolExecutor, permitindo que os desenvolvedores integrem facilmente a
transferência de elementos em suas aplicações multithread.

5o - Cuidado com o bloqueio: como essas classes são projetadas para transferir
elementos entre threads, os desenvolvedores devem ter cuidado ao usar bloqueios
explícitos, pois podem causar bloqueios ou deadlock se não forem cuidadosamente
gerenciados.

6o - Uso adequado de recursos: alguns métodos, como o take(), podem bloquear a
thread indefinidamente se não houver elementos disponíveis para transferência.
Portanto, é importante garantir que os recursos estejam disponíveis para
transferência antes de chamar esses métodos.

*/

// ----------------------------------------------------------------------------

/* Definições sobre a classe LinkedTransferQueue:

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


---> Algumas das implementações mais comuns são:

    * LinkedTransferQueue: Implementação não limitada, baseada em uma lista
    encadeada. Essa classe é útil em cenários onde não há necessidade de limitar
    o tamanho da fila.

    * SynchronousQueue: Implementação que permite que apenas uma thread produtora
    aguarde por uma thread consumidora para retirar um elemento da fila. Essa
    classe é útil em cenários onde é necessário sincronizar a comunicação entre
    threads de forma estrita.

    * LinkedBlockingDeque: Implementação baseada em uma lista encadeada dupla.
    Essa classe é útil em cenários onde é necessário permitir que as threads
    produtoras e consumidoras adicionem e removam elementos da fila em ambas
    as extremidades.

Os propósitos dessas classes são oferecer opções flexíveis e eficientes para
implementar filas em ambientes multithreaded. A interface TransferQueue adiciona
um novo nível de controle e sincronização à comunicação inter-thread, permitindo
que as threads produtoras e consumidoras trabalhem de forma mais cooperativa e
eficiente. As classes que implementam ela oferecem diferentes implementações de
filas que podem ser adaptadas para atender a diferentes requisitos de desempenho
e comportamento em diferentes cenários de uso.


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
