package R_concurrent;


/* Definições sobre o pacote `java.util.concurrent`:

Introduzido para adicionar uma camada de abstração para lidarmos melhor com a
concorrência de uma forma mais facil e consisa.

O pacote java.util.concurrent é uma das bibliotecas mais importantes do Java para
programação concorrente, que oferece diversas classes e interfaces para lidar com
a execução simultânea de múltiplas tarefas em um programa.
Essas classes e interfaces fornecem um conjunto de ferramentas para ajudar a
escrever programas que lidam com threads e sincronização.

---> Algumas das principais classes e interfaces disponíveis incluem:

    - Executor e ExecutorService: interfaces que definem um framework para a
    execução de tarefas assíncronas em um pool de threads.

    - Callable e Future: interfaces que permitem a execução de tarefas assíncronas
    com um valor de retorno, e que permitem o controle do tempo de espera para o
    resultado.

    - RunnableFuture: interface que combina as interfaces Runnable e Future.

    - ThreadPoolExecutor: classe que implementa um executor de tarefas com um
    pool de threads, permitindo a execução simultânea de múltiplas tarefas.

    - ScheduledThreadPoolExecutor: classe que implementa um executor de tarefas
    que permite a execução periódica de tarefas.

    - ConcurrentHashMap: classe que implementa um mapa concorrente que permite
    o acesso simultâneo de várias threads.

    - ConcurrentLinkedQueue e ConcurrentLinkedDeque: classes que implementam
    filas concorrentes que permitem o acesso simultâneo de várias threads.

O pacote java.util.concurrent é amplamente utilizado em aplicações que precisam
lidar com tarefas concorrentes e/ou paralelas, como aplicações de servidor,
processamento de dados em tempo real, sistemas de jogos, entre outros.

---> Algumas das principais utilidades do pacote java.util.concurrent incluem:

    - Permite a criação de aplicações mais eficientes e escaláveis que podem lidar
    com tarefas simultâneas de maneira mais eficaz.

    - Permite a implementação de algoritmos mais avançados e complexos, como
    algoritmos de processamento paralelo e algoritmos de busca em paralelo.

    - Fornece um conjunto de classes e interfaces que são seguras para uso
    concorrente, garantindo que as operações sejam executadas corretamente em
    ambientes multi-threaded.

    - Permite uma implementação mais fácil e segura de algoritmos concorrentes
    e paralelos, eliminando a necessidade de lidar diretamente com sincronização
    e gerenciamento de threads.

*/

/* Definições sobre as classes Atômicas e seus usos para evitar concorrência:

Classes atômicas são uma das formas de lidar com concorrência, e servem para
garantir a consistência de dados compartilhados entre threads. Em geral, quando
várias threads tentam acessar e modificar os mesmos dados ao mesmo tempo, pode
ocorrer uma condição de corrida (race condition), que pode levar a erros ou
inconsistências no programa.

As classes atômicas resolvem esse problema garantindo que certas operações sejam
executadas de forma atômica, ou seja, sem interferência de outras threads.
Isso é feito por meio de instruções de hardware que garantem a exclusão mútua em
um nível muito baixo, evitando que outras threads possam interferir em uma
operação em andamento.

---> Algumas das principais classes atômicas em Java incluem:

    - AtomicInteger: usado para operações atômicas em valores inteiros.
    - AtomicLong: usado para operações atômicas em valores longos.
    - AtomicBoolean: usado para operações atômicas em valores booleanos.
    - AtomicReference: usado para operações atômicas em referências a objetos.

Essas classes possuem métodos que permitem a realização de operações atômicas,
como incremento, decremento, comparação e troca, entre outras.
Além disso, as classes atômicas também fornecem garantias de visibilidade, ou
seja, garantem que uma operação realizada por uma thread será imediatamente
visível para outras threads que acessam o mesmo objeto.

As classes atômicas são úteis em diversas aplicações que envolvem concorrência,
como sistemas de processamento em tempo real, sistemas de controle de acesso a
dados, sistemas de cache, entre outros. Elas também podem ser usadas em conjunto
com outras ferramentas de concorrência, como locks, semáforos e barreiras de
sincronização, para criar soluções mais complexas para problemas de concorrência.

*/

// Essas classes oferecem métodos que executam operações em lote (Batch) porisso
// a nomeclatura "Atomic", como por exemplo incrementar e retornar o valor,
// aqui temos duas operações em lote que a concorrência poderia alterar o obj
// antes mesmo de dar tempo de retornar o valor alterado anteriormente,
// com isso causaria inconcistências, porisso executamos as duas operações
// com AtomicInteger por exemplo de uma só vez `ìncrementAndGet()` o equivalente
// ao `++i` e o `getAndIncrement()` equivalente ao `i++`.

import java.util.concurrent.atomic.AtomicInteger;

public class Aula229Concurrent_AtomicClasses_AtomicInteger_AtomicBoolean_AtomicReference_OthersAtomics_operacoesSincronizadasEmBatchEvitaConcorrencia {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(-1);

    }
}
