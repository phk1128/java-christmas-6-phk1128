package christmas.view.input;

import static christmas.view.input.util.InputConvertor.convertStringToInt;
import static christmas.view.input.util.InputConvertor.convertStringToList;
import static christmas.view.input.util.InputValidator.Day.validateDay;
import static christmas.view.input.util.InputValidator.Menus.validateMenus;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderMenu;
import java.util.List;

public class InputView {

    public OrderMenu requestOrderMenu() {
        String input = Console.readLine();
        List<String> menus = convertStringToList(input);
        validateMenus(menus);
        return new OrderMenu(menus);
    }

    public int requestDay() {
        String input = Console.readLine();
        validateDay(input);
        return convertStringToInt(input);
    }
}
