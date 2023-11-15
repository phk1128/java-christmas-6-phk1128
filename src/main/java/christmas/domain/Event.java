package christmas.domain;

import christmas.constant.EventConstant.Condition;
import christmas.constant.EventConstant.Days;
import christmas.constant.EventConstant.Discount;
import christmas.constant.EventConstant.Message;
import christmas.constant.EventConstant.Target;
import java.util.Arrays;
import java.util.List;

public enum Event {

    /*
    index0(Condition): 총 주문 금액 조건
    index1(Days): 적용 날짜
    index2(Target): 할인 메뉴 또는 증정품
    index3(Discount): 할인 금액
    index4(DiscountFromTotal): 총금액에서 할인 금액
    index5(Message): 할인 메시지 (e.g. WEEKDAY -> "평일 할인:")
    */
    SPECIAL(Condition.CASE_A, Days.SPECIAL, Target.SPECIAL, Discount.SPECIAL, 1000, Message.SPECIAL),
    WEEKDAY(Condition.CASE_A, Days.WEEKDAY, Target.WEEKDAY, Discount.WEEKDAY, 0, Message.WEEKDAY),
    WEEKEND(Condition.CASE_A, Days.WEEKEND, Target.WEEKEND, Discount.WEEKEND, 0, Message.WEEKEND),
    CHRISTMAS(Condition.CASE_A, Days.EVERY, Target.CHRISTMAS, Discount.CHRISTMAS, 0, Message.CHRISTMAS),
    PRESENTATION(Condition.CASE_B, Days.EVERY, Target.PRESENTATION, Discount.PRESENTATION, 0, Message.PRESENTATION);


    private final int condition;
    private final List<Integer> days;
    private final String target;
    private final int discount;
    private final int discountFromTotal;
    private final String message;

    Event(int condition, List<Integer> days, String target, int discountMenu, int discountTotal, String message) {
        this.condition = condition;
        this.days = days;
        this.target = target;
        this.discount = discountMenu;
        this.discountFromTotal = discountTotal;
        this.message = message;
    }

    public static List<Event> findAllByDay(int day) {

        return Arrays.stream(Event.values())
                .filter(event -> event.days.contains(day) || event.days.contains(day % 7))
                .toList();
    }

    public static List<Event> getAllEvent() {

        return Arrays.stream(Event.values()).toList();
    }

    public int getDiscount() {

        return discount;
    }

    public int getDiscountFromTotal() {

        return discountFromTotal;
    }

    public String getTarget() {

        return target;
    }

    public int getCondition() {

        return condition;
    }

    public String getMessage() {

        return message;
    }
}
