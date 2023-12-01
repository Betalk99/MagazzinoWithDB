package product;

public class Smartphone extends Product{
    public Smartphone(int id, ProductTypes type, String model, String brand, String description, double displaySize, double storageCap, double purchasePrice, double sellPrice) {
        super(type, model, brand, description, displaySize, storageCap, purchasePrice, sellPrice,id);
    }
}
