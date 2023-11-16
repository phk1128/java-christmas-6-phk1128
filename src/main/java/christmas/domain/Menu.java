package christmas.domain;

import christmas.constant.MenuConstant.MenuType;
import java.util.Arrays;

public enum Menu {

    //애피타이저
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000),

    //메인
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000),
    BBQ_RIBS(MenuType.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000),

    //디저트
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5_000),

    //음료
    ZERO_COKE(MenuType.DRINK, "제로콜라", 3_000),
    RED_WINE(MenuType.DRINK, "레드와인", 60_000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25_000),

    //NONE
    NONE(MenuType.NONE, "없는메뉴", 0);

    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElse(NONE);
    }

    public static boolean isExistByName(String menuName) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.getName().equals(menuName));
    }

}
