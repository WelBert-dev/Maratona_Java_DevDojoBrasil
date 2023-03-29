package streams;

/*
---> Definições sobre a API Streams no Java 8:

A API Streams em Java é uma parte importante da biblioteca de coleções Java 8
e superiores. A API Streams é utilizada para processar coleções de objetos, tais
como listas, conjuntos, mapas, arrays, e assim por diante, de uma forma mais
concisa e eficiente.

Os Streams permitem a execução de operações em um conjunto de dados sem a
necessidade de alterar a coleção original. Eles oferecem uma série de recursos,
como filtragem, mapeamento, classificação, operações de redução e muito mais.

Em resumo, a API Streams em Java é uma ferramenta poderosa para processar e
manipular coleções de dados de uma maneira mais eficiente e expressiva,
permitindo uma maior flexibilidade e desempenho na programação.

---> Vantagens de se utilizar:

    - Fácil leitura e manutenção do código devido à sua expressividade.
    - Capacidade de processar coleções grandes de dados em paralelo.
    - Maior flexibilidade na execução de operações em coleções, sem a
    necessidade de criar loops explícitos.

---> Utilidades e aplicações:

    - Processamento de coleções de dados em aplicativos web e mobile.
    - Análise de dados em aplicações de ciência de dados.
    - Cálculos financeiros e matemáticos em aplicações financeiras.
    - Transformação e manipulação de dados em processos de ETL
    (Extract, Transform, Load).

---> Métodos mais Uteis da API Streams:

- filter(Predicate<T> predicate): retorna um Stream contendo apenas os elementos
da coleção original que correspondem ao predicado especificado.
Este método é útil para filtrar elementos de uma coleção com base em um critério
específico.

- map(Function<T, R> mapper): retorna um Stream contendo os resultados da aplicação
da função especificada a cada elemento da coleção original.
Este método é útil para transformar elementos de uma coleção em outros elementos
de um tipo diferente.

- flatMap(Function<T, Stream<R>> mapper): retorna um Stream contendo todos os
elementos resultantes da aplicação da função especificada a cada elemento da
coleção original, concatenados em um único Stream.
Este método é útil para transformar elementos de uma coleção em múltiplos
elementos de um tipo diferente.

- sorted(): retorna um Stream contendo os elementos da coleção original em ordem
natural (ascendente).
Este método é útil para classificar elementos de uma coleção em uma ordem específica.

- distinct(): retorna um Stream contendo apenas os elementos distintos da coleção
original. Este método é útil para remover elementos duplicados de uma coleção.

- reduce(T identity, BinaryOperator<T> accumulator): combina os elementos da coleção
original usando o operador binário especificado, começando com o elemento inicial
especificado.
Este método é útil para reduzir os elementos de uma coleção em um único valor.

- collect(Collector<T, A, R> collector): coleta os elementos da coleção original
em um resultado especificado pelo coletor especificado.
Este método é útil para coletar elementos de uma coleção em uma estrutura de dados
específica, como uma lista ou um mapa.

- forEach(Consumer<T> action): executa a ação especificada em cada elemento da
coleção original.
Este método é útil para executar uma ação em cada elemento de uma coleção sem
modificar a coleção em si.

- peek(Consumer<T> action): retorna um Stream contendo os mesmos elementos da
coleção original, mas executa a ação especificada em cada elemento do Stream.
Este método é útil para depuração e logging, pois permite executar uma ação em
cada elemento de uma coleção sem modificar o Stream em si.

- anyMatch(Predicate<T> predicate): retorna um valor booleano indicando se algum
elemento da coleção original corresponde ao predicado especificado.
Este método é útil para verificar se pelo menos um elemento de uma coleção
corresponde a um critério específico.

- allMatch(Predicate<T> predicate): retorna um valor booleano indicando se todos
os elementos da coleção original correspondem ao predicado especificado.
Este método é útil para verificar se todos os elementos de uma coleção correspondem
a um critério específico.

- noneMatch(Predicate<T> predicate): retorna um valor booleano indicando se nenhum
elemento da coleção original corresponde ao predicado especificado.
Este método é útil para verificar se nenhum elemento de uma coleção corresponde a
um critério específico.

- skip(long n): retorna um Stream contendo todos os elementos da coleção original,
exceto os primeiros n elementos.
Este método é útil para pular os primeiros elementos de uma coleção.

- limit(long maxSize): retorna um Stream contendo os primeiros maxSize elementos
da coleção original.
Este método é útil para limitar o número de elementos de uma coleção que são
processados.

- toArray(): retorna um array contendo todos os elementos do Stream.
Este método é útil para converter um Stream em um array.

*/

import streams.domain.LightNovelModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Aula203a205Streams_introduction {

    private static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                new LightNovelModel("Tokyo Ghoul", 2.0),
                new LightNovelModel("KissXKiss", 3.2),
                new LightNovelModel("Dragon Ball", 5.2),
                new LightNovelModel("Danielle", 3.2),
                new LightNovelModel("Welzika", 4.2)));

    public static void main(String[] args) {

        // ----------------------------[Antes da API Stream:] -----------------------------
        // 1 - Order LightNovels by title
        System.out.println("Antes de ordenar: \n"+listOfLightNovels);
        listOfLightNovels.sort(Comparator.comparing(LightNovelModel::getTitle));
        System.out.println("Depois de ordenar: \n"+listOfLightNovels);

        // 2 - Retrieve the first 3 titles LightNovels with price less than < 4.0
        List<String> titlesList = new ArrayList<>();
        for (LightNovelModel novel : listOfLightNovels) {
            if(novel.getPrice() < 4) {
                titlesList.add(novel.getTitle());
            }

            // Ao encontrar os 3 primeiros titulos que respeitem a regra para a iteração
            if(titlesList.size() >= 3) {
                break;
            }
        }

        System.out.println("[SEM STREAM] Lista com os 3 primeiros titulos less than 4.0: \n"+titlesList);

        // ----------------------------[COM a API Stream:] -----------------------------
        List<String> collectLightNovelsTitlesList = listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() < 4)
                .sorted(Comparator.comparing(LightNovelModel::getTitle))
                .limit(3)
                .map(LightNovelModel::getTitle)
                .collect(Collectors.toList()); // final operator

        System.out.println("[COM STREAM] Lista com os 3 primeiros titulos less than 4.0: \n"+collectLightNovelsTitlesList);

        // Quantos LightNovels tem o preço menor que 4.0:

        long qtdeLightNovelsWithPriceLessThan4 = listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() < 4)
                .count();

        System.out.println(qtdeLightNovelsWithPriceLessThan4); // 3

        // Quantos LightNovels tem o preço menor que 4.0 com DISTINCT:

        listOfLightNovels.stream()
                .distinct() // o elemento deve implementar equals() e hashCode()
                .filter(novel -> novel.getPrice() < 4)
                .count();

        // Printando all element no console de uma maneira elegante
        listOfLightNovels.forEach(System.out::println);

    }
}
