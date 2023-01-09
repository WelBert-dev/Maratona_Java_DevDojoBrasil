package date;

// Classe utilitária geralmente utilizada para pegar inetrvalos entre datas e etc...
// Baseada em segundos e nanoSeconds
// Ou seja precisamos fornecer segundos ou nano segundos,
// Exemplo de classe compativel: LocalDateTime, LocalTime e Instant.
// Para datas "simple" (22/12/1999) utiliza-se a classe Period.
// Possiveis retornos: Horas, minutos e segundos. (podendo ser apenas um deles, ou alguns)
// Mais info: https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html

// A depender da formatação padrão de cada classe utilizada nesse Duration,
// (LocalTime, LocalDateTime) a saída será compatível seguindo a mesma
// Convenção (T, H, M, S)
// Ex. LocalDateTime: PT17544H0.000200349S
// Ex. LocalTime: PT-6H-59M-59.99997496S
// Ex. Instant: PT1M0.000002428S
// NÃO é compatível com a LocalDate!!! pois aqui trabalhamos com Segundos, para ela utiliza-se o Period

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Aula123Duration_Intervalos_Utils {
    public static void main(String[] args) {

        // Analisando diferanças entre anos
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        LocalDateTime nowAfterTwoYears = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusYears(2);

        Duration d1 = Duration.between(now, nowAfterTwoYears);
        System.out.println(d1); // PT17544H0.000200349S (horas, e segundos)

        // Analisando a diferença entre horas/minutos/segundos
        LocalTime nowTime = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        LocalTime timeMinus7Hours = LocalTime.now(ZoneId.of("America/Sao_Paulo")).minusHours(7);

        Duration d2 = Duration.between(nowTime, timeMinus7Hours);
        System.out.println(d2); // PT-6H-59M-59.99997496S


        // Analisando a diferença entre minutos e segundos:
        Duration d3 = Duration.between(Instant.now(), Instant.now().plusSeconds(60));
        System.out.println(d3); // PT1M0.000002428S

//        // Analisando a diferença entre dias: NÂO é compatível Unchecked UnsupportedTemporalTypeException
//        LocalDate nowDate = LocalDate.now();
//        LocalDate datePlus7Days = LocalDate.now().plusDays(7);
//
//        Duration d3 = Duration.between(nowDate, datePlus7Days);
//        System.out.println(d3); // ERROR


        // Possibilita também converter dias em horas e etc:
        System.out.println(Duration.ofDays(20)); // 20 Days == PT480H
        System.out.println(Duration.of(20, ChronoUnit.DAYS));  // mesma coisa que o de cima

        System.out.println(Duration.ofHours(20)); // PT20H
        System.out.println(Duration.of(20, ChronoUnit.HOURS)); // mesma coisa que o de cima

        System.out.println(Duration.ofMinutes(1000)); // PT16H40M
        System.out.println(Duration.of(1000, ChronoUnit.MINUTES)); // mesma coisa que o de cima
    }
}

