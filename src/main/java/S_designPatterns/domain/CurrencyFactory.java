package S_designPatterns.domain;

// Desacopla as regras de negócio da classe, ou seja, não importa como será criado
// Apenas me forneca a instância correta.
public class CurrencyFactory {
    public static Currency newCurrency(CountryEnum countryEnum) {
        switch (countryEnum)
        {
            case USA: return new UsDollar();
            case BR: return new Real();
            default: throw new IllegalArgumentException("No currency found for this country");
        }
    }
}
