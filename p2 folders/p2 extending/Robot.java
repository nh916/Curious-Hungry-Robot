import java.awt.*;
import java.util.*;


public class Robot extends Point{

//    need to figure out if the instance methods are correct or not???!!!
//          do we need more, less, and are the ones here correct or not??!!?

// i am not sure if this error is actually occuring
// the robot is : is being printed twice. This could get dangerous and something could go wrong
//    check and fix this

//    idk why its happening or how to fixx it

//    need to fix extends so they both extend and it works
//          dont know how thats supposed to work




// when he gets at energy can he be less than 0 or does he have to be more than 0

//    he is requesting it at times without getting to the energy location
//            and when he gets there he is at -11 or something. is he still supposed to be at -11???
//      check to see if this is still happening or not

//    check to see if storing fifo and lifo is working correctly.
//    check to see if all the code is working correctly and if all is working correctly say that in report
//    say what you have and what could have gone wrong and how you would have made sure the answers are correct if you had extra time



    ArrayList<Energy> AllEnergy;
    static private final double maxEnergy = 400;
    private double currentEnergy;

    double amountTravelled;

    private Deque<Energy> memory;
    private boolean fifo;


    public Robot(boolean fifo) {
        super(0, 0);
        this.fifo = fifo;
        currentEnergy = 400;

        this.AllEnergy = Energy.makeAllEnergy();
        memory = new ArrayDeque<Energy>();
        amountTravelled = 0;
    }


    protected void activate() {
            System.out.println("activate energy " + currentEnergy);

            if (currentEnergy / maxEnergy > 0.50 || memory.isEmpty() && currentEnergy > 0) {
                curious();
            } else if (currentEnergy / maxEnergy <= 0.50 && currentEnergy / maxEnergy > 0) {
                hungry();
            } else if (currentEnergy / maxEnergy <= 0.0) {
                inactive();
            } else {
//            just to let me know if anything goes wrong
//            runs it 1,000 times so i make sure to see it and it doesn't get lost in the other print statements
                for (int i = 0; i < 1000; i++) {
                    System.out.println("State unaccounted for!!!");
                }
            }
        }


    private void curious() {

        Random random = new Random();
        int randomGoalX = random.nextInt(400 + 1) - 200;
        int randomGoalY = random.nextInt(400 + 1) - 200;

        Point randomGoal = new Point(randomGoalX, randomGoalY);

        System.out.println("picked a new point " + randomGoal);
        System.out.println("robot is: " + super.getLocation());
//        System.out.println("stack energy is " + currentEnergy);

//        While should be here. because, we need to check for hungry and snap
//        while its not at the robot
//        this is being repeated. should this be in another method or how can this be reused

        while (!getLocation().equals(randomGoal.getLocation()) && currentEnergy > 0) {
            if (currentEnergy/maxEnergy > 0.50 || memory.isEmpty()) {

                move(randomGoalX, randomGoalY);
            }
            else {
                break;
            }
        }

        System.out.println("End robot: " + super.getLocation());

        activate();
    }


