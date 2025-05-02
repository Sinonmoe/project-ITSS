package itss.convenience;

public enum UnitType {
    KG("kg"),
    L("l"),
    PCS("pcs");

    private final String unit;

    UnitType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
