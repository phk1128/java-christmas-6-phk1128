package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Benefit;
import christmas.domain.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasServiceTest {

    private ChristmasService christmasService;

    @BeforeEach
    void setUp() throws Exception {

        this.christmasService = new ChristmasService();
    }

    @DisplayName("날짜와 주문한 메뉴에 맞게 이벤트별 할인 금액을 계산된 혜택 도메인을 생성한다.")
    @Test
    void createBenefit() throws Exception {
        //given
        List<String> menus = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        int day = 3;
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        Benefit benefit = christmasService.getBenefit(orderMenu, day);
        //then
        assertThat(benefit.detailsToString())
                .contains("크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원", "특별 할인: -1,000원", "증정 이벤트: -25,000원");
    }

    @DisplayName("증정메뉴와 갯수를 가져온다.")
    @Test
    void getPresentation() throws Exception {
        //given
        List<String> menus = List.of("티본스테이크-1", "바비큐립-1", "시저샐러드-3", "샴페인-3");
        int day = 25;
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        Benefit benefit = christmasService.getBenefit(orderMenu, day);
        //then
        assertThat(christmasService.getPresentationMenu(benefit))
                .contains("샴페인 1개");
    }

    @DisplayName("할인 전 총금액을 가져온다.")
    @Test
    void getTotalPriceBeforeDiscount() throws Exception {
        //given
        List<String> menus = List.of("아이스크림-2", "시저샐러드-2", "바비큐립-1");
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        //then
        assertThat(christmasService.getTotalPriceBeforeDiscount(orderMenu)).isEqualTo("80,000원");

    }

    @DisplayName("할인 후 총금액을 가져온다.")
    @Test
    void getTotalPriceAfterDiscount() throws Exception {
        //given
        List<String> menus = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        int day = 3;
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        Benefit benefit = christmasService.getBenefit(orderMenu, day);
        //then
        assertThat(christmasService.getTotalPriceAfterDiscount(orderMenu, benefit)).contains("135,754원");
    }

}