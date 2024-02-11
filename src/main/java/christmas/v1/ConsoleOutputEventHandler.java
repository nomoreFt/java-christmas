package christmas.v1;

import christmas.v1.order.OrderItem;
import christmas.v1.service.OrderResult;

import java.text.MessageFormat;

public class ConsoleOutputEventHandler implements OutputEventHandelr{
    @Override
    public void displayWelcomeMessage() {
        System.out.println(Messages.get("welcome.message"));
    }

    @Override
    public void displayOrderResult(OrderResult orderResult) {
        printPreview(orderResult);

        printOrderMenu(orderResult);

        printTotalAmount(orderResult);

        printGift(orderResult);

        printBenefit(orderResult);

        printTotalBenefitAmount(orderResult);

        printFinalDiscountedAmount(orderResult);

        printBadge(orderResult);
    }

    private static void printBadge(OrderResult orderResult) {
        System.out.println(Messages.get("order.badge"));
        System.out.println( orderResult.badge());
    }

    private static void printFinalDiscountedAmount(OrderResult orderResult) {
        System.out.println(Messages.get("order.final.amount"));
        System.out.println(orderResult.totalAfterDiscount());

        System.out.println();
    }

    private static void printTotalBenefitAmount(OrderResult orderResult) {
        System.out.println(Messages.get("order.total.benefits"));
        System.out.println(orderResult.totalBenefitAmount());
        System.out.println();
    }

    private static void printBenefit(OrderResult orderResult) {
        System.out.println(Messages.get("order.benefits"));
        orderResult.benefits().forEach(benefit ->
                System.out.println(MessageFormat.format(Messages.get("order.benefit.item"), benefit.getDescription(), benefit.getBenefitAmount()))
        );
        System.out.println();
    }

    private static void printGift(OrderResult orderResult) {
        System.out.println(Messages.get("order.gifts"));
        orderResult.gifts().forEach(gift ->
                System.out.println(gift.toString())
        );
        System.out.println();
    }

    private static void printTotalAmount(OrderResult orderResult) {
        System.out.println(Messages.get("order.total.before"));
        System.out.println(orderResult.totalBeforeDiscount());

        System.out.println();
    }

    private static void printOrderMenu(OrderResult orderResult) {
        System.out.println(Messages.get("order.menu"));
        orderResult.orderList().forEach(item ->
                System.out.println(item.toString())
        );

        System.out.println();
    }

    private static void printPreview(OrderResult orderResult) {
        String orderPreviewMsg = MessageFormat.format(Messages.get("order.preview"),
                orderResult.orderedDate().getMonthValue(),
                orderResult.orderedDate().getDayOfMonth());
        System.out.println(orderPreviewMsg);
        System.out.println();
    }
}
