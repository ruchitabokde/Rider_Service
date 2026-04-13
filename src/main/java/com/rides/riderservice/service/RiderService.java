package com.rides.riderservice.service;

import com.rides.riderservice.dto.RiderRequestDTO;
import com.rides.riderservice.dto.RiderResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RiderService {

    RiderResponseDTO createRider(RiderRequestDTO requestDTO);

    List<RiderResponseDTO> getAllRiders();

    RiderResponseDTO getRiderById(UUID id);

    RiderResponseDTO updateRider(UUID id, RiderRequestDTO requestDTO);

    void deleteRider(UUID id);

}