package com.gymmanager.repository.user;

import com.gymmanager.repository.pakage.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PackageEntity, Integer> {

}
