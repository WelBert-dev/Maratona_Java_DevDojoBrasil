package T_jdbc;

// Utilizamos o PreparedStatement para pré compilar a consulta SQL, evitando assim
// SQLInjection e melhorando o Desempenho.

/* Definições sobre PreparedStatement:

É uma classe fornecida pela API JDBC (Java Database Connectivity) que permite
criar consultas SQL parametrizadas. Ele é usado para melhorar a segurança,
desempenho e clareza do código ao interagir com um banco de dados relacional.

Ao contrário de uma declaração regular (Statement), que é usada para executar
consultas SQL diretas, um PreparedStatement é pré-compilado no momento da criação.
Isso significa que o banco de dados analisa, otimiza e armazena em cache o plano
de execução da consulta, resultando em um desempenho mais rápido quando a consulta
é executada várias vezes com diferentes parâmetros.

---> Aqui estão algumas das aplicações e utilidades:

    - Consultas parametrizadas: Permite criar consultas SQL com parâmetros marcados,
    representados por pontos de interrogação (?). Os valores dos parâmetros podem
    ser definidos posteriormente, evitando problemas de segurança, como ataques de
    injeção de SQL, pois os valores são tratados separadamente da consulta SQL em si.

    - Reutilização de consultas: Como o PreparedStatement é pré-compilado, ele pode
    ser reutilizado várias vezes com diferentes valores de parâmetros.
    Isso economiza o tempo necessário para analisar e otimizar a consulta toda vez
    que ela é executada.

    - Melhor desempenho: O pré-compilado do PreparedStatement resulta em um desempenho
    mais rápido do que o uso de declarações regulares. O banco de dados pode armazenar
    em cache os planos de execução das consultas parametrizadas, o que reduz o tempo
    de processamento.

    - Clareza do código: O uso de PreparedStatement torna o código mais legível e
    compreensível. A consulta SQL é definida com os marcadores de posição para os
    parâmetros, e os valores dos parâmetros são definidos separadamente, tornando
    o código mais organizado e fácil de entender.

    - Manipulação de tipos de dados: O PreparedStatement lida automaticamente com
    a conversão de tipos de dados, evitando a necessidade de conversões manuais.
    Ele fornece métodos específicos para definir valores de parâmetros com base
    em tipos de dados comuns, como inteiros, strings, datas, etc.

Em resumo, o PreparedStatement é usado para executar consultas SQL parametrizadas
de forma segura, eficiente e legível. Ele oferece vantagens como reutilização de ]
consultas, melhor desempenho, manipulação automática de tipos de dados e proteção
contra ataques de injeção de SQL.


---> Pontos importantes e atenções a se tomar:

1o - Consultas parametrizadas: Sempre utilize consultas parametrizadas ao criar
um PreparedStatement. Evite concatenar valores diretamente na consulta SQL, pois
isso pode abrir brechas para ataques de injeção de SQL.
Em vez disso, use pontos de interrogação (?) como marcadores de posição para os
parâmetros.

2o - Definindo valores de parâmetros: Utilize os métodos apropriados do
PreparedStatement para definir valores de parâmetros. Esses métodos, como
setInt(), setString(), setDate(), entre outros, garantem a correta manipulação
dos tipos de dados, convertendo-os adequadamente antes de serem enviados ao
banco de dados.

3o - Evite recompilações desnecessárias: Aproveite a reutilização do
PreparedStatement sempre que possível. Ao executar consultas múltiplas com
a mesma estrutura, mas com diferentes valores de parâmetros, reutilize o
PreparedStatement existente em vez de criar um novo a cada vez.
Isso economiza recursos e melhora o desempenho.

4o - Fechando recursos adequadamente: Certifique-se de fechar o PreparedStatement,
ResultSet e Connection corretamente após o uso. Para isso, utilize os métodos
close() ou utilize as estruturas try-with-resources para garantir que os recursos
sejam liberados adequadamente, mesmo em caso de exceções.

5o - Gerenciamento de transações: Se estiver trabalhando com transações,
certifique-se de usar o recurso de transação adequado. Você pode desativar
o modo de confirmação automática (auto-commit) da conexão e, em seguida,
gerenciar manualmente as transações utilizando os métodos commit() e rollback().

6o - Verificação de erros: Sempre trate as exceções adequadamente ao trabalhar
com PreparedStatement. Isso inclui capturar e tratar exceções específicas do JDBC,
como SQLExceptions, para lidar com erros de consulta, problemas de conexão, etc.
A manipulação adequada de exceções ajuda a identificar e solucionar problemas no
acesso ao banco de dados.

7o - Otimize consultas: O PreparedStatement ajuda a otimizar o desempenho, mas é
importante garantir que as consultas em si sejam eficientes. Certifique-se de
criar índices apropriados, utilizar cláusulas WHERE adequadas para filtrar
resultados e considerar o uso de técnicas de otimização de consultas do banco
de dados.

*/

import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aula268PreparedStatement_preCompilandoConsultasSQL_evitaSQLInjections_melhoraDesempenho {
    public static void main(String[] args) {
        // Estudos anteriores sem o PreparedStatement para tratar SQLInjection:
//        log.info(ProducerService.findByName("Herbert or X'='X"));
        // [Producer(id=1, name=Mad House), Producer(id=3, name=NHK), Producer(id=21, name=Herbert Richards), Producer(id=22, name=Dream Works), Producer(id=23, name=Maratona Java), Producer(id=27, name=Studios Deen), Producer(id=28, name=Studios Ghibili)]

        // Tratando SQLInjection com preCompilação do Statement usando PreparedStatement:
//        log.info(ProducerService.findByNamePreparedStatement("Herbert or X'='X"));
        // []

        // Tratando SQLInjection com preCompilação do Statement usando PreparedStatement: com LIKE %%%s%%
        log.info(ProducerService.findByNamePreparedStatement("Studio"));
        // [Producer(id=27, name=Studios Deen), Producer(id=28, name=Studios Ghibili)]
    }
}
