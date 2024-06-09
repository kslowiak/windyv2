import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.signum;

public class ElevatorSystem {
    private List<Elevator> elevators;
    private List<Request> requests;

    public ElevatorSystem(int numElevators) { //creation of elevators
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i));
        }
        requests = new ArrayList<>();
    }

    public void pickup(int pickupFloor, int direction) {
        requests.add(new Request(pickupFloor, direction));
    }

    public void update(int elevatorId, int currentFloor, int targetFloor) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.setTargetFloor(targetFloor);
                break;
            }
        }
    }

    public void step() {

        if (!requests.isEmpty()) {
            //for (Request request : requests) {
            Request request = requests.get(0);
            // Find the nearest elevator
            Elevator nearestElevator = null;
            int minDistance = Integer.MAX_VALUE;
            for (Elevator elevator : elevators) {
                int distance = Math.abs(elevator.getCurrentFloor() - request.getPickupFloor());
                if(distance < minDistance) {

                    if (elevator.getIsTaken() && (elevator.getDirection() == request.getDirection()) && (elevator.getDirection() == signum(elevator.getTargetFloor() - elevator.getCurrentFloor()))) {

                        if ((elevator.getDirection() != -1) && (elevator.getCurrentFloor()<=request.getPickupFloor()) &&(elevator.getTargetFloor() > request.getPickupFloor())) {
                            minDistance = distance;
                            nearestElevator = elevator;
                        }
                        if ((elevator.getDirection() != 1) && (elevator.getCurrentFloor()>=request.getPickupFloor()) && (elevator.getTargetFloor() < request.getPickupFloor())) {
                            minDistance = distance;
                            nearestElevator = elevator;
                        }


                    }
                    if(!elevator.getIsTaken()) {
                        minDistance = distance;
                        nearestElevator = elevator;

                    }
                }
            }

            if (nearestElevator != null) {
                if(!nearestElevator.getIsTaken()){
                    nearestElevator.setTargetFloor(request.getPickupFloor());
                    request = requests.remove(0);
                    System.out.println("request taken; elevator id: " + nearestElevator.getId());
                } else if ((nearestElevator.getIsTaken())) {
                    //nearestElevator.setTargetFloor(request.getPickupFloor());
                    request = requests.remove(0);

                    System.out.println("request taken; elevator id: " + nearestElevator.getId() +" additional passanger ");
                }

            }

        }

        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public List<String> status() {
        List<String> statusList = new ArrayList<>();
        for (Elevator elevator : elevators) {
            statusList.add(elevator.getStatus());
        }
        return statusList;
    }

    private class Request {
        private int pickupFloor;
        private int direction; // 1 for up, -1 for down

        public Request(int pickupFloor, int direction) {
            this.pickupFloor = pickupFloor;
            this.direction = direction;
        }

        public int getPickupFloor() {
            return pickupFloor;
        }

        public int getDirection() {
            return direction;
        }

    }

    public void move(int elevatorId, int targetFloor) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.setTargetFloor(targetFloor);
                break;
            }
        }
    }

    public static void main(String[] args) {

        ElevatorSystem system = new ElevatorSystem(3); // Example with 3 elevators
        system.pickup(5, 1);
        system.pickup(3, 1);
        system.pickup(4, -1);
        system.pickup(7, -1);
        system.pickup(1, 1);

//        system.pickup(2, 1);
//        system.pickup(5, 1);
//        system.pickup(5, -1);
//        system.pickup(7, -1);
//        system.pickup(0, 1);


        for (int i = 0; i < 15; i++) {
            System.out.println("---  ");
            system.step();
            List<String> statuses = system.status();
            for (String status : statuses) {
                System.out.println(status);
            }
            System.out.println("---");
        }
    }
}