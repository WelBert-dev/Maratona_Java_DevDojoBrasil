package classesInternas;

/* O que são classes Locais no escopo de Inner Class? (Local Inner Classes)

    - Em Java, uma classe local é uma classe definida dentro de um método ou
    bloco de código.
    Ela é definida dentro de um bloco de código de outro método, em vez de
    estar no nível de classe.

    - As classes locais são geralmente usadas quando uma classe precisa ser
    criada e usada apenas dentro de um método ou bloco específico, e não
    precisa ser acessada em nenhum outro lugar do código.
    Uma classe local tem acesso aos membros da classe externa, incluindo
    membros privados, e pode ser usada para implementar funcionalidades que
    não são facilmente realizadas usando outras técnicas de programação.

    - As classes locais são divididas em dois tipos: classes locais anônimas
    e classes locais nomeadas. As classes locais anônimas são classes sem
    nome que são definidas dentro de um método e instanciadas imediatamente.
    As classes locais nomeadas, por outro lado, têm um nome e podem ser
    instanciadas em qualquer ponto do método em que são definidas.

    - As classes locais são um recurso poderoso da linguagem Java e podem ser
    usadas para melhorar a modularidade e a organização do código.
    No entanto, seu uso excessivo pode tornar o código mais difícil de entender
    e manter.

*/

/* Obs importante:

    - Só podemos criar Local Inner Class sendo ´abstract´, ou ´final´, pois

    - Em Java, uma classe interna local é uma classe definida dentro de um
    método, construtor ou bloco. Essa classe interna pode ter seus próprios
    campos, métodos e construtores, e pode acessar os membros da classe
    externa em que foi definida.

        - Uma classe interna local em Java pode acessar variáveis locais e parâmetros
        do método ou construtor externo que as contém/encapsula, mas somente se essas
        variáveis e parâmetros forem ´final´ ou ´efetivamente final´.
        Isso ocorre porque a classe interna local pode continuar a existir após a
        conclusão do método ou construtor externo, MAS as variáveis locais e
        parâmetros desse método ou construtor externo que as encapsula deixam
        de existir após a conclusão desses métodos ou construtores.

        - Como resultado, todos os campos definidos em uma classe interna local são
        implicitamente final, independentemente de serem declarados como final ou não,
        para que possam ser acessados a partir do método ou construtor externo e sejam
        imutáveis para garantir a integridade da classe interna.

        - Em resumo, os atributos definidos no escopo do método que encapsula a classe
        interna local são ´final´ ou ´efetivamente final´ porque a classe interna local
        só pode acessar variáveis locais e parâmetros que são final ou efetivamente final.

*/


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Aula190InnerClass_localInnerClass_classesLocais {

    private String outerClassName = "Olá mundo com outerClassName!";

    public static void main(String[] args) {
        Aula190InnerClass_localInnerClass_classesLocais objeOuterClass =
                new Aula190InnerClass_localInnerClass_classesLocais();
        objeOuterClass.staticMethod_wrapperLocalInnerClassExample();

        // LocalInnerClass obj.printLocalInnerClassThis: classesInternas.Aula190InnerClass_localInnerClass_classesLocais$1LocalInnerClass@5acf9800
        // LocalInnerClass obj.printLocalInnerClassName (Atribute): Olá Mundo com Local Inner Classes!

        // Obs: Acessando o this do outerClassObject dentro da LocalInnerClass:
        // Cujo método que o encapsula é STATIC: (Utilizando reflexão para saber).
        // LocalInnerClass obj.printOuterClassThisWithLocalInnerClass_staticMethod:
        // O método staticMethod_wrapperLocalInnerClassExample é estático.
        // classesInternas.Aula190InnerClass_localInnerClass_classesLocais@36baf30c // <- this instânciando OuterClass

    }

    public void staticMethod_wrapperLocalInnerClassExample() {

        class LocalInnerClass {
            private String localInnerClassName = "Olá Mundo com Local Inner Classes!";
            public void printLocalInnerClassThis(){
                System.out.println(this);
            }

            public void printOuterClassThisWithLocalInnerClass_staticMethod(){
                //System.out.println(Aula190InnerClass_localInnerClass_classesLocais.this);
                // Só é possível acessar o this do OuterClass se o método for non-static!

                // Não é possível acessar o objeto this da classe externa a partir de uma
                // classe interna local definida dentro de um método estático, pois uma
                // classe interna local não estática tem uma referência implícita ao objeto
                // da classe externa, enquanto uma classe interna local estática não tem.
                //
                // Quando uma classe interna local não estática é criada, ela mantém uma
                // referência implícita ao objeto da classe externa que a criou, permitindo
                // o acesso ao objeto this da classe externa.
                // No entanto, se a classe interna local é definida dentro de um método
                // estático, não há objeto da classe externa para manter uma referência,
                // e portanto não é possível acessar o objeto this da classe externa a
                // partir da classe interna local.
                //
                // Para acessar membros de uma classe externa em uma classe interna local
                // estática, você pode passar uma referência explícita à classe externa
                // como um parâmetro para o construtor da classe interna local estática ou
                // definir os membros que precisam ser acessados como estáticos.

                // Se o método que encapsula o LocalInnerClass for estático:
                Class<?> outerClassClazz = Aula190InnerClass_localInnerClass_classesLocais.class;
                try {
                    Method method = outerClassClazz.getMethod("staticMethod_wrapperLocalInnerClassExample");
                    if (Modifier.isStatic(method.getModifiers())) {
                        System.out.println("O método staticMethod_wrapperLocalInnerClassExample é estático.");

                        // Não é possível acessar o this da classe OuterClass que encapsula
                        // OuterClass: Aula190InnerClass_localInnerClass_classesLocais
                        // Então para acessar precisamos instânciar um objeto para ele.

                        Aula190InnerClass_localInnerClass_classesLocais outerClassObject = new Aula190InnerClass_localInnerClass_classesLocais();
                        System.out.println(outerClassObject);
                    } else { // NUNCA vai cair aqui, apenas cai no exemplo utilizando non-static.
                        System.out.println("O método staticMethod_wrapperLocalInnerClassExample não é estático.");
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
            public void printLocalInnerClassName() {
                System.out.println("Atributo Nome da LocalInnerClass (Escopo de método): "+
                        this.getLocalInnerClassName());
            }

            public String getLocalInnerClassName() {
                return localInnerClassName;
            }

            public void setLocalInnerClassName(String localInnerClassName) {
                this.localInnerClassName = localInnerClassName;
            }
        }

        // Como o escopo da classe só existe dentro do método, então
        // devemos instânciar e manipular a classe aqui mesmo:
        LocalInnerClass localInnerClass = new LocalInnerClass();

        System.out.println("------------------------[ localInnerClass.printLocalInnerClassThis() returns: ]-----------------------------");
        localInnerClass.printLocalInnerClassThis();
        System.out.println();

        System.out.println("------------------------[ localInnerClass.printLocalInnerClassName() returns: ]-----------------------------");
        localInnerClass.printLocalInnerClassName();
        System.out.println();

        System.out.println("------------------------[ localInnerClass.printOuterClassThisWithLocalInnerClass_staticMethod() returns: ]-----------------------------");
        localInnerClass.printOuterClassThisWithLocalInnerClass_staticMethod();
        System.out.println();

        // Após finalizar execução do método a classe morre.
    }
}
