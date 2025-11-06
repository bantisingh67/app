package com.app.evaluation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "car_evalution_photos")
public class CarEvalutionPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "car_evalution_details_id")
    private CarEvalutionDetails carEvalutionDetails;

    public CarEvalutionDetails getCarEvalutionDetails() {
        return carEvalutionDetails;
    }

    public void setCarEvalutionDetails(CarEvalutionDetails carEvalutionDetails) {
        this.carEvalutionDetails = carEvalutionDetails;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}