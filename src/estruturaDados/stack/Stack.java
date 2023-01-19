package estruturaDados.stack;

import estruturaDados.utils.StaticStruct;

public class Stack<T> extends StaticStruct<T> {

    public Stack(){
        super();
    }
    public Stack(int capacity){
        super(capacity);
    }

    public boolean add(T element) {
        return super.add(element);
    }
    public boolean add(T element, int index) {
        return super.add(element, index);
    }

}
