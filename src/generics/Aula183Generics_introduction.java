package generics;

// Aqui está a abordagem necessária antes do uso de generics,
// Com uso de Objects, mas ela não é uma abordagem robusta e
// passivel de vários erros, portanto chega o generics para resolver.
// Generics serve apenas em tempo de desenvolvimento e para o
// programador em sí, pois ao compilar o Java faz "Type Erasure" e
// simplesmente substituí todos <T> pelo tipo real final.
// Ele toma essa abordagem para manter compatibilidades em relaçao
// aos versionamentos anteriores (Antes do generics) das JRE
// que iram executar o código Java.

/* OverView sobre Generics:
    - Sintaxe: A sintaxe básica para usar Generics é colocar o tipo entre colchetes angulares < >,
    conhecidos como "diamond operator". Por exemplo, List<String> list = new ArrayList<>();

    - Tipo Genérico: Ao usar Generics, você pode definir um tipo genérico para uma classe ou interface.
    Por exemplo, public class MinhaClasse<T> { ... }. Isso permite que a classe ou interface trabalhe
    com qualquer tipo de objeto, tornando-a mais flexível e reutilizável.

    - Wildcards: É possível usar wildcards para permitir que uma classe ou método trabalhe com qualquer
    tipo, ou um subtipo específico.
    Existem dois tipos de wildcards: ? e ? extends Type.
    Por exemplo, public void meuMetodo(List<? extends Number> lista) { ... }.

    - Bounded Types: Você pode restringir o tipo genérico para uma classe ou interface específica ou um
    subtipo dela.
    Existem duas formas de bounded types: extends e super.
    Por exemplo, public class MinhaClasse<T extends Number> { ... }.

    - Type Erasure: O Java usa Type Erasure para implementar Generics em tempo de compilação, removendo
    as informações de tipo em tempo de execução. Isso significa que o código em tempo de execução não
    sabe qual é o tipo real dos objetos genéricos. Isso pode levar a algumas limitações ao trabalhar
    com Generics em tempo de execução, como restrições na reflexão.

    - Boas práticas: Ao usar Generics, é importante seguir boas práticas, como definir nomes de tipo
    genérico descritivos, evitar a supressão de avisos do compilador e verificar os tipos de objetos
    genéricos em tempo de execução.

    - Definição de Reflexão: Reflexão em Java é a capacidade de um programa Java inspecionar e manipular
    objetos em tempo de execução, sem ter conhecimento prévio de seus tipos ou estruturas em tempo de
    compilação. Isso é feito por meio de classes e interfaces fornecidas pelo pacote ´java.lang.reflect´.
        - Em outras palavras, a reflexão permite que um programa Java examine a estrutura interna de um
        objeto em tempo de execução e obtenha informações sobre seus campos, métodos, construtores e tipos.
        Também permite que o programa chame esses métodos, acesse esses campos e crie instâncias desses
        objetos, tudo isso em tempo de execução.

        - A reflexão é frequentemente usada em situações em que o tipo de objeto a ser manipulado é
        desconhecido ou só será conhecido em tempo de execução. Por exemplo, uma biblioteca que manipula
        objetos de terceiros pode usar a reflexão para inspecionar esses objetos e determinar seus tipos,
        campos e métodos.

        - Obs: O uso da reflexão pode ser uma solução para obter essas informações genéricas apagadas
        em tempo de execução do "Type Erasure".

*/

/* Usando Reflexão (RunTime) para obter o tipo real do objeto genérico "apagado" em tempo de compilação:
- Exemplo:  Obtendo as informações de tipo de uma lista genérica em tempo de execução: List<String>
- O Retorno será o "caminho" do pacote completo (java.lang.String);

    import java.lang.reflect.Field;
    import java.lang.reflect.ParameterizedType;
    import java.util.ArrayList;
    import java.util.List;

    public class ExemploGenerics {
        private List<String> lista = new ArrayList<String>();

        public static void main(String[] args) throws Exception {
            Field field = ExemploGenerics.class.getDeclaredField("lista");
            ParameterizedType pt = (ParameterizedType) field.getGenericType();
            Class<?> typeArgument = (Class<?>) pt.getActualTypeArguments()[0];
            System.out.println("Tipo de elemento da lista: " + typeArgument.getName());
            // Retorno: Tipo de elemento da lista: java.lang.String
        }
    }

*/



// Diamond symbol: <>
// <T>:
// <E>:
// <E extends T>:

import serializacao.dominio.ProductModelSerial;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class Aula183Generics_introduction {
    private List<String> listaReflect = new ArrayList<>();
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


        // Uso de reflexão para obter informções de objetos genéricos <T>
        // "apagados" em tempo de compilação com uso de Type Erasure,
        // em tempo de execução:


        Field field = null;
        try {

            field = Aula183Generics_introduction.class.getDeclaredField("listaReflect");

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Class<?> typeArgument = (Class<?>) pt.getActualTypeArguments()[0];
        System.out.println("Tipo de elemento da lista: " + typeArgument.getName());
    }
}
