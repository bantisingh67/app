package com.app.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

    @Value("${accesskey}")
    private String accessKey;
    @Value("${secretkey}")
    private String accessSecret;
    @Value("${region}")
    private String region;

    public AWSCredentials credentails() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return credentials;
    }
    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3 s3clint = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentails()))
                .withRegion(region).build();
        return s3clint;
    }

}