package bespokeBikes.rest;

import bespokeBikes.products.ProductsEntity;
import bespokeBikes.products.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import bespokeBikes.products.exceptions.MissingProductException;

@Slf4j
@RestController
public class ProductController {

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity getProducts() throws MissingProductException {
        return ResponseEntity.ok(productsService.getProducts());
    }

    //Could have used to restrict access, @Preauthorize("hasRole('USER')")
    @PutMapping("/updateProducts")
    public ResponseEntity updateProducts(@RequestBody ProductsEntity requestBody) throws MissingProductException {
        log.info("Updating product.");
        Optional<ProductsEntity> found = productsService.findProductById(requestBody.getId());
        if(found.isEmpty()) {
            log.info("This product is not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found.");
        }
        productsService.updateProduct(requestBody);
        return ResponseEntity.ok(requestBody);
    }

}