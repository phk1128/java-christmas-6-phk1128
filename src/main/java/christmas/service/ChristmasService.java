package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Event;
import christmas.domain.OrderMenu;

public class ChristmasService {

    public Benefit getBenefit(OrderMenu orderMenu, int day) {
        Benefit benefit = new Benefit();
        addDiscountIntoBenefitDetails(orderMenu, benefit, day);
        return benefit;
    }

    public String getOrderMenuDetail(OrderMenu orderMenu) {
        return orderMenu.detailsToString();
    }

    public String getTotalPriceBeforeDiscount(OrderMenu orderMenu) {
        return String.format("%,d원\n", orderMenu.calculateTotalPrice());
    }

    public String getPresentationMenu(Benefit benefit) {
        return benefit.presentationMenuToString();
    }

    public String getTotalPriceAfterDiscount(OrderMenu orderMenu, Benefit benefit) {
        int totalPrice = orderMenu.calculateTotalPrice();
        int totalDiscount = benefit.calculateTotalDiscount();
        totalPrice += (totalDiscount - benefit.calculatePresentationPrice());
        return String.format("%,d원\n", totalPrice);
    }

    public String getBadge(Benefit benefit) {
        return Badge.findByTotalDiscount(benefit.calculateTotalDiscount());
    }


    private void addDiscountIntoBenefitDetails(OrderMenu orderMenu, Benefit benefit, int day) {
        Event.findAllByDay(day)
                .stream()
                .filter(event -> orderMenu.calculateTotalPrice() > event.getCondition())
                .forEach(event -> benefit.addDiscountIntoDetails(event, calculateDiscount(orderMenu, event, day)));
    }

    private int calculateDiscount(OrderMenu orderMenu, Event event, int day) {
        int result = 0;
        int orderMenuCount = orderMenu.countMenuByEventTarget(event.getTarget());
        int discount = event.getDiscount();
        int discountFromTotal = event.getDiscountFromTotal();
        result -= (discount * orderMenuCount) + discountFromTotal;

        if (event == Event.CHRISTMAS) {
            result -= discount * (day - 1) + 1000;
        }
        if (event == Event.PRESENTATION) {
            result -= discount;
        }
        return result;
    }
}
