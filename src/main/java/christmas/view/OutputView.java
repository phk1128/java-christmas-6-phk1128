package christmas.view;

import christmas.dto.BenefitDto;
import christmas.dto.BenefitDtos;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenuDtos;
import christmas.dto.VisitDto;

public class OutputView {


    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENUS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String BENEFIT_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENUS_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_TITLE = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_TITLE = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";
    private static final String ORDER_MENU = "%s %d개";
    private static final String PRICE = "%,d원";
    private static final String BENEFIT = "%s: " + PRICE;
    private static final String NONE = "없음";


    public void printIntro() {
        System.out.println(INTRO);
    }

    public void printAskVisitDay() {
        System.out.println(ASK_VISIT_DAY);
    }

    public void printAskOrderMenus() {
        System.out.println(ASK_ORDER_MENUS);
    }

    public void printBenefitPreview(VisitDto visitDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(BENEFIT_PREVIEW, visitDto.day()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printOrderMenus(OrderMenuDtos orderMenuDtos) {
        System.out.println(makeTitleMessage(ORDER_MENUS_TITLE));
        StringBuilder sb = new StringBuilder();
        for (OrderMenuDto orderMenuDto : orderMenuDtos.orderMenuDtos()) {
            String menuName = orderMenuDto.menuName();
            int quantity = orderMenuDto.quantity();
            sb.append(String.format(ORDER_MENU, menuName, quantity));
            sb.append(LINE_SEPARATOR);
        }
        System.out.println(sb.toString());
    }

    public void printTotalPrice(OrderMenuDtos orderMenuDtos) {
        System.out.println(makeTitleMessage(TOTAL_PRICE_TITLE));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRICE, orderMenuDtos.totalPrice()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printGiftMenu(BenefitDtos benefitDtos) {
        System.out.println(makeTitleMessage(GIFT_MENU_TITLE));
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        sb.append(LINE_SEPARATOR);
        if (benefitDtos.totalDiscount() != 0) {
            sb.setLength(0);
            String giftMenu = benefitDtos.giftMenu();
            int quantity = benefitDtos.giftQuantity();
            sb.append(String.format(ORDER_MENU, giftMenu, quantity));
            sb.append(LINE_SEPARATOR);
        }
        System.out.println(sb.toString());
    }

    public void printBenefits(BenefitDtos benefitDtos) {
        System.out.println(makeTitleMessage(BENEFIT_TITLE));
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        sb.append(LINE_SEPARATOR);
        if (benefitDtos.totalDiscount() != 0) {
            sb.setLength(0);
            appendBenefits(benefitDtos, sb);
        }
        System.out.println(sb.toString());

    }

    public void printTotalDiscount(BenefitDtos benefitDtos) {
        System.out.println(TOTAL_DISCOUNT_TITLE);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRICE, benefitDtos.totalDiscount()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());

    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRICE, totalPriceAfterDiscount));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printBadgeName(String badgeName) {
        System.out.println(makeTitleMessage(BADGE_TITLE));
        System.out.println(badgeName);
    }

    private void appendBenefits(BenefitDtos benefitDtos, StringBuilder sb) {
        for (BenefitDto benefitDto : benefitDtos.benefitDtos()) {
            if (benefitDto.discount() != 0) {
                String benefitName = benefitDto.name();
                int discount = benefitDto.discount();
                sb.append(String.format(BENEFIT, benefitName, discount));
                sb.append(LINE_SEPARATOR);
            }
        }
    }


    private String makeTitleMessage(String title) {
        return title;
    }
}
