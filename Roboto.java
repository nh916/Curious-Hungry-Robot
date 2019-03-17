//import java.awt.*;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Deque;
//
//public class Roboto {
//
//    static private final double maxEnergy = 400;
//    private double currentEnergy;
//    private Point location;
//
//
//
//    public boolean fifo;
//
//
//    public Roboto(boolean fifo) {
//        this.fifo = fifo;
//        currentEnergy = 400;
//        location = new Point(0, 0);
//    }
//
//
//    private void move(int goalX, int goalY) {
//
//        int goalDistanceX = goalX - (int)location.getX();
//        int goalDistanceY = goalY - (int)location.getY();
//
//        if (goalDistanceX >= 0 && goalDistanceY >= 0) {
////            we're in the first quadrant (+,+)
//
////            if x and y are equidistant then move diagonal
//            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
////                move diagonal
//                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY() + 13, goalX, goalY);
//
//                location.move((int)location.getX() + 13, (int)location.getY() + 13);
////                currentEnergy = currentEnergy - 26;
//
//            }
////            if x is shorter go right
//            else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
////                move 13 to the right
//                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY());
//                location.move((int)location.getX() + 13, (int)location.getY());
//            } else {
////                move 13 up
//                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() + 13);
//                location.move((int)location.getX(), (int)location.getY() + 13);
//            }
//        }
//
//
////Q2
//        else if (goalDistanceX <= 0 && goalDistanceY >= 0) {
////          were in the 2nd quadrant (-,+)
//            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
////                go diagonal
//                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY() + 13);
//                location.move((int)location.getX() - 13, (int)location.getY() + 13);
//            }
//
////            check to make sense out of this later. This is supposed to be correct
//            else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() + 13, goalX, goalY)) {
////                move 13 to the left
//                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY());
//                location.move((int)location.getX() - 13, (int)location.getY());
//            } else {
////                move 13 up
//                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() + 13);
//                location.move((int)location.getX(), (int)location.getY() + 13);
//            }
//        }
//
//
////Q3
////            were in the 3rd quadrant (-,-)
//        else if (goalDistanceX <= 0 && goalDistanceY <= 0) {
//
//
//            if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
////                go diagonal
//                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY() - 13);
//                location.move((int)location.getX() - 13, (int)location.getY() - 13);
//
//
//            } else if (location.distance((int)location.getX() - 13, (int)location.getY(), goalX, goalY) < location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
////                move 13 left
//                currentEnergy = currentEnergy - location.distance((int)location.getX() - 13, (int)location.getY());
//                location.move((int)location.getX() - 13, (int)location.getY());
//
//            } else {
////                move 13 down
//                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() - 13);
//                location.move((int)location.getX(), (int)location.getY() - 13);
//
//            }
//        }
//
//
////        Q4
//        else if (goalDistanceX >= 0 && goalDistanceY <= 0) {
////            were in the 4th quadrant (+,-)
//
//            if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) == location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
////                go diagonal
//                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY() - 13);
//                location.move((int)location.getX() + 13, (int)location.getY() - 13);
//
//            } else if (location.distance((int)location.getX() + 13, (int)location.getY(), goalX, goalY) > location.distance((int)location.getX(), (int)location.getY() - 13, goalX, goalY)) {
////                move to the right 13
//                currentEnergy = currentEnergy - location.distance((int)location.getX() + 13, (int)location.getY());
//                location.move((int)location.getX() + 13, (int)location.getY());
//
//            } else {
////                move down 13
//                currentEnergy = currentEnergy - location.distance((int)location.getX(), (int)location.getY() - 13);
//                location.move((int)location.getX(), (int)location.getY() - 13);
//
//            }
//
//        }
//
//
//
//
//
//
////        Cases unaccounted for
//        else {
//            System.out.println(location.getLocation());
//            System.out.println("there was a case that was unaccounted for!");
//
//        }
//
//
//
//
//        //            check for snap
//        if (location.distance((int)location.getX(),(int)location.getY(),goalX,goalY) <= 9){
////                snap
//            currentEnergy = currentEnergy - location.distance(location.getX(),location.getY(),goalX,goalY);
//
//            location.move(goalX,goalY);
//
//            System.out.println("GOAL HIT!! BOOYA!!");
//        }
//
////        check to see if there is a energy near by and if there is any add it to the memory in a way that is appropriate
//        for (Energy energy : AllEnergy){
//            if (location.distance(energy.getPlace()) <= 13){
//                if (fifo){
//                    memory.addLast(energy);
//                }
//                else{
//                    memory.addFirst(energy);
//                }
//            }
//        }
//        states();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
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
//}
