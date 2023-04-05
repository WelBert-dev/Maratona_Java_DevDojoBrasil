package streams;

// Ao trabalhar com fluxos de tipos primitivos é interessante utilizar Streams
// especializados para eles, evitando assim overhead, AutoBoxing e UnBoxing
// em looping assim ganhamos mais performance!

// Todos os tipos primitivos possuem um Stream personalizado, sendo eles:
// IntStream
// DoubleStream
// LongStream

// Obs: Também é possível converter um Stream personalizado em uma Stream genérica
// fazendo o boxing da StreamPersonalizada para a stream genérica com o método:
// boxed(): Retorna uma Stream<Integer> contendo os elementos da stream original
// como Integer.
// Util quando alguma operação subjacente espera um objeto Wrapper ao invés de primitivo.

// Para trabalhar com eles em um fluxo basta utilizar mapToInt e ao invés de retornar
// Stream<Integer> o retorno será um IntStream.

/* Definições sobre as interfaces especializadas para primitivos numéricos:

Uma das principais utilidades das classes especializadas é que elas evitam a
necessidade de criar objetos wrapper para tipos primitivos, como Integer ou
Double, quando se trabalha com coleções de dados.
Isso é importante porque a criação de objetos pode ser um processo custoso em
termos de desempenho, especialmente em coleções grandes.

Além disso, as classes especializadas fornecem uma série de métodos especializados
para operações matemáticas e estatísticas, como média, soma, máximo, mínimo, entre
outras. Esses métodos são projetados para trabalhar com dados numéricos de forma
eficiente e são mais expressivos do que a utilização de métodos genéricos em uma
coleção de objetos.

As classes especializadas também fornecem métodos para converter coleções de dados
numéricos em outros tipos de dados, como arrays ou outras coleções.
Isso pode ser útil em muitos cenários, como quando se trabalha com bibliotecas ou
APIs que esperam determinados tipos de dados.

Em resumo, as classes especializadas para tipos primitivos na API Streams oferecem
uma maneira mais eficiente e expressiva de trabalhar com coleções de dados numéricos,
fornecendo métodos especializados para manipulação de dados e operações matemáticas.
Isso pode levar a um código mais limpo e eficiente em termos de desempenho,
especialmente em cenários onde é necessário lidar com grandes conjuntos de dados
numéricos.

*/

/* Definições sobre OverHead:

Overhead é um termo utilizado para descrever a quantidade de recursos (como tempo
de processamento, memória ou largura de banda) que são consumidos por um determinado
processo, atividade ou operação, além do necessário para realizar sua tarefa principal.

Em outras palavras, overhead é a sobrecarga adicional que é colocada sobre um sistema
ou processo para realizar tarefas que não são diretamente relacionadas com a sua
finalidade principal.
Por exemplo, a sobrecarga de processamento que é colocada sobre um sistema quando
ele executa um antivírus em segundo plano, enquanto o usuário executa outras tarefas.

O overhead pode ser causado por uma variedade de fatores, como a necessidade de
realizar verificações de segurança adicionais, a alocação de memória para armazenar
informações adicionais ou a execução de operações adicionais para manter a integridade
de um sistema. Ele pode afetar a eficiência e o desempenho de um sistema, bem como o
consumo de recursos, como espaço em disco ou tempo de processamento.

*/

import streams.domain.LightNovelModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Aula210Streams_trabalhando_com_tipos_primitivos_numericos_evita_autoBoxing_unBoxing {
    private final static List<LightNovelModel> listOfLightNovels = new ArrayList<>(
            List.of(
                    new LightNovelModel("Tokyo Ghoul", 2.0),
                    new LightNovelModel("KissXKiss", 3.2),
                    new LightNovelModel("Dragon Ball", 5.2),
                    new LightNovelModel("Danielle", 3.2),
                    new LightNovelModel("Welzika", 4.2)));
    public static void main(String[] args) {
        // Somar todos os LightNovels que possuem preço maior que 3:

        // SEM Stream personalizada:
        listOfLightNovels.stream()
                .filter(novel -> novel.getPrice() > 3)
                .map(LightNovelModel::getPrice)
                .reduce(Double::sum)
                .ifPresent(System.out::println); // 15.8


        // --------------------------------------------------------------------

        // COM Stream personalizada para tipos primitivos:
        // Obs: quando queremos apenas os preços, e vamos discartar o obj!
        // Pois se trabalhar com o obj por completo vai rolar autoboxing&unboxing!
        double sum = listOfLightNovels.stream()
                .mapToDouble(LightNovelModel::getPrice) // aqui descarta o obj e trabalha apenas com preço
                .filter(price -> price > 3)
                .sum();
        System.out.println(sum); // 15.8

        // ---------- [Convertendo DoubleStream para Stream<Double>] ----------
        // Util quando algum método subjacente espera um Wrapper ao invés de primitivo.
        // Exemplo: Adicionar os valores numéricos em uma coleção de Double:

        List<Double> listOfPricesGreatThan3 = listOfLightNovels.stream()
                .mapToDouble(LightNovelModel::getPrice)
                .filter(price -> price > 3)
                .boxed()
                .collect(Collectors.toList());

        listOfPricesGreatThan3.forEach(System.out::println);
    }
}
