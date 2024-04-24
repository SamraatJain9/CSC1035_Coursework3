package assignment3.packages;

public enum Category {
    GROCERIES("Groceries"),
    UTILITIES("Utilities"),
    TRANSPORTATION("Transportation"),
    ENTERTAINMENT("Entertainment"),
    HEALTHCARE("Healthcare"),
    DINING_OUT("Eating Out"),
    EDUCATION("Education"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
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
