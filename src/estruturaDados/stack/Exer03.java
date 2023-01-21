package estruturaDados.stack;

// loiane groner

import estruturaDados.arrayList.Exe_console_crudContatos;
import estruturaDados.utils.InputConsole;

import java.util.Scanner;

public class Exer03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean isFinalized = false;
        while(!isFinalized) {

            Exe_console_crudContatos.getLinha();
            System.out.println("Utilize a classe Pilha(Stack) e desenvolva:\n");

            System.out.println("- 1. crie uma pilha com capacidade para 20 Livros.");

            System.out.println("- 2. Insira 6 Livros nela, cada livro contém:\n Nome, ISBN, Ano de Lançamento, e Autor.");

            System.out.println("- 3. Crie um exemplo para utilizar cada método da classe Stack.\n");
            Exe_console_crudContatos.getLinha();

            Stack<Integer> stackPar = new Stack<>(10);
            Stack<Integer> stackImpar = new Stack<>(10);

            for (int i = 0; i<10; i++) {

                int num = InputConsole.readInformationInt((i+1) + "o Número: ", scan);

                if (num != -1) {
                    // é uma entrada válida

                    if (num == 0) {
                        if ( stackPar.pop() == null ) {
                            System.out.println("Operação inválida, pilha PAR está vazia!");
                        }
                        if ( stackImpar.pop() == null ){
                            System.out.println("Operação inválida, pilha ÍMPAR está vazia!");
                        }
                    } else if ( num % 2 == 0 ) {
                        stackPar.push(num);
                    } else {
                        stackImpar.push(num);
                    }
                }
            }

            System.out.println("-------------- [ Programa finalizado, a seguir elementos restantes ] ----------------");
            if (!stackPar.isEmpty()) {
                System.out.print("Elementos PARES: ");
                while (!stackPar.isEmpty()) {
                    System.out.print(stackPar.pop() + ", ");
                }
            }

            if (!stackImpar.isEmpty()) {
                System.out.print("Elementos IMPARES: ");
                while (!stackImpar.isEmpty()) {
                    System.out.print(stackImpar.pop() + ", ");
                }
            }

            String resp = InputConsole.readInformation("Deseja continuar a utilizar o programa?[S] - Sim | [N] - Não\n R: ", scan).toUpperCase();

            if (resp.equals("N") || resp.equals("NÃO")) {
                isFinalized = true;
            }
        }
    }
}
