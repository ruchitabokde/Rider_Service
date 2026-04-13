package com.rides.riderservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class RiderResponseDTO {

    private UUID riderId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private LocalDateTime createdAt;

    public RiderResponseDTO(UUID riderId, String name, String email, String phone, String city, LocalDateTime createdAt) {
        this.riderId = riderId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.createdAt = createdAt;
    }

    public UUID getRiderId() {
        return riderId;
    }

    public void setRiderId(UUID riderId) {
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