package I_collections;

// Relacionamentos 1 para N utilizando maps par armazenar os pares Chave/Valor

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula180Map_AndHashMap_AndLinkedHashMap_pt03_relacionamento1paraN {

    public static void main(String[] args) {

        // Os relacionamentos 1 para N utilizando maps para armazenar:

        Consumidor consumidor1 = new Consumidor("Wellison");
        Consumidor consumidor2 = new Consumidor("Clemont");
        Consumidor consumidor3 = new Consumidor("Wesley");
        Consumidor consumidor4 = new Consumidor("Danielle");

        Manga mangaDBZ = new Manga("Dragon Ball Z", 300);
        Manga mangaTokyoGhoul = new Manga("Tokyo Ghoul", 200);
        Manga mangaJujutsu = new Manga("Jujutsu no kaisen", 100);
        Manga mangaOnePiece = new Manga("One Piece", 3000);

        List<Manga> mangasDoConsumidor1 = List.of(mangaDBZ, mangaOnePiece, mangaTokyoGhoul);

        List<Manga> mangasDoConsumidor2 = List.of(mangaDBZ, mangaJujutsu);

        Map<Consumidor, List<Manga>> map1ParaN = new HashMap<>();
        map1ParaN.putIfAbsent(consumidor1, mangasDoConsumidor1);
        map1ParaN.putIfAbsent(consumidor2, mangasDoConsumidor2);

        System.out.println("Consumidor 1 "+consumidor1.getNome() +
                " Possui os mangas: "+ mangasDoConsumidor1);

        System.out.println("Consumidor 2 "+consumidor2.getNome() +
                " Possui os mangas: "+ mangasDoConsumidor2);


        System.out.println("---------------------------------------");
        for (Map.Entry<Consumidor, List<Manga>> entry : map1ParaN.entrySet()) {
            System.out.println("------------------------------");
            System.out.println("Chave: "+ entry.getKey() +
                    " | Valor: "+ entry.getValue());

            System.out.println("----------------------------");
            System.out.println("Consumidor " + entry.getKey().getNome());
            for (Manga manga: entry.getValue()) {
                System.out.println("Comprou o manga "+ manga);
            }
        }
    }
}
