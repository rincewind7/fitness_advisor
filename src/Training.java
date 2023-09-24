public class Training {
    private String description;
    private TrainingType type; // na przykład: siłowy, cardio, itp.
    private int duration;

    public Training(TrainingType type, int duration, String description) {
        this.description = description;
        this.type = type;
        this.duration = duration;
    }


    // Getter'y, setter'y
}
