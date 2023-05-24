package T_jdbc;

// Obs: Primeiro devemos criar a Procedure ou Function no Banco, para ai chamar CALL no Java!

/* Procedure criada e utilizada como exemplo:
    $ docker ps
    $ docker exec -it e7dd05585b51 bash
    $ mysql -uroot -p
    $ USE db_anime_store;

    DELIMITER $$
    CREATE PROCEDURE sp_get_producer_by_name_usingLike(param_name varchar(100))
    BEGIN
    SELECT * FROM `db_anime_store`.`tbl_producer` AS p WHERE p.name LIKE param_name;
    END$$
    DELIMITER ;

    CALL `db_anime_store`.`sp_get_producer_by_name_usingLike`("STUDIO"); // -> query que executamos no JAVA

*/

import T_jdbc.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aula270CallableStatement_utilizadoQuandoQueremosExecutarFunctionsOuStorageProceduresNoBanco {
    public static void main(String[] args) {
        log.info(ProducerService.findByNameCallableStatement("HACK"));
        // [Producer(id=1, name=HACKED' WHERE `id` > 0; #), Producer(id=3, name=HACKED02), Producer(id=21, name=HACKED02), Producer(id=22, name=HACKED02), Producer(id=23, name=HACKED02), Producer(id=27, name=HACKED02), Producer(id=28, name=HACKED02)]
    }
}
