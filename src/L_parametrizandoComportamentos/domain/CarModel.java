package L_parametrizandoComportamentos.domain;

public class CarModel {
    private String nome;
    private String brand;
    private String model;
    private String color;

    public CarModel(String nome, String brand, String model, String color) {
        this.nome = nome;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public CarModel() {
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "nome='" + nome + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void printCarInConsoleVOID() {
        System.out.println("CarModel{" +
                "nome='" + nome + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}');
    }
}
