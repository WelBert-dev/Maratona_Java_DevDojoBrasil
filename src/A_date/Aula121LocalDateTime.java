package A_date;

// Como o proprio nome ja diz, é a junção dos LocalDate e LocalTime
// Introduzida no java 8 e imutável!
// Bem mais flexível que as outras classes obsoletas do Java!

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class Aula121LocalDateTime {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 2022-12-07T23:39:20.813999247

        // Possui a junção de todos os métodos presentes nas classes especializadas Date e Time

        // É possivel setar as informações com a junção das duas classes:
        // Obs: A partir de um LocalDate!
        LocalDate date = LocalDate.of(2023, Month.JANUARY, 1);
        LocalTime time = LocalTime.of(0, 0, 1);

        System.out.println(date); // 2023-01-01 <- antes
        System.out.println(date.atTime(time)); // 2023-01-01T00:00:01 <- depois

        LocalDateTime ldt1 = date.atTime(time);
        System.out.println(ldt1); // 2023-01-01T00:00:01

        LocalDateTime ldt2 = time.atDate(date);
        System.out.println(ldt2); // 2023-01-01T00:00:01

    }
}
