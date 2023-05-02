package B_time;

// Obs: Toda fez que temos "Formatter" estamos transformando um objeto em uma String,
// e toda vez que temos um "Parsing" estamos transformando uma string em um objeto.
// Para visualizar os formatos aceitos em ambos, basta ler a documentação apertando ctrl + q na classe/metodo

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Aula129DateTimeFormatter_Utils {

    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();

        // Transformando apartir de um objeto em/para string
        String stringDateBasic = nowDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(stringDateBasic); //20221226

        String stringDateLikeSQL = nowDate.format(DateTimeFormatter.ISO_DATE);
        System.out.println(stringDateLikeSQL); // 2022-12-26 (Também suporta offset 2022-12-26+09:00)

        // O padrão:
        String stringDateDefault = nowDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(stringDateDefault); // 2022-12-26 (ou seja quando chamamos o .toString() do LocalDate é ele)

        // Mesma operação porém com LocalDateTime
        LocalDateTime nowLocalDateTime = LocalDateTime.now();

        String stringDateTimeDefault = nowLocalDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(stringDateTimeDefault); // 2022-12-26T21:25:47.897024374

        // Transformando apartir de uma string em/para algum dos objetos de tempo:
        // Obs: Devemos tomar cuidado com o padrão aceito pelo método, para isto:

        LocalDate parseByIsoBasic = LocalDate.parse("20221226", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(parseByIsoBasic); // 2022-12-26

        LocalDate parseByIsoDateSuportOffSet = LocalDate.parse("2022-12-26+23:59", DateTimeFormatter.ISO_DATE);
        System.out.println(parseByIsoDateSuportOffSet); // 2022-12-26
        // Como o LocalDate n trabalha com Time então ele simplemente trunca o OffSet

        LocalDate parseByIsoDefault= LocalDate.parse("2022-12-26", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(parseByIsoDefault); // 2022-12-26

        LocalDateTime parseByIsoDateTime = LocalDateTime.parse("2022-12-26T21:30:29.165451040", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(parseByIsoDateTime); // 2022-12-26T21:30:29.165451040


        // Caso queremos definir um padrão na unha:
        // Brasil: dd/MM/yyyy
        // Japão: yyyy/MM/dd
        // EUA: MM/dd/yyyy

        DateTimeFormatter formatterPatternOfBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatBR = LocalDate.now().format(formatterPatternOfBR);
        System.out.println(formatBR); // 26/12/2022

        DateTimeFormatter formatterPatternOfJapan = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formatJapan = LocalDate.now().format(formatterPatternOfJapan);
        System.out.println(formatJapan); // 2022/12/26

        DateTimeFormatter formatterPatternOfEUA = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatEUA = LocalDate.now().format(formatterPatternOfEUA);
        System.out.println(formatEUA); // 12/26/2022

        // Agora transformando esse padrão criado por nós em algum dos objetos de tempo:

        System.out.println("pamonha");
        LocalDate parseOfPatternBR = LocalDate.parse("26/12/2022", formatterPatternOfBR);
        System.out.println(parseOfPatternBR); // 2022-12-26

        LocalDate parseOfPatternJapan = LocalDate.parse("2022/12/26", formatterPatternOfJapan);
        System.out.println(parseOfPatternJapan); // 2022-12-26

        LocalDate parseOfPatternEUA = LocalDate.parse("12/26/2022", formatterPatternOfEUA);
        System.out.println(parseOfPatternEUA); // 2022-12-26

        // Definindo um padrão e pegando apartir de um Locale:
        DateTimeFormatter formatterGR = DateTimeFormatter.ofPattern("dd.MMMM.yyyy", Locale.GERMAN);
        String formatGR = LocalDate.now().format(formatterGR);
        System.out.println(formatGR); // 26.Dezember.2022

        LocalDate parseOfCustonPattern = LocalDate.parse("26.Dezember.2022", formatterGR);
        System.out.println(parseOfCustonPattern);
    }


}
