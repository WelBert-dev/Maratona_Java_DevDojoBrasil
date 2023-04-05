package N_methodReference;

// Obs: Apenas utilizar method reference quando a lambda
// acessar APENAS um método, se utilizar mais deve-se
// utilizar a sintaxe lambda comum.

/* Definições sobre os 4 tipos de method reference:

    - Referência a métodos estáticos: MyClass::staticMethod;
    Este tipo de referência é usado para referenciar um método estático existente.
    O nome da classe é usado como prefixo do nome do método estático

    - Referência a métodos de instância em um objeto particular: obj::instanceMethod;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto específico. O objeto é especificado antes do nome do método.

    - Referência a método de instância de um tipo de objeto arbitrário: String::length;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto de um tipo arbitrário. O objeto é passado como o primeiro parâmetro para
    a expressão lambda, e a sintaxe é ClassName::instanceMethod.
    Obs: Objeto esse que ainda não foi criado.

    - Referência a um construtor: ArrayList::new;
    Este tipo de referência é usado para referenciar um construtor de uma classe.
    A sintaxe é ClassName::new.

*/

/* Para métodos non-static existe dois tipos de Method Reference:

    - Referência a métodos de instância em um objeto particular: obj::instanceMethod;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto específico. O objeto é especificado antes do nome do método.

    - Referência a método de instância de um tipo de objeto arbitrário: String::length;
    Este tipo de referência é usado para referenciar um método de instância em um
    objeto de um tipo arbitrário. O objeto é passado como o primeiro parâmetro para
    a expressão lambda, e a sintaxe é ClassName::instanceMethod.

*/

/* Diferenças entre os dois tipos de referência a métodos non-static:

Em resumo, a diferença é que a referência a um método de instância de um objeto
particular é criada a partir de um objeto específico instânciado, enquanto a
referência a um método de instância de um objeto arbitrário de um tipo específico
é criada a partir do tipo/classe do objeto e não de um objeto específico instânciado.
Ou seja no primeiro caso o objeto já foi instânciado, no segundo caso o objeto ainda
não existe, porisso "arbitrário".

*/


import L_parametrizandoComportamentos.domain.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Aula199MethodReference_Referencia_a_metodos_nao_estaticos {
    public static void main(String[] args) {

        // Referência a métodos de instância em um objeto particular: obj::instanceMethod;
        // Ou seja, objeto instânciado:

        // Objeto instânciado em exemplo será um Comparator
        // Porém não implements Comparator, apenas respeita as
        // regras de Target Type e Functional Descriptor das lambdas:
        CarComparators_MethodReferenceStaticAndNonStatic objCarComparators = new CarComparators_MethodReferenceStaticAndNonStatic();

        List<CarModel> listCars = new ArrayList<>();
        listCars.add(new CarModel("Audi", "Audi", "A3", "green"));
        listCars.add(new CarModel("Mustang", "Mustang", "V8", "red"));

        // Comparator sem passar no teste "É UM":
        listCars.sort(objCarComparators::compareByNome_NonStatic);

        // Exatamente a mesma coisa porém sem method reference:
        listCars.sort((car1, car2) -> objCarComparators.compareByNome_NonStatic(car1, car2));

        // ----------------------------------------------------------------------------------
        // Referência a método de instância de um tipo de objeto arbitrário: String::length;
        // Ou seja, objeto NÃO instânciado:

        List<String> listNames = new ArrayList(List.of("Wellison", "Danielle"));

        listNames.sort(String::compareTo); // Obs esse método é Non-Static chamado pelo nome da classe

        // ----------------------------------------------------------------------------------
        // Converter uma string int em um integer:
        // String é o tipo de entrada <T>
        // integer é o tipo de saída <R>
        // Function<T, R>:
        Function<String, Integer> numStringToInteger_NonMethodreference = s -> Integer.parseInt(s);
        // Exemplo de uso, devemos aplicar o método apply() da functional Interface Function:
        Integer r_IntegerGeneratedByLambda = numStringToInteger_NonMethodreference.apply("10");


        // Como utilizamos apenas um método, podemos substituir por Method Reference:
        Function<String, Integer> numStringToInteger_WithMethodReference = Integer::parseInt;
        // Exemplo de uso, devemos aplicar o método apply() da functional Interface Function:
        Integer r_IntegerGeneratedByMethodReference = numStringToInteger_WithMethodReference.apply("10");

        // ----------------------------------------------------------------------------------
        // Utilizando o BiPredicate com Method Reference:
        // Exemplo: Verificar se um nome existe na Lista:

        BiPredicate<List<String>, String> checkIfContainsNameInList = List::contains;

        // retorno é um boolean
        if (checkIfContainsNameInList.test(listNames, "Wellison")) {
            System.out.println("Wellison está presente na lista!");
        } else {
            System.out.println("Wellison não está presente na lista!");
        }

    }
}
