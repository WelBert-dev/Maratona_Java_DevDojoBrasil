package S_designPatterns.devDojo;

import S_designPatterns.domain.Person;
import S_designPatterns.domain.PersonWithBuilderGenerator;

public class Aula246Builder {
    public static void main(String[] args) {
        Person buildWellison = new Person.PersonBuilder()
                .firstName("Wellison")
                .lastName("Bertelli")
                .userName("welbert")
                .email("wellison.bertelli@hotmail.com")
                .build();

        System.out.println(buildWellison);

        PersonWithBuilderGenerator buildIrineu = PersonWithBuilderGenerator.PersonWithBuilderGeneratorBuilder.aPersonWithBuilderGenerator()
                .firstName("Irineu")
                .lastName("InemEu")
                .userName("inini")
                .email("irineu@hotmail.com")
                .build();

        System.out.println(buildIrineu);
    }
}
