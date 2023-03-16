package generics.dominio;

public class CarModel extends VehicleModel implements Comparable{

    public CarModel(String nome) {
        super(nome);
    }

    @Override
    public int compareTo(Object o) {
        return super.getNome().compareTo(((CarModel)o).getNome());
    }
}
