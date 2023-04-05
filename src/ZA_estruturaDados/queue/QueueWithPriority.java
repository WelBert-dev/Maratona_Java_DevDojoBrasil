package ZA_estruturaDados.queue;

// Contextualizando, esta classe realiza uma lógica no método de adição
// e já posiciona os elementos em ordem correta, seguindo essa prioridade,
// a depender das regras de negócio
// neste caso elementos menores tem maior prioridade, logo a ordem
// da fila sera ASC (crescente) 1, 2, 4, 6, 7, 8...

public class QueueWithPriority<T> extends Queue<T>{
    public QueueWithPriority() {
        super();
    }

    public QueueWithPriority(int capacity) {
        super(capacity);
    }

    @Override
    public boolean enqueue(T element) {

        Comparable<T> chave = (Comparable<T>) element;

        int i;
        for (i = 0; i < this.size; i++) {
            if (chave.compareTo(this.elements[i]) < 0) {
                break;
            }
        }
        return super.add(element, i);
    }
}
