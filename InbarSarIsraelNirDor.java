package InbarSarIsrael323101485NirDor212779243;
// Inbar and Nir project
// Inbar Sar Israel: 323101485, Nir Dor: 212779243
// Lecturer's name: Keren

import InbarSarIsrael323101485NirDor212779243.exception.*;
import static InbarSarIsrael323101485NirDor212779243.UserInputUtils.*;

import java.util.Scanner;

public class InbarSarIsraelNirDor {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        Manager manager = new Manager();
        String choiceNum;

        do {
            printMenu();
            choiceNum = s.nextLine();

            handleChoice(manager, choiceNum);
            System.out.println();
        } while (!choiceNum.equals("0"));
        s.close();
    }

    private static void printMenu() {
        System.out.println("""
                Hello, Please choose a number from the menu:
                0- Exit
                1- Add Seller
                2- Add Buyer
                3- Add Product for a seller
                4- Add Product for a buyer
                5- Order payment for a buyer
                6- Displaying the details of all buyers
                7- Displaying the details of all sellers
                8- Displaying all the products from the same category
                9- Change the current cart for a buyer""");
    }

    private static void handleChoice(Manager manager, String choiceNum) {
        try {
            switch (choiceNum) {
                case "0": {
                    System.out.println("Exit");
                    break;
                }
                case "1": {
                    addSeller(manager);
                    break;
                }
                case "2": {
                    addBuyer(manager);
                    break;
                }
                case "3": {
                    addProductSeller(manager);
                    break;
                }
                case "4": {
                    addProductBuyer(manager);
                    break;
                }
                case "5": {
                    orderPayment(manager);
                    break;
                }
                case "6": {
                    System.out.println(manager.toStringBuyersDetails());
                    break;
                }
                case "7": {
                    System.out.println(manager.toStringSellersDetails());
                    break;
                }
                case "8": {
                    Product.eCategory category = getCategoryFromUser(manager);
                    System.out.println(manager.toStringAllProducts(category));
                    break;
                }
                case "9": {
                    changeCart(manager);
                    break;
                }
                default: {
                    System.out.println("Invalid number!");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addSeller(Manager manager) throws CloneNotSupportedException {
        System.out.println("Please enter the new seller's username:");
        String newSellerName = getString();

        while (manager.checkSellerExist(newSellerName) != null) {
            System.out.println("Username already exists!\nPlease enter another username:");
            newSellerName = s.nextLine();
        }
        System.out.println("Please enter the new seller's password:");
        String password = getString();

        manager.addSeller(new Seller(newSellerName, password));
    }

    private static void addBuyer(Manager manager) throws CloneNotSupportedException {
        System.out.println("Please enter the new buyer's username:");
        String newBuyer = getString();
        while (manager.checkBuyerExist(newBuyer) != null) {
            System.out.println("Username already exists!\nPlease enter another username:");
            newBuyer = getString();
        }
        System.out.println("Please enter the new buyer's password:");
        String password = getString();
        System.out.println("Please enter the new buyer's address:\ncountry:");
        String country = getString();
        System.out.println("city:");
        String city = getString();
        System.out.println("name street:");
        String nameStreet = getString();
        System.out.println("number of building:");
        int numBuilding = getPositiveInt();
        Address address = new Address(country, city, nameStreet, numBuilding);
        manager.addBuyer(new Buyer(newBuyer, password, address));
    }

    private static void addProductSeller(Manager manager) throws NoSellersException, CloneNotSupportedException {
        validateNotEmptySellers(manager);
        System.out.println(manager.toStringSellersNames());

        Seller currSeller = getSellerFromUser(manager);
        if (currSeller == null) {
            return;
        }
        String productName = getNameProductFromUser(currSeller);
        Product.eCategory category = getCategoryFromUser(manager);

        System.out.println("Please enter the price of the item:");
        double price = getPositiveDouble();

        System.out.println("Do you need this product in a spacial packing? (1 -> yes, 0 -> no) ");
        String packing = getAnswerFromTheUser();

        double pricePac;
        Product product;
        if (packing.equals("1")) {
            System.out.println("How much the package cost? ");
            pricePac = getPositiveDouble();
            product = new PackageProduct(productName, price, category, pricePac);
            currSeller.addProduct(product);
        } else {
            product = new Product(productName, price, category);
            currSeller.addProduct(product);
        }
        System.out.println("seller " + currSeller.getName() + " Added item: " + product);
    }

    private static void addProductBuyer(Manager manager) throws Exception{
        validateNotEmptyBuyers(manager);
        validateNotEmptySellers(manager);

        System.out.println(manager.toStringBuyersNames());
        Buyer currBuyer = getBuyerFromUser(manager);

        if (currBuyer == null) {
            return;
        }
        System.out.println(manager.toStringSellersNames());

        Seller currSeller = getSellerFromUser(manager);
        if (currSeller == null) {
            return;
        }
        if (currSeller.getProducts()[0] == null) {
            throw new NoSellerProductsException();
        }
        System.out.println(currSeller.toStringProducts());

        Product currProduct = null;
        while (currProduct == null) {
            System.out.println("Please choose a product from the list");
            String nameProduct = getString();
            currProduct = currSeller.checkProductExist(nameProduct);
        }
        currBuyer.getOrderCart().addProduct(currProduct);
        System.out.println("Buyer " + currBuyer.getName() + " wants to buy from seller " + currSeller.getName() + " a: " + currProduct);
    }

    private static void orderPayment(Manager manager) throws Exception {
        validateNotEmptyBuyers(manager);

        System.out.println(manager.toStringBuyersNames());

        System.out.println("Check out!");
        Buyer currBuyer = getBuyerFromUser(manager);

        if (currBuyer == null) {
            return;
        }

        if (currBuyer.getOrderCart().getNumsOfProducts() == 0) {
            throw new NoBuyerProductsException();
        }

        System.out.println("The total price is: " + currBuyer.getOrderCart().totalPrice());
        currBuyer.checkOut();
        System.out.println("Payment processed for buyer " + currBuyer.getName());
    }

    private static Product.eCategory getCategoryFromUser(Manager manager) throws NoSellersException {
        validateNotEmptySellers(manager);
        System.out.println(manager.toStringCategory());
        int categoryNum = validRangeNum(4);
        String category = manager.choseCategory(categoryNum);
        return Product.eCategory.valueOf(category);
    }

    private static void changeCart(Manager manager) throws Exception {
        validateNotEmptyBuyers(manager);

        System.out.println(manager.toStringBuyersNames());
        Buyer currBuyer = getBuyerFromUser(manager);
        if (currBuyer == null) {
            return;
        }
        validateNotEmptyHistoryCrat(currBuyer);
        if (currBuyer.getOrderCart().getNumsOfProducts() != 0) {
            System.out.println("Do ypu want to replace your current cart? (1 -> yes, 0 -> no)");
            String answer = getAnswerFromTheUser();
            if (answer.equals("0")) {
                return;
            }
        }
        System.out.println(currBuyer.toStringOrderHistory());
        int numOfHistoryCart = validRangeNum(currBuyer.getNumsOfHistory());
        Order orderHistory = currBuyer.getOrdersHistory()[--numOfHistoryCart];
        currBuyer.updateOrder(orderHistory);
    }

    private static String getAnswerFromTheUser() {
        String answer = getString();
        while (!answer.equals("0") && !answer.equals("1")) {
            System.out.println("Invalid number please enter again (1 -> yes, 0 -> no) ");
            answer = getString();
        }
        return answer;
    }

    private static Seller getSellerFromUser(Manager manager) {
        Seller foundSeller;
        do {
            System.out.println("Please enter seller name OR 0 to exit:");
            String sellerName = getString();
            if (sellerName.equals("0")) {
                System.out.println("Seller not found, continue to the menu.");
                return null;
            }
            foundSeller = manager.checkSellerExist(sellerName);

            if (foundSeller == null) {
                System.out.println("The name doesn't exist.");
            }
        } while (foundSeller == null);

        return foundSeller;
    }

    private static Buyer getBuyerFromUser(Manager manager) {
        Buyer foundBuyer;
        do {
            System.out.println("Please enter buyer name OR 0 to exit:");
            String buyerName = getString();
            if (buyerName.equals("0")) {
                System.out.println("Buyer not found, continue to the menu.");
                return null;
            }
            foundBuyer = manager.checkBuyerExist(buyerName);

            if (foundBuyer == null) {
                System.out.println("The name doesn't exist.");
            }
        } while (foundBuyer == null);

        return foundBuyer;
    }

     private static String getNameProductFromUser(Seller seller) {
         System.out.println("Please enter the name of the item:");
         boolean isChosen;
         String productName;
         do {
             isChosen = false;
             productName = getString();
             for (int i = 0; i < seller.getNumsOfProducts(); i++) {
                 if (seller.getProducts()[i].getName().equals(productName)) {
                     isChosen = true;
                     break;
                 }
             }
             if (isChosen) System.out.println("This name is chosen, please select another name:");
         } while (isChosen);
         return productName;
     }

    private static void validateNotEmptyBuyers(Manager manager) throws NoBuyersException {
        if (manager.buyers.getSize() == 0) {
            throw new NoBuyersException();
        }
    }

    private static void validateNotEmptyHistoryCrat(Buyer buyer) throws NoBuyerHistoryCratException {
        if (buyer.getNumsOfHistory() == 0) {
            throw new NoBuyerHistoryCratException();
        }
    }

    private static void validateNotEmptySellers(Manager manager) throws NoSellersException {
        if (manager.sellers.getSize() == 0) {
            throw new NoSellersException();
        }
    }

    private static int validRangeNum(int end) {
        int selectionNum;
        while (true) {
            System.out.println("Please enter your selection number:");
            String strCategoryNum = getString();
            try {
                selectionNum = Integer.parseInt(strCategoryNum);
                if (0 < selectionNum && selectionNum <= end) {
                    break;
                }
            } catch (Exception _) {
            }
            System.out.println("Invalid input");
        }
        return selectionNum;
    }
}