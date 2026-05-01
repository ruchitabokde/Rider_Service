package com.rides.riderservice.controller;

import com.rides.riderservice.dto.ApiResponse;
import com.rides.riderservice.dto.RiderRequestDTO;
import com.rides.riderservice.dto.RiderResponseDTO;
import com.rides.riderservice.service.RiderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/riders")
public class RiderController {

    private static final Logger log = LoggerFactory.getLogger(RiderController.class);

    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RiderResponseDTO>> createRider(@Valid @RequestBody RiderRequestDTO requestDTO) {
        log.info("Incoming request to create rider");
        RiderResponseDTO response = riderService.createRider(requestDTO);
        log.info("Response status: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Rider created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RiderResponseDTO>>> getAllRiders() {
        log.info("Incoming request to get all riders");
        List<RiderResponseDTO> riders = riderService.getAllRiders();
        log.info("Response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(ApiResponse.success(riders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RiderResponseDTO>> getRiderById(@PathVariable Long id) {
        log.info("Incoming request to get rider by id: {}", id);
        RiderResponseDTO rider = riderService.getRiderById(id);
        log.info("Response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(ApiResponse.success(rider));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RiderResponseDTO>> updateRider(@PathVariable Long id, @Valid @RequestBody RiderRequestDTO requestDTO) {
        log.info("Incoming request to update rider with id: {}", id);
        RiderResponseDTO response = riderService.updateRider(id, requestDTO);
        log.info("Response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(ApiResponse.success(response, "Rider updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRider(@PathVariable Long id) {
        log.info("Incoming request to delete rider with id: {}", id);
        riderService.deleteRider(id);
        log.info("Response status: {}", HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(null, "Rider deleted successfully"));
    }

}
