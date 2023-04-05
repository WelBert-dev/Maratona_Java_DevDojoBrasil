package methodReference;

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

/* Definições e Sintaxe para Referências ao Construtor:

Referência a um construtor é uma forma de se referenciar um construtor de uma
classe em vez de invocá-lo diretamente.
Ela é utilizada principalmente em programação funcional, onde é comum passar
métodos e construtores como argumentos para outras funções.

A referência a um construtor é útil em situações em que você precisa passar um
construtor como argumento para uma função, ou quando você deseja instanciar uma
classe com um determinado construtor em um momento posterior.

Aqui está um exemplo de como criar uma referência a um construtor em Java:

    // Classe exemplo com um construtor que recebe um argumento

    public class Exemplo {

        public Exemplo(String argumento) {
           ...
        }
    }

    // Criando uma referência a um construtor
    Function<String, Exemplo> referencia = Exemplo::new;

    // Vejamos que as assinaturas batem, aonde o tipo de entrada para Function
    // é String (compatível com o tipo de entrada do construtor)
    // e o tipo de retorno é um obj do tipo "Exemplo", ou seja o obj que será criado
    // posteriormente.

Neste Exemplo, O método "referencia" recebe uma String como argumento e retorna um
objeto Exemplo criado com o construtor que recebe um argumento do tipo String.

*/

/* Utilidades:

    - Passar um construtor como argumento para um método que cria objetos
    dinamicamente.

    - Instanciar objetos de uma classe em um momento posterior sem ter que
    escrever código adicional para criar uma instância manualmente.

    - Implementar fábricas de objetos genéricos, onde você precisa instanciar
    objetos de diferentes tipos em tempo de execução.

Em resumo, a referência a um construtor é uma ferramenta poderosa que permite
criar objetos de uma classe com um construtor específico de forma dinâmica,
passá-lo como argumento para outras funções e instanciar objetos de uma classe
em um momento posterior.

*/

/* Em casos de sobrecarga de construtor como o Java sabe qual é o correto?

O compilador Java usa a assinatura do construtor para determinar qual construtor
executar em casos de sobrecarga. A assinatura de um construtor inclui o nome do
construtor e os tipos de seus parâmetros.

*/

/* Supplier é uma Functional Interface que não recebe nada e retorna um <T>

Supplier em Java é uma interface funcional que representa um fornecedor de valores.
Ela define um método chamado "get()", que não recebe argumentos e retorna um valor.

O objetivo da interface Supplier é fornecer um objeto que possa fornecer valores
quando necessário.

- Utilidades:

    - Criação de objetos em tempo de execução: Em muitos casos, é necessário
    criar um objeto em tempo de execução.
    Pode ser usada para criar um objeto quando necessário, evitando assim a
    criação de objetos desnecessários.

    - Gerar valores de teste: Para testar um método que requer um valor, é
    possível usar a interface Supplier para fornecer um valor de teste.
    Dessa forma, é possível testar o método com vários valores diferentes.

    - Stream API: É usada em conjunto com a Stream API para gerar valores para
    uma stream.

    - API de Data e Hora: É usada em conjunto com a API de Data e Hora para
    gerar valores para datas e horas.

    - Injeção de dependência: É usada para injetar dependências em objetos.
    Isso é útil quando um objeto requer outro objeto como dependência, mas a
    criação do objeto dependente é complexa ou cara.

Em resumo, a interface Supplier é uma ferramenta muito útil para fornecer valores
quando necessário, criando objetos em tempo de execução e injetando dependências
em objetos. Ela é muito usada em APIs do Java, como a Stream API e a API de
Data e Hora, e pode ajudar a simplificar o código e torná-lo mais legível e
fácil de manter.

*/

import L_parametrizandoComportamentos.domain.CarModel;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class Aula200MethodReference_Referencia_ao_construtor {
    public static void main(String[] args) {

        //----------------- Quando o construtor NÃO recebe parâmetros --------------------
        // Obs: o objeto NÃO será criado agora!
        // Com Supplier NÃO é possível quando construtor possuí parametros!
        Supplier<CarComparators_MethodReferenceStaticAndNonStatic> newCarComparatorsSupplier =
                CarComparators_MethodReferenceStaticAndNonStatic::new;

        // Finalmente instância o Objeto:
        CarComparators_MethodReferenceStaticAndNonStatic objCriado = newCarComparatorsSupplier.get();

        //----------------- Quando o construtor recebe parâmetros ------------------------
        // obs: o objeto NÃO será criado agora!
        // Com BiFunction pois o construtor recebe mais de 1 param:
        // Obs Caso precise de mais params, deve-se criar um Function personalizado!
        // https://stackoverflow.com/questions/18400210/java-8-where-is-trifunction-and-kin-in-java-util-function-or-what-is-the-alt

        // CarModel Atributes:
        //    private String nome;
        //    private String brand;
        //    private String model;
        //    private String color;

        // Cria a FourFunction personalizada, pois o java só oferece TriFunction:
        // e meu objeto recebe 4 params no construtor:
        @FunctionalInterface
        interface FourFunction<A,B,C,D,R> {
            R apply(A a, B b, C c, D d);

            default <V> FourFunction<A, B, C, D, V> andThen(
                    Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d));
            }
        }

        // Ordem dos tipos: nome, brand, model, color, returns CarModel
        FourFunction<String, String, String, String, CarModel> newCarModelWithFourFunction =
                CarModel::new;

        // Finalmente instância o objeto com o FourFunction personalizado:
        CarModel objCriado2 = newCarModelWithFourFunction.apply("Audi", "Audi", "A3", "green");
        System.out.println(objCriado2);

    }
}
