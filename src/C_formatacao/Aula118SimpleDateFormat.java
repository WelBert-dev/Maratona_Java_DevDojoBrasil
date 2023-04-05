package C_formatacao;

// Formatação de data mais simplificada que o DateFormat
// Utilizando um padrão estilo mascara (yyyy.MM.dd)
// Mais info: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aula118SimpleDateFormat {
    public static void main(String[] args) {
        String pattern = "yyyy.MM.dd G 'ás' HH:mm:ss z";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        System.out.println(sdf.format(new Date())); // 2022.12.07 d.C. ás 22:17:23 BRT


        String pattern2 = "'Brasil' dd 'de' MMMM 'de' yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(pattern2);
        System.out.println(sdf2.format(new Date())); // Brasil 07 de dezembro de 2022

        // Obs: Segue a mesma lógica no metodo parsing, aonde devemos respeitar esse pattern/mascara:

        try {
            System.out.println(sdf2.parse("Brasil 07 de dezembro de 2022")); // Wed Dec 07 00:00:00 BRT 2022

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
