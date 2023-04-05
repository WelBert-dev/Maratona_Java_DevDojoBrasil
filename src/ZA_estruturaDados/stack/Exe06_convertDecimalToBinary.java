package estruturaDados.stack;

public class Exe06_convertDecimalToBinary {
    public static void main(String[] args) {
        System.out.println(convertDecimalToBinary(3)); // 11
        System.out.println(convertDecimalToBinary(25)); // 11

        System.out.println(convertDecimalToAnywayBase(2000, 8)); // octal
        System.out.println(convertDecimalToAnywayBase(2000, 16)); // hexadecimal
    }

    public static String convertDecimalToBinary(int decimal) {

        Stack<Integer> stack = new Stack<Integer>();
        String numBinary = "";
        int remaining; // resto

        while(decimal > 0) {
            remaining = decimal % 2;
            stack.push(remaining);
            decimal /= 2;
        }

        while(!stack.isEmpty()) {
            numBinary += stack.pop();
        }

        return numBinary;
    }

    public static String convertDecimalToAnywayBase(int decimal, int base) {
        if (base > 16) {
            throw new IllegalArgumentException("Base não suportada, o máximo suportado é hexadecimal (base 16)!");
        }

        Stack<Integer> stack = new Stack<Integer>();
        String numBase = "";
        int remaining; // resto
        final String bases = "0123456789ABCDEF";

        while(decimal > 0) {
            remaining = decimal % base;
            stack.push(remaining);
            decimal /= base;
        }

        while(!stack.isEmpty()) {
            numBase += bases.charAt(stack.pop()); // retorna o caractere do numero correspondente da lista
        }

        return numBase;
    }
}
