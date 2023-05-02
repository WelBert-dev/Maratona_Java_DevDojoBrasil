package Q_threads.sincronismo;

// Quando uma classe é thread-safe quer dizer que os métodos delas são `sincronized`.
// PORÉM deve-se atentar com os escopos em que esta sendo definido, pois não adianta
// utilizar classes deste contexto em contextos não safe, ou seja, se atentar sobre
// o escopo em que esta sendo utilizado, talvez utilizando `sincronized` nele.

// Pois para garantir compatibilidade com a classe thread-safe utilizada o escopo
// também deve estar safe.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aula226Threads_ClassesThreadSafe {
    public static void main(String[] args) {
        ThreadSafeNames safe1 = new ThreadSafeNames();
        safe1.add("Wellison");
//        Runnable r1 = () -> {
//            safe1.removeFirst();
//        };
        // mesma coisa porém com method reference:
        Runnable r1 = safe1::removeFirst;
        new Thread(r1).start();
        new Thread(r1).start();

    }
}

class ThreadSafeNames {
    private final List<String> names = Collections.synchronizedList(new ArrayList<>());

    // Solução: Ou tornar o removeFirst() synchronized ou tornar a classe como um tôdo.
    // No caso seria todos os métodos com `synchronized`.
    // No caso se tornar todos os métodos aqui como sendo `synchronized` não será mais
    // necessário utilizar `Collections.synchronizedList(new ArrayList<>())` e sim
    // podemos utilizar uma List normal, pois os métodos de interface para tal ja o fazem.

    public synchronized void add(String name){
        names.add(name);
    }

    // Solução: Ou tornar o removeFirst() synchronized ou tornar a classe como um tôdo.
    public synchronized void removeFirst() {
        System.out.println("Antes de verificar size: Thread: "+Thread.currentThread().getName());
        if (names.size() > 0) {
            System.out.println("Depois de verificar size: Thread: "+Thread.currentThread().getName());
            System.out.println(names.remove(0));
            System.out.println("Depois de verificar size e remover. Thread: "+Thread.currentThread().getName());
        }
    }

}
