package InbarSarIsrael323101485NirDor212779243;

public class Product implements Cloneable {
    private String name;
    private double price;
    private final eCategory theCategory;
    private final int id;
    private static int counterId = 1000;

    public enum eCategory {Children, Electricity, Office, Clothing}

    public Product(String name, double price, eCategory category) {
        this.name = name;
        this.price = price;
        this.theCategory = category;
        this.id = ++counterId;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
        this.theCategory = other.theCategory;
        this.id = other.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public double getPrice() {
        return this.price;
    }

    public eCategory getTheCategory() {
        return theCategory;
    }

    public boolean setPrice(double price) {
        if (price > 0) {
            this.price = price;
            return true;
        }
        return false;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    @Override
    public String toString() {
        return "Product Name: " + this.name + ", Category: " + this.theCategory + ", Price: " + this.price;
    }
}