package bespokeBikes.sales;

import bespokeBikes.common.SalesComissions;
import bespokeBikes.customer.CustomerRepository;
import bespokeBikes.products.ProductRepository;
import bespokeBikes.products.exceptions.MissingProductException;
import bespokeBikes.sales.exception.MissingSalesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<SalesEntity> getSales() throws MissingSalesException {
        log.info("Getting list of sales.");
        List<SalesEntity> salesEntities = salesRepository.getAllBy();
        return salesEntities;
    }

    public List<SalesEntity> getSalesFilteredByDateString(String filterDate) {
        log.info("Getting sales for '{}' ",filterDate);
        List<SalesEntity> data = salesRepository.getSalesEntitiesBySalesDate(filterDate);
        return data;

    }

    public List<SalesComissions> getSalesFilteredByDateRange(String startDate, String endDate) {
        log.info("Getting sales between '{}' and '{}' with commissions. ",startDate, endDate);

        List<SalesEntity> salesEntities = salesRepository.locateSalesInDateRange(startDate,endDate);
        List<SalesComissions> processedData = salesEntities.stream()
                .map(salesEntity -> {
                    SalesComissions sc =  new SalesComissions();
                    sc.setProduct(salesEntity.getProduct());
                    sc.setCustomer(salesEntity.getCustomer());
                    sc.setSalesPerson(salesEntity.getSalesPerson());
                    sc.setDate(salesEntity.getSalesDate());

                    String price = salesEntity.getProduct().getSalePrice();
                    String commission = salesEntity.getProduct().getCommissionPercentage();

                    Double priceValue = Double.parseDouble(price);
                    Double commissionValue = Double.parseDouble(commission);

                    //Calculate commission
                    Double calculatedValue = priceValue * (commissionValue/100);
                    sc.setPrice(price);
                    sc.setCommission(String.format("%.2f", calculatedValue));
                    return sc;
                })
                .toList();

        return processedData;
    }

    public List<SalesComissions> getCommissionReport(String startDate, String endDate, String salesPersonId) {
        log.info("Getting sales between '{}' and '{}' with commissions. ",startDate, endDate);

        List<SalesEntity> salesEntities = salesRepository.commissionReport(startDate,endDate, salesPersonId);
        List<SalesComissions> processedData = salesEntities.stream()
                .map(salesEntity -> {
                    SalesComissions sc =  new SalesComissions();
                    sc.setSalesPerson(salesEntity.getSalesPerson());
                    sc.setDate(salesEntity.getSalesDate());

                    String price = salesEntity.getProduct().getSalePrice();
                    String commission = salesEntity.getProduct().getCommissionPercentage();

                    Double priceValue = Double.parseDouble(price);
                    Double commissionValue = Double.parseDouble(commission);

                    //Calculate commission
                    Double calculatedValue = priceValue * (commissionValue/100);
                    sc.setPrice(price);
                    sc.setCommission(String.format("%.2f", calculatedValue));
                    return sc;
                })
                .filter(sc -> sc != null && sc.getPrice() != null && sc.getCommission() != null) // Example condition
                .toList();

        return processedData;
    }

    @Transactional
    public SalesEntity saveSaleEntity(SalesEntity requestBody) throws MissingProductException {
        log.info("Saving new sale.");
        return salesRepository.save(requestBody);
    }
}
