package generics;

// Diamond symbol: <>
// <T>:
// <E>:
// <E extends T>:

import java.util.ArrayList;
import java.util.List;

public class Aula183Generics_introduction {
    public static void main(String[] args) {
        // Antigamente como era feito até a versão 1.4 do Java:
        // Utilizava a abordagem de trabalhar com Object
        // Uma vez que todos os objetos extends dele
        // Porém assim não garante concistência das coleções
        // Pois ai podemos quebrar a regra de elementos do mesmo tipo
        // Pois podemos literalmente adicionar qualquer classe (Todas extends de Object)

        List list = new ArrayList<>();
        list.add("Wellison");
        list.add(10);
        list.add(true);

        System.out.println(list); // [Wellison, 10, true]

        // Problemas na manipulação, pois devemos fazer vários IF
        // para garantir o cast do Objeto para uma referência correta.
        for (Object o: list){
            System.out.println(o);
        }

        // Chega Generics para resolver o problema:
        // Assim garantimos em tempo de compilação que a coleção
        // ira suportar apenas um tipo, assim "Forçamos" que o tipo
        // da List<T> ou outra classe genérica é de apenas um.
        // Ou seja em tempo de compilação ocorre um Type erasure
        // que é literalmente isso, o Java simplesmente ignora o tipo da lista
        // ela serve apenas como guia na criação, garantindo que não
        // estamos inserindo valores de vários tipos.

        // Porisso tomar cuidados ao utilizar como parâmetro classes
        // genéricas e não especificar o tipo com o operador diamond <>
        // pois assim o Java não identificará em tempo de compilação
        // podendo assim adicionar valores de tipos diferentes
        // e ao utilizar algum método especifico de algum tipo especifico
        // ocorrer um ClassCastException
    }
}
