package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isLegendaryItem(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (isNormalItem(item)) {
                GildedRoseItem normalItem = NormalItem.of(item);
                normalItem.updateQuality();
            } else if (isAged_brie(item)) {
                GildedRoseItem agedBrie = AgedBrieItem.of(item);
                agedBrie.updateQuality();
            }  else if (isConcertItem(item)){
                GildedRoseItem concertItem = ConcertItem.of(item);
                concertItem.updateQuality();
            }
        }
    }

    private boolean isNormalItem(Item item) {
        return !isAged_brie(item) && !isConcertItem(item) && !isLegendaryItem(item);
    }

    private boolean isConcertItem(Item item) {
        return item.name.equals(CONCERT);
    }

    private boolean isLegendaryItem(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private boolean isAged_brie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}