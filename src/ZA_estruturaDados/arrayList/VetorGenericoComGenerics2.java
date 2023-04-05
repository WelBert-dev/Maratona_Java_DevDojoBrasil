package estruturaDados.arrayList;

import estruturaDados.utils.StaticStruct;

public class VetorGenericoComGenerics2<T> extends StaticStruct<T> {

    VetorGenericoComGenerics2(){
        super();
    }
    VetorGenericoComGenerics2(int capacity){
        super(capacity);
    }

    public boolean add(T element) {
        return super.add(element);
    }

    public boolean add(T element, int index) {
        return super.add(element, index);
    }
}
