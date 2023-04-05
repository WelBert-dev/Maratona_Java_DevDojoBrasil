package formatacao;

// Utilizado para Internacionalização, ou seja
// para melhor se adaptar as datas em diversos paises e etc...
// NÂO só para formatação de datas,como também para moedas, números e etc..
// Baseado na localização da JVM do usuário (corrente).
// Baseado na ISO passada no construtor (Basta pesquisar na net as possibilidades).

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Aula115Locale_Internacionalizacao {
    public static void main(String[] args) {
        // Obs: Para pegar essa ISO, o cabeçalho HTTP envia ela.
        Locale localeItaly = new Locale("it", "IT");
        Locale localeSuica = new Locale("it", "CH");
        Locale localeIndia = new Locale("hi", "IN");
        Locale localeJapones = new Locale("ja", "JP");
        Locale localeHolanda= new Locale("nl", "NL");

        // pega a data corrente do pt-BR (07/12/2022)
        //  para depois formatar em outras localizações essa mesma instância.
        Calendar calendar = Calendar.getInstance();


        DateFormat dfItaly = DateFormat.getDateInstance(DateFormat.FULL, localeItaly);
        DateFormat dfSuica = DateFormat.getDateInstance(DateFormat.FULL, localeSuica);
        DateFormat dfIndia = DateFormat.getDateInstance(DateFormat.FULL, localeIndia);
        DateFormat dfJapones = DateFormat.getDateInstance(DateFormat.FULL, localeJapones);
        DateFormat dfHolanda = DateFormat.getDateInstance(DateFormat.FULL, localeHolanda);


        // Formata a data corrente como se estivesse localizado nos paises
        System.out.println("Italia: " + dfItaly.format(calendar.getTime())); // Italia: mercoledì 7 dicembre 2022
        System.out.println("Suiça: " + dfSuica.format(calendar.getTime())); // Suiça: mercoledì, 7 dicembre 2022
        System.out.println("India: " + dfIndia.format(calendar.getTime())); // India: बुधवार, 7 दिसंबर 2022
        System.out.println("Japão: " + dfJapones.format(calendar.getTime())); // Japão: 2022年12月7日水曜日
        System.out.println("Holanda: " + dfHolanda.format(calendar.getTime())); // Holanda: woensdag 7 december 2022


        // Verifica como no sistema operacional corrente, é "traduzido" o nome de cada localização: (pt-BR)
        System.out.println(localeItaly.getDisplayCountry()); // Itália
        System.out.println(localeSuica.getDisplayCountry()); // Suíça
        System.out.println(localeJapones.getDisplayCountry()); // Japão

        // Faz a mesma função anterior, mas se baseia em uma instância de Locale:
        // (Exemplo: como é traduzido "Itália" em Japonês?)
        System.out.println(localeItaly.getDisplayCountry(localeJapones)); // イタリア (Itália em Japonês)
        System.out.println(localeSuica.getDisplayCountry(localeIndia)); // स्विट्ज़रलैंड (Suíça em Indiano)
        System.out.println(localeJapones.getDisplayCountry(localeItaly)); // Giappone (Japonês em Italiano)

        // Faz a mesma funções anterior porém agora como é na LINGUAGEM:
        System.out.println(localeItaly.getDisplayLanguage(localeJapones)); // イタリア語 (Itália em Japonês): ItariaGO
        System.out.println(localeSuica.getDisplayLanguage(localeIndia)); // इतालवी (Suíça em Indiano)
        System.out.println(localeJapones.getDisplayLanguage(localeItaly)); // giapponese (Japonês em Italiano)


        // Para saber como esta configurado o S.O corrente e pegar a ISO:
        // Neste caso, todas as datas e números iram estar no padrão BR
        System.out.println(Locale.getDefault()); // pt_BR

        // Para pegar uma lista das ISOS suportadas tanto para lingua quanto para pais:
        String[] isoCountries = Locale.getISOCountries();
        String[] isoLanguages = Locale.getISOLanguages();

        for (String isoCountry : isoCountries) {
            System.out.print(isoCountry + " ");
        }
        System.out.println();
        for (String isoLanguage : isoLanguages) {
            System.out.print(isoLanguage + " ");
        }
        System.out.println();
    }
}
