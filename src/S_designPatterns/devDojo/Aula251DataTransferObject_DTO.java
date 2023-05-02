package S_designPatterns.devDojo;

// Já sei bem o que é.
// Melhor utilizar records para representar!
// Modelo de representação de dados que seram trafegados entre camadas ou aplicações
// Carregando apenas o payload útil, ou seja, apenas carrega atributos que o outro
// lado precisa, ou também transforma esses dados no padrão que o outro lado espera.

import S_designPatterns.domain.*;

public class Aula251DataTransferObject_DTO {
    public static void main(String[] args) {
        // Cria os objetos nos quais iremos agrupar (join) os atributos no DTO:
        Person person = new Person.PersonBuilder()
                .firstName("Wellison")
                .lastName("Bertelli")
                .userName("welbert")
                .email("wellison.bertelli@hotmail.com")
                .build();
        AircraftSingletonEager aircrfat = AircraftSingletonEager.getINSTANCE();
        CountryEnum country = CountryEnum.BR;
        Currency currency = CurrencyFactory.newCurrency(country);

        // Finalmente cria o DTO com o payload útil que desejamos em outras camadas/aplicações
        ReportDTO reportDTO = ReportDTO.ReportDTOBuilder
                .aReportDTO()
                .personName(person.getFirstName() + " " + person.getLastName())
                .aircraftName(aircrfat.getName())
                .country(country)
                .currency(currency)
                .build();

        System.out.println(reportDTO);
    }
}
