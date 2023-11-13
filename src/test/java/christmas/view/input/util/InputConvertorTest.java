package christmas.view.input.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputConvertorTest {

    @DisplayName("문자의 공백을 제거하고 ',' 을 기준으로 split한다. ")
    @Test
    void convertStringToList() throws Exception {
        //given
        String input = " 양송이수프 - 1  , 레드와인- 1 ";
        //when
        //then
        assertThat(InputConvertor.convertStringToList(input))
                .contains("양송이수프-1", "레드와인-1");
    }

    @DisplayName("문자의 공백을 제거하고 숫자로 변환한다.")
    @Test
    void convertStringToInt() throws Exception {
        //given
        String input = "  1  ";
        //when
        //then
        assertThat(InputConvertor.convertStringToInt(input)).isEqualTo(1);
    }

}