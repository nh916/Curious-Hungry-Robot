import java.awt.*;
import java.util.*;

public class Robot {

    ArrayList<Energy> AllEnergy;
    static private final double maxEnergy = 400;
    private double currentEnergy;
    private Point robot;
    double amountTravelled;
    private Deque<Energy> memory;
    private boolean fifo;


    public Robot(boolean fifo) {
        this.fifo = fifo;
        currentEnergy = 400;
        robot = new Point(0, 0);
        this.AllEnergy = Energy.makeAllEnergy();
        memory = new ArrayDeque<Energy>();
        amountTravelled = 0;
    }


    protected void activate() {

        if(currentEnergy/maxEnergy > 0.50 || memory.isEmpty() && currentEnergy > 0){
            curious();
        }
        else if (currentEnergy/maxEnergy <= 0.50 && currentEnergy/maxEnergy > 0){
            hungry();
        }
        else if (currentEnergy/maxEnergy <= 0.0){
            inactive();
        }
        else {
//            Print out statement in case there is a robot state that is unaccounted for
                System.out.println("Robot has encountered a state that has been unaccounted for in the Robot class and in the activate method.");
        }

    }


    private void curious() {

        Random random = new Random();
        int randomGoalX = random.nextInt(400 + 1) - 200;
        int randomGoalY = random.nextInt(400 + 1) - 200;

        Point randomGoal = new Point(randomGoalX, randomGoalY);



        while (!robot.getLocation().equals(randomGoal.getLocation()) && currentEnergy > 0) {
            if (currentEnergy/maxEnergy > 0.50 || memory.isEmpty()) {
                move(randomGoalX, randomGoalY);
            }
            else {
                break;
            }
        }


        activate();
    }


