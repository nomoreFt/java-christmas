package christmas.v1.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domain > Money")
class MoneyTest {

    @Test
    @DisplayName("금액을 곱한다.")
    void multiply() {
        Money money = Money.won(2_000);
        Money result = money.multiply(2);

        assertEquals(Money.won(4_000), result);
    }

    @DisplayName("금액을 더한다.")
    @Test
    void add() {
        Money money = Money.won(1_000);
        Money result = money.add(Money.won(1_000));

        assertEquals(Money.won(2_000), result);
    }

    @DisplayName("금액을 뺀다.")
    @Test
    void minus() {
        Money money = Money.won(2_000);
        Money result = money.minus(Money.won(1_000));

        assertEquals(Money.won(1_000), result);
    }

    @DisplayName("크거나 같은지 비교한다.")
    @Test
    void isGreaterThanOrEqual() {
        Money money = Money.won(2_000);
        Money anotherMoney = Money.won(1_000);

        assertEquals(true, money.isGreaterThanOrEqual(anotherMoney));
    }

    @DisplayName("초과하는지 비교한다.")
    @Test
    void isGreaterThan() {
        Money money = Money.won(2_000);
        Money anotherMoney = Money.won(1_000);

        assertEquals(true, money.isGreaterThan(anotherMoney));
    }
}