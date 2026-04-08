package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface DriverRepository extends JpaRepository<Driver,Long> {
    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id=:id AND license_number = :license") // RAW mysql query , error is thrown at runtime if query has issue
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);
}
