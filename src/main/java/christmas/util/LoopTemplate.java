package christmas.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LoopTemplate {

    private LoopTemplate() {

    }

    public static <T> T tryCatchTemplate(Supplier<T> callback) {
        while(true) {
            try {
                return callback.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}