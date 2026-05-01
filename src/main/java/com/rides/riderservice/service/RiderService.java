package com.rides.riderservice.service;

import com.rides.riderservice.dto.RiderRequestDTO;
import com.rides.riderservice.dto.RiderResponseDTO;

import java.util.List;

public interface RiderService {

    RiderResponseDTO createRider(RiderRequestDTO requestDTO);

    List<RiderResponseDTO> getAllRiders();

    RiderResponseDTO getRiderById(Long id);

    RiderResponseDTO updateRider(Long id, RiderRequestDTO requestDTO);

    void deleteRider(Long id);

}
