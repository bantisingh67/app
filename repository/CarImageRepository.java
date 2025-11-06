package com.app.repository;

import com.app.carsServices.CarImage;
import org.springframework.data.repository.Repository;

public interface CarImageRepository extends Repository<CarImage, Long> {
    CarImage save(CarImage image);
}