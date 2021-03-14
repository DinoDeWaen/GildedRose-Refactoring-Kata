package com.gildedrose;

public class NormalItem implements GildedRoseItem {
    Item item;

    private NormalItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
            if (item.sellIn < 0 && item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    public static GildedRoseItem of(Item item) {
        return new NormalItem(item);
    }
}
