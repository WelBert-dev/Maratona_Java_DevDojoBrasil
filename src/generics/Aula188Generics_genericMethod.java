package generics;


import estruturaDados.models.PatientModel;
import generics.dominio.CarModel;

import java.util.List;

/* 1o - Como declarar métodos genéricos sem precisar tornar a classe genérica como um todo:

    - A-) Para criar apenas métodos genéricos sem precisar tornar a classe como um todo,
    é preciso informar na assinatura do método o <T> após o modificador de acesso:

                  Aqui
                    ⬇️
    private static <T> void createArrayWithObject(T t) {
        ...
    }

    - B-) Mesmo código porém com retorno (exemplo: List<T>):

        private static <T> List<T> createArrayWithObject(T t) {
            ...
            return ...
        }

    - C-) Para delimitar os tipos possíveis de entrada no parâmetro do método:
        - wildcards with bounded types (extends ou super);
      Exemplo: Neste exemplo, extamos delimitando as possibilidades de entrada de T t para
      aceitar apenas classes que implementam "Comparable<T>":

        private static <T extends Comparable> List<T> createArrayWithObject(T t) {
            ...
            return ...
        }

*/

/* Pontos importantes e atenções a se tomar quando se trabalha com Métodos Genéricos <T>:

    - Inferência de tipo: em Java 7, foi introduzida a inferência de tipos em construtores
    e métodos genéricos, o que permite que o tipo de objeto retornado ou o tipo de argumentos
    de entrada sejam inferidos pelo compilador sem a necessidade de especificação manual,
    tornando mais fácil escrever código genérico sem precisar especificar os tipos explicitamente.
    Isso pode ser feito usando o operador de diamante <>.

    - Bridge methods: em algumas situações, é necessário que o compilador gere métodos adicionais,
    conhecidos como "bridge methods", para garantir a compatibilidade com versões anteriores e a
    correta implementação de classes genéricas.

    - Métodos genéricos podem ser sobrecarregados: é possível ter vários métodos genéricos com a
    mesma assinatura, mas tipos genéricos diferentes. Isso pode ser útil em situações em que
    diferentes tipos precisam ser processados de maneira diferente.

    - Métodos genéricos podem ser usados em conjunto com lambdas: os métodos genéricos podem ser
    usados em conjunto com lambdas para criar funções genéricas que possam ser usadas com vários
    tipos. Isso pode tornar o código mais conciso e fácil de ler.

    - Wildcards podem ser usados para aumentar a flexibilidade: wildcards podem ser usados para
    permitir que tipos genéricos sejam mais flexíveis.
    Por exemplo, o wildcard ? extends T significa que o tipo deve ser uma subclasse de T,
    enquanto o wildcard ? super T significa que o tipo deve ser uma superclasse de T.

    - Tipos genéricos raw: (EVITAR uso) Os tipos genéricos raw em Java referem-se a usos de tipos
    genéricos sem especificar o tipo de parâmetro de tipo genérico.
    Por exemplo, em vez de usar List<String>, um programador pode usar simplesmente List, que é uma
    versão raw de List<T>.
    Isso pode ser útil em algumas situações, mas também pode causar problemas se não for usado com
    cuidado.

    - Métodos genéricos podem ser usados para evitar código duplicado: em vez de escrever vários
    métodos que fazem a mesma coisa para diferentes tipos, é possível escrever um único método
    genérico que funciona para todos os tipos.
    Isso pode economizar tempo e esforço e tornar o código mais fácil de manter.

    - Métodos genéricos podem ser usados em conjunto com interfaces funcionais: os métodos genéricos
    podem ser usados em conjunto com interfaces funcionais para criar funções genéricas que possam
    ser passadas como parâmetros para outros métodos.
    Isso pode tornar o código mais flexível e reutilizável.

    - Erros de compilação podem ser difíceis de entender: quando ocorrem erros de compilação
     em métodos genéricos, eles podem ser difíceis de entender, pois os tipos genéricos são
     removidos durante a compilação. É importante prestar atenção aos erros de compilação e
     tentar entender qual é o problema subjacente.

    - O uso de tipos genéricos pode afetar o desempenho: o uso excessivo de tipos genéricos pode
    afetar o desempenho do programa, pois pode levar a autoboxing e autounboxing desnecessários.
    É importante ser cauteloso ao usar tipos genéricos e tentar minimizar seu uso, especialmente
    em loops ou em código que é executado repetidamente.

    - É importante documentar o uso de tipos genéricos: é importante documentar claramente o uso
    de tipos genéricos em métodos, para que outros programadores possam entender como o método
    funciona e quais tipos são permitidos.
    Isso pode facilitar a manutenção do código e evitar erros de compilação no futuro.

    - Métodos genéricos podem ser usados em conjunto com a reflexão: é possível usar métodos
    genéricos em conjunto com a reflexão para criar código dinâmico que possa trabalhar com
    diferentes tipos em tempo de execução.
    Isso pode ser útil em situações em que o tipo de dados não é conhecido até o tempo de execução.

    - Métodos genéricos podem ser usados para criar classes genéricas: embora este ponto esteja
    relacionado à criação de classes genéricas, é importante notar que métodos genéricos podem
    ser usados para criar classes genéricas.
    Isso pode ser útil para criar classes que trabalham com diferentes tipos de dados, como listas,
    árvores e mapas genéricos.

    - É importante entender os bounds dos tipos genéricos: é importante entender os bounds (limites)
    dos tipos genéricos em métodos, para que seja possível garantir que apenas tipos específicos sejam
    passados como parâmetros.
    Isso pode evitar erros de compilação e garantir que o código funcione corretamente.

    - Métodos genéricos podem ser usados em conjunto com a anotação @SuppressWarnings: a anotação
    @SuppressWarnings pode ser usada para desativar os warnings do compilador relacionados ao uso
    de tipos genéricos. No entanto, é importante usar essa anotação com cautela e apenas quando for
    absolutamente necessário.

    - Métodos genéricos podem ser usados em conjunto com a anotação @SafeVarargs: a anotação
    @SafeVarargs pode ser usada para indicar ao compilador que um método varargs (que aceita
    um número variável de argumentos) é seguro para tipos genéricos.
    Isso pode ajudar a evitar warnings do compilador relacionados a tipos genéricos em métodos
    varargs.

    - Métodos genéricos podem ser usados para criar APIs flexíveis: os métodos genéricos podem
    ser usados para criar APIs flexíveis que possam ser usadas com diferentes tipos de dados.
    Isso pode tornar o código mais reutilizável e fácil de manter.

    - É importante ter cuidado ao usar tipos genéricos em métodos assíncronos: ao usar tipos
    genéricos em métodos assíncronos, é importante garantir que o código seja seguro para
    threads e evite condições de corrida. É importante usar os recursos apropriados do Java,
    como o ConcurrentHashMap, para garantir a segurança da thread.

    - É importante entender a diferença entre tipos genéricos e tipos curinga: tipos genéricos
    são tipos que incluem um parâmetro de tipo, enquanto tipos curinga são tipos que não incluem
    um parâmetro de tipo específico. É importante entender a diferença entre os dois tipos e saber
    quando usar cada um.

*/

