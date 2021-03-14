package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isAged_brie(item)
                    && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!isLegendaryItem(item)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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
                }
            }

            if (!isLegendaryItem(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAged_brie(item)) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!isLegendaryItem(item)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private boolean isLegendaryItem(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private boolean isAged_brie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}