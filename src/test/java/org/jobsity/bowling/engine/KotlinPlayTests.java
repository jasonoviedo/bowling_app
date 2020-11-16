package org.jobsity.bowling.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class KotlinPlayTests {
    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreation() {
        //First, create a simple play, it all should work
        KotlinPlay p1 = new KotlinPlay("John", 10, false);
        assertEquals("John", p1.getName());
        assertEquals(10, p1.getPinCount());
        assertFalse(p1.getFoul());

        //Now, test an invalid pinCount, it should throw
        new KotlinPlay("John", 11, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationNegativePins() {
        new KotlinPlay("John", -1, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationPinsAndFoul() {
        new KotlinPlay("John", 2, true);
    }
}
