import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Energy {
    private static final int numberOfEnergy = 33;
    private static final double capacity = 300;
    private double amount;
    private Point place;

    public Energy(){
        amount = 300;
        place =  new Point(0,0);
    }

    public Energy(Point place){
        amount = 300;
        this.place = place;
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
                if (newEnergy.place.distance(e.place) < 20){
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
        return place;
    }


    public double recharge(double request){

        if (request <= amount) {
            amount = amount - request;

            return request;
        }
        else{
            double charge = amount;
            amount = 0;

            return charge;
        }
    }

    public boolean equals(Energy energy){
        return this.place.equals(energy.place);
    }


    private double getEnergy(){
        return amount;
    }




















}
