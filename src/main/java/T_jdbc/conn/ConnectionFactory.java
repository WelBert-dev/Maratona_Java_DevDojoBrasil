package T_jdbc.conn;

// Aula 256 - Adicionando as depêndencias do Mysql Connector no pom, e criando a lógica de conn;

/* API para manipulação de db: java.sql

- Principais classes e interfaces:
   - Connection:
   - Statement:
   - ResultSet:
   - DriverManager:

- Essas interfaces definem os contratos em que os diferentes bancos devem fornecer
as implementações necessárias, assim podemos trabalhar orientado a interface e
desaclopar essas dependencias facilmente, podendo assim realizar trocas de bancos
facilmente.

- Podemos adicionar os drivers manualmente ou utilizando gerenciadores de dependências
como o Maven (para facilitar a nossa vida).
    - mysql connector java maven;

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {
    }
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/db_anime_store";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
