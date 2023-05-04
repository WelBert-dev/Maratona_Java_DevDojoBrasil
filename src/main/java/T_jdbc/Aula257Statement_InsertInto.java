package T_jdbc;

/* Definições sobre Statement:

Na API JDBC em Java, Statement é uma interface que define métodos para enviar
instruções SQL para um banco de dados.

Um objeto Statement é criado a partir de uma conexão com o banco de dados e
usado para enviar consultas SQL ou atualizações para o banco de dados.
O Statement é uma das formas de enviar instruções SQL para o banco de dados
usando JDBC.

---> As aplicações e utilidades do Statement incluem:

    - Execução de consultas SQL: É usado para enviar consultas SQL para o banco
    de dados. As consultas podem ser do tipo SELECT, que retornam um conjunto de
    resultados, ou do tipo UPDATE, INSERT ou DELETE, que modificam os dados no
    banco de dados.

    - Processamento de resultados: Quando uma consulta SELECT é executada, o
    Statement retorna um objeto ResultSet que contém o conjunto de resultados.
    O ResultSet pode ser usado para iterar sobre as linhas retornadas pela
    consulta e extrair os valores das colunas.

    - Prevenção de ataques de injeção de SQL: Ao usar um Statement, as consultas
    SQL são pré-compiladas e enviadas ao banco de dados como uma única unidade.
    Isso ajuda a evitar ataques de injeção de SQL, pois os dados inseridos pelo
    usuário são tratados como parâmetros em vez de serem concatenados com a
    consulta SQL.

    - Melhoria do desempenho: O Statement pode ser usado para enviar várias
    consultas SQL para o banco de dados como uma única transação.
    Isso melhora o desempenho do aplicativo, pois reduz o número de chamadas
    para o banco de dados.

    - Suporte a diferentes tipos de bancos de dados: O Statement é independente
    do banco de dados e pode ser usado com vários tipos de bancos de dados,
    incluindo MySQL, Oracle, Microsoft SQL Server e PostgreSQL.

Em resumo, o Statement é uma interface da API JDBC que permite que os desenvolvedores
enviem instruções SQL para um banco de dados relacional. Ele é usado para executar
consultas SQL, processar resultados, evitar ataques de injeção de SQL, e em alguns
casos otimizar o desempenho da consulta.

---> Alguns dos principais métodos incluem:

    - executeQuery(String sql): É usado para enviar uma instrução SQL do tipo
    SELECT para o banco de dados. Ele retorna um objeto ResultSet que contém o
    conjunto de resultados da consulta.

    - executeUpdate(String sql): É usado para enviar uma instrução SQL do tipo
    INSERT, UPDATE ou DELETE para o banco de dados. Ele retorna o número de
    linhas afetadas pela operação.

    - execute(String sql): É usado para enviar uma instrução SQL para o banco de
    dados. Ele retorna um valor booleano que indica se a instrução foi executada
    com sucesso.

    - getResultSet(): É usado para obter o conjunto de resultados da última consulta
    executada no objeto Statement. Ele retorna um objeto ResultSet que pode ser
    usado para iterar sobre as linhas do conjunto de resultados.

    - getUpdateCount(): É usado para obter o número de linhas afetadas pela última
    operação de inserção, atualização ou exclusão executada no objeto Statement.

    - setFetchSize(int rows): É usado para definir o tamanho do lote (batch) de
    resultados que serão buscados do banco de dados. Ele é usado para otimizar o
    desempenho da consulta, reduzindo o número de chamadas ao banco de dados.

    - addBatch(String sql): É usado para adicionar uma instrução SQL ao lote (batch)
    de comandos que serão executados no objeto Statement.

    - executeBatch(): É usado para executar o lote (batch) de comandos adicionados
    ao objeto Statement. Ele retorna um array de inteiros que contém o número de
    linhas afetadas por cada comando no lote.

*/

import T_jdbc.domain.Producer;
import T_jdbc.repository.ProducerRepository;

public class Aula257Statement_InsertInto {
    public static void main(String[] args) {
        Producer nhkProducer = Producer.builder()
                .name("Studios Deen 8")
                .build();

        ProducerRepository.save(nhkProducer);
    }
}
