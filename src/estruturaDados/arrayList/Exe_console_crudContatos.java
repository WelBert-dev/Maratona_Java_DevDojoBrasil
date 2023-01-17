package estruturaDados.arrayList;

import estruturaDados.models.ContatoModel;
import estruturaDados.utils.ClearConsole;

import java.util.Scanner;

public class Exe_console_crudContatos {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        VetorGenericoComGenerics<ContatoModel> contatos = new VetorGenericoComGenerics<ContatoModel>(10);

        createContactsDinamic(10, contatos);

        int option = 1;
        while (option != 0) {
            option = getOptionSelectedFromMenuInt(scan);

            switch (option) {
                case 1:
                    option01AddContactInTheLast(scan, contatos);
                    break;
                case 2:
                    option02AddContactToIndex(scan, contatos);
                    break;
            }
        }
        System.out.println("Usuário digitou 0, Programa finalizado! ;-;\n");
    }

    public static void createContactsDinamic(int qtde, VetorGenericoComGenerics<ContatoModel> contatos) {
        ContatoModel contato;
        for (int i = 0; i < qtde; i++) {
            contato = new ContatoModel();
            contato.setNome("Contato" + i);
            contato.setEmail("contato" + i + "@hotmail.com");
            contato.setTelefone("9999-999" + i);

            contatos.add(contato);
        }
    }

    public static int getOptionSelectedFromMenuInt(Scanner scan) {

        boolean validInput = false;
        int option = 0;

        while (!validInput) {

            getLinha();
            System.out.println("[ Seleciona alguma das seguintes opções: ]");
            System.out.println("[PRESS 1] - Adiciona contato no final da lista.");
            System.out.println("[PRESS 2] - Adiciona contato em uma posição específica.");
            System.out.println("[PRESS 3] - Obtém contato de uma posição específica.");
            System.out.println("[PRESS 4] - Consulta contato.");
            System.out.println("[PRESS 5] - Consulta última ocorrência do contato retornando o índice.");
            System.out.println("[PRESS 6] - Verifica se contato existe na lista.");
            System.out.println("[PRESS 7] - Excluir contato por posição/índice(flag) da lista.");
            System.out.println("[PRESS 8] - Excluir contato(flag) da lista.");
            System.out.println("[PRESS 9] - Verifica quantidade de contatos da lista.");
            System.out.println("[PRESS 10] - Limpa os contatos da lista por completo (reset).");
            System.out.println("[PRESS 11] - Imprimir lista.");

            System.out.print("[PRESS 0] - Finalizar programa.\n R: ");

            try {
                String input = scan.nextLine();
                option = Integer.parseInt(input);

                if (option >= 0 && option <= 11) {
                    validInput = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception ex) {
                System.out.println("Entrada Inválida, digite novamente!\n\n");
            }
        }
        return option;
    }

    // opções do menu:

    public static void option01AddContactInTheLast(Scanner scan, VetorGenericoComGenerics<ContatoModel> contatos ) {
        ClearConsole.clear();

        boolean isFinalized = false;
        VetorGenericoComGenerics<ContatoModel> newContacts = new VetorGenericoComGenerics<ContatoModel>(3);

        System.out.println("\n[Cadastro de contato]\n");

        while(!isFinalized) {
            getLinha();

            String name = readInformation("Nome: ", scan);
            String email = readInformation("Email: ", scan);
            String numero = readInformation("Telefone: ", scan);

            if (!newContacts.add(new ContatoModel(name, email, numero)))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);

            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }

            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                getLinha();
                for (int i = 0; i <= newContacts.getSize()-1; i++) {
                    System.out.println("------------ [Contato " + (i+1) + "] --------------");
                    System.out.println("Nome: " + newContacts.getElement(i).getNome());
                    System.out.println("Email: " + newContacts.getElement(i).getEmail());
                    System.out.println("Telefone: " + newContacts.getElement(i).getTelefone());
                }

                getLinha();
                resp = readInformation("[OK] - Confirmar cadastro.\n[C] - Canselar operção\n R: ", scan);
                while(!resp.isEmpty() && !resp.toUpperCase().equals("OK") && !resp.toUpperCase().equals("C")) {
                    resp = readInformation("\n[Operação INVÀLIDA!! ;-;]\n[OK] - Confirmar cadastro.\n[C] - Cancelar operção\n R: ", scan);
                }

                if (resp.toUpperCase().equals("OK")) {
                    ContatoModel contato;
                    for (int i = 0; i <= newContacts.getSize()-1; i++) {
                        contato = new ContatoModel(newContacts.getElement(i).getNome(),
                                newContacts.getElement(i).getEmail(),
                                newContacts.getElement(i).getTelefone());

                        contatos.add(contato);
                    }
                } else {
                    newContacts.clear();
                }

                System.out.println(contatos);
            }
        }
    }

    public static void option02AddContactToIndex(Scanner scan, VetorGenericoComGenerics<ContatoModel> contatos ) {
        boolean isFinalized = false;

        getLinha();
        System.out.println("\n[Cadastro de contato em uma posição especifica]\n");

        System.out.println("------------ [Lista atual: ] --------------");

        for (int i = 0; i <= contatos.getSize()-1; i++) {
            System.out.println("------------ [Contato do índice" + i + "] --------------");
            System.out.println("Nome: " + contatos.getElement(i).getNome());
            System.out.println("Email: " + contatos.getElement(i).getEmail());
            System.out.println("Telefone: " + contatos.getElement(i).getTelefone());
        }

        getLinha();

        System.out.println("-------------- [Novo contato] --------------");

        while(!isFinalized) {
            getLinha();

            String name = readInformation("Nome: ", scan);
            String email = readInformation("Email: ", scan);
            String numero = readInformation("Telefone: ", scan);
            int index = readInformationInt("Índice: ", scan);

            if (!contatos.add(new ContatoModel(name, email, numero), index))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);

            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }

            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                getLinha();

                System.out.println("------------ [Lista nova: ] --------------");
                printContactList(contatos);

                getLinha();
            }
        }
    }

    public static void option03getContactWithIndex(Scanner scan, VetorGenericoComGenerics<ContatoModel> contatos ) {
        boolean isFinalized = false;

        getLinha();
        System.out.println("\n[Obter um contato utilizando um index como flag]\n");

        while(!isFinalized) {
            getLinha();

            String resp = readInformation("Qual índice utilizar na consulta?\n R: ", scan);

            try {
                ContatoModel contato = contatos.getElement(Integer.parseInt(resp));
                System.out.println("[Contato encontrado, segue informações:]");
                printAtributesAndValuesFromContact(contato);

                resp = readInformation("\n R:", scan);

            } catch(Exception ex) {
                System.out.println("Elemento não encontrado, tente outro índice!! ;-;");
            }

            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                getLinha();
                System.out.println("------------ [Lista nova: ] --------------");
                getLinha();

                printContactList(contatos);
            }
        }
    }

    public static String readInformation(String msg, Scanner scan) {
        System.out.print(msg);
        return scan.nextLine();
    }

    public static int readInformationInt(String msg, Scanner scan) {
        System.out.print(msg);
        int resp = -1;
        boolean isValid = false;

        while(!isValid) {
            try {
                System.out.print(msg);
                resp = Integer.parseInt(scan.nextLine());
                isValid = true;
            } catch (Exception ex) {
                System.out.println("Entrava inválida!!");
            }
        }

        return resp;
    }

    public static void printContactList(VetorGenericoComGenerics<ContatoModel> contatos) {
        for (int i = 0; i <= contatos.getSize()-1; i++) {
            System.out.println("------------ [Contato do índice" + i + "] --------------");
            printAtributesAndValuesFromContact(contatos.getElement(i));
        }
    }

    public static void printAtributesAndValuesFromContact(ContatoModel contato) {
        System.out.println("Nome: " + contato.getNome());
        System.out.println("Email: " + contato.getEmail());
        System.out.println("Telefone: " + contato.getTelefone());
    }

    public static void getLinha() {
        System.out.println("---------------------------------------------------");
    }
}