    private void move(double goalX, double goalY) {
        double originalAmount = 0;

        Point startPoint = new Point();
        startPoint = super.getLocation();

        double goalDistanceX = goalX -  super.getX();
        double goalDistanceY = goalY -  super.getY();

//        while (!robot.getLocation().equals(randomGoal.getLocation()) && currentEnergy > 0) {


        if (goalDistanceX >= 0 && goalDistanceY >= 0) {
//            we're in the first quadrant (+,+)


            if (super.distance( super.getX() + 13, super.getY(), goalX, goalY) == super.distance( super.getX(),  super.getY() + 13, goalX, goalY)) {
//                move diagonal
                currentEnergy = currentEnergy - super.distance( super.getX() + 13,  super.getY() + 13, goalX, goalY);

                super.setLocation(super.getX() + 13,  super.getY() + 13);


            }
//            if x is shorter go right
            else if (super.distance( super.getX() + 13,  super.getY(), goalX, goalY) < super.distance( super.getX(), super.getY() + 13, goalX, goalY)) {
//                move 13 to the right
                currentEnergy = currentEnergy - super.distance( super.getX() + 13,  super.getY());
                super.setLocation( super.getX() + 13, super.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - super.distance( super.getX(), super.getY() + 13);
                super.setLocation( super.getX(), super.getY() + 13);
            }
        }


//Q2
        else if (goalDistanceX <= 0 && goalDistanceY >= 0) {
//          were in the 2nd quadrant (-,+)
            if (super.distance( super.getX() - 13,  super.getY(), goalX, goalY) == super.distance( super.getX(),  super.getY() + 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - super.distance( super.getX() - 13, super.getY() + 13);
                super.setLocation( super.getX() - 13,  super.getY() + 13);
            } else if (super.distance( super.getX() - 13, super.getY(), goalX, goalY) < super.distance( super.getX(),  super.getY() + 13, goalX, goalY)) {
//                move 13 to the left
                currentEnergy = currentEnergy - super.distance( super.getX() - 13, super.getY());
                super.setLocation(super.getX() - 13, super.getY());
            } else {
//                move 13 up
                currentEnergy = currentEnergy - super.distance(super.getX(), super.getY() + 13);
                super.setLocation(super.getX(), super.getY() + 13);
            }
        }


//Q3
//            were in the 3rd quadrant (-,-)
        else if (goalDistanceX <= 0 && goalDistanceY <= 0) {


            if (super.distance( super.getX() - 13, super.getY(), goalX, goalY) == super.distance( super.getX(), super.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - super.distance(super.getX() - 13,  super.getY() - 13);
                super.setLocation(super.getX() - 13, super.getY() - 13);


            } else if (super.distance( super.getX() - 13, super.getY(), goalX, goalY) < super.distance(super.getX(),  super.getY() - 13, goalX, goalY)) {
//                move 13 left
                currentEnergy = currentEnergy - super.distance(super.getX() - 13, super.getY());
                super.setLocation(super.getX() - 13, super.getY());

            } else {
//                move 13 down
                currentEnergy = currentEnergy - super.distance(super.getX(),  super.getY() - 13);
                super.setLocation(super.getX(),  super.getY() - 13);

            }
        }


//        Q4
        else if (goalDistanceX >= 0 && goalDistanceY <= 0) {
//            were in the 4th quadrant (+,-)

            if (super.distance( super.getX() + 13, super.getY(), goalX, goalY) == super.distance(super.getX(), super.getY() - 13, goalX, goalY)) {
//                go diagonal
                currentEnergy = currentEnergy - super.distance(super.getX() + 13,  super.getY() - 13);
                super.setLocation(super.getX() + 13, super.getY() - 13);

            } else if (super.distance(super.getX() + 13, super.getY(), goalX, goalY) > super.distance(super.getX(), super.getY() - 13, goalX, goalY)) {
//                move to the right 13
                currentEnergy = currentEnergy - super.distance( super.getX() + 13,  super.getY());
                super.setLocation(super.getX() + 13, super.getY());

            } else {
//                move down 13
                currentEnergy = currentEnergy - super.distance(super.getX(),  super.getY() - 13);
                super.setLocation(super.getX(), super.getY() - 13);

            }

        }


//        Cases unaccounted for
        else {
            System.out.println(super.getLocation());
            System.out.println("there was a case that was unaccounted for!");
            inactive();

        }


        //            check for snap
        if (super.distance(super.getX(), super.getY(), goalX, goalY) <= 9) {

            currentEnergy = currentEnergy - super.distance(super.getX(), super.getY(), goalX, goalY);

            super.setLocation(goalX, goalY);

            System.out.println("goal hit!!!!!!!!!!!!!!!!!!!!");
        }

        amountTravelled = amountTravelled + startPoint.distance(startPoint.getX(),startPoint.getY(),super.getX(),super.getY());

//        check to see if there is a energy near by and if there is any add it to the memory in a way that is appropriate
        for (Energy i : AllEnergy) {
            if (super.distance(i.getPlace()) <= 13) {
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
        System.out.println("size currently " + AllEnergy.size());
        System.out.println("memory size is " + memory.size());

        Point memoryGoal;
        Energy energy;

        System.out.println("current energy is " + currentEnergy);


        if (fifo) {
            energy = memory.removeFirst();
            memoryGoal = energy.getPlace();

            System.out.println("memory goal is FIFO " + memoryGoal);

            while (!super.getLocation().equals(memoryGoal.getLocation()) && currentEnergy > 0) {
                move(memoryGoal.getX(), memoryGoal.getY());
            }


        } else {
            energy = memory.removeFirst();
            memoryGoal = energy.getPlace();
            System.out.println("memory goal is LIFO " + memoryGoal);

            while (!super.getLocation().equals(memoryGoal.getLocation()) && currentEnergy > 0) {
                move(memoryGoal.getX(), memoryGoal.getY());
            }

        }

        System.out.println("robots location is " + super.getLocation());


        if (super.getLocation().equals(memoryGoal.getLocation())){

            System.out.println("robot energy before request " + currentEnergy);
            double request = maxEnergy - currentEnergy;
            currentEnergy = currentEnergy + energy.recharge(request);


            System.out.println("we are in the if and the current location and the memory location are the same ");


            if (energy.getAmount() == 0) {
                AllEnergy.remove(energy);
                memory.remove(energy);

                System.out.println("SIZEEEE " + AllEnergy.size());
                System.out.println("SIZEEE memory " + memory.size());
            }

            System.out.println("we have recharged and we currently have " + currentEnergy);
        }
        activate();
    }


    private void inactive(){
        System.out.println("we have hit inactive");
        System.out.println(currentEnergy);
        System.out.println("amount travelled is " + amountTravelled);
    }



}

