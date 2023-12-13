package christmas.controller;

import christmas.constant.ErrorMessage;
import christmas.domain.benefit.BenefitGroup;
import christmas.domain.orderMenu.OrderMenuGroup;
import christmas.domain.reservation.Reservation;
import christmas.service.ChristmasService;
import christmas.util.Convertor;
import christmas.util.LoopTemplate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.regex.Pattern;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printIntro();
        Reservation reservation = requestReservation();
        OrderMenuGroup orderMenuGroup = requestOrderMenuGroup();
        BenefitGroup benefitGroup = christmasService.createBenefitGroup(reservation, orderMenuGroup);
        outputView.printPreview(christmasService.getVisitDay(reservation));
        responseOrderMenuGroup(orderMenuGroup);
        responseTotalPrice(orderMenuGroup);
        responseGiftMenu(benefitGroup);
        responseBenefitGroup(benefitGroup);
        responseTotalDiscount(benefitGroup);
        responseTotalPriceAfterDiscount(orderMenuGroup, benefitGroup);
        responseBadge(benefitGroup);
    }

    private void responseBadge(BenefitGroup benefitGroup) {
        outputView.printBreakLine();
        outputView.printBadge(christmasService.getBadgeName(benefitGroup));
    }

    private void responseTotalPriceAfterDiscount(OrderMenuGroup orderMenuGroup, BenefitGroup benefitGroup) {
        outputView.printBreakLine();
        outputView.printTotalPriceAfterDiscount(
                christmasService.getTotalPriceAfterDiscount(orderMenuGroup, benefitGroup));
    }

    private void responseTotalDiscount(BenefitGroup benefitGroup) {
        outputView.printBreakLine();
        outputView.printTotalDiscount(christmasService.getTotalDiscount(benefitGroup));
    }

    private void responseBenefitGroup(BenefitGroup benefitGroup) {
        outputView.printBreakLine();
        outputView.printBenefitGroup(christmasService.getBenefits(benefitGroup),
                christmasService.getTotalDiscount(benefitGroup));
    }

    private void responseGiftMenu(BenefitGroup benefitGroup) {
        outputView.printBreakLine();
        outputView.printGiftMenu(christmasService.getGiftMenuName(benefitGroup),
                christmasService.getGiftQuantity(benefitGroup));
    }

    private void responseTotalPrice(OrderMenuGroup orderMenuGroup) {
        outputView.printBreakLine();
        outputView.printTotalPriceBeforeDiscount(christmasService.getTotalPriceBeforeDiscount(orderMenuGroup));
    }

    private void responseOrderMenuGroup(OrderMenuGroup orderMenuGroup) {
        outputView.printBreakLine();
        outputView.printOrderMenuGroup(christmasService.getOrderMenus(orderMenuGroup));
    }

    private OrderMenuGroup requestOrderMenuGroup() {
        return LoopTemplate.tryCatchTemplate(() -> {
            outputView.printAskOrderMenuGroup();
            String input = inputView.readInput();
            input = input.replace(" ", "");
            List<String> inputOrderMenuGroup = Convertor.convertToStringList(input);
            validateOrderMenuFormat(inputOrderMenuGroup);
            return christmasService.createOrderMenuGroup(inputOrderMenuGroup);
        });
    }

    private Reservation requestReservation() {
        return LoopTemplate.tryCatchTemplate(() -> {
            outputView.printAskReservation();
            String input = inputView.readInput();
            int inputVisitDay = Convertor.convertToInt(input, ErrorMessage.INVALID_DAY.getMessage());
            return christmasService.createReservation(inputVisitDay);
        });
    }

    private void validateOrderMenuFormat(List<String> inputOrderMenuGroup) {
        if (isWrongOrderMenuFormat(inputOrderMenuGroup)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isWrongOrderMenuFormat(List<String> inputOrderMenuGroup) {
        return inputOrderMenuGroup.stream()
                .anyMatch(orderMenu -> !Pattern.matches("^[a-z|A-z|ㄱ-ㅎ|가-힣|0-9]+-[0-9]+$", orderMenu));
    }
}
