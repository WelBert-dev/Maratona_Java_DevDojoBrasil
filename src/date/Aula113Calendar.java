package date;

// Ela é do mesmo pacote do Date(); (java.util).
// Ela é uma classe abstrata, ou seja não podemos dar new nela.
// Então para criar objetos devemos utilizar os métodos estáticos presentes nela.
// Isto pois é realizado um calculo de acordo com a origem para assim gerar datas corretas
// de acordo com essa origem.
// Porém também ja esta se tornando obsoleto igual ao Date();
// Também só vai até 1 janeiro de 1970 igual ao Date();

// Classes que implementam ela no java 17:
// BuddhistCalendar
// GregorianCalendar
// JapaneseImperialCalendar

// A saída sem formatação sera algo parecido com isto:
// java.util.GregorianCalendar[time=1670439756293,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="America/Sao_Paulo",offset=-10800000,dstSavings=0,useDaylight=false,transitions=93,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2022,MONTH=11,WEEK_OF_YEAR=50,WEEK_OF_MONTH=2,DAY_OF_MONTH=7,DAY_OF_YEAR=341,DAY_OF_WEEK=4,DAY_OF_WEEK_IN_MONTH=1,AM_PM=1,HOUR=4,HOUR_OF_DAY=16,MINUTE=2,SECOND=36,MILLISECOND=293,ZONE_OFFSET=-10800000,DST_OFFSET=0]

import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class Aula113Calendar {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance(); // pega de acordo com o S.O corrente
        System.out.println("Sem formatação: \n" + c); // a saída será diferente, pois o Calendar trabalha com bem mais informações.

        // tecnica básica para visualizar melhor a saída:

        Date d = c.getTime();
        System.out.println("\nFormatando com uma instância de Date: \n" + d); // Wed Dec 07 16:07:04 BRT 2022

        // Primeiro dia da semana de acordo com a região gerada no getInstance():

        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
            System.out.println("Domingo é o primeiro dia da semana!");
        }

        // De acordo com a instância de calendar corrente (07/12/2022):
        // Dia da semana:
        System.out.println(c.get(Calendar.DAY_OF_WEEK)); // 4 -> quarta
        // Dia do mês:
        System.out.println(c.get(Calendar.DAY_OF_MONTH)); // 7
        // Dia do ano:
        System.out.println(c.get(Calendar.DAY_OF_YEAR)); // 341 -> final do ano
        // Dia da semana no mês
        System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 1 (primeira semana do mês)

        // Acrescentando dias na instância de calendar corrente (07/12/2022):
        c.add(Calendar.DAY_OF_MONTH, 2); // adiciona 2 dias do mês.
        Date dPlus2Days = c.getTime();
        System.out.println(dPlus2Days); // Antes: Wed Dec 07 18:34:14 BRT 2022
                                        // Depois: Fri Dec 09 18:34:14 BRT 2022

        // Acrescentando horas na instância de calendar corrente (07/12/2022):
        // (Lembrando: Depois de adicionar 2 dias na mesma instância corrente)
        c.add(Calendar.HOUR, 2); // adiciona 2 horas
        Date dPlus2hours = c.getTime();
        System.out.println(dPlus2hours); // Antes: Fri Dec 09 18:37:32 BRT 2022
                                         // Depois: Fri Dec 09 20:37:32 BRT 2022
        // Obs: Em caso de passar valores em que a data corrente mude para outro dia,
        // a instância respeita a regra e "vira" o dia/mês/ano
        // Para BURLAR essa regra utilizar o método c.roll()
        // Faz a mesma coisa que o add, porém quando vira o dia/mês/ano ele retorna ao incio do dia corrente



    }
}
