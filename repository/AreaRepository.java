package com.app.repository;

import com.app.evaluation.Area;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AreaRepository extends Repository<Area, Long> {
    List<Area> findByPinCode(int pinCode);
}