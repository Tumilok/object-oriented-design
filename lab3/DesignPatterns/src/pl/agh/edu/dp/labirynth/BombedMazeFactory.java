package pl.agh.edu.dp.labirynth;

public class BombedMazeFactory extends MazeFactory {

    @Override
    Room createRoom(int number) {
        return new BombedRoom(number);
    }

    @Override
    Wall createWall() {
        return new BombedWall();
    }

    @Override
    Door createDoor(Room r1, Room r2) {
        return new BombedDoor(r1, r2);
    }
}
