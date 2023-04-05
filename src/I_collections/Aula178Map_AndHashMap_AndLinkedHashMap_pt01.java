package collections;

// Não é extends de Collections ou seja não é bem uma coleção técnicamente falando,
// tomar cuidado ao utilizar métodos Polimorficos que esperam implementações de Collection!
// Mas ainda sim é utilizado para estruturar dados com algumas especializações:

// Trabalha com o conceito de key/value ou seja, utilizamos a chave para manipular o valor.
// Map é uma interface, logo precisamos de classes que implementem ela,
// HashMap é uma especialização que se trabalha utilizando hashCode igual ao HashSet,
// porém com maior performance em alguns aspectos:
// É necessário equals() e hashcode() devidamente implementados no Object que será armazenado.
// - NÃO podemos duplicar chaves, ou seja as chaves é o ID do valor portanto elas devem ser únicas.
// Obs: Também organiza a lista de acordo com hashCode ou seja não garante ordenação!
// - Ao tentar adicionar elementos com a mesma chave (duplicados), ele simplesmente sobre-escreve o anterior
// colocando o valor novo acossiado a chave duplicada em questão, DIFERENTE de HashSet que nem deixa inserir
// elementos duplicados.

/* Vantagens do HashMap em relaçao ao HashSet:
HashMap e HashSet são estruturas de dados diferentes em Java e, portanto, são usadas para finalidades diferentes.
O HashMap é usado para armazenar pares chave-valor, enquanto o HashSet é usado para armazenar um conjunto de
elementos exclusivos. No entanto, existem algumas vantagens específicas do HashMap em relação ao HashSet:

    - Acesso direto aos valores associados à chave: No HashMap, os valores são associados a chaves,
    o que significa que você pode acessar o valor diretamente usando a chave, sem precisar iterar
    sobre o mapa. Isso torna a busca de valores mais rápida do que no HashSet.

    - Flexibilidade na definição de tipos de chave e valor: No HashMap, a chave e o valor podem ser
    de qualquer tipo, desde que sejam objetos. Isso significa que você pode usar tipos de dados
    personalizados como chaves e valores. Já no HashSet, apenas um tipo de elemento pode ser armazenado.

    - Possibilidade de atualizar valores: No HashMap, é possível atualizar o valor associado a uma
    chave existente, o que não é possível no HashSet. Isso é útil em situações em que você precisa
    alterar um valor armazenado em uma coleção.

    - Melhor desempenho em operações de busca: O HashMap tem uma implementação mais eficiente de
    busca do que o HashSet, especialmente para grandes coleções de dados. Isso ocorre porque a
    busca no HashMap é baseada em chaves, que são únicas, enquanto a busca no HashSet é baseada em
    elementos, que podem ser duplicados.

Em resumo, o HashMap é uma estrutura de dados mais flexível e eficiente para armazenar pares chave-valor, enquanto o HashSet é mais adequado para armazenar um conjunto de elementos exclusivos.

*/
// Em resumo, o HashMap é mais adequado para armazenar pares chave-valor e permite um acesso direto aos
// valores associados às chaves. O Set, por outro lado, é mais adequado para armazenar um conjunto de
// elementos exclusivos e não permite valores duplicados. No entanto, o HashMap pode ser mais flexível
// em termos de tipos de chave e valor e permite a atualização de valores.

