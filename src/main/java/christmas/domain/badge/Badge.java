package christmas.domain.badge;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    NONE(0, "없음"),
    STAR(-5000, "별"),
    TREE(-10000, "트리"),
    SANTA(-20000, "산타");


    private final int totalDiscountCondition;
    private final String name;

    Badge(int totalDiscountCondition, String name) {
        this.totalDiscountCondition = totalDiscountCondition;
        this.name = name;
    }


    public static String findByTotalDiscount(int totalDiscount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.totalDiscountCondition >= totalDiscount)
                .min(Comparator.comparingInt(badge -> badge.totalDiscountCondition - totalDiscount))
                .map(badge -> badge.name)
                .orElse(NONE.name);
    }

}
