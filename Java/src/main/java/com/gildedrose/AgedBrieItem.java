package com.gildedrose;

public class AgedBrieItem implements GildedRoseItem {
    private final Item item;

    public AgedBrieItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
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
