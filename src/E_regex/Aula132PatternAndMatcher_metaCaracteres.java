package E_regex;

// Os meta caracteres são os caracteres especiais de configuração
// para o pattern, existem inumeros e aqui estão os mais utilizados
// \d: Todos os digítos.
// \D: Ao contrário, ou seja retorna tudo que NÂO for digito.
// \s: Todos os espaços em branco (\t, \n, \f, \r).
// \S: Ao contrário, ou seja retorna tudo que NÂO for espaço em branco.
// \w: Todas as letras (a-z e A-Z), digítos, e _ (Ou seja, exclui todos caracteres especiais menos o "_").
// \W Ao contrário, ou seja retorna tudo que NÂO for letras, digitos e "_".

// Operadores quantificador:
// ? Zero ou uma
// * Zero ou mais
// + Uma ou mais
// {n-min, n-max} de n-min até n-max
// () agrupamento
// | ou/or (exemplo: o(v|c)o ovo ou oco
// $ fim da linha

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aula132PatternAndMatcher_metaCaracteres {

    public static void main(String[] args) {
        // Trabalhando com digítos:

        String regex = "\\d";
        String texto = "1kaka123kaka1";
        Pattern pattern = Pattern.compile(regex); // verifica se a expressão é válida (se ela compila).
        Matcher matcher = pattern.matcher(texto); // verifica se ocorre algum match com a string (percorre por completo).
        System.out.println("Texto: "+ texto);
        System.out.println("indice: 0123456789");
        System.out.println("regex: "+ regex);
        System.out.println("Posições encontradas: ");

        while(matcher.find()) {
            // .group() retorna o grupo de valores encontrados ou seja o valor que deu match em sí
            System.out.println("Índice do primeiro char: "+ matcher.start()+
                    " | Valor encontrado: "+matcher.group()); //
            // Posições encontradas: (texto: 1kaka123kaka1) regex: \\d (digitos)
            //                               0XXXX567XXXX12
            // Índice do primeiro char: 0 | Valor encontrado: 1
            // Índice do primeiro char: 5 | Valor encontrado: 1
            // Índice do primeiro char: 6 | Valor encontrado: 2
            // Índice do primeiro char: 7 | Valor encontrado: 3
            // Índice do primeiro char: 12 | Valor encontrado: 1
        }

        // Retornando tudo que NÂO for digito:

        regex = "\\D";
        texto = "1kaka123kaka1"; // mesma string anterior

        pattern = Pattern.compile(regex); // verifica se a expressão é válida (se ela compila).
        matcher = pattern.matcher(texto); // verifica se ocorre algum match com a string (percorre por completo).
        System.out.println("Texto: "+ texto);
        System.out.println("indice: 0123456789");
        System.out.println("regex: "+ regex);
        System.out.println("Posições encontradas: ");

        while(matcher.find()) {
            System.out.println("Índice do primeiro char: "+ matcher.start()+
                    " | Valor encontrado: "+matcher.group()); //
            // Posições encontradas: (texto: 1kaka123kak,a,1) regex: \\D (All menos digitos)
            //                               X1234XXX8910,11,X
            // Índice do primeiro char: 1 | Valor encontrado: k
            // Índice do primeiro char: 2 | Valor encontrado: a
            // Índice do primeiro char: 3 | Valor encontrado: k
            // Índice do primeiro char: 4 | Valor encontrado: a
            // Índice do primeiro char: 8 | Valor encontrado: k
            // Índice do primeiro char: 9 | Valor encontrado: a
            // Índice do primeiro char: 10 | Valor encontrado: k
            // Índice do primeiro char: 11 | Valor encontrado: a
        }
    }
}
