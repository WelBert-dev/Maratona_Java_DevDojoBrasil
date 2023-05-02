package S_designPatterns.devDojo;

import S_designPatterns.domain.AircraftSingletonEager;
import S_designPatterns.domain.AircraftSingletonLazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Aula248a250Singleton {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        // --------------------- [Eager Inicialization] -----------------------
        // Obs: AircraftSingletonEager só possuí dois valores na lista: ["1A", "1B"]
        AircraftSingletonEager instanceEager = AircraftSingletonEager.getINSTANCE();
        AircraftSingletonEager instanceEager2 = AircraftSingletonEager.getINSTANCE();

        System.out.println(instanceEager.bookSeat("1A")); // true

        System.out.println(instanceEager2.bookSeat("1A")); // false

        // Provando que ambas referências apontam para o mesmo obj em memória:
        System.out.println(instanceEager); // S_designPatterns.domain.AircraftSingletonEager@65ab7765
        System.out.println(instanceEager2); // S_designPatterns.domain.AircraftSingletonEager@65ab7765
        System.out.println(instanceEager == instanceEager2); // true


        // --------------------- [Lazy Inicialization] -----------------------
        // Mesmo Singleton anterior, porém aqui ele carrega de uma forma Lazy (preguiçosa)
        // ou seja, apenas carrega em memória no momento em que chamarmos o getINSTANCE()
        // a primeira vez, nas próximas vezes ele re-utiliza a instância criada inicialmente.

        // OBS: NÃO é thread-safe pois a lógica de verificação inicial se a Instância é nula
        // não é sincronizada, ou seja se várias threads acessarem pode ocorrer inconcistências.
        // SOLUÇÃO: Sincronizar o método como um tôdo ou utilizar Double Lock.

        AircraftSingletonLazy instanceLazy = AircraftSingletonLazy.getINSTANCE();
        AircraftSingletonLazy instanceLazy2 = AircraftSingletonLazy.getINSTANCE();

        System.out.println(instanceLazy.bookSeat("1A")); // true

        System.out.println(instanceLazy2.bookSeat("1A")); // false

        // Provando que ambas referências apontam para o mesmo obj em memória:
        System.out.println(instanceLazy); // S_designPatterns.domain.AircraftSingletonLazy@eed1f14
        System.out.println(instanceLazy2); // S_designPatterns.domain.AircraftSingletonLazy@eed1f14
        System.out.println(instanceLazy == instanceLazy2); // true

        // AINDA PODE OCORRER PROBLEMAS se utilizar Reflexão para alterar o Obj e retirar o Singleton dele.

        // --------------------- [Burlando o Singleton com Reflexão] -----------------------
        System.out.println("Instância original: " + instanceLazy);
        // Instância original: S_designPatterns.domain.AircraftSingletonLazy@eed1f14

        Constructor<AircraftSingletonLazy> constructor = AircraftSingletonLazy.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true); // burla o encapsulamento do modificador private <=> public
        AircraftSingletonLazy aircraftSingletonLazyBurlado = constructor.newInstance("787-TAM");

        System.out.println("Instância burlada: "+ aircraftSingletonLazyBurlado);
        // Instância burlada: S_designPatterns.domain.AircraftSingletonLazy@4eec7777
        System.out.println(aircraftSingletonLazyBurlado.bookSeat("1A"));
        // Erá pra ser false pois já retiramos esse assento do objeto singleton original, porém não é o mesmo obj
        // pois burlamos o singleton com reflexão e agr é outro obj em memória logo == true

    }
}
