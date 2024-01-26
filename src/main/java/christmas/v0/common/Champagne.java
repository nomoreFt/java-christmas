package christmas.v0.common;

public class Champagne extends Gift{
    private int cnt;

    public Champagne(int cnt) {
        this.cnt = cnt;
    }

    public static Champagne of(int cnt) {
        return new Champagne(cnt);
    }
}
