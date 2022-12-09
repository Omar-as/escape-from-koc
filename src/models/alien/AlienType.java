package models.alien;

public enum AlienType {
    SHOOTER("shooterA"),
    BLIND("blindA"),
    TIME_WASTING("timeA");

    public final String name;
    private AlienType(String name) {
        this.name = name;
    }
}