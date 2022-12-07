package formatacao;

// Utilizado para formatações em geral


import java.text.DateFormat;
import java.util.Calendar;

public class Aula114DateFormat {
    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();

        // Existem 7 maneiras de criar uma instância de DateFormat
        // Todos esses se baseiam na localização do PC corrente.
        DateFormat[] dfArray = new DateFormat[7];
        dfArray[0] = DateFormat.getInstance(); // 07/12/2022 19:00
        dfArray[1] = DateFormat.getDateInstance(); // 7 de dez. de 2022
        dfArray[2] = DateFormat.getDateTimeInstance(); // 7 de dez. de 2022 19:00:36
        dfArray[3] = DateFormat.getDateInstance(DateFormat.SHORT); // 07/12/2022
        dfArray[4] = DateFormat.getDateInstance(DateFormat.MEDIUM); // 7 de dez. de 2022
        dfArray[5] = DateFormat.getDateInstance(DateFormat.LONG); // 7 de dezembro de 2022
        dfArray[6] = DateFormat.getDateInstance(DateFormat.FULL); // quarta-feira, 7 de dezembro de 2022

        // Por fim formata e verifica o resultado:
        for (DateFormat df : dfArray) {
            System.out.println(df.format(c1.getTime())); // pois ele aceita um Date, e o getTime retorna um
        }
    }
}
