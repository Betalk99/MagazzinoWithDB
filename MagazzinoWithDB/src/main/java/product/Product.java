package product;

public class Product {
    private int id;
    private ProductTypes type;
    private String model;
    private String brand;
    private String description;
    private double displaySize;
    private double storageCap;
    private double purchasePrice;
    private double sellPrice;


    public Product(ProductTypes type, String model, String brand, String description, double displaySize, double storageCap, double purchasePrice, double sellPrice, int id) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.brand = brand;
        this.description = description;
        this.displaySize = displaySize;
        this.storageCap = storageCap;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductTypes getType() {
        return type;
    }

    public void setType(ProductTypes type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    public double getStorageCap() {
        return storageCap;
    }

    public void setStorageCap(double storageCap) {
        this.storageCap = storageCap;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", displaySize=" + displaySize +
                ", storageCap=" + storageCap +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
