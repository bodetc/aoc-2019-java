package net.spizzer.aoc2019.day13;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.arcade.ArcadeCabinet;
import net.spizzer.aoc2019.intcode.arcade.ArtificialIntelligence;
import net.spizzer.aoc2019.intcode.arcade.Joystick;
import net.spizzer.aoc2019.intcode.arcade.Tile;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day13 extends AbstractDay<ArcadeCabinet, Long, Integer> {

    private final boolean silent;

    public Day13() {
        this(true);
    }

    private Day13(boolean silent) {
        this.silent = silent;
    }

    @Override
    public int getDay() {
        return 13;
    }

    @Override
    public ArcadeCabinet parseInput(List<String> lines) {
        long[] program = ParseUtils.parseProgram(lines);
        return new ArcadeCabinet(program);
    }

    @Override
    public Long solveFirstStar(ArcadeCabinet cabinet) {
        cabinet.run();

        printScreen(cabinet);

        return cabinet.getCount(Tile.BLOCK);
    }

    @Override
    public Integer solveSecondStar(ArcadeCabinet cabinet) {
        cabinet.insertCoins();
        ArtificialIntelligence ai = new ArtificialIntelligence(cabinet);
        Joystick joystick = Joystick.NEUTRAL;

        do {
            cabinet.run(joystick);
            printScreen(cabinet);
            joystick = ai.nextMove();
        } while (cabinet.getCount(Tile.BLOCK) > 0);

        return cabinet.getScore();
    }

    private void printScreen(ArcadeCabinet cabinet) {
        if (!silent) {
            cabinet.printScreen();
        }
    }

    public static void main(String[] args) {
        new Day13(false).runDay();
    }
}
