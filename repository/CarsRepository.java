package com.app.repository;

import com.app.carsServices.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CarsRepository extends JpaRepository<Cars, Long> {
    @Query(
            "Select c from Cars c " +
                    "join c.brand b " +
                    "join c.transmission t " +
                    "join c.model m " +
                    "join c.year y " +
                    "join c.price p " +
                    "join c.fuelType f " +
                    "join c.km_driven km " +
                    "where b.name = :details or t.type = :details or m.name = :details or y.year = :details" +
                    " or p.price = :details or f.fuel_type = :details or km.driven_range = :details")

     List<Cars> searchCar(
            @Param("details") String details
    );

}