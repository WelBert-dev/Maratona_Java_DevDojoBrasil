package ZA_estruturaDados.queue;

// Utilize a classe Queue para simular um sistema de senhas de um pronto socorro.
// Cada pessoa pode ser classificada com 3 códigos:
// vermelho - prioridade maior (1),
// amarelo - prioridade média (2) e
// verde - prioridade baixa (3).
// O programa deve obedecer as seguintes condições:
// Enfileire 6 pessoas na fila inicial,
// Pessoas com prioridade vermelha devem ser atendidas primeiro;
// Cada consulta dura cerda de 5 segundos, e depois o próximo da fila é chamado.
// A Cada 4 segundos uma pessoa chega no pronto socorro com prioridade aleatória.
// (Crie um código java para definir essa aleatoriedade)

import ZA_estruturaDados.models.PatientModel;

public class Exe03_filaProntoSocorroComPrioridade {
    public static final int GREEN = 2;
    public static final int YELLOW = 1;
    public static final int RED = 0;

    public static void main(String[] args) {
        QueueWithPriority<PatientModel> queue = new QueueWithPriority<>();

        queue.enqueue(new PatientModel("Paciente 1", GREEN));
        queue.enqueue(new PatientModel("Paciente 2", YELLOW));
        queue.enqueue(new PatientModel("Paciente 3", RED));
        queue.enqueue(new PatientModel("Paciente 4", GREEN));
        queue.enqueue(new PatientModel("Paciente 5", GREEN));
        queue.enqueue(new PatientModel("Paciente 6", GREEN));

        PSAtendimentoRunnable atendimento = new PSAtendimentoRunnable(queue);
        PSNovosPacientesRunnable novoPaciente = new PSNovosPacientesRunnable(queue);


        Thread t1 = new Thread(atendimento);
        Thread t2 = new Thread(novoPaciente);

        t1.start();
        t2.start();


    }
    public static void atendePessoa(Queue<PatientModel> fila) {
        PatientModel pacienteAtendido = fila.dequeue();
        System.out.println(pacienteAtendido.getNome() + " atendido!!");
    }
}
