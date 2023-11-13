package christmas.view.input.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("입력 메뉴 예외처리 테스트")
    @Nested
    class InputMenuExceptionTest {
        @DisplayName("'메뉴이름-메뉴갯수' 형식이 아니므로 예외가 발생한다.")
        @ValueSource(strings = {"3-양송이수프", "-3-아이스크림", "레드와인--3", "샴페인"})
        @ParameterizedTest
        void validateFormat(String input) throws Exception {
            //given
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Menus.validateFormat(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("각 메뉴의 갯수가 0이므로 예외가 발생한다.")
        @ValueSource(strings = {"아이스크림-0", "샴페인-0", "바비큐립-0", "레드와인-0"})
        @ParameterizedTest
        void validateQuantity(String input) throws Exception {
            //given
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Menus.validateQuantity(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("'메뉴이름-메뉴갯수' 형식이 아닌 것이 포함되어 있으므로 예외가 발생한다.")
        @Test
        void atWrongFormat_validateMenus() throws Exception {
            //given
            List<String> menus = List.of("양송이수프-1", "제로콜라-1", "샴페인-1", "1-레드와인", "바비큐립-2");
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Menus.validateMenus(menus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("메뉴의 갯수가 0인것이 포함되어 있으므로 예외가 발생한다.")
        @Test
        void atWrongQuantity_validateMenus() throws Exception {
            //given
            List<String> menus = List.of("양송이수프-1", "제로콜라-1", "샴페인-0", "1-레드와인", "바비큐립-2");
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Menus.validateMenus(menus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @DisplayName("입력 날짜 예외처리 테스트")
    @Nested
    class InputDayException {

        @DisplayName("숫자가 아니므로 예외가 발생한다.")
        @ValueSource(strings = {"a", "-", "*", "ㄱ", "날짜"})
        @ParameterizedTest
        void validateFormat(String day) throws Exception {
            //given
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Day.validateFormat(day))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("1과31 사이가 아니므로 예외가 발생한다.")
        @ValueSource(strings = {"0", "100", "32", "-1"})
        @ParameterizedTest
        void validateRange(String day) throws Exception {
            //given
            //when
            //then
            assertThatThrownBy(() -> InputValidator.Day.validateRange(day))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

}

