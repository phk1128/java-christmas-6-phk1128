package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuConstant.MenuType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

;

class OrderMenusTest {

    @DisplayName("혜택 메뉴를 카운팅 한다.")
    @Test
    void countBenefitMenu() throws Exception {
        //given
        String input1 = Menu.ICE_CREAM.getName() + "-2";
        String input2 = Menu.CHOCOLATE_CAKE.getName() + "-3";
        //when
        List<OrderMenu> orderMenus = List.of(OrderMenu.create(input1), OrderMenu.create(input2));
        //then
        assertThat(OrderMenus.create(orderMenus).countBenefitTargetMenu(MenuType.DESSERT))
                .isEqualTo(5);

    }

    @DisplayName("총 주문 금액을 계산한다.")
    @Test
    void calculateTotalPrice() throws Exception {
        //given
        String input1 = Menu.BARBEQUE_RIB.getName() + "-1";
        String input2 = Menu.CAESAR_SALAD.getName() + "-2";
        //when
        List<OrderMenu> orderMenus = List.of(OrderMenu.create(input1), OrderMenu.create(input2));
        //then
        assertThat(OrderMenus.create(orderMenus).calculateTotalPrice())
                .isEqualTo(Menu.BARBEQUE_RIB.getPrice() + Menu.CAESAR_SALAD.getPrice() * 2);
    }

    @DisplayName("주문 예외처리 테스트")
    @Nested
    class ValidateTest {
        @DisplayName("음료만 주문하여 예외가 발생한다.")
        @Test
        void validateOnlyDrink() throws Exception {
            //given
            String input1 = "레드와인-1";
            String input2 = "샴페인-2";
            //when
            List<OrderMenu> orderMenus = List.of(OrderMenu.create(input1), OrderMenu.create(input2));
            //then
            assertThatThrownBy(() -> OrderMenus.create(orderMenus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("음료만");
        }

        @DisplayName("메뉴 수량이 20개가 초과하여 예외가 발생한다.")
        @Test
        void validateQuantity() throws Exception {
            //given
            String input1 = "티본스테이크-10";
            String input2 = "양송이수프-15";
            //when
            List<OrderMenu> orderMenus = List.of(OrderMenu.create(input1), OrderMenu.create(input2));
            //then
            assertThatThrownBy(() -> OrderMenus.create(orderMenus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("20개");
        }

        @DisplayName("중복된 메뉴가 존재하여 예외가 발생한다.")
        @Test
        void validateDuplicate() throws Exception {
            //given
            String input1 = "티본스테이크-2";
            String input2 = "티본스테이크-1";
            //when
            List<OrderMenu> orderMenus = List.of(OrderMenu.create(input1), OrderMenu.create(input2));
            //then
            assertThatThrownBy(() -> OrderMenus.create(orderMenus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }


}