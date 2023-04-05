package P_streams;

import P_streams.domain.CategoryEnum;
import P_streams.domain.LightNovelModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aula214Streams_Collectors_groupingBy_agrupando_elementos_em_mapas {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0, CategoryEnum.FANTASY),
                    new LightNovelModel("KissXKiss", 3.2, CategoryEnum.ECCHI),
                    new LightNovelModel("Dragon Ball", 5.2, CategoryEnum.FANTASY),
                    new LightNovelModel("Danielle", 3.2, CategoryEnum.DRAMA),
                    new LightNovelModel("Welzika", 4.2, CategoryEnum.ADVENTURE)));
    public static void main(String[] args) {

        // -------------------- [SEM STREAMS + trabalhoso] --------------------

        Map<CategoryEnum, List<LightNovelModel>> categoryLightNovelMap = new HashMap<>();
        List<LightNovelModel> fantasy = new ArrayList<>();
        List<LightNovelModel> ecchi = new ArrayList<>();
        List<LightNovelModel> drama = new ArrayList<>();
        List<LightNovelModel> adventure = new ArrayList<>();

        for (LightNovelModel novel : listOfLightNovels) {
            switch (novel.getCategory()) {
                case FANTASY: fantasy.add(novel); break;
                case ECCHI: ecchi.add(novel); break;
                case DRAMA: drama.add(novel); break;
                case ADVENTURE: adventure.add(novel); break;
            }
        }

        categoryLightNovelMap.put(CategoryEnum.FANTASY, fantasy);
        categoryLightNovelMap.put(CategoryEnum.ECCHI, ecchi);
        categoryLightNovelMap.put(CategoryEnum.DRAMA, drama);
        categoryLightNovelMap.put(CategoryEnum.ADVENTURE, adventure);

        System.out.println(categoryLightNovelMap);
        // {ADVENTURE=[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}], FANTASY=[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}, LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}], ECCHI=[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}], DRAMA=[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}]}

        // -------------------- [COM STREAMS + FACIL] --------------------
        Map<CategoryEnum, List<LightNovelModel>> categoryLightNovelMapWithCollectors =
                listOfLightNovels.stream()
                .collect(Collectors.groupingBy(LightNovelModel::getCategory));

        System.out.println(categoryLightNovelMapWithCollectors);
        // {ADVENTURE=[LightNovelModel{title='Welzika', price=4.2, category=ADVENTURE}], FANTASY=[LightNovelModel{title='Tokyo Ghoul', price=2.0, category=FANTASY}, LightNovelModel{title='Dragon Ball', price=5.2, category=FANTASY}], ECCHI=[LightNovelModel{title='KissXKiss', price=3.2, category=ECCHI}], DRAMA=[LightNovelModel{title='Danielle', price=3.2, category=DRAMA}]}
    }
}
