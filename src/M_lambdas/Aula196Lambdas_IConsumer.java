package lambdas;

// Também é uma Interface Funcional do mesmo pacote que Predicate;
// - Diferença deles: Consumer tem retorno VOID;
// Mesmo contexto que o Predicate, porém sem retornos;

/* Definições mais precisas:

É uma interface funcional que representa uma função que recebe um argumento e não
retorna nenhum valor. O objetivo principal do Consumer é consumir ou realizar uma
ação com base no seu argumento.

O Consumer é frequentemente usado em combinação com outras interfaces funcionais,
como Predicate e Function, para criar pipelines de processamento de dados.
Por exemplo, um Predicate pode ser usado para filtrar uma lista de objetos com base
em um critério específico, e um Consumer pode ser usado para realizar uma ação em
cada objeto filtrado.

---> Algumas aplicações comuns do Consumer incluem:

    - Iteração de coleções: O Consumer pode ser usado para iterar sobre uma coleção
    de objetos e realizar uma ação em cada objeto.

    - Saída de dados: O Consumer pode ser usado para imprimir dados em um formato
    específico.

    - Efeitos colaterais: O Consumer pode ser usado para realizar efeitos colaterais
    em uma operação, como atualizar um banco de dados ou enviar uma notificação.

---> Algumas das principais utilidades do Consumer são:

    - Flexibilidade: O Consumer é uma interface funcional genérica que pode ser
    usada em uma ampla variedade de situações.

    - Composição: O Consumer pode ser facilmente composto com outras interfaces
    funcionais, como Predicate e Function, para criar pipelines de processamento
    de dados.

    - Clareza: O uso do Consumer pode tornar o código mais claro e legível, pois
    a lógica da ação é separada da lógica do restante do código.

Em resumo, o Consumer é usado para realizar ações em objetos ou dados, sem retornar
nenhum valor. Ele é frequentemente usado em combinação com outras interfaces funcionais
para criar pipelines de processamento de dados, tornando o código mais flexível,
composto e claro.

*/

import L_parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Aula196Lambdas_IConsumer {
    public static void main(String[] args) {

        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        // Podemos acessar na lambda qualquer método return VOID:
        forEach(listCars, c -> System.out.println(c));
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t); // ou seja, utilizamos o método para cada elemento
        }
    }
}
