package ParkingLot.model;

import java.util.Map;

public class Floor {
    String name;
    Map<SlotType, Map<String, Slot>> slots; // instead of inner map, can use inner Set<Slot>

    public Floor(String name, Map<SlotType, Map<String, Slot>> slots) {
        this.name = name;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public Map<SlotType, Map<String, Slot>> getSlots() {
        return slots;
    }
}
