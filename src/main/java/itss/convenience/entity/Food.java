// Food.java
package itss.convenience.entity;

import itss.convenience.UnitType;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Food {
    private final String name;
    private final UnitType unitType;
    private final Map<LocalDate, Double> expirationMap = new TreeMap<>();

    public Food(String name, UnitType unitType, double quantity, LocalDate expirationDate) {
        this.name = name.toLowerCase(Locale.forLanguageTag("vi")).trim();
        this.unitType = unitType;
        if (isValidExpiration(expirationDate)) {
            expirationMap.put(expirationDate, adjustQuantity(quantity));
        }
    }
    public Food(String name, UnitType unitType, double quantity) {
        this(name, unitType, quantity, LocalDate.MAX);
    }
    public Food(String name, UnitType unitType) {
        this.name = name.toLowerCase(Locale.forLanguageTag("vi")).trim();
        this.unitType = unitType;
    }



    private boolean isValidExpiration(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    private double adjustQuantity(double quantity) {
        return (unitType == UnitType.KG || unitType == UnitType.L) ? quantity : Math.floor(quantity);
    }

    public String getName() {
        return name;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public double getTotalQuantity() {
        return expirationMap.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public void addQuantity(LocalDate date, double quantity) {
        if (!isValidExpiration(date)) return;
        double adjusted = adjustQuantity(quantity);
        expirationMap.merge(date, adjusted, Double::sum);
    }

    public void subtractQuantity(double quantityToRemove) {
        quantityToRemove = adjustQuantity(quantityToRemove);
        Iterator<Map.Entry<LocalDate, Double>> iterator = expirationMap.entrySet().iterator();
        while (iterator.hasNext() && quantityToRemove > 0) {
            Map.Entry<LocalDate, Double> entry = iterator.next();
            double currentQty = entry.getValue();
            if (quantityToRemove >= currentQty) {
                quantityToRemove -= currentQty;
                iterator.remove();
            } else {
                entry.setValue(currentQty - quantityToRemove);
                quantityToRemove = 0;
            }
        }
    }

    public boolean isExpired() {
        return expirationMap.keySet().stream().allMatch(date -> date.isBefore(LocalDate.now()));
    }

    public LocalDate getClosestExpirationDate() {
        return expirationMap.keySet().stream().filter(d -> !d.isBefore(LocalDate.now())).findFirst().orElse(null);
    }

    public Map<LocalDate, Double> getExpirationMap() {
        return expirationMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Food food = (Food) obj;
        return name.equals(food.name) && unitType == food.unitType;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, unitType);
    }

    @Override
    public String toString() {
        return "Tên: " + name +
                ", Đơn vị: " + unitType.getUnit() +
                ", Số lượng: " + getTotalQuantity() +
                ", Ngày hết hạn: " + expirationMap.keySet().stream()
                .map(LocalDate::toString)
                .collect(Collectors.joining(", "));
    }
}
