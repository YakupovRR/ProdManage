package ru.rostec.prodmanage.task.model;

public enum TaskStatus {
    IN_PROGRESS("В работе"),
    ON_DESIGN("На проектировании"),
    COMPLETED("Завершено");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
