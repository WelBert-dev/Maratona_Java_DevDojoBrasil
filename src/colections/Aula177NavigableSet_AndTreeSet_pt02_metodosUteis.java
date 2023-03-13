package colections;

// Mais sobre TreeSet e métodos mais uteis desta coleção:
// - descendingSet(): Percorre a lista de trás para frente DESC
// - lower(): < que currentObject
// - floor(): <= o currentObject (igual ou imediatamente MENOR ao current object)
// - higher(): > que currentObject
// - ceiling(): >= o currentObject (igual ou imediatamente MAIOR ao current object)

// - pollFirst: faz um pop na lista, ou seja retira o primeiro elemento e retorna ele (removendo)
// - pollLast: mesmo contexto do anterior, remove e retorna o último elemento da coleção.


import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Aula177NavigableSet_AndTreeSet_pt02_metodosUteis {
    public static void main(String[] args) {
        Comparator<PatientModel> patientModelComparador = Comparator.comparing(
                PatientModel -> PatientModel.getPriority() // aqui poderia ser get().get().get()..... ALL
        );
        NavigableSet<PatientModel> patientModelTreeSet2 = new TreeSet<>(patientModelComparador);
        patientModelTreeSet2.add(new PatientModel("Wellison", 3));
        patientModelTreeSet2.add(new PatientModel("Danielle", 2));
        patientModelTreeSet2.add(new PatientModel("Elizângela", 4));
        patientModelTreeSet2.add(new PatientModel("Sidnei", 5));

        // DESC com descendingSet: (Utiliza o compareTo() para definir a comparação dos elementos).
        for (PatientModel patient : patientModelTreeSet2.descendingSet()) { // reverse
            System.out.println(patient); // Organizado de acordo com o patientModelComparator
        }

        // lower() retornando a ocorrência/Object imediatamente menor que o 3:
        System.out.println("--------------------------------------");
        System.out.println("Objeto imediatamente menor que 3 com lower: " +
                patientModelTreeSet2.lower(new PatientModel("Adenildu", 3))); // 2 (Danielle)

        // floor() retornando a primeira ocorrência igual ou imediatamente menor que 3:
        System.out.println("--------------------------------------");
        System.out.println("Objeto igual ou imediatamente menor que 3 com floor: " +
                patientModelTreeSet2.floor(new PatientModel("Adenildu", 3))); // 3 (Wellison)

        // higher() retornando a primeira ocorrência/Object imediatamente maior que o 3:
        System.out.println("--------------------------------------");
        System.out.println("Objeto igual ou imediatamente menor que 3 com floor: " +
                patientModelTreeSet2.higher(new PatientModel("Adenildu", 3))); // 4 (Elizângela)

        // ceiling() retornando a primeira ocorrência igual ou imediatamente maior que 3:
        System.out.println("--------------------------------------");
        System.out.println("Objeto igual ou imediatamente menor que 3 com floor: " +
                patientModelTreeSet2.ceiling(new PatientModel("Adenildu", 3))); // 3 (Wellison)


        // REMOVENDO e retornando o primeiro elemento da lista:
        System.out.println(patientModelTreeSet2.size());
        System.out.println(patientModelTreeSet2.pollFirst());
        System.out.println(patientModelTreeSet2.size());


        // REMOVENDO e retornando o último elemento da lista:
        System.out.println(patientModelTreeSet2.pollLast());
        System.out.println(patientModelTreeSet2.size());
    }
}
