package J_generics;

/* Definições sobre o Wildcard:

    - Em Java, um wildcard é um caractere curinga que pode ser usado em contextos de tipos genéricos
    para indicar que qualquer tipo pode ser usado como argumento. O wildcard é representado pelo
    símbolo de interrogação (?).

    - O wildcard é usado quando não sabemos qual tipo exato será usado em um contexto genérico ou
    quando precisamos definir um tipo que seja compatível com vários tipos diferentes.

    - Existem dois tipos de wildcards: o wildcard não delimitado e o wildcard delimitado.
        - O wildcard não delimitado: é representado pelo caractere de interrogação sozinho.
        Ele indica que qualquer tipo pode ser usado como argumento.
        Por exemplo: List<?> lista = new ArrayList<>(); // pode ser uma lista de qualquer tipo.
            - Também conhecido como: wildcard unbounded.

        - O wildcard delimitado: é usado para restringir o tipo de argumento que pode ser usado.
        Ele é representado pelo caractere de interrogação seguido por uma palavra-chave ´extends´
        ou ´super´, e o tipo desejado.
        Por exemplo: List<? extends Number> lista = new ArrayList<>(); // pode ser qualquer subClasse ou a pŕopria classe Number (Abaixo ou o mesmo nível da árvore)
                     List<? super Integer> listaDeInteiros; // pode ser qualquer superClasse ou a pŕopria classe Number (Acima ou o mesmo nível da árvore)
            - Também conhecidos como: wildcard bounded.

 */

/* Pontos importantes e atenções a se tomar quando se trabalha com wildcard bounded EXTENDS:

    - A wildcard bounded extends é usada quando queremos restringir um tipo genérico para
    qualquer subtipo de uma determinada classe ou interface. Isso significa que a wildcard
    só pode ser substituída por um tipo que seja subtipo da classe ou interface especificada.

    - Quando usamos a wildcard bounded extends, a classe ou interface especificada não pode
    ser modificada. Isso significa que não podemos adicionar novos métodos ou campos à classe
    ou interface especificada após usá-la para restringir a wildcard.

    - A wildcard bounded extends pode ser usada em variáveis, parâmetros de método, retornos
    de método e tipos de retorno de método. Quando usada em um parâmetro de método, a wildcard
    bounded extends permite que o método seja chamado com argumentos que sejam do tipo da classe
    ou interface especificada ou qualquer subtipo dela.

    - A wildcard bounded extends pode causar problemas de legibilidade de código se for usada
    em excesso. É importante equilibrar a necessidade de restringir tipos com a legibilidade
    do código.

    - Quando usamos a wildcard bounded extends, não podemos adicionar elementos à coleção.
    Isso ocorre porque não sabemos exatamente qual é o tipo da coleção em tempo de compilação,
    e a adição de elementos pode causar erros em tempo de execução.

    - A wildcard bounded extends pode ser usada em conjunto com outras palavras-chave, como
    super e &, para criar restrições mais complexas.
    Por exemplo, podemos usar <? extends Number & Comparable> para restringir um tipo genérico
    para qualquer subtipo de Number que também implemente a interface Comparable.

    - Em geral, a wildcard bounded extends é útil para criar métodos genéricos que possam ser
    usados com vários tipos diferentes de argumentos. No entanto, é importante lembrar que o
    uso de wildcards pode tornar o código mais complexo e difícil de entender, portanto, deve
    ser usado com cuidado e moderação.

    - O operador extends só pode ser usado com tipos de referência, não com tipos primitivos.

    - Quando você usa um wildcard bounded com extends, você pode chamar métodos que retornam o
    tipo do curinga ou um tipo mais específico.
    Por exemplo, se você tiver uma lista de Fruit e um método que retorne apenas as frutas que
    são Apple, você pode usar um curinga bounded com extends para obter apenas as frutas Apple
    da lista. Nesse caso, a lista se tornaria List<? extends Fruit>, e você pode chamar o método
    que retorna apenas as maçãs usando List<? extends Fruit>.getApple().
        - Exemplo:

            List<Fruit> fruits = new ArrayList<>();
            fruits.add(new Apple());
            fruits.add(new Orange());
            fruits.add(new Banana());

            // Lista de frutas delimitada por <? extends Fruit>
            List<? extends Fruit> fruitsExtends = fruits;

            // Chama o método getApple() da lista
            for (Fruit fruit : fruitsExtends) {
                if (fruit instanceof Apple) {
                    ((Apple) fruit).getApple();
                }
            }

*/
/* Pontos importantes e atenções a se tomar quando se trabalha com wildcard bounded SUPER:

    - A wildcard bounded super é usada para restringir um tipo genérico para qualquer
    superclasse de uma determinada classe ou interface.
    Isso significa que a wildcard só pode ser substituída por um tipo que seja uma
    superclasse da classe ou interface especificada.

    - Ao contrário da wildcard bounded extends, a wildcard bounded super permite a adição
    de elementos em uma coleção, mas não permite a leitura de elementos.
    Isso ocorre porque não é possível saber qual é exatamente o tipo concreto dos elementos
    em tempo de compilação.

    - Ao utilizar a wildcard bounded super, deve-se ter cuidado para não adicionar elementos
    de tipos que não sejam subtipos da classe ou interface especificada, pois isso pode gerar
    erros em tempo de execução.

    - A wildcard bounded super pode ser usada em variáveis, parâmetros de método, retornos de
    método e tipos de retorno de método.
    Quando usada em um parâmetro de método, a wildcard bounded super permite que o método seja
    chamado com argumentos que sejam do tipo da classe ou interface especificada ou qualquer
    superclasse dela.

    - A wildcard bounded super pode ser usada em conjunto com outras palavras-chave, como
    extends e &, para criar restrições mais complexas.

    - O operador super só pode ser usado com tipos de referência, não com tipos primitivos.

    - Ao utilizar a wildcard bounded super, é importante lembrar que a classe ou interface
    especificada não pode ser modificada, ou seja, não podemos adicionar novos métodos ou
    campos a ela após usá-la para restringir a wildcard.

    - A wildcard bounded super pode ser útil em situações em que precisamos garantir que uma
    coleção possua apenas elementos de um tipo específico ou de seus superclasses, permitindo
    a adição de novos elementos à coleção. No entanto, é importante ter em mente que a utilização
    de wildcards pode tornar o código mais complexo e difícil de entender, devendo ser usado
    com moderação.

 */


