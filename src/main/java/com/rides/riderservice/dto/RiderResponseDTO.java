package com.rides.riderservice.dto;

import java.time.LocalDateTime;

public class RiderResponseDTO {

    private Long riderId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private LocalDateTime createdAt;

    public RiderResponseDTO(Long riderId, String name, String email, String phone, String city, LocalDateTime createdAt) {
        this.riderId = riderId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.createdAt = createdAt;
    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
