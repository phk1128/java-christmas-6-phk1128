package christmas.domain;


import christmas.constant.ErrorMessage;
import christmas.constant.MenuConstant.MenuType;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderMenu {

    private final Map<Menu, Integer> details;

    public OrderMenu(List<String> menus) {
        validateMenus(menus);
        this.details = createDetail(menus);
    }

    public String detailsToString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<Menu, Integer> entry : details.entrySet()) {
            sb.append(entry.getKey().getName());
            sb.append(" ");
            sb.append(String.format("%d개", entry.getValue()));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int calculateTotalPrice() {
        return details.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int countMenuByEventTarget(String eventTarget) {
        return details.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getType().equals(eventTarget))
                .mapToInt(Entry::getValue)
                .sum();
    }

    private Map<Menu, Integer> createDetail(List<String> menus) {
        Map<Menu, Integer> detail = new EnumMap<>(Menu.class);
        for (String menu : menus) {
            putMenuIntoDetail(menu, detail);
        }
        return detail;
    }

    private void putMenuIntoDetail(String menu, Map<Menu, Integer> detail) {
        String[] menuAndQuantity = menu.split("-");
        detail.put(Menu.findByName(menuAndQuantity[0]), Integer.parseInt(menuAndQuantity[1]));
    }

    private void validateMenus(List<String> menus) {
        if (isDuplicate(menus) || isNothing(menus)) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_MENU);
        }
        validateQuantity(menus);
        validateOnlyBeverage(menus);
    }

    private boolean isNothing(List<String> menus) {
        return menus.stream()
                .map(menu -> menu.substring(0, menu.indexOf("-")))
                .anyMatch(menu -> !Menu.isExistByName(menu));
    }

    private boolean isDuplicate(List<String> menus) {
        return menus.stream()
                .map(menu -> menu.substring(0, menu.indexOf("-")))
                .distinct()
                .count() != menus.size();
    }

    private void validateQuantity(List<String> menus) {
        if (calculateQuantity(menus) > 20) {
            throw new IllegalArgumentException(ErrorMessage.QUANTITY_OVER);
        }
    }

    private int calculateQuantity(List<String> menus) {
        return menus.stream()
                .map(menu -> menu.split("-")[1])
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private void validateOnlyBeverage(List<String> menus) {
        if (isOnlyBeverage(menus)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK);
        }
    }

    private boolean isOnlyBeverage(List<String> menus) {
        return menus.stream()
                .map(menu -> menu.substring(0, menu.indexOf("-")))
                .allMatch(menu -> Menu.findByName(menu).getType().equals(MenuType.DRINK));
    }

}
