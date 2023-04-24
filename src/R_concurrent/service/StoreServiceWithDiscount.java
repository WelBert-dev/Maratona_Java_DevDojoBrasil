package R_concurrent.service;

// Fonte interessante sobre: https://www.devmedia.com.br/trabalhando-com-completablefuture-no-java/32854

import R_concurrent.domain.Discount;
import R_concurrent.domain.Quote;

import java.util.Locale;
import java.util.concurrent.*;

public class StoreServiceWithDiscount {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static double priceGenerator() {
        System.out.printf("%s generating price...%n", Thread.currentThread().getName());
        delay();
        return ThreadLocalRandom.current().nextInt(1, 500) * 10;
    }
    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void executorShutDown() {
        executorService.shutdown();
    }
    public static String getPricesSync(String storeName) {
        System.out.printf("Getting prices sync for store: %s%n", storeName);

        double price = priceGenerator();
        Discount.Code discountCode = Discount.Code.values()[
                ThreadLocalRandom.current().nextInt(0, Discount.Code.values().length)
                ];

        return String.format(Locale.ENGLISH,"%s:%.2f:%s", storeName, price, discountCode); // storeName:price:discountCode
    }

    public static String applyDiscount(Quote quote) {
        delay();
        // Faz esse calculo para tratar caso valor do quote.getDiscountCode() seja == 0
        double discountValue = quote.getPrice() * (100 - quote.getDiscountCode().getPercentage()) / 100;

        return String.format(Locale.ENGLISH,"'%s' original price: '%.2f'. Applying discount code '%s'. Final price: '%.2f'",
                quote.getStoreName(), quote.getPrice(), quote.getDiscountCode(), discountValue);
    }
}
