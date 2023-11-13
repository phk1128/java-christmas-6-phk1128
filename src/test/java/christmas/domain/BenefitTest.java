package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {

    @DisplayName("할인 받은 총 금액을 계산한다.")
    @Test
    void addDiscount() throws Exception {
        //given
        Event weekday = Event.WEEKDAY;
        Event presentation = Event.PRESENTATION;
        int discountWeekday = -6069;
        int discountPresentation = -25000;
        //when
        Benefit benefit = new Benefit();
        benefit.addDiscountIntoDetails(weekday, discountWeekday);
        benefit.addDiscountIntoDetails(presentation, discountPresentation);
        //then
        assertThat(benefit.calculateTotalDiscount())
                .isEqualTo(discountWeekday + discountPresentation);
    }

    @DisplayName("혜택 내역을 출력 형식에 맞게 변환한다.")
    @Test
    void detailsToString() throws Exception {
        //given
        Event weekend = Event.WEEKEND;
        Event presentation = Event.PRESENTATION;
        Event christmas = Event.CHRISTMAS;
        int discountWeekend = -8116;
        int discountPresentation = -25000;
        int discountChristmas = -3200;
        //when
        Benefit benefit = new Benefit();
        benefit.addDiscountIntoDetails(weekend, discountWeekend);
        benefit.addDiscountIntoDetails(presentation, discountPresentation);
        benefit.addDiscountIntoDetails(christmas, discountChristmas);
        //then
        assertThat(benefit.detailsToString())
                .contains("주말 할인: -8,116", "증정 이벤트: -25,000", "크리스마스 디데이 할인: -3,200");
    }

    @DisplayName("혜택 받은 금액이 0원 이므로 내역은 없음을 반환한다.")
    @Test
    void atDiscountZero_detailsToString() throws Exception {
        //given

        //when
        Benefit benefit = new Benefit();
        //then
        assertThat(benefit.detailsToString()).contains("없음");
    }

    @DisplayName("증정 이벤트 할인 가격을 계산한다.")
    @Test
    void calculatePresentationPrice() throws Exception {
        //given
        Event presentation = Event.PRESENTATION;
        int discountPresentation = -25000;
        //when
        Benefit benefit = new Benefit();
        benefit.addDiscountIntoDetails(presentation, discountPresentation);

        //then
        assertThat(benefit.calculatePresentationPrice()).isEqualTo(discountPresentation);
    }

    @DisplayName("증정 메뉴 내역을 출력 형식에 맞게 변환한다.")
    @Test
    void presentationMenuToString() throws Exception {
        //given
        Event presentation = Event.PRESENTATION;
        int discountPresentation = -25000;
        //when
        Benefit benefit = new Benefit();
        benefit.addDiscountIntoDetails(presentation, discountPresentation);
        //then
        assertThat(benefit.presentationMenuToString()).contains("샴페인 1개");
    }

}