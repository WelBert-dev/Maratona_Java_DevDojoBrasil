package colections;

// NavigableSet extends de SortedSet e o SortedSet extends de Set
// Portanto todas seguem as regras sobre não poder inserir elementos iguais
// ou seja elas fazem DISTINCT e garantem isso!

// NavigableSet (extends de SortedSet) basicamente adiciona alguns métodos para
// pegar elementos baseado em posições já existentes
/* As VANTAGENS de se trabalhar com NavigableSet com implementações TreeSet e ConcurrentSkipListSet:
    - Acesso ordenado: as implementações de NavigableSet mantêm seus elementos em ordem, o que pode
    ser útil em várias situações em que a ordem dos elementos é importante.

    - Pesquisa eficiente: as implementações de NavigableSet fornecem métodos eficientes para pesquisar
    elementos com base em seus valores. Por exemplo, o método ceiling retorna o menor elemento na coleção
    que é maior ou igual a um determinado valor.

    - Subconjuntos: as implementações de NavigableSet permitem a criação de subconjuntos com base em um
    intervalo de valores. Isso pode ser útil quando você deseja trabalhar apenas com um subconjunto de
    elementos em uma coleção.

    - Concorrência: o ConcurrentSkipListSet é uma implementação de NavigableSet que é thread-safe e pode
    ser usada em ambientes concorrentes.

    - Polimorfismo: as implementações de NavigableSet são todas subtipos de SortedSet, o que significa
    que elas podem ser usadas em qualquer lugar em que um SortedSet é esperado. Isso pode ser útil em
    situações em que você deseja trocar uma implementação de conjunto por outra sem alterar o restante do código.
*/
/* As DESVANTAGENS:
    - Sobrecarga de memória: as implementações de NavigableSet, especialmente o ConcurrentSkipListSet,
    podem consumir mais memória do que outras implementações de conjunto devido à estrutura de dados
    necessária para manter os elementos em ordem.

    - Complexidade: as implementações de NavigableSet podem ser mais complexas de usar do que outras
    implementações de conjunto, especialmente se você não estiver familiarizado com os métodos
    relacionados a ordem e pesquisa.

    - Desempenho: embora as implementações de NavigableSet sejam eficientes em relação a outras implementações
    de conjunto, o desempenho pode não ser tão bom em comparação com outras estruturas de dados especializadas,
    como HashMap ou ArrayList, dependendo do uso específico.

    - Restrições de tipo de elemento: como as implementações de NavigableSet mantêm seus elementos em ordem,
    elas requerem que os elementos implementem a interface Comparable ou que um Comparator seja fornecido.
    Isso pode limitar a flexibilidade em relação a outras implementações de conjunto que não possuem essa
    restrição.
*/

// TreeSet é uma das classes que implementam a NavigableSet e ela é um algoritmo de arvore,
// portanto os elementos da lista devem ser organizável implementando a interface Comparable<T>
// Utilizar ela quando os elementos desta coleção for ordenável com Comparable<T> ou Comparator<T>
// Assim ele sempre se reajusta de acordo com essa comparação, ao atualizar a coleção de acordo com algum método.
// Obs: Também não aceita elementos duplicados (DISTINCT);
// Implementação thread-safe: embora o TreeSet não seja thread-safe, você pode usar o método
// Collections.synchronizedSet para criar uma versão thread-safe do TreeSet.

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Aula176NavigableSet_AndTreeSet_pt01 {
    public static void main(String[] args) {
        // Exeception em classes que não implementam Comparable<T>
        // NavigableSet<PatientModel> patientModelTreeSet = new TreeSet<>();
        // patientModelTreeSet.add(new PatientModel("Wellison", 3));
        // Exception PatientModel não implementa Comparable<T>

        // Algoritmos de árvore utilizam a lógica de ordenação sort()
        // para garantir a estrutura dela, portanto todas as classes Set precisam tomar cuidados
        // e garantir coleções de objetos ordenáveis por algum critério.

        // Quando não é possível implementar Comparable<T> utilizando o Comparator<T>:

        NavigableSet<PatientModel> patientModelTreeSet = new TreeSet<>(new PatientModelNameComparator());
        patientModelTreeSet.add(new PatientModel("Wellison", 3));
        // Garante ordenação corrigindo o erro anterior.

        // Utilizando abordagem mais simples sem classes auxiliares:
        // A Lista é organizada de acordo com o critério compareTo() dos elementos.
        Comparator<PatientModel> patientModelComparador = Comparator.comparing(
                PatientModel -> PatientModel.getNome() // aqui poderia ser get().get().get()..... ALL
        );
        NavigableSet<PatientModel> patientModelTreeSet2 = new TreeSet<>(patientModelComparador);
        patientModelTreeSet2.add(new PatientModel("Wellison", 3));
        patientModelTreeSet2.add(new PatientModel("Danielle", 2));
        patientModelTreeSet2.add(new PatientModel("Elizângela", 4));
        patientModelTreeSet2.add(new PatientModel("Sidnei", 5));
        for (PatientModel patient : patientModelTreeSet2) {
            System.out.println(patient); // Organizado de acordo com o patientModelComparator
        }

    }
}

class PatientModelNameComparator implements Comparator<PatientModel> {

    @Override
    public int compare(PatientModel o1, PatientModel o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
}
