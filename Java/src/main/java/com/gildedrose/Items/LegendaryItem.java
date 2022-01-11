package com.gildedrose.Items;

class LegendaryItem implements GildedRoseItem{

    @Override
    public void updateQuality() {

    }

    public static GildedRoseItem of(Item item) {
        return new LegendaryItem();
    }
}
