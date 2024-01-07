package bespokeBikes.customer.exception;

public class MissingCustomerEntity extends Exception {
    public MissingCustomerEntity(String errorMessage) {
        super(errorMessage);
    }
}
