package ZA_estruturaDados.queue;

// Utilizando a classe fila criada anteriormente,
// crie uma fila de Documentos que precisam ser impressos.
// Cada Documento possuí:
// Nome, e Quantidade de Folhas a serem impressas.
// O Programa deve:
// - Enfileirar os Documentos.
// - E Seguindo a ordem o programa deve imprimir cada documento,
// desemfileirando da fila,

import ZA_estruturaDados.models.DocumentModel;

public class Exe01_impressaoDocumentos {

    public static void main(String[] args) {
        // Fila sem prioridade, model não implementa o comparable
        Queue<DocumentModel> documentQueue = new Queue<>();
        documentQueue.enqueue(new DocumentModel("A", 1));
        documentQueue.enqueue(new DocumentModel("A", 3));
        documentQueue.enqueue(new DocumentModel("A", 6));
        documentQueue.enqueue(new DocumentModel("A", 8));
        documentQueue.enqueue(new DocumentModel("A", 2));

        System.out.println(documentQueue);

        int paperCount = 0;
        while (!documentQueue.isEmpty()) {

            DocumentModel doc = documentQueue.dequeue();

            System.out.println("Imprimindo o documento "+ doc.getNome() +
                    " com " + doc.getPaperNum() + " número de folhas!");

            try {
                Thread.sleep(200 * doc.getPaperNum());
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }

            paperCount += doc.getPaperNum();
        }

        System.out.println("Todos os documentos foram impressos! ;D total de "+paperCount+" folhas");

    }
}
