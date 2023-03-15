package generics;

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
        animals.add(new Sphynks("Irinéia")); // Ué ele ta aceitando as subClasses
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
