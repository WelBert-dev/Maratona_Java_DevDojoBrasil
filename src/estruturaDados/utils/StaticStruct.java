package estruturaDados.utils;

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

    private void multiplyCapacity() {
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
        if (!(index >= 0 && index < this.size)) {
            throw new IllegalArgumentException("Posição fora do range possível");
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
