package InbarSarIsrael323101485NirDor212779243.exception;

public class NoBuyerProductsException extends BuyerException {
    public NoBuyerProductsException() {
        super("There are no product associated to this buyer.");
    }
}
