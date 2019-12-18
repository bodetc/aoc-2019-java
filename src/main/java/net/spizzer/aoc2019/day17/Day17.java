package net.spizzer.aoc2019.day17;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.intcode.ReturnReason;

import java.util.List;

public class Day17 extends AbstractDay<IntcodeComputer, Integer, Integer> {


    private final boolean silent;

    public Day17() {
        this(true);
    }

    private Day17(boolean silent) {
        this.silent = silent;
    }

    @Override
    public int getDay() {
        return 17;
    }

    @Override
    public IntcodeComputer parseInput(List<String> lines) {
        return new IntcodeComputer(lines);
    }

    @Override
    public Integer solveFirstStar(IntcodeComputer computer) {
        computer.run();
        int[] output = computer.getIntOutput();
        AsciiBoard board = new AsciiBoard(output);
        if (!silent) {
            board.printBoard();
        }

        return board.getIntersectionArea();
    }

    @Override
    public Integer solveSecondStar(IntcodeComputer computer) {
        IntcodeComputer boardComputer = new IntcodeComputer(computer);
        boardComputer.run();
        int[] output = boardComputer.getIntOutput();
        AsciiBoard board = new AsciiBoard(output);
        String robotPath = board.getRobotPath();

        List<String> compressPath = AsciiBoard.compressPath(robotPath);
        String instruction = String.join("\n", compressPath) + "\nn\n";

        computer.setProgram(0, 2);
        instruction.chars().forEach(c -> {
            if (!silent) {
                System.out.print((char) c);
            }
            computer.run(c);
        });

        return computer.getLastIntOutput();
    }

    public static void main(String[] args) {
        new Day17(false).runDay();
    }
}
