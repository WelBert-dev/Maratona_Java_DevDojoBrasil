package estruturaDados.queue;

// No java não existe uma Classe "Queue" e sim uma interface,
// na qual podemos utilizar como tipo de referência para outras classes
// que implementam ela (LinkedList é um dos exemplos),
// assim graças ao polimorfismo iremos "enchergar" apenas os métodos
// definidos na assinatura da interface Queue.

import estruturaDados.utils.StaticStruct;

public class Queue<T> extends StaticStruct<T> {
    public Queue(){
        super();
    }
    public Queue(int capacity){
        super(capacity);
    }

    public boolean enqueue(T element) {
        return super.add(element);
    }

    public boolean enqueue(T element, int index) {
        return super.add(element, index);
    }

    public T dequeue() {
        if (this.isEmpty()) return null;

        T element = this.elements[0];
        for (int i = 0; i<this.size-1; i++) {
            this.elements[i] = this.elements[i+1];
        }

        super.size -= 1;

        return element;
    }

    public T peek() {
        if (this.isEmpty()) return null;

        return this.elements[0];
    }

    public T peek(int index) {
        if (this.isEmpty()) return null;

        return super.getElement(index);
    }
}
