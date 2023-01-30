package estruturaDados.queue;

import estruturaDados.models.PatientModel;

import java.util.LinkedList;

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

        QueueWithPriority<PatientModel> patientQueue = new QueueWithPriority<PatientModel>();

        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 2)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 1)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 9)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 3)));
        System.out.println(patientQueue.enqueue(new PatientModel("Irineu", 3)));

        System.out.println(patientQueue);

        patientQueue.dequeue();
        System.out.println(patientQueue);
    }
}
