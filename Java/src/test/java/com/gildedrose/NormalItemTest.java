package com.gildedrose;

import com.gildedrose.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NormalItemTest {

    public static final int TWO_DAYS_PASSED = 2;
    private static final int SIX_DAYS_PASSED = 6;
    private static final int FIFTY_DAYS_PASSED = 50;
    private NormalItemFixtures itemFixtures;

    @BeforeEach
    public void setup() {
        itemFixtures = new NormalItemFixtures();
    }

    @Test
    void testNormalItemHappyFlow() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.NORMAL_ITEM_DEXTERYTY_VEST);

        //When
        updateItemNumberOfDays(vest, TWO_DAYS_PASSED);

        //Then
        assertNormalItemDecrease(vest, itemFixtures.NORMAL_ITEM_DEXTERYTY_VEST, TWO_DAYS_PASSED);
    }

    @Test
    void testNormalItemQualityDecreasesDouble() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.NORMAL_ITEM_ELIXIR_OF_THE_MONGOOSE);

        //When
        updateItemNumberOfDays(vest, SIX_DAYS_PASSED);

        //Then
        assertNormalItemDecreaseToZero(vest, itemFixtures.NORMAL_ITEM_ELIXIR_OF_THE_MONGOOSE, SIX_DAYS_PASSED);
    }

    @Test
    void testNormalItemQualityNotNegative() {
        //Given
        Item vest = itemFixtures.getItemForTesting(itemFixtures.NORMAL_ITEM_DEXTERYTY_VEST);

        //When
        updateItemNumberOfDays(vest, FIFTY_DAYS_PASSED);

        //Then
        assertNormalItemDecreaseToZero(vest, itemFixtures.NORMAL_ITEM_DEXTERYTY_VEST, FIFTY_DAYS_PASSED);
    }

    //Given


    //When
    private void updateItemNumberOfDays(Item item, int days) {
        GildedRose app = new GildedRose(new Item []{item});
        IntStream.range(0, days).forEach(i ->app.updateQuality());
    }

    //Then
    private void assertNormalItemDecrease(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("quality decreased with %d days", dayspassed),testedItem.quality, equalTo(originalItem.quality -dayspassed));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn -dayspassed));
    }

    private void assertNormalItemDecreaseToZero(Item testedItem, Item originalItem, int dayspassed) {
        assertThat(String.format("is zero", dayspassed),testedItem.quality, equalTo(0));
        assertThat(String.format("sell in decreased with %d days", dayspassed),testedItem.sellIn, equalTo(originalItem.sellIn -dayspassed));
    }

    private class NormalItemFixtures {
        public static final int VEST_SELLIN = 10;
        public static final int VEST_QUALITY = 20;
        public static final int ELIXIR_SELLIN = 5;
        public static final int ELIXIR_QUALITY = 7;

        public final Item NORMAL_ITEM_DEXTERYTY_VEST = getItem("+5 Dexterity Vest", VEST_SELLIN, VEST_QUALITY);
        public final Item NORMAL_ITEM_ELIXIR_OF_THE_MONGOOSE = getItem("Elixir of the Mongoose", ELIXIR_SELLIN, ELIXIR_QUALITY);

        public Item getItemForTesting (Item item) {
            return new Item(item.name, item.sellIn, item.quality);
        }

        private Item getItem(String name, int sellin, int quality) {
            return new Item(name, sellin, quality);
        }


    }
}
