package T_jdbc;

/* Explicações com desenho sobre o ResultSet:
- Seta é o cursor, inicialmente ele aponta para null;

ResultSet rs = stmt.executeQuery(query); ------------> null
                                                       [tbl_producer]________
                                                       | id | name          |
          rs.next() == true -------------------------> | 1  | Mad House     |
          rs.next() == true -------------------------> | 2  | Studio Ghibli |
          rs.next() == true -------------------------> | 3  | NHK           |
          rs.next() == true -------------------------> | 4  | Studios Deen  |
          rs.next() == false ------------------------> null

*/

// Para saber os tipos compatíveis do Java <=> MySQL (String == VARCHAR): MySQL type to Java Type

/* Definições sobre ResultSet:
- Ele é Clouseble (Ou seja, precisa fechar após abrir ou utilizar try-with-resources);

Na API JDBC em Java, ResultSet é uma interface que representa um conjunto de
resultados retornados por uma consulta SQL executada em um banco de dados.
O ResultSet é retornado pelo método executeQuery() da interface Statement ou
PreparedStatement.

O ResultSet é usado para iterar sobre os resultados da consulta e extrair os
valores das colunas correspondentes. Ele permite que o programador manipule os
dados do banco de dados recuperados por uma consulta SQL.

---> As aplicações e utilidades do ResultSet incluem:

    - Manipulação de dados do banco de dados: Permite que o programador acesse
    e manipule os dados do banco de dados recuperados por uma consulta SQL.
    Ele fornece métodos para acessar os valores das colunas, mover o cursor para
    a próxima linha, recuperar metadados da consulta, etc.

    - Processamento de resultados: Permite que o programador processe os resultados
    da consulta SQL retornados pelo banco de dados. Ele pode ser usado para iterar
    sobre os resultados, filtrá-los, ordená-los e agregá-los.

    - Exibição de dados em interfaces gráficas: Pode ser usado para exibir os
    resultados da consulta SQL em interfaces gráficas, como tabelas e listas.
    Ele fornece métodos para acessar os valores das colunas e manipular o
    conjunto de resultados.

    - Interação com outros aplicativos: Pode ser usado para interagir com outros
    aplicativos Java, como geradores de relatórios e aplicativos de planilha
    eletrônica. Ele permite que os dados recuperados por uma consulta SQL sejam
    usados em outros contextos.

Em resumo, o ResultSet é uma interface da API JDBC que representa um conjunto de
resultados retornados por uma consulta SQL executada em um banco de dados.
Ele é usado para iterar sobre os resultados da consulta, extrair os valores das
colunas correspondentes, processar os resultados e exibi-los em interfaces gráficas.
O ResultSet é uma ferramenta essencial para manipular dados do banco de dados em
aplicativos que interagem com bancos de dados relacionais.

---> Alguns dos principais métodos incluem:

    - next(): É usado para mover o cursor para a próxima linha do conjunto de
    resultados. Ele retorna um valor booleano que indica se a próxima linha
    existe ou não.

    - getString(int columnIndex): É usado para obter o valor de uma coluna como
    uma string, usando o índice ou nome da coluna.

    - getInt(int columnIndex): É usado para obter o valor de uma coluna como um
    inteiro, usando o índice ou nome da coluna.

    - getDouble(int columnIndex): É usado para obter o valor de uma coluna como
    um double, usando o índice ou nome da coluna.

    - getDate(int columnIndex): É usado para obter o valor de uma coluna como uma
    data, usando o índice ou nome da coluna.

    - wasNull(): É usado para determinar se o valor da coluna mais recente recuperado
    pelo ResultSet foi nulo.

    - getMetaData(): É usado para obter os metadados da consulta SQL, incluindo o
    número de colunas, os tipos de dados das colunas, etc.

---> Pontos importantes e atenções a se tomar:

    1o - Cursor: O cursor do ResultSet é usado para iterar sobre os resultados
    da consulta SQL. O programador deve ter cuidado para não mover o cursor para
    fora do conjunto de resultados. Além disso, é importante lembrar de fechar o
    ResultSet quando ele não for mais necessário.

    2o - Tipo de dado: O programador deve ter cuidado ao extrair os valores das
    colunas do ResultSet, pois os tipos de dados dos valores podem não corresponder
    aos tipos de dados esperados. É importante verificar o tipo de dados da coluna
    antes de extrair o valor.

    3o - Performance: O desempenho do ResultSet pode ser afetado por vários fatores,
    incluindo o tamanho do conjunto de resultados, a complexidade da consulta SQL e
    a velocidade do banco de dados. O programador deve estar ciente desses fatores e
    otimizar o código sempre que possível.

    4o - Controle de exceções: É importante controlar as exceções ao trabalhar com o
    ResultSet. As exceções podem ocorrer quando há problemas de conexão com o banco
    de dados, consultas SQL malformadas ou outros problemas. O programador deve estar
    preparado para lidar com essas exceções de forma apropriada.

    5o - Manipulação de grandes conjuntos de resultados: Se o conjunto de resultados
    for grande, pode ser necessário usar o método setFetchSize() para controlar o
    número de linhas recuperadas de uma vez.
    Isso pode ajudar a melhorar o desempenho do aplicativo e evitar problemas de
    memória.

    6o - Segurança: O programador deve estar ciente dos riscos de segurança ao
    trabalhar com o ResultSet, incluindo injeção de SQL e outros ataques.
    É importante validar todas as entradas do usuário e usar consultas
    parametrizadas para evitar esses tipos de problemas.

*/

import T_jdbc.domain.Producer;
import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Aula261ResultSet_readAll_notRecommendedInProduction_becauseSelectAllRecords {
    public static void main(String[] args) {
        List<Producer> allProducersList = ProducerService.findAll();

        log.info("Producers found: {}", allProducersList);
    }
}
