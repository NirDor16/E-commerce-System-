package InbarSarIsrael323101485NirDor212779243;

import java.util.Arrays;

public class Buyer extends User implements Comparable<Buyer> {
    private Address address;
    private Order orderCart;
    private Order[] ordersHistory;
    private int numsOfHistory = 0;

    public Buyer(String name, String password, Address address) {
        super(name, password);
        this.address = new Address(address);
        this.ordersHistory = new Order[1];
        this.orderCart = new Order();
    }

    public Buyer(Buyer other) throws CloneNotSupportedException {
        super(other);
        this.address = new Address(other.address);
        this.orderCart = new Order(other.orderCart);
        this.numsOfHistory = other.numsOfHistory;
        this.ordersHistory = new Order[other.ordersHistory.length];
        for (int i = 0; i < other.numsOfHistory; i++) {
            this.ordersHistory[i] = new Order(other.ordersHistory[i]);
        }
    }

    public boolean setName(String name) {
        super.setName(name);
        return true;
    }

    public boolean setAddress(Address address) {
        this.address = new Address(address);
        return true;
    }

    public Address getAddress() {
        return this.address;
    }

    public Order getOrderCart() {
        return orderCart;
    }

    public Order[] getOrdersHistory() {
        return ordersHistory;
    }

    public int getNumsOfHistory() {
        return numsOfHistory;
    }

    public void checkOut() throws CloneNotSupportedException {
        if (this.numsOfHistory == this.ordersHistory.length) {
            this.ordersHistory = Arrays.copyOf(this.ordersHistory, this.ordersHistory.length * 2);
        }
        this.ordersHistory[this.numsOfHistory++] = new Order(orderCart);
        this.orderCart = new Order();
        orderCart.setNumsOfProducts(0);
    }

    public void updateOrder(Order changeOrder) throws CloneNotSupportedException {
        this.orderCart = new Order(changeOrder);
    }

    public String toStringOrderHistory() {
        String printHistory = "\nThe orders history is: ";
        if (this.numsOfHistory == 0) {
            return "\nThere is no order history yet";
        }
        for (int i = 0; i < numsOfHistory; i++) {
            printHistory += "\n\norder " + (i + 1) + " is: " + this.ordersHistory[i];
        }
        return printHistory;
    }

    @Override
    public String toString() {
        String buyerDetails = super.toString() + "\n" + this.address;
        buyerDetails += "\n" + this.orderCart.toStringProducts();
        buyerDetails += "\n" + toStringOrderHistory();
        return buyerDetails;
    }

    @Override
    public int compareTo(Buyer o) {
        return getName().compareTo(o.getName());
    }
}