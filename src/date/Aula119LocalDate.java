package date;

// Basicamente até aqui existiam duas formas de criar uma instância de Datas:
// new Date();
// Calendar.getInstance();
// Após muitos anos tendo problemas com datas, um BR criou um package API de datas,
// O java gostou de algumas funções e importou criando assim o package java.time
// Diferente de Calendar e Date o LocalDate suporta até +999999999-12-31 e -999999999-01-01
// Introduzida na versão 8 do java, e imutável, boas práticas pedem para utilizar ela!!

// LocalDate é apenas para trabalhar com datas,
// LocalDateTime é para trabalhar com os dois!

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class Aula119LocalDate {
    public static void main(String[] args) {
        System.out.println(new Date()); // toString Wed Dec 07 22:30:43 BRT 2022
        System.out.println(Calendar.getInstance()); // toString java.util.GregorianCalendar[time=1670463043929,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="America/Sao_Paulo",offset=-10800000,dstSavings=0,useDaylight=false,transitions=93,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2022,MONTH=11,WEEK_OF_YEAR=50,WEEK_OF_MONTH=2,DAY_OF_MONTH=7,DAY_OF_YEAR=341,DAY_OF_WEEK=4,DAY_OF_WEEK_IN_MONTH=1,AM_PM=1,HOUR=10,HOUR_OF_DAY=22,MINUTE=30,SECOND=43,MILLISECOND=929,ZONE_OFFSET=-10800000,DST_OFFSET=0]

        LocalDate date = LocalDate.of(2022, Month.DECEMBER, 7);
        System.out.println(date); // 2022-12-07 -> bom para salvar em bd, ja vem no formato correto

        // Métodos utilitários:

        System.out.println(date.getYear()); // 2022
        System.out.println(date.getMonth()); // DECEMBER
        System.out.println(date.getMonthValue()); // 12
        System.out.println(date.getDayOfMonth()); // 7
        System.out.println(date.getDayOfWeek()); // WEDNESDAY
        System.out.println(date.getDayOfYear()); // 341
        System.out.println(date.lengthOfMonth()); // 31 <- ultimo dia do mes de dezembro

        System.out.println(date.isLeapYear()); // false <- é ano bisexto?
        System.out.println(date.get(ChronoField.YEAR)); //  2022
        System.out.println(date.get(ChronoField.DAY_OF_MONTH)); //  7
                                                        // aqui recebe uma das classes que implementam TemporalField
                                                        // para verificar SHIFT + SHIFT e busca pela interface

        LocalDate now = LocalDate.now();
        System.out.println(now); // 2022-12-07

        System.out.println(LocalDate.MAX); // +999999999-12-31
        System.out.println(LocalDate.MIN); // -999999999-01-01

    }
}
