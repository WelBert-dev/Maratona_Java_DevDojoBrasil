package S_designPatterns.devDojo;

/* Existem 3 formas de implementar esse padrão sendo elas:

- Eager Initialization: Cria uma classe que já instância a criação do objeto no
momento em que o classLoader carrega a classe em memória, e compartilha o mesmo
objeto em memória para todos que precisarem.

    - Obs: Eager pois ele carrega de uma forma ansiosa/gulosa ou seja, já cria a
    instância compartilhada no momento em que a classe é carregada pela JVM.

    - Mais Thread-Safe que o de baixo pois não ocorrem verificações se já
    existe uma instância do objeto criado em memória no momento em que precisa da
    instância singleton compartilhada (No momento em que se chama o objeto em questão).

    - Porém podemos ainda sim burlar o singleton (Compartilhamento da mesma instância
    do objeto em memória) utilizando reflexão para alterar a acessibilidade do
    construtor privado.

- Lazy Initialization: Cria uma classe que só instância a criação do objeto
quando alguem precisa/chama, e apartir dai compartilha esse mesmo objeto
em memória para as próximas chamadas.

    - Obs: Lazy porisso, ele não carrega ansioso/guloso, ele só cria quando
    é necessário, e apartir dai compartilha essa mesma criação para todos
    que precisarem/chamarem ele.

    - Não Thread-Safe pois é necessário realizar verificações se o objeto
    já existe em memória para ai sim criar, isso pode ocorrer condições de
    corrida em ambientes de paralelismo.

    - Para solucionar podemos sincronizar essa verificação e outros métodos
    que são propícios a problemas de corrida.

    - Apresenta o mesmo problama anterior sobre ser possível burlar com uso
    de reflexão.

- Utilizando Enum: Ao invés de utilizar classes para implementar esse padrão
podemos utilizar enumeradores que por padrão já são Thread-Safe para a criação/
instanciação de Objetos e também é seguro em relação a burlações com uso de
reflexão.

    - Por Padrão é Thread-Safe na instânciação/criação do Objeto.
    - Por Padrão não é possível alterar o modificador de acesso do construtor
    utilizando reflexão.


*/

import S_designPatterns.domain.AircraftSingletonEager;
import S_designPatterns.domain.AircraftSingletonEnum;
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

        // -------------- [Solução para a burlação com reflexão anterior] ------------------
        // Utilizando Enum: - Por padrão a criação de enumerações é Thread-Safe!
        //                  - Por padrão não é possível alterar o modificador de acesso com reflexão.

        AircraftSingletonEnum instanceEnum = AircraftSingletonEnum.INSTANCE;
        AircraftSingletonEnum instanceEnum2 = AircraftSingletonEnum.INSTANCE;

        System.out.println(instanceEnum.bookSeat("1A")); // true

        System.out.println(instanceEnum2.bookSeat("1A")); // false

        // Provando que ambas referências apontam para o mesmo obj em memória:
        System.out.println(instanceEnum.hashCode()); // 1096979270
        System.out.println(instanceEnum2.hashCode()); // 1096979270
        System.out.println(instanceEnum == instanceEnum2); // true
    }
}
