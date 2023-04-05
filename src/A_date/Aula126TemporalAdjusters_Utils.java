package A_date;

// Classe estática ou seja ela é 100% utilitária.
// Utilizada para ajustes no tempo porisso a nomeclatura.

// diferanças entre with e plusDays:
// with altera sobres-crevendo a data, diferente da plus que faz a adição no tempo
// exemplo: 1999-12-22
// queremos alterar de 12 para 10, então utilizamos o with:

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Aula126TemporalAdjusters_Utils {
    public static void main(String[] args) {
        // alterando o mês sobrescrevendo ao invés de somar e etc...
        LocalDate aniversario = LocalDate.of(1999, 12, 22);
        aniversario = aniversario.with(ChronoField.MONTH_OF_YEAR, 10);
        System.out.println(aniversario); // 1999-10-22 (sobrescreveu o 12 pelo 10)

        // Pegando o próximo dia da semana ou o atual (Exemplo: quando será a próxima terça?)
        // Obs: nextOrSame(Hoje, ou a próxima) ele retorna a de hoje que é terça (se não fosse retornaria a próxima)
        // apenas next() ele retorna a próxima independente de ser hojê.
        LocalDate now = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println(now); // 2022-12-20 (Hoje)

        LocalDate nextWeek = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(nextWeek); // 2022-12-27 (terça que vem)

        // com um dia da semana diferente do de hoje (terça):
        LocalDate now2 = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(now2); // 2022-12-23 (Sexta feira desta semana corrente)

        LocalDate now3 = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(now3); // 2022-12-23 (mesma coisa, pois hoje não é sexta, então ele pega a mais proxima)
                                  // se hoje fosse sexta (2022-12-23) ele retornaria a próxima

        // pega o último dia do mês corrente com Calendar
        System.out.println("calendar:");
        System.out.println(GregorianCalendar.getInstance().getMaximum(Calendar.DAY_OF_MONTH)); // 31

        // Ajusta a data setando o último dia do mês corrente na data corrente (com TemporalAdjusters)
        System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())); // 2022-12-31

        //Ajusta a data setando o primeiro dia do mês corrente (com TemporalAdjusters)
        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())); // 2022-12-01

        //Ajusta a data setando o primeiro ou ultimo dia de um mês X (Basta escolher no .of)
        System.out.println(LocalDate.of(2022, 10, 22)
                .with(TemporalAdjusters.firstDayOfMonth())); // 2022-10-01

        System.out.println(LocalDate.of(2022, 10, 22)
                .with(TemporalAdjusters.lastDayOfMonth())); // 2022-10-31





    }
}
