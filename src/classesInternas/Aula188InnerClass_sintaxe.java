package classesInternas;

// Inner Class ou Classes internas são classes dentro de classes,
// utilizada apenas quando a outerClass realmente precisa separar
// algum comportamento e garantir a coesão do código e além disto
// APENAS a outerClass utilize a Inner Class!!!

// Introdução ao contexto de Classes internas, aqui está explicações
// sobre a sintaxe.


/* Pontos importantes e atenções a se tomar quando se trabalha com classes internas (Inner Class):

    - Acesso a membros externos: As classes internas têm acesso aos membros da classe externa em
    que são definidas, incluindo membros privados.
    Isso pode ser útil para encapsular a implementação interna de uma classe, mas também pode
    introduzir complexidade e potencialmente violar o encapsulamento.

    - Acesso de classes externas a membros de classes internas: classes externas podem acessar
    membros de classes internas usando a sintaxe ´objDaClasseInterna.nomeDoMembro´.
    Ou segue a mesma regra para classes normais aonde membros estáticos podem ser acessados
    ´NomeDaClasse.nomeDoMembro´.
    No entanto, é importante lembrar que os membros privados da classe interna PODEM ser
    acessados por classes externas que o encapsula, para outros contextos NÃO é acessível.

    - Acesso a membros estáticos: as classes internas podem acessar membros estáticos da classe externa
    diretamente, sem a necessidade de criar uma instância da classe externa. No entanto, é importante usar
    essa funcionalidade com cuidado e manter os membros estáticos bem definidos e documentados.

    - Tipo de classe interna: Existem quatro tipos de classes internas em Java, sendo elas:
    Classe interna, Estática, Local e Anônima; e cada uma tem suas próprias características
    e usos.
    É importante escolher o tipo de classe interna apropriado para o caso de uso específico.

    - Classes internas locais: uma classe interna local é uma classe interna definida dentro
    de um método. Isso significa que ela só pode ser acessada dentro desse método.
    As classes internas locais podem ser úteis para implementar lógica de método específica
    que não é necessária em outras partes do código.

    - Instanciação da classe interna: Para instanciar uma classe interna, é necessário primeiro
    criar uma instância da classe externa e, em seguida, criar uma instância da classe interna
    usando a instância da classe externa como referência.
    Isso pode ser confuso para desenvolvedores iniciantes e pode levar a erros se não for tratado
    corretamente.

    - Ciclo de vida: Uma classe interna pode viver mais tempo que a classe externa, o que significa
    que a instância da classe interna pode permanecer na memória, mesmo quando a instância da classe
    externa já foi destruída.
    Isso pode levar a vazamentos de memória se não for gerenciado corretamente.

    - Serialização: Se a classe interna contém membros não serializáveis, a serialização da classe
    externa pode falhar. É importante garantir que todos os membros da classe interna sejam
    serializáveis se a classe externa for serializável.

    - Testes unitários: A criação de testes unitários para classes internas pode ser mais difícil do
    que para classes regulares, pois requer a criação de uma instância da classe externa e, em seguida,
    a instância da classe interna para testar o comportamento da classe interna.

    - Uso em testes unitários: as classes internas também podem ser úteis em testes unitários, permitindo
    criar classes aninhadas que fornecem dados de teste específicos para um caso de teste.

    - Nomeação: a convenção de nomenclatura para classes internas é usar o nome da classe externa seguido
    do nome da classe interna. Por exemplo, se a classe externa se chama "OuterClass", a classe interna
    pode ser chamada de "OuterClass.InnerClass".

    - Aninhamento múltiplo: é possível ter várias classes internas aninhadas dentro de uma classe externa.
    Nesse caso, a convenção de nomenclatura
    Isso pode ser útil para encapsular dados e comportamentos específicos de uma classe interna em um local
    específico.

    - Escopo de variáveis: as classes internas têm acesso às variáveis locais e parâmetros da classe externa.
    Isso pode ser útil em alguns casos, mas é importante estar ciente de que as variáveis locais e parâmetros
    devem ser final ou efetivamente final para serem acessíveis a partir da classe interna.

    - Herança: as classes internas podem estender outras classes ou implementar interfaces, o que pode ser
    útil em algumas situações. No entanto, é importante ter em mente que a classe interna não pode ser
    estendida fora da classe externa.

    - Referência da classe externa: as classes internas têm uma referência implícita à classe externa, o que
    pode ser útil em alguns casos. No entanto, isso também pode causar problemas de vazamento de memória se
    a classe interna for mantida em memória por mais tempo do que a classe externa.

    - Legibilidade do código: as classes internas podem melhorar a legibilidade do código em alguns casos,
    mas também podem torná-lo mais complexo e difícil de entender em outros. É importante equilibrar os
    benefícios e as desvantagens e escolher o tipo certo de classe interna para a situação.

    - Declaração de classes internas: a declaração de uma classe interna é feita dentro da classe externa,
    e pode ser estática ou não estática. As classes internas estáticas são declaradas com o modificador de
    acesso "static" e são usadas principalmente para definir classes utilitárias que não dependem do estado
    da classe externa. As classes internas não estáticas são declaradas SEM o modificador "static" e são
    usadas principalmente para representar comportamentos específicos da classe externa.

    - Uso de classes internas anônimas: as classes internas anônimas são declaradas sem nome, e são usadas
    principalmente para definir comportamentos específicos em tempo de execução. Elas são frequentemente
    usadas em conjunto com interfaces funcionais, como a interface Runnable, e permitem criar objetos com
    comportamentos específicos sem a necessidade de criar uma nova classe separada.
        - Complemento:
            - Uma classe interna anônima é uma classe interna que não tem um nome especificado. Em vez disso,
            ela é criada em tempo de execução e é usada para implementar interfaces ou classes abstratas em
            um único local. Isso pode ser útil quando se deseja criar objetos em tempo de execução sem a
            necessidade de criar uma classe separada.

    - Classes internas de membro: uma classe interna de membro é uma classe interna que é definida como um
    membro da classe externa.
    Isso significa que ela pode ser acessada por todas as instâncias da classe externa. As classes internas
    de membro podem ser úteis para encapsular dados e comportamentos específicos de uma instância da classe
    externa.

    - Escopo de visibilidade de classes internas: as classes internas têm escopo de visibilidade dentro da
    classe externa, o que significa que elas não podem ser acessadas fora da classe externa. No entanto, as
    classes internas podem ser instanciadas fora da classe externa, desde que sejam acessadas por meio de uma
    instância da classe externa.

    - Desempenho: o uso de classes internas pode afetar o desempenho do sistema devido à necessidade de criar
    instâncias adicionais de objetos. No entanto, em muitos casos, a diferença de desempenho é insignificante
    e não deve ser uma preocupação principal.

    - Encapsulamento: o encapsulamento é uma das principais vantagens da programação orientada a objetos, e
    as classes internas podem ajudar a melhorar a encapsulação. É importante manter os membros da classe
    interna privados sempre que possível e fornecer métodos de acesso apropriados para eles.

    - Uso de referências estáticas: as classes internas podem ser definidas como estáticas, permitindo que
    sejam acessadas diretamente pela classe externa, sem a necessidade de criar uma instância da classe interna.
    Isso pode ser útil em alguns casos, mas é importante lembrar que a classe interna pode ser mantida em
    memória por mais tempo do que a classe externa, o que pode causar problemas de vazamento de memória.

    - Interfaces internas: as classes internas também podem ser usadas para definir interfaces internas,
    permitindo que as classes externas implementem essas interfaces sem expor a interface publicamente.

    - Inner Classes podem ser usadas para implementar o padrão "Builder".
    Inner Classes podem ser usadas para implementar o padrão "Builder", que é um padrão de projeto de software
    que permite a criação de objetos complexos passo a passo. Ao definir uma Inner Class que atua como um
    construtor, é possível fornecer um controle mais granular sobre como um objeto é criado e configurado.

*/

