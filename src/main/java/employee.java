public class employee {
    private String employeeName = "";
    private String employeePosition = "";
    private int employeeShiftLength = 0;
    private int employeeShiftStart = 0;
    private int employeeShiftEnd = 0;
    private boolean canFloat = true;
    
        public employee(String name, String position, int shiftStart, int shiftLength) {
        employeeName = name;
        employeePosition = position;
        employeeShiftLength = shiftLength;
        employeeShiftStart = shiftStart;
        employeeShiftEnd = shiftLength+shiftStart;
    }
    public String getName() {
        return employeeName;
    }
    public int getShiftLength() {
        return employeeShiftLength;
    }
    public int getShiftStart() {
        return employeeShiftStart;
    }
    public int getShiftEnd() {
        return employeeShiftStart+employeeShiftLength;
    }
    public boolean isOnShift(int n) {
        if ((employeeShiftStart <= n) && (n < employeeShiftEnd)) {
            return true;
        }
        else {
            return false;
        }
    }
    public int getPosition() {
        if (employeePosition.toLowerCase().equals("order filler")) {
            return 0;
        }
        else if (employeePosition.toLowerCase().equals("rotation floater")) {
            return 1;
        }
        else if (employeePosition.toLowerCase().equals("station manager")) {
            return 2;
        }
        else {
            return 3;
        }
    }

    public boolean getFloat(int time, currentPositions[] arraypos) {
        if (arraypos[time].getCashier().contains(employeeName) || arraypos[time].getOrderTaker().contains(employeeName)) {
            return false;
        }
        else {
            return true;
        }
    }


    public String getPositionName() {
        return employeePosition;
    }

    public boolean canFloat() {
        return canFloat;
    }

    public void changeFloat(boolean t) {
        canFloat = t;
    }



}
