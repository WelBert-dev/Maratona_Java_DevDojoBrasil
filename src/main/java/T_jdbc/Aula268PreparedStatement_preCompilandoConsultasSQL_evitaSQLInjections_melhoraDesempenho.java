package T_jdbc;

// Utilizamos o PreparedStatement para pré compilar a consulta SQL, evitando assim
// SQLInjection e melhorando o Desempenho.

import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aula268PreparedStatement_preCompilandoConsultasSQL_evitaSQLInjections_melhoraDesempenho {
    public static void main(String[] args) {
        // Estudos anteriores sem o PreparedStatement para tratar SQLInjection:
//        log.info(ProducerService.findByName("Herbert or X'='X"));
        // [Producer(id=1, name=Mad House), Producer(id=3, name=NHK), Producer(id=21, name=Herbert Richards), Producer(id=22, name=Dream Works), Producer(id=23, name=Maratona Java), Producer(id=27, name=Studios Deen), Producer(id=28, name=Studios Ghibili)]

        // Tratando SQLInjection com preCompilação do Statement usando PreparedStatement:
//        log.info(ProducerService.findByNamePreparedStatement("Herbert or X'='X"));
        // []

        // Tratando SQLInjection com preCompilação do Statement usando PreparedStatement: com LIKE %%%s%%
        log.info(ProducerService.findByNamePreparedStatement("Studio"));
        // [Producer(id=27, name=Studios Deen), Producer(id=28, name=Studios Ghibili)]
    }
}
