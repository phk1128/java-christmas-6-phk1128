package christmas.constant;

import java.util.regex.Pattern;

public class Constant {

    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_HYPHEN = "-";
    public static final String SPACE = " ";
    public static final String MENU_UNIT = "%d개";
    public static final String PRICE_UNIT = "%,d원";
    public static final String NOTHING = "없음";
    public static final int MENU_MAXIMUM_QUANTITY = 20;
    public static final Pattern MENU_PATTERN = Pattern.compile("^[a-z|A-z|ㄱ-ㅎ|가-힣|0-9|\s]+-[0-9\s]+$");
    public static final Pattern DAY_PATTERN = Pattern.compile("^[0-9\s]+$");
}
