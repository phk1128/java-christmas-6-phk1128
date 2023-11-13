package christmas.view.input.template;

public class InputTemplate {

    public <T> T execute(InputCallback<T> callback) {
        while (true) {
            try {
                return callback.run();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
