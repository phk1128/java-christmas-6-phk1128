package christmas.view;

import christmas.dto.BenefitDto;
import christmas.dto.BenefitsDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import christmas.dto.VisitDto;
import java.util.Arrays;
import java.util.List;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String NONE = "없음";
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENUS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PREVIEW = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENUS_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "<증정 메뉴>";
    private static final String BENEFITS_TITLE = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_TITLE = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_DISCOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<%d월 이벤트 배지>";


    public void printIntroMessage() {
        System.out.println(INTRO);
    }

    public void printAskVisitDayMessage() {
        System.out.println(ASK_VISIT_DAY);
    }

    public void printAskOrderMenusMessage() {
        System.out.println(ASK_ORDER_MENUS);
    }

    public void printPreviewMessage(VisitDto visitDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PREVIEW, visitDto.month(), visitDto.day()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printOrderMenusSheet(OrderMenusDto orderMenusDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(ORDER_MENUS_TITLE);
        sb.append(LINE_SEPARATOR);
        makeOrderMenusSheet(orderMenusDto, sb);
        System.out.println(sb.toString());
    }

    private void makeOrderMenusSheet(OrderMenusDto orderMenusDto, StringBuilder sb) {
        for(OrderMenuDto orderMenuDto : orderMenusDto.orderMenu()) {
            String menuName = orderMenuDto.menuName();
            int quantity = orderMenuDto.quantity();
            sb.append(String.format("%s %d개",menuName,quantity));
            sb.append(LINE_SEPARATOR);
        }
    }


    public void printTotalPriceBeforeDiscount(OrderMenusDto orderMenusDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOTAL_PRICE_TITLE);
        sb.append(LINE_SEPARATOR);
        sb.append(String.format("%,d원", orderMenusDto.totalPrice()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }


    public void printGiftMenu(BenefitsDto benefitsDto) {
        System.out.println(GIFT_MENU_TITLE);
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        sb.append(LINE_SEPARATOR);
        if (benefitsDto.giftQuantity() != 0) {
            sb.setLength(0);
            sb.append(String.format("%s %d개",benefitsDto.giftItem(),benefitsDto.giftQuantity()));
            sb.append(LINE_SEPARATOR);
        }
        System.out.println(sb.toString());
    }

    public void printBenefitSheet(BenefitsDto benefitsDto) {
        System.out.println(BENEFITS_TITLE);
        StringBuilder sb = new StringBuilder();
        sb.append(NONE);
        sb.append(LINE_SEPARATOR);
        if (benefitsDto.totalDiscountPrice() != 0) {
            sb.setLength(0);
            makeBenefitSheet(benefitsDto, sb);
        }
        System.out.println(sb.toString());
    }

    private void makeBenefitSheet(BenefitsDto benefitsDto, StringBuilder sb) {
        for (BenefitDto benefitDto : benefitsDto.benefitDtos()) {
            if(benefitDto.discount() != 0) {
                sb.append(String.format("%s: %,d원", benefitDto.benefitName(), benefitDto.discount()));
                sb.append(LINE_SEPARATOR);
            }
        }
    }

    public void printTotalDiscount(BenefitsDto benefitsDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOTAL_DISCOUNT_TITLE);
        sb.append(LINE_SEPARATOR);
        sb.append(String.format("%,d원", benefitsDto.totalDiscountPrice()));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOTAL_PRICE_AFTER_DISCOUNT_TITLE);
        sb.append(LINE_SEPARATOR);
        sb.append(String.format("%,d원", totalPriceAfterDiscount));
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public void printBadge(VisitDto visitDto, String badgeName) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(BADGE_TITLE, visitDto.month()));
        sb.append(LINE_SEPARATOR);
        sb.append(badgeName);
        sb.append(LINE_SEPARATOR);
        System.out.print(sb.toString());
    }


}
