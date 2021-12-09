package com.gildedrose.Items;

class NormalItem implements GildedRoseItem {
    Item item;

    private NormalItem(Item item) {
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
        int qualityDecrease = isExpired()?2:1;

        item.quality = Math.max(item.quality - qualityDecrease, 0);
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    public static GildedRoseItem of(Item item) {
        return new NormalItem(item);
    }
}
