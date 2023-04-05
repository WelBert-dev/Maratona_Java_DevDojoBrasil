package formatacao;

// TemporalAdjuster é uma interface que podemos passar para classes
// afím de ajustar o tempo, sendo assim é possível implementarmos ela
// e adicionar lógicas personalizadas (Polimorfismo)
// e utiliza-las em classes que tem como parâmetro TemporalAdjuster.

// Neste exercicio iremos utilizar a classe .with()

// Objetivo: Retornar o próximo dia util (em uma semana que só vai até quinta)
// ou seja, se hoje for quinta/sexta/sabado/domingo, o próximo dia útil será segunda.
// para outros dias (segunda/terça/quarta) o próximo dia útil será o correspondente (Razão 1).

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

class GetLastUtilDayWithTemporalAdjuster implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek currentDayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int addDays;
        switch (currentDayOfWeek){
            case THURSDAY: addDays = 4; break;
            case FRIDAY: addDays = 3; break;
            case SATURDAY: addDays = 2; break;
            default: addDays = 1; break;
        }

        return temporal.plus(addDays, ChronoUnit.DAYS);
    }
}

public class Aula127TemporalAdjuster_Impl_ExeComWith {
    public static void main(String[] args) {

        // OBS: Não altera em memória, apenas no retorno!!
        // Ou seja continua imutável!!!

        // Próximo dia útil de segunda
        LocalDate segunda = LocalDate.of(2022, Month.DECEMBER, 19);
        System.out.println("FROM " + segunda.getDayOfWeek() + " next util day is: " +
                segunda.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                segunda.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM MONDAY next util day is: 2022-12-20 (TUESDAY)


        // Próximo dia útil de terça
        LocalDate terca = LocalDate.of(2022, Month.DECEMBER, 20);
        System.out.println("FROM " + terca.getDayOfWeek() + " next util day is: " +
                terca.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                terca.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM TUESDAY next util day is: 2022-12-21 (WEDNESDAY)


        // Próximo dia útil de quarta
        LocalDate quarta = LocalDate.of(2022, Month.DECEMBER, 21);
        System.out.println("FROM " + quarta.getDayOfWeek() + " next util day is: " +
                quarta.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                quarta.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM WEDNESDAY next util day is: 2022-12-22 (THURSDAY)


        // Próximo dia útil de quinta
        LocalDate quinta = LocalDate.of(2022, Month.DECEMBER, 22);
        System.out.println("FROM " + quinta.getDayOfWeek() + " next util day is: " +
                quinta.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                quinta.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM THURSDAY next util day is: 2022-12-26 (MONDAY)


        // Próximo dia útil de sexta
        LocalDate sexta = LocalDate.of(2022, Month.DECEMBER, 23);
        System.out.println("FROM " + sexta.getDayOfWeek() + " next util day is: " +
                sexta.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                sexta.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM FRIDAY next util day is: 2022-12-26 (MONDAY)


        // Próximo dia útil de sabado
        LocalDate sabado = LocalDate.of(2022, Month.DECEMBER, 24);
        System.out.println("FROM " + sabado.getDayOfWeek() + " next util day is: " +
                sabado.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                sabado.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM SATURDAY next util day is: 2022-12-26 (MONDAY)


        // Próximo dia útil de domingo
        LocalDate domingo = LocalDate.of(2022, Month.DECEMBER, 25);
        System.out.println("FROM " + domingo.getDayOfWeek() + " next util day is: " +
                domingo.with(new GetLastUtilDayWithTemporalAdjuster()) + " (" +
                domingo.with(new GetLastUtilDayWithTemporalAdjuster()).getDayOfWeek() + ")");
        // FROM SUNDAY next util day is: 2022-12-26 (MONDAY)
    }
}
