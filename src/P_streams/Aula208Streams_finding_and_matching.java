package P_streams;

/* Operações de finding e matching na API Streams

A API Streams em Java permite trabalhar com métodos como find e match para encontrar
elementos em uma sequência de dados.
Aqui está um resumo de como funcionam esses métodos:

-------------------------------------------------------------------------------

- findFirst(): Retorna o PRIMEIRO elemento da sequência que atende a uma determinada
condição.
Se nenhum elemento for encontrado, ele retorna um objeto Optional vazio.
Aqui está um exemplo de como usá-lo:

    Optional<Integer> firstElement = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n % 2 == 0)
    .findFirst();

    O método filter() é usado para filtrar apenas os números pares da sequência,
    e o método findFirst() retorna o PRIMEIRO número par encontrado na sequência.

-------------------------------------------------------------------------------

- findAny(): Retorna QUALQUER elemento da sequência que atenda a uma determinada
condição. (Não deterministico, ou seja é random of elements searched)
Se nenhum elemento for encontrado, ele retorna um objeto Optional vazio.
Aqui está um exemplo de como usá-lo:

    Optional<Integer> anyElement = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n % 2 == 0)
    .findAny();

    O método filter() é usado para filtrar apenas os números pares da sequência,
    e o método findAny() retorna QUALQUER número par encontrado na sequência.

-------------------------------------------------------------------------------

- anyMatch(): Retorna true se pelo menos um elemento da sequência atende a uma
determinada condição.
Caso contrário, ele retorna false.
Aqui está um exemplo de como usá-lo:

    boolean anyMatch = Stream.of("apple", "banana", "cherry", "date")
    .anyMatch(fruit -> fruit.startsWith("b"));

    O método anyMatch() verifica se PELO MENOS UM elemento da sequência começa
    com a letra "b". Como "banana" atende a essa condição, o método retorna true.

-------------------------------------------------------------------------------

- allMatch(): Retorna true se todos os elementos da sequência atenderem a uma
determinada condição.
Caso contrário, ele retorna false.
Aqui está um exemplo de como usá-lo:

    boolean allMatch = Stream.of("apple", "banana", "cherry", "date")
    .allMatch(fruit -> fruit.length() > 3);

    O método allMatch() verifica se TODOS os elementos da sequência têm mais de
    três caracteres. Como TODOS atendem a condição o método retorna true.

-------------------------------------------------------------------------------

- noneMatch(): Retorna true se NENHUM elemento da sequência atender a uma determinada
condição.
Caso contrário, ele retorna false.
Aqui está um exemplo de como usá-lo:

    boolean noneMatch = Stream.of("apple", "banana", "cherry", "date")
    .noneMatch(fruit -> fruit.startsWith("z"));

    O método noneMatch() verifica se nenhum elemento da sequência começa com a
    letra "z". Como nenhum elemento atende a essa condição, o método retorna true.

*/

import P_streams.domain.LightNovelModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Aula208Streams_finding_and_matching {
    private static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0),
                    new LightNovelModel("KissXKiss", 3.2),
                    new LightNovelModel("Dragon Ball", 5.2),
                    new LightNovelModel("Danielle", 3.2),
                    new LightNovelModel("Welzika", 4.2)));
    public static void main(String[] args) {
        // ---------------------- [Fazendo match no fluxo inteiro:] -----------------------
        // OBS: Retorno boolean;

        // QUALQUER um tem o preço maior do que 0?
        boolean qualquerUm = listOfLightNovels.stream()
                .anyMatch(novel -> novel.getPrice() > 0);

        // TODOS tem o preço maior do que 0?
        boolean todosTem = listOfLightNovels.stream()
                .allMatch(novel -> novel.getPrice() > 0 );

        // NENHUM o preço é igual a 0?
        boolean nenhumTem = listOfLightNovels.stream()
                .noneMatch(novel -> novel.getPrice() == 0);

        // ---------------------- [Fazendo match no fluxo filtrado:] -----------------------
        // Operações finais com retorno Optional<E>;
        // Assim podemos utilizar algum método do Optional e tratar essas possíbilidades
        // com ifPresent() ou ifPresentOrElse() e dispatch action runnable para resolver..

        // - findAny:
        // Busca qualquer um (Não Deterministico) que passe pelos filtros anteriores
        // (Random of elements searched);
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 0)
                .findAny()
                .ifPresent(System.out::println);

        // - findFirt:
        // Busca a PRIMEIRA ocorrência que passe pelos filtros anteriores.
        // (First element searched);
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 0)
                .findFirst()
                .ifPresent(System.out::println);


        // Problema:
        // Buscando o PRIMEIRO elemento maior que 10, apoós filtrar e organizar a coleção em ASC:
        // ASC: Ordena do menor para o maior (1, 2, 3, 4, 5, 6)
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 10)
                .sorted(Comparator.comparing(LightNovelModel::getPrice))
                .findFirst()
                .ifPresent(System.out::println);

        // Mesma coisa porém organizando em DESC:
        // DESC: Ordena do maior para o menor (5, 4, 3, 2, 1)
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 0)
                .sorted(Comparator.comparing(LightNovelModel::getPrice).reversed())
                .findFirst()
                .ifPresent(System.out::println);

        // Obs: Podemos ter o mesmo resultado mas escrito mais coeso:
        // Trazendo o maior preço da coleção/fluxo após passar pelo filtro
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 0)
                .max(Comparator.comparing(LightNovelModel::getPrice))
                .ifPresent(System.out::println);

        // Trazendo o menor preço da coleção/fluxo após passar pelo filtro
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 0)
                .min(Comparator.comparing(LightNovelModel::getPrice))
                .ifPresent(System.out::println);
    }
}
