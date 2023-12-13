package christmas;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController(
                new InputView(),
                new OutputView(),
                new ChristmasService()
        );
        christmasController.run();
        // TODO: 프로그램 구현
    }
}
