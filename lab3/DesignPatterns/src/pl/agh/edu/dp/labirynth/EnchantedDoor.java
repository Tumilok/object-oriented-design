package pl.agh.edu.dp.labirynth;

public class EnchantedDoor extends Door {

    public EnchantedDoor(Room r1, Room r2) {
        super(r1, r2);
    }

    @Override
    public void Enter(){
        System.out.println("You opened Enchanted Door");
    }
}
