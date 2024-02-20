package christmas.v1.adapter.io;

import christmas.v1.adapter.OutputEventHandler;
import christmas.v1.service.dto.OrderResult;

import java.text.MessageFormat;

public class ConsoleOutputEventHandler implements OutputEventHandler {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    //없음
    private static final String NONE_MESSAGE = Messages.get("none");

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
                section.append(Formatter.formatItem(item.getName(),item.getCount())).append(LINE_SEPARATOR));
        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildTotalAmountSection(OrderResult orderResult) {
        return Messages.get("order.total.before") + LINE_SEPARATOR +
                Formatter.formatMoney(orderResult.totalBeforeDiscount()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildGiftSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.gifts") + LINE_SEPARATOR);

        //Gift.NONE 아니고 수량이 1 이상인 경우의 개수가 1개 이상인 경우에만 출력
        if(!orderResult.hasGift()){
            return section.append(NONE_MESSAGE + LINE_SEPARATOR + LINE_SEPARATOR).toString();
        }

        orderResult.gifts().forEach(gift ->
                section.append(Formatter.formatItem(gift.getName(),gift.getQuantity())).append(LINE_SEPARATOR));
        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildBenefitSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.benefits") + LINE_SEPARATOR);

        if(!orderResult.hasBenefit()){
            return section.append(NONE_MESSAGE + LINE_SEPARATOR + LINE_SEPARATOR).toString();
        }

        orderResult.benefits().forEach(benefit ->
                section.append(MessageFormat.format(Messages.get("order.benefit.item")
                        , benefit.getDescription()
                        , Formatter.formatMoney(benefit.getBenefitAmount()))
                ).append(LINE_SEPARATOR));

        return section.append(LINE_SEPARATOR).toString();
    }

    private String buildTotalBenefitAmountSection(OrderResult orderResult) {
        return Messages.get("order.total.benefits") + LINE_SEPARATOR +
                MessageFormat.format(Messages.get("order.total.benefits.money.format"),Formatter.formatMoney(orderResult.totalBenefitAmount()))
                + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildFinalDiscountedAmountSection(OrderResult orderResult) {
        return Messages.get("order.final.amount") + LINE_SEPARATOR +
                Formatter.formatMoney(orderResult.totalAfterDiscount()) + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    private String buildBadgeSection(OrderResult orderResult) {
        StringBuilder section = new StringBuilder(Messages.get("order.badge") + LINE_SEPARATOR);
        if(!orderResult.hasBadge()){
            return section.append(NONE_MESSAGE + LINE_SEPARATOR).toString();
        }

        return section.append(orderResult.badge() + LINE_SEPARATOR).toString();
    }
}