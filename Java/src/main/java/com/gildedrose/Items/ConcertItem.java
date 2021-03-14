package com.gildedrose.Items;

public class ConcertItem implements GildedRoseItem {
    private Item item;

    private ConcertItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        item.sellIn = item.sellIn - 1;

        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 0) {
                item.quality = item.quality - item.quality;
            }
        }
    }

    public static GildedRoseItem of(Item item) {
        return new ConcertItem(item);
    }
}
