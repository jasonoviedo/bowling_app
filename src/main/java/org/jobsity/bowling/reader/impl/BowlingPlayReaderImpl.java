package org.jobsity.bowling.reader.impl;

import org.jobsity.bowling.exceptions.PlayCannotBeLoadedException;
import org.jobsity.bowling.reader.BowlingPlayVO;
import org.jobsity.bowling.reader.BowlingPlayReader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class knows how to load a BowlingPlay out of a formatted line
 */
public class BowlingPlayReaderImpl implements BowlingPlayReader {
    public BowlingPlayReaderImpl() {

    }

    /**
     * List of lines to read
     *
     * @param lines Lines in the form PlayerName value
     * @return List of read plays
     * @throws PlayCannotBeLoadedException If something goes wrong
     */
    @Override
    public List<BowlingPlayVO> toPlays(List<String> lines) throws PlayCannotBeLoadedException {
        return lines
                .stream()
                .map(this::toPlay)
                .collect(Collectors.toList());
    }

    BowlingPlayVO toPlay(String line) throws PlayCannotBeLoadedException {
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
        return new BowlingPlayVO(split[0], pins, fault);
    }
}
