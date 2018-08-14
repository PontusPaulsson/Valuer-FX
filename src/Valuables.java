public class Valuables {
    String name;
    double worth;

    public  Valuables(){

    }
    public Valuables(String name, double worth) {
        this.name = name;
        this.worth = worth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }
}
