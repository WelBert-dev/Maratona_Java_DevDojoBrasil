package E_regex;

// Mais informações: https://www.programacaoprogressiva.net/2012/08/java-expressoes-regulares-regex-em-java_2423.html
// Utilizamos quantificadores quando desejamos retornar vários valores de um range "[]"
// pois o default é retornar apenas uma ocorrência (a primeira), quando queremos
// continuar a interação sobre esse range e retornar mais valores utilizamos um
// agrupamento "([])" seguido de um dos operadores descritos abaixo "([])*";

// Operadores quantificador:
// . substitui qualquer caractere
// ? Zero ou uma
// * Zero ou mais
// + Uma ou mais
// {n-min, n-max} de n-min até n-max
// () agrupamento
// | ou/or (exemplo: o(v|c)o ovo ou oco
// $ fim da linha

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aula134PatternAndMatcher_quantificadores {
    public static void main(String[] args) {
        // exemplo: regex para filtrar por valores hexadecimais válidos
        // em java para dizermos que um valor é hexadecimal utilizamos "0X" ou "0x" no início,
        // seguido do valor, (com limite de 6 casas)
        String regex = "0[xX]([0-9a-fA-F])+(\\s|$)";
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
            // Índice do primeiro char: 9 | Valor encontrado: 0xFFABC
            // Índice do primeiro char: 17 | Valor encontrado: 0x109
            // Índice do primeiro char: 23 | Valor encontrado: 0x1

        }
    }
}

class Aula135PatternAndMatcher_quantificadorespt02 {
    public static void main(String[] args) {
        // exemplo: regex para filtrar por emails válidos:

        String regex = "([a-zA-Z0-9\\.-_])+@([a-zA-Z])+(\\.([a-zA-Z])+)+";
        String texto = "wellison.bertelli@hotmail.com, bertelli.wesley@gmail.com, danielle@uoul.com.br, pamonha@gmail";

        // Classes utilitárias:
        // Para Strings é possivel verificar se ela faz match com uma regex:
        System.out.println("$%@hotmail.com".matches(regex));
        System.out.println("wellison@hotmail.com".matches(regex));

        // o método .split() utiliza uma regex:
        System.out.println(Arrays.toString(texto.split(",")));
        System.out.println(texto.split(",")[1].trim());

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
            // Índice do primeiro char: 0 | Valor encontrado: wellison.bertelli@hotmail.com
            // Índice do primeiro char: 31 | Valor encontrado: bertelli.wesley@gmail.com
            // Índice do primeiro char: 58 | Valor encontrado: danielle@uoul.com.br

        }
    }
}