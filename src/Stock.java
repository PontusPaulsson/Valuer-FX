//Worth should be stockQuote * worth;
public class Stock extends Valuables {
    private String stockQuote;
    private int amount;
    private double price;

    public Stock(String name, String stockQuote, int amount, double price) {
        this.stockQuote = stockQuote;
        this.amount = amount;
        this.price = price;
        this.setName(name);
        calculateWorth();
    }

    public String getStockQuote() {
        return stockQuote;
    }

    public void setStockQuote(String stockQuote) {
        this.stockQuote = stockQuote;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void calculateWorth(){
        worth = price * amount;
    }
}
