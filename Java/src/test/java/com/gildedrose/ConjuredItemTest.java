package com.gildedrose;

import com.gildedrose.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ConjuredItemTest {

    public static final int TWO_DAYS_PASSED = 2;
    private static final int SIX_DAYS_PASSED = 6;
    private NormalItemFixtures itemFixtures;

    @BeforeEach
    public void setup() {
        itemFixtures = new NormalItemFixtures();
    }

    @Test
    void testConjuredItem() {
        //Given
        Item conjured = itemFixtures.getItemForTesting(itemFixtures.CONJURED_ITEM);

        //When
        updateItemNumberOfDays(conjured, TWO_DAYS_PASSED);

        //Then
        assertConjuredIncrease(conjured, itemFixtures.CONJURED_ITEM, TWO_DAYS_PASSED);
    }

    @Test
    void testConjuredItemAfterLongTime() {
        //Given
        Item conjured = itemFixtures.getItemForTesting(itemFixtures.CONJURED_ITEM);

        //When
        updateItemNumberOfDays(conjured, SIX_DAYS_PASSED);

        //Then
        assertSellInPassed(conjured, itemFixtures.CONJURED_ITEM, SIX_DAYS_PASSED);
    }

    //Given


    //When
    private void updateItemNumberOfDays(Item item, int days) {
        GildedRose app = new GildedRose(new Item []{item});
        IntStream.range(0, days).forEach(i ->app.updateQuality());
    }

    //Then
    private void assertConjuredIncrease(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("quality decrease with %d days", dayspassed),testedItem.quality, equalTo(originalItem.quality - 2* dayspassed));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn - dayspassed));
    }

    private void assertSellInPassed(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("is 8", dayspassed),testedItem.quality, equalTo(6));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn - dayspassed));
    }

    private class NormalItemFixtures {
        public static final int SELLIN_5 = 5;
        public static final int QUALITY_20 = 20;

        public final Item CONJURED_ITEM = getItem("Conjured Mana Cake", SELLIN_5, QUALITY_20);

        public Item getItemForTesting (Item item) {
            return new Item(item.name, item.sellIn, item.quality);
        }

        private Item getItem(String name, int sellin, int quality) {
            return new Item(name, sellin, quality);
        }


    }
}
