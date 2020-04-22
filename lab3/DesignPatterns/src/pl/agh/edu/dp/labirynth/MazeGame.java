package pl.agh.edu.dp.labirynth;

public class MazeGame {
    public Maze createMaze(MazeBuilder mazeBuilder) throws Exception {

        Room r1 = new Room(1);
        Room r2 = new Room(2);

        mazeBuilder.addRoom(r1);
        mazeBuilder.addRoom(r2);

        mazeBuilder.addCommonWall(r1, r2, Direction.North);
        mazeBuilder.addDoor(r1, r2);

        return mazeBuilder.getCurrentMaze();
    }
}
