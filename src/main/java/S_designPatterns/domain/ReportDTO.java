package S_designPatterns.domain;

public record ReportDTO(String personName,
                        String aircraftName,
                        CountryEnum country,
                        Currency currency) {


    public static final class ReportDTOBuilder {
        private String personName;
        private String aircraftName;
        private CountryEnum country;
        private Currency currency;

        private ReportDTOBuilder() {
        }

        public static ReportDTOBuilder aReportDTO() {
            return new ReportDTOBuilder();
        }

        public ReportDTOBuilder personName(String personName) {
            this.personName = personName;
            return this;
        }

        public ReportDTOBuilder aircraftName(String aircraftName) {
            this.aircraftName = aircraftName;
            return this;
        }

        public ReportDTOBuilder country(CountryEnum country) {
            this.country = country;
            return this;
        }

        public ReportDTOBuilder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public ReportDTO build() {
            return new ReportDTO(personName, aircraftName, country, currency);
        }
    }
}

