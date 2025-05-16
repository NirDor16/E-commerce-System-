package InbarSarIsrael323101485NirDor212779243;

import java.util.Arrays;

public class Seller extends User implements Comparable<Seller>{
    private Product[] products;
    private int numsOfProducts = 0;

    public Seller(String name, String password) {
        super(name, password);
        this.products = new Product[1];
    }

    public Seller(Seller other) throws CloneNotSupportedException {
        super(other);
        this.numsOfProducts = other.numsOfProducts;
        this.products = new Product[other.products.length];
        for (int i = 0; i < this.numsOfProducts; i++) {
            this.products[i] = other.products[i].clone();
        }
    }

    public boolean setName(String name) {
        super.setName(name);
        return true;
    }

    public int getNumsOfProducts() {
            return this.numsOfProducts;
    }

    public Product[] getProducts() {
        return this.products;
    }

    public void addProduct(Product product) throws CloneNotSupportedException {
        if (this.numsOfProducts == this.products.length) {
            this.products = Arrays.copyOf(this.products, this.products.length * 2);
        }
        this.products[this.numsOfProducts] = product.clone();
        this.numsOfProducts++;
    }

    public Product checkProductExist(String name) {
        for (int i = 0; i < this.numsOfProducts; i++) {
            if (name.equals(this.products[i].getName())) {
                return this.products[i];
            }
        }
        return null;
    }

    public String toStringProducts() {
        String listOfProducts = "";
        if (this.numsOfProducts == 0) {
            return "\n\nThere is no products available";
        }
        listOfProducts += "\n\nThe list of the products is:";
        for (int i = 0; i < this.numsOfProducts; i++) {
            listOfProducts += "\n" + (i + 1) + "." + this.products[i].toString();
        }
        return listOfProducts;
    }

    @Override
    public String toString() {
        return super.toString() + toStringProducts();
    }

    @Override
    public int compareTo(Seller o) {
        return Integer.compare(this.numsOfProducts, o.getNumsOfProducts());
    }
}