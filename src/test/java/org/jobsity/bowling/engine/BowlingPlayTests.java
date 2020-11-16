package org.jobsity.bowling.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BowlingPlayTests {
    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreation() {
        //First, create a simple play, it all should work
        BowlingPlay p1 = new BowlingPlay("John", 10, false);
        assertEquals("John", p1.getName());
        assertEquals(10, p1.getPinCount());
        assertFalse(p1.getFoul());

        //Now, test an invalid pinCount, it should throw
        new BowlingPlay("John", 11, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationNegativePins() {
        new BowlingPlay("John", -1, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationPinsAndFoul() {
        new BowlingPlay("John", 2, true);
    }
}
