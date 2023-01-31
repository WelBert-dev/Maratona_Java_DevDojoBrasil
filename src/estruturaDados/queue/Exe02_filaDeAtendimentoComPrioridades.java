package estruturaDados.queue;

// Escreva um programa que simule a distribuição de senhas de atendimento,
// a um grupo de pessoas. Cada pessoa pode receber uma senha prioritária ou
// uma senha normal. O programa deve obedecer os seguintes critérios:
// - Existe apenas 1 atendente;
// - 1 pessoa com senha normal deve ser atendida a cada 3 pessoas com senha prioritária.
// - Não havendo prioriodades, as pessoas com senhas normais podem ser atendidas.

import estruturaDados.models.PatientModel;

public class Exe02_filaDeAtendimentoComPrioridades {
    public static void main(String[] args) {
        final int MAX_PRIORITY = 3;
        Queue<PatientModel> regularPatientQueue = new Queue<>();
        Queue<PatientModel> priorityPatientQueue = new Queue<>();

        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente A", 2)));
        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente B", 2)));
        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente C", 2)));
        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente D", 2)));
        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente E", 2)));
        System.out.println(regularPatientQueue.enqueue(new PatientModel("Paciente F", 2)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PG", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PH", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PI", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PJ", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PK", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PL", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PM", 1)));
        System.out.println(priorityPatientQueue.enqueue(new PatientModel("Paciente PN", 1)));

        while (!regularPatientQueue.isEmpty() || !priorityPatientQueue.isEmpty() ) {
            int count = 0;
            while (!priorityPatientQueue.isEmpty() && count < MAX_PRIORITY) {
                atendePessoa(priorityPatientQueue);
                count++;
            }

            if (!regularPatientQueue.isEmpty()) {
                atendePessoa(regularPatientQueue);
            }

            if (priorityPatientQueue.isEmpty()) {
                while (!regularPatientQueue.isEmpty()) {
                    atendePessoa(regularPatientQueue);
                }
            }
        }


    }
    public static void atendePessoa(Queue<PatientModel> fila) {
        PatientModel pacienteAtendido = fila.dequeue();
        System.out.println(pacienteAtendido.getNome() + " atendido!!");
    }

}
