package T_jdbc;

import T_jdbc.domain.Producer;
import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Aula262ResultSet_read_findByName {
    public static void main(String[] args) {
        List<Producer> producersList = ProducerService.findByName("Studio");
        log.info("Producers found: {}", producersList);
    }
}
