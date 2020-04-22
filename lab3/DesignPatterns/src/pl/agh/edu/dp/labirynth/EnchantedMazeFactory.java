package pl.agh.edu.dp.labirynth;

public class EnchantedMazeFactory extends MazeFactory {

    @Override
    Room createRoom(int number) {
        return new EnchantedRoom(number);
    }

    @Override
    Wall createWall() {
        return new EnchantedWall();
    }

    @Override
    Door createDoor(Room r1, Room r2) {
        return new EnchantedDoor(r1, r2);
    }
}
