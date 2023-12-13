package christmas.constant;

public enum ErrorMessage {

    INVALID_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    REJECT_ONLY_DRINK_ORDER("음료만 주문 시, 주문할 수 없습니다."),
    EXCEEDS_MAXIMUM_ORDER_QUANTITY("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
