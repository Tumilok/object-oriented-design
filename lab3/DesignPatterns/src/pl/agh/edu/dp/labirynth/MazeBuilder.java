package pl.agh.edu.dp.labirynth;

public interface MazeBuilder {

    void addRoom(Room room);
    void addDoor(Room r1, Room r2) throws Exception;
    void addCommonWall(Room r1, Room r2, Direction direction) throws Exception;
    Maze getCurrentMaze();
}
