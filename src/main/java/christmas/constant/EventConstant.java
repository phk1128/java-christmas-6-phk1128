package christmas.constant;

import christmas.constant.MenuConstant.MenuType;
import christmas.domain.Menu;
import java.util.List;

public class EventConstant {
    public static class Condition {
        public static final int CASE_A = 10000;
        public static final int CASE_B = 120000;

    }

    public static class Days {
        public static final List<Integer> EVERY = List.of(1, 2, 3, 4, 5, 6);
        public static final List<Integer> WEEKDAY = List.of(0, 3, 4, 5, 6);
        public static final List<Integer> WEEKEND = List.of(1, 2);
        public static final List<Integer> SPECIAL = List.of(3, 25);
    }

    public static class Target {
        public static final String WEEKDAY = MenuType.DESSERT;
        public static final String WEEKEND = MenuType.MAIN;
        public static final String CHRISTMAS = MenuType.NONE;
        public static final String SPECIAL = MenuType.NONE;
        public static final String PRESENTATION = Menu.샴페인.name();
    }

    public static class Discount {
        public static final int WEEKDAY = 2023;
        public static final int WEEKEND = 2023;
        public static final int CHRISTMAS = 100;
        public static final int SPECIAL = 0;
        public static final int PRESENTATION = Menu.샴페인.getPrice();
    }

    public static class Message {
        public static final String SPECIAL = "특별 할인:";
        public static final String WEEKDAY = "평일 할인:";
        public static final String WEEKEND = "주말 할인:";
        public static final String CHRISTMAS = "크리스마스 디데이 할인:";
        public static final String PRESENTATION = "증정 이벤트:";

    }

}
