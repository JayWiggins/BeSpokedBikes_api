package bespokeBikes.sales.exception;

public class MissingSalesException extends Exception {
    public MissingSalesException(String errorMessage) {
        super(errorMessage);
    }
}
