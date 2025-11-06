package com.app.repository;

import com.app.evaluation.CarEvalutionPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEvalutionPhotosRepository extends JpaRepository<CarEvalutionPhotos, Long> {
  }