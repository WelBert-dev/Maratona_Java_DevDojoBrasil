package estruturaDados.queue;

public class App {
    public static void main(String[] args) {

        Queue<Integer> fila = new Queue<Integer>();
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.enqueue(40);

        System.out.println(fila);
        System.out.println(fila.isEmpty());
        System.out.println(fila.dequeue());
        System.out.println(fila);
    }
}
