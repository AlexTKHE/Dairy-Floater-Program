import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

public class HalfHourIntervals {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        // Vars
        schedule may30 = new schedule(14, 21, 2);

        // assumption that there will never be more cashiers than lines
        // assumption that there will never be less order takers than lines
        
        may30.changeNumRotations(1, 3);



        // using random class and methods
        randomize random = new randomize();
        employee[] daysShift30 = random.createEmployees(45);
        currentPositions[] positions = new currentPositions[(may30.getEnd() - may30.getStart()) * 2];
        int count = 0;

        // Creating employees

        // creating the schedule

        for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

            // Making count go up to check if the person is on shift at that time &
            // intiliziing positions
            positions[i - may30.getStart()] = new currentPositions(may30.getNumCashiers(), may30.getNumOrderTakers());
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

        write myWriter = new write("Walk-ups", "Driveway", "Curb", "Floaters");
        myWriter.writeSchedule(daysShift30, positions, may30);

    }
}