public class Aula188InnerClass_sintaxe {

    public static void main(String[] args) {
        // Lembrando: main é um bloco estático, ou seja não é garantido
        // que o Objeto ja está "criado" em memória, portanto ainda
        // segue as mesmas regras sobre estáticos aonde não podemos
        // fazer acesso/referência a blocos não estáticos dentro dele.
        // Ou seja, não podemos acessar recursos do

        OuterClass.printInnerAtributeName();

        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.printOuterAtributeName();

        // Pontos importantes sobre o this: sempre será referência ao
        // prórpio objeto independente se é interno ou não, saída do this:
        outerClass.printOuterThis(); // classesInternas.OuterClass@4617c264
        innerClass.printInnerThis(); // classesInternas.OuterClass$InnerClass@36baf30c


    }
}

class OuterClass {
    private String outerName = "Luffy do outerClass";
    public class InnerClass {
        private String innerName = "Narutinho do innerClass";
        public InnerClass() {
        }

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

        public void printOuterAtributeName() {
            System.out.println("this.Inner printando OuterClass.this.outerName: ");

            System.out.println(OuterClass.this.getOuterName());
        }

        public void printInnerThis() {
            System.out.println(this);
            printInnerAtributeName(); // acesso a método estático mesmo não sendo estátio :O
        }
    }

    public static void printInnerAtributeName() {
        System.out.println("this.Outer printando Inner.this.innerName: ");

        // primeiro devemos criar um objeto do tipo externo OuterClass:
        // pois estamos em um bloco estático:
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();

        System.out.println(innerClass.getInnerName());
        String innerName = innerClass.innerName;
        System.out.println("Inner name privado: " + innerName);
    }

    public void printOuterThis() {
        System.out.println(this);
    }
    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }
}
