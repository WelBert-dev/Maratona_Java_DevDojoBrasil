package T_jdbc;

import T_jdbc.domain.Producer;
import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aula269PreparedStatement_preCompilandoConsultasSQL_updateMethod_consultasSemRetorno {
    public static void main(String[] args) {
        // Antes, sem o PreparedStatement no Update: SQLInjection (Todas linhas são afetadas pois ele interpreta)
//         ProducerService.update(Producer.builder().id(1).name("HACKED' WHERE `id` > 0; #").build());
//         ProducerService.update(Producer.builder().id(1).name("HACKED02'; #").build());
        // Updated producer 'HACKED02'; #' from the database, rows affected '7'

        // SOLUÇÃO: Utilizar PreparedStatement: Apenas um registro é alterado pois ele não interpreta
         ProducerService.updatePreparedStatement(Producer.builder().id(1).name("HACKED' WHERE `id` > 0; #").build());
        // Updated producer 'HACKED' WHERE `id` > 0; #' from the database, rows affected '1'
    }
}
