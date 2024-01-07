package bespokeBikes.products;

import bespokeBikes.products.exceptions.MissingProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductsEntity> getProducts() throws MissingProductException {
        log.info("Getting list of products.");
        List<ProductsEntity> productsEntities = productRepository.getAllBy();
        return productsEntities;
    }

    public Optional<ProductsEntity> findProductById(String id) throws MissingProductException {
        Optional<ProductsEntity> productsEntity = productRepository.findById(id);
        return productsEntity;
    }

    @Transactional
    public ProductsEntity updateProduct(ProductsEntity requestBody) throws MissingProductException {
        ProductsEntity product =  ProductsEntity.builder()
                .manufacturer(requestBody.getManufacturer())
                .style(requestBody.getStyle())
                .salePrice(requestBody.getSalePrice())
                .commissionPercentage(requestBody.getCommissionPercentage())
                .purchasePrice(requestBody.getPurchasePrice())
                .qtyOnHand(requestBody.getQtyOnHand())
                .name(requestBody.getName())
                .id(requestBody.getId())
                .build();

        log.info("Saving product update");
        return productRepository.save(product);
    }
}
