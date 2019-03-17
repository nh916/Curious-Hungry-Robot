import java.awt.*;
import java.util.Random;

public class Robot {
    static final double maxEnergy = 200;
    double currentEnergy;

    //    int (int)location.getX();
//    int (int)location.getY();
    Point location;

    public Robot() {
        currentEnergy = 200;
//        (int)location.getX() = 0;
//        (int)location.getY() = 0;
        location = new Point(0, 0);
    }


    //currently this is not setting the new position so after each run it keeps starting back from 0,0
    private void curious() {
        Random random = new Random();
        int randomGoalX = random.nextInt(401) - 200;
        int randomGoalY = random.nextInt(401) - 200;

        //        currently using randomGoal only to see if its within snap distance
        Point randomGoal = new Point (randomGoalX,randomGoalY);

        System.out.println("random goal is " + randomGoal);
        System.out.println("location is: " + location.getLocation());
        System.out.println("Energy is: " + currentEnergy);


//        While should be here. because, we need to check for hungry and snap
//        while its not at the location
        while (!location.getLocation().equals(randomGoal.getLocation()) ) {
//            might want to put this in move because both curious and hungry is using it

//            check if it has enough energy to be curious
            if (currentEnergy / maxEnergy > 0.50){
//                then keep moving towards that goal
                move(randomGoalX, randomGoalY);
            }
//            check if its hungry
//            might wanna put hungry inside the while
//            also check for inactive
            if (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0){
                break;
            }
        }


        if (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
            hungry();
        }

        System.out.println("After all of that were at: " + location.getLocation());

////  currently this is used to check to snap or see if its hungry or not
//        if (location.distance((int)location.getX(), (int)location.getY(), randomGoalX, randomGoalY) < 9.0) {
//            snap();
//        }
//        if (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
//            hungry();
//        }

    }

//as moving detect energy, check to see hungry/states, check for snap


    //    pass in the goal and let it move in that direction
//    so this could work for both memoryGoal and randomGoal
    private void move(int goalX, int goalY) {

        int goalDistanceX = goalX - (int)location.getX();
        int goalDistanceY = goalY - (int)location.getY();

        if (goalDistanceX > 0 && goalDistanceY > 0) {
//            we're in the first quadrant (+,+)

//            if x and y are equidistant then move diagonal
            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move diagonal
                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY() + 13, goalX, goalY);
                location.move((int)location.getX() + 13, (int)location.getY() + 13);
//                currentEnergy = currentEnergy - 26;

//                might want to subtract by the amount it just moved ***

            }
//            if x is shorter go right
            else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move 13 to the right
                location.move((int)location.getX() + 13, (int)location.getY());
                currentEnergy = currentEnergy - 13;
            } else {
//                move 13 up
                location.move((int)location.getX(), (int)location.getY() + 13);
                currentEnergy = currentEnergy - 13;
            }
        }


//Q2
        else if (goalDistanceX < 0 && goalDistanceY > 0) {
//          were in the 2nd quadrant (-,+)
            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                go diagonal
                location.move((int)location.getX() - 13, (int)location.getY() + 13);
                currentEnergy = currentEnergy - 26;
            }

//            check to make sense out of this later. This is supposed to be correct
            else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move 13 to the left

//                here i was checking to see the distance to see if i was doing the calculation wrong
//                System.out.println(location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY));
//                System.out.println(location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY));

                location.move((int)location.getX() - 13, (int)location.getY());
                currentEnergy = currentEnergy - 13;
            } else {
//                move 13 up
                location.move((int)location.getX(), (int)location.getY() + 13);
                currentEnergy = currentEnergy - 13;
            }
        }


//Q3
//            were in the 3rd quadrant (-,-)
        else if (goalDistanceX < 0 && goalDistanceY < 0) {


            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                go diagonal
                location.move((int)location.getX() - 13, (int)location.getY() - 13);
                currentEnergy = currentEnergy - 26;

            } else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                move 13 left
                location.move((int)location.getX() - 13, (int)location.getY());
                currentEnergy = currentEnergy - 13;
            } else {
//                move 13 down
                location.move((int)location.getX(), (int)location.getY() - 13);
                currentEnergy = currentEnergy - 13;
            }
        }


//        Q4
        else if (goalDistanceX > 0 && goalDistanceY < 0) {
//            were in the 4th quadrant (+,-)

            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                go diagonal
                location.move((int)location.getX() + 13, (int)location.getY() - 13);
                currentEnergy = currentEnergy - 26;
            } else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) > location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                move to the right 13
                location.move((int)location.getX() + 13, (int)location.getY());
                currentEnergy = currentEnergy - 13;
            } else {
//                move down 13
                location.move((int)location.getX(), (int)location.getY() - 13);
                currentEnergy = currentEnergy - 13;
            }

        }


//        Cases unaccounted for
        else {
            System.out.println(location.getLocation());
            System.out.println("there was a case that was unaccounted for!");
//            just in case we hit (0,100)

        }
        //            check for snap
        if (location.distance((int)location.getX(),(int)location.getY(),goalX,goalY) <= 9){
//                snap
            currentEnergy = currentEnergy - location.distance(location.getX(),location.getY(),goalX,goalY);
            location.move(goalX,goalY);
            System.out.println("GOAL HIT!! BOOYA!!");

        }
    }







    private void hungry() {
//        uses its memory to move toward last remembered energy location
//          the memory needs to be a stack
//        if it finds new energy it remembers that and then keeps going towards the goal it had
//        if there is no remembered memory then it just keeps being curious
        System.out.println("Wooh I worked so hard, my energy is: " + currentEnergy);
        System.out.println("YUM YUM ME HUNGRY!");
    }





    private void inactive() {
//    end the simmulation
//        sends everything to sample to compute stats I think
    }




//    private void states() {
//        Robot chr = new Robot();
//
//        while (currentEnergy / maxEnergy > 0.50) {
//            chr.curious();
//        }
//        while (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
////            this needs to be hungry but currently there is nothing in hungry. and This avoids an infinite loop
//            chr.hungry();
//        }
//        if (currentEnergy / maxEnergy <= 0.0) {
//            chr.inactive();
//        }
//    }


    public static void main(String[] args) {
        Robot blah = new Robot();
        blah.curious();
    }



}
