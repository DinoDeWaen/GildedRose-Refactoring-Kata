package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class LegendaryItemTest {

    public static final int TWO_DAYS_PASSED = 2;
    private static final int SIX_DAYS_PASSED = 6;
    private static final int FIFTY_DAYS_PASSED = 50;
    private LegendaryItemFixtures itemFixtures;

    @BeforeEach
    public void setup() {
        itemFixtures = new LegendaryItemFixtures();
    }

    @Test
    void testLegendaryItem2Days() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.LEGENDARY_ITEM_0);

        //When
        updateItemNumberOfDays(vest, TWO_DAYS_PASSED);

        //Then
        assertLegendaryItem(vest);
    }

    @Test
    void testItemNegativeSellIn() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.LEGENDARY_ITEM_NEGATIVE);

        //When
        updateItemNumberOfDays(vest, SIX_DAYS_PASSED);

        //Then
        assertLegendaryItem(vest);
    }

    @Test
    void testLegendaryItemQualityNotNegative() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.LEGENDARY_ITEM_0);

        //When
        updateItemNumberOfDays(vest, FIFTY_DAYS_PASSED);

        //Then
        assertLegendaryItem(vest);
    }

    //Given


    //When
    private void updateItemNumberOfDays(Item item, int days) {
        GildedRose app = new GildedRose(new Item []{item});
        IntStream.range(0, days).forEach(i ->app.updateQuality());
    }

    //Then
    private void assertLegendaryItem(Item testedItem) {
        assertThat(String.format("quality is always 80"),testedItem.quality, equalTo(80));
        assertThat(String.format("sell in never higher than 0"),testedItem.sellIn, lessThanOrEqualTo(0));
    }

    private class LegendaryItemFixtures {
        public static final int LEGENDARY_ITEM_0_SELLIN = 0;
        public static final int LEGENDARY_ITEM_NEGATIVE_SELLIN = -1;

        public final Item LEGENDARY_ITEM_0 = getLegendaryItem(LEGENDARY_ITEM_0_SELLIN);
        public final Item LEGENDARY_ITEM_NEGATIVE = getLegendaryItem(LEGENDARY_ITEM_NEGATIVE_SELLIN);

        public Item getItemForTesting (Item item) {
            return new Item(item.name, item.sellIn, item.quality);
        }

        private Item getLegendaryItem(int sellin) {
            return new Item("Sulfuras, Hand of Ragnaros", sellin, 80);
        }

        private Item getItem(String name, int sellin, int quality) {
            return new Item(name, sellin, quality);
        }
    }
}
