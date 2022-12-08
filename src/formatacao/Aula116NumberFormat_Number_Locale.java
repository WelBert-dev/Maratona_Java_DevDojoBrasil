package formatacao;

// Internacionalização:
// Formatações de números (de acordo com o Locale passado).
// Obs: Caso alguns métodos fiquem em vermelho é porque esta disponível
// Porem devemos configurar a JDK para expandir o languageLevel suportado.

import java.text.NumberFormat;
import java.util.Locale;

public class Aula116NumberFormat_Number_Locale {
    public static void main(String[] args) {
        Locale localeUS = new Locale("en", "US");
        Locale localeJapones = Locale.JAPAN;
        Locale localeItaliano = Locale.ITALY;

        NumberFormat[] nfArray = new NumberFormat[4];

        nfArray[0] = NumberFormat.getInstance(); // retorna a instância baseado no locale default (pt-BR)
        nfArray[1] = NumberFormat.getInstance(localeUS); // 10,000.213
        nfArray[2] = NumberFormat.getInstance(localeJapones); // 10,000.213
        nfArray[3] = NumberFormat.getInstance(localeItaliano); // 10.000,213

        // Valor que será formatado de acordo com os locale e numberformart (Para núemros comuns) != moedas
        double valor = 10_000.2130;

        for (NumberFormat n : nfArray) {
            System.out.println(n.format(valor));
        }

    }
}
