package com.app.controller;

import com.app.carsServices.CarImage;
import com.app.carsServices.Cars;
import com.app.payload.ImageDto;
import com.app.repository.CarImageRepository;
import com.app.repository.CarsRepository;
import com.app.service.BucketService;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {
    private BucketService bucketService;
    private CarsRepository carsRepository;
    private CarImageRepository carImageRepository;
    public ImageController(BucketService bucketService, CarsRepository carsRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carsRepository = carsRepository;
        this.carImageRepository = carImageRepository;
    }

    //http://localhost:8035/api/v1/images/upload/file/bucket-reyansh/car/1
    @PostMapping("/upload/file/car/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos(
            @RequestParam("file") MultipartFile file,
            @PathVariable long carId) {
        String url = bucketService.uploadFile(file);
        Cars car = carsRepository.findById(carId).get();
        CarImage image = new CarImage();
        image.setUrl(url);
        image.setCars(car);
        CarImage savedImage = carImageRepository.save(image);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }
}

//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<ByteArrayResource> downloadCarImage(@PathVariable String bucketName) {
//        byte[] data = bucketService.downloadFile(bucketName);
//        ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + bucketName + "\"")
//                .body(resource);
//    }
//
//    @DeleteMapping("/delete/{fileName}")
//    public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
//        return new ResponseEntity<>(bucketService.deleteFile(fileName), HttpStatus.OK);
//    }


