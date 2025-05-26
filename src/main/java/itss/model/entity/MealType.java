package itss.model.entity;

public enum MealType {
    breakfast("Sáng"),
    lunch("Trưa"),
    dinner("Tối");

    private final String displayName;

    MealType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
