package S_designPatterns.domain;

public class PersonWithBuilderGenerator {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    private PersonWithBuilderGenerator() {
    }

    public static final class PersonWithBuilderGeneratorBuilder {
        private String firstName;
        private String lastName;
        private String userName;
        private String email;

        private PersonWithBuilderGeneratorBuilder() {
        }

        public static PersonWithBuilderGeneratorBuilder aPersonWithBuilderGenerator() {
            return new PersonWithBuilderGeneratorBuilder();
        }

        public PersonWithBuilderGeneratorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonWithBuilderGeneratorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonWithBuilderGeneratorBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public PersonWithBuilderGeneratorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PersonWithBuilderGenerator build() {
            PersonWithBuilderGenerator personWithBuilderGenerator = new PersonWithBuilderGenerator();
            personWithBuilderGenerator.firstName = this.firstName;
            personWithBuilderGenerator.userName = this.userName;
            personWithBuilderGenerator.lastName = this.lastName;
            personWithBuilderGenerator.email = this.email;
            return personWithBuilderGenerator;
        }
    }
}
