package J_generics.dominio;

public class VehicleModel {
    private String nome;

    public VehicleModel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
