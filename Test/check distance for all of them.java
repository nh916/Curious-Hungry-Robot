
int checkDistanceCase = 1;

        switch (checkDistanceCase){
        case 1: location.setLocation(currentX,currentY);
        currentEnergy = currentEnergy - 0;
        checkDistanceCase + 1;
        break;
        case 2: location.setLocation(currentX + 13,currentY);
        currentEnergy = currentEnergy - 13;
        break;
        case 3: location.setLocation(currentX - 13,currentY);
        currentEnergy = currentEnergy - 13;
        break;
        case 4: location.setLocation(currentX,currentY + 13);
        currentEnergy = currentEnergy - 13;
        break;
        case 5: location.setLocation(currentX,currentY - 13);
        currentEnergy = currentEnergy - 13;
        break;
        case 6: location.setLocation(currentX + 13,currentY + 13);
        currentEnergy = currentEnergy - 26;
        break;
        case 7: location.setLocation(currentX - 13,currentY - 13);
        currentEnergy = currentEnergy - 26;
        break;
        case 8: location.setLocation(currentX + 13,currentY - 13);
        currentEnergy = currentEnergy - 26;
        break;
        case 9: location.setLocation(currentX - 13,currentY + 13);
        currentEnergy = currentEnergy - 13;
        break;

