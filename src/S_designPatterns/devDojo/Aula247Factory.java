package S_designPatterns.devDojo;

// Desacopla as regras de negócio da classe, ou seja, não importa como será criado
// Apenas me forneca a instância correta.

import S_designPatterns.domain.CountryEnum;
import S_designPatterns.domain.Currency;
import S_designPatterns.domain.CurrencyFactory;

public class Aula247Factory {
    public static void main(String[] args) {
        Currency brCurrency = CurrencyFactory.newCurrency(CountryEnum.BR);
        System.out.println(brCurrency.getSymbol());

    }
}
