package edu.cec.todo.model;
public enum Priority {
    HIGH("Red", "danger"), MEDIUM("Yellow", "warning"), LOW("Green", "success");
    private final String colorName;
    private final String badgeClass;
    Priority(String colorName, String badgeClass) { this.colorName = colorName; this.badgeClass = badgeClass; }
    public String getColorName() { return colorName; }
    public String getBadgeClass() { return badgeClass; }
}
