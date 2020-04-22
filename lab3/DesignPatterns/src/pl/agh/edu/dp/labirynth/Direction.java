package pl.agh.edu.dp.labirynth;

public enum Direction {
    North(0), South(1), East(2), West(3);
    public final int value;

    private Direction(int value) {
        this.value = value;
    }

    private Direction evaluateDirection(int value) {
        switch (value) {
            case 0:
                return North;
            case 1:
                return South;
            case 2:
                return East;
            case 3:
                return West;
            default:
                return null;
        }
    }

    public Direction getOppositeSide() {
        return evaluateDirection((this.value + Direction.values().length / 2) % Direction.values().length);
    }


}