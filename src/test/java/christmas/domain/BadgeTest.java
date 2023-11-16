package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총 할인 금액으로 해당하는 배지를 찾는다.")
    @Test
    void findByTotalDiscount() throws Exception {
        //given
        int totalDiscount1 = -4000;
        int totalDiscount2 = -7000;
        int totalDiscount3 = -11000;
        int totalDiscount4 = -21000;
        //when
        //then
        assertAll(
                () -> assertEquals("없음", Badge.findByTotalDiscount(totalDiscount1)),
                () -> assertEquals("별", Badge.findByTotalDiscount(totalDiscount2)),
                () -> assertEquals("트리", Badge.findByTotalDiscount(totalDiscount3)),
                () -> assertEquals("산타", Badge.findByTotalDiscount(totalDiscount4))
        );
    }

}