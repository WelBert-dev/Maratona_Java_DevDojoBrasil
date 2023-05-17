package T_jdbc;

import T_jdbc.service.ProducerService;

public class Aula265ResultSet_TYPE_SCROLL_INSENSITIVE_definindoUmDosTypesNaCriacaoDoResultSet {
    public static void main(String[] args) {
        ProducerService.showTypeScrollWorking();
        // Showing how type scroll working..
        // Movendo o cursor a vontade, pois TYPE_SCROLL_INSENSITIVE.. (porém as transações corrente no DB não é refletido aqui..)
        // Last row? 'true'
        // Row number: '4'
        // Producer Java Entity: 'Producer(id=4, name=Studios Deen UPDATED 2)'
        // ------------------------------------------------------------
        // Vamos para a posição de maneira absoluta, ou seja, considera o row/valor passado 2:
        // Row Absolute? 'true'
        // Row number: '2'
        // Producer Java Entity: 'Producer(id=2, name=Studio Ghibli)'
        // ------------------------------------------------------------
        // Vamos para a posição de maneira relativa, ou seja, considera o row corrente + ou - o valor passado: -1
        // Ou seja, volta -1 da posição atual 2:
        // Row Relative? 'true'
        // Row number: '1'
        // Producer Java Entity: 'Producer(id=1, name=Mad House)'
        // ------------------------------------------------------------
        // Verificando em qual posição estamos, sem alterar o ponteiro do Cursor:
        // is Last? 'false'
        // Row number: '1'
        // ------------------------------------------------------------
        // is First? 'true'
        // Row number: '1'
        // ------------------------------------------------------------
        // Aqui precisamos ultrapassar o último elemento, fazendo o cursor apontar para null -1:
        // is After Last Row? 'true'
        // Row number: '0'
        // ------------------------------------------------------------
        // Aqui estamos DEPOIS do ultimo elemento (null), então voltamos tudo com previous:
        // Obs: Se estivessemos no último elemento (rs.last()), o previous iria
        // começar PULANDO esse último elemento, porisso é necessário apontar
        // o cursor para o AfterLast e depois utiliza-lo...
        //
        // Producer Java Entity: 'Producer(id=4, name=Studios Deen UPDATED 2)'
        // Producer Java Entity: 'Producer(id=3, name=NHK)'
        // Producer Java Entity: 'Producer(id=2, name=Studio Ghibli)'
        // Producer Java Entity: 'Producer(id=1, name=Mad House)'
    }
}