// LinkedHashMap: Mesma lógica do LinkedHashSet aonde passa a organizar a lista sem ser por meio do hashCode
// como em HashMap comuns, mas ainda utilziando a abordagem de indexar com hasCode.
// Como estamos trabalhando orientado a Interface basta mudar a instância do new para LinkedHashMap();
// Ele organiza não com base em equals() nem compareTo() ele apenas mantém e garante a ordem de inserção.

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Aula178Map_AndHashMap_AndLinkedHashMap_pt01 {
    public static void main(String[] args) {
        Map<String, String> stringHashMap = new HashMap<>(); // LinkedHashMap mantém a lista organizada pelo equals
        stringHashMap.put("Chave1", "ValorDaChave1");
        stringHashMap.put("Chave2", "ValorDaChave2");
        stringHashMap.put("Chave3", "ValorDaChave3");
        stringHashMap.put("Chave4", "ValorDaChave4");

        System.out.println(stringHashMap);

        // Ou seja podemos utilizar como dicionário:
        Map<String, String> dicionarioPortuguesToIngles = new HashMap<>();
        dicionarioPortuguesToIngles.put("Céu","Sky");
        dicionarioPortuguesToIngles.put("Origem","Source");
        dicionarioPortuguesToIngles.put("Destino","Target");
        dicionarioPortuguesToIngles.put("Valor","Value");
        dicionarioPortuguesToIngles.put("Contexto","Context");
        dicionarioPortuguesToIngles.put("Rua","Street");
        dicionarioPortuguesToIngles.put("Quarto","Room");
        dicionarioPortuguesToIngles.put("Quarto","Room Sobrescrevido"); // chaves iguais ele sobrescreve
        // Resolvendo isso no Java 8:
        // Verifica antes de adicionar, e só adiciona se a chave não estiver relacionada a nenhum objeto da lista
        dicionarioPortuguesToIngles.putIfAbsent("Quarto", "Room verificando antes de sobrescrever");

        System.out.println("Guerra no "+ dicionarioPortuguesToIngles.get("Céu")); // Guerra no Sky (Skywars)


        // Iterando sobre um HashMap:
        // Existem duas formas de iterar um Map, com a chave ou com o valor:
        // Iterando utilizando a chave:
        for (String chave: dicionarioPortuguesToIngles.keySet()) {
            // keySet() retornando um Set ou seja, garante elementos únicos (DISTINCT)
            System.out.println(chave);
            // Guerra no Sky
            // Destino
            // Quarto
            // Contexto
            // Valor
            // Céu
            // Origem
            // Rua
        }
        // Iterando utilizando o valor:
        System.out.println("-------------------------");
        for(String valor: dicionarioPortuguesToIngles.values()) {
            // values() retorna uma Collection ou seja, interface mais genérica podendo conter elementos duplicados (NOT DISTINCT)
            System.out.println(valor);
            // Target
            // Room Sobrescrevido
            // Context
            //Value
            // Sky
            // Source
            // Street
        }

        // Iterando pela chave e pegando o valor com ela:
        // Obs: Não é muito utilizado a abordagem de pegar uma chave apartir de um valor
        // apenas o inverso, porém deve existir alguma gambiarra que o faz (não sendo recomendável).
        System.out.println("-------------------------");
        for (String chave: dicionarioPortuguesToIngles.keySet()) {
            System.out.println("Chave: "+ chave + " | Valor: "+ dicionarioPortuguesToIngles.get(chave));
            // Chave: Destino | Valor: Target
            // Chave: Quarto | Valor: Room Sobrescrevido
            // Chave: Contexto | Valor: Context
            // Chave: Valor | Valor: Value
            // Chave: Céu | Valor: Sky
            // Chave: Origem | Valor: Source
            // Chave: Rua | Valor: Street
        }

        // Gambiarra para retornar uma chave apartir de um valor:
        // Obs: Uma Map.Entry<K, V> é o correspondente de um nó para Listas ligadas
        // ou seja é a representação de cada Objeto mapeado, contendo nele
        // métodos gerais que utilizam os atributos chave/valor de cada Objeto mapeado.
        // Utilizado para quando se quer trabalhar com ambos os valores (Chave/Valor)

        String value = "Target"; // valor que estamos procurando para retornar a chave associada.
        for (Map.Entry<String, String> entry : dicionarioPortuguesToIngles.entrySet()) {
            if (value.equals(entry.getValue())) {
                System.out.println(entry.getKey()); // Destino
            }
        }

        System.out.println("--------------------------");
        Map<String, String> stringLinkedHashMap = new LinkedHashMap<>();
        // LinkedHashMap mantém a lista organizada pela ordem de inserção (deferente da HashMap)
        stringLinkedHashMap.put("Chave4", "ValorDaChave4");
        stringLinkedHashMap.put("Chave2", "ValorDaChave2");
        stringLinkedHashMap.put("Chave3", "ValorDaChave3");
        stringLinkedHashMap.put("Chave1", "ValorDaChave1");

        System.out.println(stringLinkedHashMap);
    }
}
