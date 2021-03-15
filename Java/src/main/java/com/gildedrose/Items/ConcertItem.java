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
    }

    private void decreaseQuality() {
        int qualityIncrease = getFactor();

        if (item.sellIn < 0) {
            setQualityToZero();
        } else {
            item.quality = Math.min(item.quality + qualityIncrease, 50);
        }
    }

    private void decreaseSellIn() {
        --item.sellIn;
    }

    private int getFactor() {
        if (item.sellIn < 6) {
            return 3;
        } else if (item.sellIn < 11) {
            return 2;
        } else {
            return 1;
        }
    }

    private void setQualityToZero() {
        item.quality = 0;
    }

    public static GildedRoseItem of(Item item) {
        return new ConcertItem(item);
    }
}
