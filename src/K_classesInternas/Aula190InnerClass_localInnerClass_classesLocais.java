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

        - Uma classe interna local em Java pode acessar variáveis locais e
        parâmetros do método ou construtor externo que as contém/encapsula,
        mas somente se essas variáveis e parâmetros forem ´final´ ou
        ´efetivamente final´.
        Isso ocorre porque a classe interna local pode continuar a existir
        após a conclusão do método ou construtor externo, MAS as variáveis
        locais e parâmetros desse método ou construtor externo que as
        encapsula deixam de existir após a conclusão desses métodos ou
        construtores.

        - Como resultado, todos os campos definidos em uma classe interna
        local são implicitamente final, independentemente de serem declarados
        como final ou não, para que possam ser acessados a partir do método
        ou construtor externo e sejam imutáveis para garantir a integridade
        da classe interna.

        - Em resumo, os atributos definidos no escopo do método que encapsula
        a classe interna local são ´final´ ou ´efetivamente final´ porque a
        classe interna local só pode acessar variáveis locais e parâmetros que
        são final ou efetivamente final.

*/

/* Pontos importantes e atenções a se tomar quando se trabalha com Local Inner Classes:

    - Escopo de visibilidade: Uma Local Inner Class pode acessar membros da
    classe externa e variáveis locais e parâmetros do método em que está
    definida, desde que sejam ´final ou ´efetivamente final´.
    É importante entender o escopo de visibilidade da Local Inner Class para
    garantir que os membros internos sejam acessados corretamente.

    - Acesso a membros da classe externa: as classes internas locais também podem
     acessar membros da classe externa, como campos e métodos, mesmo que sejam
     privados.
     Isso ocorre porque a classe interna é uma parte da classe externa.

    - Criação de instâncias: uma classe interna local não pode ser instanciada
    fora do método em que foi definida.
    Isso ocorre porque a classe interna local é apenas uma classe interna em
    relação ao método em que foi definida, mas não faz parte da classe externa.
    Portanto, a classe interna não pode ser usada fora do método em que foi
    definida, a menos que seja instanciada dentro desse método.

    - Visibilidade da classe: uma classe interna local só é visível dentro do
    método em que foi definida. Isso significa que outras partes do programa
    não podem acessá-la diretamente. Se a classe interna local precisar ser
    usada fora do método, ela pode ser retornado por um método do método que
    a define.

    - Performance: o uso excessivo de classes internas locais pode afetar
    negativamente o desempenho do programa.
    Isso ocorre porque cada classe interna local é compilada separadamente,
    o que pode aumentar o tamanho do arquivo .class e afetar o tempo de
    carregamento do programa. Portanto, é importante usar as classes internas
    locais com moderação e apenas quando necessário.

    - Convenções de Nomenclatura: É uma boa prática seguir as convenções de
    nomenclatura em Java ao nomear Local Inner Classes. Os nomes de Local Inner
    Classes geralmente começam com uma letra maiúscula, e os nomes de classe
    compostos devem usar o padrão camelCase.

    - Tamanho e complexidade do código: O uso excessivo de Local Inner Classes
    pode levar a um código mais complexo e difícil de entender e manter.
    É importante avaliar se o uso de Local Inner Classes é realmente necessário
    e se há outras maneiras de estruturar o código de forma mais clara e concisa.
    Afetando assim na legibilidade e manutenibilidade do código.

    - Classes Anônimas: Local Inner Classes são semelhantes a classes anônimas, que
    são definidas no momento da instanciação. As classes anônimas podem ser úteis
    quando é necessário implementar uma interface ou uma classe abstrata de forma
    rápida e concisa. No entanto, elas podem tornar o código menos legível e
    dificultar a depuração.

    - Interfaces funcionais: Local Inner Classes são frequentemente usadas para
    criar instâncias de interfaces funcionais, como a interface Runnable.
    Em vez de criar uma nova classe que implementa a interface, pode ser mais
    conveniente definir uma Local Inner Class para implementar a interface
    diretamente dentro do método onde ela será usada.

    - Sobrescrita de membros: Local Inner Classes podem sobrescrever membros da
    classe externa com o mesmo nome.
    Isso pode levar a comportamentos inesperados, se o código que chama o método
    não estiver ciente da Local Inner Class e sua sobrescrita.

    - Uso de variáveis estáticas: Local Inner Classes não podem conter variáveis
    estáticas, apenas variáveis de instância.
    Isso ocorre porque uma Local Inner Class é criada dentro do escopo de um método
    e não faz parte da classe externa.

    - Acesso a membros privados: Local Inner Classes podem acessar membros privados
    da classe externa, como campos e métodos.
    No entanto, isso pode violar o encapsulamento e tornar o código mais difícil de
    entender e manter. É importante usar o bom senso ao decidir quais membros devem
    ser acessados pela Local Inner Class.

    - Herança: Local Inner Classes não podem ser estendidas ou ser uma superclasse,
    pois elas não podem ser acessadas fora do método em que foram definidas.

    - Modularidade: Local Inner Classes podem ser usadas para modularizar o código
    e encapsular a implementação de um recurso específico dentro do método em que
    será usado. No entanto, isso deve ser feito com cuidado para evitar a criação
    de uma cadeia de dependências complexa.

    - Concorrência: Local Inner Classes podem ser usadas em ambientes concorrentes,
    mas é importante lembrar que elas compartilham o mesmo estado que a classe
    externa em que foram definidas.
    Isso pode levar a problemas de concorrência se não for tratado adequadamente.

    - Polimorfismo: Local Inner Classes podem ser usadas em polimorfismo, como
    outras classes Java. Isso significa que uma referência de uma classe mais
    genérica pode ser usada para referenciar uma instância de uma classe mais
    específica, incluindo uma Local Inner Class.

    - Depreciação: Local Inner Classes estão se tornando obsoletas em favor das
    expressões lambda, que foram introduzidas no Java 8. As expressões lambda
    fornecem uma maneira mais concisa e expressiva de definir comportamentos
    específicos sem a necessidade de criar classes internas separadas.

    - Serialização: Local Inner Classes podem ser serializadas, mas há algumas
    limitações. A classe externa e quaisquer outras classes internas devem ser
    serializáveis e a Local Inner Class deve ser marcada como "transient" para
    evitar problemas de serialização.

    - Debugging: O debugging de Local Inner Classes pode ser complicado, pois
    elas são definidas dentro do escopo de um método e não podem ser acessadas
    diretamente fora desse escopo. No entanto, as ferramentas de debugging
    modernas geralmente permitem que você inspecione o estado de uma Local
    Inner Class e seus membros.

    - Uso em lambda: Local Inner Classes podem ser usadas como alternativa para
    expressões lambda em alguns casos. No entanto, as expressões lambda são
    geralmente mais concisas e expressivas, especialmente para implementações
    de interfaces funcionais simples.

    - Criação de classes internas em métodos: Local Inner Classes são um tipo de
     classe interna que é definida dentro de um método. No entanto, também existem
     outras classes internas que podem ser definidas dentro de outras classes,
     como classes internas estáticas e classes internas regulares.

    - Exemplo de uso: Um exemplo comum de uso de Local Inner Classes é em classes
    de eventos, onde a Local Inner Class é usada para implementar o comportamento
    do evento em resposta a uma ação do usuário. Por exemplo, em um botão de clique,
    a Local Inner Class seria usada para definir o comportamento do clique do botão.

    - Utilidade em código legado: Local Inner Classes podem ser úteis em código
    legado ou em situações em que não é possível ou desejável modificar a estrutura
    do código existente. No entanto, em novos projetos, é recomendável considerar
    o uso de alternativas mais modernas, como expressões lambda ou outras formas de
    programação funcional.

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
