package colections;

// Regras para a implementação do equals em um Object Model:

// Reflexivo: x.equals(x) == true para tudo != null;
// Simétrico: para x e y != null, se x.equals(y) == true logo y.equals(x) também tem que ser true.
// Transitividade: para x, y e z != null, se x.equals(y) && x.equals(z) == true então y.equals(z) tambeḿ tem que ser true.
// Consistente: para todas as chamadas, x.equals(x) sempre retorna true desde que x != null.
// para x != null, x.equals(null) tem que retorna false;

import java.util.Objects;

public class Aula162Equals_pt02_ImplEqualsToObjModelExemple {

    public static void main(String[] args) {
        Smartphone s1 = null;
        Smartphone s2 = null;

        if (s1 == null && s2 == null) { // faz o tratamento de null antes de chamar método equals evita nullPointer
            // objetos são iguais
        } else if (s1 == null || s2 == null) {
            // objetos são diferentes
        } else if (s1.equals(s2)) {
            // objetos são iguais
        } else {
            // objetos são diferentes
        }

       // System.out.println(s1.equals(s2)); // NullPointer

        // Atributos null são válidos:
        s1 = new Smartphone(null, null);
        s2 = new Smartphone(null, null);
        System.out.println(s1.equals(s2)); // true
    }
}

class Smartphone {
    private String serialNumber;
    private String brand; // marca

    public Smartphone(String serialNumber, String brand) {
        this.serialNumber = serialNumber;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) { // Regra para smartphones iguais: ALL atributos ==
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // se classes forem diferentes, então nunca será ==
        Smartphone that = (Smartphone) o;
        return Objects.equals(serialNumber, that.serialNumber) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, brand);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
