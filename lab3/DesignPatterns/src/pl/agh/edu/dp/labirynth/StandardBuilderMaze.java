package pl.agh.edu.dp.labirynth;

public class StandardBuilderMaze implements MazeBuilder {

    private Maze currentMaze;

    public StandardBuilderMaze() {
        currentMaze = new Maze();
    }

    @Override
    public void addRoom(Room room) {
        room.setSide(Direction.North, new Wall());
        room.setSide(Direction.East, new Wall());
        room.setSide(Direction.South, new Wall());
        room.setSide(Direction.West, new Wall());
        currentMaze.addRoom(room);
    }

    @Override
    public void addDoor(Room r1, Room r2) throws Exception {
        Direction dir = getCommonWallDirection(r1, r2);
        if (dir == null) {
            throw new Exception("Rooms doesn't have common wall");
        }
        Door door = new Door(r1, r2);
        r1.setSide(dir, door);
        r2.setSide(dir.getOppositeSide(), door);
    }

    @Override
    public void addCommonWall(Room r1, Room r2, Direction dir) throws Exception {
        MapSite side = r1.getSide(dir);
        if (side == null) {
            throw new Exception("Side doesn't exist");
        }
        r2.setSide(dir.getOppositeSide(), side);
    }

    private Direction getCommonWallDirection(Room r1, Room r2) {
        for (Direction dir: Direction.values()) {
            if (r1.getSide(dir).equals(r2.getSide(dir.getOppositeSide()))) {
                return dir;
            }
        }
        return null;
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
