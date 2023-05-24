package T_jdbc.repository;

import T_jdbc.conn.ConnectionFactory;
import T_jdbc.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProducerRepository {
    public static void save(Producer producer) {
        String query = "INSERT INTO `db_anime_store`.`tbl_producer` (`name`)\n" +
                        "VALUES ('%s');".formatted(producer.getName());

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            log.info("Quantidade de records afetados: {}", rowsAffected);
            System.out.println("Passou pelo save");
            // Qualquer alteração nos records/registros que a query for gerar utiliza `executeUpdate()`.
        } catch (SQLException e) {
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
        }
    }
    public static void delete(int id) {
        String query = "DELETE FROM `db_anime_store`.`tbl_producer`\n" +
                       "WHERE (`id` = %d);".formatted(id);

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            log.info("Deleted producer '{}' from the database, rows affected '{}'", id, rowsAffected);
            // Qualquer alteração nos records/registros que a query for gerar utiliza `executeUpdate()`.
        } catch (SQLException e) {
            log.error("Error while trying to delete producer '{}'", id, e);
        }
    }
    public static void update(Producer producer) {
        String query = ("UPDATE `db_anime_store`.`tbl_producer` " +
                        "SET `name` = '%s' " +
                        "WHERE (`id` = %d);").formatted(producer.getName(), producer.getId());

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            log.info("Updated producer '{}' from the database, rows affected '{}'", producer.getName(), rowsAffected);
            // Qualquer alteração nos records/registros que a query for gerar utiliza `executeUpdate()`.
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producer.getName(), e);
        }
    }
    public static List<Producer> findAll() {
        log.info("Finding ALL Producers");

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`;";

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");

                producersList.add(Producer.builder()
                                .id(id)
                                .name(name)
                                .build());
            }

            // Qualquer query com retorno de registros utiliza `executeQuery()`.
            // Returns ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }

        return producersList;
    }
    public static List<Producer> findByName(String name) {
        log.info("Finding ALL Producers with name like '{}'", name);

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`\n" +
                       "WHERE `name` LIKE '%%%s%%';".formatted(name);

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                var producerId = rs.getInt("id");
                var producerName = rs.getString("name");

                producersList.add(Producer.builder()
                        .id(producerId)
                        .name(producerName)
                        .build());
            }

            // Qualquer query com retorno de registros utiliza `executeQuery()`.
            // Returns ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            log.error("Error while trying to find all producers with name like {}", name, e);
        }

        return producersList;
    }
    public static void showProducerMetaData() {
        log.info("Showing tbl_producer metadada.. ");

        String query = "SELECT * FROM `db_anime_store`.`tbl_producer`;";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            rs.next();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                log.info("------------------------------------------------------------");
                log.info("Table Name: '{}'", metaData.getTableName(i));
                log.info("Column Name: '{}'", metaData.getColumnName(i));
                log.info("Column Name Type: '{}'", metaData.getColumnTypeName(i));
                log.info("Column Label: '{}'", metaData.getColumnLabel(i));
                log.info("Column Catalog name: '{}'", metaData.getCatalogName(i));
                log.info("Column Class Name: '{}'", metaData.getColumnClassName(i));
                log.info("Column Display Size: '{}'", metaData.getColumnDisplaySize(i));
                log.info("Precision: '{}'", metaData.getPrecision(i));
                log.info("Scale: '{}'", metaData.getScale(i));
                log.info("Schema Name: '{}'", metaData.getSchemaName(i));
                log.info("Column '{}' isAutoIncrement? : '{}'",metaData.getColumnName(i),
                                                                       metaData.isAutoIncrement(i));
                log.info("Column '{}' isCurrency? : '{}'",metaData.getColumnName(i),
                                                                  metaData.isCurrency(i));
                log.info("Column '{}' isNullable? : '{}'",metaData.getColumnName(i),
                                                                  metaData.isNullable(i));
                log.info("Column '{}' isCaseSensitive? : '{}'",metaData.getColumnName(i),
                                                                       metaData.isCaseSensitive(i));
                log.info("Column '{}' isSearchable? : '{}'",metaData.getColumnName(i),
                                                                    metaData.isSearchable(i));
                log.info("Column '{}' isReadOnly? : '{}'",metaData.getColumnName(i),
                                                                  metaData.isReadOnly(i));
                log.info("Column '{}' isWritable? : '{}'",metaData.getColumnName(i),
                                                                  metaData.isWritable(i));
                log.info("Column '{}' isSigned? : '{}'",metaData.getColumnName(i),
                                                                metaData.isSigned(i));
                log.info("Column '{}' isDefinitelyWritable? : '{}'",metaData.getColumnName(i),
                                                                            metaData.isDefinitelyWritable(i));
                log.info("------------------------------------------------------------");
            }

        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }
    }
    public static void showDriverMetaData() {
        log.info("Showing driver metadada.. ");

        String query = "SELECT * FROM `db_anime_store`.`tbl_producer`;";

        try (Connection conn = ConnectionFactory.getConnection()) {
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            // ResultSet Types é a forma como podemos navegar nos registros
            // Unidirecionalmente, apenas de cima para baixo (do primeiro registro
            // até o ultimo, ou do ultimo até o primeiro..
            // E Além disto, verificamos se pra cada tipo desse, ele suporta
            // alterações enquanto percorre e etc...
            // Ou seja, informações sobre o cursor do resultset
            if(databaseMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                log.info("TYPE_FORWARD_ONLY: O Driver NÃO suporta navegações unidirecionais, ou seja " +
                        "não podemos navegar do fim para o inicio e vici versa nos records");

                // Verificando se além de navegar unidirecional podemos alterar os registros
                // enquanto percorremos os registros de cima para baixo e vici versa...
                if (databaseMetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY,
                                                                  ResultSet.CONCUR_UPDATABLE)) {
                    log.info("& CONCUR_UPDATABLE: O Driver suporta alterações nos registros enquanto percorremos " +
                            "unidirecionalmente os registros, ou seja UPDATABLE");
                }
            }

            // TYPE_SCROLL_INSENSITIVE indica que podemos navegar unidirecionalmente,
            // porém as alterações feitas nele não refletem no banco de dados..
            // ou seja, mantém apenas em Cache essas mudanças...
            if(databaseMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                log.info("TYPE_SCROLL_INSENSITIVE: O Driver suporta navegações unidirecionais, ou seja " +
                        "podemos navegar do fim para o inicio e vici versa nos records " +
                        "PORÉM não reflete as alterações aqui feitas no banco de dados...");

                // Verificando se além de navegar unidirecional podemos alterar os registros
                // enquanto percorremos os registros de cima para baixo e vici versa...
                if (databaseMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                  ResultSet.CONCUR_UPDATABLE)) {
                    log.info("& CONCUR_UPDATABLE: O Driver suporta alterações nos registros enquanto percorremos " +
                            "unidirecionalmente os registros, ou seja UPDATABLE");
                }
            }

            // TYPE_SCROLL_SENSITIVE indica que podemos navegar unidirecionalmente,
            // porém ao contrário do anterior, aqui as mudanças feitas no banco de dados
            // são refletidas neste RESULTSET, ou seja, não será necessário realizar
            // novas consultas, pois as mudanças lá seram refletidas aqui também!
            // OBS: Poucos drivers suportam essa funcionalidade devido a complexidade.
            if(databaseMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                log.info("TYPE_SCROLL_SENSITIVE: O Driver suporta navegações unidirecionais, ou seja " +
                        "podemos navegar do fim para o inicio e vici versa nos records " +
                        "E as alterações feitas no banco são refletidas aqui!!!");

                // Verificando se além de navegar unidirecional podemos alterar os registros
                // enquanto percorremos os registros de cima para baixo e vici versa...
                if (databaseMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                  ResultSet.CONCUR_UPDATABLE)) {
                    log.info("& CONCUR_UPDATABLE: O Driver suporta alterações nos registros enquanto percorremos " +
                            "unidirecionalmente os registros, ou seja UPDATABLE");
                }
            }

        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }
    }
    public static void showTypeScrollWorking() {
        log.info("Showing how type scroll working.. ");

        String query = "SELECT * FROM `db_anime_store`.`tbl_producer`;";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(query)) {

            // Movendo o cursor a vontade, pois TYPE_SCROLL_INSENSITIVE.. (porém as transações corrente no DB não é refletido aqui..)
            log.info("Movendo o cursor a vontade, pois TYPE_SCROLL_INSENSITIVE.. (porém as transações corrente no DB não é refletido aqui..)");
            log.info("Last row? '{}'", rs.last());
            log.info("Row number: '{}'", rs.getRow());
            log.info("Producer Java Entity: '{}'", Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("------------------------------------------------------------");

            log.info("Row Absolute? '{}'", rs.absolute(2)); // aponta diretamente para o registro 2
            log.info("Row number: '{}'", rs.getRow());
            log.info("Producer Java Entity: '{}'", Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("------------------------------------------------------------");

            log.info("Row Relative? '{}'", rs.relative(-1)); // aponta o cursor -1 apartir da linha corrente (2)
            log.info("Row number: '{}'", rs.getRow());
            log.info("Producer Java Entity: '{}'", Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("------------------------------------------------------------");

            // Verificando em qual posição estamos, sem alterar o ponteiro do Cursor:
            log.info("Verificando em qual posição estamos, sem alterar o ponteiro do Cursor:");
            log.info("is Last? '{}'", rs.isLast());
            log.info("Row number: '{}'", rs.getRow());
            log.info("------------------------------------------------------------");

            log.info("is First? '{}'", rs.isFirst());
            log.info("Row number: '{}'", rs.getRow());
            log.info("------------------------------------------------------------");

            // Aqui precisamos ultrapassar o ultimo elemento, fazendo o cursor apontar para null -1:
            log.info("Aqui precisamos ultrapassar o ultimo elemento, fazendo o cursor apontar para null -1:");
            rs.last();
            rs.next();
            log.info("is After Last Row? '{}'", rs.isAfterLast());
            log.info("Row number: '{}'", rs.getRow());
            log.info("------------------------------------------------------------");

            // Aqui estamos depois do ultimo elemento (null), então voltamos tudo:
            // Obs: Se estivessemos no ultimo elemento (rs.last()), o previous iria
            // começar pulando esse ultimo elemento, porisso é necessário apontar
            // o cursor para o AfterLast e depois utiliza-lo...
            log.info("Aqui estamos depois do ultimo elemento (null), então voltamos tudo com previous:");
            while(rs.previous()) {
                log.info("Producer Java Entity: '{}'", Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            }

        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }
    }
    public static List<Producer> findByNameAndUpdateToUpperCaseInDatabase(String name) {
        log.info("Finding ALL Producers with name like '{}' and Updated to UPPERCASE in DB...", name);

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`\n" +
                "WHERE `name` LIKE '%%%s%%';".formatted(name);

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                rs.updateString("name", rs.getString("name").toUpperCase());
                // rs.cancelRowUpdates(); -> Caso queira cancelar, tem que ser antes de updateRow();
                // Uma vez executado o trigger updateRow() não podemos retornar atrás!!
                rs.updateRow();

                producersList.add(Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all producers with name like {}", name, e);
        }

        return producersList;
    }
    public static List<Producer> findByNameAndInsertWhenNotFound(String name) {
        log.info("Finding ALL Producers with name like '{}' and Insert when notfound in DB...", name);

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`\n" +
                "WHERE `name` LIKE '%%%s%%';".formatted(name);

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.next()) {
                System.out.println("ENTROOOU NO IF NOT NEXT");
                rs.moveToInsertRow();
                rs.updateString("name", name);
                rs.insertRow();

                // Volta e vai dnv para pegar o id que foi gerado no Insert.
                rs.beforeFirst();
                rs.next();
                return List.of(Producer.builder().id(rs.getInt("id")).name(name).build());
            }

            // Volta pois existem ocorrências, ou seja, não insere nada.
            rs.beforeFirst();
            while(rs.next()){
                System.out.println("ENTROOOU NO WHILE EXISTS");
                producersList.add(Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all producers with name like {}", name, e);
        }

        return producersList;
    }
    public static List<Producer> findByNameAndDeleteWhenFound(String name) {
        log.info("Finding ALL Producers with name like '{}' and DELETE when found in DB...", name);

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`\n" +
                "WHERE `name` LIKE '%%%s%%';".formatted(name);

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.next()) return producersList;

            // Volta pois existem ocorrências, ou seja, DELETA todas ocorrências do DB.
            rs.beforeFirst();
            while(rs.next()){
                System.out.println("ENTROOOU NO WHILE EXISTS");
                producersList.add(Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());

                rs.deleteRow();
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all producers with name like {}", name, e);
        }

        return producersList;
    }
    public static List<Producer> findByNamePreparedStatement(String name) {
        log.info("Finding ALL Producers with name like '{}' using PreparedStatement for SQLInjection Handdler...", name);

        String query = "SELECT `id`, `name` FROM `db_anime_store`.`tbl_producer`\n" +
                "WHERE `name` LIKE ?;";

        List<Producer> producersList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(conn, query, name);
             ResultSet rs = preparedStatement.executeQuery()) {

            if (!rs.next()) return producersList;

            // Volta pois existem ocorrências, ou seja, returns all
            rs.beforeFirst();
            while(rs.next()){
                System.out.println("ENTROOOU NO WHILE EXISTS");
                producersList.add(Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all producers with name like {}", name, e);
        }

        return producersList;
    }
    private static PreparedStatement createPreparedStatement(Connection conn, String query, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }
    public static void updatePreparedStatement(Producer producer) {
        String query = ("UPDATE `db_anime_store`.`tbl_producer` " +
                "SET `name` = ? " +
                "WHERE (`id` = ?);");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementForUpdate(conn, query, producer)) {

            int rowsAffected = ps.executeUpdate();
            log.info("Updated producer '{}' from the database, rows affected '{}'", producer.getName(), rowsAffected);
            // Qualquer alteração nos records/registros que a query for gerar utiliza `executeUpdate()`.
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producer.getName(), e);
        }
    }
    private static PreparedStatement createPreparedStatementForUpdate(Connection conn, String query, Producer producer) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setString(1, producer.getName());
        ps.setInt(2, producer.getId());
        return ps;
    }
}
