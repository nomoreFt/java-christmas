package christmas.v1.adapter.io;

import christmas.v1.common.Money;

import java.text.MessageFormat;

public class Formatter {
    public static String formatMoney(Money money) {
        String formattedAmount = money.toString();
        return MessageFormat.format(Messages.get("price.format"), formattedAmount);
    }
    
    public static String formatItem(String name, long count) {
        return MessageFormat.format(Messages.get("item.format"), name,count);
    }
}
