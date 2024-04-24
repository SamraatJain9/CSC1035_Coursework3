package assignment3.packages.src.packages.enums;

// Enum representing different currencies
public enum Currency {

    RUPEE("Indian Rupee"),
    DINAR("Libyan Dinar"),
    HRYVNIA("Ukrainian Hryvnia"),
    RUBLE("Russian Ruble"),
    STERLING("Sterling"),
    USDOLLAR("US Dollar"),
    EURO("Euro"),
    YUAN("Chinese Yuan"),
    YEN("Japanese Yen");

    private final String displayName; // Display name of the currency

    // Constructor
    Currency(String displayName) {
        this.displayName = displayName;
    }

    // Method to get the display name of the currency
    public String getDisplayName() {
        return displayName;
    }

    // Override toString method to return the display name
    @Override
    public String toString() {
        return displayName;
    }
}
