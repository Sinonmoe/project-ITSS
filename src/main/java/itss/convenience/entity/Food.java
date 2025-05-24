package itss.convenience.entity;

import itss.convenience.UnitType;

import java.time.LocalDate;
import java.util.Locale;

public class Food {
    private final String name;
    private final UnitType unitType;
    private double quantity;
    private final LocalDate expirationDate;// localdate

    public Food(String name, UnitType unitType, float quantity, LocalDate expirationDate) {
        this.name = name.toLowerCase(Locale.forLanguageTag("vi"));
        this.unitType = unitType;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public double getQuantity() {
        if (unitType == UnitType.KG || unitType == UnitType.L) {
            return quantity;
        } else {
            return Math.floor(quantity);
        }
    }

    public void setQuantity(double quantity) {
        if (unitType == UnitType.KG || unitType == UnitType.L) {
            this.quantity = quantity;
        } else {
            this.quantity = Math.floor(quantity);
        }
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Food food = (Food) obj;
        return name.equals(food.name) &&
                unitType == food.unitType;
    }
}
