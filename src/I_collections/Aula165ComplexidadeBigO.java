package I_collections;

// Quando se trabalha com coleções de dados, essas coleções se enquandram em
// uma das duas opções: Ordened e Sorted (ordenado e sortido),
// Todas as classes de coleções possuem especialidades para trabalhar com
// uma dessas categorias, assim escolhendo a classe correta temos
// ganhos significativos de performance.
// Por tanto, existe essa forma de se calcular a complexidade de um algoritmo
// para assim termos parametros de medição, chamado Big-O.
// O Big-O mede cada tipo de operação (get, set, e etc) e para cada uma
// existe uma performance, para saber qual é a melhor tabela abaixo.

/* Descrições sobre as complexidades:
 As notações de complexidade Big-O são usadas para descrever o tempo de execução ou espaço de memória
 necessários para um algoritmo à medida que o tamanho da entrada aumenta. Essas notações são escritas
 usando a notação O() e indicam a ordem de crescimento do algoritmo à medida que o tamanho da entrada
 aumenta. Aqui estão as principais notações de complexidade Big-O e seus significados:

 - O(1): Tempo de execução constante. Isso significa que o algoritmo leva a mesma quantidade de tempo,
 independentemente do tamanho da entrada.
   * Exemplo: acesso a um elemento em uma matriz.

  - O(log n): Tempo de execução logarítmico. Isso significa que o tempo de execução cresce proporcionalmente
  ao logaritmo do tamanho da entrada.
    * Exemplo: busca binária em uma lista ordenada.

  - O(n): Tempo de execução linear. Isso significa que o tempo de execução cresce proporcionalmente ao tamanho
  da entrada.
    * Exemplo: percorrer todos os elementos de uma lista.

  - O(n log n): Tempo de execução quase-linear. Isso significa que o tempo de execução cresce proporcionalmente
  ao tamanho da entrada multiplicado pelo logaritmo do tamanho da entrada.
    * Exemplo: algoritmos de classificação eficientes, como o mergesort ou o heapsort.

  - O(n^2): Tempo de execução quadrático. Isso significa que o tempo de execução cresce proporcionalmente
   ao quadrado do tamanho da entrada.
    * Exemplo: algoritmos de ordenação com desempenho inferior, como bubble sort ou selection sort.

  - O(2^n): Tempo de execução exponencial. Isso significa que o tempo de execução cresce exponencialmente
   em relação ao tamanho da entrada.
    * Exemplo: algoritmos de força bruta, como a resolução do problema do caixeiro-viajante.

  - O(n!): Tempo de execução fatorial. Isso significa que o tempo de execução cresce fatorialmente em relação
  ao tamanho da entrada.
    * Exemplo: resolução do problema de permutação.

 Geralmente, um algoritmo com complexidade Big-O menor é preferível, pois requer menos recursos computacionais
 à medida que o tamanho da entrada aumenta. No entanto, em muitos casos, é necessário comprometer entre a
 eficiência do algoritmo e a simplicidade de implementação ou outros requisitos do sistema.
*/

/* Tabela de calculos Big-O para as coleções do java: (Com gráficos: https://www.bigocheatsheet.com/)
- List implementations:
                       get   add   contains  next remove(0)  iterator.remove
 ArrayList             O(1)  O(1)  O(n)      O(1) O(n)       O(n)
 LinkedList            O(n)  O(1)  O(n)      O(1) O(1)       O(1)
 CopyOnWrite-ArrayList O(1)  O(n)  O(n)      O(1) O(n)       O(n)
---------------------------------------------------------------------------
 - Set implementations:
                       add       contains  next      notes
 HashSet               O(1)      O(1)      O(h/n)    h is the table capacity
 LinkedHashSet         O(1)      O(1)      O(1)
 CopyOnWriteArraySet   O(n)      O(n)      O(1)
 EnumSet               O(1)      O(1)      O(1)
 TreeSet               O(log n)  O(log n)  O(log n)
 ConcurrentSkipListSet O(log n)  O(log n)  O(1)
---------------------------------------------------------------------------
 - Map implementations:
                       get       containsKey  next      Notes
 HashMap               O(1)      O(1)         O(h/n)    h is the table capacity
 LinkedHashMap         O(1)      O(1)         O(1)
 IdentityHashMap       O(1)      O(1)         O(h/n)    h is the table capacity
 EnumMap               O(1)      O(1)         O(1)
 TreeMap               O(log n)  O(log n)     O(log n)
 ConcurrentHashMap     O(1)      O(1)         O(h/n)    h is the table capacity
 ConcurrentSkipListMap O(log n)  O(log n)     O(1)
---------------------------------------------------------------------------
 - Queue implementations:
                       offer     peek  poll      size
 PriorityQueue         O(log n)  O(1)  O(log n)  O(1)
 ConcurrentLinkedQueue O(1)      O(1)  O(1)      O(n)
 ArrayBlockingQueue    O(1)      O(1)  O(1)      O(1)
 LinkedBlockingQueue   O(1)      O(1)  O(1)      O(1)
 PriorityBlockingQueue O(log n)  O(1)  O(log n)  O(1)
 DelayQueue            O(log n)  O(1)  O(log n)  O(1)
 LinkedList            O(1)      O(1)  O(1)      O(1)
 ArrayDeque            O(1)      O(1)  O(1)      O(1)
 LinkedBlockingDeque   O(1)      O(1)  O(1)      O(1)
 ---------------------------------------------------------------------------
*/

/* Fluxograma Collections Framework Hierarchy in Java:
                     +----+----+
                    | Collection|                                     +---->   Map  <---+ Interface separada da hierarquia
                     +----+----+                                      |                 |
                          |                                 +---------+---------+-------+-------+
             +------------+-----------+                     |                   |               |
             |                        |                   HashMap          LinkedHashMap     TreeMap
           Set                      List                    |                   |               |
             |                        |                   Hashtable        IdentityHashMap   WeakHashMap
    +--------+-------+       +--------+-------+              |                                  |
    |                |       |                |           Properties                         SortedMap
 HashSet     SortedSet     ArrayList       LinkedList
    |                |            |          /      \
 LinkedHashSet  NavigableSet   Vector  DoublyLinkedList
                                             |
                                          Stack
                                             |
                                       PriorityQueue
 */


import java.util.TreeMap;

public class Aula165ComplexidadeBigO {
    public static void main(String[] args) {
        TreeMap pamonha;

        // Calculo logaritmico escrito em Java:
        double x = 10; // número a ser calculado o logaritmo
        double b = 2; // base do logaritmo
        double logb = Math.log(x) / Math.log(b); // cálculo do logaritmo na base b de x
        System.out.println("log" + b + "(" + x + ") = " + logb); // impressão do resultado

    }
}
