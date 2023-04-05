package ZA_estruturaDados.queue;

import ZA_estruturaDados.models.PatientModel;

import java.util.Random;

public class PSNovosPacientesRunnable implements Runnable{
    private QueueWithPriority<PatientModel> fila;
    private Random priorityRandom = new Random();

    public PSNovosPacientesRunnable(QueueWithPriority<PatientModel> fila) {
        this.fila = fila;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(8000);
                PatientModel paciente = new PatientModel("Paciente novo "+i, priorityRandom.nextInt(3)); // até 3 excluindo ele
                fila.enqueue(paciente);
                System.out.println(paciente + " chegou e entrou na fila");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
