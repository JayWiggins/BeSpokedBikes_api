package bespokeBikes.rest;

import bespokeBikes.products.exceptions.MissingProductException;
import bespokeBikes.sales.SalesEntity;
import bespokeBikes.sales.SalesService;
import bespokeBikes.sales.exception.MissingSalesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
public class SalesController {
    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/getSales")
    public ResponseEntity getSales() throws MissingSalesException {
        return ResponseEntity.ok(salesService.getSales());
    }

    @GetMapping(value = "/getSales", params = "filterDate")
    public ResponseEntity getFilteredSales(@RequestParam("filterDate") String filterDate) throws MissingSalesException {
        return ResponseEntity.ok(salesService.getSalesFilteredByDateString(filterDate));
    }

    @GetMapping(value = "/getSales", params = {"startDate", "endDate"})
    public ResponseEntity getFilteredSales(@RequestParam("startDate") String startDate,
                                                @RequestParam("endDate") String endDate) throws MissingSalesException {

        return ResponseEntity.ok(salesService.getSalesFilteredByDateRange(startDate, endDate));
    }

    @GetMapping(value = "/getSalesReport", params = {"startDate", "endDate", "salesPersonId"})
    public ResponseEntity getSaleReport(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate,
                                        @RequestParam("salesPersonId") String salesPersonId) throws MissingSalesException {

        return ResponseEntity.ok(salesService.getCommissionReport(startDate, endDate, salesPersonId));
    }

    @PostMapping("/createSale")
    public ResponseEntity createSale(@RequestBody SalesEntity requestBody) throws MissingProductException {
        log.info("Creating a new sale.");
        salesService.saveSaleEntity(requestBody);
        return ResponseEntity.ok(requestBody);
    }


}
