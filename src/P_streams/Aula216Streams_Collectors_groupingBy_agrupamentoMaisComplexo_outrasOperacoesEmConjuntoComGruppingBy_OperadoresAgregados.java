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
    }
}
