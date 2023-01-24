package estruturaDados.stack;

// loiane groner

import estruturaDados.arrayList.Exe_console_crudContatos;
import estruturaDados.arrayList.VetorGenericoComGenerics;
import estruturaDados.models.BookModel;
import estruturaDados.models.ContatoModel;
import estruturaDados.utils.ClearConsole;
import estruturaDados.utils.InputConsole;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Exer03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String resp = "";

        boolean isFinalized = false;
        while(!isFinalized) {

            Exe_console_crudContatos.getLinha();
            System.out.println("Utilize a classe Pilha(Stack) e desenvolva:\n");

            System.out.println("- 1. crie uma pilha com capacidade para 20 Livros.");

            System.out.println("- 2. Insira 6 Livros nela, cada livro contém:\n Nome, ISBN, Ano de Lançamento, e Autor.");

            System.out.println("- 3. Crie um exemplo para utilizar cada método da classe Stack.\n");
            Exe_console_crudContatos.getLinha();

            Stack<BookModel> stackBooks = new Stack<BookModel>(20);
            loopingAddBooksInTheStack(stackBooks, scan, resp);

            if(!stackBooks.isEmpty()) {
                int option = 1;
                while (option != 0) {
                    option = getOptionSelectedFromMenuInt(scan);

                    switch (option) {
                        case 1:
                            option01AddBookInTheLast(scan, stackBooks);
                            break;
                        case 2: // Adiciona livro em alguma posição da pilha. (push with index)
                            option02AddBookToIndex(scan, stackBooks);
                            break;
                    }
                }
                System.out.println("Usuário digitou 0, Programa finalizado! ;-;\n");
            }
            resp = InputConsole.readInformation("Deseja continuar a utilizar o programa?[S] - Sim | [N] - Não\n R: ", scan).toUpperCase();

            if (resp.equals("N") || resp.equals("NÃO")) {
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

            book.setNome(loopingValidInputIfEmpty("Nome: ", scan));
            book.setISBN(loopingValidInputIfEmpty("ISBN: ", scan));
            book.setAnoLancamento(LocalDate.of( Integer.parseInt(loopingValidInputIfEmpty("Ano de lançamento [AAAA]: ", scan)), 1, 1 ));
            book.setAutor(loopingValidInputIfEmpty("Autor: ", scan));

            stackBooks.push(book);

            resp = loopingValidInputIfEmpty("Deseja continuar adicionando livros na pilha?[S] - Sim | [N] - Não\n R:", scan).toUpperCase();
            if (resp.equals("N") || resp.equals("NÃO")) {
                isAddBooks = false;
            }

            count++;
        }
    }

    public static int getOptionSelectedFromMenuInt(Scanner scan) {

        boolean validInput = false;
        int option = 0;

        while (!validInput) {

            Exe_console_crudContatos.getLinha();
            System.out.println("[ Seleciona alguma das seguintes opções: ]");
            System.out.println("[PRESS 1] - Adiciona livro no topo da pilha. (push)");
            System.out.println("[PRESS 2] - Adiciona livro em alguma posição da pilha. (push with index)");
            System.out.println("[PRESS 3] - Remove livro do final da pilha retornando-o. (pop)");
            System.out.println("[PRESS 4] - Espia ultimo livro da pilha. (peek)");
            System.out.println("[PRESS 5] - Imprimir todos elementos da pilha. (getAll)");

            System.out.print("[PRESS 0] - Finalizar programa.\n R: ");

            try {
                String input = scan.nextLine();
                option = Integer.parseInt(input);

                if (option >= 0 && option <= 4) {
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

        System.out.println("\n[Cadastro de Livro]\n");

        while(!isFinalized) {
            Exe_console_crudContatos.getLinha();


            book.setNome(loopingValidInputIfEmpty("Nome: ", scan));
            book.setISBN(loopingValidInputIfEmpty("ISBN: ", scan));
            book.setAnoLancamento(LocalDate.of( Integer.parseInt(loopingValidInputIfEmpty("Ano de lançamento [AAAA]: ", scan)), 1, 1 ));
            book.setAutor(loopingValidInputIfEmpty("Autor: ", scan));

            if (!newBooks.push(book))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);

            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }

            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();
                for (int i = 0; i <= newBooks.getSize()-1; i++) {
                    System.out.println("------------ [Livro " + (i+1) + "] --------------");
                    System.out.println("Nome: " + newBooks.get(i).getNome());
                    System.out.println("ISBN: " + newBooks.get(i).getISBN());
                    System.out.println("Ano de lançamento: " +
                            DateFormat.getDateInstance(DateFormat.SHORT).format(newBooks.get(i).getAnoLancamento()));
                    System.out.println("Autor: " + newBooks.get(i).getAutor());
                }

                Exe_console_crudContatos.getLinha();
                resp = InputConsole.readInformation("[OK] - Confirmar cadastro.\n[C] - Canselar operção\n R: ", scan);
                while(!resp.isEmpty() && !resp.toUpperCase().equals("OK") && !resp.toUpperCase().equals("C")) {
                    resp = InputConsole.readInformation("\n[Operação INVÀLIDA!! ;-;]\n[OK] - Confirmar cadastro.\n[C] - Cancelar operção\n R: ", scan);
                }

                if (resp.toUpperCase().equals("OK")) {
                    for (int i = 0; i <= newBooks.getSize()-1; i++) {
                        book.setNome(newBooks.get(i).getNome());
                        book.setISBN(newBooks.get(i).getISBN());
                        book.setAnoLancamento(newBooks.get(i).getAnoLancamento());
                        book.setAutor(newBooks.get(i).getAutor());

                        stackBooks.push(book);
                    }
                } else {
                    newBooks.clear();
                }

                System.out.println(stackBooks);
            }
        }
    }

    public static void option02AddBookToIndex(Scanner scan, Stack<BookModel> stackBooks){
        boolean isFinalized = false;
        BookModel book = new BookModel();

        Exe_console_crudContatos.getLinha();
        System.out.println("\n[Cadastro de Livros em uma posição especifica]\n");

        System.out.println("------------ [Pilha atual: ] --------------");

        for (int i = 0; i <= stackBooks.getSize()-1; i++) {
            System.out.println("------------ [Livro do índice " + i + "] --------------");
            System.out.println("Nome: " + stackBooks.get(i).getNome());
            System.out.println("ISBN: " + stackBooks.get(i).getISBN());
            System.out.println("Ano de lançamento: " +
                    DateFormat.getDateInstance(DateFormat.SHORT).format(stackBooks.get(i).getAnoLancamento()));
            System.out.println("Autor: " + stackBooks.get(i).getAutor());
        }

        Exe_console_crudContatos.getLinha();

        System.out.println("-------------- [Novo livro] --------------");

        while(!isFinalized) {
            Exe_console_crudContatos.getLinha();

            book.setNome(loopingValidInputIfEmpty("Nome: ", scan));
            book.setISBN(loopingValidInputIfEmpty("ISBN: ", scan));
            book.setAnoLancamento(LocalDate.of(
                    Integer.parseInt(loopingValidInputIfEmpty("Ano de lançamento [AAAA]: ", scan)), 1, 1 ));
            book.setAutor(loopingValidInputIfEmpty("Autor: ", scan));

            int index = InputConsole.readInformationInt("Índice: ", scan);

            if (!stackBooks.push(book, index))
                System.out.println("Ocorreu um erro ao tentar adicionar, deseja continuar operações?");

            String resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);

            while(!resp.isEmpty() && !resp.toUpperCase().equals("S") && !resp.toUpperCase().equals("N") && !resp.toUpperCase().equals("NÃO") && !resp.toUpperCase().equals("SIM")) {
                resp = InputConsole.readInformation("Adicionar mais? [S] - Sim [N] - Não\n R: ", scan);
            }

            if (resp.toUpperCase().equals("N") || resp.toUpperCase().equals("NÃO")) {
                isFinalized = true;

                Exe_console_crudContatos.getLinha();

                System.out.println("Pilha de livros nova:");
                for (int i = 0; i <= stackBooks.getSize()-1; i++) {
                    System.out.println("------------ [Livro do índice" + i + "] --------------");
                    System.out.println("Nome: " + stackBooks.get(i).getNome());
                    System.out.println("ISBN: " + stackBooks.get(i).getISBN());
                    System.out.println("Ano de lançamento: " +
                            DateFormat.getDateInstance(DateFormat.SHORT).format(stackBooks.get(i).getAnoLancamento()));
                    System.out.println("Autor: " + stackBooks.get(i).getAutor());
                }

                Exe_console_crudContatos.getLinha();
            }
        }
    }
}
