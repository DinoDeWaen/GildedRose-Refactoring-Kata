package com.gildedrose.Items;

public class AgedBrieItem implements GildedRoseItem {
    private final Item item;

    private AgedBrieItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();
        inCreaseQuality();
    }

    private boolean isExpired() {
        return item.sellIn < 0;
    }

    private void inCreaseQuality() {
        int qualityIncrease = isExpired()?2:1;

        item.quality = Math.min(item.quality + qualityIncrease, 50);
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new AgedBrieItem(item);
    }
}
