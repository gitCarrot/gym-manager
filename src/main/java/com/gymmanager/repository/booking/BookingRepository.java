package com.gymmanager.repository.booking;

import com.gymmanager.repository.pakage.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<PackageEntity, Integer> {

}
