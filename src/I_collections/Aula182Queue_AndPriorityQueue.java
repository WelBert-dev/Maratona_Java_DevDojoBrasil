package collections;

// Filas em Java (Mais estudo em src/estruturaDados)
// FIFO (First in First Out): Primeiro a entrar é o primeiro a sair.
// Também só aceita elementos ordenáveis que implementam a interface Comparable<T>
// Ou utilizando um Comparator<T> na instânciação dela.
// Exemplo de utilização: Filas de hospitais aonde existem prioridades no atendimento.
// Mais exemplos práticos em: src/estruturaDados/queue/Exe02_filaDeAtendimentoComPrioridades.java

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Aula182Queue_AndPriorityQueue {
    public static void main(String[] args) {
        Comparator<Consumidor> consumidorComparator = Comparator.comparing(
                consumidor -> consumidor.getNome() // aqui poderia ser get().get().get()..... ALL
        );

        // Organiza a fila de acordo com o compareTo():
        Queue<Consumidor> consumidorQueue = new PriorityQueue<>(consumidorComparator);
        consumidorQueue.add(new Consumidor("Maria"));
        consumidorQueue.add(new Consumidor("João"));
        consumidorQueue.add(new Consumidor("Ana"));
        consumidorQueue.add(new Consumidor("Pedro"));

        System.out.println(consumidorQueue);

        // peek espia o primeiro elemento da fila
        System.out.println(consumidorQueue.peek()); // Consumidor{id=90509, nome='Ana'}

        // Removendo um objeto apartir de outro: NÃO recomendável utilizar remove()
        System.out.println(consumidorQueue.remove(new Consumidor(consumidorQueue.peek().getNome(), consumidorQueue.peek().getId())));

        // Iterando na Fila, REMOVENDO e retornando elementos:
        while(!consumidorQueue.isEmpty()) {
            System.out.println(consumidorQueue.poll());
            // Consumidor{id=53549, nome='Ana'}
            // Consumidor{id=38746, nome='João'}
            // Consumidor{id=71262, nome='Maria'}
            // Consumidor{id=49484, nome='Pedro'}
        }
    }
}
