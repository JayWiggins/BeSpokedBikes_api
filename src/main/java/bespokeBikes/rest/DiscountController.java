package bespokeBikes.rest;

import bespokeBikes.discount.DiscountService;
import bespokeBikes.discount.exception.MissingDiscountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/getDiscounts")
    public ResponseEntity getDiscounts() throws MissingDiscountException {
        return ResponseEntity.ok(discountService.getDiscounts());
    }
}
