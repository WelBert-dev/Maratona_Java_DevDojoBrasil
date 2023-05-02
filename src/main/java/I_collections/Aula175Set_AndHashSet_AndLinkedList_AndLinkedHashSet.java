package I_collections;

// LinkedList listas ligadas, mais estudos em src/estuturaDados:
// Basicamente uma lista aonde o elemento corrente aponta para o proximo e o anterior;
// Guardando melhor a posição inicial e a posição final da coleção.
// Uma das melhores classe para se trabalhando com grande remoções de elementos, ela é a mais performatica.
// Complexidade Big-O remove(): O(1);

// Set é uma interface utilizada para se trabalhar com classes deste contexto.

// HashSet é uma implementação que utiliza o hashCode do elemento, portanto
// não é indexada então é preciso percorrer a coleção para obter elementos.
// além de não conter index a lista também não garante organização devido a utilizar o hashcode como critério.
// Complexidade Big-O add() O(1); contains() O(1); next() O(h/n);

// Resolvendo o problema anterior a LinkedHashSet vem para garantir essa organização,
// aqui ele mantém a ordem de acordo com a inserção de elementos.
// Todas as classes que implementam e extends Set utilizam a implementação equals() para ver se os elementos são iguais.

import java.util.*;

public class Aula175Set_AndHashSet_AndLinkedList_AndLinkedHashSet {
    public static void main(String[] args) {

        // LinkedList:
        // Como adotamos programação orientada a interface basta trocar a instância new para LinkedList
        List<PatientModel> patientModelList = new LinkedList<>();
        patientModelList.add(new PatientModel("Irineu", 0));
        patientModelList.add(new PatientModel("Irinéia", 1));
        patientModelList.add(new PatientModel("Wellison", 3));
        patientModelList.add(new PatientModel("Bertelli", 4));
        patientModelList.add(new PatientModel("Danielle", 5));
        patientModelList.add(new PatientModel("Elizângela", 6));

        patientModelList.removeIf(patient -> patient.getPriority() == 0);// possi mesmos métodos, porém mais performatico.

        // Set e HashSet:
        // Vantagem é que ele não permite dois elementos iguais na coleção (DISTINCT)
        // Ele é organizado pelo Hash ou seja não garantimos a ordenação sort inicial.
        // Não existe index, ou seja o elemento não é indexado então é preciso percorrer a coleção;

        Set<PatientModel> patientModelHashSet = new HashSet<>();
        patientModelHashSet.add(new PatientModel("Irineu", 0));
        patientModelHashSet.add(new PatientModel("Irinéia", 1));
        patientModelHashSet.add(new PatientModel("Wellison", 3));
        patientModelHashSet.add(new PatientModel("Bertelli", 4));
        patientModelHashSet.add(new PatientModel("Danielle", 5));
        patientModelHashSet.add(new PatientModel("Elizângela", 6));

        for (PatientModel patient: patientModelHashSet) {
            System.out.println(patient);
        }

        // LinkedHashSet: Resolve problema de ordenação e garante posição de acordo com inserção inicial de elementos.
        Set<PatientModel> patientModelLinkedHashSet = new LinkedHashSet<>();
        patientModelHashSet.add(new PatientModel("Irineu", 0));
        patientModelHashSet.add(new PatientModel("Irinéia", 1));
        patientModelHashSet.add(new PatientModel("Wellison", 3));
        patientModelHashSet.add(new PatientModel("Bertelli", 4));
        patientModelHashSet.add(new PatientModel("Danielle", 5));
        patientModelHashSet.add(new PatientModel("Elizângela", 6));

        for (PatientModel patient: patientModelLinkedHashSet) {
            System.out.println(patient);
        }

    }
}
