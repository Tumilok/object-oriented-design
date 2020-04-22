package pl.agh.edu.dp.labirynth;

public class CountingMazeBuilder implements MazeBuilder {

    private int counts;

    public CountingMazeBuilder() {
        counts = 0;
    }

    @Override
    public void addRoom(Room room) {
        counts += 5;
    }

    @Override
    public void addDoor(Room r1, Room r2) throws Exception {
        counts ++;
    }

    @Override
    public void addCommonWall(Room r1, Room r2, Direction direction) throws Exception {
        counts --;
    }

    @Override
    public Maze getCurrentMaze() {
        return null;
    }

    public int getCounts() {
        return counts;
    }
}
