package L_parametrizandoComportamentos;

import L_parametrizandoComportamentos.domain.CarModel;

@FunctionalInterface
public interface CarPredicate {
    public boolean teste(CarModel car);

}
