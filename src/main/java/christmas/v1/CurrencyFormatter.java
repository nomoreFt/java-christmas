package christmas.v1;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    public static String format(Money money) {
        String formattedAmount = money.toString();
        return MessageFormat.format(Messages.get("price.format"), formattedAmount);
    }
}
