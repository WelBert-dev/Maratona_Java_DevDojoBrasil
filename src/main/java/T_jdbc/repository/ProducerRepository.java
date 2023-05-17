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
}
