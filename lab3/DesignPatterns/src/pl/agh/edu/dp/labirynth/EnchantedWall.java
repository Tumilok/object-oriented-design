package pl.agh.edu.dp.labirynth;

public class EnchantedWall extends Wall {

    public EnchantedWall() {
        super();
    }

    @Override
    public void Enter(){
        System.out.println("You hit Enchanted Wall");
    }
}
