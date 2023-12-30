package dairyfloater;

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
        while (remainSchedule.length() > 0) {
            employeeArray[x] = remainSchedule.substring(0, remainSchedule.indexOf("."));
            remainSchedule = remainSchedule.substring(remainSchedule.indexOf(".") + 1);

            x++;
        }
        return employeeArray;
    }

    public Employee[] creatEmployees(String schedule) {

        String[] employeeStrings = createArray(schedule);
        Employee[] employees = new Employee[employeeStrings.length];
        String name = "";
        String position = "";
        int shiftStart = 0;
        int shiftLength = 0;
        int shiftEnd = 0;
        for (int x = 0; x < employees.length; x++) {
            name = employeeStrings[x].substring(5, employeeStrings[x].indexOf(","));
            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);
            position = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));
            employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",") + 1);
            shiftStart = Integer.parseInt(employeeStrings[x].substring(0, employeeStrings[x].indexOf("-")));
            if (shiftStart < 8) {
                shiftStart += 12;
            }
            shiftEnd = Integer.parseInt(employeeStrings[x].substring(employeeStrings[x].indexOf("-") + 1));
            if (shiftEnd < 10) {
                shiftEnd += 12;
            }
            shiftLength = shiftEnd - shiftStart;

            employees[x] = new Employee(name, position, shiftStart, shiftLength);
        }

        return employees;
    }

    // public static void main(String[] args) {

    // String names = "Dulcinea, Rotation Floater, 2-9.\n" + //
    // "Renate, Rotation Floater, 2-9.\n" + //
    // "Tasha, Rotation Floater, 2-6.\n" + //
    // "Friederike, Order Filler, 2-9.\n" + //
    // "Maribel, Rotation Floater, 2-9.\n" + //
    // "Minetta, Rotation Floater, 6-9.\n" + //
    // "Agnola, Rotation Floater, 2-9.\n" + //
    // "Petra, Rotation Floater, 6-9.\n" + //
    // "Koo, Rotation Floater, 6-9.\n" + //
    // "Jojo, Order Filler, 8-2.\n" + //
    // "Mariejeanne, Rotation Floater, 8-2.\n" + //
    // "Bridgette, Rotation Floater, 11-4.\n" + //
    // "Lucie, Rotation Floater, 12-4.\n" + //
    // "Philippine, Rotation Floater, 12-4.\n" + //
    // "Dulce, Order Filler, 12-4.\n" + //
    // "Myrle, Station Manager, 12-4.\n" + //
    // "Elenore, Rotation Floater, 6-9.\n" + //
    // "Fenelia, Rotation Floater, 2-9.\n" + //
    // "Trisha, Rotation Floater, 2-9.\n" + //
    // "Libbi, Order Filler, 2-9.\n" + //
    // "Karoline, Station Manager, 2-9.\n" + //
    // "Doralia, Rotation Floater, 6-9.\n" + //
    // "Dorita, Rotation Floater, 2-9.\n" + //
    // "Konstanze, Rotation Floater, 2-6.\n" + //
    // "Marti, Rotation Floater, 2-9.\n" + //
    // "Cassi, Manager, 2-6.\n" + //
    // "Arabelle, Rotation Floater, 2-6.\n" + //
    // "Lauri, Rotation Floater, 6-9.\n" + //
    // "Tessie, Rotation Floater, 2-9.\n" + //
    // "Lorianne, Order Filler, 2-6.\n" + //
    // "Catie, Rotation Floater, 2-9.\n" + //
    // "Constantine, Manager, 2-9.\n" + //
    // "Stella, Rotation Floater, 2-6.\n" + //
    // "Kissee, Rotation Floater, 6-9.\n" + //
    // "Remy, Rotation Floater, 6-9.\n" + //
    // "Clarinda, Rotation Floater, 2-6.\n" + //
    // "Brigida, Rotation Floater, 2-9.\n" + //
    // "Stephannie, Rotation Floater, 6-9.\n" + //
    // "Kathryn, Rotation Floater, 6-9.\n" + //
    // "Sheilah, Rotation Floater, 2-9.\n" + //
    // "Catrina, Rotation Floater, 2-9.\n" + //
    // "Denyse, Rotation Floater, 2-6.\n" + //
    // "Martguerita, Rotation Floater, 2-9.\n" + //
    // "Collette, Order Filler, 6-9.\n" + //
    // "Shell, Manager, 6-9.";
    // ScanIn test = new ScanIn();
    // Employee[] jeremy = test.creatEmployees(names);
    // System.out.println(jeremy[0].getShiftEnd());

    // }
}
