package christmas.domain.event.common;

public enum Badge {
    Empty("없음"),
    Star("별"),
    Tree("트리"),
    Santa("산타");

    private String name;

    Badge(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
