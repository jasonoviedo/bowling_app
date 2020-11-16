package org.jobsity.bowling.engine;

import org.jobsity.bowling.engine.frames.Bowling10thFrame;
import org.jobsity.bowling.engine.frames.BowlingFrame;
import org.junit.Test;

import static org.junit.Assert.*;

public class BowlingFrameTests {

    @Test
    public void testRegularFrameStrike() {
        //Create a strike
        BowlingFrame frame1 = new BowlingFrame(1);
        KotlinPlay play1 = new KotlinPlay("John", 10, false);
        //Strike should finish the frame right away
        assertTrue(frame1.addPlay(play1));

        //Score for now should be 10
        frame1.scoreFrame();
        assertEquals(10, frame1.getScore());

        //But being a strike it will count the next two balls
        KotlinPlay play2 = new KotlinPlay("John", 5, false);
        frame1.recordExtraBall(play2);
        frame1.scoreFrame();
        assertEquals(15, frame1.getScore());

        //The second
        KotlinPlay play3 = new KotlinPlay("John", 5, false);
        frame1.recordExtraBall(play3);
        frame1.scoreFrame();
        assertEquals(20, frame1.getScore());

        //But not a third
        KotlinPlay play4 = new KotlinPlay("John", 2, false);
        frame1.recordExtraBall(play4);
        frame1.scoreFrame();
        assertEquals(20, frame1.getScore());
    }

    @Test
    public void testRegularFrameSpare() {
        //Create a spare
        BowlingFrame frame2 = new BowlingFrame(2);
        KotlinPlay play1 = new KotlinPlay("John", 7, false);
        KotlinPlay play2 = new KotlinPlay("John", 3, false);
        //First ball should not complete the frame
        assertFalse(frame2.addPlay(play1));
        //But second should
        assertTrue(frame2.addPlay(play2));

        //Score before adding the next balls
        frame2.scoreFrame();
        assertEquals(10, frame2.getScore());

        //Being a spare it should consider the next ball
        KotlinPlay play3 = new KotlinPlay("John", 2, false);
        frame2.recordExtraBall(play3);
        frame2.scoreFrame();
        assertEquals(12, frame2.getScore());

        //But not the second
        KotlinPlay play4 = new KotlinPlay("John", 2, false);
        frame2.recordExtraBall(play4);
        frame2.scoreFrame();
        assertEquals(12, frame2.getScore());
    }

    @Test
    public void testRegularFrameOpen() {
        //Create an open frame
        BowlingFrame frame3 = new BowlingFrame(3);
        KotlinPlay play1 = new KotlinPlay("John", 7, false);
        KotlinPlay play2 = new KotlinPlay("John", 2, false);
        assertFalse(frame3.addPlay(play1));
        //Frame will be finished by the second ball
        assertTrue(frame3.addPlay(play2));

        //Score the frame
        frame3.scoreFrame();
        assertEquals(9, frame3.getScore());

        //Next balls should not register
        KotlinPlay play3 = new KotlinPlay("John", 2, false);
        frame3.recordExtraBall(play3);
        frame3.scoreFrame();
        assertEquals(9, frame3.getScore());
    }

    @Test
    public void testCreation10thFrameStrike() {
        //Create a strike
        BowlingFrame frame = new Bowling10thFrame(10);
        KotlinPlay play1 = new KotlinPlay("John", 10, false);
        //Strike should not finish the 10th frame right away, contrary to regular ones
        assertFalse(frame.addPlay(play1));

        //Score for now should be 10
        frame.scoreFrame();
        assertEquals(10, frame.getScore());

        //But being a strike it will count the next two balls
        KotlinPlay play2 = new KotlinPlay("John", 5, false);
        //Should not finish yet
        assertFalse(frame.addPlay(play2));
        frame.scoreFrame();
        assertEquals(15, frame.getScore());

        //This play should finish the frame
        KotlinPlay play3 = new KotlinPlay("John", 5, false);
        assertTrue(frame.addPlay(play3));
        frame.scoreFrame();
        assertEquals(20, frame.getScore());

        //Extra balls should not be considered
        KotlinPlay play4 = new KotlinPlay("John", 2, false);
        frame.recordExtraBall(play4);
        frame.scoreFrame();
        assertEquals(20, frame.getScore());
    }

    @Test
    public void testCreation10thFrameSpare() {
        //Create a strike
        BowlingFrame frame = new Bowling10thFrame(10);
        KotlinPlay play1 = new KotlinPlay("John", 9, false);
        //Strike should not finish the 10th frame right away, contrary to regular ones
        assertFalse(frame.addPlay(play1));

        //Score for now should be 10
        frame.scoreFrame();
        assertEquals(9, frame.getScore());

        //But being a strike it will count the next two balls
        KotlinPlay play2 = new KotlinPlay("John", 1, false);
        //Should not finish yet
        assertFalse(frame.addPlay(play2));
        frame.scoreFrame();
        assertEquals(10, frame.getScore());

        //This play should finish the frame
        KotlinPlay play3 = new KotlinPlay("John", 5, false);
        assertTrue(frame.addPlay(play3));
        frame.scoreFrame();
        assertEquals(15, frame.getScore());
    }

    @Test
    public void testCreation10thFrameOpen() {
        //Create a strike
        BowlingFrame frame = new Bowling10thFrame(10);
        KotlinPlay play1 = new KotlinPlay("John", 5, false);
        //Strike should not finish the 10th frame right away, contrary to regular ones
        assertFalse(frame.addPlay(play1));

        //Score for now should be 10
        frame.scoreFrame();
        assertEquals(5, frame.getScore());

        //But being a strike it will count the next two balls
        KotlinPlay play2 = new KotlinPlay("John", 1, false);
        //Should finish the frame
        assertTrue(frame.addPlay(play2));
        frame.scoreFrame();
        assertEquals(6, frame.getScore());
    }

    @Test(expected = IllegalStateException.class)
    public void testFrameAddPlayAfterFinished() {
        //Create a strike
        BowlingFrame frame1 = new BowlingFrame(1);
        KotlinPlay play1 = new KotlinPlay("John", 5, false);
        //Open frame, not finished
        assertFalse(frame1.addPlay(play1));

        //This should finish it
        KotlinPlay play2 = new KotlinPlay("John", 5, false);
        assertTrue(frame1.addPlay(play2));

        //Should fail
        KotlinPlay play3 = new KotlinPlay("John", 5, false);
        frame1.addPlay(play3);
    }

}
