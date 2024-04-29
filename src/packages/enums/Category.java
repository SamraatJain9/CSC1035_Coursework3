package assignment3.packages.src.packages.enums;

// Enum representing different expense categories
public enum Category {
    GROCERIES("Groceries"),
    UTILITIES("Utilities"),
    TRANSPORTATION("Transportation"),
    ENTERTAINMENT("Entertainment"),
    HEALTHCARE("Healthcare"),
    DINING_OUT("Dining Out"),
    EDUCATION("Education"),
    OTHER("Other");

    private final String displayName; // Display name of the category

    // Constructor
    Category(String displayName) {
        this.displayName = displayName;
    }

    // Method to get the display name of the category
    public String getDisplayName() {
        return displayName;
    }

    // Override toString method to return the display name
    @Override
    public String toString() {
        return displayName;
    }
}
