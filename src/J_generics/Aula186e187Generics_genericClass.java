package generics;

/* Pontos importantes e atenções a se tomar quando se trabalha com Classes Genéricas <T>:

    - Restrições de tipo: você pode impor restrições sobre o tipo de parâmetro genérico que
    uma classe pode receber, utilizando a palavra-chave "extends".
    Por exemplo: public class MinhaClasse<T extends Number> { ... }
    Desta forma, a classe genérica aceitará somente tipos que são subtipos de Number.

    - O tipo de objeto retornado por um método genérico pode ser inferido pelo compilador,
    se as informações necessárias estiverem disponíveis. No entanto, em alguns casos, é
    preciso especificar o tipo manualmente, utilizando a sintaxe "nomeDoMetodo::<tipo>".

    - Uma classe genérica pode ter métodos que não são genéricos, assim como métodos que são
    genéricos apenas em alguns parâmetros. É possível utilizar os dois tipos de métodos em
    conjunto sem problemas.

    - Quando se trabalha com classes genéricas, é importante considerar as questões de
    compatibilidade com versões anteriores, uma vez que o tipo de parâmetro pode ser alterado
    na evolução da classe.

    - É importante ter cuidado ao utilizar tipos genéricos em conjunto com arrays, pois pode
    haver problemas de compatibilidade e de segurança.

    - Alguns cuidados extras devem ser tomados quando se trabalha com tipos genéricos em conjunto
    com herança e polimorfismo, uma vez que esses conceitos podem ter impacto sobre a escolha do
    tipo correto.

    - É importante lembrar que, em tempo de execução, as informações sobre o tipo genérico são
    apagadas, de modo que os tipos genéricos não são reificados em Java. Por isso, é preciso ter
    cuidado ao utilizar operações que dependem do tipo em tempo de execução.
        - Type Erasure, existe uma solução utilizando reflexão para obter essas infos "apagadas".

    - Erros de compilação: é comum que erros de compilação envolvendo classes genéricas sejam mais
    difíceis de identificar e corrigir do que erros de compilação envolvendo classes não genéricas.

    - Uso de tipos genéricos em bibliotecas e APIs: ao utilizar bibliotecas e APIs que fazem uso de
    tipos genéricos, é importante entender como esses tipos são utilizados e quais são as restrições
    e possibilidades de uso.

    - Alguns recursos do Java, como a serialização de objetos ou a reflexão, podem apresentar desafios
    adicionais ao trabalhar com classes genéricas. É importante entender como esses recursos funcionam
    com classes genéricas para evitar problemas de compatibilidade ou segurança.

    - Por fim, é importante lembrar que as classes genéricas são uma ferramenta poderosa e flexível
    em Java, mas que requerem um pouco de atenção e cuidado para serem utilizadas de forma adequada
    e segura.

 */

import generics.dominio.BoatModel;
import generics.dominio.CarModel;
import generics.service.CarRentalService;
import generics.service.RentalService;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Aula186e187Generics_genericClass {
    public static void main(String[] args) {
        // Demonstração: Aluguel de carros (em service), com e sem generics.

        // Sem generics, ficamos limitados a trabalhar apenas com CARRO:
        System.out.println("-------------------------------------[SEM GENERICS]----------------------------------");
        CarRentalService carRentalService = new CarRentalService();
        CarModel carAvailable = carRentalService.printAvailableCar();
        System.out.println("Usando o carro por um mes...");
        carRentalService.giveBackRetrieveCar(carAvailable);

        // Com generics, podemos trabalhar com qualquer VEÍCULO/Objeto:
        System.out.println("-------------------------------------[COM GENERICS]----------------------------------");
        Queue<CarModel> carsQueue = new PriorityQueue<>(
                List.of(new CarModel("Fusca"), new CarModel("BMW")));

        Queue<BoatModel> boatsQueue = new PriorityQueue<>(
                List.of(new BoatModel("Canoa"), new BoatModel("Iati")));

        RentalService<CarModel> carRentalServiceGenerics = new RentalService<>(carsQueue);
        RentalService<BoatModel> boatRentalServiceGenerics = new RentalService<>(boatsQueue);

        // Códigos são exatamente iguais ao sem, generics, porém
        // ao invés de utilizar o serviço específico para Carro, trocamos pelo genérico:
        CarModel carAvailableGenerics = carRentalServiceGenerics.printAvailableObject();
        System.out.println("Usando o carro por um mes...");
        carRentalServiceGenerics.giveBackRetrieveCar(carAvailableGenerics);

        BoatModel boatAvailableGenerics = boatRentalServiceGenerics.printAvailableObject();
        System.out.println("Usando o barco por um mes...");
        boatRentalServiceGenerics.giveBackRetrieveCar(boatAvailableGenerics);
    }
}
