package org.jobsity.bowling.engine.java;

import org.jobsity.bowling.engine.impl.JavaGameEngine;
import org.jobsity.bowling.engine.impl.JavaPlay;
import org.jobsity.bowling.engine.impl.JavaPlayer;
import org.junit.Test;

public class JavaEngineTests {
    private final JavaGameEngine gameEngine = new JavaGameEngine();

    @Test(expected = RuntimeException.class)
    public void testAddNewPlayers() {
        JavaPlay play1 = new JavaPlay("Player1", 10, false);
        JavaPlay play2 = new JavaPlay("Player2", 1, false);
        JavaPlay play3 = new JavaPlay("Player3", 1, false);
        JavaPlayer player1 = gameEngine.getOrRecordPlayerFor(play1);
        player1.addPlay(play1);
        JavaPlayer player2 = gameEngine.getOrRecordPlayerFor(play2);
        player2.addPlay(play2);

        //Finished first round
        player1 = gameEngine.getOrRecordPlayerFor(play1);
        //Player 3 did not play in the first round, exception should be raised
        JavaPlayer player3 = gameEngine.getOrRecordPlayerFor(play3);
    }
}
