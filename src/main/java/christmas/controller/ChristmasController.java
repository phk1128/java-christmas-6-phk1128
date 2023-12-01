package christmas.controller;

import christmas.domain.benefit.Benefits;
import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import christmas.dto.BenefitsDto;
import christmas.dto.OrderMenusDto;
import christmas.dto.VisitDto;
import christmas.service.ChristmasService;
import christmas.util.LoopTemplate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(
            InputView inputView,
            OutputView outputView,
            ChristmasService christmasService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printIntroMessage();

        Visit visit = requestVisitDay();
        OrderMenus orderMenus = requestOrderMenus();
        Benefits benefits = christmasService.createBenefits(visit, orderMenus);
        int totalPriceAfterDiscount = christmasService.calculateTotalPriceAfterDiscount(orderMenus, benefits);
        String badgeName = christmasService.getBadgeName(benefits);

        VisitDto visitDto = VisitDto.from(visit);
        OrderMenusDto orderMenusDto = OrderMenusDto.from(orderMenus);
        BenefitsDto benefitsDto = BenefitsDto.from(benefits);

        responseResult(visitDto, orderMenusDto, benefitsDto, totalPriceAfterDiscount, badgeName);


    }


    private Visit requestVisitDay() {
        outputView.printAskVisitDayMessage();
        return LoopTemplate.tryCatchTemplate(() -> {
                    String visitDay = inputView.readInput();
                    return christmasService.createVisit(visitDay);
                });
    }

    private OrderMenus requestOrderMenus() {
        outputView.printAskOrderMenusMessage();
        return LoopTemplate.tryCatchTemplate(() -> {
            String orderMenus = inputView.readInput();
            return christmasService.createOrderMenus(orderMenus);
        });
    }

    private void responseResult(
            VisitDto visitDto,
            OrderMenusDto orderMenusDto,
            BenefitsDto benefitsDto,
            int totalPriceAfterDiscount,
            String badgeName
    ) {
        responseVisitDate(visitDto);
        responseOrderMenus(orderMenusDto);
        responseBenefits(benefitsDto);
        responseTotalPriceAfterDiscount(totalPriceAfterDiscount);
        responseBadge(visitDto,badgeName);
    }

    private void responseVisitDate(VisitDto visitDto) {
        outputView.printPreviewMessage(visitDto);
    }

    private void responseOrderMenus(OrderMenusDto orderMenusDto) {
        outputView.printOrderMenusSheet(orderMenusDto);
        outputView.printTotalPriceBeforeDiscount(orderMenusDto);

    }

    private void responseBenefits(BenefitsDto benefitsDto) {
        outputView.printGiftMenu(benefitsDto);
        outputView.printBenefitSheet(benefitsDto);
        outputView.printTotalDiscount(benefitsDto);
    }

    private void responseTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        outputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    private void responseBadge(VisitDto visitDto, String badgeName) {
        outputView.printBadge(visitDto, badgeName);
    }

}
