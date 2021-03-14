package com.gildedrose.Items;

public class LegendaryItem implements GildedRoseItem{

    @Override
    public void updateQuality() {

    }

    public static GildedRoseItem of(Item item) {
        return new LegendaryItem();
    }
}
