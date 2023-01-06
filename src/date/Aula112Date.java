package date;

// A classe Date sofreu algumas mudanças ao longo do tempo
// pois de inicio não supria 100% das necessidades,
// então o java creiou outras classes com o mesmo contexto
// para tentar suprir essa carencia
// Cronograma:
// Date -> Calendar -> Pacote inteiro para lidar com datas.

// Date: Muitos sistemas legados ainda utilizam ela (java.util):
// O Date armazena um Long que representa os miliseconds desde 1970
// Obs: Problemas com a internacionalidade, em cada pais/cidade o Horario é diferente
// Esse foi um dos motivos por essa classe estar sendo descontinuada.
// a depender da linguagem do sistema operacional, a saida será diferente.

// Por debaixo dos panos o println (toString) converte esse miliseconds em uma data compreensível.


import java.text.DateFormat;
import java.util.Date;

public class Aula112Date {
    public static void main(String[] args) {
        Date date = new Date(1_000_000_000); // Mon Jan 12 10:46:40 BRT 1970 <- no pc do wesy
        System.out.println("Long 1Milion seconds: " + date);

        // Para pegar os miliseconds que representam esse date:
        System.out.println("Representação do date em miliseconds: " + date.getTime()); // 1000000000

        // Sem passar nada ele pega a data corrente do S.O
        Date now = new Date();
        DateFormat df = DateFormat.getInstance();

        System.out.println("Agora é: " + now); // date corrente do S.O em RunTime
        System.out.println("Valor máximo de um Date: " + df.format(new Date(Long.MAX_VALUE)));

    }
}
