package com.rides.riderservice.service;

import com.rides.riderservice.dto.RiderRequestDTO;
import com.rides.riderservice.dto.RiderResponseDTO;
import com.rides.riderservice.entity.Rider;
import com.rides.riderservice.exception.RiderNotFoundException;
import com.rides.riderservice.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RiderServiceImpl implements RiderService {

    private static final Logger log = LoggerFactory.getLogger(RiderServiceImpl.class);

    private final RiderRepository riderRepository;

    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public RiderResponseDTO createRider(RiderRequestDTO requestDTO) {
        log.info("Creating rider: {}", requestDTO.getEmail());
        Rider rider = new Rider();
        rider.setName(requestDTO.getName());
        rider.setEmail(requestDTO.getEmail());
        rider.setPhone(requestDTO.getPhone());
        rider.setCity(requestDTO.getCity());
        Rider savedRider = riderRepository.save(rider);
        log.info("Rider created with id: {}", savedRider.getRiderId());
        return mapToResponseDTO(savedRider);
    }

    @Override
    public List<RiderResponseDTO> getAllRiders() {
        log.info("Fetching all riders");
        List<Rider> riders = riderRepository.findAll();
        return riders.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public RiderResponseDTO getRiderById(UUID id) {
        log.info("Fetching rider by id: {}", id);
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Rider not found with id: " + id));
        return mapToResponseDTO(rider);
    }

    @Override
    public RiderResponseDTO updateRider(UUID id, RiderRequestDTO requestDTO) {
        log.info("Updating rider with id: {}", id);
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException("Rider not found with id: " + id));
        rider.setName(requestDTO.getName());
        rider.setEmail(requestDTO.getEmail());
        rider.setPhone(requestDTO.getPhone());
        rider.setCity(requestDTO.getCity());
        Rider updatedRider = riderRepository.save(rider);
        log.info("Rider updated with id: {}", updatedRider.getRiderId());
        return mapToResponseDTO(updatedRider);
    }

    @Override
    public void deleteRider(UUID id) {
        log.info("Deleting rider with id: {}", id);
        if (!riderRepository.existsById(id)) {
            throw new RiderNotFoundException("Rider not found with id: " + id);
        }
        riderRepository.deleteById(id);
        log.info("Rider deleted with id: {}", id);
    }

    private RiderResponseDTO mapToResponseDTO(Rider rider) {
        return new RiderResponseDTO(rider.getRiderId(), rider.getName(), rider.getEmail(), rider.getPhone(), rider.getCity(), rider.getCreatedAt());
    }

}