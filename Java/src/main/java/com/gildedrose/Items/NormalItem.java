package com.gildedrose.Items;

public class NormalItem implements GildedRoseItem {
    Item item;

    private NormalItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        decreaseSellIn();

        decreaseQuality();
        if (isExpired()) {
            decreaseQuality();
        }
    }

    private boolean isExpired() {
        return item.sellIn < 0;
    }

    private void decreaseQuality() {
        item.quality = Math.max(item.quality - 1, 0);
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new NormalItem(item);
    }
}
