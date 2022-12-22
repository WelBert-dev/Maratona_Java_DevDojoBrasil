package date;

// Tudo que é co-relacionado a datas, horas, minutos, segundos e nano segundos
// se utiliza a ChronoUnit que seria tipo a tipagem desses tempos.
// porisso utilizamos ela como parâmetro em classes relacionadas a tempo.
// então ela possuí varios métodos já prontos para trabalhar com elas..
// muito mais simples de se utilizar doque as classes de tempo em sí
// exemplo: periodo entre dias: ChronoUnit.DAY.beetween(tal, tal);

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Aula125ChronoUnit_Utils {
    public static void main(String[] args) {
        LocalDateTime aniversario = LocalDateTime.of(1999, Month.DECEMBER, 22, 12, 0, 0);
        System.out.println(ChronoUnit.YEARS.between(aniversario, LocalDateTime.now())); // 22 anos
        System.out.println(ChronoUnit.DAYS.between(aniversario, LocalDateTime.now())); // 8399 dias
        System.out.println(ChronoUnit.MONTHS.between(aniversario, LocalDateTime.now())); // 275 meses
        System.out.println(ChronoUnit.WEEKS.between(aniversario, LocalDateTime.now())); // 1199 semanas
    }
}
