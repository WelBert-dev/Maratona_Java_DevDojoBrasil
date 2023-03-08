package colections;


// Estudos mais a fundo sobre Compare e Comparator em src/estruturaDados
// Aqui contém as 3 possíveis formas de se trabalhar com ordenação de Objetos em geral:
// - Comparable<T>
// - Comparator<T>
// - Quando a classe em sí não implementa a interface Comparable
// delegamos assim essa responsabilidade para a API Java.

// Apesar de ser mais prático delegar ao Java, a depender do Objeto e da coesão
// é mais interessante implementar esse método no Objeto em sí para assim definir
// um critério default que vai servir para vários cenários em diversos pontos do código.
// - Mas é uma boa escolha de se adotar quando não temos controle do Objeto em ordenação,
// como códigos terceiros de frameworks e etc...

import java.util.*;

public class Aula170e171SortingLists_pt02e03_ComparableAndComparator {
    public static void main(String[] args) {

        // Utilziando a regra de comparação padrão: compareTo do Comparable:
        List<PatientModel> patientModelList = new ArrayList<>();
        patientModelList.add(new PatientModel("Wellison", 3));
        patientModelList.add(new PatientModel("Danielle", 2));
        patientModelList.add(new PatientModel("Irineu", 1));
        patientModelList.add(new PatientModel("InemEu", 7));
        patientModelList.add(new PatientModel("Wellison", 6));
        patientModelList.add(new PatientModel("Wellison", 8));
//        Collections.sort(patientModelList);
//        System.out.println(patientModelList);
//
//        // Alterando essa regra padrão em um ponto especifico do código:
//        Collections.sort(patientModelList, new AlterRuleSortingComparator());

//        // Outra forma de fazer quando o objeto não implementa a interface
//        // Comparable, e sem precisar também de uma classe auxiliar Comparator
//        // personalizada, delegando a implementação para a API JAVA:
//
//        // vamos que aqui eu escolho o atributo no qual pretendo utilizar como flag
//        // na ordenação ASC.

        Comparator<PatientModel> comparador = Comparator.comparing(PatientModel::getPriority);
        patientModelList.sort(comparador);

        // outra sintaxe:
//        Collections.sort(patientModelList, (p1, p2) -> Double.compare(p1.getPriority(), p2.getPriority()));



        Collections.reverse(patientModelList);

        System.out.println(patientModelList);

    }
}

class PatientModel{

    private String nome;
    private int priority; // quantom mais baixo mais prioritário ou seja 1 é a maior prioridade.

    public PatientModel() {}

    public PatientModel(String nome, int priority) {
        this.nome = nome;
        this.priority = priority;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

//    @Override
//    public int compareTo(Object o) {
//        // obj1 > obj2 return > 0; (1)
//        // obj1 < obj2 return < 0; (-1)
//        // obj1 == obj2 return 0;
//        PatientModel otherObject = this.getClass().cast(o);
//        if (this.priority > otherObject.getPriority()) {
//            return 1;
//
//        } else if (this.priority < otherObject.getPriority()) {
//            return -1;
//        }
//        return 0;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientModel that = this.getClass().cast(o);
        return priority == that.getPriority() && Objects.equals(nome, that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, priority);
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "nome='" + nome + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}

class AlterRuleSortingComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        // ... Altera a regra de comparação.
        return Integer.compare(((PatientModel)o1).getPriority(), ((PatientModel)o2).getPriority());
    }
}
