package itss.convenience;

public enum UnitType {
    kg("kg"),
    l("l"),
    pcs("pcs");

    private final String unit;

    UnitType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
    @Override
    public String toString() {
        return unit;
    }
}
