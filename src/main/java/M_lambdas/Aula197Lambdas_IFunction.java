package M_lambdas;

// Function<T, R> aonde T é oque esta recebendo e R é o retorno;

/* Assinatura dele:

    public interface Function<T, R> {
        R apply(T t);
    }

*/
/* Definições mais precisas:

É uma interface funcional genérica que representa uma função que recebe um argumento
de um determinado tipo e retorna um resultado de outro tipo.

- Onde "T" é o tipo do argumento de entrada e "R" é o tipo do resultado de saída.

A principal utilidade da interface Function é permitir a passagem de funções como
argumentos para outros métodos, o que é especialmente útil em programação funcional
e em situações em que a lógica de processamento de dados precisa ser dinâmica e
flexível.

Por exemplo, podemos usar a interface Function para mapear os elementos de uma lista
de um tipo para outro tipo. Suponha que temos uma lista de objetos do tipo "Person" e
queremos transformá-la em uma lista de Strings contendo apenas os nomes das pessoas.
Podemos usar a interface Function da seguinte maneira:

    List<Person> people = ... // lista de pessoas
    Function<Person, String> nameExtractor = person -> person.getName();
    List<String> names = people.stream().map(nameExtractor).collect(Collectors.toList());

Neste exemplo, usamos a interface Function para definir a função que extrai o nome de
uma pessoa, que é então passada como argumento para o método "map" da classe "Stream",
que aplica essa função a cada elemento da lista de pessoas e retorna uma nova lista
contendo apenas os nomes.

Outra aplicação comum da interface Function é na implementação de callbacks em
bibliotecas e frameworks. Por exemplo, podemos fornecer uma função que será chamada
quando um determinado evento ocorrer em uma aplicação.

Em resumo, a interface Function é uma ferramenta poderosa para trabalhar com funções,
permitindo a criação de código mais flexível, modular e coeso.

*/
// ----------------------------------------------------------------------------
/* Diferenças e Aplicações para cada Interface Funcional:
- (Predicate, Consumer e Function);

* FunctionalInterface Predicate: É usada para avaliar uma expressão booleana em
um ou mais argumentos.

- A declaração da interface Predicate é:

    public interface Predicate<T> {
        boolean test(T t);
    }

Onde "T" é o tipo do argumento de entrada. A função test() retorna um valor booleano,
que pode ser usado para filtrar ou avaliar elementos de uma coleção.

Por exemplo, podemos usar para filtrar uma lista de números, mantendo apenas aqueles
que são pares. Suponha que temos uma lista de números inteiros e queremos filtrá-la
para manter apenas os números pares. Podemos usar a interface Predicate da seguinte
forma:

    List<Integer> numbers = ... // lista de números
    Predicate<Integer> isEven = n -> n % 2 == 0;
    List<Integer> evenNumbers = numbers.stream().filter(isEven).collect(Collectors.toList());

Neste exemplo, usamos a interface Predicate para definir a função que verifica se um
número é par, que é então passada como argumento para o método "filter" da classe "Stream",
que aplica essa função a cada elemento da lista de números e retorna uma nova lista contendo
apenas os números pares.

----------------------------------------------------------------------------

* FunctionalInterface Consumer: É usada para realizar uma operação em um ou mais
argumentos sem retornar um valor.

- A declaração da interface Consumer é:

    public interface Consumer<T> {
        void accept(T t);
    }

Onde "T" é o tipo do argumento de entrada. A função accept() não retorna nenhum valor,
mas pode ser usada para realizar operações em elementos de uma coleção ou em outras
estruturas de dados.

Por exemplo, podemos usar para imprimir cada elemento de uma lista de Strings.
Suponha que temos uma lista de Strings e queremos imprimir cada elemento.
Podemos usar a interface Consumer da seguinte forma:

    List<String> strings = ... // lista de Strings
    Consumer<String> printer = s -> System.out.println(s);
    strings.forEach(printer);

Neste exemplo, usamos a interface Consumer para definir a função que imprime uma
String, que é então passada como argumento para o método "forEach" da lista de
Strings, que aplica essa função a cada elemento da lista e imprime o resultado.

----------------------------------------------------------------------------

* FunctionalInterface Function: É usada para transformar um ou mais argumentos
em um resultado de outro tipo.

- A declaração da interface Function é:

    public interface Function<T, R> {
        R apply(T t);
    }

Onde "T" é o tipo do argumento de entrada e "R" é o tipo do resultado de saída.
A função apply() recebe um argumento do tipo "T" e retorna um resultado do tipo "R".

Por exemplo, podemos usar para transformar uma lista de objetos de um tipo para
outro tipo. Suponha que temos uma lista de objetos "Person" e queremos transformá-la
em uma lista de Strings contendo apenas os nomes das pessoas.
Podemos usar a interface Function da seguinte forma:

    List<Person> people = ... // lista de pessoas

    Function<Person, String> nameExtractor = Person::getName;

    List<String> names = people.stream()
                               .map(nameExtractor)
                               .collect(Collectors.toList());

Neste exemplo, usamos a referência de método Person::getName como argumento da
interface Function.
Isso significa que a função apply() da interface Function será chamada para cada
objeto Person na lista, e o resultado da função será o nome da pessoa.
Em seguida, usamos a operação map() da API de streams do Java para transformar
cada objeto Person em seu nome, usando a função nameExtractor.
Finalmente, coletamos os resultados em uma nova lista de strings usando a operação
collect().

*/



import L_parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Aula197Lambdas_IFunction {
    public static void main(String[] args) {
        List<String> listNames = List.of("Wellison", "Danielle");

        List<Integer> listLengthOfNames = map(listNames, n -> n.length());
        System.out.println(listLengthOfNames); // [8, 8]

        List<String> listNamesInUpperCase = map(listNames, n -> n.toUpperCase());
        System.out.println(listNamesInUpperCase); // [WELLISON, DANIELLE]

        // Utilizando Method Reference:

        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        Function<CarModel, String> nameExtractor = CarModel::getNome;

        List<String> namesOfCars = listCars.stream()
                .map(nameExtractor)
                .collect(Collectors.toList());

        System.out.println(namesOfCars);
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
