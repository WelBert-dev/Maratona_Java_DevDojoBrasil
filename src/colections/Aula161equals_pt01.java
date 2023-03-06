package colections;

// O método equals é definido na classe Object logo todos os Objetos
// podem implementar com Override, ele é utilizado para comparar valores
// ao invés de comparar referência em memória (==),
// Nele devemos definir quais atributos seram utilizados
// para essa comparação, ou seja a regra que define qual atributo
// deve ser igual para a comparação retornar true.
// ATENÇÂO: Equals sempre trabalha em conjunto do hashCode, então os dois devem
// ser implementados linearmente em relação aos atributos de comparação.
// para assim os dois seram consistentes entre sí.

// A implementação padrão do método equals verifica se dois objetos são iguais
// com base no conteúdo dos campos do objeto, enquanto a implementação padrão
// do método hashCode gera um valor hash com base no conteúdo desses campos.
// Se dois objetos são iguais de acordo com o método equals, eles devem ter
// o mesmo valor hash de acordo com o método hashCode.
// Se dois objetos tiverem o mesmo valor hash de acordo com o método hashCode,
// isso não significa necessariamente que eles são iguais de acordo com o método equals,
// mas é uma indicação de que eles podem ser iguais e precisam ser comparados mais detalhadamente.
// Essa relação entre equals e hashCode é importante porque muitas coleções em Java,
// como HashMap e HashSet, usam valores hash para armazenar e recuperar objetos com mais eficiência.
// Sem uma implementação correta do método hashCode, essas coleções não funcionariam corretamente,
// resultando em bugs e desempenho ruim. Por isso, é importante que as implementações de equals e hashCode
// trabalhem em conjunto e sejam consistentes entre si.

// Para outros tipos de comparações como ordenações/sorting devemos
// utilizar as interfaces Comparable<T> ou Comparator<T>
// ambas tem o mesmo propósito de verificar também por meio de algum dos
// atributos de um objeto, se um objeto é maior, menor ou igual a outro.
// convenções de retorno:
//      Se this > otherObject então return 1 (positivo)
//      Se this < otherObject então return -1 (negativo)
//      Se this == otherObject então return 0

// A Diferença de uso para as duas abordagens é a seguinte:
// - Para Comparable<T> implementamos essa interface no próprio objeto que sera ordenado em sí,
// ou seja, definimos a regra "natural" de ordenação (definimos o critério default). compareTo(T otherObject);
// - Para Comparator<T> implementamos essa interface em uma classe auxiliar, ou seja fora do objeto em comparação,
// ou seja, definimos a regra "personalizada" de ordenação para utilizarmos apenas em pontos especificos do
// código, assim podemos mudar o critério default de ordenação se preciso. compare(T object1, T object2);

public class Aula161equals_pt01 {
    public static void main(String[] args) {
        String nome1 = "Wellison Bertelli";
        String nome2 = "Wellison Bertelli";
        System.out.println(nome1 == nome2); // comparando REFERÊNCIA em memória.
        // true pois ambos fazem referência ao mesmo valor no pool de strings
        // false seria se ao invez de passar o valor literal nós criasse um objeto string
        // (new String("Wellison Bertelli")) e verificassemos se são iguais,
        // aqui não estariamos mais verificando do pool de strings e sim alocamos
        // manualmente um espaço em memória para o objeto String.

        String nome3 = new String("Wellison Bertelli");
        System.out.println(nome3 == nome2); // false

        System.out.println(nome1.equals(nome2)); // comparando VALOR em memória. true
    }
}
