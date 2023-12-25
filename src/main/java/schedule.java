public class schedule {
    private int startOfDay;
    private int endOfDay;
    private int numberOfLines;
    private int numberOfOrderTakers;
    private int numberOfCashiers;
    public schedule (int startOfDay, int endOfDay, int numberOfLines) {
        this.startOfDay = startOfDay;
        this.endOfDay = endOfDay;
        this.numberOfLines = numberOfLines;
        numberOfOrderTakers = numberOfLines;
        numberOfCashiers = numberOfLines;
    }
    public int getStart() {
        return startOfDay;
    }
    public int getEnd() {
        return endOfDay;
    }
    public int getNumOrderTakers() {
        return numberOfOrderTakers;
    }
    public int getNumCashiers() {
        return numberOfCashiers;
    }
    public int getNumberOfLines() {
        return numberOfLines;
    }
    public int getScheduleLength() {
        return endOfDay-startOfDay;
    }

    public void changeNumRotations(int cashiers, int orderTakers) {
        numberOfCashiers = cashiers;
        numberOfOrderTakers = orderTakers;
    }


}
