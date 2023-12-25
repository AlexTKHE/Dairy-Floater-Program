public class currentPositions {
    private String[] cashier;
    private String[] orderTaker;
    private int numberOfCashiers = 0;
    private int numberOfOrderTakers = 0;
    
    public currentPositions(int numOfCashiers, int numOfOrderTakers) {
        cashier = new String[numOfCashiers];
        orderTaker = new String[numOfOrderTakers];
        for (int x = 0; x < numOfCashiers; x++) {
            cashier[x] = "did not initilize -- C";
        }
        for (int x = 0; x < numOfOrderTakers; x++) {
            orderTaker[x] = "did not initilize -- OT";
        }
        numberOfCashiers = numOfCashiers;
        numberOfOrderTakers = numOfOrderTakers;

    }
    public String getCashier(int indexOfCashier) {
        return cashier[indexOfCashier];
    }

    public String getCashier() {
        String allCashiers = "";
        for (int x = 0; x < numberOfCashiers; x++) {
            allCashiers += cashier[x] + " ";
        }
        return allCashiers;
    }

    public String getOrderTaker(int indexOfOrderTaker) {
        return orderTaker[indexOfOrderTaker];
    }

    public String getOrderTaker() {
        String allOrderTakers = "";
        for (int x = 0; x < numberOfOrderTakers; x++) {
            allOrderTakers += orderTaker[x] + " ";
        }
        return allOrderTakers;
    }

    public void changeCashier(String onCashier, int indexOfCashier) {
        cashier[indexOfCashier] = onCashier;
    }

    public void changeOrderTaker(String onOrderTaker, int indexOfOrderTaker) {
        orderTaker[indexOfOrderTaker] = onOrderTaker;
    }

    public boolean hasCashier(int indexOfCashier) {
        if (!cashier[indexOfCashier].equals("did not initilize -- C") && cashier[indexOfCashier].length() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasOrderTaker(int indexOfOrderTaker) {
        if (!orderTaker[indexOfOrderTaker].equals("did not initilize -- OT") && orderTaker[indexOfOrderTaker].length() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getNumCashiers() {
        return cashier.length;
    }

    public int getNumOrderTakers() {
        return orderTaker.length;
    }
}

