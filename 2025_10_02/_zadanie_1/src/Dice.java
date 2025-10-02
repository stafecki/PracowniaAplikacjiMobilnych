import java.util.Random;
public class Dice{
    private int val;

    public Dice(){
        val = new Random().nextInt(6)+1;
    }

    public int getVal(){
        return val;
    }
}
