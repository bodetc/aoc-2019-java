package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.helpers.maze.GraphNode;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IntcodeMazeNode implements GraphNode<Point2D> {
    private final Point2D point;
    private final IntcodeMazeTile tile;
    private final IntcodeComputer computer;

    public IntcodeMazeNode(Point2D point, IntcodeMazeTile tile, IntcodeComputer computer) {
        this.point = point;
        this.tile = tile;
        this.computer = computer;
    }

    @Override
    public Point2D getKey() {
        return point;
    }

    public boolean isTarget() {
        return tile == IntcodeMazeTile.TARGET;
    }

    public Set<IntcodeMazeNode> getConnections() {
        return Arrays.stream(IntcodeMazeDirection.values())
                .map(this::createNode)
                .filter(node -> node.tile != IntcodeMazeTile.WALL)
                .collect(Collectors.toSet());
    }

    private IntcodeMazeNode createNode(IntcodeMazeDirection direction) {
        IntcodeComputer computer = new IntcodeComputer(this.computer);
        Point2D point = direction.fromPoint(this.point);
        computer.run(direction.getValue());
        IntcodeMazeTile tile = IntcodeMazeTile.fromValue(computer.getLastIntOutput());
        return new IntcodeMazeNode(point, tile, computer);
    }
}
