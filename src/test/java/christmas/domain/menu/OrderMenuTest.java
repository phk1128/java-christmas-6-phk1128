package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuTest {

    @DisplayName("없는 메뉴,잘못된 주문 형식,0개 주문이므로 예외가 발생한다.")
    @ValueSource(strings = {"바닐라라떼-1",",,,","1-asda","양송이수프- 0"})
    @ParameterizedTest
    void validateOrderMenu(String input) throws Exception {
        //given
        //when
        //then
        assertThatThrownBy(() -> OrderMenu.create(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


}