package classesInternas;

public class Aula191InnerClass_anonymousInnerClass_classesAnonimas {
    public static void main(String[] args) {
        // Já utilizei bastante, bem simples:
        Animal animal = new Animal();
        animal.walk();
        // Animal andando sem override

        // Mudando o comportamento apenas para pontos especificos:
        Animal animalAnonimo = new Animal() {
            @Override
            public void walk() {
                System.out.println("Animal sobrescrito andando");
                nonExists(); // acessando métodos criados neste escopo
            }

            // NÃO adianta criar métodos neste escopo pois a variável
            // de referência não conhece ele, portanto será um código
            // totalmente inacessível, a não ser que chame neste bloco:

            public void nonExists() {
                System.out.println("Método inacessível");
            }
        };
        animalAnonimo.walk();
        // Animal sobrescrito andando

        // Ou seja, altera o comportamento apenas para esse objeto
        // animalAnonimo, bem simples.
    }
}

class Animal {
    public void walk() {
        System.out.println("Animal andando sem override");
    }
}
