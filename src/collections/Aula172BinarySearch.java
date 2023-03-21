package collections;

/*
 - BinarySearch também funciona para Arrays nativos em java não apenas para coleções.
 - Utilizado para realizar a busca de um elemento retornando um index.
 - A Diferença dele para um indexOf() é que além de retornar NEGATIVO quando um Objeto não existe na lista
 retorna também em qual posição ele deveria estar para manter a lista ordenada.
 - Quando existe o elemento em busca, retorna um inteiro POSITIVO que representa o index dele.
 - Quando NÂO existe o elemento em busca, retorna um inteiro NEGATIVO que representa
 o index que o elemento deve ser inserido para manter a lista ordenada subtraindo 1.
 OBS: Mais explicações sobre a regra meio confusa no inicio mas com um propósito abaixo.
 - ATENSÃO: Devido a essa regra de retornar NEGATIVO quando não existir subtraíndo 1
 Tomar CUIDADOS ao se trabalhar com coleções/listas de valores NEGATIVOS.
      - Não irá rolar Exceptions, porém vai adicionar complexidade para tranformar
     o valor desse resultado tornando ele consistênte e correto.

 - ATENSÃO: A coleção em questão deve ser da categoria Sorted, ou seja lista organizada ASC ou DESC..
 - Em casos de utilizar Wrappers não será necessário implementar o Comparable,
 pois o proprio Java o faz, bastando assim utilizar o Collections.sort() simples sem sobrecarga.
 - Em casos aonde se utiliza outras classes aonde não implementam Comparable ou Comparator,
 será necessário criar uma instância correta deste contexto, e passa-la ao sort() sobrecarregado.
*/

// FÓRMULA em casos de retorno NEGATIVO indicando que o elemento não exists na lista,
// Sendo necessário assim transformar o index corretamente para obter o index real
// No qual o elemento deveria estar para manter a lista organizada/sorted:
/*
    int realIndex = 0;
    if (indexOfElementWithBinarySearched < 0){ // NEGATIVO é preciso aplicar a transformação:
        realIndex = -(indexOfElementWithBinarySearched + 1);

        ... Aqui podemos realizar a criação e inserção do Objeto na lista,
        de uma forma em que mantém a lista organizada na regra definida em Comparable<T>

    } else { // POSITIVO não é preciso, pois o index vem corretamente.
        realIndex = indexOfElementWithBinarySearched;
    }
*/




// Regras:
/*
    - Retorna o Index do elemento se ele existir.
    - Se NÃO existir o elemento ele retorna a seguinte convenção:
        (-(ponto de inserção) -1) aonde "ponto de inserção" == index aonde o elemento deveria estar.
        e subtraí 1, pois se o valor retornado for 0 não saberiamos se é o resultado Exists da busca
        ou se é a posição em que o elemento deveria estar, ou seja NotExists.

        OU SEJA, ele garante que só irá retornar POSITIVO se o elemento Exists,
        caso contrario o retorno será sempre NEGATIVO.

        PORISSO deve se ATENTAR ao criar coleções com valores NEGATIVOS,
        pois ai iria adicionar mais complexidade nesta lógica,
        e a matematica que deverá ser aplicada após o processamento,
        fazendo assim a transformação correta do elemento iria ter que
        levar em consideração esse fator.
*/

// Vantagens de se utilizar a busca binária: APENAS e só APENAS se aplica em listas Sorted/Ordenadas!!
/*
   - A vantagem de utilizar o método binarySearch() é a eficiência em encontrar um elemento em
   uma lista ordenada. A busca binária é um algoritmo eficiente para encontrar um elemento em
   uma lista ordenada, pois a busca é feita pela metade da lista em cada iteração, reduzindo
   drasticamente o número de comparações necessárias.

   - Em comparação com uma busca linear, em que cada elemento da lista é verificado um por um
   até que o elemento procurado seja encontrado, a busca binária é significativamente mais rápida
   para listas maiores. Isso se deve ao fato de que a BUSCA BINÁRIA corta a lista pela METADE a
   cada iteração, enquanto a busca LINEAR percorre TODOS os elementos da lista.
*/



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Aula172BinarySearch {
    public static void main(String[] args) {

        // Com classes Wrapper:
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(2);
        numbersList.add(0);
        numbersList.add(4);
        numbersList.add(3);

        Collections.sort(numbersList); // sem sobrecarga pois o Wrapper implementa Comparable<T>
        int indexOfElementSearched = Collections.binarySearch(numbersList, 6);// key == elemento em busca.
        System.out.println(indexOfElementSearched);


        // Com classes personalizadas que NÂO implementam Comparable<T> e
        // para utilizar sorted() precisamos definir as regras de comparação:
        Comparator<PatientModel> comparador = Comparator.comparing(
                PatientModel::getPriority // aqui poderia ser um lambda get().get().get()..... ALL em casos de Objetos aninhados
        );

        List<PatientModel> patientModelList = new ArrayList<>();
        patientModelList.add(new PatientModel("Wellison", 3));
        patientModelList.add(new PatientModel("Danielle", 2));
        patientModelList.add(new PatientModel("Irineu", 1));
        patientModelList.add(new PatientModel("InemEu", 7));
        patientModelList.add(new PatientModel("Wellison", 6));
        patientModelList.add(new PatientModel("Wellison", 8));
        System.out.println("Antes de organizar: "+patientModelList);

        Collections.sort(patientModelList, comparador);
        PatientModel wellisonPatient = new PatientModel("pamiro", 9);
        int indexOfElementWithBinarySearched = Collections.binarySearch(patientModelList, wellisonPatient, comparador);
        System.out.println("Depois de organizar: "+patientModelList);
        System.out.println("----------------------------------------");
        System.out.println("Index do Elemento buscado: "+indexOfElementWithBinarySearched);

        // Fórmula para obter o index real de inserção para elementos NotExists na lista:

        int realIndex = 0;
        if (indexOfElementWithBinarySearched < 0){ // NEGATIVO é preciso aplicar a transformação:
            realIndex = -(indexOfElementWithBinarySearched + 1);
        } else { // POSITIVO não é preciso, pois o index vem corretamente.
            realIndex = indexOfElementWithBinarySearched;
        }



        // Fórmula para obter o
    }
}
