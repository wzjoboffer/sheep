package com.awzhan.game.sheep.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EliminateBox {

    private List<Brand> slots;

    public EliminateBox() {
        this.slots = new ArrayList<>(7);
    }

    public void addToSlots(final Brand brand) {
        slots.add(brand);
        slots.sort(Comparator.comparing(Brand::getName));

        paint();
    }

    private void paint() {
        for (int i = 0; i < slots.size(); i++) {
            final Brand brand = slots.get(i);
            int x = i * brand.getWidth() + 10;
            int y = 600;
            brand.setBounds(x, y, 50, 50);
        }
    }
}
