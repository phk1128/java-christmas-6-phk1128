package christmas.view;

import christmas.domain.benefit.Benefit;
import christmas.domain.orderMenu.OrderMenu;
import java.util.List;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MONEY_FORMAT = "%,d원";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String BENEFIT_FORMAT = "%s: %,d원";
    private static final String NONE = "없음";

    public void printIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printAskReservation() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printAskOrderMenuGroup() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printPreview(int visitDay) {
        String message = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDay);
        System.out.println(message);
    }

    public void printOrderMenuGroup(List<OrderMenu> orderMenus) {
        StringBuilder sb = new StringBuilder();
        for (OrderMenu orderMenu : orderMenus) {
            String menuName = orderMenu.getMenuName();
            int quantity = orderMenu.getQuantity();
            sb.append(String.format(MENU_FORMAT, menuName, quantity));
            sb.append(LINE_SEPARATOR);
        }
        System.out.println("<주문 메뉴>");
        System.out.print(sb.toString());
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        String message = String.format(MONEY_FORMAT, totalPrice);
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(message);
    }

    public void printGiftMenu(String giftMenuName, int giftMenuQuantity) {
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        if (giftMenuQuantity != 0) {
            sb.setLength(0);
            String message = String.format(MENU_FORMAT, giftMenuName, giftMenuQuantity);
            sb.append(message);
        }
        System.out.println("<증정 메뉴>");
        System.out.println(sb.toString());
    }

    public void printBenefitGroup(List<Benefit> benefits, int totalDiscount) {
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        if (totalDiscount != 0) {
            sb.setLength(0);
            appendBenefit(benefits, sb);
        }
        System.out.println("<혜택 내역>");
        System.out.print(sb.toString());
    }

    private void appendBenefit(List<Benefit> benefits, StringBuilder sb) {
        for (Benefit benefit : benefits) {
            int discount = benefit.getDiscount();
            if (discount != 0) {
                String name = benefit.getName();
                sb.append(String.format(BENEFIT_FORMAT, name, discount));
                sb.append(LINE_SEPARATOR);
            }
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        String message = String.format(MONEY_FORMAT, totalDiscount);
        System.out.println("<총혜택 금액>");
        System.out.println(message);
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        String message = String.format(MONEY_FORMAT, totalPriceAfterDiscount);
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(message);
    }

    public void printBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.print(badgeName);
    }

    public void printBreakLine() {
        System.out.println();
    }
}
