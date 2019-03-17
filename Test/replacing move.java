private void move(Point goal) {

        int robotXRounded = (int)Math.round(robot.getX());
        int robotYRounded = (int)Math.round(robot.getY());

        int goalDistanceX = goalX - (int) robot.getX();
        int goalDistanceY = goalY - (int) robot.getY();

        if (goalDistanceX >= 0 && goalDistanceY >= 0) {
//            we're in the first quadrant (+,+)


        if (robot.distance((int) robot.getX() + 13, (int) robot.getY(), goalX, goalY) == robot.distance((int) robot.getX(), (int) robot.getY() + 13, goalX, goalY)) {
//                move diagonal
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() + 13, (int) robot.getY() + 13, goalX, goalY);

        robot.move((int) robot.getX() + 13, (int) robot.getY() + 13);


        }
//            if x is shorter go right
        else if (robot.distance((int) robot.getX() + 13, (int) robot.getY(), goalX, goalY) < robot.distance((int) robot.getX(), (int) robot.getY() + 13, goalX, goalY)) {
//                move 13 to the right
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() + 13, (int) robot.getY());
        robot.move((int) robot.getX() + 13, (int) robot.getY());
        } else {
//                move 13 up
        currentEnergy = currentEnergy - robot.distance((int) robot.getX(), (int) robot.getY() + 13);
        robot.move((int) robot.getX(), (int) robot.getY() + 13);
        }
        }


//Q2
        else if (goalDistanceX <= 0 && goalDistanceY >= 0) {
//          were in the 2nd quadrant (-,+)
        if (robot.distance((int) robot.getX() - 13, (int) robot.getY(), goalX, goalY) == robot.distance((int) robot.getX(), (int) robot.getY() + 13, goalX, goalY)) {
//                go diagonal
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() - 13, (int) robot.getY() + 13);
        robot.move((int) robot.getX() - 13, (int) robot.getY() + 13);
        } else if (robot.distance((int) robot.getX() - 13, (int) robot.getY(), goalX, goalY) < robot.distance((int) robot.getX(), (int) robot.getY() + 13, goalX, goalY)) {
//                move 13 to the left
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() - 13, (int) robot.getY());
        robot.move((int) robot.getX() - 13, (int) robot.getY());
        } else {
//                move 13 up
        currentEnergy = currentEnergy - robot.distance((int) robot.getX(), (int) robot.getY() + 13);
        robot.move((int) robot.getX(), (int) robot.getY() + 13);
        }
        }


//Q3
//            were in the 3rd quadrant (-,-)
        else if (goalDistanceX <= 0 && goalDistanceY <= 0) {


        if (robot.distance((int) robot.getX() - 13, (int) robot.getY(), goalX, goalY) == robot.distance((int) robot.getX(), (int) robot.getY() - 13, goalX, goalY)) {
//                go diagonal
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() - 13, (int) robot.getY() - 13);
        robot.move((int) robot.getX() - 13, (int) robot.getY() - 13);


        } else if (robot.distance((int) robot.getX() - 13, (int) robot.getY(), goalX, goalY) < robot.distance((int) robot.getX(), (int) robot.getY() - 13, goalX, goalY)) {
//                move 13 left
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() - 13, (int) robot.getY());
        robot.move((int) robot.getX() - 13, (int) robot.getY());

        } else {
//                move 13 down
        currentEnergy = currentEnergy - robot.distance((int) robot.getX(), (int) robot.getY() - 13);
        robot.move((int) robot.getX(), (int) robot.getY() - 13);

        }
        }


//        Q4
        else if (goalDistanceX >= 0 && goalDistanceY <= 0) {
//            were in the 4th quadrant (+,-)

        if (robot.distance((int) robot.getX() + 13, (int) robot.getY(), goalX, goalY) == robot.distance((int) robot.getX(), (int) robot.getY() - 13, goalX, goalY)) {
//                go diagonal
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() + 13, (int) robot.getY() - 13);
        robot.move((int) robot.getX() + 13, (int) robot.getY() - 13);

        } else if (robot.distance((int) robot.getX() + 13, (int) robot.getY(), goalX, goalY) > robot.distance((int) robot.getX(), (int) robot.getY() - 13, goalX, goalY)) {
//                move to the right 13
        currentEnergy = currentEnergy - robot.distance((int) robot.getX() + 13, (int) robot.getY());
        robot.move((int) robot.getX() + 13, (int) robot.getY());

        } else {
//                move down 13
        currentEnergy = currentEnergy - robot.distance((int) robot.getX(), (int) robot.getY() - 13);
        robot.move((int) robot.getX(), (int) robot.getY() - 13);

        }

        }


//        Cases unaccounted for
        else {
        System.out.println(robot.getLocation());
        System.out.println("there was a case that was unaccounted for!");
        inactive();

        }


        //            check for snap
        if (robot.distance((int) robot.getX(), (int) robot.getY(), goalX, goalY) <= 9) {

        currentEnergy = currentEnergy - robot.distance(robot.getX(), robot.getY(), goalX, goalY);

        robot.move(goalX, goalY);

        System.out.println("goal hit");
        }

//        check to see if there is a energy near by and if there is any add it to the memory in a way that is appropriate
        for (Energy i : AllEnergy) {
        if (robot.distance(i.getPlace()) <= 13) {
        if (fifo) {
        memory.addLast(i);
        } else {
        memory.addFirst(i);
        }
        }
        }
        }