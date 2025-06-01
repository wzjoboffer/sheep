package com.awzhan.game.sheep.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

public class EliminateBox {

    private List<Brand> slots;
    private Map<String, Integer> counts;

    public EliminateBox() {
        this.slots = new ArrayList<>(7);
        this.counts = new HashMap<>();
    }

    public void addToSlots(final Brand brand) {
        slots.add(brand);
        slots.sort(Comparator.comparing(Brand::getName));
        paint();

        verify(brand);

        final String name = brand.getName();
        counts.put(name, counts.getOrDefault(name, 0) + 1);
        if (counts.get(name) == 3) {
            eliminate(name);
        }
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

    private void verify(final Brand brand) {
        if (slots.size() > 8) {
            JOptionPane.showMessageDialog(brand, "Eliminate Box is full, Game Failed");
            System.exit(0);
        }
    }

    private void eliminate(final String name) {
        Iterator<Brand> iter = slots.iterator();
        while (iter.hasNext()) {
            final Brand brand = iter.next();
            if (StringUtils.equalsIgnoreCase(name, brand.getName())) {
                brand.getParent().remove(brand);
                iter.remove();
            }
        }
        counts.put(name, 0);
    }
}
