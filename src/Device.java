//Worth should be calcul
public class Device extends Valuables {
    private double priceWhenBought;
    private int condition; // 1-10 where 10 is new condition

    public Device(String name, double priceWhenBought, int condition) {
        this.priceWhenBought = priceWhenBought;
        this.condition = condition;
        this.setName(name);
        calculateWorth();
    }

    public double getPriceWhenBought() {
        return priceWhenBought;
    }

    public void setPriceWhenBought(double priceWhenBought) {
        this.priceWhenBought = priceWhenBought;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void calculateWorth(){
        worth = priceWhenBought * (condition * 0.1);
    }
}
