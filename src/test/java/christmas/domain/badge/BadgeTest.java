package christmas.domain.badge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("배지 테스트")
    @Test
    void badgeTest() throws Exception {
        //given
        int totalDiscount1 = -1000;
        int totalDiscount2 = -5000;
        int totalDiscount3 = -6000;
        int totalDiscount4 = -10000;
        //when

        //then

        assertAll(
                () -> assertEquals("없음", Badge.findByTotalDiscount(totalDiscount1)),
                () -> assertEquals("별", Badge.findByTotalDiscount(totalDiscount2)),
                () -> assertEquals("별", Badge.findByTotalDiscount(totalDiscount3)),
                () -> assertEquals("트리", Badge.findByTotalDiscount(totalDiscount4))
        );
    }

}