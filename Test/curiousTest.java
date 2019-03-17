// This is the curious state when it walks in any direction in any amount it feels like it
private void curious(){
        Random random = new Random();
        double randomPointX = random.nextDouble() * 13;
        double randomPointY = random.nextDouble() * 13;

//        currently only getting nice int points and not going in between any points that would be needed
        walk.setLocation(randomPointX,randomPointY);

        }