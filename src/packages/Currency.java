package assignment3.packages.src.packages;

public enum Currency{

    STERLING("Sterling"),
    USDOLLAR("US Dollar"),
    EURO("Euro"),
    YUAN("Chinese Yuan"),
    YEN("Japanese Yen");

    private final String displayName;

    Currency(String displayName) {
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
