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

- Algumas aplicações comuns do Predicate incluem:

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

- Algumas das principais utilidades do Predicate são:

    - Flexibilidade: O Predicate é uma interface funcional genérica que pode ser
    usada em uma ampla variedade de situações.

    - Composição: O Predicate pode ser facilmente composto com outras interfaces
    funcionais, como Function e Consumer, para criar pipelines de processamento
    de dados.

    - Clareza: O uso do Predicate pode tornar o código mais claro e legível, pois
    a lógica da avaliação da condição é separada da lógica do restante do código.

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
