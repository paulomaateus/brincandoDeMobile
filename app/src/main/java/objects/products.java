package objects;

public class products implements Cloneable {
    protected String name;
    protected double buyPrice;
    protected int amount;
    protected double sellPrice;

    public products(String name, double buyPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.amount = 0;
        this.sellPrice = 1.3 * buyPrice;
    }

    public products(String name, double buyPrice, double sellPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.amount = 0;
        this.sellPrice = sellPrice;
    }

    public products(String name, double buyPrice, double sellPrice, int amout) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.amount = amount;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return this.name;
    }

    public double getBuyPrice() {
        return this.buyPrice;
    }

    public void updateAmount(int amount) {
        this.amount += amount;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getAmount() {
        return this.amount;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return this.name + " R$ " + this.sellPrice + " unidades = " + this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public products clone() {
        products p;
        try {
            p = (products) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }

}
