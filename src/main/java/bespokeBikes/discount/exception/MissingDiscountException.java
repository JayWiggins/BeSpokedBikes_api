package bespokeBikes.discount.exception;

public class MissingDiscountException extends Exception {
    public MissingDiscountException(String errorMessage) {
        super(errorMessage);
    }
}
