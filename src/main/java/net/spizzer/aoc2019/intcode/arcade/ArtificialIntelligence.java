package net.spizzer.aoc2019.intcode.arcade;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

public class ArtificialIntelligence {
    private final ArcadeCabinet cabinet;

    public ArtificialIntelligence(ArcadeCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Joystick nextMove() {
        Point2D ball = cabinet.getPosition(Tile.BALL);
        Point2D paddle = cabinet.getPosition(Tile.PADDLE);
        return Joystick.fromValue(Integer.compare(ball.x, paddle.x));
    }
}
