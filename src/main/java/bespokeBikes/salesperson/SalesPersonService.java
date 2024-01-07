package bespokeBikes.salesperson;

import bespokeBikes.salesperson.exception.MissingSalesPersonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SalesPersonService {
    @Autowired
    private SalesPersonRepository salesPersonRepository;

    public SalesPersonService(SalesPersonRepository salesPersonRepository) {
        this.salesPersonRepository = salesPersonRepository;
    }

    public List<SalesPersonEntity> getSalesPersons() throws MissingSalesPersonException {
        log.info("Getting list of sales persons.");
        List<SalesPersonEntity> salesPersonEntities = salesPersonRepository.getAllBy();
        return salesPersonEntities;
    }

    public Optional<SalesPersonEntity> findBySalesPersonId(String id) throws MissingSalesPersonException {
        Optional<SalesPersonEntity> salesPersonEntity = salesPersonRepository.findById(id);
        return salesPersonEntity;
    }


    @Transactional
    public SalesPersonEntity updateSalesPerson(SalesPersonEntity requestBody) throws MissingSalesPersonException {
        SalesPersonEntity salesPerson =  SalesPersonEntity.builder()
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .address(requestBody.getAddress())
                .phone(requestBody.getPhone())
                .startDate(requestBody.getStartDate())
                .terminationDate(requestBody.getTerminationDate())
                .manager(requestBody.getManager())
                .id(requestBody.getId())
                .build();

        log.info("Saving sales person update");
        return salesPersonRepository.save(salesPerson);
    }
}
