package org.jobsity.bowling;

import org.jobsity.bowling.engine.BowlingPlay;
import org.jobsity.bowling.exceptions.PlayCannotBeLoadedException;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingTokenizer {
    public BowlingTokenizer() {

    }

    public List<BowlingPlay> toPlays(List<String> lines) throws PlayCannotBeLoadedException{
        return lines
                .stream()
                .map(this::toPlay)
                .collect(Collectors.toList());
    }

    BowlingPlay toPlay(String line) throws PlayCannotBeLoadedException {
        String[] split = line.split(" ");
        if (split.length != 2)
            throw new PlayCannotBeLoadedException("Wrong format in line " + line);

        int pins = 0;
        boolean fault = false;
        try {
            pins = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            if (!split[1].equals("F"))
                throw new RuntimeException(e);
            fault = true;
        }
        return new BowlingPlay(split[0], pins, fault);
    }
}
