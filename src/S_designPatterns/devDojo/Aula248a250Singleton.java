package S_designPatterns.devDojo;

import S_designPatterns.domain.AircraftSingletonEager;

public class Aula248a250Singleton {
    public static void main(String[] args) {
        // Obs: AircraftSingletonEager só possuí dois valores na lista: ["1A", "1B"]
        AircraftSingletonEager instanceEager = AircraftSingletonEager.getINSTANCE();
        AircraftSingletonEager instanceEager2 = AircraftSingletonEager.getINSTANCE();

        System.out.println(instanceEager.bookSeat("1A"));

        System.out.println(instanceEager2.bookSeat("1A"));

        // Provando que ambas referências apontam para o mesmo obj em memória:
        System.out.println(instanceEager); // S_designPatterns.domain.AircraftSingletonEager@65ab7765
        System.out.println(instanceEager2); // S_designPatterns.domain.AircraftSingletonEager@65ab7765
        System.out.println(instanceEager == instanceEager2); // true

    }
}