    private void move(double goalX, double goalY) {
        double originalAmount = 0;

        Point startPoint = new Point();
        startPoint = robot.getLocation();

        double goalDistanceX = goalX -  robot.getX();
        double goalDistanceY = goalY -  robot.getY();

//        while (!robot.getLocation().equals(randomGoal.getLocation()) && currentEnergy > 0) {


        if (goalDistanceX >= 0 && goalDistanceY >= 0) {
//            we're in the first quadrant (+,+)


            if (robot.distance( robot.getX() + 13, robot.getY(), goalX, goalY) == robot.distance( robot.getX(),  robot.getY() + 13, goalX, goalY)) {
//                move diagonal
                currentEnergy = currentEnergy - robot.distance( robot.getX() + 13,  robot.getY() + 13, goalX, goalY);

                robot.setLocation(robot.getX() + 13,  robot.getY() + 13);


            }
//            if x is shorter go right
            else if (robot.distance( robot.getX() + 13,  robot.getY(), goalX, goalY) < robot.distance( robot.getX(), robot.getY() + 13, goalX, goalY)) {
//                move 13 to the right
                currentEnergy = currentEnergy - robot.distance( robot.getX() + 13,  robot.getY());
                robot.setLocation( robot.getX() + 13, robot.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - robot.distance( robot.getX(), robot.getY() + 13);
                robot.setLocation( robot.getX(), robot.getY() + 13);
            }
        }


//Q2
        else if (goalDistanceX <= 0 && goalDistanceY >= 0) {
//          were in the 2nd quadrant (-,+)
            if (robot.distance( robot.getX() - 13,  robot.getY(), goalX, goalY) == robot.distance( robot.getX(),  robot.getY() + 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - robot.distance( robot.getX() - 13, robot.getY() + 13);
                robot.setLocation( robot.getX() - 13,  robot.getY() + 13);
            } else if (robot.distance( robot.getX() - 13, robot.getY(), goalX, goalY) < robot.distance( robot.getX(),  robot.getY() + 13, goalX, goalY)) {
//                move 13 to the left
                currentEnergy = currentEnergy - robot.distance( robot.getX() - 13, robot.getY());
                robot.setLocation( robot.getX() - 13, robot.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - robot.distance(robot.getX(), robot.getY() + 13);
                robot.setLocation( robot.getX(), robot.getY() + 13);
            }
        }


//Q3
//            were in the 3rd quadrant (-,-)
        else if (goalDistanceX <= 0 && goalDistanceY <= 0) {


            if (robot.distance( robot.getX() - 13, robot.getY(), goalX, goalY) == robot.distance( robot.getX(), robot.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - robot.distance( robot.getX() - 13,  robot.getY() - 13);
                robot.setLocation( robot.getX() - 13, robot.getY() - 13);


            } else if (robot.distance( robot.getX() - 13, robot.getY(), goalX, goalY) < robot.distance(robot.getX(),  robot.getY() - 13, goalX, goalY)) {
//                move 13 left
                currentEnergy = currentEnergy - robot.distance( robot.getX() - 13, robot.getY());
                robot.setLocation(robot.getX() - 13, robot.getY());

            } else {
//                move 13 down
                currentEnergy = currentEnergy - robot.distance(robot.getX(),  robot.getY() - 13);
                robot.setLocation(robot.getX(),  robot.getY() - 13);

            }
        }


//        Q4
        else if (goalDistanceX >= 0 && goalDistanceY <= 0) {
//            were in the 4th quadrant (+,-)

            if (robot.distance( robot.getX() + 13, robot.getY(), goalX, goalY) == robot.distance(robot.getX(), robot.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - robot.distance( robot.getX() + 13,  robot.getY() - 13);
                robot.setLocation(robot.getX() + 13, robot.getY() - 13);

            } else if (robot.distance(robot.getX() + 13,  robot.getY(), goalX, goalY) > robot.distance(robot.getX(), robot.getY() - 13, goalX, goalY)) {
//                move to the right 13
                currentEnergy = currentEnergy - robot.distance( robot.getX() + 13,  robot.getY());
                robot.setLocation(robot.getX() + 13, robot.getY());

            } else {
//                move down 13
                currentEnergy = currentEnergy - robot.distance(robot.getX(),  robot.getY() - 13);
                robot.setLocation(robot.getX(), robot.getY() - 13);

            }

        }


//        Cases unaccounted for
        else {
            System.out.println(robot.getLocation());
            System.out.println("There was an error in robot movement and there was a case unaccounted for. The robot is now inactive");
            inactive();

        }


        //            check for snap
        if (robot.distance( robot.getX(),  robot.getY(), goalX, goalY) <= 9) {

            currentEnergy = currentEnergy - robot.distance(robot.getX(), robot.getY(), goalX, goalY);

            robot.setLocation(goalX, goalY);

        }

        amountTravelled = amountTravelled + startPoint.distance(startPoint.getX(),startPoint.getY(),robot.getX(),robot.getY());

//        check to see if there is a energy near by and if there is any add it to the memory in a way that is appropriate
        for (Energy i : AllEnergy) {
            if (robot.distance(i.getPlace()) <= 13) {
                if (memory.contains(i)) {
                    memory.remove(i);
                }
                if (fifo) {
                    memory.addLast(i);

                } else {
                    memory.addFirst(i);
                }

            }

        }
//        activate();
    }


    private void hungry() {

        Point memoryGoal;
        Energy energy;


        energy = memory.removeFirst();
        memoryGoal = energy.getPlace();


        while (!robot.getLocation().equals(memoryGoal.getLocation()) && currentEnergy > 0) {
            move(memoryGoal.getX(), memoryGoal.getY());
        }



        if (robot.getLocation().equals(memoryGoal.getLocation())){


            double request = maxEnergy - currentEnergy;
            currentEnergy = currentEnergy + energy.recharge(request);



            if (energy.getAmount() == 0) {
                AllEnergy.remove(energy);
                memory.remove(energy);

            }

        }
        activate();
    }


    private void inactive(){
//        System.out.println(currentEnergy);
//        System.out.println("amount travelled: " + amountTravelled);
        return;
    }


}