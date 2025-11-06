package com.app.service;

import com.app.carsServices.Brand;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {

    public List<Brand> readExcel(String filePath) {
        List<Brand> entries = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) { // Fixed XSSFWorkbook typo

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            // Read each row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Brand entity = new Brand();

                // Read ID from Column 0 (Ensure it is numeric)
                entity.setId((long) row.getCell(0).getNumericCellValue());
                // Read Name from Column 1
                entity.setName(row.getCell(1).getStringCellValue());
                entries.add(entity);
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }

        return entries;
    }
}
