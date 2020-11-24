package org.jobsity.bowling.engine.java;

import org.jobsity.bowling.engine.impl.JavaPlay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JavaPlayTests {
    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreation() {
        //First, create a simple play, it all should work
        JavaPlay p1 = new JavaPlay("John", 10, false);
        assertEquals("John", p1.getName());
        assertEquals(10, p1.getPinCount());
        assertFalse(p1.getFoul());

        //Now, test an invalid pinCount, it should throw
        new JavaPlay("John", 11, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationNegativePins() {
        new JavaPlay("John", -1, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayCreationPinsAndFoul() {
        new JavaPlay("John", 2, true);
    }
}
