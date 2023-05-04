package T_jdbc;

import T_jdbc.domain.Producer;
import T_jdbc.service.ProducerService;

public class Aula260Statement_updateWhere {
    public static void main(String[] args) {
        Producer producer = Producer.builder()
                .id(4) // flag do WHERE
                .name("Studios Deen UPDATED 2")
                .build();

        ProducerService.update(producer);
    }
}