public class Aula188Generics_genericMethod {
    public static void main(String[] args) {
        // Testando A-) Podemos passar qualquer tipo:
        System.out.println("-------------------------------------[Testando A-) Podemos passar qualquer tipo:]----------------------------------");
        System.out.println("");
        createArrayWithObjectReturnsVoid(new PatientModel()); // Tipo do listT: estruturaDados.models.PatientModel
        createArrayWithObjectReturnsVoid(new CarModel("BMW")); // Tipo do listT: generics.dominio.CarModel
        createArrayWithObjectReturnsVoid(50); // Tipo do listT: java.lang.Integer

        // Testando B-) Método com o mesmo contexto, porém útilizando RETURN:
        System.out.println("-------------------------------------[Testando B-) Método com o mesmo contexto, porém útilizando RETURN:]----------------------------------");
        List<PatientModel> patientsList = createArrayWithObjectReturnsList(new PatientModel());
        List<CarModel> carsList = createArrayWithObjectReturnsList(new CarModel("BMW"));
        List<Integer> integersList = createArrayWithObjectReturnsList(50);

        // Testando C-) Método com o mesmo contexto, porém utilizando DELIMITADOR
        // wildcard with bounded types (extends or super):
        // Exemplo: utilizando ps <T extends Comparable> List<T>
        System.out.println("-------------------------------------[Testando C-) Método com o mesmo contexto, porém utilizando DELIMITADOR]----------------------------------");
        // Aonde "CarModel" NÃO implements Comparable logo NÃO passa no filtro. Nem COMPILA:
        //createArrayWithObjectReturnsListAndDelimiterWithWildcardAndBoundedTypes(new CarModel("BMW"));

        // Aonde "PatientModel" implements Comparable logo passa no teste e retorna Lista. Logo COMPILA:
        createArrayWithObjectReturnsListAndDelimiterWithWildcardAndBoundedTypes(new PatientModel());

    }


