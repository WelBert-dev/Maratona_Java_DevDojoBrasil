package estruturaDados.linkedList;

// Estrutura de dados dinâmica e não utiliza arrays estáticos.
// Cada elemento é um Nó ou Célula
// Ponteiros (No caso do java variáveis do tipo referência)
// Referência ao primeiro elemento da lista
// Cada Nó faz referência ao próximo elemento da lista
// Ou seja, é preciso percorrer a lista por completo para pegar os elementos
// NÃO sendo possível acessar diretamente o elemento central por exemplo.

public class LinkedList<T> {

    private Node<T> init; // aponta para o primeiro elemento da lista
    private Node<T> last; // aponta para o ultimo elemento da lista
    private int size = 0;

    public void add(T element) { // 1 2
        Node<T> celula = new Node<>(element);
        if (this.size == 0) {
            this.init = celula;
        } else {
            this.last.setNextNode(celula);
        }

        this.last = celula;
        this.size++;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        if (this.size == 0) return "[]";

        StringBuilder builder = new StringBuilder();
        Node<T> currentNode = this.init;

        builder.append("[").append(currentNode.getElement()).append(",");

//        int count = 1;
//        while (currentNode.getNextNode() != null && count < this.size-1) {
//            currentNode = currentNode.getNextNode();
//            builder.append(currentNode.getElement()).append(",");
//            count++;
//        }
//
//        builder.append(currentNode.getNextNode().getElement()).append("]");



        return builder.toString();
    }
}
