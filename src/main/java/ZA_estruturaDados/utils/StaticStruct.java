package ZA_estruturaDados.utils;

public abstract class StaticStruct<T> {
    protected T[] elements;
    protected int size;

    public StaticStruct() {
        this(10);
    }
    public StaticStruct(int capacity){
        this.elements = (T[]) new Object[capacity]; // mais elegante
        this.size = 0;
    }
    public int getSize() {
        return this.size;
    }

    protected void multiplyCapacity() {
        if (this.size == this.elements.length) {
            T[] elementsNew = (T[]) new Object[this.elements.length * 2];

            for (int i = 0; i<this.elements.length; i++) {
                elementsNew[i] = this.elements[i];
            }

            this.elements = elementsNew;
        }
    }
    protected boolean add(T element) {

        // Duplica a capacidade do array (caso size ultrapasse o length definido inicialmente)
        // Tornando a capacidade do array dinâmica
        this.multiplyCapacity();

        if (this.size < elements.length) {
            this.elements[this.size] = element;
            this.size++;
            return true;
        }

        return false;
    }
    protected boolean add(T element, int index) {
        if (!(index >= 0) && !(index < this.size)) {
            System.out.println(this.size);
            return false;
        }

        // Duplica a capacidade do array (caso size ultrapasse o length definido inicialmente)
        // Tornando a capacidade do array dinâmica
        this.multiplyCapacity();

        // move os elementos apartir do fim, +1 (esquerda) casa até chegar no index passado
        for (int i = this.size-1; i >= index; i--) {
            this.elements[i+1] = this.elements[i];
        }

        // após liberado a casa/index, insere o elemento
        this.elements[index] = element;
        this.size ++; // size real agora aumenta +1 (trabalhando com tamanho real, não com o definido na criação do array

        return true;
    }
    protected T getElement(int indexOf) {
        if (!(indexOf >= 0 && indexOf < this.size)) {
            return null;
        }

        return this.elements[indexOf];
    }
    protected T removeElementByIndex(int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IllegalArgumentException("Posição fora do range possível");
        }

        T element = this.elements[index];

        for (int i = index; i<this.size-1; i++) {
            // começa no index que será removido, e atribuí o próximo elemento nele (um a um)

            this.elements[i] = this.elements[i+1];
        }

        // Removeu 1 elemento, logo o tamanho real abaixou (pois sempre trabalhamos com o tamanho real ao invés do tamanho definido na criação do array)
        this.size = this.size - 1;

        return element;
    }
    public void clear() {
        // opção 1
        //this.elements = (T[]) new Object[this.elements.length];
        // opção 2
        //this.size = 0;
        // opção 3
        for (int i=0; i<this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;

    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < this.size-1; i++) {
            s.append(this.elements[i]);
            s.append(", ");
        }

        if (this.size > 0) { // evita exeção
            s.append(this.elements[this.size-1]);
        }

        s.append("]");

        return s.toString();
    }

}
