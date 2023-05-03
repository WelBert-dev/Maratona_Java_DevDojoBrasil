package T_jdbc;

import T_jdbc.domain.Producer;
import T_jdbc.repository.ProducerRepository;

public class Aula257Statement_InsertInto {
    public static void main(String[] args) {
        Producer nhkProducer = Producer.builder()
                .name("Studios Deen 8")
                .build();

        ProducerRepository.save(nhkProducer);
    }
}
