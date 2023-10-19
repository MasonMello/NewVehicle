import java.util.Random;

public class VehicleRace {
    public static Random rand = new Random();
    public static final int INITIAL_SPEED_MIN = 95;
    public static final int INITIAL_SPEED_MAX = 105;
    public static final int DISTANCE_TO_FINISH = 500;
    public static final int MILLIDELAY = 1000;

    public static void main(String[] args) {
        // Create two vehicles (cars) with initial speeds
        Vehicle car1 = new Vehicle("Toyota", getRandomSpeed());
        Vehicle car2 = new Kart(getRandomSpeed());

        // Display initial information
        System.out.println("Initial Information:");
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(ConsoleColors.GREEN + "LET THE RACE BEGIN!" + ConsoleColors.RESET);
        // Race until one car reaches the finish line
        raceUntilFinish(car1, car2);

        // Determine the winner
        determineWinner(car1, car2);
    }

    public static void raceUntilFinish(Vehicle car1, Vehicle car2){
        while (car1.getDistanceTraveled() < DISTANCE_TO_FINISH && car2.getDistanceTraveled() < DISTANCE_TO_FINISH) {
            raceCars(car1, car2);
            pause(MILLIDELAY);
        }
    }

    public static void determineWinner(Vehicle car1, Vehicle car2){
        if (car1.getDistanceTraveled() >= DISTANCE_TO_FINISH &&
                car1.getDistanceTraveled() > car2.getDistanceTraveled()) {
            System.out.println(car1.getBrand() + " won the race!");
        } else if (car2.getDistanceTraveled() >= DISTANCE_TO_FINISH &&
                car2.getDistanceTraveled() > car1.getDistanceTraveled()){
            System.out.println(car2.getBrand() + " won the race!");
        } else if (car2.getDistanceTraveled() >= DISTANCE_TO_FINISH &&
                car2.getDistanceTraveled() == car1.getDistanceTraveled()){
            System.out.println("It's a tie!");
        }
    }
    public static int getRandomSpeed() {
        // Generate a random initial speed within the specified range
        return rand.nextInt(INITIAL_SPEED_MAX - INITIAL_SPEED_MIN + 1) + INITIAL_SPEED_MIN;
    }

    public static void raceCars(Vehicle car1, Vehicle car2) {
        // Simulate a race step
        int speed1 = car1.getSpeed();
        int speed2 = car2.getSpeed();

        // Randomly vary the speeds slightly
        speed1 += rand.nextInt(21) - 10;
        speed2 += rand.nextInt(21) - 10;

        speed1 = checkForKart(car2, car1, speed1);
        speed2 = checkForKart(car1, car2, speed2);

        // Ensure speeds are positive
        speed1 = Math.max(speed1, 0);
        speed2 = Math.max(speed2, 0);

        // Update distances based on speeds
        car1.updateDistance(speed1);
        car2.updateDistance(speed2);

        System.out.println(car1.getBrand() + " advanced at an average speed of " + speed1 + "mph and has traveled " + car1.getDistanceTraveled() + " miles");
        System.out.println(car2.getBrand() + " advanced at an average speed of " + speed2 + "mph and has traveled " + car2.getDistanceTraveled() + " miles");
    }

    public static int checkForKart(Vehicle carToBeChecked, Vehicle carToBeAttacked, int speed){
        if(carToBeChecked instanceof Kart){
            int origspeed = speed;
            Kart k = (Kart) carToBeChecked;
            SpecialItem s = k.detailedAttack();
            speed -= s.getDelay();
            System.out.println(s.getTextColor() + "The " + k.getBrand() + " slowed " + carToBeAttacked.getBrand() + " by " +
                    s.getDelay() + " with a " + s.getItem() + " reducing the speed of " + origspeed + "mph to " + speed + "mph" + ConsoleColors.RESET);
        }
        return speed;
    }
    public static void pause(int millis){
        //need a try catch because the sleep method can throw an exception.
        //java will not compile if you do not handle a method that declares it may throw and exception.
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


