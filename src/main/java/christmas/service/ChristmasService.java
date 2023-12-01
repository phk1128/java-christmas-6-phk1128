package christmas.service;

import christmas.domain.badge.Badge;
import christmas.domain.benefit.Benefits;
import christmas.domain.benefit.ChristmasBenefit;
import christmas.domain.benefit.GiftBenefit;
import christmas.domain.benefit.SpecialBenefit;
import christmas.domain.benefit.WeekdayBenefit;
import christmas.domain.benefit.WeekendBenefit;
import christmas.domain.gift.Gift;
import christmas.domain.menu.OrderMenu;
import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import java.util.ArrayList;
import java.util.List;

public class ChristmasService {

    public OrderMenus createOrderMenus(String inputOrderMenus) {
        List<OrderMenu> orderMenus = new ArrayList<>();

        String[] menusAndQuantities = inputOrderMenus.split(",");

        addOrderMenu(menusAndQuantities, orderMenus);

        return OrderMenus.create(orderMenus);
    }

    private void addOrderMenu(String[] menusAndQuantities, List<OrderMenu> orderMenus) {
        for (String menuAndQuantity : menusAndQuantities) {
            orderMenus.add(OrderMenu.create(menuAndQuantity));
        }
    }

    public Benefits createBenefits(Visit visitDate, OrderMenus orderMenus) {
        return Benefits.create(
                ChristmasBenefit.create(visitDate, orderMenus),
                SpecialBenefit.create(visitDate, orderMenus),
                WeekdayBenefit.create(visitDate, orderMenus),
                WeekendBenefit.create(visitDate, orderMenus),
                GiftBenefit.create(orderMenus)
        );
    }

    public Visit createVisit(String visitDay) {
        return Visit.create(visitDay);
    }


    public int calculateTotalPriceAfterDiscount(OrderMenus orderMenus, Benefits benefits) {
        return orderMenus.calculateTotalPrice() + benefits.calculateTotalDiscountExcludedGift();
    }

    public String getBadgeName(Benefits benefits) {
        return Badge.findByTotalDiscount(benefits.calculateTotalDiscount());
    }



}
