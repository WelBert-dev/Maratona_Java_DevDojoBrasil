package T_jdbc;

import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aula267InsertInto_and_DeleteWhere_with_ResultSet_RefletindoAlteracoesDoStatementNoBanco {
    public static void main(String[] args) {
        log.info(ProducerService.findByNameAndInsertWhenNotFound("Irineu"));
    }
}