    // A-) Criando um método genérico sem precisar tornar a classe genérica como um todo:
    public static <T> void createArrayWithObjectReturnsVoid(T t){
        List<T> listT = List.of(t); // tipo será criado de acordo com a passagem.
        System.out.println(listT);
        // Utilizando reflexão para "burlar" o type erasure (que apaga o tipo em tempo de compilação)
        // para pegar esse tipo em tempo de execução:
        Class<?> clazz = listT.get(0).getClass();
        System.out.println("Tipo do listT: "+clazz.getName());

        // Obs sobre essa abordagem com uso de reflexão:
        // Se a lista estiver vazia, você pode usar o método getComponentType() da classe Class para
        // obter a classe do tipo genérico. Aqui está um exemplo de código:

        // ERROR Não esta sendo possível com esse:
//        List<?> lista = new ArrayList<?>();
//        Class<?> classe = lista.getClass().getComponentType();
//        System.out.println(classe.getName()); // Imprime "java.lang.Object"

        // Sempre vai cair no bloco else mais aninhado, pois o Java nunca vai saber
        // qual é o tipo da classe e muito menos da superClasse mais genérica (Object)
        // pois utilizamos o wildcard "?" na criação da coleção List
        // List<?> lista = new ArrayList<>();

//        List<?> lista = new ArrayList<>();
//        Type type = lista.getClass().getGenericSuperclass();
//
//        if (type instanceof ParameterizedType) {
//            ParameterizedType parameterizedType = (ParameterizedType) type;
//            Type[] typeArguments = parameterizedType.getActualTypeArguments();
//
//            if (typeArguments.length > 0 && typeArguments[0] instanceof Class<?>) {
//                Class<?> classe = (Class<?>) typeArguments[0];
//                System.out.println(classe.getName());
//            } else {
//                // Não foi possível obter o tipo genérico da lista
//                // Lançar uma exceção ou utilizar um tipo padrão
//                System.out.println("Não foi possível obter o tipo genérico da lista\n" +
//                        "Lançar uma exceção ou utilizar um tipo padrão");
//            }
//        } else {
//            // A lista não possui um tipo genérico especificado
//            // Lançar uma exceção ou utilizar um tipo padrão
//            System.out.println("A lista não possui um tipo genérico especificado\n" +
//                    "Lançar uma exceção ou utilizar um tipo padrão");
//        }

    }

    // B-) Mesmo contexto anterior, porém com RETURN:
    public static <T> List<T> createArrayWithObjectReturnsList(T t){
        List<T> listT = List.of(t);
        return listT;
    }

    // C-) Mesmo contexto anterior, porém delimitando as possibilidades de entrada
    // utilziando wildcard with bounded types (extends or super):
    public static <T extends Comparable> List<T> createArrayWithObjectReturnsListAndDelimiterWithWildcardAndBoundedTypes(T t){
        List<T> listT = List.of(t);

        Class<?> clazz = listT.get(0).getClass();
        System.out.println("Tipo do listT: "+clazz.getName());
        System.out.println("Passa no teste É UM COMPARABLE!");

        return listT;
    }
}
