public class Meal {
    private String description;
    private MealType type; // na przykład: siłowy, cardio, itp.
    private int calories;

    public Meal (MealType type, int calories, String description) {
        this.description = description;
        this.type = type;
        this.calories = calories;

    }

    public MealType getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }
}
