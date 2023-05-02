package L_parametrizandoComportamentos;

// Neste package é discutido do porque utilizar lambdas
// programação funcional torna o código mais coeso e
// facil de entender e dar manuteção.

// Aqui seram realizados métodos de filtragem aonde o critério
// sempre é alterado, porisso utilizamos a parametrização
// de funções, para evitar replicar códigos que realizão a
// mesma função, com critérios simples diferentes:

// Exemplo:
// - Filtrar carros por cor `filterCarByColor()`
// - Filtrar carros por marca `filterCarByBrand()`
// - Filtrar carros por cor e marca `filterCarByColorAndBrand()`

// Ambos fazem a mesma coisa, porém com pequenas alterações,
// para isso criamos apenas um método "filter" e nele
// passamos uma função que se adequa ao cenário de filtragem.
// Assim criamos apenas um método:

// Com Interface Predicate: O Java ja implementa com Generics!
// - Filtrar carros por parametrização `filter(c -> c.getBrand...)`



import L_parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;

public class Aula193e194parametrizandoComportamentos_lambdas_pt01ept02 {
    public static void main(String[] args) {
        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        // Sem uso de parametrização, REPLICANDO código:
        System.out.println(filterCarByBrand(listCars, "Audi"));
        System.out.println(filterCarByColor(listCars, "red"));
        System.out.println(filterCarByColorAndBrand(listCars, "green", "Audi"));

        listCars.stream().filter(c -> c.getColor().equals("green"));

        // Com uso de parametrização, método fica mais GENÉRICO:
        // Porém ainda sem utilizar lambdas!
        System.out.println(filter(listCars, new CarPredicate() {
            @Override
            public boolean teste(CarModel car) {
                return car.getColor().equals("green");
            }
        }));

        // Com lambdas implementamos uma interface que possuí apenas
        // um método, porisso é possível utilizar a sintaxe x -> x
        // pois ele assume que só pode chamar um método da interface,
        // portanto garante a execução:

        System.out.println(filter(listCars, c -> c.getColor().equals("red") && c.getBrand().equals("Mustang") ));
    }

    // Sem parametrizar comportamentos (lambdas):
    // Problema: Replicação de código com mesmo contexto:
    // Obs: Apenas o if é alterado, portanto podemos parametrizar!
    public static CarModel filterCarByColor(List<CarModel> listCars, String color){

        for (CarModel car : listCars) {
            if(car.getColor().equals(color)) { // apenas aqui é alterado
                return car;
            }
        }

        return null;
    }
    public static CarModel filterCarByBrand(List<CarModel> listCars, String brand){

        for (CarModel car : listCars) {
            if(car.getBrand().equals(brand)) { // apenas aqui é alterado
                return car;
            }
        }

        return null;
    }
    public static CarModel filterCarByColorAndBrand(List<CarModel> listCars, String color, String brand){

        for (CarModel car : listCars) {
            if(car.getColor().equals(color) && car.getBrand().equals(brand)) { // apenas aqui é alterado
                return car;
            }
        }

        return null;
    }

    // Método genérico, utilizando parametrização para uso de lambdas:
    public static CarModel filter(List<CarModel> listCars, CarPredicate carPredicate){

        for (CarModel car : listCars) {
            if(carPredicate.teste(car)) { // aqui chamamos o teste do predicate.
                return car;
            }
        }

        return null;
    }
}
