package T_jdbc;

/* Definições sobre DatabaseMetaData

A interface DatabaseMetaData faz parte do pacote JDBC (Java Database Connectivity)
e é usada para obter informações detalhadas sobre o banco de dados ao qual você
está conectado. Ela fornece métodos para recuperar informações sobre o banco de
dados subjacente, como suas tabelas, colunas, índices, procedimentos armazenados,
capacidades SQL, etc. Essas informações são úteis para várias aplicações, como
ferramentas de administração de bancos de dados, geração de código automatizada,
documentação de banco de dados e muito mais.

---> Aqui estão algumas das aplicações e utilidades:

    - Descoberta de metadados: Podemos usar os métodos fornecidos por ele para
    descobrir informações sobre as tabelas presentes no banco de dados, como
    seus nomes, colunas, tipos de dados, restrições, etc.
    Essas informações podem ser usadas para criar consultas dinâmicas, gerar
    código automaticamente ou fornecer informações aos usuários sobre a
    estrutura do banco de dados.

    - Geração de código automática: Com base nas informações obtidas dele, é
    possível gerar automaticamente código Java ou SQL para mapear as tabelas
    do banco de dados em objetos Java ou para criar consultas SQL adequadas.
    Isso pode economizar muito tempo e esforço no desenvolvimento de aplicativos
    que interagem com o banco de dados.

    - Validação de recursos suportados: Permite que você obtenha informações
    sobre os recursos suportados pelo banco de dados, como os tipos de dados
    suportados, as capacidades SQL compatíveis, as funções e procedimentos
    armazenados disponíveis e muito mais.
    Isso é útil para garantir a compatibilidade entre o seu código e o banco
    de dados subjacente.

    - Documentação do banco de dados: As informações obtidas dela podem ser
    usadas para criar documentação automática do banco de dados. podemos gerar
    um documento que descreva a estrutura do banco de dados, as tabelas, colunas,
    relacionamentos e outras informações relevantes.

    - Manutenção do banco de dados: Também fornece informações sobre as restrições
    de integridade referencial, índices e outras propriedades do banco de dados.
    Essas informações podem ser usadas para auxiliar na manutenção do banco de
    dados, como verificação de integridade, otimização de consultas, etc.

Em resumo, o DatabaseMetaData é uma interface que fornece métodos para obter
informações detalhadas sobre o banco de dados subjacente. Ele é usado para
várias aplicações que envolvem interação com o banco de dados, como geração
de código automática, descoberta de metadados, documentação, manutenção do
banco de dados, entre outros.


*/

/* Definições sobre ResultSet.TYPE_FORWARD_ONLY: (Cursor não pode navegar aleatóriamente)

É uma das constantes usadas para definir o tipo de cursor em um objeto ResultSet.
Um ResultSet é um objeto que mantém os resultados de uma consulta SQL executada
em um banco de dados.

ResultSet.TYPE_FORWARD_ONLY é usado para criar um ResultSet que permite percorrer
os resultados apenas para frente, ou seja, do primeiro ao último registro.
Você não pode retroceder ou mover-se aleatoriamente pelos registros usando esse
tipo de cursor.

A principal vantagem de usar ResultSet.TYPE_FORWARD_ONLY é que ele consome menos
recursos de memória e é mais eficiente em termos de desempenho. Como ele não precisa
armazenar todos os registros na memória, é mais adequado quando você precisa apenas
ler os registros sequencialmente, sem a necessidade de retroceder ou acessar
registros específicos de forma não sequencial.

Por exemplo, se você estiver processando um grande conjunto de resultados e não
precisar acessar registros anteriores, usar ResultSet.TYPE_FORWARD_ONLY pode ajudar
a economizar recursos e melhorar a velocidade de processamento.

No entanto, é importante notar que nem todos os bancos de dados ou drivers JDBC
suportam ResultSet.TYPE_FORWARD_ONLY. Portanto, é sempre recomendável verificar
a documentação do driver específico que você está usando para confirmar se esse
tipo de cursor é suportado.

Em resumo, ResultSet.TYPE_FORWARD_ONLY é uma constante usada para definir o tipo
de cursor em um objeto ResultSet, permitindo percorrer os resultados apenas para
frente. Isso é útil quando você precisa apenas ler os registros sequencialmente,
economizando recursos de memória e melhorando o desempenho.

OBS: Ele possuí esse comportamento de apenas permitir percorrer sequencialmente
os registros pois a consulta é realizada a medida em que percorremos os recultados
ou seja, ele não realiza a consulta e mantém todos registros em memória, vai
retornando a medida em que o cursor vai percorrendo. Esse comportamento é parecido
com uma LinkedList, PORÉM não é utilizado ela em lowlevel!!!

OBS2: No entanto, o uso de um ResultSet.TYPE_FORWARD_ONLY não significa
necessariamente que os registros estão sendo consultados no banco de dados
a cada iteração. Na prática, o driver JDBC geralmente busca um número fixo
de registros antecipadamente e os armazena em buffer na memória.
Conforme você itera sobre o ResultSet, os registros são lidos do buffer em
memória, sem a necessidade de acessar novamente o banco de dados para cada
registro individual. Esse mecanismo de buffering pode ajudar a mitigar a
latência de comunicação com o banco de dados.

*/

