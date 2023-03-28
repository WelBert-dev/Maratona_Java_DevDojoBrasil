package lambdas;

// Predicate é uma interface funcional,
// Interfaces funcionais possuem apenas UM METODO;
// porisso é possível utilizar a sintaxe lambda!

// Function descriptor: é o método ÚNICO e sua assinatura.

/* REGRAS quando se utiliza programação funcional c/ Lambdas:
    - A Interface deve definir o contrato para APENAS UM método.
    - o Function descriptor deve ser respeitado:
      - Se for apenas um parâmetro pode utilizar `x -> x.blabla`;
      - Se for mais de um parâmetro: `(param1,param2) -> param1.blabla`;
      - Se não existir parÂmetros utilizar: `() -> blabla`;
*/

// java.util.function -> pacote do Predicate

/* Definições mais precisas:

É uma interface funcional que representa uma função que recebe um argumento e
retorna um valor booleano. O objetivo principal do Predicate é avaliar uma
condição ou predicado em relação ao seu argumento e retornar um valor
verdadeiro ou falso.

O Predicate é frequentemente usado para filtrar coleções de objetos.
Ele pode ser usado em combinação com outras interfaces funcionais, como
Function e Consumer, para criar pipelines de processamento de dados.
Por exemplo, um Predicate pode ser usado para filtrar uma lista de objetos
com base em um critério específico e, em seguida, um Consumer pode ser usado
para imprimir os objetos filtrados.

---> Algumas aplicações comuns do Predicate incluem:

    - Filtragem de coleções: O Predicate pode ser usado para filtrar uma coleção
    de objetos com base em um critério específico. Por exemplo, um Predicate pode
    ser usado para filtrar uma lista de objetos para retornar apenas aqueles que
    atendem a um determinado critério, como objetos com um valor de atributo
    específico.

    - Validação de dados: O Predicate pode ser usado para validar dados de entrada
    com base em um critério específico. Por exemplo, um Predicate pode ser usado
    para validar se uma entrada de usuário é um número positivo.

    - Testes unitários: O Predicate pode ser usado em testes unitários para avaliar
    se uma classe ou método está produzindo o resultado esperado.

---> Algumas das principais utilidades do Predicate são:

    - Flexibilidade: O Predicate é uma interface funcional genérica que pode ser
    usada em uma ampla variedade de situações.

    - Composição: O Predicate pode ser facilmente composto com outras interfaces
    funcionais, como Function e Consumer, para criar pipelines de processamento
    de dados.

    - Clareza: O uso do Predicate pode tornar o código mais claro e legível, pois
    a lógica da avaliação da condição é separada da lógica do restante do código.

*/

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


import parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Aula195Lambdas_IPredicate {
    public static void main(String[] args) {
        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        // Utilizando Stream do java que recebe um Predicate:
        Stream<CarModel> greenCar = listCars.stream().filter(c -> c.getNome().equals(c.getBrand()));
        Optional<CarModel> first = greenCar.findFirst();
        System.out.println(first);
    }
}
