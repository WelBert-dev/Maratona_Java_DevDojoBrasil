package ZC_mercadoLivreBootCamp;

import java.util.*;

public class exe02_possiveisnumeros {
    public static void main(String[] args) {
        int maxDigit = 6;

        List<Integer> numbersList = calculation(maxDigit);
        numbersList.forEach(System.out::println);
    }
    static List<Integer> calculation(int maxDigit) {
        List<Integer> resultsList = new ArrayList<>();

        for (int i = 0; i <= maxDigit; i++) {
            for (int j = 0; j <= maxDigit; j++) {
                for (int k = 0; k <= maxDigit; k++) {
                    for (int l = 0; l <= maxDigit; l++) {
                        int numberGenerated = i * 1000 + j * 100 + k * 10 + l;
                        int sumDigits = i + j + k + l;

                        if (sumDigits == 21) {
                            resultsList.add(numberGenerated);
                        }
                    }
                }
            }
        }

        return resultsList;
    }
}
