package com.gildedrose;

import com.gildedrose.Items.*;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).map(ItemFactory::mapToGildedRoseItem).forEach(gildedRoseItem ->gildedRoseItem.updateQuality());
    }
}