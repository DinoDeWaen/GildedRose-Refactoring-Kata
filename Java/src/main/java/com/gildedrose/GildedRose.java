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
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                    if (item.sellIn < 0 && item.quality > 0) {
                        item.quality = item.quality - 1;
                    }
                }
            } else if (isAged_brie(item)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }

                if (item.sellIn < 0 && item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }  else if (isConcertItem(item)){
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (isConcertItem(item)) {
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
                    }

                    if (item.sellIn < 0) {
                        item.quality = item.quality - item.quality;
                    }
                }
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