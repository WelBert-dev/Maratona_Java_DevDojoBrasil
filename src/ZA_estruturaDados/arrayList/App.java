package ZA_estruturaDados.arrayList;

import ZA_estruturaDados.models.ContatoModel;

public class App {
    public static void main(String[] args) {
        // Problemas: È possível quebrar o principio de que um array deve guardar os mesmos tipos
        VetorGenericoComObject vInteger = new VetorGenericoComObject(10);

        vInteger.add(1);vInteger.add(2);vInteger.add(3);vInteger.add(4);vInteger.add(5);

        System.out.println(vInteger);
        vInteger.add(10, 0);
        System.out.println(vInteger);
        vInteger.removeElementByElement(1);
        System.out.println(vInteger);
        vInteger.removeElementByIndex(1);
        System.out.println(vInteger);

        VetorGenericoComObject vFloat = new VetorGenericoComObject(10);

        vFloat.add("oi");
        vFloat.add(2F);
        vFloat.add(3F);
        vFloat.add(4F);
        vFloat.add(5F);

        System.out.println(vFloat);

        // Testa o mesmo porém com uma classe criada por mim.
        VetorGenericoComObject vContato = new VetorGenericoComObject(10);
        ContatoModel contato1 = new ContatoModel("Wellison", "(11) 9 4298-9935", "wellison@hotmail.com");
        ContatoModel contato2= new ContatoModel("Danielle", "(11) 9 4298-9935", "dani@hotmail.com");
        ContatoModel contato3 = new ContatoModel("Wellison", "(11) 9 4298-9935", "wellison.bertelli@hotmail.com");

        vContato.add(contato1);
        vContato.add(contato2);
        vContato.add(contato3);

        System.out.println(vContato);
        vContato.removeElementByIndex(1); // remove Danielle
        System.out.println(vContato);
        vContato.add(new ContatoModel("Pamonha", "(11) 9 9999-9999", "pamonha@hotmail.com"), 1);
        System.out.println(vContato);
        vContato.removeElementByElement(new ContatoModel("Pamonha", "(11) 9 9999-9999", "pamonha@hotmail.com"));
        System.out.println(vContato);
        System.out.println(vContato.getIndexOfElement(new ContatoModel("Wellison", "(11) 9 4298-9935", "wellison.bertelli@hotmail.com")));


        // Com generics  garante o principio de que Arrays referenciam objetos do mesmo tipo,
        // ao contrário da solução anterior aonde podemos atribuir tipos diferentes no Array
        // devido a lista ser do tipo Object.

        VetorGenericoComGenerics<ContatoModel> vContatoGenerics = new VetorGenericoComGenerics<ContatoModel>(10);

        vContatoGenerics.add(contato1);
        vContatoGenerics.add(contato2);
        vContatoGenerics.add(contato3);

        System.out.println(vContatoGenerics);
        vContatoGenerics.removeElementByIndex(1); // remove Danielle
        System.out.println(vContatoGenerics);
        vContatoGenerics.add(new ContatoModel("Pamonha", "(11) 9 9999-9999", "pamonha@hotmail.com"), 1);
        System.out.println(vContatoGenerics);
        vContatoGenerics.removeElementByElement(new ContatoModel("Pamonha", "(11) 9 9999-9999", "pamonha@hotmail.com"));
        System.out.println(vContatoGenerics);
        System.out.println(vContatoGenerics.indexOf(new ContatoModel("Wellison", "(11) 9 4298-9935", "wellison.bertelli@hotmail.com")));
        System.out.println("Antes: " + vContatoGenerics);
        vContatoGenerics.add(new ContatoModel("Danielle", "(11) 9 4298-9935", "dani@hotmail.com"), 0);
        vContatoGenerics.add(new ContatoModel("Danielle", "(11) 9 4298-9935", "dani@hotmail.com"), 2);
        System.out.println("Depois: " + vContatoGenerics);
        System.out.println(vContatoGenerics.lastIndexOf(new ContatoModel("Danielle", "(11) 9 4298-9935", "dani@hotmail.com")));
    }
}
