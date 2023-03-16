package generics;

import generics.dominio.BoatModel;
import generics.dominio.CarModel;
import generics.service.CarRentalService;
import generics.service.RentalService;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Aula186Generics_genericClass {
    public static void main(String[] args) {
        // Demonstração: Aluguel de carros (em service), com e sem generics.

        // Sem generics, ficamos limitados a trabalhar apenas com CARRO:
        System.out.println("-------------------------------------[SEM GENERICS]----------------------------------");
        CarRentalService carRentalService = new CarRentalService();
        CarModel carAvailable = carRentalService.printAvailableCar();
        System.out.println("Usando o carro por um mes...");
        carRentalService.giveBackRetrieveCar(carAvailable);

        // Com generics, podemos trabalhar com qualquer VEÍCULO/Objeto:
        System.out.println("-------------------------------------[COM GENERICS]----------------------------------");
        Queue<CarModel> carsQueue = new PriorityQueue<>(
                List.of(new CarModel("Fusca"), new CarModel("BMW")));

        Queue<BoatModel> boatsQueue = new PriorityQueue<>(
                List.of(new BoatModel("Canoa"), new BoatModel("Iati")));

        RentalService<CarModel> carRentalServiceGenerics = new RentalService<>(carsQueue);
        RentalService<BoatModel> boatRentalServiceGenerics = new RentalService<>(boatsQueue);

        // Códigos são exatamente iguais ao sem, generics, porém
        // ao invés de utilizar o serviço específico para Carro, trocamos pelo genérico:
        CarModel carAvailableGenerics = carRentalServiceGenerics.printAvailableObject();
        System.out.println("Usando o carro por um mes...");
        carRentalServiceGenerics.giveBackRetrieveCar(carAvailableGenerics);

        BoatModel boatAvailableGenerics = boatRentalServiceGenerics.printAvailableObject();
        System.out.println("Usando o barco por um mes...");
        boatRentalServiceGenerics.giveBackRetrieveCar(boatAvailableGenerics);
    }
}
