package S_designPatterns.domain;

import java.util.HashSet;
import java.util.Set;

public class AircraftSingletonEager {
    // Eager Initialization: Por conta de ele ser estático, a criação vai se dar no momento
    // em que o classLoader passar por essa classe.
    // Mais sobre ClassLoader: https://www.devmedia.com.br/entendendo-classloaders-em-java/29076
    private static final AircraftSingletonEager INSTANCE = new AircraftSingletonEager("787-TAM");
    private final Set<String> availableSeats = new HashSet<>();
    private final String name;
    private AircraftSingletonEager(String name) {
        this.name = name;
    }
    {
        availableSeats.add("1A");
        availableSeats.add("1B");
    }
    public boolean bookSeat(String seat) {
        return availableSeats.remove(seat);
    }
    public static AircraftSingletonEager getINSTANCE() {
        return INSTANCE;
    }
}