import java.util.ArrayList;
import java.util.List;

public class Aula184e185Generics_wildcard {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Sphynks> sphynksSubFromCatList = new ArrayList<>();

        dogs.add(new Dog("Snoop"));
        dogs.add(new Dog("Belinha"));
        dogs.add(new Dog("Magguie"));

        cats.add(new Cat("Sphynks"));
        cats.add(new Cat("Neve"));
        cats.add(new Cat("Catarina"));

        sphynksSubFromCatList.add(new Sphynks("Cleopatra"));
        sphynksSubFromCatList.add(new Sphynks("Bills"));

        // wildcard bounded extends (qualquer subClasse ou a pŕopria classe):
        System.out.println("-------------------[Extends]--------------------");
        printConsultaWithWildcardBoundedExtends(dogs);
        printConsultaWithWildcardBoundedExtends(cats);
        printConsultaWithWildcardBoundedExtends(sphynksSubFromCatList);

        // wildcard bounded super (qualquer superClasse eliminando a própria classe):
        System.out.println("-------------------[Super]--------------------");
        printConsultaWithWildcardBoundedSuper(cats);
        printConsultaWithWildcardBoundedSuper(cats);
    }
    public static void printConsultaWithWildcardBoundedExtends(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.consulta();
        }

        // Ao passar uma lista que respeite o contrato do wildcard bounded a cima,
        // não é mais possível dentro deste bloco adicionar mais elementos na lista
        // pois o Java deve garantir que o Polimorfismo não ira "burlar" o
        // contrato de que deve aceitar apenas elementos que estão abaixo da árvore
        // de "Animal", pois com o uso de Polimorfismo seria possível adicionar
        // qualquer classe que "É" uma animal, ou seja Dog, Cat e Sphynks.

        //animals.add(new Cat("Irinéia"));
        // Checked Exeception: 'add(capture<? extends generics.Animal>)' in 'java.util.List' cannot be applied to '(generics.Cat)'
    }

    public static void printConsultaWithWildcardBoundedSuper(List<? super Cat> animals) {

        for (Object animal : animals) {
            if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cat.consulta();
            }
        }

        animals.add(new Cat("InemEU"));

        // Aqui devemos utilizar como variável de referencia o tipo MAIS genérico,
        // o ultimo elemento da àrvore de herança Object, pois o contrato
        // diz que aceita qualquer classe a cima da classe especificada, então
        // não podemos por exemplo utilizar uma variável do tipo "Animal" pois ainda
        // existem outras superClasses a cima dela, ou seja para garantir que
        // a classe real/final passada seja compatível com "super" temos
        // que utilizar o primeiro elemento da árvore: Object.

        // E Diferente da abordagem anterior com o uso do ´extends´,
        // Aqui graças ao Polimorfismo podemos GARANTIR que quem passa
        // é um Cat, logo a lista pode aceitar qualquer subclasse dele.
        animals.add(new Sphynks("Irinéia"));
        Cat sphynks = new Sphynks("InemEu");
        animals.add(sphynks);

        // animals.add(new Dog("Pamonha")); não é possivel
    }
}


abstract class Animal {
    private String consulta;
    private String nome;

    public Animal(String consulta) {
        this.consulta = consulta;
    }

    public Animal() {
    }

    public abstract void consulta(); // override pelas subClasses

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class Dog extends Animal{
    public Dog(String nome) {
        super.setNome(nome);
    }
    @Override
    public void consulta() {
        System.out.println("Realizando a consulta no Cachorro! nome: " + getNome());
    }
}

class Cat extends Animal {

    public Cat(String nome) {
        super.setNome(nome);
    }
    @Override
    public void consulta() {
        System.out.println("Realizando a consulta no Gato! nome: " + getNome());
    }
}

class Sphynks extends Cat {

    public Sphynks(String nome) {
        super(nome);
    }

    // Não é obrigatório implementar pois a superClasse Cat ja o faz.
    @Override
    public void consulta() {
        System.out.println("Realizando a consulta no Sphynks! nome: " + getNome());
    }
}
