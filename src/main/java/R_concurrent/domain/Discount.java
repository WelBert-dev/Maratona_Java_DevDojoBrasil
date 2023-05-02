package R_concurrent.domain;

// storeName:price:discountCode
public class Discount {
    public enum Code {
        NONE(0), FIVE_PERCENT(5), TEN_PERCENT(10), FIFTEEN_PERCENT(15);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
        public int getPercentage() {
            return percentage;
        }
    }
}
