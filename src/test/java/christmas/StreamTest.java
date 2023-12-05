package christmas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @DisplayName("스트림테스트")
    @Test
    void streamTest() throws Exception {
        //given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        //when
        //then
        Optional<Integer> reduce = integers.stream().reduce(Integer::max);
        Stream<Integer> integerStream = integers.stream().dropWhile(num -> num < 3);

        System.out.println(integerStream.collect(Collectors.toList()));
        System.out.println(reduce.get());
    }
}
