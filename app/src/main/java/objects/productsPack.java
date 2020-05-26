package objects;

public class productsPack extends products  {
    private int units;

    public productsPack(String name, double buyPrice, int units) {
        super(name,  buyPrice);
        this.units = units;
    }
    public productsPack(String name, double buyPrice, double sellPrice, int units) {
        super(name,  buyPrice, sellPrice);
        this.units = units;
    }
    public productsPack(String name, double buyPrice, double sellPrice,  int units, int amount) {
        super(name,  buyPrice, sellPrice, amount);
        this.units = units;
    }

    public int getAmountUnits() {
        return this.units;
    }
    @Override
    public String toString(){
        return this.name + " R$ " + this.sellPrice + " Pacotes = " + units;
    }
    public double getTotalPrice(){
        return this.amount * this.sellPrice;
    }
}
