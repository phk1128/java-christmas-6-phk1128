package christmas.domain.badge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("배지 테스트")
    @Test
    void badgeTest() throws Exception {
        //given
        int totalDiscount = -8000;
        //when
        //then
        System.out.println(Badge.findByTotalDiscount(totalDiscount).getName());
    }

}