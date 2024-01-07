package bespokeBikes.rest;

import bespokeBikes.salesperson.SalesPersonEntity;
import bespokeBikes.salesperson.SalesPersonService;
import bespokeBikes.salesperson.exception.MissingSalesPersonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
public class SalesPersonController {
    private final SalesPersonService salesPersonService;

    @Autowired
    public SalesPersonController(SalesPersonService salesPersonService) {
        this.salesPersonService = salesPersonService;
    }

    @GetMapping("/getSalesPersons")
    public ResponseEntity getSalesPersons() throws MissingSalesPersonException {
        return ResponseEntity.ok(salesPersonService.getSalesPersons());
    }

    @PutMapping("/updateSalesPersons")
    public ResponseEntity updateSalesPersons(@RequestBody SalesPersonEntity requestBody) throws MissingSalesPersonException {
        log.info("Updating sales persons.");
        Optional<SalesPersonEntity> found = salesPersonService.findBySalesPersonId(requestBody.getId());
        if(found.isEmpty()) {
            log.info("This sales person is not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sales person not found.");
        }
        salesPersonService.updateSalesPerson(requestBody);
        return ResponseEntity.ok(requestBody);
    }

}
