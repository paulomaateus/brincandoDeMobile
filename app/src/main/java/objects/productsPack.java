package objects;

public class productsPack extends products  {
    private int units;

    public productsPack(String name, double buyPrice, int units) {
        super(name,  buyPrice);
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
