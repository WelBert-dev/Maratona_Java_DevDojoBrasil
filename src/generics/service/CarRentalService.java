package generics.service;

// E Se decidirmos alugar barcos tambem?
// teriamos que duplicar códigos que fazem A MESMA COISA
// porém apenas mudando o model de CarModel para BoatModel
// aí entra o generics para facilitar nossa vida.

import generics.dominio.CarModel;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CarRentalService { // Serviço de aluguel
    private Queue<CarModel> availableCar = new PriorityQueue<>( // disponível
            List.of(new CarModel("Fusca"), new CarModel("BMW")));

    public CarModel printAvailableCar() {
        System.out.println("Buscando carros disponíveis...");
        CarModel car = availableCar.poll(); // pop
        System.out.println("Alugando o carro: "+car);
        System.out.println("------------------------");
        System.out.println("Carros disponíveis para alugar: "+availableCar);
        System.out.println("------------------------");
        return car;
    }

    public void giveBackRetrieveCar(CarModel car) {
        System.out.println("------------------------");
        System.out.println("Devolvendo carro alugado...");
        availableCar.add(car);
        System.out.println("Carro devolvido: "+car);
        System.out.println("------------------------");
        System.out.println("Carros disponíveis para alugar: "+availableCar);
        System.out.println("------------------------");
    }
}
