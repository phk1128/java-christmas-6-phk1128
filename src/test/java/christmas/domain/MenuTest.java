package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class MenuTest {

    @DisplayName("메뉴판에 없는 메뉴들이므로 true")
    @ValueSource(strings = {"양송이수프", "아이스크림", "티본스테이크"})
    @ParameterizedTest
    void isExistByName(String menuName) throws Exception {
        //given
        //when
        //then
        assertTrue(Menu.isExistByName(menuName));
    }

    @DisplayName("메뉴판에 없는 메뉴들이므로 false")
    @ValueSource(strings = {"떡갈비", "비빔밥", "햄버거"})
    @ParameterizedTest
    void isNotExistByName(String menuName) throws Exception {
        //given
        //when
        //then
        assertFalse(Menu.isExistByName(menuName));
    }

}