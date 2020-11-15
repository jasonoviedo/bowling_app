package org.jobsity;

import org.jobsity.model.BowlingPlay;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingTokenizer {
    public BowlingTokenizer() {

    }

    List<BowlingPlay> toPlays(List<String> lines) {
        return lines
                .stream()
                .map(this::toPlay)
                .collect(Collectors.toList());
    }

    BowlingPlay toPlay(String line) {
        String[] split = line.split(" ");
        if (split.length != 2)
            throw new RuntimeException("Wrong format in line " + line);

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
