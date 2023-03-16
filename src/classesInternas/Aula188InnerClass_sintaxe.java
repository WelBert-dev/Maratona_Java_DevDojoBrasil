package classesInternas;

// Inner Class ou Classes internas são classes dentro de classes,
// utilizada apenas quando a outerClass realmente precisa separar
// algum comportamento e garantir a coesão do código e além disto
// APENAS a outerClass utilize a Inner Class!!!

// Introdução ao contexto de Classes internas, aqui está explicações
// sobre a sintaxe:

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
        }
    }

    public static void printInnerAtributeName() {
        System.out.println("this.Outer printando Inner.this.innerName: ");

        // primeiro devemos criar um objeto do tipo externo OuterClass:
        // pois estamos em um bloco estático:
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();

        System.out.println(innerClass.getInnerName());

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
