package D_internacionalizacao;

// Utilizada para internacionalização/tradução automṕatica
// de acordo com a Locale (Localização), baseado em
// chave-valor.
// Para realizar a tradução basta alterar o Locale na criação dp bundle.

import java.util.Locale;
import java.util.ResourceBundle;

public class Aula130ResourceBundle_Internacionalizacao_Util {
    public static void main(String[] args) {
        System.out.println(Locale.getDefault());

        // Cria o bundle passando a locale desejada para tradução no dicionário
        ResourceBundle bundleEnUS = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        System.out.println(bundleEnUS.getString("hello")); // Hello
        System.out.println(bundleEnUS.getString("good.morning")); // Good Morning

        // A chave pode mudar também, porém é interessante mante um padrão pois ai basta apenas
        // alterar o locale na criação do objeto e ai a tradução ocorre automaticamente
        // pois no restante do código a chamada (Key) é a mesma para os dois..
        // POLIMORFISMO, assim não precisamos realizar essas duplicatas:
        ResourceBundle bundlePtBR = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
        System.out.println(bundlePtBR.getString("hello")); // Olá
        System.out.println(bundlePtBR.getString("good.morning")); // Bom dia

        // Exemplo de lógica que toma a decisão e cria o Objeto de acordo com o Locale:

        Locale localeSelected;
        if (Global.getLocale().toString().equals("pt_BR")){
            localeSelected = new Locale("pt", "BR");
        } else if (Global.getLocale().equals("en_US")) {
            localeSelected = new Locale("en", "US");
        } else {
            localeSelected = Locale.getDefault();
        }
        System.out.println(Global.getLocale().toString());
        ResourceBundle bundleMain = ResourceBundle.getBundle("messages", localeSelected);
        System.out.println(bundleMain.getString("hello")); // Olá (A depender do locale)
        System.out.println(bundleMain.getString("good.morning")); // Bom dia (A depender do locale)


        // Verifica se contem alguma chave (key) la no .properties do bundle recém criado
        System.out.println(bundleMain.containsKey("hello")); // true
        System.out.println(bundleMain.containsKey("dream")); // false

        // Ordem de procura do bundle caso não encontre o Locale criado nos .properties:
        // Locale("fr", "CA");
        // messages_fr_CA.properties
        // messages_en_US.properties (Default do systema)
        // messages_en.properties
        // messages.properties
        // caso não encontre nenhum desses na ordem, ele lança uma exeção
        // Ou seja, ele vai executar em cascata, então podemos colocar em messages.properties
        // as palavras que são em comum entre as linguagens, assim
        // caso ele não encontre ele procurta neste arquivo genérico.

        System.out.println(bundleMain.getString("hi")); // ele procura no locale criado, não encontra então vai até
                                                            // o .properties mais genérico (messages.properties)
        // Exemplos de utilidade: Quando alguma palavra não possuí uma tradução correta em outros idiomas..
        // Também podemos colocar no bundle mais genérico as mensagens de Exception...
    }
}

class Global {
    public static Locale getLocale(){
        // MOCK
        return new Locale("en", "US");
    }
}
