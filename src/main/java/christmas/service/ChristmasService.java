package christmas.service;


import christmas.domain.badge.Badge;
import christmas.domain.benefit.Benefits;
import christmas.domain.benefit.ChristmasBenefit;
import christmas.domain.benefit.GiftBenefit;
import christmas.domain.benefit.SpecialDayBenefit;
import christmas.domain.benefit.WeekdayBenefit;
import christmas.domain.benefit.WeekendBenefit;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.domain.visit.Visit;
import java.util.ArrayList;
import java.util.List;

public class ChristmasService {

    public Visit createVisit(String inputVisitDay) {
        return Visit.create(inputVisitDay);
    }

    public OrderMenus createOrderMenus(String inputOrderMenus) {
        inputOrderMenus = removeSpace(inputOrderMenus);
        List<OrderMenu> orderMenus = new ArrayList<>();
        for (String inputOrderMenu : inputOrderMenus.split(",")) {
            orderMenus.add(OrderMenu.create(inputOrderMenu));
        }
        return OrderMenus.create(orderMenus);
    }

    public Benefits createBenefits(Visit visit, OrderMenus orderMenus) {
        return Benefits.create(
                WeekdayBenefit.create(visit, orderMenus),
                WeekendBenefit.create(visit, orderMenus),
                ChristmasBenefit.create(visit, orderMenus),
                SpecialDayBenefit.create(visit, orderMenus),
                GiftBenefit.create(orderMenus)
        );
    }

    public int calculateTotalPriceAfterDiscount(OrderMenus orderMenus, Benefits benefits) {
        return orderMenus.calculateTotalPrice() + benefits.calculateTotalDiscountExcludeGift();

    }

    public String findBadgeByTotalDiscount(Benefits benefits) {
        return Badge.findByTotalDiscount(benefits.calculateTotalDiscount()).getName();
    }


    private String removeSpace(String inputOrderMenus) {
        return inputOrderMenus.replace(" ", "");
    }
}
