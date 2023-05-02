package R_concurrent;

/* Definições sobre a Interface BlockingQueue:

É uma interface da API de concorrência do Java que representa uma fila que suporta
operações de bloqueio. Ela permite que as threads acessem a fila de maneira
concorrente e assíncrona, garantindo que as operações de inserção e remoção de
elementos sejam realizadas de forma segura e ordenada.

A principal utilidade do BlockingQueue é permitir a comunicação e coordenação entre
threads em um ambiente concorrente.


---> Algumas das aplicações mais comuns do BlockingQueue incluem:

    - Producer-Consumer: é uma situação em que uma thread produz dados e outra
    thread consome esses dados. Pode ser usada para enviar os dados produzidos
    pela thread produtora para a thread consumidora.

    - Pool de Threads: um pool de threads é um conjunto de threads pré-instanciadas
    que ficam esperando por tarefas para serem executadas. A BlockingQueue pode ser
    usada para armazenar as tarefas a serem executadas e distribuí-las entre as
    threads disponíveis.

    - Coordenação de tarefas: em um ambiente onde várias threads precisam trabalhar
    juntas para realizar uma tarefa, a BlockingQueue pode ser usada para coordenar
    as etapas do processo. Cada thread pode executar uma etapa da tarefa e passar
    o resultado para a próxima etapa através da BlockingQueue.

    - Mensagens: pode ser usada como um canal de comunicação entre componentes do
    sistema. Uma thread pode enviar mensagens para outra thread através da fila,
    permitindo uma comunicação assíncrona e segura.


---> Algumas das implementações mais comuns são:

    * ArrayBlockingQueue: baseada em array que cria uma fila com tamanho fixo.
    Ela permite que múltiplas threads insiram e removam elementos da fila de
    forma concorrente.

    * LinkedBlockingQueue: baseada em lista ligada que cria uma fila com tamanho
    opcional. Ela é otimizada para operações de inserção e remoção em extremidades
    opostas da fila, o que a torna útil em aplicações de produtor-consumidor.

    * PriorityBlockingQueue: baseada em heap que cria uma fila com prioridades.
    Ela permite que os elementos sejam ordenados de acordo com sua prioridade,
    o que a torna útil em aplicações que requerem um processamento por ordem
    de prioridade.

    * SynchronousQueue: cria uma fila sem armazenamento interno. Ela é usada para
    sincronizar a comunicação entre duas threads, permitindo que uma thread envie
    um elemento para outra thread e bloqueie até que o elemento seja recebido.

    * DelayQueue: cria uma fila com elementos que têm um tempo de expiração.
    Ela permite que os elementos sejam ordenados de acordo com o tempo de
    expiração, o que a torna útil em aplicações que exigem a execução de
    tarefas em momentos específicos.


---> Diferenças para cada implementação dela:

* ArrayBlockingQueue: é uma fila baseada em array com tamanho fixo.
Ela é útil para situações em que o número máximo de elementos é conhecido
antecipadamente.

    - Possui um tamanho fixo, definido na criação da fila.

    - Oferece uma implementação de alto desempenho para filas com tamanho fixo.

    - Possui uma ordem de inserção e remoção dos elementos garantida.

* LinkedBlockingQueue: é uma fila baseada em lista ligada com tamanho opcional.
Ela é útil em situações em que o número de elementos é desconhecido ou pode variar.

    - Não possui um tamanho fixo, mas pode ter um tamanho opcional definido na
    criação da fila.

    - Possui uma ordem de inserção e remoção dos elementos garantida.

    - É otimizada para operações de inserção e remoção em extremidades opostas
     da fila.

    - É uma boa escolha para aplicações de produtor-consumidor, em que uma ou
    mais threads produzem elementos e outras threads consomem esses elementos.

* PriorityBlockingQueue: é uma fila baseada em heap com elementos ordenados por
prioridade. Ela é útil em situações em que é necessário processar os elementos
em ordem de prioridade.

    - Os elementos são ordenados por prioridade, que pode ser definida por meio
    de um comparador ou por uma implementação da interface Comparable.

    - Possui uma ordem de remoção dos elementos garantida pela ordem de prioridade.

    - Oferece uma implementação de alto desempenho para filas com elementos ordenados
    por prioridade.

* SynchronousQueue: é uma fila que não armazena elementos internamente. Ela é útil
em situações em que é necessário sincronizar a comunicação entre duas threads.

    - Não possui armazenamento interno, o que significa que cada operação de inserção
    deve ser correspondida por uma operação de remoção.

    - É uma boa escolha para aplicações de produtor-consumidor em que as threads
    produtoras e consumidoras devem estar em sincronia.

* DelayQueue: é uma fila que armazena elementos com um tempo de expiração. Ela é
útil em situações em que é necessário processar os elementos em um momento
específico no futuro.

    - Os elementos são armazenados com um tempo de expiração definido na criação
    da fila.

    - Os elementos são ordenados pelo tempo de expiração, o que permite que eles
    sejam processados em um momento específico no futuro.

    - É uma boa escolha para aplicações que requerem a execução de tarefas em
    momentos específicos.


---> Pontos importantes e atenções em comum para as classes que implementam:

1o - Thread-safe: Todas as classes que implementam a interface BlockingQueue são
thread-safe, o que significa que podem ser usadas por várias threads simultaneamente
sem a necessidade de sincronização adicional.

2o - Operações de bloqueio: As operações de bloqueio, como put() e take(), podem
fazer com que uma thread seja bloqueada até que um elemento esteja disponível na
fila ou haja espaço suficiente na fila para inserir um novo elemento.

3o - Capacidade limitada: Algumas implementações de BlockingQueue, como
ArrayBlockingQueue, possuem uma capacidade limitada definida na criação da fila.
Isso significa que uma thread que tenta inserir um elemento na fila quando ela
está cheia pode ser bloqueada até que um elemento seja removido da fila.

4o - Métodos adicionais: As classes que implementam a interface BlockingQueue
podem ter métodos adicionais que são específicos da implementação. Por exemplo,
a LinkedBlockingQueue tem métodos como drainTo() e offer() que não estão
disponíveis em outras implementações.

5o - Prioridades: Algumas implementações de BlockingQueue, como PriorityBlockingQueue,
armazenam elementos ordenados por prioridade, definida por uma implementação da
interface Comparable ou por um comparador.

6o - Tamanho variável: Algumas implementações de BlockingQueue, como LinkedBlockingQueue,
possuem um tamanho variável que pode crescer ou diminuir conforme a demanda.

7o - Sincronização: O uso de operações de bloqueio em classes que implementam a
interface BlockingQueue pode exigir sincronização adicional em outras partes do
código para evitar deadlocks ou condições de corrida.

8o - Métodos Bloqueantes: Os métodos de remoção (take() e poll()) e de inserção
(put() e offer()) são métodos bloqueantes, o que significa que eles podem esperar
por um tempo indeterminado até que a operação possa ser concluída.
Os métodos offer() e poll() com timeout permitem especificar um tempo limite para
a espera.

9o - Operações Atômicas: Todas as operações da BlockingQueue são atômicas, o que
significa que são executadas em uma única operação, sem interferência de outras
threads.

10o - Cancelamento: É importante definir uma política para lidar com o cancelamento
das operações de inserção e remoção. Algumas implementações da interface BlockingQueue
suportam a operação cancel() para cancelar as operações pendentes.

11o - Uso de Condições: A interface BlockingQueue usa objetos Condition para
implementar a espera condicional em operações de inserção e remoção. É importante
entender como as condições funcionam e como usá-las corretamente para evitar
problemas de concorrência.

*/

