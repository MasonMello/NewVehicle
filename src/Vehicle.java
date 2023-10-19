class Vehicle {
    private String brand;
    private int speed;
    private int distanceTraveled;

    private int currentLapSpeed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
        this.distanceTraveled = 0;
    }

    public String getBrand() {
        return brand;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public void updateDistance(int speed) {
        // Update the distance traveled based on speed
        distanceTraveled += speed;
    }

    public int getCurrentLapSpeed() {
        return currentLapSpeed;
    }

    public void setCurrentLapSpeed(int currentLapSpeed) {
        this.currentLapSpeed = currentLapSpeed;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", speed=" + speed +
                ", distanceTraveled=" + distanceTraveled +
                '}';
    }
}