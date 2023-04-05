package scanner;

// A Classe scanner é bem util quando queremos percorrer uma lista de diferentes tipos primitivos
// e desejamos receber em variáveis do tipo correto, sem ser apenas Strings que seria o "padrão"
// para isto podemos utilizar a classe Scanner

import java.util.Scanner;

public class Aula137Scanner_Utils {
    public static void main(String[] args) {
        // Sem utilizar Scanner:
        String texto = "Levi, Eren, Mikasa, true, 1200";
        String[] nomes = texto.split(",");
        for (String nome : nomes) {
            System.out.println(nome.trim()); // aqui todos seram do tipo String
        }

        // Utilizando Scanner:

        Scanner scanner = new Scanner(texto); // aqui ele percorre a lista e faz um split() por padrão nos " ";
        scanner.useDelimiter(", "); // aqui mudamos o comportamento padrão e utilizamos "," no split();
        while(scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                int inteiro = scanner.nextInt();
                System.out.println("Int: "+inteiro);
            } else if (scanner.hasNextBoolean()) {
                boolean boleano = scanner.nextBoolean();
                System.out.println("Boolean: "+boleano);
            } else  {
                System.out.println(scanner.next());
            }
        }
    }
}
