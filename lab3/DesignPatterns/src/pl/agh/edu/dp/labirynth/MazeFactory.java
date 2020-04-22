package pl.agh.edu.dp.labirynth;

public class MazeFactory {

    Room createRoom(int number) {
        return new Room(number);
    }

    Wall createWall() {
        return new Wall();
    }

    Door createDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}
