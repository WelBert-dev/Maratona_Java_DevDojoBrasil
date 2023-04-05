package collections;

// Operação básica porém muito utilizada no dia-a-dia
// Obs: Ao utilizar 0 como size do array, o java se encarrega
// de definir o tamanho corretamente:
// integersList.toArray(new Integer[0]);
// Com isto temos mais performance do que se definirmos nós mesmos com:
// integersList.toArray(new Integer[integersList.size()]);

// Obs sobre o Arrays.asList(new int[]{1,2,3}): Ele cria uma conexão com o array
// original passado no parâmetro, ou seja cria-se um link entre os dois,
// então as operações que forem realizadas em um, afeta o outro.

// O asList() é um varargs então podemos utilizar sobrecarregando com virgulas 1, 2 ,3 4, 5, 6...

/*
    É otimizado para criar uma lista de tamanho fixo a partir de um array existente e,
    portanto, é mais eficiente em termos de memória e desempenho. Além disso, o método
    Arrays.asList() retorna uma visualização da lista, o que significa que não é criada
    uma nova instância de lista. Em vez disso, é criado um objeto interno que atua como
    uma "janela" para o array subjacente, permitindo que você trabalhe com a lista como
    se fosse uma lista regular.

    SOLUÇÃO: Para quebrar esse vínculo utilizar o array no construtor e criar uma nova instância
    de List<T>: List<Integer> pamonha = new ArrayList<>(Arrays.asList(listToArray));

*/

/*
    Por outro lado, a conversão de um array para um stream e, em seguida, para uma lista
    usando Arrays.stream(listToArray).toList() cria uma nova lista e, portanto, é menos
    eficiente em termos de memória e desempenho. Além disso, esse método pode ser útil
    em situações em que é necessário realizar operações em um stream antes de criar a
    lista final.
*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aula173ListToArrayConvertion {
    public static void main(String[] args) {
        List<Integer> integersList = new ArrayList<>();
        integersList.add(1);
        integersList.add(2);
        integersList.add(3);

        // Converte uma lista em um array:
        // Object[] objects = inteiros.toArray(); -> sem especificar ele retorna Objects
        Integer[] listToArray = integersList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(listToArray));

        // Converte um array em uma lista:
//        List<Integer> arrayToList = Arrays.stream(listToArray).toList();
//        System.out.println(arrayToList);
        List<Integer> integers = Arrays.asList(listToArray); // linka os dois arrays em uma List<T>
        //integers.add(4); -> Operação inválida pois Arrays não podem ser redimensionados.
        integers.set(2, 4); // Operação VÀLIDA pois não estemos alterando o dimensionamento do array (size)

        //integers.remove(2); -> Operação inválida pois estamos tentando redimensionar o Array.

        System.out.println("List<T>: "+integers.toString()); // List<T>: [1, 2, 4]
        System.out.println("Integer[] - Array: "+Arrays.toString(listToArray)); // Integer[] - Array: [1, 2, 4]

        // Para "quebrar" esse vínculo entre essas coleções é passar ela ao construtor,
        // com isto sera criado um array do zero e assim podemos realizar as alterações:
        List<Integer> pamonha = new ArrayList<>(Arrays.asList(listToArray));



    }
}
