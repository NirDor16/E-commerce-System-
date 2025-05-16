package InbarSarIsrael323101485NirDor212779243;

public class PackageProduct extends Product {
    private double specialPackaging;

    public PackageProduct(String name, double price, eCategory category, double specialPackaging) {
        super(name,price,category);
        this.specialPackaging = specialPackaging;
    }

    public PackageProduct(PackageProduct other) {
        super(other);
        this.specialPackaging =other.specialPackaging;
    }

    public double getSpecialPackaging() {
        return this.specialPackaging;
    }

    public void setSpecialPackaging(double specialPackaging) {
        this.specialPackaging = specialPackaging;
    }

    public double getTotalPrice(){
        return super.getPrice() + this.specialPackaging;
    }

    @Override
    public PackageProduct clone() throws CloneNotSupportedException {
        return (PackageProduct) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + " packaging: " + this.specialPackaging + " Total Price is: " + getTotalPrice();
    }
}
