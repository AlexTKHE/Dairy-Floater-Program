package dairyfloater;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

record PositionsRecord(int[] countC, int[] countO) {
};

public class Write {
    String outFile = "/Users/alex/Desktop/Dairy-Floater-Program/app/src/main/resources/txt/output.txt";
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";
    private String name4 = "";
    private int cashierPerLine = 0;
    private int orderTakerPerLine = 0;

    public int numLines(Schedule may30) {
        return may30.getNumberOfLines();
    }

    public Write(String name1, String name2, String name3, String name4) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
    }

    public void createHeader(Schedule may30, int x, PrintWriter writer) {
        if (numLines(may30) > 0 && x == 0) {
            writer.println(" \n \n" + name1 + ": \n \n");
        } else if (numLines(may30) > 1 && x == 1) {
            writer.println(" \n \n" + name2 + ": \n \n");
        } else if (numLines(may30) > 1 && x == 2) {
            writer.println(" \n \n" + name3 + ": \n \n");
        } else if (numLines(may30) > 1 && x == 3) {
            writer.println(" \n \n" + name4 + ": \n \n");
        } else {
            writer.println("ERROR ERROR ERROR WRONG AMOUNT OF LINES NOOO \n \n \n");
        }
    }

    public void createShifts(PrintWriter writer, Employee[] daysShift30) {

        writer.println("The schedule for the day in the format of (Name, Position, shift start val - shift end val):");

        // to write the people on shift
        for (int x = 0; x < daysShift30.length; x++) {
            if (daysShift30[x].getShiftStart() < 13 && daysShift30[x].getShiftEnd() < 13) {
                writer.println(daysShift30[x].getName() + ", " + daysShift30[x].getPositionName() + ", "
                        + daysShift30[x].getShiftStart() +
                        "-" + daysShift30[x].getShiftEnd() + ".");
            } else if (daysShift30[x].getShiftStart() < 13 && daysShift30[x].getShiftEnd() >= 13) {
                writer.println(daysShift30[x].getName() + ", " + daysShift30[x].getPositionName() + ", "
                        + daysShift30[x].getShiftStart() +
                        "-" + (daysShift30[x].getShiftEnd() - 12) + ".");
            } else {
                writer.println(daysShift30[x].getName() + ", " + daysShift30[x].getPositionName() + ", "
                        + (daysShift30[x].getShiftStart() - 12) +
                        "-" + (daysShift30[x].getShiftEnd() - 12) + ".");
            }
        }
    }

    public void writeTime(int count, double timeHolder, PrintWriter writer) {
        if (count % 2 != 0) {
            if (timeHolder < 12) {
                writer.print((int) timeHolder + ":30-" + (int) (timeHolder + 0.5) + ":00 AM:        ");
            } else if (timeHolder == 12.5) {
                writer.print((int) timeHolder + ":30-" + (int) (timeHolder + .5) + ":00 PM:        ");
            } else if (timeHolder > 1) {
                writer.print((int) timeHolder - 12 + ":30-" + (int) (timeHolder - 11.5) + ":00 PM:         ");
            }
        } else {
            if (timeHolder < 12) {
                writer.print((int) timeHolder + ":00-" + (int) (timeHolder + 0.5) + ":30 AM:        ");
            } else if (timeHolder == 12) {
                writer.print((int) timeHolder + ":00-" + (int) (timeHolder + .5) + ":30 PM:        ");
            } else if (timeHolder > 12) {
                writer.print((int) timeHolder - 12 + ":00-" + (int) (timeHolder - 11.5) + ":30 PM:         ");
            }
        }
    }

    public boolean isMoreThanOneLine(Schedule may30) {
        if (numLines(may30) > 1) {
            return true;
        } else {
            return false;
        }
    }

    public PositionsRecord createPositionsPerfectMatch(Schedule may30, PrintWriter writer, CurrentPositions[] positions,
            int i, int[] countC, int[] countO) {
        if (numLines(may30) == 1) {
            for (int ii = 0; ii < positions[i - may30.getStart()].getNumCashiers(); ii++) {
                // writer.print("Cashier " + (ii+1) + ": " + + " ");
                String cashierName = positions[i - may30.getStart()].getCashier(ii);
                String fmtString = String.format("Cashier %3d: %15s   ", ii + 1, cashierName);
                writer.print(fmtString);
            }
            writer.print("    ");

            for (int ii = 0; ii < positions[i - may30.getStart()].getNumOrderTakers(); ii++) {
                String orderTakerName = positions[i - may30.getStart()].getOrderTaker(ii);
                String fmtString = String.format("Order Taker %3d: %15s   ", ii + 1, orderTakerName);
                writer.print(fmtString);
            }
            

        } else if (numLines(may30) == 2) {
            cashierPerLine = positions[i - may30.getStart()].getNumCashiers() / numLines(may30);
            orderTakerPerLine = positions[i - may30.getStart()].getNumOrderTakers() / numLines(may30);

            for (int ii = 0; ii < cashierPerLine; ii++) {
                // writer.print("Cashier " + (ii+1) + ": " + + " ");
                String cashierName = positions[i - may30.getStart()].getCashier(countC[i - may30.getStart()]);
                String fmtString = String.format("Cashier %3d: %15s   ", ii + 1, cashierName);
                writer.print(fmtString);
                countC[i - may30.getStart()]++;
            }

            for (int ii = 0; ii < orderTakerPerLine; ii++) {
                String orderTakerName = positions[i - may30.getStart()].getOrderTaker(countO[i - may30.getStart()]);
                String fmtString = String.format("Order Taker %3d: %15s   ", ii + 1, orderTakerName);
                writer.print(fmtString);
                countO[i - may30.getStart()]++;
            }

        } else if (numLines(may30) == 3) {

            cashierPerLine = positions[i - may30.getStart()].getNumCashiers() / numLines(may30);
            orderTakerPerLine = positions[i - may30.getStart()].getNumOrderTakers() / numLines(may30);

            for (int ii = 0; ii < cashierPerLine; ii++) {
                // writer.print("Cashier " + (ii+1) + ": " + + " ");
                String cashierName = positions[i - may30.getStart()].getCashier(countC[i - may30.getStart()]);
                String fmtString = String.format("Cashier %3d: %15s   ", ii + 1, cashierName);
                writer.print(fmtString);
                countC[i - may30.getStart()]++;
            }

            for (int ii = 0; ii < orderTakerPerLine; ii++) {
                String orderTakerName = positions[i - may30.getStart()].getOrderTaker(countO[i - may30.getStart()]);
                String fmtString = String.format("Order Taker %3d: %15s   ", ii + 1, orderTakerName);
                writer.print(fmtString);
                countO[i - may30.getStart()]++;
            }

        }

        else {
            writer.println("there was an error with reading the number of lines.");
            writer.println("Cashier: " + positions[i - may30.getStart()].getCashier() + "       Order Taker: "
                    + positions[i - may30.getStart()].getOrderTaker());

        }

        return new PositionsRecord(countC, countO);
    }

    private void lessCash(Schedule may30, double timeHolder, int count, PrintWriter writer,
            CurrentPositions[] positions, int[] countC) {
        for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

            timeHolder = i - (double) count / 2;

            // if it is odd count for time stuff
            writeTime(count, timeHolder, writer);

            String cashierName = positions[i - may30.getStart()]
                    .getCashier(countC[i - may30.getStart()]);
            String fmtString = String.format("Cashier %3d: %15s   ", 1, cashierName);
            writer.print(fmtString);

            // for time and creating a new line
            writer.println("");
            count++;
        }
    }

    public void lessCashMoreOT(int count, Schedule may30, PrintWriter writer, double timeHolder,
            CurrentPositions[] positions, int[] countC, int[] countO) {

        for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

            timeHolder = i - (double) count / 2;

            // if it is odd count for time stuff
            writeTime(count, timeHolder, writer);

            String cashierName = positions[i - may30.getStart()]
                    .getCashier(countC[i - may30.getStart()]);
            String fmtStringC = String.format("Cashier %3d: %15s   ", 1, cashierName);
            writer.print(fmtStringC);

            for (int ii = 0; ii < positions[i - may30.getStart()].getNumOrderTakers()
                    - positions[i - may30.getStart()].getNumOrderTakers() / numLines(may30) * numLines(may30); ii++) {
                String orderTakerName = positions[i - may30.getStart()].getOrderTaker(countO[i - may30.getStart()]);
                String fmtString = String.format("Order Taker %3d: %15s   ", ii + 1, orderTakerName);
                writer.print(fmtString);
                countO[i - may30.getStart()]++;
            }

            // for time and creating a new line
            writer.println("");
            count++;
        }
    }

    public void write(String n) throws FileNotFoundException, UnsupportedEncodingException {
        
        PrintWriter writer = new PrintWriter(outFile, "UTF-8");
        writer.print(n);
        writer.close();
    }

    public void writeSchedule(Employee[] daysShift30, CurrentPositions[] positions, Schedule may30)
            throws FileNotFoundException, UnsupportedEncodingException {

        double timeHolder = 0;
        int count = 0;
        int[] countC = new int[positions.length];
        int[] countO = new int[positions.length];

        PrintWriter writer = new PrintWriter(outFile, "UTF-8");

       

        // to write the people on cashier or ordertaker at any given time

        writer.println("Rotation Schedule for the day will be: ");

        count = 0;

        for (int x = 0; x < numLines(may30); x++) {
            createHeader(may30, x, writer);
            count = 0;
            for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

                timeHolder = i - (double) count / 2;

                // if it is odd count for time stuff
                writeTime(count, timeHolder, writer);

                if (numLines(may30) == 3 && positions[i - may30.getStart()].getNumCashiers() == 2 && x < 2) {
                    String cashierName = positions[i - may30.getStart()].getCashier(x);
                    String fmtString = String.format("Cashier %3d: %15s   ", 1, cashierName);
                    writer.print(fmtString);
                    countC[i - may30.getStart()]++;
                }

                PositionsRecord v = createPositionsPerfectMatch(may30, writer, positions, i, countC, countO);
                countC = v.countC();
                countO = v.countO();

                // for time and creating a new line
                writer.println("");
                count++;
            }

        }
        // start of next method
        boolean doesLineIntoOT = (int) may30.getNumOrderTakers() / numLines(may30) != (double) may30.getNumOrderTakers()
                / numLines(may30);
        boolean doesLineIntoCashier = (double) may30.getNumCashiers()
                / numLines(may30) != (int) may30.getNumOrderTakers()
                        / numLines(may30);

        if (doesLineIntoOT && (numLines(may30) < 3 || (numLines(may30) == 3 && may30.getNumCashiers() == 1))
                && doesLineIntoCashier) {
            count = 0;
            createHeader(may30, 3, writer);
            lessCashMoreOT(count, may30, writer, timeHolder, positions, countC, countO);
        }

        else if ((numLines(may30) < 3 || numLines(may30) == 3 && may30.getNumCashiers() == 1)
                && ((double)may30.getNumCashiers()%numLines(may30) != 0)) {
            count = 0;
            createHeader(may30, 3, writer);
            lessCash(may30, timeHolder, count, writer, positions, countC);
        } else if ((int) may30.getNumOrderTakers() / numLines(may30) != (double) may30.getNumOrderTakers()
                / numLines(may30)) {
            count = 0;
            createHeader(may30, 3, writer);
            for (int i = may30.getStart(); i < may30.getStart() + may30.getScheduleLength() * 2; i++) {

                timeHolder = i - (double) count / 2;

                // if it is odd count for time stuff
                writeTime(count, timeHolder, writer);

                for (int ii = 0; ii < positions[i - may30.getStart()].getNumOrderTakers()
                        - positions[i - may30.getStart()].getNumOrderTakers() / numLines(may30)
                                * numLines(may30); ii++) {
                    String orderTakerName = positions[i - may30.getStart()].getOrderTaker(countO[i - may30.getStart()]);
                    String fmtString = String.format("Order Taker %3d: %15s   ", ii + 1, orderTakerName);
                    writer.print(fmtString);
                    countO[i - may30.getStart()]++;
                }

                // for time and creating a new line
                writer.println("");
                count++;
            }
        }

        writer.close();
    }

}
