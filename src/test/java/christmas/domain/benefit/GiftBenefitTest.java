package christmas.domain.benefit;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import christmas.service.ChristmasService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftBenefitTest {

    @DisplayName("선물 테스트")
    @Test
    void giftTest() throws Exception {
        //given
        ChristmasService christmasService = new ChristmasService();
        String input = "샴페인-1, 티본스테이크-1";
        String day = "3";
        //when
        OrderMenus orderMenus = christmasService.createOrderMenus(input);
        Visit visit = christmasService.createVisit(day);

        Benefits benefits = christmasService.createBenefits(visit, orderMenus);
        //then
        System.out.println(benefits.getGiftItem());
        System.out.println(benefits.getGiftItemQuantity());
    }

}