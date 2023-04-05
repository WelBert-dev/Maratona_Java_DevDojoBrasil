package ZA_estruturaDados.stack;

import ZA_estruturaDados.utils.StaticStruct;

public class Stack<T> extends StaticStruct<T> {

    public Stack(){
        super();
    }
    public Stack(int capacity){
        super(capacity);
    }

    public boolean push(T element) { return super.add(element); }
    public boolean push(T element, int index) {
        return super.add(element, index);
    }

    public T peek() {
        if (this.isEmpty()) return null;

        return this.elements[size-1];
    }
    public T peek(int index) {
        if (this.isEmpty()) return null;

        return super.getElement(index);
    }

    public T pop() {
        if (this.isEmpty()) return null;

//        T lastElement = this.elements[size - 1];
//        this.elements[size-1] = null;
//        this.size--;


        return this.elements[--size];
    }
    public T pop(int index) {
        if (this.isEmpty()) return null;

        return super.removeElementByIndex(index);
    }
    public T get(int index) {
        return super.getElement(index);
    }

}
