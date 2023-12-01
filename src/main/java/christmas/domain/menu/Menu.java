package christmas.domain.menu;

import static christmas.util.ErrorMessage.INVALID_ORDER_MENU;

import christmas.domain.menu.MenuConstant.MenuType;
import java.util.Arrays;

public enum Menu {

    NONE("없음", 0, MenuType.NONE),
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    T_BORN_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BARBEQUE_RIB("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),
    ZERO_COKE("제로콜라", 3000, MenuType.DRINK),
    RED_WINE("레드와인", 60000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25000, MenuType.DRINK),
    ;

    private final String name;
    private final int price;
    private final String type;

    Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    // findFirst를 이용한 Lazy Evaluation
    public static Menu findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_MENU.getMessage()));
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

}
