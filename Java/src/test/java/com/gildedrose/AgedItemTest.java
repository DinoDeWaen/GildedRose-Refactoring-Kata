package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AgedItemTest {

    public static final int TWO_DAYS_PASSED = 2;
    private static final int FIFTY_DAYS_PASSED = 50;
    private AgedItemFixtures itemFixtures;

    @BeforeEach
    public void setup() {
        itemFixtures = new AgedItemFixtures();
    }

    @Test
    void testAgedItemHappyFlow() {
        //Given
        Item brie = itemFixtures.getItemForTesting(itemFixtures.AGED_BRIE_ITEM);

        //When
        updateItemNumberOfDays(brie, TWO_DAYS_PASSED);

        //Then
        assertAgedItemDecrease(brie, itemFixtures.AGED_BRIE_ITEM, TWO_DAYS_PASSED);
    }

    @Test
    void testAgedEndValues() {
        //Given
        Item brie = itemFixtures.getItemForTesting(itemFixtures.AGED_BRIE_ITEM);

        //When
        updateItemNumberOfDays(brie, FIFTY_DAYS_PASSED);

        //Then
        assertAgedBrieEndDate(brie, itemFixtures.AGED_BRIE_ITEM, FIFTY_DAYS_PASSED);
    }

    //Given


    //When
    private void updateItemNumberOfDays(Item item, int days) {
        GildedRose app = new GildedRose(new Item []{item});
        IntStream.range(0, days).forEach(i ->app.updateQuality());
    }

    //Then
    private void assertAgedItemDecrease(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("quality increased with %d days", dayspassed),testedItem.quality, equalTo(originalItem.quality + dayspassed));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn -dayspassed));
    }

    private void assertAgedBrieEndDate(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("is fifty", dayspassed),testedItem.quality, equalTo(50));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn -dayspassed));
    }

    private class AgedItemFixtures {
        public static final int BRIE_SELLIN = 2;
        public static final int BRIE_QUALITY = 0;

        public final Item AGED_BRIE_ITEM = getItem("Aged Brie", BRIE_SELLIN, BRIE_QUALITY);

        public Item getItemForTesting (Item item) {
            return new Item(item.name, item.sellIn, item.quality);
        }

        private Item getItem(String name, int sellin, int quality) {
            return new Item(name, sellin, quality);
        }


    }
}
