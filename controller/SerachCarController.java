package com.app.controller;

import com.app.carsServices.Cars;
import com.app.repository.CarsRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search-car")
public class SerachCarController {
    private CarsRepository carsRepository;

    public SerachCarController(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    //http://localhost:8035/api/v1/search-car/cars?param=honda
    @GetMapping("/cars")
    public List<Cars> searchCars(
            @RequestParam String param
    ) {

        return carsRepository.searchCar(param);
    }
}

//    @PostMapping("/upload")
//    public ResponseEntity<Car_Image> uploadFile(@RequestParam("file") MultipartFile file) {
//        Car_Image fileEntity = imageService.uploadFile(file);
//        return ResponseEntity.ok(fileEntity);
//    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
//        Car_Image fileEntity = imageService.downloadFile(fileId);

//        Path filePath = Paths.get(fileEntity.getFilePath()).normalize();
//        Resource resource;
//        try {
//            resource = new UrlResource(filePath.toUri());
//        } catch (Exception e) {
//            throw new RuntimeException("File not found: " + fileEntity.getFileName(), e);
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
//                .body(resource);
//    }



//    @Autowired
//    private S3Service s3Service;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = s3Service.uploadFile(file);
//        return ResponseEntity.ok("File uploaded successfully: " + fileName);
//    }
//
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
//        byte[] fileBytes = s3Service.downloadFile(fileName);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .body(fileBytes);
//    }
//}
