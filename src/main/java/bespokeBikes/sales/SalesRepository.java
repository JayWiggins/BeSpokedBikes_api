package bespokeBikes.sales;

import bespokeBikes.sales.exception.MissingSalesException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends CrudRepository<SalesEntity, String> {
    List<SalesEntity> getAllBy() throws MissingSalesException;

    List<SalesEntity> getSalesEntitiesBySalesDate(String filterDate);
    Optional<SalesEntity> findById(String salesId);
    @Query(value = "SELECT * FROM sales_entity s WHERE STR_TO_DATE(s.sales_date, '%c/%e/%Y') BETWEEN STR_TO_DATE(:startDate, '%c/%e/%Y') AND STR_TO_DATE(:endDate, '%c/%e/%Y')", nativeQuery = true)
    List<SalesEntity> locateSalesInDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Query(value = "SELECT * FROM sales_entity s WHERE STR_TO_DATE(s.sales_date, '%c/%e/%Y') BETWEEN STR_TO_DATE(:startDate, '%c/%e/%Y') AND STR_TO_DATE(:endDate, '%c/%e/%Y') AND s.sales_person_id  = :salesPersonId", nativeQuery = true)
    List<SalesEntity> commissionReport(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("salesPersonId") String salesPersonId);

}
