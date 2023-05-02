package S_designPatterns.domain;

import java.util.HashSet;
import java.util.Set;

public class AircraftSingletonLazy {
    // Lazy Initialization: Não carrega de forma "gulosa/ansiosa" pois agora não ira criar
    // o objeto no momento em que o classLoader passar por aqui (Diferente do Eager anterior).
    // Mais sobre ClassLoader: https://www.devmedia.com.br/entendendo-classloaders-em-java/29076
    private static AircraftSingletonLazy INSTANCE;
    private final Set<String> availableSeats = new HashSet<>();
    private final String name;
    private AircraftSingletonLazy(String name) {
        this.name = name;
    }
    {
        availableSeats.add("1A");
        availableSeats.add("1B");
    }
    public boolean bookSeat(String seat) {
        return availableSeats.remove(seat);
    }
    public static AircraftSingletonLazy getINSTANCE() {
        // Obs: Lazy por isso, ele não vai criar a instância no momento em que o classLoader
        // passar por aqui, e sim quando chamarmos este método, a primeira vez ele cria e nas
        // próximas vezes ele re-utiliza a mesma instância criada inicialmente.

        // Obs: Não THREAD-SAFE: pois aqui não é sincronizado.
//        if (INSTANCE == null) INSTANCE = new AircraftSingletonLazy("787-TAM");
//        return INSTANCE;

        // Solução: sincronizar o método como um tôdo ou utilizar Double Lock:
        if (INSTANCE == null) {
            synchronized (AircraftSingletonLazy.class){
                if (INSTANCE == null) {
                    INSTANCE = new AircraftSingletonLazy("787-TAM");
                }
            }
        }

        return INSTANCE;
    }
}
