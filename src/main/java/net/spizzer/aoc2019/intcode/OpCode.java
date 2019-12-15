package net.spizzer.aoc2019.intcode;

import net.spizzer.aoc2019.common.ValueBase;

enum OpCode implements ValueBase<Integer> {
    ADD(1, 2, 1),
    MULTIPLY(2, 2, 1),
    INPUT(3, 0, 1),
    OUTPUT(4, 1, 0),
    JUMP_IF_TRUE(5, 2, 0),
    JUMP_IF_FALSE(6, 2, 0),
    LESS_THAN(7, 2, 1),
    EQUALS(8, 2, 1),
    RELATIVE_BASE_OFFSET(9, 1, 0),
    EXIT(99, 0, 0);

    private final int value;
    private final int nbInputParam;
    private final int nbOutputParam;

    OpCode(int value, int nbInputParam, int nbOutputParam) {
        this.value = value;
        this.nbInputParam = nbInputParam;
        this.nbOutputParam = nbOutputParam;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public int getNumberOfInputParameters() {
        return nbInputParam;
    }

    public int getOutputOffset() {
        return nbOutputParam > 0 ? nbInputParam + 1 : 0;
    }

    public int getPositionChange() {
        return nbInputParam + nbOutputParam + 1;
    }

    public static OpCode fromInstruction(int instruction) {
        return ValueBase.fromValue(values(), instruction % 100);
    }
}
