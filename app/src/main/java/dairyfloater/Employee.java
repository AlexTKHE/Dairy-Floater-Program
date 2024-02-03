package dairyfloater;

public class Employee {
    private String employeeName = "";
    private String employeePosition = "";
    private int employeeShiftLength = 0;
    private int employeeShiftStart = 0;
    private int employeeShiftEnd = 0;
    private int shouldFloatTrack = 0;
    private boolean canFloat = true;
    
        public Employee(String name, String position, int shiftStart, int shiftEnd) {
        employeeName = name;
        employeePosition = position;
        employeeShiftLength = shiftEnd-shiftStart;
        employeeShiftStart = shiftStart;
        employeeShiftEnd = shiftEnd;
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
        else if (employeePosition.toLowerCase().equals("rotation floater") || employeePosition.toLowerCase().equals("senior rotation")) {
            return 1;
        }
        else if (employeePosition.toLowerCase().equals("station manager")) {
            return 2;
        }
        else {
            return 3;
        }
    }

    public boolean getFloat(int time, CurrentPositions[] arraypos) {
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

    public boolean shouldFloat() {
        if(shouldFloatTrack == 0) {
            return true;
        }
        return false;
    }


    public boolean canFloat() {
        return canFloat;
    }

    public void changeFloat(boolean t) {
        canFloat = t;

        if (!t) {
            shouldFloatTrack = 2;
        }

        if(t && shouldFloatTrack != 0) {
            shouldFloatTrack--;
        }
    }
    

    public boolean isOnRotations(Schedule may30, int i, CurrentPositions[] positions) {
        for (int p = 0; p < may30.getNumCashiers(); p++) {
            if (positions[i-may30.getStart()].getCashier(p).equals(getName())) {
                return true;
            }
        }
        for (int p = 0; p < may30.getNumOrderTakers(); p++) {
            if (positions[i-may30.getStart()].getOrderTaker(p).equals(getName())) {
                return true;
            }
        }
        return false;
    }


}
