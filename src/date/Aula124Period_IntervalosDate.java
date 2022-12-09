package date;

// Segue a mesma lógica que a Duration, porém com suporte para datas comums
// LocalDate é um dos exemplos suportados por ela (Diferente da Duration que não suporta)
// Obs: os retornos se baseiam em: Ano/Mes/Dia e não retorna SEMANAS!
// Para semanas ele converte em dias!

// Para debugar: breakpoint + SHIFT + F9

// Obs: Ele não retorna a data normalizada não entendi bem,
// mas sei que é relacionado ao .getMonths e etc..
// Para isto temos a ChronoUnit que resolve
// PROCURAR mais sobre!

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class Aula124Period_IntervalosDate {
    public static void main(String[] args) {

        // Agora depois de 2 dias
        LocalDate now = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalDate nowAfter2Days = LocalDate.now(ZoneId.of("America/Sao_Paulo"))
                .plusDays(2);
        Period periodo = Period.between(now, nowAfter2Days);
        System.out.println(periodo); // P2D

        // Agora depois de 2 anos
        LocalDate nowYears = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalDate nowYearsAfter2Years = LocalDate.now(ZoneId.of("America/Sao_Paulo"))
                .plusYears(2);
        Period periodo2 = Period.between(nowYears, nowYearsAfter2Years);
        System.out.println(periodo2); // P2Y

        // Agora depois de 2 anos e 7 dias
        LocalDate now2 = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalDate now2After2YearsAnd7Days = LocalDate.now(ZoneId.of("America/Sao_Paulo"))
                .plusYears(2).plusDays(7);
        Period periodo3 = Period.between(now2, now2After2YearsAnd7Days);
        System.out.println(periodo3); // P2Y7D

        // Convertendo 58 semanas em dias:
        System.out.println(Period.ofWeeks(58)); // P406D

        // Convertendo 58 meses: (Obs ele suporta meses então retornará o obvio)
        System.out.println(Period.ofMonths(58)); // P58M

        // Parte que explica aonde não possui suporte:
        System.out.println(Period.between(LocalDate.now(), LocalDate.now()
                .plusDays(periodo3.getDays())).getMonths()); // 0 <- ERROR, Solução: ChronoUnit
    }
}
