package formatacao;

// Trabalhando com zonas e transformando/convertendo em LocalDates e etc:
// ZonedId: Com elá é possível retornar um MAP com as zonas suportadas pelo Java.
// Com Instant é possível realizar as conversões automaticamente (Pois ele trabalha com ZuluTime).
// ZonedDateTime: Com ela é ṕossível vizualizar as diferenças de horarios entre as zonas/locais (mostrando a zoneId tbm)
// ZoneOffSet: é aonde demonstra as diferenças de horários em sí (Emcapsulando em apenas isto), ou seja não demonstra a zoneId
// OffSetDateTime: é a classe que faz o parsing entre as diferenças e ai podemos vizualizar em formato de data.
// Também é possível trabalhar com diferentes calendarios/eras
// como as Eras do Japão.

// Para o horário de brasília temos a seguinte opção: BET=America/Sao_Paulo


import java.time.*;
import java.time.chrono.JapaneseDate;
import java.util.Map;

public class Aula128Zone_Utils {
    public static void main(String[] args) {
        Map<String, String> shortIds = ZoneId.SHORT_IDS;
        System.out.println(shortIds); // abaixo todas as zonas suportadas:
        // {EAT=Africa/Addis_Ababa, EST=-05:00, PNT=America/Phoenix, PLT=Asia/Karachi, CNT=America/St_Johns, IET=America/Indiana/Indianapolis, VST=Asia/Ho_Chi_Minh, JST=Asia/Tokyo, ART=Africa/Cairo, PST=America/Los_Angeles, BET=America/Sao_Paulo, MIT=Pacific/Apia, CAT=Africa/Harare, AGT=America/Argentina/Buenos_Aires, NET=Asia/Yerevan, CST=America/Chicago, IST=Asia/Kolkata, AET=Australia/Sydney, BST=Asia/Dhaka, ACT=Australia/Darwin, HST=-10:00, NST=Pacific/Auckland, AST=America/Anchorage, MST=-07:00, SST=Pacific/Guadalcanal, CTT=Asia/Shanghai, PRT=America/Puerto_Rico, ECT=Europe/Paris}

        System.out.println(ZoneId.systemDefault()); // America/Sao_Paulo

        // pegando a partir de um valor do MAP:
        ZoneId zonedIdOfTokyo = ZoneId.of("Asia/Tokyo");
        System.out.println(zonedIdOfTokyo); // Asia/Tokyo

        // Current sem alteração
        LocalDateTime nowOfAmericaSP = LocalDateTime.now();
        System.out.println(nowOfAmericaSP); // 2022-12-26T19:42:15.690664806

        // passando ela para um LocalDateTime:
        LocalDateTime nowOfAsiaTokyo = LocalDateTime.now(zonedIdOfTokyo);
        System.out.println(nowOfAsiaTokyo); // 2022-12-27T07:42:15.690913888

        // Verificando as diferenças de zona comparando duas:
        ZonedDateTime nowDiferenceByAmericaSPFromAsiaTokyo = nowOfAmericaSP.atZone(zonedIdOfTokyo);
        System.out.println(nowDiferenceByAmericaSPFromAsiaTokyo); // 2022-12-26T19:45:19.447985416+09:00[Asia/Tokyo]
        // Ou seja as diferenças entre SP e Tokyo é de +9 Horas em relação a SP
        // ele menciona algo sobre o Zulu time, então ficou confuso (pesquisar mais sobre).

        // mesma funcionalidade porém com nano segundos:
        Instant nowInstant = Instant.now();
        System.out.println(nowInstant); // 2022-12-26T22:56:11.628306908Z (Z de zulu time)

        // Obs: Com Instant (que trabalha com ZuluTime) ja trás a saída converetida corretamente
        ZonedDateTime zonedDateTimeOfTokyo = nowInstant.atZone(zonedIdOfTokyo);
        System.out.println(zonedDateTimeOfTokyo); // 2022-12-27T07:56:11.628306908+09:00[Asia/Tokyo]
        // a diferença entre ZuluTime e Tokyo é de +9Horas em relação ao Zulu (Já trás formatado corretamente)

        ZonedDateTime zonedDateTimeOfSystemDefault = nowInstant.atZone(ZonedDateTime.now().getZone());
        System.out.println(zonedDateTimeOfSystemDefault); // 2022-12-26T19:56:11.628306908-03:00[America/Sao_Paulo]
        // a diferença entre ZuluTime e São Paulo é de -3Horas em relação ao Zulu (Já trás formatado correntamente)


        // Fazendo a mesma coisa porém de uma forma mais "Fácil" quando não se sabe o ZoneID,
        // porém sabe a diferença entre os horários:

        ZoneOffset ofSetTokyo = ZoneOffset.of("+09:00");
        OffsetDateTime offsetDateTimeDiferenceByAmericaFromTokyo = nowOfAmericaSP.atOffset(ofSetTokyo);
        System.out.println(offsetDateTimeDiferenceByAmericaFromTokyo); // 2022-12-26T20:09:50.062410339+09:00
        // (Em comparação a operação anterior: 2022-12-26T20:09:50.062410339+09:00[Asia/Tokyo])
        // ou seja a unica diferença é o ID/Zone de Tokyo no final "[Asia/Tokyo]"
        // Em resumo o fomato é: Data/Hora/MiliSeconds/OffSet/Zona

        // mesma operação porém de outra forma:
        OffsetDateTime offsetDateTimeDiferenceByAmericaFromTokyo2 = OffsetDateTime.of(nowOfAmericaSP, ofSetTokyo);
        System.out.println(offsetDateTimeDiferenceByAmericaFromTokyo2); // 2022-12-26T20:09:50.062410339+09:00
        // Exatamente a mesma saída do de cima.

        // Com Instante (Aonde já faz a conversão automaticamente):
        OffsetDateTime offsetDateTimeDiferenceByZuluFromAmerica = nowInstant.atOffset(ZonedDateTime.now().getOffset());
        System.out.println(offsetDateTimeDiferenceByZuluFromAmerica); // 2022-12-26T20:23:22.972477392-03:00

        OffsetDateTime offsetDateTimeDiferenceByZuluFromTokyo = nowInstant.atOffset(ofSetTokyo);
        System.out.println(offsetDateTimeDiferenceByZuluFromTokyo); // 2022-12-27T08:26:36.343317784+09:00


        // Trabalhando com as eras (De exemplo a do Japão):
        JapaneseDate japoneseDate = JapaneseDate.from(LocalDate.now());
        System.out.println(japoneseDate); // atual: Japanese Reiwa 4-12-26

        LocalDate meijiEraLocalDate = LocalDate.of(1900, 2, 1);
        JapaneseDate meijiEra = JapaneseDate.from(meijiEraLocalDate);
        System.out.println(meijiEra); // antigamente: Japanese Meiji 33-02-01

    }
}
