package com.app.controller;

import com.app.carsServices.Brand;
import com.app.repository.BrandRepository;
import com.app.service.BulkUploadBrandNameService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand/bulk-upload")
public class BrandBulkUploadController {
    private BulkUploadBrandNameService uploadBrandNameService;
    private BrandRepository brandRepository;

    public BrandBulkUploadController(BulkUploadBrandNameService uploadBrandNameService, BrandRepository brandRepository) {
        this.uploadBrandNameService = uploadBrandNameService;
        this.brandRepository = brandRepository;
    }
    //http://localhost:8035/api/v1/brand/bulk-upload
    @PostMapping("/upload")
    public String uploadExcelFile() {
        String filePath = "C:/Users/Psm I/Documents/Book2.xlsx";
        try {
            List<Brand> brands = uploadBrandNameService.readExcel(filePath);
            for (Brand brand : brands) {
                try {
                    brandRepository.save(brand);
                }catch(OptimisticLockingFailureException e) {
                System.out.println("Optimistic locking failure: " + brand.getId());
                // Handle confict resolution (e.g., retry, log, etc.
              }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Uploaded";
    }
}
