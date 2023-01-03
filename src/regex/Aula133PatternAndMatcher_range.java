package regex;

// Podemos delimitar intervalos de valores possíveis no pattern
// utilizando "[]" <-
// [a-zA-Z0-9]

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aula133PatternAndMatcher_range {
    public static void main(String[] args) {
        // exemplo: regex para filtrar por valores hexadecimais válidos
        // em java para dizermos que um valor é hexadecimal utilizamos "0X" ou "0x" no início,
        // seguido do valor, (com limite de 6 casas)
        String regex = "0[xX][0-9a-fA-F]";
        String texto = "12 0x 0X 0xFFABC 0x109 0x1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        System.out.println("Texto: "+ texto);
        System.out.println("indice: 0123456789");
        System.out.println("regex: "+ regex);
        System.out.println("Posições encontradas: ");

        while(matcher.find()) {
            System.out.println("Índice do primeiro char: "+ matcher.start()+
                    " | Valor encontrado: "+matcher.group());
            // Posições encontradas:
            // Índice do primeiro char: 9 | Valor encontrado: 0xF
            // Índice do primeiro char: 17 | Valor encontrado: 0x1
            // Índice do primeiro char: 23 | Valor encontrado: 0x1

            // Ainda não é oque queremos, pois ele para a busca quando da match na expressão,
            // para retornar o valor completo do hexadecimal precisamos continuar a interação.
        }
    }
}
