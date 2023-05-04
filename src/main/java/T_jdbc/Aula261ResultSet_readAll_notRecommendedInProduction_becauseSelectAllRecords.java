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
