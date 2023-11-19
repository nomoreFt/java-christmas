package christmas.domain.event.common;

public enum FoodType {
    Appetizer("에피타이저"),
    Main("메인"),
    Dessert("디저트"),
    Beverage("음료");

    private String name;

    FoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
