package ZA_estruturaDados.utils;

public class ClearConsole {

    public static void clear(){

        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
                System.out.println("Entrou no if");

            }else{
                Runtime.getRuntime().exec("clear");
                System.out.println("Entrou no else");
            }
        }
        catch (final Exception e){
            //  Tratar Exceptions
        }
    }
}
