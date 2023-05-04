package T_jdbc.repository;

import T_jdbc.conn.ConnectionFactory;
import T_jdbc.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        String query = "SELECT * FROM `db_anime_store`.`tbl_producer`;";

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
}
