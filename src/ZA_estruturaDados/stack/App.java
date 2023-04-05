package ZA_estruturaDados.stack;

// LIFO: Last in First out (Ultimo a entrar é o primeiro a sair)
public class App {
    public static void main(String[] args) {
        Stack<String> stackList = new Stack<String>();

        System.out.println(stackList.isEmpty());
        stackList.push("pamonha"); // 0
        System.out.println(stackList.isEmpty());
        stackList.push("pamonha"); // 1
        stackList.push("pamonha"); // 2
        stackList.push("boli"); // 3
        System.out.println(stackList.isEmpty());
        System.out.println(stackList);
        System.out.println(stackList.peek());
        //stackList.push("boli", 3);
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList.pop());
        System.out.println(stackList);
    }
}
