package P_streams;

import P_streams.domain.CategoryEnum;
import P_streams.domain.LightNovelModel;
import P_streams.domain.PromotionEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aula215Streams_Collectors_groupingBy_agrupamentoMaisComplexo_elementos_em_mapas_aninhados {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0, CategoryEnum.FANTASY),
                    new LightNovelModel("KissXKiss", 3.2, CategoryEnum.ECCHI),
                    new LightNovelModel("Dragon Ball", 5.2, CategoryEnum.FANTASY),
                    new LightNovelModel("Danielle", 3.2, CategoryEnum.DRAMA),
                    new LightNovelModel("Welzika", 4.2, CategoryEnum.ADVENTURE)));
    public static void main(String[] args) {
        //----------- [Mapa/groupingBy sem aninhamentos complexos] ------------
        // Agrupando todos novels cujo price < 3.0
        // se for true então PromotionEnum.UNDER_PROMOTION
        // se for false então PromotionEnum.NORMAL_PRICE
        // result: Map<PromotionEnum, List<LightNovelModel>>
        Map<PromotionEnum, List<LightNovelModel>> promotionOrNot = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(novel ->
                        novel.getPrice() < 3 ?
                                PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE));

        System.out.println(promotionOrNot);
        // {UNDER_PROMOTION=[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}],
        // NORMAL_PRICE=[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}, LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}, LightNovelModel{title='Danielle', price=3.2, category=DRAMA}, LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}]}

        //---------- [Mapa/groupingBy com aninhamentos complexos] -------------
        // Agrupando todos novels com a mesma lógica anterior porém
        // com a chave do mapa sendo a categoria:
        // result: Map<CategoryEnum, Map<PromotionEnum, List<LightNovelModel>>>

        Map<CategoryEnum, Map<PromotionEnum, List<LightNovelModel>>> promotionOrNotWithKeyCategory = listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory,
                        Collectors.groupingBy(novel ->
                                novel.getPrice() < 3 ?
                                        PromotionEnum.UNDER_PROMOTION : PromotionEnum.NORMAL_PRICE)));

        System.out.println(promotionOrNotWithKeyCategory);
        // {FANTASY={UNDER_PROMOTION=[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}], NORMAL_PRICE=[LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}]},
        // DRAMA={NORMAL_PRICE=[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}]},
        // ECCHI={NORMAL_PRICE=[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}]},
        // ADVENTURE={NORMAL_PRICE=[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}]}}

    }
}
