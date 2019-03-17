import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Energy extends Point{
    private static final int numberOfEnergy = 33;
    private static final double capacity = 300;
    private double amount;


    public Energy(){
        super(0,0);
        amount = 300;
    }

    public Energy(Point place){
        super(place);
        amount = 300;

    }

    private static Energy generateAnEnergy(){
        Random random = new Random();

        int randomPointX = random.nextInt(400 + 1)-200;
        int randomPointY = random.nextInt(400 + 1)-200;

        Point place = new Point (randomPointX,randomPointY);
        Energy energies = new Energy(place);
        return energies;
    }

    public double getAmount() {
        return amount;
    }


    public static ArrayList makeAllEnergy(){
        ArrayList <Energy> EnergyList = new ArrayList<Energy>();

        EnergyList.add(generateAnEnergy());

        for (int i = 0; i < numberOfEnergy - 1; i++) {
            Energy newEnergy = generateAnEnergy();
            boolean succeeded = true;

            for (Energy e: EnergyList) {
                if (newEnergy.distance(e) < 20){
                    succeeded = false;
                }
            }
            if (succeeded){
                EnergyList.add(newEnergy);
            }
            else{
                i--;
            }
        }
        return EnergyList;
    }


    public Point getPlace(){
        return super.getLocation();
    }


    public double recharge(double request){
        System.out.println("i want " + request);

        if (request <= amount) {
            amount = amount - request;
            System.out.println("charge given back " + request);
            System.out.println("left in the tank " + amount);

            return request;
        }
        else{
            double charge = amount;
            amount = 0;

            System.out.println("charge given back " + charge);
            System.out.println("left in the tank " + amount);
            return charge;
        }
    }

//    might not workkkk!!!!
    public boolean equals(Energy energy){
        if (energy.x == x && energy.y == y){
            return true;
        }
        return false;

//        return super.equals(energy);
    }

//    toString method just to see if each energy is far enough from each other
//    public String toString(){
//        return super.toString();
//
//    }

//    needs to have a getter that returns the amount of energy that is left in the energy tank


    private double getEnergy(){
        return amount;
    }




//    public static void main(String[] args){
////        System.out.println(makeAllEnergy());
//        ArrayList <Energy> test = makeAllEnergy();
//        System.out.println(test);
//        System.out.println(test.size());
//        Energy blah = new Energy();
//    }



















}
