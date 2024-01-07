package bespokeBikes.products.exceptions;

public class MissingProductException extends Exception {
    public MissingProductException(String errorMessage) {
        super(errorMessage);
    }
}
