package com.gildedrose.Items;

public class ConjuredItem implements GildedRoseItem {
    private final Item item;

    private ConjuredItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();
        decreaseQuality();
    }

    private boolean isExpired() {
        return item.sellIn < 0;
    }

    private void decreaseQuality() {
        int qualityIncrease = isExpired()?4:2;

        item.quality = Math.max(item.quality - qualityIncrease, 0);
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new ConjuredItem(item);
    }
}
