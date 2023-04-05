package J_generics.dominio;

public class BoatModel extends VehicleModel implements Comparable{

    public BoatModel(String nome) {
        super(nome);
    }

    @Override
    public int compareTo(Object o) {
        return super.getNome().compareTo(((BoatModel)o).getNome());
    }
}
