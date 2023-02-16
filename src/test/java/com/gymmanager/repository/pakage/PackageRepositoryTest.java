package com.gymmanager.repository.pakage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void test_save() {

        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("Powerlifting 15 weeks Program");
        packageEntity.setPeriod(105);

        //when
        packageRepository.save(packageEntity);

        //then
        assertNotNull(packageEntity.getPackageSeq());

    }

    @Test
    public void test_findByCreatedAtAfter(){

        //set
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(2);

        PackageEntity packageEntity0 = new PackageEntity();
        packageEntity0.setPackageName("Student 3 months Program");
        packageEntity0.setPeriod(90);

        packageRepository.save(packageEntity0);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("Worker 5 months Program");
        packageEntity1.setPeriod(150);

        packageRepository.save(packageEntity1);

        //when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        //check
        assertEquals(1, packageEntities.size());
        assertEquals(packageEntity1.getPackageSeq(), packageEntities.get(0).getPackageSeq());



    }

    @Test
    public void test_updateCountAndPeriod(){

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("Gym event for discount");
        packageEntity.setPeriod(7);
        packageRepository.save(packageEntity);


        //when
        int updatedCount = packageRepository.updateCountAndPeriod(packageEntity.getPackageSeq(), 30, 120);
        final PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getPackageSeq()).get();

        //then
        assertEquals(1, updatedCount);
        assertEquals(30, updatedPackageEntity.getCount());
        assertEquals(120, updatedPackageEntity.getPeriod());

    }


    @Test
    public void test_delete(){

        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("Pass to remove");
        packageEntity.setCount(1);
        PackageEntity newPackageEntity = packageRepository.save(packageEntity);

        //when
        packageRepository.deleteById(packageEntity.getPackageSeq());

        //then
        assertTrue(packageRepository.findById(packageEntity.getPackageSeq()).isEmpty());

    }



}