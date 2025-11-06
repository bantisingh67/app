package com.app.repository;

import com.app.evaluation.CustomerVisit;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CustomerVisitRepository extends Repository<CustomerVisit, Long> {
    Optional<CustomerVisit> findById(int customerId);

    void save(CustomerVisit customerVisit);
}