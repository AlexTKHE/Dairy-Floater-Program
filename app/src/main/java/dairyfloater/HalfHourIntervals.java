package dairyfloater;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

public class HalfHourIntervals {
    public String createSchedule(int numberOfEmployees) {
        Randomize random = new Randomize();
        Employee[] daysShift30 = random.createEmployees(numberOfEmployees);
        String total = "";
        for (int x = 0; x < daysShift30.length; x++) {
            String employeeName = daysShift30[x].getName();
            if (daysShift30[x].getShiftStart() < 13 && daysShift30[x].getShiftEnd() < 13) {
                total += (employeeName + ", " + daysShift30[x].getPositionName() + ", "
                        + daysShift30[x].getShiftStart() +
                        "-" + daysShift30[x].getShiftEnd() + ". ");
            } else if (daysShift30[x].getShiftStart() < 13 && daysShift30[x].getShiftEnd() >= 13) {
                total += (employeeName + ", " + daysShift30[x].getPositionName() + ", "
                        + daysShift30[x].getShiftStart() +
                        "-" + (daysShift30[x].getShiftEnd() - 12) + ". ");
            } else {
                total += (employeeName + ", " + daysShift30[x].getPositionName() + ", "
                        + (daysShift30[x].getShiftStart() - 12) +
                        "-" + (daysShift30[x].getShiftEnd() - 12) + ". ");
            }
        }
        return total;
    }
    public String createRotations(Employee[] daysShift30, int startInput, int endInput, int linesInput, int cashiersInput, int orderTakersInput) throws FileNotFoundException, UnsupportedEncodingException {

        // Vars
        Schedule may30 = new Schedule(startInput, endInput, linesInput);

        // assumption that there will never be more cashiers than lines
        // assumption that there will never be less order takers than lines
        
        may30.changeNumRotations(cashiersInput, orderTakersInput);



        // using random class and methods
        Randomize random = new Randomize();
        CurrentPositions[] positions = new CurrentPositions[(may30.getEnd() - may30.getStart()) * 2];
        int count = 0;

        // Creating employees

        // creating the schedule

        for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

            // Making count go up to check if the person is on shift at that time &
            // intiliziing positions
            positions[i - may30.getStart()] = new CurrentPositions(may30.getNumCashiers(), may30.getNumOrderTakers());
            count++;

            int hour = i - count / 2;
            for (int z = 0; z < daysShift30.length; z++) {
                daysShift30[z].changeFloat(true);
            }

            // to create the rotations
            for (int p = 0; p < may30.getNumCashiers(); p++) {
                for (int z = 0; z < daysShift30.length; z++) {
                    int x = 0;
                    int[] scrambled = random.randomizeEmployees(daysShift30);
                    x = scrambled[z];
                    daysShift30 = random.assignACashier(daysShift30, positions, may30, hour, i,
                            x, p);
                }
            }

            for (int p = 0; p < may30.getNumOrderTakers(); p++) {
                for (int z = 0; z < daysShift30.length; z++) {
                    int x = 0;
                    int[] scrambled = random.randomizeEmployees(daysShift30);
                    x = scrambled[z];
                    daysShift30 = random.assignAOrderTaker(daysShift30, positions, may30, hour, i,
                            x, p);
                }
            }

        }

        Write myWriter = new Write("Walk-ups", "Driveway", "Curb", "Floaters");
        myWriter.writeSchedule(daysShift30, positions, may30);
        return positions[6].getCashier();

    }
}