package com.gildedrose.Items;

public class ItemFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJIURED = "Conjured Mana Cake";

    public static GildedRoseItem mapToGildedRoseItem(Item item) {
        if (isLegendaryItem(item)) {
            return LegendaryItem.of(item);
        } else if (isAged_brie(item)) {
            return AgedBrieItem.of(item);
        }  else if (isConcertItem(item)) {
            return ConcertItem.of(item);
        } else if (isConjuredItem(item)){
            return ConjuredItem.of(item);
        } else {
            return NormalItem.of(item);
        }
    }

    private static boolean isConjuredItem(Item item) {
        return item.name.equals(CONJIURED);
    }

    private static boolean isConcertItem(Item item) {
        return item.name.equals(CONCERT);
    }

    private static boolean isLegendaryItem(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private static boolean isAged_brie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}
