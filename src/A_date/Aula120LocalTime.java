package date;

// Classe simplificada criada quando se quer trabalhar apenas com tempo, desconsiderando datas.
// Introduzida no java 8 em conjunto de LocalDate, e LocalDateTime
// Utilizar ela como boas praticas!

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class Aula120LocalTime {
    public static void main(String[] args) {
        LocalTime time = LocalTime.of(23, 33, 12);
        System.out.println(time); // 23:33:12

        LocalTime now = LocalTime.now();
        System.out.println(now); // 23:26:56.980298963

        System.out.println(now.getHour()); // 23
        System.out.println(now.getMinute()); // 26
        System.out.println(now.getSecond()); // 56
        System.out.println(now.getNano()); // 980298963

        System.out.println(now.get(ChronoField.CLOCK_HOUR_OF_AMPM)); // 11
        System.out.println(LocalTime.MAX); // 23:59:59.999999999
        System.out.println(LocalTime.MIN); // 00:00
    }
}
