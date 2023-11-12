package christmas.domain;

import christmas.constant.MenuConstant.MenuType;
import java.util.Arrays;
import java.util.List;

public enum Menu {

    //애피타이저
    양송이수프(MenuType.APPETIZER, 6_000),
    타파스(MenuType.APPETIZER, 5_500),
    시저샐러드(MenuType.APPETIZER, 8_000),

    //메인
    티본스테이크(MenuType.MAIN, 55_000),
    바비큐립(MenuType.MAIN, 54_000),
    해산물파스타(MenuType.MAIN, 35_000),
    크리스마스파스타(MenuType.MAIN, 25_000),

    //디저트
    초코케이크(MenuType.DESSERT, 15_000),
    아이스크림(MenuType.DESSERT, 5_000),

    //음료
    제로콜라(MenuType.BEVERAGE, 3_000),
    레드와인(MenuType.BEVERAGE, 60_000),
    샴페인(MenuType.BEVERAGE, 25_000);


    private final String menuType;
    private final int price;

    Menu(String menuType, int price) {
        this.menuType = menuType;
        this.price = price;
    }

    public String getMenuType() {
        return menuType;
    }

    public int getPrice() {
        return price;
    }

    public static boolean isExistByName(String menuName) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.name().equals(menuName));
    }

}
