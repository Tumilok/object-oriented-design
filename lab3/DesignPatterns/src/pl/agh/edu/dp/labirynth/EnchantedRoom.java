package pl.agh.edu.dp.labirynth;

public class EnchantedRoom extends Room {

    public EnchantedRoom(int number) {
        super(number);
    }

    @Override
    public void Enter() {
        System.out.println("You entered Enchanted Room");
    }
}
