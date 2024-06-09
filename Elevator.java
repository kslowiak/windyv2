public class Elevator {
    private int id;
    private int currentFloor;
    private int targetFloor;
    private int direction; // 1 for up, -1 for down, 0 for idle
    private boolean isTaken;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0; // Default starting floor
        this.targetFloor = 0;
        this.direction = 0; // Initially idle
        this.isTaken = false;
    }

    public boolean getIsTaken() {
        return isTaken;
    }

    public int getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {

        this.targetFloor = targetFloor;


        if (targetFloor > currentFloor) {
            isTaken=true;
            this.direction = 1; // Moving up
        } else if (targetFloor < currentFloor) {
            this.direction = -1; // Moving down
            isTaken=true;
        } else {
            this.direction = 0; // Idle
            isTaken=false;
        }
    }

    public void step() {
        if (currentFloor < targetFloor) {
            currentFloor++;
        } else if (currentFloor > targetFloor) {
            currentFloor--;
        }

        // Update direction
        if (currentFloor == targetFloor) {
            direction = 0; // Idle when target floor is reached
            isTaken=false;
        }
    }

    public String getStatus() {
        return String.format("Elevator %d: Current Floor: %d, Target Floor: %d, Direction: %d, Is Taken: %b",
                id, currentFloor, targetFloor, direction, isTaken);
    }
}
