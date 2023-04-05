package I_collections;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Aula179Map_AndHashMap_AndLinkedHashMap_pt02_relacionamento1para1 {
    public static void main(String[] args) {
        // Cria um consumidor e seta o ID randomicamente:
        Consumidor consumidor1 = new Consumidor("Wellison");
        System.out.println(consumidor1); // Consumidor{id=45805, nome='Wellison'}

        Consumidor consumidor2 = new Consumidor("Clemont");
        Consumidor consumidor3 = new Consumidor("Wesley");
        Consumidor consumidor4 = new Consumidor("Danielle");

        Manga mangaDBZ = new Manga("Dragon Ball Z", 300);
        Manga mangaTokyoGhoul = new Manga("Tokyo Ghoul", 200);
        Manga mangaJujutsu = new Manga("Jujutsu no kaisen", 100);
        Manga mangaOnePiece = new Manga("One Piece", 3000);


        // Os relacionamentos 1 para 1 utilizando maps para armazenar:

        Map<Consumidor, Manga> map1Para1 = new HashMap<>();
        map1Para1.put(consumidor1, mangaDBZ);
        map1Para1.put(consumidor2, mangaTokyoGhoul);
        map1Para1.put(consumidor3, mangaJujutsu);
        System.out.println(map1Para1);

        for (Map.Entry<Consumidor, Manga> entry : map1Para1.entrySet()) {
            System.out.println("Chave: "+ entry.getKey() +
                    " | Valor: "+ entry.getValue());
        }

        // Os relacionamentos 1 para N utilizando maps para armazenar:

        List<Manga> mangasDoConsumidor1 = new ArrayList<>();
//        mangasDoConsumidor1.addAll(mangaDBZ, mangaOnePiece, mangaTokyoGhoul);
        mangasDoConsumidor1.add(mangaDBZ);
        mangasDoConsumidor1.add(mangaOnePiece);
        mangasDoConsumidor1.add(mangaTokyoGhoul);

        List<Manga> mangasDoConsumidor2 = new ArrayList<>();
        mangasDoConsumidor2.add(mangaDBZ);
        mangasDoConsumidor2.add(mangaJujutsu);

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

class Consumidor {
    private Long id;
    private String nome;

    public Consumidor(String nome) {
        this.nome = nome;
        // this.id = ThreadLocalRandom.current().nextLong(); // quase garante valores únicos.
        // limitando o range de valores possíveis:
        this.id = ThreadLocalRandom.current().nextLong(0, 100_000);

    }

    public Consumidor(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Consumidor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    // equals e hashCode levam em consideração apenas o ID ou seja,
    // dois Objetos são iguais se apenas o ID for igual.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumidor that = (Consumidor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class Manga {
    private String nome;
    private int numEps;

    public Manga(String nome, int numEps) {
        this.nome = nome;
        this.numEps = numEps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return numEps == manga.numEps && Objects.equals(nome, manga.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numEps);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "nome='" + nome + '\'' +
                ", numEps=" + numEps +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumEps() {
        return numEps;
    }

    public void setNumEps(int numEps) {
        this.numEps = numEps;
    }
}
