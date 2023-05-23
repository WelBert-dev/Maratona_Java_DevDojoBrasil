package T_jdbc;

// Para realizar alterações, devemos criar um Statement com "CONCUR_UPDATABLE";
// Para que as alterações sejam refletidas no banco, devemos desparar o trigger updateRow();

// Para Cancelar mudanças feitas no Statement á serem refletidas no banco,
// devemos utilizar cancelRowUpdate() ANTES do anterior;

import T_jdbc.service.ProducerService;

public class Aula266CONCUR_UPDATABLE_RefletindoAtualizacoesDo_ResultSet_noBancoDeDados_semPrecisarDeQuerysExplicitas {
    public static void main(String[] args) {
        ProducerService.findByNameAndUpdateToUpperCaseInDatabase("Studio");
    }
}
