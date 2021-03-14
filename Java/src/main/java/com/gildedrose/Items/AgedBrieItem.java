package com.gildedrose.Items;

public class AgedBrieItem implements GildedRoseItem {
    private final Item item;

    private AgedBrieItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        item.sellIn = item.sellIn - 1;

        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 0 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    public static GildedRoseItem of(Item item) {
        return new AgedBrieItem(item);
    }
}
