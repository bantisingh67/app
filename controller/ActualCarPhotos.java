package com.app.controller;

import com.app.evaluation.CarEvalutionPhotos;
import com.app.repository.CarEvalutionDetailsRepository;
import com.app.repository.CarEvalutionPhotosRepository;
import com.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/acutalPhotos")
public class ActualCarPhotos {

    private BucketService bucketService;
    private CarEvalutionPhotosRepository carEvaluationsRepository;
    private CarEvalutionDetailsRepository carEvalutionDetailsRepository;

    public ActualCarPhotos(BucketService bucketService, CarEvalutionPhotosRepository carEvaluationsRepository, CarEvalutionDetailsRepository carEvalutionDetailsRepository) {
        this.bucketService = bucketService;
        this.carEvaluationsRepository = carEvaluationsRepository;
        this.carEvalutionDetailsRepository = carEvalutionDetailsRepository;
    }

    //http://localhost:8035/images/upload/file/psa-bucket-reyansh/car/1
    @PostMapping("/upload/file/car/{carId}")
    public ResponseEntity<List<CarEvalutionPhotos>> uploadCarPhotos(
            @RequestParam List<MultipartFile> files,
            @PathVariable long carEvaluationId) {
        if (files.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        CarEvalutionPhotos carEvalution = carEvaluationsRepository.findById(carEvaluationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "CarEvaluation not found with ID: " + carEvaluationId));
        // Upload files to S3
        List<String> fileUrls = bucketService.uploadMultipleFiles(files);

        // Save image URLs in the database
        List<CarEvalutionPhotos> savedPhotos = new ArrayList<>();
            for (String url : fileUrls) {
                CarEvalutionPhotos photo = new CarEvalutionPhotos();
                photo.setPhotoUrl(url);
                photo.setCarEvalutionDetails(photo.getCarEvalutionDetails());
                savedPhotos.add(carEvaluationsRepository.save(photo));
            }
            return new ResponseEntity<>(savedPhotos, HttpStatus.OK);
        }

    }




