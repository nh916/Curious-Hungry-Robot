// this is the part i originally had for robot class to generate a random place to walk through

//        double distance = random.nextDouble() * 18.38;

        int randomCase = random.nextInt()*4;
                double distance = random.nextDouble() * 13;

                switch (randomCase){
                case 1: x = x + distance;
                break;
                case 2: x = x - distance;
                break;
                case 3: y = y + distance;
                break;
                case 4: y = y - distance;
                break;
                }