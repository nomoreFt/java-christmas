package christmas.domain.event.common;

public enum Gift {
    Empty("없음"),
    Champagne("샴페인");

    private String name;

    Gift(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}