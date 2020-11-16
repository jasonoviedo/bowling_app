package org.jobsity.bowling.engine;

import org.junit.Test;

public class BowlingEngineTests {
    private final KotlinGameEngine gameEngine = new KotlinGameEngine();

    @Test(expected = RuntimeException.class)
    public void testAddNewPlayers() {
        BowlingPlay play1 = new BowlingPlay("Player1", 10, false);
        BowlingPlay play2 = new BowlingPlay("Player2", 1, false);
        BowlingPlay play3 = new BowlingPlay("Player3", 1, false);
        BowlingPlayer player1 = gameEngine.getOrRecordPlayerFor(play1);
        player1.addPlay(play1);
        BowlingPlayer player2 = gameEngine.getOrRecordPlayerFor(play2);
        player2.addPlay(play2);

        //Finished first round
        player1 = gameEngine.getOrRecordPlayerFor(play1);
        //Player 3 did not play in the first round, exception should be raised
        BowlingPlayer player3 = gameEngine.getOrRecordPlayerFor(play3);
    }
}
