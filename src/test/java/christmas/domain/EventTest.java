package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @DisplayName("날짜에 해당하는 모든 이벤트들을 찾는다.")
    @Test
    void findAllByDay() throws Exception {
        //given
        int day = 3;
        //when
        List<Event> events = Event.findAllByDay(3);
        //then
        assertAll(

                () -> assertThat(events).contains(Event.WEEKDAY),
                () -> assertThat(events).contains(Event.CHRISTMAS),
                () -> assertThat(events).contains(Event.SPECIAL),
                () -> assertThat(events).contains(Event.PRESENTATION)
        );
    }


}