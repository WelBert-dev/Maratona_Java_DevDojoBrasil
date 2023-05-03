package T_jdbc.test;

import T_jdbc.conn.ConnectionFactory;

public class Aula256ConnectionFactory_teste {
    public static void main(String[] args) {
        ConnectionFactory.getConnection();
        // com.mysql.cj.jdbc.ConnectionImpl@a1153bc -> Saída se o servidor Mysql estiver running
    }
}
