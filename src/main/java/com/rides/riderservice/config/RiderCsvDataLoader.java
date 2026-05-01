package com.rides.riderservice.config;

import com.rides.riderservice.entity.Rider;
import com.rides.riderservice.repository.RiderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class RiderCsvDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RiderCsvDataLoader.class);
    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final RiderRepository riderRepository;
    private final String csvPath;

    public RiderCsvDataLoader(
            RiderRepository riderRepository,
            @Value("${rider.seed.csv-path:ride_riders.csv}") String csvPath
    ) {
        this.riderRepository = riderRepository;
        this.csvPath = csvPath;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try (BufferedReader reader = openCsvReader()) {
            List<Rider> ridersToImport = parseRiders(reader);
            riderRepository.saveAll(ridersToImport);
            log.info("Rider CSV seed completed. Imported {} riders from {}", ridersToImport.size(), csvPath);
        }
    }

    private BufferedReader openCsvReader() throws IOException {
        Path filePath = Path.of(csvPath);
        if (Files.exists(filePath)) {
            log.info("Loading rider seed data from file: {}", filePath.toAbsolutePath());
            return Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        }

        ClassPathResource resource = new ClassPathResource(csvPath);
        if (resource.exists()) {
            log.info("Loading rider seed data from classpath: {}", csvPath);
            return new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        }

        throw new IllegalStateException("Rider seed CSV not found at file or classpath location: " + csvPath);
    }

    private List<Rider> parseRiders(BufferedReader reader) throws IOException {
        List<Rider> riders = new ArrayList<>();
        String line;
        boolean isHeader = true;

        while ((line = reader.readLine()) != null) {
            if (isHeader) {
                isHeader = false;
                continue;
            }

            if (line.isBlank()) {
                continue;
            }

            String[] columns = line.split(",", -1);
            if (columns.length < 6) {
                log.warn("Skipping malformed rider CSV row: {}", line);
                continue;
            }

            Long riderId = Long.parseLong(columns[0].trim());
            String email = columns[2].trim();
            if (riderRepository.existsById(riderId) || riderRepository.existsByEmail(email)) {
                continue;
            }

            Rider rider = new Rider();
            rider.setRiderId(riderId);
            rider.setName(columns[1].trim());
            rider.setEmail(email);
            rider.setPhone(columns[3].trim());
            rider.setCity(columns[4].trim());
            rider.setCreatedAt(LocalDateTime.parse(columns[5].trim(), CSV_DATE_FORMAT));
            riders.add(rider);
        }

        return riders;
    }
}
