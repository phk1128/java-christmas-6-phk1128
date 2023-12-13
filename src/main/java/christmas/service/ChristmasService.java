package christmas.service;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.domain.Badge.Badge;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitGroup;
import christmas.domain.benefit.ChristmasBenefit;
import christmas.domain.benefit.GiftBenefit;
import christmas.domain.benefit.SpecialDayBenefit;
import christmas.domain.benefit.WeekdayBenefit;
import christmas.domain.benefit.WeekendBenefit;
import christmas.domain.orderMenu.OrderMenu;
import christmas.domain.orderMenu.OrderMenuGroup;
import christmas.domain.reservation.Reservation;
import christmas.util.Convertor;
import java.util.ArrayList;
import java.util.List;

public class ChristmasService {

    public Reservation createReservation(int inputVisitDay) {
        return Reservation.create(inputVisitDay);
    }

    public OrderMenuGroup createOrderMenuGroup(List<String> inputOrderMenus) {
        List<OrderMenu> orderMenus = new ArrayList<>();
        for (String inputOrderMenu : inputOrderMenus) {
            String[] menuAndQuantity = inputOrderMenu.split("-");
            Menu menu = Menu.findByName(menuAndQuantity[0]);
            int quantity = Convertor.convertToInt(menuAndQuantity[1], ErrorMessage.INVALID_ORDER.getMessage());
            orderMenus.add(OrderMenu.create(menu, quantity));
        }
        return OrderMenuGroup.create(orderMenus);
    }

    public BenefitGroup createBenefitGroup(Reservation reservation, OrderMenuGroup orderMenuGroup) {

        return BenefitGroup.create(
                WeekdayBenefit.create(reservation, orderMenuGroup),
                WeekendBenefit.create(reservation, orderMenuGroup),
                ChristmasBenefit.create(reservation, orderMenuGroup),
                SpecialDayBenefit.create(reservation, orderMenuGroup),
                GiftBenefit.create(orderMenuGroup)
        );

    }

    public int getTotalPriceBeforeDiscount(OrderMenuGroup orderMenuGroup) {
        return orderMenuGroup.calculateTotalPrice();
    }

    public int getTotalDiscount(BenefitGroup benefitGroup) {
        return benefitGroup.calculateTotalDiscount();
    }

    public int getTotalPriceAfterDiscount(OrderMenuGroup orderMenuGroup, BenefitGroup benefitGroup) {
        return orderMenuGroup.calculateTotalPrice() + benefitGroup.calculateDiscountExcludedGift();
    }

    public String getGiftMenuName(BenefitGroup benefitGroup) {
        return benefitGroup.getGiftMenu().getName();
    }

    public String getBadgeName(BenefitGroup benefitGroup) {
        return Badge.findByTotalDiscount(benefitGroup.calculateTotalDiscount()).getName();
    }

    public int getVisitDay(Reservation reservation) {
        return reservation.getDayOfMonth();
    }

    public List<OrderMenu> getOrderMenus(OrderMenuGroup orderMenuGroup) {
        return orderMenuGroup.getOrderMenus();
    }

    public List<Benefit> getBenefits(BenefitGroup benefitGroup) {
        return benefitGroup.getBenefits();
    }

    public int getGiftQuantity(BenefitGroup benefitGroup) {
        return benefitGroup.getGiftQuantity();
    }
}
