package com.gildedrose.Items;

public class ConcertItem implements GildedRoseItem {
    private Item item;

    private ConcertItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();

        decreaseQuality();

        if (item.sellIn < 11) {
            decreaseQuality();
        }

        if (item.sellIn < 6) {
            decreaseQuality();
        }

        if (item.sellIn < 0) {
            item.quality = item.quality - item.quality;
        }
    }

    private void decreaseQuality() {
        item.quality = Math.min(item.quality + 1, 50);
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new ConcertItem(item);
    }
}
