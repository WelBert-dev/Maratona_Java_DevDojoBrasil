package colections;

// hashCode é o hash de um objeto, esse método também
// vem da classe Object e trabalha em conjunto com o equals()
// nele devemos escolher os mesmos atributos utilizados no equals()
// para gerar consistência entre os dois métodos.
// Ou seja, selecionamos os atributos que definem a regra de igualdade
// para o Objeto (qual atributo deve ser igual para o Objeto ser considerado igual?)

// Equals e HashCode são muito utilzados em Listas complexas e com maior performance,
// como HashSet e HashMap.
// Pois ao invés de comparar elemento a elemento da lista, eles utilizam
// o hashCode como index, assim podemos acessar o Objeto da lista diretamente
// sem precisar realizar a iteração elemento a elemento como algoritmos
// mais simples fazem.

// ATENÇÂO sobre colisões (quando se armazena dois Objetos com hashs iguais):
// Em Java, os objetos são geralmente armazenados em estruturas de dados que usam tabelas hash
// para permitir o acesso eficiente aos elementos. Quando um objeto é adicionado a uma tabela hash,
// o Java usa o método hashCode() desse objeto para determinar em qual slot da tabela ele deve ser armazenado.
//
// No entanto, é possível que dois objetos diferentes tenham o mesmo valor de hashCode(),
// o que é chamado de colisão de hash. Quando isso acontece, o Java armazena os objetos em
// uma lista vinculada associada ao slot da tabela hash correspondente. Essa lista é chamada
// de "lista de colisão" e permite que vários objetos sejam armazenados no mesmo slot da tabela hash.
//
// Quando um objeto é procurado na tabela hash, o Java primeiro usa o método hashCode() do objeto
// para determinar em qual slot da tabela ele pode estar. Em seguida, ele percorre a lista de colisão
// associada a esse slot para procurar o objeto correspondente. Esse processo pode ser menos eficiente
// do que simplesmente acessar o objeto diretamente na tabela hash, mas ainda é muito mais rápido
// do que pesquisar uma lista linearmente (Iterando elemento a elemento como em algoritmos simples).
//
// Para garantir que dois objetos diferentes com o mesmo valor de hashCode() sejam armazenados corretamente
// na lista de colisão, o Java também usa o método equals() dos objetos para verificar se eles são realmente
// iguais. Se dois objetos tiverem o mesmo hashCode() e o mesmo equals(), eles serão considerados iguais e
// apenas um será armazenado na tabela hash. Caso contrário, ambos serão armazenados na lista de colisão.
//
// Em resumo, o Java lida com colisões de hashCode em listas vinculadas associadas a cada slot da tabela hash
// correspondente. Quando ocorre uma colisão, vários objetos podem ser armazenados no mesmo slot, mas a eficiência
// ainda é mantida porque a busca na lista de colisão é mais rápida do que uma busca linear. O Java também usa o
// método equals() para garantir que objetos diferentes com o mesmo valor de hashCode() sejam armazenados
// corretamente na tabela hash.

// ATENÇÃO ao se trabalhar com Objetos mutáveis e hashCode pois o hash muda assim podendo gerar possíveis
// inconcistências ao utilizar métodos de busca e etc!
// Sempre utilizar atributos constantes para gerar os hashs e garantir imutabilidade.


public class Aula163hashCode_pt01 {
}
