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
        while(remainSchedule.contains(".")) {
            employeeArray[x] = remainSchedule.substring(0, remainSchedule.indexOf(".") + 1);
            remainSchedule = remainSchedule.substring(remainSchedule.indexOf(".")+1);
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

    public Employee[] createEmployees(String[] employeeStrings) {
        Employee[] employees = new Employee[employeeStrings.length - 1];
        for (int x = 0; x < employees.length; x++) {
        String name = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

        employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",")+1);

        String position = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

        employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",")+1);

        int shiftStart = Integer.parseInt(employeeStrings[x].substring(0, employeeStrings[x].indexOf("-")));
    
         if ( shiftStart < 8) {
             shiftStart += 12;
        }
        int shiftEnd = Integer.parseInt(employeeStrings[x].substring(employeeStrings[x].indexOf("-") + 1, employeeStrings[x].indexOf(".")));
        if(shiftEnd < 10) {
            shiftEnd += 12;
        }
        
        employees[x] = new Employee(name, position, shiftStart, shiftEnd);
    }
        return employees;
    }

    public Employee[] createEmployees2(String schedule) {
        String[] employeeStrings = createArray(schedule);
        Employee[] employees = new Employee[employeeStrings.length];
        for (int x = 0; x <employees.length; x++) {
        String name = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

        employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",")+1);

        String position = employeeStrings[x].substring(0, employeeStrings[x].indexOf(","));

        employeeStrings[x] = employeeStrings[x].substring(employeeStrings[x].indexOf(",")+1);

        int shiftStart = Integer.parseInt(employeeStrings[x].substring(0, employeeStrings[x].indexOf("-")));
    
         if ( shiftStart < 8) {
             shiftStart += 12;
        }
        int shiftEnd = Integer.parseInt(employeeStrings[x].substring(employeeStrings[x].indexOf("-") + 1, employeeStrings[x].indexOf(".")));
        if(shiftEnd < 10) {
            shiftEnd += 12;
        }
        
        employees[x] = new Employee(name, position, shiftStart, shiftEnd);
    }
        return  employees;
    } 


    
}
