import java.awt.*;
import java.util.*;

// ARN'T POINTS INTS? WHY WOULD A POINT BE A DOUBLE?
// would then we be rounding correctly???
// WHY ARE WE CASTING? THAT IS DANGEROUS!!!!!!!!!!!!$$$$$$$$%%%%%%%%########@@@@@@

//NEED TO FIX STACK OVERFLOWWWW!!!!!!!!

// i am not sure if this error is actually occuring
// the location is : is being printed twice. This could get dangerous and something could go wrong
//    check and fix this

// need to be sure not to call curious from move
// need to make hungry
// need to make it bounce back and fourth from hungry
// need to have a way detect energy when its around it
//    does snap go in move or in curious?

// need to consider states method
// need a way to make the energy available to robot
//    fixed by extending
// does energy start as soon as robot is started?
// send it all to sample after done and report out the answer


public class Robot{
    ArrayList<Energy> AllEnergy;
    static private final double maxEnergy = 400;
    private double currentEnergy;
    private Point location;


    private Deque<Energy> memory;
    public boolean fifo;


    public Robot(boolean fifo) {
        this.fifo = fifo;
        currentEnergy = 400;
        location = new Point(0, 0);
        this.AllEnergy = Energy.makeAllEnergy();
        memory = new ArrayDeque<Energy>();

    }

    public void activate (){
        while (currentEnergy > 0) {
            curious();
        }
    }

    //currently this is not setting the new position so after each run it keeps starting back from 0,0
    private void curious() {

        Random random = new Random();
        int randomGoalX = random.nextInt(400 + 1) - 200;
        int randomGoalY = random.nextInt(400 + 1) - 200;

        //        currently using randomGoal only to see if its within snap distance
        Point randomGoal = new Point (randomGoalX,randomGoalY);

        System.out.println("picked a new point " + randomGoal);
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

//Hungry Energy is: 88.38422689413609
//After all of that were at: java.awt.Point[x=-20,y=-42]
//Hungry Energy is: 88.38422689413609
//After all of that were at: java.awt.Point[x=-20,y=-42]
//        WHY IS THIS BEING RUN TWICE!!!!!!???????

    }

//as moving detect energy, check to see hungry/states, check for snap


    //    pass in the goal and let it move in that direction
//    so this could work for both memoryGoal and randomGoal
    private void move(int goalX, int goalY) {

        int goalDistanceX = goalX - (int)location.getX();
        int goalDistanceY = goalY - (int)location.getY();

        if (goalDistanceX >= 0 && goalDistanceY >= 0) {
//            we're in the first quadrant (+,+)

//            if x and y are equidistant then move diagonal
            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move diagonal
                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY() + 13, goalX, goalY);

                location.move((int)location.getX() + 13, (int)location.getY() + 13);
//                currentEnergy = currentEnergy - 26;

            }
//            if x is shorter go right
            else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move 13 to the right
                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY());
                location.move((int)location.getX() + 13, (int)location.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() + 13);
                location.move((int)location.getX(), (int)location.getY() + 13);
            }
        }


//Q2
        else if (goalDistanceX <= 0 && goalDistanceY >= 0) {
//          were in the 2nd quadrant (-,+)
            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY() + 13);
                location.move((int)location.getX() - 13, (int)location.getY() + 13);
            }

//            check to make sense out of this later. This is supposed to be correct
            else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
//                move 13 to the left
                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY());
                location.move((int)location.getX() - 13, (int)location.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() + 13);
                location.move((int)location.getX(), (int)location.getY() + 13);
            }
        }


//Q3
//            were in the 3rd quadrant (-,-)
        else if (goalDistanceX <= 0 && goalDistanceY <= 0) {


            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY() - 13);
                location.move((int)location.getX() - 13, (int)location.getY() - 13);


            } else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                move 13 left
                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY());
                location.move((int)location.getX() - 13, (int)location.getY());

            } else {
//                move 13 down
                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() - 13);
                location.move((int)location.getX(), (int)location.getY() - 13);

            }
        }


//        Q4
        else if (goalDistanceX >= 0 && goalDistanceY <= 0) {
//            were in the 4th quadrant (+,-)

            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY() - 13);
                location.move((int)location.getX() + 13, (int)location.getY() - 13);

            } else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) > location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
//                move to the right 13
                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY());
                location.move((int)location.getX() + 13, (int)location.getY());

            } else {
//                move down 13
                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() - 13);
                location.move((int)location.getX(), (int)location.getY() - 13);

            }

        }






//        Cases unaccounted for
        else {
            System.out.println(location.getLocation());
            System.out.println("there was a case that was unaccounted for!");

        }




        //            check for snap
        if (location.distance((int)location.getX(),(int)location.getY(),goalX,goalY) <= 9){
//                snap
            currentEnergy = currentEnergy - location.distance(location.getX(),location.getY(),goalX,goalY);

            location.move(goalX,goalY);

            System.out.println("GOAL HIT!! BOOYA!!");
        }

//        check to see if there is a energy near by and if there is any add it to the memory in a way that is appropriate
        for (Energy energy : AllEnergy){
            if (location.distance(energy.getPlace()) <= 13){
                if (fifo){
                    memory.addLast(energy);
                }
                else{
                    memory.addFirst(energy);
                }
            }
        }
    }




    private void hungry() {
//        uses its memory to move toward last remembered energy location
//          the memory needs to be a stack and a que
//        if it finds new energy it remembers that and then keeps going towards the goal it had
//        if there is no remembered memory then it just keeps being curious
        System.out.println("Hungry Energy is: " + currentEnergy);
        double request = maxEnergy - currentEnergy;
//        currentEnergy = currentEnergy + recharge(request);
        System.out.println("gulp gulp gulp... ahhhhh... Energy is now " + currentEnergy);

        // STACKOVERFLOW!!!!!!!!!!!!!!!!!!!!!!!!!!
//        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        if (fifo == true) {

            if (memory.size() == 0){
                activate();
            }

            memory.removeFirst();
            Point memoryGoal = memory.removeFirst().getPlace();
            move((int) memoryGoal.getX(), (int) memoryGoal.getY());

        } else {
            if (memory.size() == 0) {
                activate();
            }
            Point memoryGoal = memory.removeFirst().getPlace();
            move((int) memoryGoal.getX(), (int) memoryGoal.getY());
        }
    }







    private void inactive(){
//    end the simmulation
//        sends everything to sample to compute stats I think

        System.out.println("we have hit inactive");

    }




//    private void states(){
//
//        while (currentEnergy/maxEnergy > 0.50){
//            curious();
//        }
//        while (currentEnergy/maxEnergy <= 0.50 && currentEnergy/maxEnergy > 0){
//            hungry();
//        }
//        if (currentEnergy/maxEnergy <= 0.0){
//            inactive();
//        }
//    }
//
//



//    public static void main(String[] args) {
//        Robot blah = new Robot();
//        blah.curious();
//    }

}
