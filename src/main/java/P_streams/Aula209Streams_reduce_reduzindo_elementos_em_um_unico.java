package P_streams;

/* Método poderoso reduce() que reduz o fluxo de <E> em um único:
Assinatura do método: Optional<T> reduce(BinaryOperator<T> accumulator);

O método reduce() é um dos métodos mais poderosos da API Streams.
Ele é usado para reduzir uma sequência de elementos a um único elemento, aplicando
uma operação associativa aos elementos da sequência.

A operação associativa é uma operação que pode ser aplicada a DOIS elementos
quaisquer da sequência, e cujo resultado pode ser combinado com o resultado da
operação aplicada a outros elementos da sequência.
Por exemplo, a soma e a multiplicação são operações associativas, enquanto a
subtração e a divisão não são.

---> Operação ASSOCIATIVA na matemática:
(Resumo: Transitividade aonde 2 + 1 é o msm que 1 + 2);
Ou seja, "A ordem dos fatores não altera o produto";
Poréeeem, não é bem assim pois strings a ordem altera sim
    Exemplo: "hellow" e  "word" em operação de concatenação == wordhellow

É uma propriedade matemática que significa que o resultado de uma operação binária
(isto é, que envolve dois elementos) é o mesmo, independentemente da ordem em que
os elementos são processados.
Por exemplo, para uma operação associativa "op", o seguinte é verdadeiro:

    (a op b) op c = a op (b op c) --> (aonde op é +, -, /, *..)

Em outras palavras, se você aplicar a operação "op" aos elementos "a" e "b",
e depois aplicá-la ao resultado e "c", o resultado final será o mesmo que se
você aplicasse "op" aos elementos "b" e "c" primeiro, e depois aplicasse "op"
ao resultado e "a".

- SÃO Operações Associativas:
A SOMA e a MULTIPLICAÇÃO são operações associativas porque a ordem em que se
adicionam ou multiplicam números não afeta o resultado final.
Por exemplo:

    2 + 3 + 4 é o mesmo que 4 + 2 + 3, e 2 * 3 * 4 é o mesmo que 4 * 2 * 3.

- NÃO são Operações Associativas:
Já a subtração e a divisão não são operações associativas porque a ordem em que
se subtraem ou dividem números pode afetar o resultado final.
Por exemplo:

    (10 - 5) - 3 é diferente de 10 - (5 - 3), e (20 / 5) / 2 é diferente de 20 / (5 / 2).


- A propriedade de associatividade é importante no contexto do método reduce()
da API Streams, porque a função binária que é usada como argumento deve ser
associativa para que o resultado seja o mesmo, independentemente da ordem em que
os elementos são processados.
Se a função binária não for associativa, o resultado final do método reduce()
pode ser diferente, dependendo da ordem em que os elementos são processados.

O método reduce() recebe como argumento uma função binária que implementa a
operação associativa, e retorna um Optional que contém o resultado da operação.
O resultado pode ser um objeto de qualquer tipo, desde que a operação seja
definida para esse tipo.

---> Algumas aplicações e utilidades do método reduce() incluem:

    - Somar os elementos de uma sequência de números:

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum); // imprime 15

        ou também podemos utilizar method reference:
        numbers.stream().reduce(Integer::sum).ifPresent(System.out::println);


    - Multiplicar os elementos de uma sequência de números:

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int product = numbers.stream().reduce(1, (x, y) -> x * y);
        System.out.println(product); // imprime 120

    - Encontrar o maior elemento de uma sequência:

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.get()); // imprime 5

        obs: poderia utilizar o método max() ao invés do reduce.

    - Concatenar os elementos de uma sequência de strings:

        List<String> words = Arrays.asList("foo", "bar", "baz");
        String concatenated = words.stream().reduce("", (a, b) -> a + b);
        System.out.println(concatenated); // imprime "foobarbaz"
*/

/* O reduce() recebe BinaryOperator<T> accumulator, quais outros tipos?

O Java possui diversos tipos de BinaryOperator, que são interfaces funcionais que
representam uma operação binária entre dois operandos do mesmo tipo, produzindo
um resultado do mesmo tipo.

- Obs: Todos são Interfaces Funcionais

- Segue abaixo uma lista dos principais tipos de BinaryOperator:

    - BinaryOperator<T>: É a interface mais genérica de BinaryOperator.
    Ela representa uma operação binária entre dois operandos do tipo T,
    produzindo um resultado do tipo T.

    - DoubleBinaryOperator: Representa uma operação binária entre dois valores
    do tipo double, produzindo um resultado do tipo double.

    - IntBinaryOperator: Representa uma operação binária entre dois valores do
    tipo int, produzindo um resultado do tipo int.

    - LongBinaryOperator: Representa uma operação binária entre dois valores do
    tipo long, produzindo um resultado do tipo long.

- Além desses tipos de BinaryOperator, também possui outras interfaces funcionais
que representam operações binárias específicas, como:

    - BiFunction<T, U, R>: Representa uma função que recebe dois argumentos do tipo
    T e U e produz um resultado do tipo R.

    - ObjDoubleConsumer<T>: Representa um consumidor que recebe um objeto do tipo
    T e um valor do tipo double.

    - ObjIntConsumer<T>: Representa um consumidor que recebe um objeto do tipo
    T e um valor do tipo int.

    - ObjLongConsumer<T>: Representa um consumidor que recebe um objeto do tipo
    T e um valor do tipo long.

*/

import java.util.Arrays;
import java.util.List;

public class Aula209Streams_reduce_reduzindo_elementos_em_um_unico {
    public static void main(String[] args) {

        // Somando todos os valores entre sí, acumulando em um unico
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); // 1 + 2 + 3 + 4 + 5 = 15
        int sum = numbers.stream()
                .reduce(0, (x, y) -> x + y); // identity == valor inicial, ou seja, se 2
                                                    // então o resultado começa em 2 e vai somando
                                                    // portanto no final vai ser result + 2
        System.out.println(sum); // 15

        // Obs: Com method reference
        numbers.stream()
                .reduce(Integer::sum)
                .ifPresent(System.out::println); // sem utilizar a sobrecarga de identity
                                                 // o retorno será Optional<E>  

        // Obs: Com method reference e utilizando sobrecarga identity:
        Integer somaAcumuladaDosElementos = numbers.stream()
                .reduce(0, Integer::sum);

        // Operação com debbuger em tempo de execução, printando todos os valores em time current:

        Integer sumOfAllElements = numbers.stream()
                .reduce((x, y) -> {
                    int result = x + y;
                    System.out.println("x = " + x + ", y = " + y + ", x + y = " + result);
                    return result;
                })
                .orElse(0);
        // x = 1, y = 2, x + y = 3
        // x = 3, y = 3, x + y = 6
        // x = 6, y = 4, x + y = 10
        // x = 10, y = 5, x + y = 15
        System.out.println("A soma de x e y é igual a " + sumOfAllElements);
        // A soma de x e y é igual a 15
    }
}
