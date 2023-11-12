package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    @DisplayName("주문내역을 요구사항 형식에 맞게 변환한다.")
    @Test
    void detailsToString() throws Exception {
        //given
        List<String> menus = List.of("양송이수프-2", "제로콜라-1");
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        //then
        assertThat(orderMenu.detailsToString()).contains("양송이수프 2개", "제로콜라 1개");
    }

    @DisplayName("주문 총 주문 금액을 계산한다.")
    @Test
    void calculateTotalPrice() throws Exception {
        //given
        List<String> menus = List.of("티본스테이크-1", "바비큐립-1", "시저샐러드-1");
        //when
        int totalPrice = Menu.티본스테이크.getPrice() + Menu.바비큐립.getPrice() + Menu.시저샐러드.getPrice();
        OrderMenu orderMenu = new OrderMenu(menus);
        //then
        assertThat(orderMenu.calculateTotalPrice()).isEqualTo(totalPrice);

    }

    @DisplayName("이벤트 대상에 해당하는 메뉴를 카운팅한다.")
    @Test
    void countMenuByEventTarget() throws Exception {
        //given
        List<String> menus = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        //when
        OrderMenu orderMenu = new OrderMenu(menus);
        //then
        assertAll(
                () -> assertEquals(2, orderMenu.countMenuByEventTarget("dessert")),
                () -> assertEquals(2, orderMenu.countMenuByEventTarget("main"))
        );

    }

    @DisplayName("주문에 중복된 메뉴가 존재하므로 예외가 발생한다.")
    @Test
    void validateDuplicateMenu() throws Exception {
        //given
        List<String> menus = List.of("양송이수프-1", "양송이수프-1");
        //when
        //then
        assertThatThrownBy(() -> new OrderMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("주문에 메뉴판에 없는 메뉴가 존재하므로 예외가 발생한다.")
    @Test
    void validateExistMenu() throws Exception {
        //given
        List<String> menus = List.of("불고기-1", "양송이수프-1");
        //when
        //then
        assertThatThrownBy(() -> new OrderMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("주문한 메뉴 각 수량의 합이 20이 초과하여 예외가 발생한다.")
    @Test
    void validateQuantity() throws Exception {
        //given
        List<String> menus = List.of("양송이수프-2", "아이스크림-10", "제로콜라-10");
        //when
        //then
        assertThatThrownBy(() -> new OrderMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @DisplayName("주문한 메뉴가 모두 음료이므로 예외가 발생한다.")
    @Test
    void validateOnlyBeverage() throws Exception {
        //given
        List<String> menus = List.of("제로콜라-1", "레드와인-2", "샴페인-2");
        //when
        //then
        assertThatThrownBy(() -> new OrderMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
    }

}