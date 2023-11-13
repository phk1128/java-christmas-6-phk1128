package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.OrderMenu;
import christmas.service.ChristmasService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.christmasService = new ChristmasService();
    }

    public void run() {
        int day = requestDay();
        OrderMenu orderMenu = requestOrderMenu();
        Benefit benefit = christmasService.getBenefit(orderMenu, day);
        responseAll(orderMenu, benefit);

    }

    private int requestDay() {
        outputView.printRequestDayMessage();
        return christmasService.getInputRequestResult(inputView::requestDay);
    }

    private OrderMenu requestOrderMenu() {
        outputView.printRequestOrderMessage();
        return christmasService.getInputRequestResult(inputView::requestOrderMenu);
    }

    private void responseAll(OrderMenu orderMenu, Benefit benefit) {
        outputView.printPreviewMessage();

        responseOrderMenusDetails(orderMenu);
        responseTotalPriceBeforeDiscount(orderMenu);
        responsePresentationMenu(benefit);
        responseBenefitDetails(benefit);
        responseTotalDiscount(benefit);
        responseTotalPriceAfterDiscount(orderMenu, benefit);
        responseBadge(benefit);
    }

    private void responseOrderMenusDetails(OrderMenu orderMenus) {
        String orderMenusDetails = christmasService.getOrderMenuDetails(orderMenus);
        outputView.printOrderMenusDetails(orderMenusDetails);
    }

    private void responseTotalPriceBeforeDiscount(OrderMenu orderMenu) {
        String totalPriceBeforeDiscount = christmasService.getTotalPriceBeforeDiscount(orderMenu);
        outputView.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
    }

    private void responsePresentationMenu(Benefit benefit) {
        String presentationMenu = christmasService.getPresentationMenu(benefit);
        outputView.printPresentationMenu(presentationMenu);
    }

    private void responseBenefitDetails(Benefit benefit) {
        String benefitDetails = christmasService.getBenefitDetails(benefit);
        outputView.printBenefitDetails(benefitDetails);
    }

    private void responseTotalDiscount(Benefit benefit) {
        String totalDiscount = christmasService.getTotalDiscount(benefit);
        outputView.printTotalDiscount(totalDiscount);
    }

    private void responseTotalPriceAfterDiscount(OrderMenu orderMenus, Benefit benefit) {
        String totalPriceAfterDiscount = christmasService.getTotalPriceAfterDiscount(orderMenus, benefit);
        outputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    private void responseBadge(Benefit benefit) {
        String badge = christmasService.getBadge(benefit);
        outputView.printBadge(badge);
    }


}
