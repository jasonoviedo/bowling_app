package org.jobsity;

import org.jobsity.bowling.Bowling;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No data file provided");
            return;
        }
        System.out.println("running with args: " + args[0]);
        new Bowling(args[0]);
    }
}
