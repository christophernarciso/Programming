package util.json.util.exchange;

/**
 * Created by Ethan on 1/8/2018.
 */
public class ExchangeItem {
    public String name;
    public int sellAverage;
    public int overallAverage;
    public int buyAverage;
    public int id;

    public ExchangeItem(String name, int id, int sellAverage, int overallAverage, int buyAverage) {
        this.name = name;
        this.id = id;
        this.sellAverage = sellAverage;
        this.overallAverage = overallAverage;
        this.buyAverage = buyAverage;
    }

    public String getName() {
        return name;
    }

    public int getSellAverage() {
        return sellAverage;
    }

    public int getOverallAverage() {
        return overallAverage;
    }

    public int getBuyAverage() {
        return buyAverage;
    }

    public int getId() {
        return id;
    }
}
