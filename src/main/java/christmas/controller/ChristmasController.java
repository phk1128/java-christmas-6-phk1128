package christmas.controller;

import static christmas.util.LoopTemplate.tryCatchTemplate;

import christmas.domain.benefit.Benefits;
import christmas.domain.order.OrderMenus;
import christmas.domain.visit.Visit;
import christmas.dto.BenefitDtos;
import christmas.dto.OrderMenuDtos;
import christmas.dto.VisitDto;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        Visit visit = requestVisitDay();
        OrderMenus orderMenus = requestOrderMenus();
        Benefits benefits = christmasService.createBenefits(visit, orderMenus);
        int totalPriceAfterDiscount = christmasService.calculateTotalPriceAfterDiscount(orderMenus, benefits);
        String badgeName = christmasService.findBadgeByTotalDiscount(benefits);

        VisitDto visitDto = VisitDto.from(visit);
        OrderMenuDtos orderMenuDtos = OrderMenuDtos.from(orderMenus);
        BenefitDtos benefitDtos = BenefitDtos.from(benefits);

        responseAll(visitDto, orderMenuDtos, benefitDtos, totalPriceAfterDiscount, badgeName);
    }

    private void responseAll(
            VisitDto visitDto,
            OrderMenuDtos orderMenuDtos,
            BenefitDtos benefitDtos,
            int totalPriceAfterDiscount,
            String badgeName
    ) {
        outputView.printBenefitPreview(visitDto);
        responseOrderMenus(orderMenuDtos);
        responseTotalPrice(orderMenuDtos);
        responseGiftMenu(benefitDtos);
        responseBenefits(benefitDtos);
        responseTotalDiscount(benefitDtos);
        responseTotalPriceAfterDiscount(totalPriceAfterDiscount);
        responseBadge(badgeName);
    }

    private void responseBadge(String badgeName) {
        outputView.printBadgeName(badgeName);
    }

    private void responseTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        outputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }


    private void responseTotalDiscount(BenefitDtos benefitDtos) {
        outputView.printTotalDiscount(benefitDtos);
    }

    private void responseBenefits(BenefitDtos benefitDtos) {
        outputView.printBenefits(benefitDtos);
    }

    private void responseGiftMenu(BenefitDtos benefitDtos) {
        outputView.printGiftMenu(benefitDtos);
    }

    private void responseTotalPrice(OrderMenuDtos orderMenuDtos) {
        outputView.printTotalPrice(orderMenuDtos);

    }

    private void responseOrderMenus(OrderMenuDtos orderMenuDtos) {
        outputView.printOrderMenus(orderMenuDtos);
    }


    private OrderMenus requestOrderMenus() {
        return tryCatchTemplate(() -> {
            outputView.printAskOrderMenus();
            String inputOrderMenus = inputView.readInput();
            return christmasService.createOrderMenus(inputOrderMenus);
        });
    }

    private Visit requestVisitDay() {
        return tryCatchTemplate(() -> {
            outputView.printAskVisitDay();
            String inputVisitDay = inputView.readInput();
            return christmasService.createVisit(inputVisitDay);
        });
    }
}
