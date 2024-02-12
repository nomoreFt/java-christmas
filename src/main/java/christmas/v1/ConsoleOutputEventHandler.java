package christmas.v1;

import christmas.v1.order.OrderItem;
import christmas.v1.service.OrderResult;

import java.text.MessageFormat;

public class ConsoleOutputEventHandler implements OutputEventHandelr {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public void displayWelcomeMessage() {
        System.out.println(Messages.get("welcome.message"));
    }

    @Override
    public void displayOrderResult(OrderResult orderResult) {
        StringBuilder output = new StringBuilder();

        output.append(buildPreviewSection(orderResult))
                .append(buildOrderMenuSection(orderResult))
                .append(buildTotalAmountSection(orderResult))
                .append(buildGiftSection(orderResult))
                .append(buildBenefitSection(orderResult))
                .append(buildTotalBenefitAmountSection(orderResult))
                .append(buildFinalDiscountedAmountSection(orderResult))
                .append(buildBadgeSection(orderResult));

        System.out.println(output);
    }

    private String buildPreviewSection(OrderResult orderResult) {
        return MessageFormat.format(Messages.get("order.preview"),
                orderResult.orderedDate().getMonthValue(),
                orderResult.orderedDate().getDayOfMonth()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildOrderMenuSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.menu") + LINE_SEPARATOR);
        orderResult.orderList().forEach(item ->
                section.append(item.toString()).append(LINE_SEPARATOR));
        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildTotalAmountSection(OrderResult orderResult) {
        return Messages.get("order.total.before") + LINE_SEPARATOR +
                CurrencyFormatter.format(orderResult.totalBeforeDiscount()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildGiftSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.gifts") + LINE_SEPARATOR);
        orderResult.gifts().forEach(gift ->
                section.append(gift.toString()).append(LINE_SEPARATOR));
        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildBenefitSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.benefits") + LINE_SEPARATOR);
        orderResult.benefits().forEach(benefit ->
                section.append(MessageFormat.format(Messages.get("order.benefit.item"), benefit.getDescription(), CurrencyFormatter.format(benefit.getBenefitAmount()))).append(LINE_SEPARATOR));
        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildTotalBenefitAmountSection(OrderResult orderResult) {
        return Messages.get("order.total.benefits") + LINE_SEPARATOR +
                CurrencyFormatter.format(orderResult.totalBenefitAmount()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildFinalDiscountedAmountSection(OrderResult orderResult) {
        return Messages.get("order.final.amount") + LINE_SEPARATOR +
                CurrencyFormatter.format(orderResult.totalAfterDiscount()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildBadgeSection(OrderResult orderResult) {
        return Messages.get("order.badge") + LINE_SEPARATOR +
                orderResult.badge() + LINE_SEPARATOR;
    }
}