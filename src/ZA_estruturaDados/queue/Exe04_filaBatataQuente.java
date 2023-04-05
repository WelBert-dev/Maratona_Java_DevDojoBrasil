package ZA_estruturaDados.queue;

// Escreva um programa utilizando Filas que simule a brincadeira da
// "Batata quente". A brincadeira consiste de um grupo de crianças
// que fica em circulo, sentados ou em pé. Uma criança fica fora da
// roda, de costas com os olhos vendados, dizendo a frase "Batata
// quente, quente, quente... queimou" Enquanto isso, os demais vão
// passando a bola de mão em mão até ouvirem a palavra "queimou".
// Quem estiver com a bola nesse momento sai da roda. Gannha o
// último que sobrar.

import java.util.Random;

public class Exe04_filaBatataQuente {
    public static void main(String[] args) {
        Queue<Integer> fila = new Queue<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            fila.enqueue(i);
        }


        int num = getRandomInt(random, 5);

        System.out.println("quantidade de quente: "+ num);

        System.out.print("Batata quente..");
        try {
            while (fila.getSize() > 1) {
                for (int i = 0; i < num; i++) {

                    System.out.print("quente...");
                    Integer playerDeq = fila.dequeue();
                    fila.enqueue(playerDeq);
                    System.out.println("[ " +playerDeq+ " - passou a bola para o "+fila.peek(0)+" ]");
                    Thread.sleep(1000);
                }

                System.out.println("QUEIMOOU "+ fila.dequeue() + "<- ELIMINADO!");

                num = getRandomInt(random, 5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CAMPEÂO: "+fila.dequeue());

    }

    public static int getRandomInt(Random random, int bound) {
        int num = 0;
        while (num == 0) {
            num = random.nextInt(bound);
        }
        return num;
    }
}