/* Definições sobre ResultSet.CONCUR_UPDATABLE (Define a capacidade de alterações em um ResultSet)

É uma constante usada para definir a capacidade de atualização de um ResultSet.
Essa constante é usada juntamente com outras constantes para definir o tipo e a
concorrência do cursor ao criar um ResultSet a partir de uma consulta SQL.

Ao usar ResultSet.CONCUR_UPDATABLE, você está indicando que o ResultSet permite
atualizações diretas nos registros do banco de dados subjacente.
Isso significa que você pode modificar os valores das colunas no ResultSet e, em
seguida, aplicar essas alterações de volta ao banco de dados usando os métodos
apropriados.

A principal utilidade de ResultSet.CONCUR_UPDATABLE é fornecer uma maneira
conveniente de atualizar registros do banco de dados sem a necessidade de
escrever instruções SQL adicionais. Com esse modo, você pode atualizar
diretamente os valores das colunas no ResultSet, chamando métodos como
updateInt(), updateString(), updateDate(), entre outros, para alterar os
valores das colunas no registro atual.

Após fazer as atualizações desejadas no ResultSet, você pode chamar o método
updateRow() para aplicar as alterações no banco de dados.
Isso atualiza o registro atual no banco de dados com os valores atualizados
no ResultSet.

É importante observar que nem todos os ResultSet suportam a atualização direta.
A capacidade de atualização depende do driver JDBC e do banco de dados subjacente.
Nem todos os bancos de dados suportam atualizações diretas em um ResultSet, e
alguns drivers JDBC podem impor restrições adicionais. Portanto, é sempre
recomendável verificar a documentação do driver JDBC específico e as capacidades
do banco de dados para determinar se ResultSet.CONCUR_UPDATABLE é suportado.

Em resumo, ResultSet.CONCUR_UPDATABLE é uma constante usada para definir a
capacidade de atualização de um ResultSet. Quando usado, permite que você
modifique os valores das colunas no ResultSet e aplique essas alterações
diretamente ao banco de dados.
Isso fornece uma maneira conveniente de atualizar registros sem a necessidade
de instruções SQL adicionais. No entanto, a capacidade de atualização depende
do driver JDBC e do banco de dados subjacente.

*/

import T_jdbc.service.ProducerService;

public class Aula264DatabaseMetaData_metadadosDoDriver_analisandoComportamentoDoDriverComoUmTodo {
    public static void main(String[] args) {
        ProducerService.showDriverMetaData();
        // Showing driver metadada..
        // TYPE_FORWARD_ONLY: O Driver suporta navegações unidirecionais, ou seja podemos navegar do fim para o inicio e vici versa nos records
        // & CONCUR_UPDATABLE: O Driver suporta alterações nos registros enquanto percorremos unidirecionalmente os registros, ou seja UPDATABLE

        // TYPE_SCROLL_INSENSITIVE: O Driver suporta navegações unidirecionais, ou seja podemos navegar do fim para o inicio e vici versa nos records PORÉM não reflete as alterações aqui feitas no banco de dados...
        // & CONCUR_UPDATABLE: O Driver suporta alterações nos registros enquanto percorremos unidirecionalmente os registros, ou seja UPDATABLE
    }
}
