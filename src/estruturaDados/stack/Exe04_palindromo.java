package estruturaDados.stack;

public class Exe04_palindromo {
    public static void main(String[] args) {
        System.out.println(isPalindromo("ada"));
    }
    public static boolean isPalindromo(String palavra){
        Stack<Character> stackChar = new Stack<Character>(palavra.length());
        String palavraReverse = "";
        for (int i=0; i<palavra.length(); i++) {
            stackChar.push(palavra.charAt(i));
        }
        for (int i=0; i<palavra.length(); i++) {
            palavraReverse += stackChar.pop();
        }
        System.out.println(palavra+" "+palavraReverse);

        return palavraReverse.equalsIgnoreCase(palavra);
    }
}
