package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    없음(0),
    별(-5000),
    트리(-10000),
    산타(-20000);


    private final int totalDiscount;

    Badge(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public static String findByTotalDiscount(int totalDiscount) {

        return Arrays.stream(Badge.values())
                .filter(badge -> badge.totalDiscount >= totalDiscount)
                .min(Comparator.comparingInt(badge -> badge.totalDiscount - totalDiscount))
                .map(badge -> badge.name())
                .orElse(없음.name());
    }
}
