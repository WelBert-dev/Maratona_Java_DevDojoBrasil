package estruturaDados.stack;

// loiane groner

import estruturaDados.arrayList.Exe_console_crudContatos;
import estruturaDados.models.BookModel;
import estruturaDados.utils.ClearConsole;
import estruturaDados.utils.InputConsole;

import java.time.LocalDate;
import java.util.Scanner;

public class Exer03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String resp = "";

        boolean isFinalized = false;
        while(!isFinalized) {
            questionHeaderInfo();

            Stack<BookModel> stackBooks = new Stack<BookModel>(20);
            loopingAddBooksInTheStack(stackBooks, scan, resp); // add inicial

            if(!stackBooks.isEmpty()) {
                int option = 1;
                while (option != 0) {
                    option = getOptionSelectedFromMenuInt(scan);

                    switch (option) {
                        case 0:
                            isFinalized = true;
                            break;
                        case 1:
                            option01AddBookInTheLast(scan, stackBooks);
                            break;
                        case 2: // Adiciona livro em alguma posição da pilha. (push with index)
                            option02AddBookWithIndex(scan, stackBooks);
                            break;
                        case 3: // Adiciona livro em alguma posição da pilha. (pop)
                            option03RemoveBook(scan, stackBooks);
                            break;
                        case 4: // Adiciona livro em alguma posição da pilha. (pop)
                            option04GetElement(scan, stackBooks); //option05GetAll
                            break;
                        case 5: // Adiciona livro em alguma posição da pilha. (pop)
                            option05GetAll(scan, stackBooks);
                            break;
                    }
                }
                System.out.println("Usuário digitou 0, Programa finalizado! ;-;\n");
            }
        }
    }
    public static void questionHeaderInfo() {
        Exe_console_crudContatos.getLinha();
        System.out.println("Utilize a classe Pilha(Stack) e desenvolva:\n");

        System.out.println("- 1. crie uma pilha com capacidade para 20 Livros.");

        System.out.println("- 2. Insira 6 Livros nela, cada livro contém:\n Nome, ISBN, Ano de Lançamento, e Autor.");

        System.out.println("- 3. Crie um exemplo para utilizar cada método da classe Stack.\n");
        Exe_console_crudContatos.getLinha();
    }
    public static int getOptionSelectedFromMenuInt(Scanner scan) {

        boolean validInput = false;
        int option = 0;

        while (!validInput) {

            Exe_console_crudContatos.getLinha();
            System.out.println("[ Seleciona alguma das seguintes opções: ]");
            System.out.println("[PRESS 1] - Adiciona livro no topo da pilha. (push)");
            System.out.println("[PRESS 2] - Adiciona livro em alguma posição da pilha. (push with index)");
            System.out.println("[PRESS 3] - Remove livro da pilha retornando-o. (pop)");
            System.out.println("[PRESS 4] - Espia livro da pilha. (peek)");
            System.out.println("[PRESS 5] - Imprimir todos elementos da pilha. (getAll)");

            System.out.print("[PRESS 0] - Finalizar programa.\n R: ");

            try {
                String input = scan.nextLine();
                option = Integer.parseInt(input);

                if (option >= 0 && option <= 5) {
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
    public static void option01AddBookInTheLast(Scanner scan, Stack<BookModel> stackBooks) {
        ClearConsole.clear();

        boolean isFinalized = false;
        Stack<BookModel> newBooks = new Stack<BookModel>();
        BookModel book = new BookModel();

        System.out.println("\n------ [Cadastro de Livro no topo da pilha] -------\n");

        while(!isFinalized) {
            Exe_console_crudContatos.getLinha();

            if (!seedStackWithInputConsole(book, newBooks, scan))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                printElementInConsoleWithStacklist(newBooks); // imprime elementos
                Exe_console_crudContatos.getLinha();

                resp = InputConsole.readInformation("[OK] - Confirmar cadastro.\n[C] - Canselar operção\n R: ", scan);
                while(!resp.isEmpty() && !resp.toUpperCase().equals("OK") && !resp.toUpperCase().equals("C")) {
                    resp = InputConsole.readInformation("\n[Operação INVÀLIDA!! ;-;]\n[OK] - Confirmar cadastro.\n[C] - Cancelar operção\n R: ", scan);
                }
                if (resp.toUpperCase().equals("OK")) {
                    for (int i = 0; i <= newBooks.getSize()-1; i++) {
                        seedBookModelWithParams(book,
                                newBooks.get(i).getNome(),
                                newBooks.get(i).getISBN(),
                                newBooks.get(i).getAnoLancamento(),
                                newBooks.get(i).getAutor());

                        stackBooks.push(book);
                    }
                } else {
                    newBooks.clear();
                }

                Exe_console_crudContatos.getLinha();
                System.out.println(stackBooks);
            }
        }
    }
    public static void option02AddBookWithIndex(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Cadastro de Livros em uma posição especifica] --\n");

        System.out.println("---------------- [Pilha atual: ] ------------------");
        printElementInConsoleWithStacklist(stackBooks);
        Exe_console_crudContatos.getLinha();

        System.out.println("------------------ [Novo livro] -------------------");
        while(!isFinalized) {
            Exe_console_crudContatos.getLinha();

            if (!seedStackWithInputConsole(
                    book,
                    stackBooks,
                    scan,
                    InputConsole.readInformationInt("Índice: ", scan)))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                System.out.println("Pilha de livros nova:");
                printElementInConsoleWithStacklist(stackBooks);
                Exe_console_crudContatos.getLinha();
            }
        }
    }
    //Remove livro do final da pilha retornando-o. (pop)
    public static int getOptionSelectedFromMenuIntOpt3(Scanner scan) {

        boolean validInput = false;
        int option = 0;

        while (!validInput) {

            Exe_console_crudContatos.getLinha();
            System.out.println("[ Seleciona alguma das seguintes operações: ]");
            System.out.println("[PRESS 1] - Remove elemento do final");
            System.out.println("[PRESS 2] - Remove elemento com index");

            System.out.print("[PRESS 0] - Cancelar.\n R: ");

            try {
                String input = scan.nextLine();
                option = Integer.parseInt(input);

                if (option >= 0 && option <= 2) {
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
    public static int getOptionSelectedFromMenuIntOpt4(Scanner scan) {

        boolean validInput = false;
        int option = 0;

        while (!validInput) {

            Exe_console_crudContatos.getLinha();
            System.out.println("[ Seleciona alguma das seguintes operações: ]");
            System.out.println("[PRESS 1] - Obtém elemento do final");
            System.out.println("[PRESS 2] - Obtém elemento com index");

            System.out.print("[PRESS 0] - Cancelar.\n R: ");

            try {
                String input = scan.nextLine();
                option = Integer.parseInt(input);

                if (option >= 0 && option <= 2) {
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
    public static void option03RemoveBook(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Remove livros retornando eles no console] --\n");

        System.out.println("---------------- [Pilha atual: ] ------------------");
        printElementInConsoleWithStacklist(stackBooks);
        Exe_console_crudContatos.getLinha();

        while(!isFinalized && !stackBooks.isEmpty()) {

            if(!stackBooks.isEmpty()) {
                int option = 1;
                while (option != 0) {
                    option = getOptionSelectedFromMenuIntOpt3(scan);

                    switch (option) {
                        case 1:
                            option03RemoveBookInTheLast(scan, stackBooks);
                            break;
                        case 2: // Adiciona livro em alguma posição da pilha. (push with index)
                            option03RemoveBookWithIndex(scan, stackBooks);
                            break;
                    }
                }
                System.out.println("Usuário digitou 0, Operação cancelada!!\n");
            }

            String resp = InputConsole.readInformation("Remover mais? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("* Remover mais? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                System.out.println("Pilha de livros nova:");
                printElementInConsoleWithStacklist(stackBooks);
                Exe_console_crudContatos.getLinha();
            }
        }
    }
    public static void option03RemoveBookInTheLast(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Removendo livros do final da pilha...] --\n");

        while(!isFinalized && !stackBooks.isEmpty()) {
            BookModel element = stackBooks.pop();
            if (element == null)
                System.out.println("Ocorreu alguma falha ao tentar remover, tente novamente! ;-;");

            System.out.println("Elemento removido com sucesso! ;D\nAbaixo informações do mesmo!\n");
            System.out.println(element);

            String resp = InputConsole.readInformation("Remover mais do final? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("* Remover mais do final? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                System.out.println("Pilha de livros nova:");
                printElementInConsoleWithStacklist(stackBooks);
                Exe_console_crudContatos.getLinha();
            }
        }
    }
    public static void option03RemoveBookWithIndex(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Removendo livro do index...] --\n");

        while(!isFinalized && !stackBooks.isEmpty()) {
            int index =  InputConsole.readInformationInt("Índice: ", scan);
            BookModel element = stackBooks.pop(index);
            if (element == null)
                System.out.println("Ocorreu alguma falha ao tentar remover, tente novamente! ;-;");

            System.out.println("Elemento removido com sucesso! ;D\nAbaixo informações do mesmo!\n");
            System.out.println(element);

            String resp = InputConsole.readInformation(
                    "Remover elementos mais do final? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation(
                        "* Remover mais elementos do final? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                System.out.println("Pilha de livros nova:");
                printElementInConsoleWithStacklist(stackBooks);
                Exe_console_crudContatos.getLinha();
            }
        }
    }
    public static void option04GetElement(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Obtendo um livro utilizando Peek] --\n");

        System.out.println("---------------- [Pilha atual: ] ------------------");
        printElementInConsoleWithStacklist(stackBooks);
        Exe_console_crudContatos.getLinha();

        while(!isFinalized && !stackBooks.isEmpty()) {

            if(!stackBooks.isEmpty()) {
                int option = 1;
                while (option != 0) {
                    option = getOptionSelectedFromMenuIntOpt4(scan);

                    switch (option) {
                        case 1:
                            option04GetLastElement(scan, stackBooks);
                            break;
                        case 2: // Adiciona livro em alguma posição da pilha. (push with index)
                            option04GetElementWithIndex(scan, stackBooks);
                            break;
                    }
                }
                System.out.println("Usuário digitou 0, Operação cancelada!!\n");
            }

            String resp = InputConsole.readInformation("Remover mais? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("* Remover mais? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                System.out.println("Pilha de livros nova:");
                printElementInConsoleWithStacklist(stackBooks);
                Exe_console_crudContatos.getLinha();
            }
        }
    }
    public static void option04GetLastElement(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Obtendo livros do final da pilha...] --\n");

        while(!isFinalized && !stackBooks.isEmpty()) {
            BookModel element = stackBooks.peek();
            if (element == null)
                System.out.println("Ocorreu alguma falha ao tentar remover, tente novamente! ;-;");

            System.out.println("Elemento obtido com sucesso! ;D\nAbaixo informações do mesmo!\n");
            System.out.println(element);

            String resp = InputConsole.readInformation("Obter novamente do final? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("* Remover mais do final? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;
            }
        }
    }
    public static void option04GetElementWithIndex(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n-- [Obtendo livros da pilha com index...] --\n");

        while(!isFinalized && !stackBooks.isEmpty()) {
            int index =  InputConsole.readInformationInt("Índice: ", scan);
            BookModel element = stackBooks.peek(index);
            if (element == null)
                System.out.println("Ocorreu alguma falha ao tentar obter elemento, tente novamente! ;-;");

            System.out.println("Elemento obtido com sucesso! ;D\nAbaixo informações do mesmo!\n");
            System.out.println(element);

            String resp = InputConsole.readInformation(
                    "Obter mais elementos com index? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation(
                        "* Obter mais elementos com index? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;
            }
        }
    }
    public static void option05GetAll(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;

        Exe_console_crudContatos.getLinha();
        System.out.println("\n--------- [Obtendo TODOS livros da pilha] ---------\n");

        if (stackBooks.isEmpty()) {
            System.out.println("Pilha vazia, adicione elementos nela e tente novamente!");
            return;
        }

        while (!isFinalized && !stackBooks.isEmpty()) {
            System.out.println("---------------- [Pilha atual: ] ------------------");
            printElementInConsoleWithStacklist(stackBooks);
            Exe_console_crudContatos.getLinha();

            String resp = InputConsole.readInformation("Imprimir novamente? [S] - Sim [N] - Não\n R: ", scan);
            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("* Remover mais? [S] - Sim [N] - Não\n R: ", scan);
            }
            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;
            }
        }
    }
    private static String loopingValidInputIfEmpty(String msg, Scanner scan) {
        String resp = InputConsole.readInformation(msg, scan);
        while (resp.isEmpty()) {
            System.out.println("Informação inválida!");
            resp = InputConsole.readInformation("* " + msg, scan);
        }

        return resp;
    }
    private static void loopingAddBooksInTheStack(Stack<BookModel> stackBooks, Scanner scan, String resp) {
        BookModel book = new BookModel();

        int count = 1;
        boolean isAddBooks = true;
        while (isAddBooks) {
            System.out.println("-------------- [ "+count+"o Livro "+"] ------------------");

            if(!seedStackWithInputConsole(book, stackBooks, scan)) // alimenta a pilha
                System.out.println("Erro ao alimentar a pilha!!! ;-;");

            resp = loopingValidInputIfEmpty("Deseja continuar adicionando livros na pilha?[S] - Sim | [N] - Não\n R:", scan).toUpperCase();
            if (resp.equals("N") || resp.equals("NÃO")) {
                isAddBooks = false;
            }

            count++;
        }
    }
    public static boolean seedStackWithInputConsole(BookModel book, Stack<BookModel> stackBooks, Scanner scan) {
        seedBookModelWithInputConsole(book, scan);

        return stackBooks.push(book);
    }
    public static boolean seedStackWithInputConsole(BookModel book, Stack<BookModel> stackBooks, Scanner scan, int index) {
        seedBookModelWithInputConsole(book, scan);

        return stackBooks.push(book, index);
    }
    public static void seedBookModelWithInputConsole(BookModel book, Scanner scan) {
        book.setNome(loopingValidInputIfEmpty("Nome: ", scan));
        book.setISBN(loopingValidInputIfEmpty("ISBN: ", scan));
        book.setAnoLancamento(LocalDate.of( Integer.parseInt(loopingValidInputIfEmpty("Ano de lançamento [AAAA]: ", scan)), 1, 1 ));
        book.setAutor(loopingValidInputIfEmpty("Autor: ", scan));
    }
    public static void seedBookModelWithParams(BookModel book, String nome, String isbn, LocalDate anoLanc, String autor) {
        book.setNome(nome);
        book.setISBN(isbn);
        book.setAnoLancamento(anoLanc);
        book.setAutor(autor);
    }
    public static void printElementInConsoleWithStacklist(Stack<BookModel> stackBooks){
        for (int i = 0; i <= stackBooks.getSize()-1; i++) {
            System.out.println("------------------- [Livro " + (i + 1) + "] ---------------------");
            System.out.println(stackBooks.get(i));
        }
    }
}
