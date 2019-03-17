public class CuriousHungryRobot {
    public static void main(String[] args){

//        Robot robot = new Robot(true);
//        robot.activate();


        Sample fifo = new Sample("Fifo");
        Sample Lifo = new Sample("Lifo");

        for (int i = 0; i < 1000; i++) {
            Robot robot = new Robot(true);
            robot.activate();
            fifo.add(robot.amountTravelled);
        }

        for (int i = 0; i < 1000; i++) {
            Robot robot = new Robot(false);
            robot.activate();
            Lifo.add(robot.amountTravelled);
        }

        fifo.computeStats();
        System.out.println(fifo);

        Lifo.computeStats();
        System.out.println(Lifo);



    }
}
