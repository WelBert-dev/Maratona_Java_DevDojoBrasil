package estruturaDados.queue;

import estruturaDados.models.PatientModel;

public class PSAtendimentoRunnable implements Runnable{
    private QueueWithPriority<PatientModel> fila;

    public PSAtendimentoRunnable(QueueWithPriority<PatientModel> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        while (!fila.isEmpty()) {

            try {
                System.out.println(fila.dequeue() + " atendida.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Atendimento concluído! ;D");
    }
}
