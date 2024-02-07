package dairyfloater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JTextArea;

public class ScanIn {

    public int amountEmployees(String schedule) {
        String remainSchedule = "";
        remainSchedule = schedule;
        int locationP = 0;
        int employees = 0;
        while (remainSchedule.length() > 0) {
            locationP = remainSchedule.indexOf(".");
            if (locationP != -1) {
                employees++;
                remainSchedule = remainSchedule.substring(locationP + 1);
            } else {
                employees++;
                remainSchedule = "";
            }
        }
        return employees;
    }

    public boolean firstCharSpace(String employeeStrings) {
        return employeeStrings.substring(0, 1).equals(" ");
    }

    public String[] createArray(String schedule) {
        String[] employeeArray = new String[amountEmployees(schedule)];
        String remainSchedule = schedule;
        int x = 0;
        while (remainSchedule.contains(".")) {
            employeeArray[x] = remainSchedule.substring(0, remainSchedule.indexOf(".") + 1);
            remainSchedule = remainSchedule.substring(remainSchedule.indexOf(".") + 1);
            x++;
        }
        return employeeArray;
    }

    public static String processString(String schedule) {
        schedule = schedule.trim();

        // Replace <br> or <br/> with an empty string
        schedule = schedule.replaceAll("(?i)<br\\s*/?>", "");

        // If the line still contains <br>, split it into multiple lines
        if (schedule.contains("<br>")) {
            String[] parts = schedule.split("<br>");
            schedule = String.join("", parts);
        }

        return schedule;
    }

    public static int findFirstNumberIndex(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static int findFirstAlphabeticalLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (Character.isLetter(currentChar)) {
                return i;
            }
        }

        return '\0';
    }

    public static int findSecondSpace(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
                if (count == 2) {
                    return i;
                }

            }
        }
        return -1;
    }

    public static int checkIfNextNumber(String str, int index) {
        if (Character.isDigit(str.charAt(index + 1))) {
            return index + 2;
        } else {
            return index + 1;
        }
    }

    public static int countEmployees(String filePath) {
        int count = 0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains("manager") ||
                        line.toLowerCase().contains("rotation floater") ||
                        line.toLowerCase().contains("senior rotation") ||
                        line.toLowerCase().contains("order filler") ||
                        line.toLowerCase().contains("full-time") ||
                        line.toLowerCase().contains("station manager")) {
                    count++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Employee createAnEmployee(String line, String position) {
        int shiftStart = Integer.parseInt(line.substring(findFirstNumberIndex(line),
                checkIfNextNumber(line, findFirstNumberIndex(line))));
        if (line.substring(findFirstNumberIndex(line),
                checkIfNextNumber(line, findFirstNumberIndex(line)) + 4).toLowerCase().contains("pm")
                && shiftStart != 12) {
            shiftStart += 12;
        }
        line = line.substring(checkIfNextNumber(line, findFirstNumberIndex(line)));
        int shiftEnd = Integer.parseInt(line.substring(findFirstNumberIndex(line),
                checkIfNextNumber(line, findFirstNumberIndex(line))));
        if (line.substring(findFirstNumberIndex(line),
                checkIfNextNumber(line, findFirstNumberIndex(line)) + 4).toLowerCase().contains("pm")
                && shiftEnd != 12) {
            shiftEnd += 12;
        }

        line = line.substring(checkIfNextNumber(line, findFirstNumberIndex(line)));

        line = line.substring(findFirstNumberIndex(line));
        int startIndex = findFirstAlphabeticalLetter(line);
        line = line.substring(startIndex);

        String name;

        if (findSecondSpace(line) == -1) {
            name = line;
        } else {
            name = line.substring(0, findSecondSpace(line));
        }

        int index = findFirstAlphabeticalLetter(name);

        String firstName = name.substring(index, name.substring(index).indexOf(" "));

        String lastName = name.substring(firstName.length() + 1);

        String lastInitial = lastName.substring(0, 1);
        
        lastInitial = lastInitial.replaceAll("[^a-zA-Z]", "");

        name = firstName + " " + lastInitial;

        Employee employee = new Employee(name, position, shiftStart, shiftEnd);
        return employee;
    }

    public static Employee[] employeeScanIn(String filePath) {
        int count = 0;
        Employee[] employees = new Employee[countEmployees(filePath)];
        String position = "";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains("manager") ||
                        line.toLowerCase().contains("rotation floater") ||
                        line.toLowerCase().contains("senior rotation") ||
                        line.toLowerCase().contains("order filler") ||
                        line.toLowerCase().contains("full-time") ||
                        line.toLowerCase().contains("station manager") 
                        ) {

                    if (line.toLowerCase().contains("manager")) {
                        position = "Manager";
                        employees[count] = createAnEmployee(line, position);
                    }
                     else if (line.toLowerCase().contains("rotation floater")) {
                        position = "Rotation Floater";
                        employees[count] = createAnEmployee(line, position);
                    } else if (line.toLowerCase().contains("senior rotation")) {
                        position = "Senior Rotation";
                        employees[count] = createAnEmployee(line, position);
                    } else if (line.toLowerCase().contains("order filler")) {
                        position = "Order Filler";
                        employees[count] = createAnEmployee(line, position);
                    } else if (line.toLowerCase().contains("full-time")) {
                        position = "Full-Time";
                        employees[count] = createAnEmployee(line, position);
                    } else if (line.toLowerCase().contains("station manager")) {
                        position = "Station Manager";
                        employees[count] = createAnEmployee(line, position);
                    }

                    count++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee[] createEmployees(String[] employeeStrings) {
        Employee[] employees = new Employee[employeeStrings.length - 1];
        for (int x = 0; x < employees.length; x++) {
            String name = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);

            String position = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);

            int shiftStart = Integer.parseInt(employeeStrings[x].substring(0, employeeStrings[x].indexOf("-")));

            if (shiftStart < 8) {
                shiftStart += 12;
            }
            int shiftEnd = Integer.parseInt(
                    employeeStrings[x].substring(employeeStrings[x].indexOf("-") + 1, employeeStrings[x].indexOf(".")));
            if (shiftEnd < 10) {
                shiftEnd += 12;
            }

            employees[x] = new Employee(name, position, shiftStart, shiftEnd);
        }
        return employees;
    }

    public Employee[] createEmployees2(String schedule) {
        String[] employeeStrings = createArray(schedule);
        Employee[] employees = new Employee[employeeStrings.length];
        for (int x = 0; x < employees.length; x++) {
            String name = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);

            String position = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);

            int shiftStart = Integer.parseInt(employeeStrings[x].substring(0, employeeStrings[x].indexOf("-")));

            if (shiftStart < 8) {
                shiftStart += 12;
            }
            int shiftEnd = Integer.parseInt(
                    employeeStrings[x].substring(employeeStrings[x].indexOf("-") + 1, employeeStrings[x].indexOf(".")));
            if (shiftEnd < 10) {
                shiftEnd += 12;
            }

            employees[x] = new Employee(name, position, shiftStart, shiftEnd);
        }
        return employees;
    }

    public String readInRotations(int linesInput) throws IOException {
        String file = "src/main/resources/txt/rotations.txt";

        try (FileReader reader = new FileReader(file)) {
            JTextArea textArea = new JTextArea();
            textArea.read(reader, file);

            String rotations = textArea.getText();

            rotations = rotations.replaceAll("\t", "&#09;");
            rotations = rotations.replaceAll("\n", "<br>");
            rotations = rotations.replaceAll(" ", "&nbsp;");

            return rotations;
        }
    }

}
