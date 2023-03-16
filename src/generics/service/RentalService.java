package generics.service;

import generics.dominio.CarModel;

import java.util.Queue;

public class RentalService<T> {
    private Queue<T> objectsAvailable;

    public RentalService(Queue<T> objectsAvailable) {
        this.objectsAvailable = objectsAvailable;
    }

    public T printAvailableObject() {
        System.out.println("Buscando objetos disponíveis...");
        T t = objectsAvailable.poll(); // pop
        System.out.println("Alugando o objeto: "+t);
        System.out.println("------------------------");
        System.out.println("Objetos disponíveis para alugar: "+objectsAvailable);
        System.out.println("------------------------");
        return t;
    }

    public void giveBackRetrieveCar(T t) {
        System.out.println("------------------------");
        System.out.println("Devolvendo objeto alugado...");
        objectsAvailable.add(t);
        System.out.println("Objeto devolvido: "+t);
        System.out.println("------------------------");
        System.out.println("Objetos disponíveis para alugar: "+objectsAvailable);
        System.out.println("------------------------");
    }
}
