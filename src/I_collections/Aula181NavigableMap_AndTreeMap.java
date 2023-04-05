package collections;

// Classe com o mesmo contexto da NavigableSet e TreeSet porém com Maps
// Mesma regra sobre a utilização de NavigableSet aqui também é necessário
// o Objeto deve implementar a interface Comparable<T> ou utilizamos um Comparator<T>
// No caso a chave que deve implementar Comparable<T>.

// Ou seja possui os mesmos métodos de NavigableSet porém para Maps:
// - descendingMap(): Percorre a lista de trás para frente DESC
// - lowerKey() e lowerEntry(): < que currentObject
// - floorKey() e floorEntry(): <= o currentObject (igual ou imediatamente MENOR ao current object)
// - higherKey() e higherEntry(): > que currentObject
// - ceilingKey() e ceilingEntry(): >= o currentObject (igual ou imediatamente MAIOR ao current object)

// - pollFirstEntry(): faz um pop na lista, ou seja retira o primeiro elemento e retorna ele (removendo)
// - pollLastEntry(): mesmo contexto do anterior, remove e retorna o último elemento da coleção.

// - headMap(toKey): retorna todos elementos ABAIXO do toKey, {A, B, C, D} headMap("C") retornaria {A, B}

import java.util.*;

public class Aula181NavigableMap_AndTreeMap {
    public static void main(String[] args) {

        Comparator<Consumidor> consumidorComparator = Comparator.comparing(
                consumidor -> consumidor.getNome() // aqui poderia ser get().get().get()..... ALL
        );

        NavigableMap<Consumidor, List<Manga>> navigableMap = new TreeMap<>(consumidorComparator);
        navigableMap.put(new Consumidor("Irineia"),
                List.of(new Manga("DBZ", 300),
                        new Manga("Tokyo Ghoul", 100)));

        navigableMap.put(new Consumidor("Wellison"),
                List.of(new Manga("Jujutsu no kaisen", 500),
                        new Manga("One Piece", 3000)));

        System.out.println(navigableMap);


        // Para entender melhor ele organiza o HashMap de acordo com o compareTo():
        NavigableMap<String, String> navigableMapString = new TreeMap<>();
        navigableMapString.put("A", "Antenor");
        navigableMapString.put("E", "Elefantino");
        navigableMapString.put("B", "Bueno");
        navigableMapString.put("F", "Flausino");

        System.out.println(navigableMapString);

        // DESC percorre de trás para frente
        for (Map.Entry<String, String> entry: navigableMapString.descendingMap().entrySet()) {
            System.out.println(entry);
        }

        // lowerEntry() retornando a entrada (Nó) com key imediatamente MENOR QUE o CurrentKey:
        // ou seja retorna a chave e o valor utilizando a chave:
        System.out.println("--------------------------------------");
        for (Map.Entry<String, String> entry: navigableMapString.entrySet()) {
            System.out.println("Objeto com chave imediatamente menor que "+entry.getKey()+" com lowerEntry: " +
                            navigableMapString.lowerEntry(entry.getKey()));
            // Ou seja retorna o Nó completo Key/Value, sáida do for:
            // --------------------------------------
            // Objeto com chave imediatamente menor que A com lowerEntry: null
            // Objeto com chave imediatamente menor que B com lowerEntry: A=Antenor
            // Objeto com chave imediatamente menor que E com lowerEntry: B=Bueno
            // Objeto com chave imediatamente menor que F com lowerEntry: E=Elefantino
        }
        // Só retorna o VALUE utilizando a chave:
        System.out.println(navigableMapString.lowerKey("F")); // E


        // floorEntry() retornando a entrada (Nó) com key IGUAL ou imediatamente MENOR QUE o CurrentKey:
        // ou seja retorna a chave e o valor utilizando a chave:
        System.out.println("--------------------------------------");
        for (Map.Entry<String, String> entry: navigableMapString.entrySet()) {
            System.out.println("Objeto com chave IGUAL ou imediatamente MENOR QUE "+entry.getKey()+" com floorEntry: " +
                    navigableMapString.floorEntry(entry.getKey()));
            // Ou seja retorna a entrada (Nó) completo Key/Value saída do for:
            // Objeto com chave IGUAL ou imediatamente MENOR QUE A com floorEntry: A=Antenor
            // Objeto com chave IGUAL ou imediatamente MENOR QUE B com floorEntry: B=Bueno
            // Objeto com chave IGUAL ou imediatamente MENOR QUE E com floorEntry: E=Elefantino
            // Objeto com chave IGUAL ou imediatamente MENOR QUE F com floorEntry: F=Flausino
        }
        // Só retorna o VALUE utilizando a chave:
        System.out.println(navigableMapString.floorKey("F")); // F


        // higherEntry() retornando a entrada (Nó) com key imediatamente MAIOR QUE o CurrentKey:
        System.out.println("--------------------------------------");
        for (Map.Entry<String, String> entry: navigableMapString.entrySet()) {
            System.out.println("Objeto com chave imediatamente MAIOR QUE "+entry.getKey()+" com higherEntry: " +
                    navigableMapString.higherEntry(entry.getKey()));
            // Ou seja retorna a entrada (Nó) completo Key/Value saída do for:
            // Objeto com chave imediatamente MAIOR QUE A com higherEntry: B=Bueno
            // Objeto com chave imediatamente MAIOR QUE B com higherEntry: E=Elefantino
            // Objeto com chave imediatamente MAIOR QUE E com higherEntry: F=Flausino
            // Objeto com chave imediatamente MAIOR QUE F com higherEntry: null
        }
        System.out.println(navigableMapString.higherKey("F")); // null


        // ceilingEntry() retornando a entrada (Nó) com key IGUAL ou imediatamente MAIOR QUE o CurrentKey:
        System.out.println("--------------------------------------");
        for (Map.Entry<String, String> entry: navigableMapString.entrySet()) {
            System.out.println("Objeto com chave IGUAL ou imediatamente MAIOR QUE "+entry.getKey()+" com ceilingEntry: " +
                    navigableMapString.ceilingEntry(entry.getKey()));
            // Ou seja retorna a entrada (Nó) completo Key/Value saída do for:
            // Objeto com chave IGUAL ou imediatamente MAIOR QUE A com ceilingEntry: A=Antenor
            // Objeto com chave IGUAL ou imediatamente MAIOR QUE B com ceilingEntry: B=Bueno
            // Objeto com chave IGUAL ou imediatamente MAIOR QUE E com ceilingEntry: E=Elefantino
            // Objeto com chave IGUAL ou imediatamente MAIOR QUE F com ceilingEntry: F=Flausino
        }
        System.out.println(navigableMapString.ceilingKey("F")); // F


        // Retornando todos as entradas da chave cujo valor esteja ABAIXO de uma chave:
        // navigableMapString={A=Antenor, B=Bueno, E=Elefantino, F=Flausino}
        // valores ABAIXO e APENAS ABAIXO de C temos A e B:
        System.out.println(navigableMapString);
        System.out.println(navigableMapString.headMap("B")); // {A=Antenor, B=Bueno}

        // Retornando todos as entradas da chave cujo valor esteja ACIMA de uma chave:
        // valores ACIMA ou IGUAL de C temos E e F:
        // Obs: Diferente do headMap() que não retorna IGUALDADES apenas os valores ABAIXO de
        // o tailMap() CONSIDERA também IGUALDADES.
        System.out.println(navigableMapString.tailMap("C")); // {E=Elefantino, F=Flausino}
        System.out.println(navigableMapString.tailMap("E")); // {E=Elefantino, F=Flausino}

        // OBS sobre tailMap() e headMap(): AMBOS os métodos retornam a mesma coleção
        // ou seja, se modificarmos o retorno de um deles vamos estar modificando
        // a coleção ORIGINAL em memória (Estilo o método .asList()) dos Arrays
        // no qual mantém uma conexão entre o Array de origem e a List de destino.


        // REMOVENDO e retornando entradas (Nó):
        System.out.println(navigableMapString.pollFirstEntry()); // pop no primeiro elemento
        System.out.println(navigableMapString.pollLastEntry()); // pop no ultimo elemento

    }
}
