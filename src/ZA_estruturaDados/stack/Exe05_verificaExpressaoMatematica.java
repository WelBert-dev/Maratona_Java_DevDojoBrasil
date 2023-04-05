package estruturaDados.stack;

// Escreva um programa para verificar se uma expressão matematica
// tem os parênteses abertos e fechados de forma correta, isto é:
// 1 - Se o número de parênteses à esquerda e à direita são iguais e
// 2 - Se todos parênteses abertos são seguidos de um fechamento e
// 3 - Não podendo começar um fechado se não abriu primeiro.

// Exemplos:
// As expressões ((A+B) ou A+B( violam a regra 1
// As expressões )A+B(-C ou (A+B))-C +D violam a regra 2
// A expressão ((A+B)+D) está CORRETA!

public class Exe05_verificaExpressaoMatematica {
    public static void main(String[] args) {
        System.out.println(isValidMathExpressionParentheses("((A+B) ou A+B(")); // false
        System.out.println(isValidMathExpressionParentheses(")A+B(-C ou (A+B))-C+D")); // false
        System.out.println(isValidMathExpressionParentheses("((A+B)+D)")); // true
    }
    // ((A+B) ou A+B(
    public static boolean isValidMathExpressionParentheses(String expression) {
        Stack<Character> openAndClosed = new Stack<Character>();
        Stack<Character> open = new Stack<Character>();
        Stack<Character> closed = new Stack<Character>();


        for (int i=0; i<expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                openAndClosed.push(expression.charAt(i));
                open.push(expression.charAt(i));
            } else if (expression.charAt(i) == ')') {
                openAndClosed.pop();
                closed.push(expression.charAt(i));
            }
        }

        return ((openAndClosed.getSize() <= 0 || openAndClosed.isEmpty()) && open.getSize() == closed.getSize());
    }

    final static String OPEN = "([{";
    final static String CLOSED = ")]}";

    public static boolean isValidMathExpressionParenthesesV2(String expression) {
        Stack<Character> pilha = new Stack<Character>();
        int index = 0;
        char symbol, top;

        while (index < expression.length()) {
            symbol = expression.charAt(index);

            if (OPEN.indexOf(symbol) > -1) {
                pilha.push(symbol);
            } else if (CLOSED.indexOf(symbol) > -1) {
                if (pilha.isEmpty()) {
                    return false;
                } else {
                    top = pilha.pop();

                    if (OPEN.indexOf(top) != CLOSED.indexOf(symbol)) {
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
