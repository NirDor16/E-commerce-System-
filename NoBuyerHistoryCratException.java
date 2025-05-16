package InbarSarIsrael323101485NirDor212779243.exception;

public class NoBuyerHistoryCratException extends BuyerException {
    public NoBuyerHistoryCratException() {
        super("There are no history associated to this buyer.");
    }
}
