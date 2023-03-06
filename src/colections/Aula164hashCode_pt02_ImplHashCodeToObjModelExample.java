package colections;

// A implementação do hashCode na superClasse contem o token ´native´ na assinatura
// isso indica que o código desse método é escrito em outra linguagem nativa,
// como C ou C++
//
// Regras para a implementação do equals em um Object Model:
// - Dois objetos iguais geram hashs iguais:
//      se x.equals(y) == true, então y.hashCode() == x.hashCode();
// - Hashs iguais e objetos diferentes é possível: (Porisso da tabela de hashs, e buckets de armazenamento)
//      se y.hashCode() == x.hashCode() não necessáriamente o y.equals(x) tem que ser == true;
// - Objetos diferentes geram hashs diferentes:
//      se y.hashCode() != x.hashCode() então o x.equals(y) tem que ser == false;


// Caso queira personalizar a geração do hashCode e deixar consistente o contrato entre equals e hashCode
// em casos de equals personalizados que utilizam apenas um dos atributos como flag de igualdade
// podemos chamar o atributo (desde que o mesmo seja um Objeto Wrapper do java) e delegar
// a responsabilidade de geraçaõ do hash para o java, chamando o hashCode do atributo:
// return this.keyIsEquals.hashCode();
// Macete: Quando o atributo desejado para gerar o hashCode for um tipo primitivo,
// podemos envolve-lo em um Wrapper e chamar o método hashCode:
// Long.valueOf(keyIdIsEquals).hashCode(); <- exemplo utilizando Long

public class Aula164hashCode_pt02_ImplHashCodeToObjModelExample {
}

class SmartphoneHash {

    private String keyIsEquals;
    private long keyIdIsEquals;

    @Override
    public int hashCode() { // sempre delegar a responsabilidade de geração do hash para o Java
        // return super.hashCode();
        // return this.keyIsEquals.hashCode();
        return Long.valueOf(keyIdIsEquals).hashCode();
       //
    }
}