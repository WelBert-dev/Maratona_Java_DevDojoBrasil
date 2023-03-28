package optional;

// - Obs: NÃO pode utilizar Optional como parâmetros de entrada!!
// - Também NÃO pode utilizar Optional como Atributos de classes!! pois ela não é serializer
// - Indicado apenas para utilizar em retornos de métodos!

/* Definições sobre Optional:

A classe Optional é uma classe que foi introduzida no Java 8 para tratar valores
nulos em um programa de forma mais elegante e segura.
A classe Optional encapsula um valor e permite verificar se o valor é nulo ou não.

O objetivo da classe Optional é evitar NullPointerExceptions, que são comuns em
programas Java quando se tenta acessar um objeto nulo.
A classe Optional ajuda a tornar o código mais legível e mais seguro, uma vez que
obriga o programador a verificar se o valor é nulo antes de tentar acessá-lo.

Em resumo, a classe Optional é uma ferramenta muito útil para tratar valores nulos
de forma mais elegante e segura, melhorar a legibilidade do código e simplificar o
tratamento de valores nulos.
A classe é amplamente utilizada em APIs Java e é uma parte importante da programação
Java moderna.

*/

/* Utilidades e aplicações:

    - Evitar NullPointerExceptions: Ajuda a evitar NullPointerExceptions, uma vez
    que é necessário verificar se o valor está presente antes de tentar acessá-lo.

    - Melhorar a legibilidade do código: Torna o código mais legível, uma vez que
    obriga o programador a verificar se o valor está presente antes de tentar
    acessá-lo.

    - Simplificar o tratamento de valores nulos: Torna o tratamento de valores nulos
    mais simples e seguro.
    Em vez de escrever vários testes if para verificar se um valor é nulo ou não, é
    possível usar a classe Optional para verificar isso de forma mais elegante.

    - Combinar valores: Permite combinar valores de forma mais simples e segura.
    Por exemplo, é possível combinar dois objetos Optional usando o método
    "orElse()" ou "orElseGet()" para retornar o primeiro valor presente.

    - Usar com Streams: Pode ser usada em conjunto com a Stream API do Java para
    tratar valores nulos em operações de fluxo.

*/

/* Métodos mais Uteis do Optional:

- isPresent(): Verifica se o valor encapsulado está presente.
Retorna true se o valor estiver presente e false caso contrário.

- get(): Retorna o valor encapsulado, se estiver presente.
Caso contrário, lança uma exceção NoSuchElementException.

- orElse(T other): Retorna o valor encapsulado, se estiver presente.
Caso contrário, retorna o valor padrão especificado como argumento.

- orElseGet(Supplier<? extends T> other): Retorna o valor encapsulado,
se estiver presente.
Caso contrário, retorna o valor gerado pelo objeto Supplier especificado
como argumento.

- orElseThrow(Supplier<? extends X> exceptionSupplier): Retorna o valor encapsulado,
se estiver presente.
Caso contrário, lança a exceção gerada pelo objeto Supplier especificado como argumento.

- map(Function<? super T, ? extends U> mapper): Transforma o valor encapsulado
usando a função especificada como argumento e retorna um objeto Optional contendo
o resultado da transformação.

- flatMap(Function<? super T, Optional<U>> mapper): Transforma o valor encapsulado
usando a função especificada como argumento, que retorna um objeto Optional.
Retorna um objeto Optional vazio se o valor encapsulado for nulo ou o objeto Optional
retornado pela função especificada se for vazio.

- filter(Predicate<? super T> predicate): Verifica se o valor encapsulado satisfaz
a condição especificada pela função Predicate.
Retorna um objeto Optional contendo o valor encapsulado se ele satisfizer a condição
especificada, caso contrário, retorna um objeto Optional vazio.

Os métodos acima são apenas alguns exemplos dos métodos disponíveis na classe
Optional. Eles são úteis para verificar se um valor está presente, manipular
valores de forma segura e evitar exceções NullPointerException.

*/

import parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Aula201Optional_Working_with_nulls {
    public static void main(String[] args) {

        // Quando o retorno do método pode ser null:

        //Optional.of(null); // <- NullPointer
        Optional.ofNullable(null); // Aceita nulls
        Optional<String> wellison = Optional.ofNullable(findNameWithOthersAPI("wellison"));
        System.out.println(wellison); // Optional.empty

        wellison.orElse("EMPTY"); // retorna o obj ou se ele for null retorna o param "EMPTY";

        Optional<Object> empty = Optional.empty(); // criando um Optional vazio

        // Se o Objeto não for empty podemos executar um Consumer (Functional Interface):
        // Obs: Consumer não possue retorno, porisso chamamos o print dentro da lambda!

        wellison.ifPresent(objEncapsulado -> System.out.println(objEncapsulado)); // Wellison

        // Se Optional for empty ou seja não encapsula nada ele executa um Runnable
        wellison.ifPresentOrElse(objEncapsulado -> System.out.println(objEncapsulado),
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Optional é Vazio!");
                    }
                });

        // Se existir na lista altera o valor do atributo do objeto da lista:

        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        find(listCars, car -> car.getColor().equals("green"))
                .ifPresent(car -> car.setColor(car.getColor() + " = VERDE")); // color="green = Verde";

        System.out.println(listCars); // Objeto alterado em memória


        // Se não existir e queremos lançar uma exception:
        // Obs: Quando utilizar orElse alguma coisa ele extraí o obj encapsulado!
        CarModel lamborguini = find(listCars, car -> car.getModel().equals("lamborguini"))
                .orElseThrow(IllegalArgumentException::new);


        // Caso não exista e queremos criar um objeto equivalente:
        CarModel lamborguiniCreated = find(listCars, car -> car.getModel().equals("lamborguini"))
                .orElse(new CarModel());
        // produz o mesmo resultado, porém utilizando um Supplier (Não recebe argumentos e exec .get());
        CarModel lamborguiniCreatedSupplier = find(listCars, car -> car.getModel().equals("lamborguini"))
                .orElseGet(() -> new CarModel());

    }

    // API de terceiros aonde não temos controle sobre o retorno:
    // cenário aonde NÃO podemos alterar o retorno para um Optional ao invés de null
    public static String findNameWithOthersAPI(String name) {
        List<String> list = List.of("Wellison", "Danielle", "Irineu");

        if (list.contains(name)) {
            return name;
        }

        return null;
    }

    // Método com melhor cenário aonde ja retorna um Optional:

    public static Optional<String> findNameWithUsingOptional(String name) {
        List<String> list = List.of("Wellison", "Danielle", "Irineu");

        if (list.contains(name)) {
            return Optional.of(name);
        }

        return Optional.empty();
    }

    // Método de busca genérica utilizando lambda com Predicate:
    public static Optional<CarModel> find(List<CarModel> list, Predicate<CarModel> predicate) {
        CarModel carFound = null;

        for (CarModel car : list) {
            if (predicate.test(car)) {
                carFound = car;
            }
        }

        return Optional.ofNullable(carFound);
    }
}
