package net.spizzer.aoc2019.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceCardsTest {

    @Test
    void testDealIntoNewStack() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .dealIntoNewStack();

        assertEquals(SpaceCards.fromArray(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}), spaceCards);
    }

    @Test
    void testCut() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .cut(3);

        assertEquals(SpaceCards.fromArray(new int[]{3, 4, 5, 6, 7, 8, 9, 0, 1, 2}), spaceCards);
    }

    @Test
    void testCutNegative() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .cut(-4);

        assertEquals(SpaceCards.fromArray(new int[]{6, 7, 8, 9, 0, 1, 2, 3, 4, 5}), spaceCards);
    }

    @Test
    void testDealWithIncrement() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .dealWithIncrement(3);

        assertEquals(SpaceCards.fromArray(new int[]{0, 7, 4, 1, 8, 5, 2, 9, 6, 3}), spaceCards);
    }

    @Test
    void test1() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .dealWithIncrement(7)
                .dealIntoNewStack()
                .dealIntoNewStack();

        assertEquals(SpaceCards.fromArray(new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}), spaceCards);
    }

    @Test
    void test2() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .cut(6)
                .dealWithIncrement(7)
                .dealIntoNewStack();

        assertEquals(SpaceCards.fromArray(new int[]{3, 0, 7, 4, 1, 8, 5, 2, 9, 6}), spaceCards);
    }

    @Test
    void test3() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .dealWithIncrement(7)
                .dealWithIncrement(9)
                .cut(-2);

        assertEquals(SpaceCards.fromArray(new int[]{6, 3, 0, 7, 4, 1, 8, 5, 2, 9}), spaceCards);
    }

    @Test
    void test4() {
        SpaceCards spaceCards = SpaceCards.factoryOrder(10)
                .dealIntoNewStack()
                .cut(-2)
                .dealWithIncrement(7)
                .cut(8)
                .cut(-4)
                .dealWithIncrement(7)
                .cut(3)
                .dealWithIncrement(9)
                .dealWithIncrement(3)
                .cut(-1);

        assertEquals(SpaceCards.fromArray(new int[]{9, 2, 5, 8, 1, 4, 7, 0, 3, 6}), spaceCards);
    }
}