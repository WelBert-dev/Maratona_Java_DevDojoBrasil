package S_designPatterns.domain;

public interface Currency {
    String getSymbol();

}
class Real implements Currency {
    @Override
    public String getSymbol() {
        return "R$";
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}

class UsDollar implements Currency {
    @Override
    public String getSymbol() {
        return "U$";
    }
}