package com.app.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BucketService {
    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 amazonS3;

    @Autowired
    public BucketService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));
            //generate and return file url
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    //Multiple car photo Upload
    public List<String> uploadMultipleFiles(List<MultipartFile> files) {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());

                amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));

                String fileUrl = amazonS3.getUrl(bucketName, fileName).toString();
                fileUrls.add(fileUrl);
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
            }
        }

        return fileUrls;
    }
    }

//    public byte[] downloadFile(String fileName) {
//        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
//        try {
//            byte[] content = IOUtils.toByteArray(inputStream);
//            return content;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public String deleteFile(String fileName) {
//        amazonS3.deleteObject(bucketName, fileName);
//        return fileName + " removed ...";
//    }

//    private File convertMultiPartFileToFile(MultipartFile file) {
//        File convertedFile = new File(file.getOriginalFilename());
//        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
//            fos.write(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return convertedFile;
//    }

//}