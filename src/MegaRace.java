import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MegaRace {
    private static ArrayList<Vehicle> vehicles;

    public static final int DISTANCE_TO_FINISH = 500;

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        vehicles = new ArrayList<>();
        String userIn;
        do{
            System.out.print("Enter V to add a vehicle, K to add a kart, and G to start the race ->");
            userIn = input.nextLine().trim().toUpperCase();
            if (userIn.equals("K")){
                addVehicle(true);
            }else if (userIn.equals("V")){
                addVehicle(false);
            }
        }while(!userIn.equals("G"));

        if(vehicles.size() < 2){
            System.out.println("There are not enough vehicles for the race!");
        }else{
            raceCars();
        }
    }

    public static void addVehicle(boolean isKart){
        Vehicle v;
        System.out.println("Please enter the speed: ");
        String StringSpeed = input.nextLine();
        int speed = Utilities.parseInt(StringSpeed, 1);
        if(isKart){
            System.out.print("Enter the racer's name: ");
            String name = input.nextLine();
            v = new Kart(name, speed);
        }else{
            System.out.print("Enter the vehicle's model: ");
            String type = input.nextLine();
            v = new Vehicle(type, speed);
        }
        vehicles.add(v);
    }

    public static void raceCars(){
        do {
            setLapSpeeds();
            kartAttacks();
            updateDistances();
            printDistances();
            Utilities.pause(500);
        }while(checkForWinner() == null);

    }


    public static void setLapSpeeds(){
        for (Vehicle vehicle: vehicles){
            vehicle.setCurrentLapSpeed((vehicle.getSpeed() - 5) + (ThreadLocalRandom.current().nextInt(0,10)));
        }
    }

    public static void kartAttacks(){
        for (Vehicle v: vehicles
             ) {
            if(v instanceof Kart){
                //loop to get an index of another car to attack (v != defender)
                //
                Vehicle carToBeAttacked = vehicles.get(ThreadLocalRandom.current().nextInt(vehicles.size()));
                int origspeed = carToBeAttacked.getSpeed();//all changes to speeds should be on the defender
                Kart k = (Kart) v;
                SpecialItem s = k.detailedAttack();
                carToBeAttacked.setCurrentLapSpeed(carToBeAttacked.getCurrentLapSpeed() - s.getDelay());
                System.out.println(s.getTextColor() + k.getBrand() + " slowed " + "The " + carToBeAttacked.getBrand() + " by " +
                        s.getDelay() + " with a " + s.getItem() + " reducing the speed of " + origspeed + "mph to " + carToBeAttacked.getCurrentLapSpeed() + "mph" + ConsoleColors.RESET);
            }
        }
    }

    public static void updateDistances(){
        for (Vehicle v: vehicles
             ) {
            v.updateDistance(v.getCurrentLapSpeed());
        }
    }

    public static void printDistances(){
        for (Vehicle vehicle: vehicles){
            System.out.println(vehicle.getDistanceTraveled());
        }
    }

    public static Vehicle checkForWinner(){
        for (Vehicle v: vehicles
             ) {
            if (v.getDistanceTraveled() >= DISTANCE_TO_FINISH){
                System.out.println(v.getBrand() + " WINS!");
                return v;
            }
        }
        return null;
    }
}
