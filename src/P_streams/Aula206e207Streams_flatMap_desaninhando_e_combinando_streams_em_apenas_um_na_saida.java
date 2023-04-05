package streams;

/*

---> Definições e contextualização sobre flatMap()

O método flatMap() da classe Stream é um método intermediário que é usado para
transformar uma Stream de objetos em outra Stream de objetos, com base em uma
função fornecida como argumento.
O objetivo do método flatMap() é permitir a manipulação e transformação de objetos
em uma Stream em um único passo, sem a necessidade de operações adicionais.

Ele é particularmente útil quando se trabalha com dados que possuem uma estrutura
hierárquica, como listas aninhadas ou mapeamentos de valores.

A função passada como argumento do método flatMap() é aplicada a cada elemento da
Stream, que é então transformado em uma nova Stream de objetos.
As Streams resultantes são então combinadas em uma única Stream de saída.

Em resumo, o método flatMap() da classe Stream é uma ferramenta útil para transformar
objetos em uma Stream em outros objetos em uma nova Stream em um único passo.
Ele é particularmente útil ao trabalhar com dados hierárquicos e pode ajudar a
simplificar operações de transformação e filtragem.

---> O que ele faz em low level para isso ser possivel?

O método flatMap() da classe Stream é implementado através de iteração e manipulação
de ponteiros.

Ao chamar o método flatMap(), a Stream de entrada é iterada e para cada elemento, a
função fornecida como argumento é aplicada.
Essa função retorna uma nova Stream de objetos que é adicionada a uma nova Stream
resultante. A iteração continua até que todos os elementos da Stream de entrada
tenham sido processados.

Internamente, o método flatMap() é implementado por meio de uma estrutura de loop,
que itera sobre a Stream de entrada e chama a função de mapeamento para cada elemento.
Para cada elemento, a função de mapeamento retorna uma nova Stream de objetos.
Essas Streams são combinadas em uma única Stream de saída por meio da manipulação
de ponteiros.

Na prática, a implementação detalhada do método flatMap() da classe Stream é complexa
e depende de vários fatores, incluindo a implementação específica da JVM em que o
código está sendo executado, a configuração do sistema e o hardware subjacente.
No entanto, o resultado final é uma operação eficiente e flexível que pode ser usada
para transformar objetos em uma Stream em outros objetos em uma nova Stream em um
único passo.

---> Passo a Passo da implementação em LowLevel:

    1o - Iteração da Stream de entrada: O método flatMap() começa iterando a Stream
    de entrada, que contém os objetos que serão transformados.

    2o - Aplicação da função de mapeamento: Para cada elemento da Stream de entrada,
    a função de mapeamento é aplicada, produzindo uma nova Stream de objetos.
    Essa função é fornecida como argumento do método flatMap() e é responsável por
    transformar o objeto da Stream de entrada em uma nova Stream de objetos.

    3o - Concatenação das Streams resultantes: As Streams resultantes da aplicação
    da função de mapeamento são concatenadas em uma única Stream resultante.
    Isso é feito por meio da manipulação de ponteiros, que conectam as Streams
    resultantes.

    4o - Retorno da Stream resultante: A Stream resultante é retornada como saída
    do método flatMap().

Em resumo, o método flatMap() da classe Stream itera sobre a Stream de entrada,
aplica uma função de mapeamento a cada elemento e concatena as Streams resultantes
em uma única Stream de saída.
Esse processo permite transformar objetos em uma Stream em outros objetos em uma
nova Stream em um único passo, tornando o código mais conciso e eficiente.

---> As aplicações e utilidades do método flatMap() na classe Stream incluem:

    - Transformar uma Stream de objetos em outra Stream de objetos: O método
    flatMap() é usado para transformar objetos em uma Stream em outros objetos
    em uma nova Stream.
    Isso é particularmente útil quando se trabalha com listas aninhadas ou
    mapeamentos de valores.

    - Reduzir o número de passos em operações de transformação: O método flatMap()
    pode ser usado para realizar operações de transformação em uma única etapa,
    sem a necessidade de etapas intermediárias.

    - Simplificar operações de filtragem: O método flatMap() pode ser usado em
    conjunto com o método filter() para realizar operações de filtragem em uma
    única etapa.

    - Trabalhar com estruturas hierárquicas: O método flatMap() é especialmente
    útil ao trabalhar com dados que possuem uma estrutura hierárquica, como
    listas aninhadas ou mapeamentos de valores.

*/


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aula206e207Streams_flatMap_desaninhando_e_combinando_streams_em_apenas_um_na_saida {
    public static void main(String[] args) {
        List<List<String>> empresa = new ArrayList<>();
        empresa.add(new ArrayList<>(
                List.of("Wellison", "Pedro", "Gustavo", "Raveli")
        ));
        empresa.add(new ArrayList<>(
                List.of("Caio", "Leticia", "Tainara", "Micael")
        ));

        // --------------------------------------------------------------------
        // SEM flatMap:
        // Olha o aninhamento de streams que se forma:
        System.out.println("Sem achatar com flatMap: ");
        Stream<Stream<String>> streamStream = empresa.stream()
                .map(setor -> setor.stream());

        streamStream.forEach(setor -> setor
                .forEach(System.out::println));
        // Queremos transformar em Stream<String> apenas!

        // --------------------------------------------------------------------
        // COM flatMap (Reduzimos a quantidade de passos necessários para o msm result)
        // Solução: PORÉM ficar esperto pois vai ficar todos join no mesmo fluxo!!
        System.out.println("Achatando com flatMap: ");
        Stream<String> stringStream = empresa.stream()
                .flatMap(Collection::stream);

        stringStream.forEach(System.out::println);

        // --------------------------------------------------------------------
        // Problema: Transformar uma lista bidimencional em apenas uma apenas com os chars:
        // SEM Stream:

        empresa.forEach(setor -> setor
                .forEach(person ->
                        System.out.println(Arrays.toString(person.split("")))));
        // [W, e, l, l, i, s, o, n] person
        // [P, e, d, r, o]
        // [G, u, s, t, a, v, o]
        // [R, a, v, e, l, i]
        // [C, a, i, o]
        // [L, e, t, i, c, i, a]
        // [T, a, i, n, a, r, a]
        // [M, i, c, a, e, l]

        // --------------------------------------------------------------------
        for (List<String> setor : empresa) {
            // Transformando um array em uma stream:
            //Arrays.stream(String[])
            List<String> collect = setor.stream()
                    .map(person -> person.split("")) // Stream<String[]>
                    .flatMap(Arrays::stream) // Stream<String> (Join em tudo)
                    .collect(Collectors.toList()); // List<String>

            System.out.println(collect);
            // [W, e, l, l, i, s, o, n, P, e, d, r, o, G, u, s, t, a, v, o, R, a, v, e, l, i]
            // [C, a, i, o, L, e, t, i, c, i, a, T, a, i, n, a, r, a, M, i, c, a, e, l]
        }

        // --------------------------------------------------------------------
        // Com Listas auxiliares sem uso de stream:
        List<String> joinLists = new ArrayList<>();
        empresa.forEach(setor -> setor.forEach(person -> joinLists.add(person)));
        // [Wellison, Pedro, Gustavo, Raveli, Caio, Leticia, Tainara, Micael]

        List<String> lettersAllJoinList = new ArrayList<>();
        joinLists.forEach(person -> List.of(person.split(""))
                .forEach(letter -> lettersAllJoinList.add(letter)));
        // [W, e, l, l, i, s, o, n, P, e, d, r, o, G, u, s, t, a, v, o, R, a, v, e, l, i, C, a, i, o, L, e, t, i, c, i, a, T, a, i, n, a, r, a, M, i, c, a, e, l]
        System.out.println(lettersAllJoinList);

        // --------------------------------------------------------------------
        // COM Stream aonde não necessita de listas auxiliares:
        // Mapeando uma Lista BiDimencional sem loopings explicitos

        List<String> lettersAllJoinListWithStream = empresa.stream()
                .flatMap(Collection::stream)
                .map(person -> person.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(lettersAllJoinListWithStream);
        // [W, e, l, l, i, s, o, n, P, e, d, r, o, G, u, s, t, a, v, o, R, a, v, e, l, i, C, a, i, o, L, e, t, i, c, i, a, T, a, i, n, a, r, a, M, i, c, a, e, l]

    }
}
