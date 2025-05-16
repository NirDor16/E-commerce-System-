package InbarSarIsrael323101485NirDor212779243;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Order {
    private Product[] products;
    private int numsOfProducts = 0;
    private LocalDateTime date;

    public Order() {
        this.products = new Product[1];
    }

    public Order(Order other) throws CloneNotSupportedException {
        this.date = LocalDateTime.now();
        dupProducts(other.getProducts(), other.numsOfProducts);
    }

    public Product[] getProducts() {
        return this.products;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.date.format(formatter);
    }

    public void setNumsOfProducts(int numsOfProducts) {
        this.numsOfProducts = numsOfProducts;
    }

    public int getNumsOfProducts() {
        return numsOfProducts;
    }

    public void addProduct(Product product) throws CloneNotSupportedException {
        if (this.numsOfProducts == this.products.length) {
            this.products = Arrays.copyOf(this.products, this.products.length * 2);
        }
        this.products[this.numsOfProducts] = product.clone();
        this.numsOfProducts++;
    }

    public double totalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < this.numsOfProducts; i++) {
            Product currProduct = this.products[i];

            if (currProduct instanceof PackageProduct) {
                totalPrice += ((PackageProduct) currProduct).getTotalPrice();
            } else {
                totalPrice += currProduct.getPrice();
            }
        }
        return totalPrice;
    }

    public void dupProducts(Product[] products, int numsOfProducts) throws CloneNotSupportedException {
        this.products = new Product[products.length];
        this.numsOfProducts = numsOfProducts;
        for (int i = 0; i < numsOfProducts; i++) {
            this.products[i] = products[i].clone();
        }
    }

    public String toStringProducts() {
        String listOfProducts = "";
        if (this.numsOfProducts == 0) {
            return "\nThere are no products yet";
        }
        listOfProducts += "\nThe list of the products is: ";
        for (int i = 0; i < this.numsOfProducts; i++) {
            listOfProducts += "\n  " + (i + 1) + "." + this.products[i].toString();
        }
        return listOfProducts;
    }

    @Override
    public String toString() {
        return getDate() + toStringProducts();
    }
}







