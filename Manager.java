package InbarSarIsrael323101485NirDor212779243;

import java.util.Arrays;

public class Manager {

    UsersList buyers = new UsersList();
    UsersList sellers = new UsersList();

    public void addBuyer(Buyer buyer) throws CloneNotSupportedException {
        this.buyers.addUser(new Buyer(buyer));
    }

    public void addSeller(Seller seller) throws CloneNotSupportedException {
        this.sellers.addUser(new Seller(seller));
    }

    public Seller checkSellerExist(String name) {
        return (Seller) this.sellers.checkUserExist(name);
    }

    public Buyer checkBuyerExist(String name) {
        return (Buyer) this.buyers.checkUserExist(name);
    }

    public String choseCategory(int num) {
        Product.eCategory[] allCategory = Product.eCategory.values();
        return allCategory[--num].name();
    }

    public String toStringAllProducts(Product.eCategory category) throws Exception {
        String products = "the list of the product:";
        boolean findProduct = false;
        for (int i = 0; i < this.sellers.getSize(); i++) {
            Seller seller = (Seller) this.sellers.get(i);
            for (int j = 0; j < seller.getNumsOfProducts(); j++) {
                if (seller.getProducts()[j].getTheCategory() == category) {
                    products += "\n Seller name: " + seller.getName() + ", " + seller.getProducts()[j];
                    findProduct = true;
                }
            }
        }
        if (!findProduct) {
            return "There aren't products from this category";
        }
        return products;
    }

    public String toStringCategory() {
        Product.eCategory[] allCategory = Product.eCategory.values();
        String category = "The list of the category:\n";
        for (int i = 0; i < allCategory.length; i++) {
            category += i + 1 + ". " + allCategory[i].name() + "\n";
        }
        return category;
    }

    public String toStringSellersDetails() {
        Arrays.sort(this.sellers.getUsers(), 0, this.sellers.getSize());
        return sellers.toString();
    }

    public String toStringBuyersDetails() {
        Arrays.sort(this.buyers.getUsers(), 0, this.buyers.getSize());
        return this.buyers.toString();
    }

    public String toStringBuyersNames() {
        return this.buyers.getUserNames();
    }

    public String toStringSellersNames() {
        return this.sellers.getUserNames();
    }
}
