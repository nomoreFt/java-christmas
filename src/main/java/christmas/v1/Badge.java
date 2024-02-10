package christmas.v1;


public enum Badge {
    NONE("없음", 0),
    STAR("별", 1),
    TREE("트리", 2),
    SANTA("산타", 3);

    private final String description;
    private final int priority;

    Badge(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }



    //toString
    @Override
    public String toString() {
        return description;
    }

    public boolean isHigherThan(Badge badge) {
        return this.priority > badge.priority;
    }

    public int getPriority() {
        return priority;
    }
}
