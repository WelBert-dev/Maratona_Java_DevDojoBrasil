package P_streams;

// Collectors é um framework bem poderoso como operações finais em Streams.

/* Definições

É uma classe utilitária que fornece vários métodos estáticos para coletar
elementos de um stream em uma coleção ou um valor único.

Os métodos da classe Collectors são usados para criar uma instância de Collector,
que define como os elementos de um stream devem ser coletados. Um Collector
especifica uma operação de redução, que pode ser usada para combinar todos os
elementos de um stream em uma coleção ou um valor único.

*/

/* Métodos uteis

    - toList(): Retorna uma lista que contém todos os elementos do stream.

    - toSet(): Retorna um conjunto que contém todos os elementos do stream,
    sem duplicatas.

    - toMap(): Retorna um mapa que contém pares chave-valor dos elementos do
    stream, onde a chave e o valor são especificados por funções de mapeamento.

    - joining(): Retorna uma string contendo a concatenação de todos os elementos
    do stream, separados por um delimitador especificado.

    - counting(): Retorna o número de elementos no stream como um valor long.

    - minBy(): Retorna o menor elemento do stream com base em um comparador
    especificado.

    - maxBy(): Retorna o maior elemento do stream com base em um comparador
    especificado.

    - averagingInt(), averagingDouble(), averagingLong(): Retorna a média dos
    valores inteiros, de ponto flutuante ou longos do stream.

    - summingInt(), summingDouble(), summingLong(): Retorna a soma dos valores
    inteiros, de ponto flutuante ou longos do stream.

    - partitioningBy(): Retorna um mapa que contém as partições dos elementos
    do stream de acordo com um predicado especificado.

    - groupingBy(): Retorna um mapa que contém os elementos do stream agrupados
    de acordo com uma função de agrupamento especificada.

    - reducing(): Retorna um coletor que executa uma redução sequencial dos
    elementos do stream, com base em uma operação binária especificada.

    - mapping(): Retorna um coletor que aplica uma função de mapeamento aos
    elementos do stream antes de coletá-los.

    - teeing(): Retorna um coletor que permite agrupar resultados de dois coletores
    independentes em um terceiro resultado.

    - toCollection(): Retorna uma coleção especificada pelo usuário que contém todos
    os elementos do stream.

    - toConcurrentMap(): Retorna um mapa que contém pares chave-valor dos elementos
    do stream, onde a chave e o valor são especificados por funções de mapeamento,
    e que pode ser usado com segurança em threads concorrentes.

    - toUnmodifiableList(), toUnmodifiableSet(), toUnmodifiableMap(): Retorna uma
    lista, conjunto ou mapa que contém os elementos do stream, mas que não pode
    ser modificado.

    - summarizingInt(), summarizingDouble(), summarizingLong(): Retorna um objeto
    de resumo que contém estatísticas como a soma, média, mínimo e máximo dos
    valores inteiros, de ponto flutuante ou longos do stream.

    - filtering(): Retorna um coletor que aplica um filtro a elementos do stream
    antes de coletá-los.
*/


import P_streams.domain.LightNovelModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Aula213Streams_Collectors_summarizing_count_sum_min_avg_max_tudoEmUmObjeto_sumario {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0),
                    new LightNovelModel("KissXKiss", 3.2),
                    new LightNovelModel("Dragon Ball", 5.2),
                    new LightNovelModel("Danielle", 3.2),
                    new LightNovelModel("Welzika", 4.2)));
    public static void main(String[] args) {
        //---------------------- [ Operações separadas: ] ---------------------
        // COUNT (returns long e Wrapper de Long):
        long count = listOfLightNovels.stream()
                .count();
        Long countWithCollectors = listOfLightNovels.stream()
                .collect(Collectors.counting());
        // Output: 5

        // SUM (Evitando autoBoxing e unBoxing com mapToDouble):
        double sum = listOfLightNovels.stream()
                .mapToDouble(LightNovelModel::getPrice)
                .sum();
        Double sumWithCollectors = listOfLightNovels.stream()
                .collect(Collectors.summingDouble(LightNovelModel::getPrice));
        // Outpout: 17.8

        // MIN (returns Optional):
        listOfLightNovels.stream()
                .min(Comparator.comparing(LightNovelModel::getPrice))
                .ifPresent(System.out::println);
        listOfLightNovels.stream()
                .collect(Collectors.minBy(Comparator.comparing(LightNovelModel::getPrice)))
                .ifPresent(System.out::println);
        // Output: LightNovelModel{title='Tokyo Ghoul', price=2.0}

        // AVERAGE:
        listOfLightNovels.stream()
                .mapToDouble(LightNovelModel::getPrice)
                .average()
                .ifPresent(System.out::println);
        Double avgWithCollectors = listOfLightNovels.stream()
                .collect(Collectors.averagingDouble(LightNovelModel::getPrice));
        // Output: 3.56

        // MAX (returns Optional):
        listOfLightNovels.stream()
                .max(Comparator.comparing(LightNovelModel::getPrice))
                .ifPresent(System.out::println);
        listOfLightNovels.stream()
                .collect(Collectors.maxBy(Comparator.comparing(LightNovelModel::getPrice)))
                .ifPresent(System.out::println);
        // Output: LightNovelModel{title='Dragon Ball', price=5.2}

        //----------- [ Todas operações anteriores em apenas uma: ] -----------
        // Resumo, com todas essas operações em apenas um Objeto:
        // Obs: Porém ele trás apenas os valores, ou seja
        // não é possível analisar o Objeto no qual é referente!
        // exemplo: minimo é o LightNovelModel{title='Tokyo Ghoul', price=2.0}
        // porém ele trás apenas o valor 2.0, não faz referência ao objeto por completo.
        DoubleSummaryStatistics sumario = listOfLightNovels.stream()
                .collect(Collectors.summarizingDouble(LightNovelModel::getPrice));

        System.out.println(sumario);
        // DoubleSummaryStatistics{count=5, sum=17,800000, min=2,000000, average=3,560000, max=5,200000}

        //--------------------- [Concatenando strings: ] ----------------------
        String concatStringWithCollectors = listOfLightNovels.stream()
                .map(LightNovelModel::getTitle)
                .collect(Collectors.joining(", "));
        // Output: Tokyo Ghoul, KissXKiss, Dragon Ball, Danielle, Welzika
    }
}
