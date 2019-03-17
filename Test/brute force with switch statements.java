import java.awt.*;
import java.util.Random;

public class Robot {
    static final double maxEnergy = 200;
    double currentEnergy;

    int currentX;
    int currentY;
    Point location;

    public Robot() {
        currentEnergy = 200;
        currentX = 0;
        currentY = 0;
        location = new Point(currentX, currentY);
    }


    //currently this is not setting the new position so after each run it keeps starting back from 0,0
    private void curious() {
        Random random = new Random();
        int randomGoalX = random.nextInt(401) - 200;
        int randomGoalY = random.nextInt(401) - 200;
        Point goal = new Point(randomGoalX, randomGoalY);


//        double distance = location.distance(randomGoalX, randomGoalY, currentX, currentY);
//
//        System.out.println("distance: " + distance);
//        System.out.println("goal: " + goal);
//        System.out.println("walk :" + location);


        if (location.distance(currentX, currentY, randomGoalX, randomGoalY) < 9.0) {
            snap();
        }
        if (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
            hungry();
        }

        double distance = location.distance(currentX, currentY, randomGoalX,randomGoalY);
        double newDistance = 0;

        int checkDistanceCase = 1;
        switch (checkDistanceCase) {
            case 1:
                newDistance = location.distance(currentX, currentY, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 2:
                newDistance = location.distance(currentX + 13, currentY, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 3:
                newDistance = location.distance(currentX + 13, currentY, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 4:
                newDistance = location.distance(currentX, currentY + 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 5:
                newDistance = location.distance(currentX, currentY - 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 6:
                newDistance = location.distance(currentX + 13, currentY + 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 7:
                newDistance = location.distance(currentX - 13, currentY - 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 8:
                newDistance = location.distance(currentX + 13, currentY - 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }

            case 9:
                newDistance = location.distance(currentX - 13, currentY + 13, randomGoalX, randomGoalY);
                if (newDistance < distance) {
                    distance = newDistance;
                }
        }

        System.out.println("goal location " + goal);
        System.out.println("current location " + location);
        System.out.println("distance between goal and location is: "  + location.distance(currentX,currentY,randomGoalX,randomGoalY));
        System.out.println("move " + distance);
    }



    private void move(){

    }







    private void snap(){

    }





    private void hungry() {
//        uses its memory to move toward last remembered energy location
//          the memory needs to be a stack
//        if it finds new energy it remembers that and then keeps going towards the goal it had
//        if there is no remembered memory then it just keeps being curious


    }

    private void inactive() {
//    end the simmulation
//        sends everything to sample to compute stats I think
    }


    private void states() {
        Robot chr = new Robot();

        while (currentEnergy / maxEnergy > 0.50) {
            chr.curious();
        }
        while (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
//            this needs to be hungry but currently there is nothing in hungry. and This avoids an infinite loop
            chr.hungry();
        }
        if (currentEnergy / maxEnergy <= 0.0) {
            chr.inactive();
        }
    }


    public static void main(String[] args) {
        Robot blah = new Robot();
        blah.curious();
    }



}
