package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConcertItemTest {

    public static final int TWO_DAYS_PASSED = 2;
    private static final int SIX_DAYS_PASSED = 6;
    private static final int FIFTY_DAYS_PASSED = 50;
    private NormalItemFixtures itemFixtures;

    @BeforeEach
    public void setup() {
        itemFixtures = new NormalItemFixtures();
    }

    @Test
    void test15DayConcertItem() {
        //Given
        Item concert = itemFixtures.getItemForTesting(itemFixtures.CONCERT_ITEM_15);

        //When
        updateItemNumberOfDays(concert, TWO_DAYS_PASSED);

        //Then
        assertNormalItemIncrease(concert, itemFixtures.CONCERT_ITEM_15, TWO_DAYS_PASSED, 1);
    }

    @Test
    void test10DayConcertItem() {
        //Given
        Item concert = itemFixtures.getItemForTesting(itemFixtures.CONCERT_ITEM_10);

        //When
        updateItemNumberOfDays(concert, TWO_DAYS_PASSED);

        //Then
        assertNormalItemIncrease(concert, itemFixtures.CONCERT_ITEM_10, TWO_DAYS_PASSED, 2);
    }

    @Test
    void test5DayConcertItem() {
        //Given
        Item concert = itemFixtures.getItemForTesting(itemFixtures.CONCERT_ITEM_5);

        //When
        updateItemNumberOfDays(concert, TWO_DAYS_PASSED);

        //Then
        assertNormalItemIncrease(concert, itemFixtures.CONCERT_ITEM_5, TWO_DAYS_PASSED, 3);
    }

    @Test
    void testConcertItem1dayAfterConcert() {
        //Given
        Item concert = itemFixtures.getItemForTesting(itemFixtures.CONCERT_ITEM_5);

        //When
        updateItemNumberOfDays(concert, SIX_DAYS_PASSED);

        //Then
        assertDayAfterConcert(concert, itemFixtures.CONCERT_ITEM_5, SIX_DAYS_PASSED);
    }

    //Given


    //When
    private void updateItemNumberOfDays(Item item, int days) {
        GildedRose app = new GildedRose(new Item []{item});
        IntStream.range(0, days).forEach(i ->app.updateQuality());
    }

    //Then
    private void assertNormalItemIncrease(Item testedItem, Item originalItem, int dayspassed, int factor) {
        assertThat(String.format("quality increase with %d days", dayspassed),testedItem.quality, equalTo(originalItem.quality + factor*dayspassed));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn - dayspassed));
    }

    private void assertDayAfterConcert(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("is zero", dayspassed),testedItem.quality, equalTo(0));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn - dayspassed));
    }

    private class NormalItemFixtures {
        public static final int CONCERT_5_SELLIN = 5;
        public static final int CONCERT_10_SELLIN = 10;
        public static final int CONCERT_15_SELLIN = 15;
        public static final int CONCERT_QUALITY_20 = 20;

        public final Item CONCERT_ITEM_5 = getItem("Backstage passes to a TAFKAL80ETC concert", CONCERT_5_SELLIN, CONCERT_QUALITY_20);
        public final Item CONCERT_ITEM_10 = getItem("Backstage passes to a TAFKAL80ETC concert", CONCERT_10_SELLIN, CONCERT_QUALITY_20);
        public final Item CONCERT_ITEM_15 = getItem("Backstage passes to a TAFKAL80ETC concert", CONCERT_15_SELLIN, CONCERT_QUALITY_20);

        public Item getItemForTesting (Item item) {
            return new Item(item.name, item.sellIn, item.quality);
        }

        private Item getItem(String name, int sellin, int quality) {
            return new Item(name, sellin, quality);
        }


    }
}
