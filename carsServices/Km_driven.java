package com.app.carsServices;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "km_driven")
public class Km_driven {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "driven_range", nullable = false)
    private String driven_range;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriven_range() {
        return driven_range;
    }

    public void setDriven_range(String driven_range) {
        this.driven_range = driven_range;
    }
}