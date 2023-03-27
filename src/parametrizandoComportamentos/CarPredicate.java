package parametrizandoComportamentos;

import parametrizandoComportamentos.domain.CarModel;

@FunctionalInterface
public interface CarPredicate {
    public boolean teste(CarModel car);

}
