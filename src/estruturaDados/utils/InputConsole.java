package estruturaDados.utils;

import java.util.Scanner;

public class InputConsole {

    private InputConsole(){}

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
}
