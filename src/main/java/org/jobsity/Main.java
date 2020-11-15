package org.jobsity;

import org.jobsity.model.BowlingPlay;
import org.jobsity.model.BowlingPlayer;
import org.jobsity.model.GameEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private final TxtParser parser;
    private final BowlingTokenizer tokenizer;
    private final GameEngine engine;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No data file provided");
            return;
        }
        System.out.println("running with args: " + args[0]);
        new Main(args[0]);
    }

    Map<String, BowlingPlayer> players = new HashMap<>();

    public Main(String file) {
        // Main program
        parser = new TxtParser();
        tokenizer = new BowlingTokenizer();
        engine = new GameEngine();
        List<String> lines = parser.load(file);
        List<BowlingPlay> plays = tokenizer.toPlays(lines);
        plays.stream()
                .peek(System.out::println)
                .forEach(p -> {
                    BowlingPlayer player = engine.getOrRecordPlayerFor(p);
                    player.addPlay(p);
                });

        engine.printScores();
    }
}
