package estruturaDados.queue;

import estruturaDados.models.PatientModel;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {

        // Queue criada por mim:
        Queue<Integer> fila = new Queue<Integer>();
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.enqueue(40);

        System.out.println(fila);
        System.out.println(fila.isEmpty());
        System.out.println(fila.dequeue());
        System.out.println(fila);

        // Queue na API Java:

        java.util.Queue<Integer> filaJava = new LinkedList<Integer>();
        filaJava.add(10); // enqueue (push)
        filaJava.peek(); // espiar (no caso o elemento de index 0)
        filaJava.remove(); // dequeue (no caso o elemento de index 0)

        // Fila com prioridade criada por mim
        QueueWithPriority<PatientModel> patientQueue = new QueueWithPriority<PatientModel>();

        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 2)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 1)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 9)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 3)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 3)));

        System.out.println(patientQueue);

        patientQueue.dequeue();
        System.out.println(patientQueue);


        // fila com prioridade nativa do Java:
        java.util.Queue<PatientModel> filaJavaPatient = new PriorityQueue<PatientModel>();

        System.out.println(filaJavaPatient.add(new PatientModel("Irineu", 2)));
        System.out.println(filaJavaPatient.add(new PatientModel("Irineu", 1)));
        System.out.println(filaJavaPatient.add(new PatientModel("Irineu", 9)));
        System.out.println(filaJavaPatient.add(new PatientModel("Irineu", 3)));
        System.out.println(filaJavaPatient.add(new PatientModel("Irineu", 3)));

        // Isso pois o model Patient implementa a interface comparable, caso NÃO o tivera:
        // NÃo é boa prática:

        java.util.Queue<PatientModel> filaJavaPatientSemComparableNoObj = new PriorityQueue<PatientModel>(
                new Comparator<PatientModel>() {
                    @Override
                    public int compare(PatientModel p1, PatientModel p2) {
                        // obj1 > obj2 return > 0; (1)
                        // obj1 < obj2 return < 0; (-1)
                        // obj1 == obj2 return 0;
//                        if (p1.getPriority() > p2.getPriority()) {
//                            return 1;
//
//                        } else if (p1.getPriority() < p2.getPriority()) {
//                            return -1;
//                        }
//                        return 0;

                        // mais elegante
                        return Integer.valueOf(p1.getPriority()).compareTo(p2.getPriority());
                    }
                }
        );

        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 2)));
        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 1)));
        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 9)));
        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 3)));
        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 3)));
        System.out.println(filaJavaPatientSemComparableNoObj.add(new PatientModel("Irineu", 9)));

        System.out.println(filaJavaPatientSemComparableNoObj);

    }
}
