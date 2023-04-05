package methodReference;

// Bastante utilizado em conjunto com lambdas, aonde melhora a sintaxe/coesão
// A única regra de utilização com lambdas são:
// Apenas utilizar method reference quando a lambda acessar apenas um método.

/* Definições sobre os 4 tipos de method reference:

    - Referência a métodos estáticos: MyClass::staticMethod;
    Este tipo de referência é usado para referenciar um método estático existente.
    O nome da classe é usado como prefixo do nome do método estático

    - Referência a métodos de instância em um objeto particular: obj::instanceMethod;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto específico. O objeto é especificado antes do nome do método.

    - Referência a método de instância de um tipo de objeto arbitrário: String::length;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto de um tipo arbitrário. O objeto é passado como o primeiro parâmetro para
    a expressão lambda, e a sintaxe é ClassName::instanceMethod.

    - Referência a um construtor: ArrayList::new;
    Este tipo de referência é usado para referenciar um construtor de uma classe.
    A sintaxe é ClassName::new.

*/

/* Definições mais precisas sobre `Referência a métodos estáticos`:

As referências a métodos estáticos são uma funcionalidade introduzida no Java 8
que permite criar facilmente expressões lambda que chamam métodos estáticos.
Uma referência a método estático é uma expressão que referencia um método
estático existente, sem chamar explicitamente o método na expressão lambda.

As referências a métodos estáticos são usadas para simplificar a escrita de código
e torná-lo mais legível, especialmente quando a expressão lambda contém apenas uma
chamada a um método estático. Elas também ajudam a evitar a duplicação de código,
uma vez que um método estático pode ser reutilizado em várias expressões lambda.

As referências a métodos estáticos são definidas usando o operador de duplo-colon
(::) seguido pelo nome do método estático.
Por exemplo, para criar uma referência a um método estático chamado printMessage na
classe MyClass, podemos usar a seguinte sintaxe:

    MyClass::printMessage

Podemos então usar essa referência a método estático como argumento para uma
expressão lambda que espera um tipo de interface funcional que tenha um único
método abstrato compatível com o método estático.
Por exemplo, podemos usar a referência a método estático MyClass::printMessage
como argumento para um objeto do tipo Consumer<String>, que representa uma função
que aceita um argumento do tipo String e não retorna nenhum valor:

    Consumer<String> messagePrinter = MyClass::printMessage;

Isso é equivalente a escrever uma expressão lambda que chama explicitamente o
método estático:

    Consumer<String> messagePrinter = message -> MyClass.printMessage(message);

Usando referências a métodos estáticos, o código pode ficar mais legível e mais
conciso, especialmente quando várias expressões lambda usam o mesmo método estático.

*/


// Lambdas só se importam com `target type` e `functional descriptor`,
// Target type: É o contexto de aonde a lambda esta inserida.
// Functional descriptor: É a entrada e a saída do método, ambos tem que
// ser do mesmo tipo, se respeitar essas regras não será necessário
// passar no teste `É UM` do polimorfismo, apenas deve respeitar
// o target type e o functional descriptor.


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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Aula198MethodReference_Referencia_a_metodos_estaticos {
    public static void main(String[] args) {

        // Exemplo utilizado na aula 197Lambdas_IFunction:
        // Porém adequando ao method reference static:

        List<String> listNames = List.of("Wellison", "Danielle");

        //List<Integer> listLengthOfNames = map(listNames, n -> n.length()); <- ANTES
        List<Integer> listLengthOfNames = map(listNames, String::length); // <- DEPOIS
        System.out.println(listLengthOfNames); // [8, 8]

        //List<String> listNamesInUpperCase = map(listNames, n -> n.toUpperCase()); <- ANTES
        List<String> listNamesInUpperCase = map(listNames, String::toUpperCase); // <- DEPOIS
        System.out.println(listNamesInUpperCase); // [WELLISON, DANIELLE]


        // Exemplo de utilização respeitando as regras do target type e functional descriptor:
        // Exemplo: Passando uma lambda ao invés de passar um Comparator<> que passa no teste `É UM`;

        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        // Obs: Passamos uma lambda que respeita os tipos de entradas e retornos do Comparator<>
        Collections.sort(listCars, (o1, o2) -> o1.getColor().compareTo(o2.getColor()));
        System.out.println(listCars);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T e : list) {
            R r = function.apply(e);
            result.add(r);
        }

        return result;
    }
}
