import java.util.Random;

public class Kart extends Vehicle{
    private static Random rand = new Random();
    private SpecialItem[] items = {new SpecialItem("Green Shell", 7, ConsoleColors.RED),
            new SpecialItem("Banana", 1, ConsoleColors.YELLOW),
    new SpecialItem("Boomerang", 5, ConsoleColors.BLUE)};
    public Kart( int speed){
        super("Mario Kart", speed);

    }
    public Kart(String racer, int speed){
        super(racer +" (Kart)", speed);
    }

    public int attack(){
        return detailedAttack().getDelay();
    }
    public SpecialItem detailedAttack(){
        int idx = rand.nextInt(items.length);
        return items[idx];
    }
}
