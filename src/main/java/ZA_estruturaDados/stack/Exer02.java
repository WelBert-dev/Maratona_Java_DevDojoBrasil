package ZA_estruturaDados.stack;

import ZA_estruturaDados.arrayList.Exe_console_crudContatos;
import ZA_estruturaDados.utils.InputConsole;

import java.util.Scanner;

public class Exer02 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean isFinalized = false;
        while(!isFinalized) {

            Exe_console_crudContatos.getLinha();
            System.out.println("Escreva um programa que leia 10 números. Para cada número lido,\n" +
                    "Verifique e codifique de acordo com as regras a seguir:\n");

            System.out.println("- Se o número for par, empilhe na pilha par.");

            System.out.println("- Se o número for ímpar, empilhe na pilha impar.");

            System.out.println("- Se o número for zero (0), desempilhe um elemento de cada pilha.\n" +
                    "Caso alguma pilha esteja vazia, mostre uma mensagem de erro na tela.\n");

            System.out.println("Ao final do programa desempilhe todos os elementos de ambas as pilhas,\n" +
                    "imprimindo-os na tela.");
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
