package formatacao;

// Internacionalização:
// Formatações de moedas (de acordo com o Locale passado).
// Obs: Segue a mesma lógica de formatar numeros, porém pega uma instancia diferente no NumberFormat.
// Trocando o NumberFormat.getInstance() por NumberFormat.getCurrencyInstance();

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Aula117NumberFormat_Coin_Locale {
    public static void main(String[] args) {
        Locale localeUS = new Locale("en", "US");
        Locale localeJapones = Locale.JAPAN;
        Locale localeItaliano = Locale.ITALY;

        NumberFormat[] nfArray = new NumberFormat[4];

        nfArray[0] = NumberFormat.getCurrencyInstance(); // retorna a instância baseado no locale default (pt-BR) R$ 10.000,21
        nfArray[1] = NumberFormat.getCurrencyInstance(localeUS); // $10,000.21
        nfArray[2] = NumberFormat.getCurrencyInstance(localeJapones); // ￥10,000
        nfArray[3] = NumberFormat.getCurrencyInstance(localeItaliano); // 10.000,21 €

        // Moeda que será formatada de acordo com o Locale passado na instanciação de NumberFormat
        double coin = 10_000.2130;

        for (NumberFormat n : nfArray) {
            System.out.println(n.format(coin));
            System.out.println(n.getMinimumFractionDigits()); // pega o máximo de casas decimais suportado
            // também é possivel utilizar o set do fraction e limitar o mesmo.
        }

        // Fazendo a formatação, apartir de um valor em String:
        // Obs: Deve-se passar o formato correto e os simbolos também!

        String coinString = "R$ 10.000,21"; // Obs: Não utilizar "_" separador de milhar pois aqui n é válido!

        try { // Exception Checked
            System.out.println(nfArray[0].parse(coinString));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
