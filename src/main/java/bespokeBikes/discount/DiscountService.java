package bespokeBikes.discount;

import bespokeBikes.discount.exception.MissingDiscountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<DiscountEntity> getDiscounts() throws MissingDiscountException {
        log.info("Getting list of discounts.");
        List<DiscountEntity> discountEntities = discountRepository.getAllBy();
        return discountEntities;
    }
}
