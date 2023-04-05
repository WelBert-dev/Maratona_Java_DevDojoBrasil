package E_regex;

// Existem 2 classes que são utilizadas em conjuntos
// quando trabalhamos com regex no java sendo elas:
// Pattern: Padrão (Define qual padrão queremos encontrar no texto/string
// Matcher: Faz o match e encontra o padraõ definido anteriormente,
// para isto ele percorre a string buscando pelo padrão, a cada ocorrência
// ele toma uma decisão se utilizado um while.


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aula131PatternAndMatcher {
    public static void main(String[] args) {
        String regex = "ab";
        String texto = "abaaba";
        Pattern pattern = Pattern.compile(regex); // verifica se a expressão é válida (se ela compila).
        Matcher matcher = pattern.matcher(texto); // verifica se ocorre algum match com a string (percorre por completo).
        System.out.println("Texto: "+ texto);
        System.out.println("indice: 0123456789");
        System.out.println("regex: "+ regex);
        System.out.print("Posições encontradas: ");

        while(matcher.find()) {
            // start retorna a primeira posição referênte ao primeiro char do padrão buscado no texto
            // exemplo: abaaba (ab estão nas posições: 0, 3 pois é aonde inicia a flag/padrão buscado).
            System.out.print(matcher.start()+" "); // Posições encontradas: 0 3
        }
        System.out.println();

        // Detalhe sobre o comportamento do matcher:
        // Ao utilizar o método .start() por padrão, ele percorre a lista de elementos
        // e ao dar match com o padrão definido ele retorna e continua apartir dali,
        // ou seja se temos um padrão "aba", e realizamos a busca na string "abababa"
        // ele irá considerar os retornos: 0 4, pois ele descarta o aba do meio (pois ja fez match)
        // com a primeira ocorrência:
        // abababa
         
         

        regex = "aba";
        texto = "abababa";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(texto);
        System.out.println("Texto: "+ texto);
        System.out.println("indice: 0123456789");
        System.out.println("regex: "+ regex);
        System.out.print("Posições encontradas: ");
        while(matcher.find()) {
            System.out.print(matcher.start()+" "); // Posições encontradas: 0 4
        }
        System.out.println();
    }
}
