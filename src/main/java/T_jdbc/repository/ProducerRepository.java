package T_jdbc.repository;

import T_jdbc.conn.ConnectionFactory;
import T_jdbc.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            e.printStackTrace();
        }
    }
}
