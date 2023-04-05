package ZA_estruturaDados.stack;

import ZA_estruturaDados.arrayList.Exe_console_crudContatos;
import ZA_estruturaDados.utils.InputConsole;

import java.util.Scanner;

public class Exer01 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean isFinalized = false;
        while(!isFinalized) {

            Exe_console_crudContatos.getLinha();
            System.out.println("Escreva um programa que leia 10 números. Para cada número lido,\n" +
                    "Verifique e codifique de acordo com as regras a seguir:\n");

            System.out.println("- Se o número for par, empilhe na pilha.");

            System.out.println("- Se o número for ímpar, desempilhe um número da pilha,\n" +
                    "Caso ela estiver vazia, mostre uma mensagem.");

            System.out.println("- Se ao final do programa a pilha não estiver vazia,\n" +
                    "Desempilhe todos os elementos, imprimindo-os na tela.");
            Exe_console_crudContatos.getLinha();

            Stack<Integer> stackList = new Stack<>(10);
            for (int i = 0; i<10; i++) {

                int num = InputConsole.readInformationInt((i+1) + "o Número: ", scan);

                if (num != -1) {
                    // é uma entrada válida

                    if(num % 2 == 0) {
                        stackList.push(num);
                    } else if (stackList.isEmpty()){
                        System.out.println("Pilha vazia, entre com um número par!!");
                    } else {
                        stackList.pop();
                    }
                }
            }

            System.out.println("-------------- [ Programa finalizado, a seguir elementos pares restantes ] ----------------");
            if (!stackList.isEmpty()) {
                while (!stackList.isEmpty()) {
                    System.out.println(stackList.pop());
                }
            }

            String resp = InputConsole.readInformation("Deseja continuar a utilizar o programa?[S] - Sim | [N] - Não\n R: ", scan).toUpperCase();

            if (resp.equals("N") || resp.equals("NÃO")) {
                isFinalized = true;
            }
        }

    }

}