// ----------------------------------------------------------------------------

/* Definições sobre a classe ArrayBlockingQueue:

É uma implementação da interface BlockingQueue, que é utilizada para manipular
coleções de elementos em ambientes multithreaded. A ArrayBlockingQueue é uma
fila de tamanho fixo, onde os elementos são armazenados em um array e os acessos
são bloqueados quando a fila está cheia ou vazia, permitindo que as threads possam
aguardar até que haja espaço para inserir novos elementos ou até que haja elementos
disponíveis para remover.

Essa classe é particularmente útil em cenários onde é necessário controlar o número
máximo de elementos em uma fila, como por exemplo em sistemas de produção e consumo
de dados, onde um conjunto de threads é responsável por produzir dados que são
inseridos na fila, enquanto outro conjunto de threads é responsável por consumir
esses dados da fila e processá-los. A ArrayBlockingQueue garante que a fila não
ultrapasse o tamanho máximo permitido, evitando assim problemas de memória e de
performance na aplicação.


---> Algumas das principais aplicações da classe ArrayBlockingQueue incluem:

    - Sistema de gerenciamento de threads: Pode ser utilizada para implementar
    um sistema de gerenciamento de threads em uma aplicação, aonde as threads
    são colocadas em uma fila e processadas em ordem de chegada.

    - Comunicação entre threads: Pode ser utilizada para permitir a comunicação
    entre threads em uma aplicação, aonde uma thread insere dados na fila e outra
    thread consome esses dados.

    - Implementação de algoritmos de processamento de dados: Pode ser utilizada
    para implementar algoritmos de processamento de dados em tempo real, aonde os
    dados são inseridos na fila e processados em tempo hábil.

    - Controle de acesso a recursos: Pode ser utilizada para controlar o acesso
    a recursos em uma aplicação, aonde as threads aguardam em uma fila até que o
    recurso esteja disponível.

*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Aula234Concurrent_ArrayBlockingQueue_threadFicaBloqueadaAguardandoLiberarEspacoParaAddDeAcordoComInitalCapacityDefinidaAoCriar {
    public static void main(String[] args) throws InterruptedException {
        // Definimos uma capacidade inicial obrigatória pois é por ela
        // que é identificado quando a thread deve esperar até liberar
        // espaço para add valores, OBS: A THREAD executando fica wait
        // até a liberação da lista!!!

        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);

        bq.put("Wellison");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
        System.out.println("Trying to add another value");
        //bq.put("Danielle"); // Aqui a thread main fica wait infinitamente até que bq.remove e etc..

        // Nunca será executado essa linha:
        //System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());

        // Solução: Executar outra thread que faça a remoção do valor para a main não ficar mais wait:

        Runnable runnableRemover = () -> {
            System.out.printf("%s removed the value, unAwait the main thread%n", Thread.currentThread().getName());
            bq.remove();
            try {
                Thread.sleep(2000);
                System.out.printf("%s removed the value, unAwait the main thread again%n", Thread.currentThread().getName());
                bq.take(); // retorna e remove a cabeça da fila (primeiro elemento)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(runnableRemover).start();
        bq.put("Danielle");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
        System.out.println("Trying to add another value");
        bq.put("Animal");
        System.out.printf("%s added the value %s%n", Thread.currentThread().getName(), bq.peek());
    }

}
