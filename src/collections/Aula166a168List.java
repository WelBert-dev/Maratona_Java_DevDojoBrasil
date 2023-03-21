package collections;

// - List é uma interface que extends de Collection.
// - Utilizada para se trabalhar orientado a interface.
// - Contém os métodos mais genéricos e basicos para se trabalhar com listas.
// - O tamanho do array aumenta conforme necessário (dinamicamente),
// por debaixo dos panos é re-criado o array e a variavel de referência passa a
// apontar para este novo array criado.

// A interface List é uma das interfaces mais importantes do framework de coleções do Java.
// Ela define um contrato para classes que representam sequências ordenadas de elementos e
// oferece uma série de métodos para manipulação dessas sequências.
import java.util.ArrayList;
import java.util.List;

public class Aula166a168List {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<>(); // Se n utilizar generics <> ele define como Object. (ou seja qualquer valor)
        lista.add("Wellison");
        lista.add("Bertelli");
    }
}
