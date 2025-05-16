package InbarSarIsrael323101485NirDor212779243.exception;

public class NoSellerProductsException extends BuyerException {
    public NoSellerProductsException() {
        super("There are no product associated to this seller.");
    }
}
