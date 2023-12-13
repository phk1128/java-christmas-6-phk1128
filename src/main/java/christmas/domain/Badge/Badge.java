package christmas.domain.Badge;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    NONE("없음", 0),
    STAR("별", -5000),
    TREE("트리", -10000),
    SANTA("산타", -20000);

    private final String name;
    private final int totalDiscountCondition;

    Badge(String name, int totalDiscountCondition) {
        this.name = name;
        this.totalDiscountCondition = totalDiscountCondition;
    }

    public String getName() {
        return name;
    }

    public static Badge findByTotalDiscount(int totalDiscount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> totalDiscount < badge.totalDiscountCondition)
                .min(Comparator.comparing(badge -> badge.totalDiscountCondition - totalDiscount))
                .orElse(NONE);
    }
}
