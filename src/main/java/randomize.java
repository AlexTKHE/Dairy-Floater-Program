import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class randomize {

    public employee[] createEmployees(int n) {
        String name = "";
        String instancePosition = "";
        employee[] daysShift30 = new employee[n];
        for (int i = 0; i < daysShift30.length; i++) {
            // all following logic to make a random and logical shift

            Random rand = new Random();
            int upperbound = 100;
            int int_random = rand.nextInt(upperbound);
            int start = 0;
            int shiftLength = 0;
            // Logic to randomize the position
            if (int_random < 10) {
                instancePosition = "Order Filler";
            } else if (int_random < 85) {
                instancePosition = "Rotation Floater";
            } else if (int_random < 95) {
                instancePosition = "Station Manager";
            } else {
                instancePosition = "Manager";
            }
            // Logic to randomize the shifts start

            // For any shift before 8 it will ocilate between 2-9s 2-6s and 6-9s at random
            if (i <= 8 || i >= 16) {
                int upperbound2 = 2;
                int startR = rand.nextInt(upperbound2);
                if (startR == 0) {
                    start = 14;
                    int upperbound3 = 2;
                    int startR1 = rand.nextInt(upperbound3);
                    if (startR1 == 0) {
                        shiftLength = 4;
                    } else {
                        shiftLength = 7;
                    }
                } else {
                    start = 18;
                    shiftLength = 3;
                }
            }
            // between 8 and 10 will be two 8-2s

            else if (i == 9 || i == 10) {
                start = 8;
                shiftLength = 6;
            }

            else if (i == 11) {
                start = 11;
                shiftLength = 5;
            } else if (i > 11 || i < 16) {
                start = 12;
                shiftLength = 4;

            }

            // START

            Random rand5 = new Random();
            int upperbound3 = 4944;
            int int_random3 = rand5.nextInt(upperbound3) + 1;

            // Logic to randomize the position

            try {
                Scanner in = new Scanner(new File("src/main/txt/names.txt"));
                for (int o = 0; o < int_random3; o++) {
                    // Check to make sure that reading works
                    in.nextLine();
                }
                name = in.nextLine();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // END

            daysShift30[i] = new employee(name, instancePosition, start, shiftLength);
        }
        return daysShift30;
    }

    public int[] randomizeEmployees(employee[] daysShift30) {

        // create a random scrambled array for employees on shift on that time

        int[] randomA = new int[daysShift30.length];
        for (int y = 0; y < daysShift30.length; y++) {
            randomA[y] = y;
        }

        Random rand3 = new Random();

        for (int y = 0; y < randomA.length; y++) {
            int randomIndexToSwap = rand3.nextInt(randomA.length);
            int temp = randomA[randomIndexToSwap];
            randomA[randomIndexToSwap] = randomA[y];
            randomA[y] = temp;
        }
        // end of randomization

        return randomA;
    }

    public employee[] assignACashier(employee[] daysShift30, currentPositions[] positions, schedule may30,
            int hour, int halfHour, int employee, int takerNum) {
        if (!positions[halfHour-may30.getStart()].hasCashier(takerNum) && daysShift30[employee].isOnShift(hour) && daysShift30[employee].getPosition() == 1
                && daysShift30[employee].canFloat()) {
            // if the person is avaliable it will put them on the part cashier
            positions[halfHour - may30.getStart()].changeCashier(daysShift30[employee].getName(), takerNum);
            daysShift30[employee].changeFloat(false);
        }
        return daysShift30;
    }

    public employee[] assignAOrderTaker(employee[] daysShift30, currentPositions[] positions, schedule may30,
             int hour, int halfHour, int employee, int takerNum) {
        
        if (!positions[halfHour-may30.getStart()].hasOrderTaker(takerNum) && daysShift30[employee].isOnShift(hour) && daysShift30[employee].getPosition() == 1
                && daysShift30[employee].canFloat()) {
            // if the person is avaliable it will put them on the part cashier
            positions[halfHour - may30.getStart()].changeOrderTaker(daysShift30[employee].getName(), takerNum);
            daysShift30[employee].changeFloat(false);
        }
        return daysShift30;
    }
}
