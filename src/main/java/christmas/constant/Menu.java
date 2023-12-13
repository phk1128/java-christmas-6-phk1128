package christmas.constant;

import java.util.Arrays;
import java.util.Objects;

public enum Menu {

    NONE("없음", MenuType.NONE, 0),

    MUSHROOM_SOUP("양송이수프", MenuType.APPETIZER, 6_000),
    TAPAS("타파스", MenuType.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8_000),

    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55_000),
    BBQ_RIBS("바비큐립", MenuType.MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25_000),

    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15_000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5_000),

    ZERO_COKE("제로콜라", MenuType.DRINK, 3_000),
    RED_WINE("레드와인", MenuType.DRINK, 60_000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25_000);

    private final String name;
    private final MenuType type;
    private final int price;

    Menu(String name, MenuType type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public static Menu findByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Objects.equals(name, menu.name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage()));
    }

    public String getName() {
        return name;
    }

    public MenuType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public enum MenuType {
        APPETIZER, MAIN, DESSERT, DRINK, NONE
    }
}
