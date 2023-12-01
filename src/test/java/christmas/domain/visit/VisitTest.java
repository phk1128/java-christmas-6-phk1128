package christmas.domain.visit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitTest {

    @DisplayName("방문날짜 테스트")
    @ValueSource(strings = {"0", "32", "a"})
    @ParameterizedTest
    void visitDateExceptionTest(String input) throws Exception {
        //given
        //when
        //then
        assertThatThrownBy(() -> Visit.create(input)).isInstanceOf(IllegalArgumentException.class);
    }


}