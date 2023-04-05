package streams;

import streams.domain.CategoryEnum;
import streams.domain.LightNovelModel;
import streams.domain.PromotionEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Aula217Streams_Collectors_groupingBy_agrupamentoMaisComplexo_regrasDefinidasPorLambdas_retornandoMapasComListasDeValue {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0, CategoryEnum.FANTASY),
                    new LightNovelModel("KissXKiss", 3.2, CategoryEnum.ECCHI),
                    new LightNovelModel("Dragon Ball", 5.2, CategoryEnum.FANTASY),
                    new LightNovelModel("Danielle", 3.2, CategoryEnum.DRAMA),
                    new LightNovelModel("Jujutsu", 6.2, CategoryEnum.FANTASY),
                    new LightNovelModel("Welzika", 4.2, CategoryEnum.ADVENTURE)));

    public static void main(String[] args) {
        // ------------- [Retornando mapas dentro de mapas: ] -----------------

        // Sem retornar lista, trabalhando com dois groupingBy ou seja, 2 mapas aninhados
        Map<CategoryEnum, Map<PromotionEnum, List<LightNovelModel>>> collect = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.groupingBy(novel -> novel.getPrice() < 3 ?
                                PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE)));

        System.out.println(collect);
        System.out.println();
        // {DRAMA={NORMAL_PRICE=[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}]},
        // FANTASY={NORMAL_PRICE=[LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}, LightNovelModel{title='Jujutsu', price=6.2, category=FANTASY}], UNDER_PROMOTION=[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}]},
        // ADVENTURE={NORMAL_PRICE=[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}]},
        // ECCHI={NORMAL_PRICE=[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}]}}

        // ------------- [ Retornando Listas dentro de mapas: ] ---------------

        // Retornando Mapas com listas aninhadas ao invés de mapas, ou seja
        // utilizando mapping ao invés de groupingBy aninhado em outro:
        Map<CategoryEnum, List<PromotionEnum>> collect1 = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.mapping(novel -> novel.getPrice() < 3 ?
                                        PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE,
                                Collectors.toList())));

        System.out.println(collect1);
        System.out.println();
        // {DRAMA=[NORMAL_PRICE],
        // FANTASY=[UNDER_PROMOTION, NORMAL_PRICE, NORMAL_PRICE],
        // ADVENTURE=[NORMAL_PRICE], ECCHI=[NORMAL_PRICE]}

        // ------- [ Retornando qualquer collection dentro de mapas: ] --------

        // Obs: Fabricando qualquer tipo de coleção ao invés de List apenas altere
        // o parametro Collectors.toList() para Collectors.toCollection(Collection::new)
        // - Neste exemplo estamos retornando um Set que EVITA DUPLICATAS: DISTINCT
        // E NÃO garante posição de inserção:

        Map<CategoryEnum, Set<PromotionEnum>> collect2 = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.mapping(novel -> novel.getPrice() < 3 ?
                                        PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE,
                                Collectors.toSet())));

        System.out.println(collect2);
        // {DRAMA=[NORMAL_PRICE],
        // FANTASY=[NORMAL_PRICE, UNDER_PROMOTION],
        // ADVENTURE=[NORMAL_PRICE],
        // ECCHI=[NORMAL_PRICE]}

        // Retornando uma LinkedHashSet aninhada ao mapa: DISTINCT e garante posição de inserção
        Map<CategoryEnum, LinkedHashSet<PromotionEnum>> collect3 = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.mapping(novel -> novel.getPrice() < 3 ?
                                        PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE,
                                Collectors.toCollection(LinkedHashSet::new))));

        System.out.println(collect3);
        // {DRAMA=[NORMAL_PRICE],
        // FANTASY=[UNDER_PROMOTION, NORMAL_PRICE],
        // ADVENTURE=[NORMAL_PRICE],
        // ECCHI=[NORMAL_PRICE]}
    }
}
