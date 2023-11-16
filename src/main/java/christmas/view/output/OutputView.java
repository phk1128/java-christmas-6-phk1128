package christmas.view.output;

public class OutputView {

    private static final String INITIATING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String REQUEST_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENUS_DETAIL_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String PRESENTATION_MENU_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL_TITLE = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_TITLE = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";


    public void printRequestDayMessage() {
        System.out.println(INITIATING + System.lineSeparator() + REQUEST_DAY);
    }

    public void printRequestOrderMessage() {
        System.out.println(REQUEST_ORDER);
    }

    public void printPreviewMessage() {
        System.out.println(PREVIEW);
    }

    public void printOrderMenusDetails(String orderMenuDetails) {
        System.out.println(ORDER_MENUS_DETAIL_TITLE + System.lineSeparator() + orderMenuDetails);
    }

    public void printTotalPriceBeforeDiscount(String totalPriceBeforeDiscount) {
        System.out.println(TOTAL_PRICE_TITLE + System.lineSeparator() + totalPriceBeforeDiscount);
    }

    public void printPresentationMenu(String presentationMenu) {
        System.out.println(PRESENTATION_MENU_TITLE + System.lineSeparator() + presentationMenu);
    }

    public void printBenefitDetails(String benefitDetails) {
        System.out.println(BENEFIT_DETAIL_TITLE + System.lineSeparator() + benefitDetails);
    }

    public void printTotalDiscount(String totalDiscount) {
        System.out.println(TOTAL_DISCOUNT_TITLE + System.lineSeparator() + totalDiscount);
    }

    public void printTotalPriceAfterDiscount(String totalPriceAfterDiscount) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT + System.lineSeparator() + totalPriceAfterDiscount);
    }

    public void printBadge(String badge) {
        System.out.print(BADGE_TITLE + System.lineSeparator() + badge);
    }

}
