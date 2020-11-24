package org.jobsity.bowling.printer.impl;

import org.jetbrains.annotations.NotNull;
import org.jobsity.bowling.engine.BowlingFrame;
import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.engine.BowlingPlay;
import org.jobsity.bowling.printer.BowlingPrinter;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class knows how to print a bowling game info into the specified format
 */
public class BowlingPrinterImpl implements BowlingPrinter {
    private BowlingGameEngine gameEngine;
    private final PrintStream out;

    public BowlingPrinterImpl(PrintStream out) {
        this.out = out;
    }

    /**
     * Print scores according to specification
     */
    public void printScore(BowlingGameEngine gameEngine) {
        out.println();
        out.println();
        out.println("=============================================");
        out.println();

        this.gameEngine = gameEngine;
        String frames = IntStream.range(1, 11)
                .mapToObj(Integer::toString)
                .map(s -> s + "\t\t")
                .collect(Collectors.joining());
        out.println("Frame\t\t" + frames);

        for (String player : gameEngine.getPlayerList()) {
            out.println(player);
            int cumulativeScore = 0;

            String str1 = gameEngine.getFrames(player)
                    .stream()
                    .map(frame1 -> getPrintStringFor(player, frame1))
                    .collect(Collectors.joining());

            StringBuilder sb = new StringBuilder();
            for (BowlingFrame frame : gameEngine.getFrames(player)) {
                cumulativeScore += frame.getScore();
                sb.append(cumulativeScore);
                sb.append("\t\t");
            }
            String str2 = sb.toString();
            out.println("Pinfalls\t" + str1);
            out.println("Score\t\t" + str2);
        }
    }

    @NotNull
    public String getPrintStringFor(String player, BowlingFrame frame) {
        List<BowlingPlay> plays = gameEngine.getPlays(player, frame);
        String res = (!frame.isStrike() ? getPrintStringFor(plays.get(0)) : "")
                + '\t'
                + (frame.isStrike() ? "x" : (frame.isSpare() ? "/" : getPrintStringFor(plays.get(1))))
                + '\t';
        if (frame.getFrameNumber() == 9) {
            String thirdBall = plays.size() == 3
                    ? getPrintStringFor(plays.get(2))
                    : "";
            res += thirdBall;
        }
        return res;
    }

    @NotNull
    public String getPrintStringFor(BowlingPlay play) {
        return play.getPinCount() == 10 ? "x" : (play.isFoul() ? "F" : String.valueOf(play.getPinCount()));
    }

}
