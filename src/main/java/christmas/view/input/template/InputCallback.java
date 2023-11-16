package christmas.view.input.template;

@FunctionalInterface
public interface InputCallback<T> {

    T run() throws IllegalArgumentException;
}
