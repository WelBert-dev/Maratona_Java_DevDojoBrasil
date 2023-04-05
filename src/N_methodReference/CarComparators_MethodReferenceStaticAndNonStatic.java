package N_methodReference;

// Comparador do import parametrizandoComportamentos.domain.CarModel;

// Atributos:
//    private String nome;
//    private String brand;
//    private String model;
//    private String color;


// Não passa no teste "É UM" Comparator, porém respeita as regras de lambdas:
// Target Type e Functional Descriptor válidos.

/* O que é um target type e functional descriptor no java?

O target type (tipo alvo) é o tipo de dado esperado em um contexto onde uma
expressão lambda, uma referência a método ou uma classe anônima são usados.
O compilador Java usa o tipo alvo para determinar o tipo de interface funcional
que deve ser usada para validar a expressão lambda ou a referência a método.

Uma interface funcional é uma interface que define um único método abstrato
(também conhecida como método funcional), e pode ser usada como um tipo de
dado para expressões lambda, referências a método e classes anônimas.
Por exemplo, a interface funcional Runnable define um método "run()" sem
argumentos e sem retorno:

    @FunctionalInterface
    public interface Runnable {
        public abstract void run();
    }

Quando você passa uma expressão lambda para um método que espera um tipo Runnable,
o compilador usa o tipo alvo (Runnable) para determinar que a expressão lambda
deve ser validada como uma implementação de Runnable.

O functional descriptor (descritor funcional) é um termo usado para descrever a
assinatura de um método funcional, ou seja, a lista de argumentos e o tipo de
retorno do método.
O descritor funcional é usado pelo compilador Java para garantir que uma expressão
lambda ou uma referência a método corresponda a um tipo funcional compatível.

Por exemplo, se você tiver uma interface funcional que espera um método com um
parâmetro inteiro e um retorno booleano:

    @FunctionalInterface
    public interface MyFunctionalInterface {
        boolean myMethod(int x);
    }

Para criar uma expressão lambda ou uma referência a método que corresponda a essa
interface, você deve fornecer um método com a mesma assinatura
(um parâmetro inteiro e um retorno booleano).
Algo parecido com:

    MyFunctionalInterface func = (x) -> x > 0;

Em resumo, o target type e o functional descriptor são conceitos importantes em
Java para lidar com expressões lambda, referências a método e interfaces
funcionais, e são usados pelo compilador para garantir que as expressões sejam
válidas e compatíveis com o contexto em que são usadas.

*/

import L_parametrizandoComportamentos.domain.CarModel;

public class CarComparators_MethodReferenceStaticAndNonStatic {
    public CarComparators_MethodReferenceStaticAndNonStatic() {
    }

    // Comparações non-static:
    public int compareByNome_NonStatic(CarModel car1, CarModel car2) {
        return car1.getNome().compareTo(car2.getNome());
    }

    public int compareByBrand_NonStatic(CarModel car1, CarModel car2) {
        return car1.getBrand().compareTo(car2.getBrand());
    }

    public int compareByModel_NonStatic(CarModel car1, CarModel car2) {
        return car1.getModel().compareTo(car2.getModel());
    }

    public int compareByColor_NonStatic(CarModel car1, CarModel car2) {
        return car1.getColor().compareTo(car2.getColor());
    }


    // Comparações utilizando static:
    public static int compareByNome(CarModel car1, CarModel car2) {
        return car1.getNome().compareTo(car2.getNome());
    }

    public static int compareByBrand(CarModel car1, CarModel car2) {
        return car1.getBrand().compareTo(car2.getBrand());
    }

    public static int compareByModel(CarModel car1, CarModel car2) {
        return car1.getModel().compareTo(car2.getModel());
    }

    public static int compareByColor(CarModel car1, CarModel car2) {
        return car1.getColor().compareTo(car2.getColor());
    }


}
