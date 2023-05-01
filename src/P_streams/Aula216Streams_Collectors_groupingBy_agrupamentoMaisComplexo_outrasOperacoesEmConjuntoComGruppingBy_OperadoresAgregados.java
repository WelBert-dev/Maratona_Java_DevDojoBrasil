package P_streams;

import P_streams.domain.CategoryEnum;
import P_streams.domain.LightNovelModel;

import java.util.*;
import java.util.stream.Collectors;

public class Aula216Streams_Collectors_groupingBy_agrupamentoMaisComplexo_outrasOperacoesEmConjuntoComGruppingBy_OperadoresAgregados {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0, CategoryEnum.FANTASY),
                    new LightNovelModel("KissXKiss", 3.2, CategoryEnum.ECCHI),
                    new LightNovelModel("Dragon Ball", 5.2, CategoryEnum.FANTASY),
                    new LightNovelModel("Danielle", 3.2, CategoryEnum.DRAMA),
                    new LightNovelModel("Welzika", 4.2, CategoryEnum.ADVENTURE)));
    public static void main(String[] args) {
        // Agrupando por categoria e contando a quantidade de ocorrências com count:
        Map<CategoryEnum, Long> operadorAgregadoCount = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory, Collectors.counting()));

        System.out.println(operadorAgregadoCount);
        // {FANTASY=2, ADVENTURE=1, ECCHI=1, DRAMA=1}

        // --------------------------------------------------------------------

        // Agrupando por categoria apenas o novel de maior valor para cada:
        Map<CategoryEnum, Optional<LightNovelModel>> maxPriceGruppingByCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.maxBy(Comparator.comparing(LightNovelModel::getPrice))));

        System.out.println(maxPriceGruppingByCategory);
        // {FANTASY=Optional[LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}],
        // ADVENTURE=Optional[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}],
        // ECCHI=Optional[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}],
        // DRAMA=Optional[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}]}

        // Mesmo agrupamento porém ao invés de return Optional<LightNovelModel> ja returns o
        // Objeto sem encapsular: (Coleta e faz algo, neste caso executa o get do Optional e
        // retorna o Objeto interno:
        Map<CategoryEnum, LightNovelModel> maxPriceGruppingByCategoryNotOptional = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(LightNovelModel::getPrice)),
                                Optional::get)));

        System.out.println(maxPriceGruppingByCategoryNotOptional);
        // {FANTASY=LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY},
        // ADVENTURE=LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE},
        // ECCHI=LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI},
        // DRAMA=LightNovelModel{title='Danielle', price=3.2, category=DRAMA}}

        // --------------------------------------------------------------------

        // Resumo de todos as Operações agregadas com summarizing:

        Map<CategoryEnum, DoubleSummaryStatistics> resumoPriceGruppingByCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.summarizingDouble(LightNovelModel::getPrice)));

        System.out.println(resumoPriceGruppingByCategory);
        // {FANTASY=DoubleSummaryStatistics{count=2, sum=7,200000, min=2,000000, average=3,600000, max=5,200000},
        // ADVENTURE=DoubleSummaryStatistics{count=1, sum=4,200000, min=4,200000, average=4,200000, max=4,200000},
        // ECCHI=DoubleSummaryStatistics{count=1, sum=3,200000, min=3,200000, average=3,200000, max=3,200000},
        // DRAMA=DoubleSummaryStatistics{count=1, sum=3,200000, min=3,200000, average=3,200000, max=3,200000}}


        // --------------------------------------------------------------------
        // SUM: Agrupando pela categoria e somando todos os preços dela (Total value):

        Map<CategoryEnum, Double> sumPricesGruppingByCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.summingDouble(LightNovelModel::getPrice)));
        System.out.println(sumPricesGruppingByCategory);
        // {DRAMA=3.2, FANTASY=7.2, ECCHI=3.2, ADVENTURE=4.2}

        // --------------------------------------------------------------------
        // AVG: Agrupando pela categoria e somando todos os preços dela (Total value):

        Map<CategoryEnum, Double> avgPricesGruppingByCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.averagingDouble(LightNovelModel::getPrice)));

        System.out.println(avgPricesGruppingByCategory);
        // {DRAMA=3.2, FANTASY=3.6, ECCHI=3.2, ADVENTURE=4.2}

        // --------------------------------------------------------------------
        // MIN: Agrupando pela categoria e retornando o menor preço por categoria:

        Map<CategoryEnum, Optional<LightNovelModel>> minPriceGruppingByCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.minBy(Comparator.comparing(LightNovelModel::getPrice))));

        System.out.println(minPriceGruppingByCategory);
        // {DRAMA=Optional[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}],
        // ANTASY=Optional[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}],
        // ECCHI=Optional[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}],
        // ADVENTURE=Optional[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}]}

        // Mesmo agrupamento porém ao invés de return Optional<LightNovelModel> ja returns o
        // Objeto sem encapsular: (Coleta e faz algo, neste caso executa o get do Optional e
        // retorna o Objeto interno: Igual ao anterior:
        Map<CategoryEnum, LightNovelModel> minPriceGruppingByCategoryNotOptional = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(LightNovelModel::getPrice)),
                                Optional::get)));

        System.out.println(minPriceGruppingByCategoryNotOptional);
        // {DRAMA=LightNovelModel{title='Danielle', price=3.2, category=DRAMA},
        // FANTASY=LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY},
        // ECCHI=LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI},
        // ADVENTURE=LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}}
    }
}
