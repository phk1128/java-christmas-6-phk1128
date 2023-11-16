package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    NONE("없음", 0),
    STAR("별", -5000),
    TREE("트리", -10000),
    SANTA("산타", -20000);

    private final String name;
    private final int totalDiscount;

    Badge(String name, int totalDiscount) {
        this.name = name;
        this.totalDiscount = totalDiscount;
    }

    public static String findByTotalDiscount(int totalDiscount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.totalDiscount >= totalDiscount)
                .min(Comparator.comparingInt(badge -> badge.totalDiscount - totalDiscount))
                .map(Badge::getName)
                .orElse(NONE.getName());
    }

    public String getName() {
        return name;
    }
}
