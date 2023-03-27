package methodReference;

// Bastante utilizado em conjunto com lambdas, aonde melhora a sintaxe/coesão
// A única regra de utilização com lambdas são:
// Apenas utilizar method reference quando a lambda acessa apenas um método.

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


import java.util.ArrayList;
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
