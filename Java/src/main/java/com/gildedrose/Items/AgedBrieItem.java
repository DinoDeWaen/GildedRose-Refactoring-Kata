package com.gildedrose.Items;

public class AgedBrieItem implements GildedRoseItem {
    private final Item item;

    private AgedBrieItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        reduceSellIn();

        inCreaseQuality();

        if (isExpired()) {
            inCreaseQuality();
        }
    }

    private boolean isExpired() {
        return item.sellIn < 0;
    }

    private void inCreaseQuality() {
        item.quality = Math.min(item.quality + 1, 50);
    }

    private void reduceSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new AgedBrieItem(item);
    }
}
