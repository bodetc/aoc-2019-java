package net.spizzer.aoc2019.intcode.maze;

import net.spizzer.aoc2019.helpers.astar.GraphNode;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MazeNode implements GraphNode<Point2D> {
    private final Point2D point;
    private final MazeTile tile;
    private final IntcodeComputer computer;

    public MazeNode(Point2D point, MazeTile tile, IntcodeComputer computer) {
        this.point = point;
        this.tile = tile;
        this.computer = computer;
    }

    @Override
    public Point2D getKey() {
        return point;
    }

    public boolean isTarget() {
        return tile == MazeTile.TARGET;
    }

    public Set<MazeNode> getConnections() {
        return Arrays.stream(MazeDirection.values())
                .map(this::createNode)
                .filter(node -> node.tile != MazeTile.WALL)
                .collect(Collectors.toSet());
    }

    private MazeNode createNode(MazeDirection direction) {
        IntcodeComputer computer = new IntcodeComputer(this.computer);
        Point2D point = direction.fromPoint(this.point);
        computer.run(direction.getValue());
        MazeTile tile = MazeTile.fromValue(computer.getLastIntOutput());
        return new MazeNode(point, tile, computer);
    }
}
