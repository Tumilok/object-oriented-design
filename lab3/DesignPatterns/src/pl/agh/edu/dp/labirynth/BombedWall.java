package pl.agh.edu.dp.labirynth;

public class BombedWall extends Wall {

    public BombedWall() {
        super();
    }

    @Override
    public void Enter() {
        System.out.println("You hit bombed wall");
    }
}
